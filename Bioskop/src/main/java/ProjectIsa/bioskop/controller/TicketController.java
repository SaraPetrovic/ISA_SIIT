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

import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.domain.Ticket;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.service.ProjectionServiceImpl;
import ProjectIsa.bioskop.service.TicketServiceImpl;

@RestController
public class TicketController {

	@Autowired
	TicketServiceImpl service;
	@Autowired
	ProjectionServiceImpl projectionService;
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
			value = "/api/addTicket",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<String> addTicket(@RequestBody Ticket ticket){
		
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
		System.out.println("\n\nId ticketa = " + ticket.getId() + "\n\n");
		Long versionBefore = service.getTicket(ticket.getId()).getVersion();
		while (true){
			try{
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				
				Ticket reservedTicket = service.reserve(ticket, user);
				if (reservedTicket != null){
					if (reservedTicket.getVersion() == versionBefore){
						System.out.println("\n\nVracam konfilkt\n\n");
		
						return new ResponseEntity<Ticket>(reservedTicket, HttpStatus.CONFLICT);
					}else{
						System.out.println("\n\nreserved ticket != null okk\n\n");
		
						return new ResponseEntity<Ticket>(reservedTicket, HttpStatus.OK);
					}
				}else{
					System.out.println("\n\nreserved ticket == null\n\n");
					return null;
				}
				
			}catch(ObjectOptimisticLockingFailureException e){
				
			}
		}
	}
	
	
}
