package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.repository.UserRepository;

@Service
public class SignUpService implements SignUpServiceInterface {

	@Autowired
	UserRepository repository;
	
	@Override
	public User validation(User newUserData) {
		Collection<User> allUsers = repository.getUsers();
		
		for (User u : allUsers) {
			if (u.getUsername().equals(newUserData.getUsername())) {
				return null;
			}
		}
		
		repository.addUser(newUserData);
		return newUserData;
	}
	
	

}
