package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.MovieOrPerformance;
import ProjectIsa.bioskop.repository.MovieOrPerformanceRepository;

@Service
public class MovieOrPerformanceServiceImpl implements MovieOrPerformanceServiceInterface{
	@Autowired
	MovieOrPerformanceRepository repository;
	
	@Override
	public Collection<MovieOrPerformance> getMovieOrPerformances() {
		Collection<MovieOrPerformance> projections = repository.getMovieOrPerformances();
		return projections;
	}

	@Override
	public MovieOrPerformance addMovieOrPerformance(MovieOrPerformance projection) {
		repository.addMovieOrPerformance(projection);
		return projection;
	}

	@Override
	public void deleteMovieOrPerformance(MovieOrPerformance projection) {
		repository.deleteMovieOrPerformance(projection);
		
	}

	@Override
	public MovieOrPerformance getMovieOrPerformance(Long id) {
		MovieOrPerformance proj = repository.getMovieOrPerformance(id);
		return proj;
	}

}
