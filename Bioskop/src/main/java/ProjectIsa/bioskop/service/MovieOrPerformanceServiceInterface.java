package ProjectIsa.bioskop.service;

import java.util.Collection;

import ProjectIsa.bioskop.domain.MovieOrPerformance;

public interface MovieOrPerformanceServiceInterface {
	Collection<MovieOrPerformance> getMovieOrPerformances();
	MovieOrPerformance addMovieOrPerformance(MovieOrPerformance projection);
	void deleteMovieOrPerformance(MovieOrPerformance projection);
	MovieOrPerformance getMovieOrPerformance(Long id);
}
