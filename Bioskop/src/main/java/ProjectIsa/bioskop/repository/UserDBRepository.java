package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.User;

public interface UserDBRepository extends Repository<User, Long> {
	User save(User user);
	List<User> findAll();
	void delete(User user);
	User findByUsername(String username);
	Adresa save(Adresa adresa);
}
