package ai.typeface.documentShare.config;

import com.azure.spring.cloud.autoconfigure.aad.filter.AadAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Configuration
public class JWTSecurityConfig {
    @Value("${app.protected.routes}")
    private String[] protectedRoutes;
    @Value("${app.whiteListed.routes}")
    private String[] whiteListedRoutes;

    @Autowired
    private AadAuthenticationFilter aadAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and().authorizeRequests().antMatchers(protectedRoutes).hasAnyRole("USER")
                .and()
                .authorizeRequests().antMatchers(whiteListedRoutes).permitAll()
                .and()
                .addFilterBefore(aadAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CustomEmptyBearerTokenFilter(), AadAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    class CustomEmptyBearerTokenFilter extends HttpFilter {
        @Override
        public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
            String path = request.getServletPath();
            if(Arrays.stream(whiteListedRoutes).noneMatch(route -> new AntPathMatcher().match(route, path)) &&
                    Arrays.stream(protectedRoutes).anyMatch(route -> new AntPathMatcher().match(route, path))){
                String auth = request.getHeader("Authorization");
                if(!auth.startsWith("Bearer")){
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    return;
                }
                chain.doFilter(request, response);
            }
        }
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
