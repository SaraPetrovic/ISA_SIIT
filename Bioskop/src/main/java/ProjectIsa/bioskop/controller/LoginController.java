package ProjectIsa.bioskop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private HttpServletRequest request;;
	@RequestMapping(
			value="/api/login",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<User> validation(@RequestBody User u) {
		HttpSession session = request.getSession();
		

		String username = u.getUsername();
		String password = u.getPassword();
		User user = loginService.validation(username, password);
		if (user != null) {
			session.setAttribute("user",user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		
	}
}
