package ProjectIsa.bioskop.service;


import java.util.Collection;

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
		Collection<User> allUsers = repository.findAll();
		for (User u : allUsers) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		
		}
		return null;
	}
}
