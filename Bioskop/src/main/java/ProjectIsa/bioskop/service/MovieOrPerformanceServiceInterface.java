package ProjectIsa.bioskop.service;

import java.util.Collection;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.MovieOrPerformance;

public interface MovieOrPerformanceServiceInterface {
	Collection<MovieOrPerformance> getAll();
	MovieOrPerformance add(MovieOrPerformance projection);
	void delete(MovieOrPerformance projection);
	MovieOrPerformance findById(Long id);
}
