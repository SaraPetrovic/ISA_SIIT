package ProjectIsa.bioskop.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
<<<<<<< HEAD
import javax.servlet.http.HttpSession;
=======
>>>>>>> 626b0dc6175f1b359fc79d77ffb75b8c34a2ee4a

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ProjectIsa.bioskop.domain.Hall;
import ProjectIsa.bioskop.domain.MovieOrPerformance;
import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.domain.Ticket;
import ProjectIsa.bioskop.domain.User;
<<<<<<< HEAD
import ProjectIsa.bioskop.domain.UserType;
=======
>>>>>>> 626b0dc6175f1b359fc79d77ffb75b8c34a2ee4a
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
	@Autowired
	HttpServletRequest request;


	
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
			value = "/api/projections/{id}/tickets",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<List<Ticket>> getProjectionsTickets(@PathVariable("id") Long id) {
		Projection p = service.getProjection(id);
		List<Ticket> ret = null;
		
		if (p != null) {
			ret = p.getTickets();
		}
		
		if (ret == null) {
			return new ResponseEntity<List<Ticket>>(ret, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Ticket>>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/createProjection",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<String> addProjection(@RequestBody Projection projection){
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null || sessionUser.getUserType() != UserType.CINEMAADMIN) {
			return new ResponseEntity<String>("{\"msg\":\"You are not logged in as cinema admin!\"}", HttpStatus.CONFLICT);
		}
		
		for(MovieOrPerformance movie: movieService.getAll()) {
			if(movie.getName().equals(projection.getMovieOrPerformance().getName())) {
				projection.setMovieOrPerformance(movie);
			}
		}
		
		projection.setHall(hallService.getHallByName(projection.getHall().getName()));
		(cinemaService.findByName(projection.getTheaterOrCinema().getName())).addProjection(projection);
		
		String message = service.addProjection(projection);
		
		if(message == null) {
			return new ResponseEntity<String>("{\"msg\":\"Projection is successfully added!\"}", HttpStatus.OK);			
		}else {
			return new ResponseEntity<String>("{\"msg\": \""+message+"\"}", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "api/changeProjection", 
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<String> changeProjection(@RequestBody Projection proj){
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null || sessionUser.getUserType() != UserType.CINEMAADMIN) {
			return new ResponseEntity<String>("{\"msg\":\"You are not logged in as cinema admin!\"}", HttpStatus.CONFLICT);
		}
		
		Hall hall = hallService.getHallById(proj.getHall().getId());
		
		Projection projection = service.getProjection(proj.getId());
		
		String message = service.changeProjection(projection, hall);
		if(message == null) {
			return new ResponseEntity<String>("{\"msg\":\"Projection is successfully changed!\"}", HttpStatus.OK);
		}
		return new ResponseEntity<String>("{\"msg\": \""+message+"\"}", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(
			value = "/api/makeReservation",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<List<Ticket>> makeReservation(@RequestBody List<Ticket> tickets) {
		User u = null;
		u = (User) request.getSession().getAttribute("user");
		if (u == null) {
			// WTF
			System.out.println("NIJE PRONASAO LOGOVANOG USERA U makeReservation api");
		}
		
		for (Ticket t : tickets) {
			Projection p = service.getProjection(t.getProjection().getId());
			t.setUser(u);
			t.setProjection(p);
			p.addTicket(t);
			Projection updatedProjection = service.makeReservation(p);
			if (updatedProjection == null) {
				return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.BAD_REQUEST);
			}
		}
		
		return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
		
	}
	
	
	
}
