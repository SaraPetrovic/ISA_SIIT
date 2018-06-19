package ProjectIsa.bioskop.service;

import java.util.Collection;

import ProjectIsa.bioskop.domain.MovieOrPerformance;

public interface MovieOrPerformanceServiceInterface {
	Collection<MovieOrPerformance> getAll();
	String add(MovieOrPerformance projection);
	void delete(MovieOrPerformance projection);
	MovieOrPerformance findById(Long id);
	MovieOrPerformance findByName(String name);
	
	String changeMovie(MovieOrPerformance movie, MovieOrPerformance newMovie);
}
