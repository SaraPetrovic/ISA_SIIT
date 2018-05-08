package ProjectIsa.bioskop.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.User;

@Service
public class SignUpService implements SignUpServiceInterface {

	final String nameRegex = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
	
	
	@Autowired
	UserService userService;
	
	@Override
	public User validation(User newUserData) {
		
		if (!newUserData.getFirstName().matches(nameRegex) ||
				!newUserData.getLastName().matches(nameRegex) ||
				newUserData.getUsername().equals("") ||
				newUserData.getPassword().equals("") ||
				!EmailValidator.getInstance().isValid(newUserData.getEmail()) ||
				newUserData.getAddress().equals(null)) {
			return null;
		}
	
		User u = userService.getUser(newUserData.getUsername());
		
		if (u == null) {
			userService.addUser(newUserData);
			return newUserData;
		} else {
			return null;
		}
		
	}
	
	

}
