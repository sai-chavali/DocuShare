package ai.typeface.documentShare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ai.typeface.documentShare.repository.entities"})
public class DocumentShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentShareApplication.class, args);
	}

}
