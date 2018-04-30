package ProjectIsa.Bioskop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.domain.UserType;
import ProjectIsa.bioskop.service.SignUpService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpServiceTest {

	@Autowired
	SignUpService signupService;
	
	@Test
	public void testSignUp() {
		User u = new User("Ime", "Prezime", "admin", "1234", UserType.REGISTEREDUSER, 
				new Adresa("grad", "uluca"), "email@gmail.com");
		
		// user already exists (main system admin)
		User signUpFailed = signupService.validation(u);
		assertThat(signUpFailed).isNull();
		
		// empty fields -> fail
		signUpFailed.setEmail("");
		assertThat(signUpFailed).isNull();
		
		// same, address=null
		signUpFailed.setAddress(null);
		signUpFailed.setEmail("email@gmail.com");
		assertThat(signUpFailed).isNull();
		
		// sign up succeeded (username is too short but that will be handled on frontend)
		u.setUsername("v");
		User signUpSucceeded = signupService.validation(u);
		assertThat(signUpSucceeded).isNotNull();
		
	}
}
