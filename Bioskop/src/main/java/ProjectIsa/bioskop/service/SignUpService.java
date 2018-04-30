package ProjectIsa.bioskop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.User;

@Service
public class SignUpService implements SignUpServiceInterface {

	@Autowired
	UserService userService;
	
	@Override
	public User validation(User newUserData) {
		
	
		User u = userService.getUser(newUserData.getUsername());
		
		if (u == null) {
			userService.addUser(newUserData);
			return newUserData;
		} else {
			return null;
		}
		
	}
	
	

}
