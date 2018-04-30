package ProjectIsa.bioskop.repository;

import java.util.Collection;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.User;

public interface UserInterface {
	Collection<User> getUsers();
	User addUser(User user);
	void deleteUser(User user);
	User getUser(String id);
	Adresa addAddress(Adresa address);
	
}
