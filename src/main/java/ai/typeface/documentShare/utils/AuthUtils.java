package ai.typeface.documentShare.utils;


import com.azure.spring.cloud.autoconfigure.aad.filter.UserPrincipal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public interface AuthUtils {
    static Map<String, String> filterClaims(UserPrincipal userPrincipal) {
        Map<String, String> filterClaims = new HashMap<>();
        Arrays.asList(AuthClaims.values()).forEach(claim -> {
            if(userPrincipal.getClaims().containsKey(claim.getLabel()))
                filterClaims.put(claim.getLabel(), userPrincipal.getClaims().get(claim.getLabel()).toString());
        });
        return filterClaims;
    }
}
