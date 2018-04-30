package ProjectIsa.bioskop.repository;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import ProjectIsa.bioskop.domain.MovieOrPerformance;

@Repository
public class MovieOrPerformanceRepository implements MovieOrPerformanceInterface{

	private Collection<MovieOrPerformance> projections = new ArrayList<MovieOrPerformance>();
	
	@Override
	public Collection<MovieOrPerformance> getMovieOrPerformances() {
		return projections;
	}

	@Override
	public MovieOrPerformance addMovieOrPerformance(MovieOrPerformance projection) {
		projections.add(projection);
		return projection;
	}

	@Override
	public void deleteMovieOrPerformance(MovieOrPerformance projection) {
		projections.remove(projection);		
	}

	@Override
	public MovieOrPerformance getMovieOrPerformance(Long id) {
		for (MovieOrPerformance p: projections){
			if (p.getId() == id){
				return p;
			}
		}
		return null;
	}
	

}
