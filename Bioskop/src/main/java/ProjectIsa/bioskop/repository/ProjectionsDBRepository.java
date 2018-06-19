package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import ProjectIsa.bioskop.domain.Projection;

public interface ProjectionsDBRepository extends Repository<Projection, Long>{
	
	Projection save(Projection Projection);
	List<Projection> findAll();
	void delete(Projection Projection);
	Projection findById(Long id);
	Projection findByName(String name);
	
	@Query(value = "SELECT p.* FROM isa.projection p WHERE p.theater_or_cinema_id = ?1",
				nativeQuery = true)
	List<Projection> getAllByCinemaId(Long id);

}
