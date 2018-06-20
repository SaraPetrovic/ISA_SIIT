package ProjectIsa.bioskop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.domain.UserType;
import ProjectIsa.bioskop.repository.AddressRepository;
import ProjectIsa.bioskop.repository.UserDBRepository;

@Service
public class UserServiceImpl implements UserService {
	private static final String DEFAULT_ADMIN_PASSWORD = "ftn";
	@Autowired
	UserDBRepository userDbRepository;
	@Autowired
	AddressRepository addressRepository;
	@Override
	public Collection<User> getUsers() {
		List<User> users = userDbRepository.findAll();
		return users;
	}
	
	@Override
	public User addUser(User user) {
		//ukoliko vec postoji adresa postavi je korisniku
		Adresa address = addressRepository.findByCityAndStreet(user.getAddress().getCity(), user.getAddress().getStreet());
		if (address != null){
			user.setAddress(address);
		}
		//admini ne moraju da aktiviraju account mailom
		user.setActivated(true);
		user.setIsFirstLogin(true);
		user.setPassword(DEFAULT_ADMIN_PASSWORD);
		if (!EmailValidator.getInstance().isValid(user.getEmail())){
			return null;
		}

		return userDbRepository.save(user);
	}
	@Override
	public void deleteUser(User user) {
		User exists = userDbRepository.findById(user.getId());
		if (exists == null) {
			return;
		}
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
		user.setEmail(changedUser.getEmail());
		userDbRepository.save(user.getAddress());
		userDbRepository.save(user);
		return user;

		
	}

	@Override
	public List<User> findAdmins() {
		// TODO Auto-generated method stub
		List<User> users = userDbRepository.findAll();
		List<User> admins = new ArrayList<User>();
		for (User user : users){
			if (user.getUserType() == UserType.SYSTEMADMIN || user.getUserType() == UserType.FANZONEADMIN || user.getUserType() == UserType.CINEMAADMIN){
				admins.add(user);
			}
		}
		return admins;
	}

	@Override
	public User changePicure(User user, String originalFilename) {
		// TODO Auto-generated method stub
		user.setProfilePicture(originalFilename);
		userDbRepository.save(user);
		return user;
	}

	@Override
	public User getUser(long id) {
		User u = userDbRepository.findById(id);
		if (u == null) {
			return null;
		}
		
		return u;
	}

	@Override
	public List<User> getFriendsOfUser(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("user");
		if (loggedUser == null) {
			return null;
		}
		return userDbRepository.getFriendsList(loggedUser.getId());
		
	}

	@Override
	public List<User> getFriendRequests(User loggedUser) {
		if (loggedUser == null) {
			return null;
		}
		return userDbRepository.getFriendRequests(loggedUser.getId());
	}

	@Override
	public User updateUser(User user) {
		User foundUser = userDbRepository.findByUsername(user.getUsername());
		if (foundUser == null) {
			return null;
		}
		return userDbRepository.save(user);

	}
	@Override
	public User getUserByUsername(String username){
		return userDbRepository.findByUsername(username);
	}
	



}
