package ProjectIsa.bioskop.service;

import java.util.UUID;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.ConfirmationCode;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.repository.ConfirmationCodesDBRepository;

@Service
public class SignUpService implements SignUpServiceInterface {

	final String nameRegex = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
	
	
	@Autowired
	UserService userService;
	@Autowired
	EmailServiceImpl emailService;
	@Autowired
	ConfirmationCodesDBRepository codesRepository;
	
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
			User added = userService.addUser(newUserData);
			this.generateConfirmationCode(added);
			return newUserData;
		} else {
			return null;
		}
		
	}

	@Override
	public String generateConfirmationCode(User newUser) {
		final String UUID_CODE = UUID.randomUUID().toString().replace("-", "");
		long userID = userService.getUser(newUser.getUsername()).getId();
		ConfirmationCode code = new ConfirmationCode(userID, UUID_CODE);
		codesRepository.save(code);
		
		String text = "\nHello " + newUser.getFirstName() + " " + newUser.getLastName() + "! Please click on the following link"
				+ " to activate your account! \n"
				+ "<a href=\"localhost:9004/api/signup/confirm=" + UUID_CODE + "/a>";
		emailService.sendSimpleMessage(newUser.getEmail(), "Confirmation link", text);
		
		return UUID_CODE;
	}

	@Override
	public User activateUser(String code) {
		ConfirmationCode cc = codesRepository.findByCode(code);
		
		if (cc == null) {
			return null;
		}
		
		User activatedUser = userService.getUser(cc.getUserID());
		activatedUser.setActivated(true);
		User savedActivatedUser = userService.updateUser(activatedUser);
		if (savedActivatedUser == null) {
			return null;
		}
		
		codesRepository.delete(cc);
		
		return savedActivatedUser;
	}
	
	

}
