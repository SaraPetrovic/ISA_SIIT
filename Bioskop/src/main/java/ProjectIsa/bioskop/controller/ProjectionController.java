package ProjectIsa.bioskop.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import ProjectIsa.bioskop.domain.Ticket;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.service.EmailService;
import ProjectIsa.bioskop.service.HallServiceImpl;
import ProjectIsa.bioskop.service.MovieOrPerformanceServiceImpl;
import ProjectIsa.bioskop.service.ProjectionServiceImpl;
import ProjectIsa.bioskop.service.TheaterOrCinemaService;
import ProjectIsa.bioskop.service.UserServiceImpl;

@RestController
public class ProjectionController {
	
	@Autowired
	ProjectionServiceImpl service;
	@Autowired
	HallServiceImpl hallService;
	@Autowired
	MovieOrPerformanceServiceImpl movieService;
	@Autowired
	UserServiceImpl userService;
	@Autowired
	TheaterOrCinemaService cinemaService;
	@Autowired
	EmailService emailService;
	@Autowired
	private HttpServletRequest request;
	
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
	
	@RequestMapping(
			value = "/api/makeReservation",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<List<Ticket>> makeReservation(@RequestBody List<Ticket> tickets) {
		User loggedUser = null;
		loggedUser = (User) request.getSession().getAttribute("user");
		if (loggedUser == null) {
			// WTF
			System.out.println("NIJE PRONASAO LOGOVANOG USERA U makeReservation api");
		}
		
		for (Ticket t : tickets) {
			Projection p = service.getProjection(t.getProjection().getId());
			// ako nije grupna, ili prva karta u grupnoj!
			if (t.getUser() == null) {
				t.setUser(loggedUser);
			} else {
				User u = userService.getUser(t.getUser().getId());
				if (u == null) {
					return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.CONFLICT);
				}
				t.setUser(u);
			}
			t.setProjection(p);
			p.addTicket(t);
			Projection updatedProjection = service.makeReservation(p);
			if (updatedProjection == null) {
				return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.BAD_REQUEST);
			}
			String msg;
			if (t.getUser().getId() != loggedUser.getId()) {
				msg = "Hello Mr./Ms. " + t.getUser().getFirstName() + " " + t.getUser().getLastName() + "!"
				+ " You have been invated for a group reservation! Please accept or decline your reservation on "
				+"your profile!";
			} else {
				msg = "Hello Mr./Ms. " + t.getUser().getFirstName() + " " + t.getUser().getLastName() + "!"
				+ "You have successesfuly reserved a ticket! Check it out on your profile!";
			}
			String[] datum = t.getProjection().getDate().split("T");
			
			msg += "\n\n\nMovie: " + p.getName();
			//msg += "\nDate: " + datum[0] + "   Time: " + datum[1];
			msg += "\nPlace: " + p.getTheaterOrCinema().getName();
			msg += "\nHall: " + p.getHall().getName();
			msg += "\nRow: " + t.getRed();
			msg += "\nColumn: " + t.getKolona();
			msg += "\nPrice: $" + t.getNewPrice();
			
			String message = msg;
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					emailService.sendSimpleMessage(t.getUser().getEmail(), "Group reservation", message);
				}
			}).start();
		}
		
		return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
		
	}
	
	
	
}
