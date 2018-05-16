package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.User;

public interface UserDBRepository extends Repository<User, Long> {
	User save(User user);
	List<User> findAll();
	void delete(User user);
	User findByUsername(String username);
	Adresa save(Adresa adresa);
	User findById(long id);
	
	@Query(value = "SELECT temp.* FROM "
			+ "(SELECT u.* FROM isa.user u "
			+ "WHERE u.id IN (SELECT f.userid2 FROM isa.friendship f "
							+ "WHERE f.userid1 = ?1 AND f.status = 0"
						+ ") "
			+ "OR u.id IN (SELECT f.userid1 FROM isa.friendship f "
							+ "WHERE f.userid2 = ?1 AND f.status = 0"
						+ ")"
		+ ") temp",
	nativeQuery = true)
	List<User> getFriendsList(long userID);
}
