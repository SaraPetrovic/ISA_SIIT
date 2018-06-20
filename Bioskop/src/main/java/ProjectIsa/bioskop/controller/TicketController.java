package ProjectIsa.bioskop.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ProjectIsa.bioskop.domain.Ticket;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.domain.UserType;
import ProjectIsa.bioskop.service.TicketServiceImpl;

@RestController
public class TicketController {

	@Autowired
	TicketServiceImpl service;
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(
			value = "/api/tickets",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Ticket>> getTickets() {
		
		Collection<Ticket> tickets = service.getTickets();

		if(tickets != null) {
			return new ResponseEntity<Collection<Ticket>>(tickets, HttpStatus.OK);	
		}else {
			return new ResponseEntity<Collection<Ticket>>(tickets, HttpStatus.NOT_FOUND);
		}
		
	}
	@RequestMapping(
			value = "/api/ticket/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ticket> getTicket(@PathVariable("id") Long id) {
		
		Ticket ticket = service.getTicket(id);

		if (ticket != null){
			return new ResponseEntity<Ticket>(ticket, HttpStatus.OK); 
		}else{
			return new ResponseEntity<Ticket>(ticket, HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(
			value = "/api/ticket/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ticket> deleteTicket(@PathVariable("id") Long id) {
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null || sessionUser.getUserType() != UserType.CINEMAADMIN) {
			return new ResponseEntity<Ticket>(new Ticket(), HttpStatus.CONFLICT);
		}
		
		Ticket ticket = service.deleteTicket(id);

		if (ticket != null){
			return new ResponseEntity<Ticket>(ticket, HttpStatus.OK); 
		}else{
			return new ResponseEntity<Ticket>(ticket, HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(
			value = "/api/addTicket",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<String> addTicket(@RequestBody Ticket ticket){
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null || sessionUser.getUserType() != UserType.CINEMAADMIN) {
			return new ResponseEntity<String>("{\"msg\":\"You are not logged in as cinema admin!\"}", HttpStatus.CONFLICT);
		}
		
		String message = service.addTicket(ticket);
		if(message == null) {
			return new ResponseEntity<String>("{\"msg\":\"Tickes is successfully added!\"}", HttpStatus.OK);
		}
		return new ResponseEntity<String>("{\"msg\": \""+message+"\"}", HttpStatus.BAD_REQUEST);		
	}
	
	@RequestMapping(value = "api/tickets/reserve",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<Ticket> reserveTicket(@RequestBody Ticket ticket){
		while (true){
			Ticket reservedTicket = null;
			try{
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				if (user == null || user.getUserType() != UserType.REGISTEREDUSER){
					return new ResponseEntity<Ticket>(reservedTicket, HttpStatus.CONFLICT);
				}
				
				reservedTicket = service.reserve(ticket, user);
				
				if (reservedTicket != null){
					
						System.out.println("\n\nreserved ticket != null okk\n\n");
		
						return new ResponseEntity<Ticket>(reservedTicket, HttpStatus.OK);
					
				}else{
					System.out.println("\n\nreserved ticket == null\n\n");
					return new ResponseEntity<Ticket>(reservedTicket, HttpStatus.BAD_REQUEST);
				}
				
			}catch(ObjectOptimisticLockingFailureException e){
				System.out.println("\n\nVracam konfilkt\n\n");
				return new ResponseEntity<Ticket>(reservedTicket, HttpStatus.CONFLICT);
			}
		}
	}
}
