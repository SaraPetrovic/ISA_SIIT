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

import ProjectIsa.bioskop.domain.OfficialItem;
import ProjectIsa.bioskop.domain.Ticket;
import ProjectIsa.bioskop.domain.User;
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
			value = "/api/addTicket",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket){
		
		Ticket newTicket = service.addTicket(ticket);
		if(newTicket == null) {
			return new ResponseEntity<Ticket>(newTicket, HttpStatus.BAD_REQUEST); 
		}
		return new ResponseEntity<Ticket>(newTicket, HttpStatus.OK);
	}
	@RequestMapping(value = "api/tickets/reserve",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<Ticket> reserveItem(@RequestBody Ticket ticket){
		System.out.println("\n\n\n\nId itema = " + ticket.getId() + "\n\n\n\n");
		Long versionBefore = service.getTicket(ticket.getId()).getVersion();
		while (true){
			try{
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				if (user == null){
					return null;
				}
				Ticket reservedTicket = service.reserve(ticket, user);
				if (reservedTicket != null){
					if (reservedTicket.getVersion() == versionBefore){
						System.out.println("\n\n\n\nVracam konfilkt\n\n\n\n");
		
						return new ResponseEntity<Ticket>(reservedTicket, HttpStatus.CONFLICT);
					}else{
						System.out.println("\n\nreserved item != null okk\n\n");
		
						return new ResponseEntity<Ticket>(reservedTicket, HttpStatus.OK);
					}
				}else{
					System.out.println("\n\nreserved item == null\n\n");
					return null;
				}
				
			}catch(ObjectOptimisticLockingFailureException e){
				
			}
		}
	}
}
