package ProjectIsa.Bioskop;

import static org.assertj.core.api.Assertions.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.domain.UserType;
import ProjectIsa.bioskop.service.UserService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	
	
	@Autowired
	UserService userService;

/*
	Anotacija @Test naznačava Springu da će se anotirana metoda izvrši prilikom testiranja.
	Ukoliko se ona izostavi, test metoda se neće izvršiti.

*/	@Before
	public void init(){
		User u = new User("Marko", "Markovic", "Marko", "1234", UserType.REGISTEREDUSER, new Adresa("asd", "asd"), "asd@asd");
		User u1 = new User("Marko", "Markovic", "Marko1", "1234", UserType.REGISTEREDUSER, new Adresa("asd", "asd"), "asd@asd");
		userService.addUser(u);
		userService.addUser(u1);
	}

	@Test
	public void testFindAll() {
		Collection<User> users = userService.getUsers();
		assertThat(users).hasSize(2);	
	}
	@Test
	public void testFindOne(){
		User u = userService.getUser("Marko1");
		assertThat(u).isNotNull();
		User u2 = userService.getUser("asdasd");
		assertThat(u2).isNull();
	}
	@Test
	public void testAdd(){
		User user = new User("asd", "asd", "asd","asd", UserType.SYSTEMADMIN, new Adresa("asd", "asd"), "asd@asd");
		Collection<User> users = userService.getUsers();
		int sizeBeforeAdd = users.size();
		userService.addUser(user);

		
		
		assertThat(users).hasSize(sizeBeforeAdd + 1);
		users.remove(user);
		
	}
	@Test
	public void testFirstTimeLogin(){
		//default admin inserted in DB password = "ftn"
		User user = userService.getUser("ftn");
		String passwordBefore = user.getPassword();
		//password has only 3 characters
		userService.changePassword(user, "asd");
		String passwordAfter = user.getPassword();
		assertThat(passwordBefore.equals(passwordAfter));
	}
	@Test
	public void testFirstTimeLogin2(){
		//default admin inserted in DB password = "ftn"
		User user = userService.getUser("ftn");
		String passwordBefore = user.getPassword();
		//password is same as default admin password - FAIL
		userService.changePassword(user, "ftn");
		String passwordAfter = user.getPassword();
		assertThat(passwordBefore.equals(passwordAfter));
	}
	@Test
	public void testFirstTimeLogin3(){
		//default admin inserted in DB password = "ftn"
		User user = userService.getUser("ftn");
		String passwordBefore = user.getPassword();
		//password - SUCCESS
		userService.changePassword(user, "123456789");
		String passwordAfter = user.getPassword();
		assertThat(!passwordBefore.equals(passwordAfter));
	}
		
	

	
}
