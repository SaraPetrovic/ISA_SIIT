package ProjectIsa.bioskop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication


@EnableTransactionManagement
@EnableJpaRepositories
public class BioskopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BioskopApplication.class, args);
	}
}
