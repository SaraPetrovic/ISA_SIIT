package ProjectIsa.bioskop.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.repository.UserDBRepository;

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
	public User getUser(String id) {
		// TODO Auto-generated method stub
		User user = userDbRepository.findByUsername(id);
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
		String response = "";
		if (!user.getPassword().equals(newPassword) && newPassword.length() > 8){
			user.setPassword(newPassword);
			user.setIsFirstLogin(false);
			//save in DB
			addUser(user);
			return true;
		}else{
			return false;
		}
	}
	



}
