package ProjectIsa.bioskop.controller;

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
import ProjectIsa.bioskop.service.SignUpService;

@RestController
public class SignUpController {
	
	@Autowired
	private SignUpService signUpService;
	
	@RequestMapping(
			value="/api/signup",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<User> validation(@RequestBody User u) {
		
		
		User user = signUpService.validation(u);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			// tj trebalo bi found kao, posto vec postoji pa ne moze da se registruje
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(
			value = "/api/signup/confirm={code}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<User> accountConfirmation(@PathVariable("code") String code) {
		
		User activatedUser = signUpService.activateUser(code);
		if (activatedUser == null) {
			return new ResponseEntity<User>(activatedUser, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(activatedUser, HttpStatus.OK);
	}
}
