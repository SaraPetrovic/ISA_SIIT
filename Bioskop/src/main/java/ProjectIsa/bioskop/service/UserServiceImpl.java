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
	
	@Autowired
	UserDBRepository userDbRepository;
	
	@Override
	public Collection<User> getUsers() {
		List<User> users = userDbRepository.findAll();
		return users;
	}
	
	@Override
	public User addUser(User user) {
		User newUser = userDbRepository.save(user);
		return newUser;
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
	



}
