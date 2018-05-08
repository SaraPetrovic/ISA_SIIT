package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import ProjectIsa.bioskop.domain.MovieOrPerformance;

public interface MovieDBRepository extends Repository<MovieOrPerformance, Long>{
	MovieOrPerformance save(MovieOrPerformance movie);
	List<MovieOrPerformance> findAll();
	void delete(MovieOrPerformance movie);
	MovieOrPerformance findByName(String name);
	MovieOrPerformance findById(Long id);
}
