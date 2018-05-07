package ProjectIsa.bioskop.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.repository.UserDBRepository;
import org.apache.commons.validator.routines.EmailValidator;

@Service
public class UserServiceImpl implements UserService {
	private static final String DEFAULT_ADMIN_PASSWORD = "ftn";
	@Autowired
	UserDBRepository userDbRepository;
	
	@Override
	public Collection<User> getUsers() {
		List<User> users = userDbRepository.findAll();
		return users;
	}
	
	@Override
	public User addUser(User user) {
		if (user.getAddress() != null){
			addAddress(user.getAddress());
		}
		user.setIsFirstLogin(true);
		if (user.getPassword() == null){
			user.setPassword(DEFAULT_ADMIN_PASSWORD);
		}
		User existingUser = userDbRepository.findByUsername(user.getUsername());
		if (existingUser == null){
			User newUser = userDbRepository.save(user);
			return newUser;
			
		}
		return null;
	}
	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userDbRepository.delete(user);
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		User user = userDbRepository.findByUsername(username);
		return user;
	}

	@Override
	public Adresa addAddress(Adresa address) {
		// TODO Auto-generated method stub
		Adresa newAddress = userDbRepository.save(address);
		return newAddress;
	}

	@Override
	public Boolean changePassword(User user, String newPassword) {
		if (!newPassword.equals(DEFAULT_ADMIN_PASSWORD) && newPassword.length() > 8){
			user.setPassword(newPassword);
			user.setIsFirstLogin(false);
			//save in DB
			userDbRepository.save(user);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public User changeProfile(User user, User changedUser) {
		//check if new username exists
		
		if (!user.getUsername().equals(changedUser.getUsername())){
			User u = userDbRepository.findByUsername(changedUser.getUsername());
			if (u != null){
				return null;
			}
		}
		if (!EmailValidator.getInstance().isValid(changedUser.getEmail())){
			return null;
		}
		if (!user.getPassword().equals(changedUser.getPassword())){
			if (changedUser.getPassword().length() < 8){
				return null;
			}
		}
		user.setUsername(changedUser.getUsername());
		user.setAddress(changedUser.getAddress());
		user.setPassword(changedUser.getPassword());
		user.setFirstName(changedUser.getFirstName());
		user.setLastName(changedUser.getLastName());
		userDbRepository.save(user.getAddress());
		userDbRepository.save(user);
		return user;

		
	}
	



}
