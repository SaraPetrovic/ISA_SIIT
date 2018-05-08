package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import ProjectIsa.bioskop.domain.Hall;

public interface HallDBRepository extends Repository<Hall, Long> {
	Hall save(Hall hall);
	List<Hall> findAll();
	void delete(Hall hall);
	Hall findByName(String name);
	Hall findById(Long id);
}
