package ProjectIsa.bioskop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import ProjectIsa.bioskop.repository.UserDBRepository;



@SpringBootApplication

//@EntityScan( basePackages = {"ProjectIsa.bioskop.domain"} )
//@ComponentScan(basePackages = { "ProjectIsa.domain","ProjectIsa.controller", "ProjectIsa.repository", "ProjectIsa.service"} )
//@ComponentScan(basePackageClasses = UserDBRepository.class)
public class BioskopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BioskopApplication.class, args);
	}
}
