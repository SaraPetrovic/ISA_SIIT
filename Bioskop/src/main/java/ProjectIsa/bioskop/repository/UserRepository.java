package ProjectIsa.bioskop.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.User;

@Repository
public class UserRepository implements UserInterface {
	private Collection<User> users = new ArrayList<User>();
	@Override
	public Collection<User> getUsers() {
		return users;	
	}
	@Override
	public User addUser(User user) {
		for (User u : users){
			if (u.getUsername().equals(user.getUsername())){
				return null;
			}
		}
		users.add(user);
		return user;
	}
	

	@Override
	public void deleteUser(User user) {
		users.remove(user);
		
	}
	@Override
	public User getUser(String id) {
		for (User u : users){
			if (u.getUsername().equals(id)){
				return u;
			}
		}
		return null;
	}
	@Override
	public Adresa addAddress(Adresa address) {
		// TODO Auto-generated method stub
		return null;
	}
}
