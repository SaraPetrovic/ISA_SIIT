package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.MovieOrPerformance;
import ProjectIsa.bioskop.repository.MovieDBRepository;

@Service
public class MovieOrPerformanceServiceImpl implements MovieOrPerformanceServiceInterface{
	@Autowired
	MovieDBRepository repository;
	
	@Override
	public Collection<MovieOrPerformance> getAll() {
		Collection<MovieOrPerformance> projections = repository.findAll();
		return projections;
	}

	@Override
	public MovieOrPerformance add(MovieOrPerformance projection) {
		MovieOrPerformance exsisting = repository.findByName(projection.getName());
		if(exsisting == null) {
			repository.save(projection);
			return projection;
		}
		return null;
	}

	@Override
	public void delete(MovieOrPerformance projection) {
		repository.delete(projection);
		
	}

	@Override
	public MovieOrPerformance findById(Long id) {
		MovieOrPerformance proj = repository.findById(id);
		return proj;
	}

}
