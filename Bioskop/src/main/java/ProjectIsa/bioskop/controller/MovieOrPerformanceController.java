package ProjectIsa.bioskop.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ProjectIsa.bioskop.domain.ChangedMovie;
import ProjectIsa.bioskop.domain.MovieOrPerformance;
import ProjectIsa.bioskop.service.MovieOrPerformanceServiceImpl;

@RestController
public class MovieOrPerformanceController {

	@Autowired 
	MovieOrPerformanceServiceImpl serviceMovie;
	
	@RequestMapping(
			value = "/api/movies",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<MovieOrPerformance>> getMovies() {
	
	Collection<MovieOrPerformance> movies = serviceMovie.getAll();
	
	if(movies == null) {
		return new ResponseEntity<Collection<MovieOrPerformance>>(movies, HttpStatus.BAD_REQUEST);
	}else {
		return new ResponseEntity<Collection<MovieOrPerformance>>(movies, HttpStatus.OK);
	}

	}
	
	@RequestMapping(
			value= "/api/movies/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<MovieOrPerformance> getMovie(@PathVariable("id") Long id){
		
		MovieOrPerformance movie = serviceMovie.findById(id);
		if (movie != null){
			return new ResponseEntity<MovieOrPerformance>(movie, HttpStatus.OK); 
		}else{
			return new ResponseEntity<MovieOrPerformance>(movie, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(
			value = "/api/createMovie",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<MovieOrPerformance> addMovie(@RequestBody MovieOrPerformance movie){
		MovieOrPerformance newMovie = serviceMovie.add(movie);
		
		if(newMovie == null) {
			return new ResponseEntity<MovieOrPerformance>(newMovie, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<MovieOrPerformance>(newMovie, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "api/changeMovie", 
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<MovieOrPerformance> changeInstitution(@RequestBody ChangedMovie changeMovie){
		
		MovieOrPerformance movie = serviceMovie.findByName(changeMovie.getSelectMovie());
		MovieOrPerformance newMovie = new MovieOrPerformance();
		newMovie.setName(changeMovie.getName());
		newMovie.setActors(changeMovie.getActors());
		newMovie.setType(changeMovie.getType());
		newMovie.setProducer(changeMovie.getProducer());
		newMovie.setFilmDuration(changeMovie.getFilmDuration());
		newMovie.setImg(changeMovie.getImg());
		newMovie.setDescription(changeMovie.getDescription());
		
		MovieOrPerformance returnMovie = serviceMovie.changeMovie(movie, newMovie);
		return new ResponseEntity<MovieOrPerformance>(returnMovie, HttpStatus.OK);
}
}
