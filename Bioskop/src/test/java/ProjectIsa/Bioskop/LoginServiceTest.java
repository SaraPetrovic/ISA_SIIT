package ProjectIsa.Bioskop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.service.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {

	@Autowired
	LoginService loginService;
	
	@Test
	public void testLogin() {
		User notLoggedUser = loginService.validation("admin", "admin");
		assertThat(notLoggedUser).isNull();
		User loggedUser = loginService.validation("a", "ftn");
		assertThat(loggedUser).isNotNull();
	}
	
}
