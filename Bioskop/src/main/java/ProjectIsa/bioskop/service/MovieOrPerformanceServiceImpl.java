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
	public MovieOrPerformance add(MovieOrPerformance movie) {
		
		if(movie.getName() == "" || movie.getType() == "" || movie.getProducer() == "" || movie.getActors() == "" 
				|| movie.getDescription() == "" || movie.getFilmDuration() == 0) {
			return null;
		}
		
		MovieOrPerformance exsisting = repository.findByName(movie.getName());
		if(exsisting == null) {
			repository.save(movie);
			return movie;
		}
		return null;
	}

	@Override
	public void delete(MovieOrPerformance movie) {
		repository.delete(movie);
		
	}

	@Override
	public MovieOrPerformance findById(Long id) {
		MovieOrPerformance proj = repository.findById(id);
		return proj;
	}

	@Override
	public MovieOrPerformance findByName(String name) {
		return repository.findByName(name);
	}

	public MovieOrPerformance changeMovie(MovieOrPerformance movie, MovieOrPerformance newMovie) {

		if (!movie.getName().equals(newMovie.getName())){
			MovieOrPerformance mov = repository.findByName(newMovie.getName());
			if (mov != null){
				return null;
			}
		}
		movie.setName(newMovie.getName());
		movie.setActors(newMovie.getActors());
		movie.setType(newMovie.getType());
		movie.setProducer(newMovie.getProducer());
		movie.setFilmDuration(newMovie.getFilmDuration());
		movie.setImg(newMovie.getImg());
		movie.setDescription(newMovie.getDescription());
		repository.save(movie);
		return movie;
	}

}
