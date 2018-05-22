package ProjectIsa.bioskop.service;

import ProjectIsa.bioskop.domain.User;

public interface SignUpServiceInterface {

	User validation(User newUserData);
	
	String generateConfirmationCode(User newUser);
}
