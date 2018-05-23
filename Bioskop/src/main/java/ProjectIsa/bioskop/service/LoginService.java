package ProjectIsa.bioskop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.repository.UserDBRepository;



@Service
public class LoginService implements LoginServiceInterface {

	
	@Autowired
	UserDBRepository repository;
	
	@Override
	public User validation(String username, String password) {

		User u = repository.findByUsername(username);
		if (u == null) {
			return null;
		}
		
		if (u.getUsername().equals(username) && u.getPassword().equals(password) && u.isActivated()) {
			return u;
		}
		
		return null;
	}
}
