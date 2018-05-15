package ProjectIsa.bioskop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ProjectIsa.bioskop.repository.UserDBRepository;



@SpringBootApplication


@EnableTransactionManagement
@EnableJpaRepositories
public class BioskopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BioskopApplication.class, args);
	}
}
