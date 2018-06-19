package ProjectIsa.bioskop.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ProjectIsa.bioskop.domain.MovieOrPerformance;
import ProjectIsa.bioskop.domain.PoluProjection;
import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.service.HallServiceImpl;
import ProjectIsa.bioskop.service.MovieOrPerformanceServiceImpl;
import ProjectIsa.bioskop.service.ProjectionServiceImpl;
import ProjectIsa.bioskop.service.TheaterOrCinemaService;

@RestController
public class ProjectionController {
	
	@Autowired
	ProjectionServiceImpl service;
	@Autowired
	HallServiceImpl hallService;
	@Autowired
	MovieOrPerformanceServiceImpl movieService;
	@Autowired
	TheaterOrCinemaService cinemaService;
	
	@RequestMapping(
					value = "/api/projections",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Projection>> getProjections() {
		
		
		Collection<Projection> projections = service.getProjections();

		if(projections == null) {
			return new ResponseEntity<Collection<Projection>>(projections, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Collection<Projection>>(projections, HttpStatus.OK);
		}

		
	}
	
	@RequestMapping(
			value= "/api/projectionss/{cinemaid}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<Projection>> getCinemasProjections(@PathVariable("cinemaid") Long id) {
		List<Projection> projections = service.getCinemasProjections(id);
		if (projections == null) {
			System.out.println("WTF??");
			return new ResponseEntity<List<Projection>>(projections, HttpStatus.BAD_REQUEST);
		}
		System.out.println("TTT");
		return new ResponseEntity<List<Projection>>(projections, HttpStatus.OK);
	}
	
	@RequestMapping(
			value= "/api/projections/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<Projection> getProjection(@PathVariable("id") Long id){
		
		Projection projection = service.getProjection(id);
		if (projection != null){
			return new ResponseEntity<Projection>(projection, HttpStatus.OK); 
		}else{
			return new ResponseEntity<Projection>(projection, HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(
			value = "/api/createProjection",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<Projection> addProjection(@RequestBody Projection projection){
		
		String projDate = projection.getDate().split("T")[0] + " " + projection.getDate().split("T")[1];
		String datum = projDate.split(" ")[0];
		String vreme = projDate.split(" ")[1];
		projection.setName(projection.getMovieOrPerformance().getName() + " " + datum + " " + vreme + "h");
		
		for(MovieOrPerformance movie: movieService.getAll()) {
			if(movie.getName().equals(projection.getMovieOrPerformance().getName())) {
				projection.setMovieOrPerformance(movie);
			}
		}
		 
		projection.setHall(hallService.getHallByName(projection.getHall().getName()));
		(cinemaService.findByName(projection.getTheaterOrCinema().getName())).addProjection(projection);
		
		Projection newProjection = service.addProjection(projection);
		
		if(newProjection == null) {
			return new ResponseEntity<Projection>(newProjection, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Projection>(newProjection, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "api/changeProjection", 
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<Projection> changeProjection(@RequestBody PoluProjection proj){

		Projection projection = service.getProjectionByName(proj.getProjectionForChange());
		
		
		Projection newProjection = new Projection();
		newProjection.setDate(proj.getDate());
		newProjection.setPrice(proj.getPrice());
		newProjection.setHall(hallService.getHallByName(proj.getHallName()));
		newProjection.setMovieOrPerformance(movieService.findByName(proj.getMovieName()));
		newProjection.setName(proj.getProjectionName());
		newProjection.setTheaterOrCinema(projection.getTheaterOrCinema());
		Projection returnProjection = service.changeProjection(projection, newProjection);
		return new ResponseEntity<Projection>(returnProjection, HttpStatus.OK);
	}
	
	
	
}
