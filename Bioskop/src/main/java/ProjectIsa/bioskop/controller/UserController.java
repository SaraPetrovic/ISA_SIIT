package ProjectIsa.bioskop.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.service.UserService;

@RestController
public class UserController {
	private HttpServletRequest request;
	@Autowired
	private UserService userService;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(
			value = "/api/users",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers() {
		
		
		Collection<User> users = userService.getUsers();
		

		return new ResponseEntity<Collection<User>>(users,
				HttpStatus.OK);
	}
	@RequestMapping(
			value= "/api/users/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") String id){
		
		User user = userService.getUser(id);
		if (user != null){
			return new ResponseEntity<User>(user, HttpStatus.OK); 
		}else{
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(
			value = "/api/users",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user){

		if (user.getAddress() != null){
			userService.addAddress(user.getAddress());
		}
		User newUser = userService.addUser(user);
		
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}
	@RequestMapping(value = "/api/changePassword", 
					produces = MediaType.APPLICATION_JSON_VALUE,
					method = RequestMethod.POST
					)
	public ResponseEntity<String> changePassword(@RequestBody String newPassword){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Boolean success = userService.changePassword(user, newPassword);
		if (success){
			return new ResponseEntity<String>("Sifra je uspesno promenjena", HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("Sifra nije promenjena", HttpStatus.CONFLICT);
		}
	}
}