package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import ProjectIsa.bioskop.domain.Projection;

public interface ProjectionsDBRepository extends Repository<Projection, Long>{
	
	Projection save(Projection Projection);
	List<Projection> findAll();
	void delete(Projection Projection);
	Projection findById(Long id);
	

}
