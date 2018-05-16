package ProjectIsa.bioskop.service;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.User;

public interface UserService {
	
	Collection<User> getUsers();
	
	User addUser(User user);
	
	Adresa addAddress(Adresa address);
	void deleteUser(User user);
	User getUser(String id);

	Boolean changePassword(User user, String newPassword);
	List<User> findAdmins();
	User changeProfile(User user, User changedUser);

	User changePicure(User user, String originalFilename);
	
	User getUser(long id);
	
	List<User> getFriendsOfUser(HttpServletRequest request);
	
}
