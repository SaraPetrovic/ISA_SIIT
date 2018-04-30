package ProjectIsa.bioskop.repository;


import java.util.Collection;

import ProjectIsa.bioskop.domain.MovieOrPerformance;

public interface MovieOrPerformanceInterface {
	Collection<MovieOrPerformance> getMovieOrPerformances();
	MovieOrPerformance addMovieOrPerformance(MovieOrPerformance projection);
	void deleteMovieOrPerformance(MovieOrPerformance projection);
	MovieOrPerformance getMovieOrPerformance(Long id);
	
}
