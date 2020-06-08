package GenLibUsecase.GenUsecase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //  Enabling JPA Auditing
public class GenUsecaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenUsecaseApplication.class, args);
	}
}