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

import ProjectIsa.bioskop.domain.Ticket;
import ProjectIsa.bioskop.service.TicketServiceImpl;

@RestController
public class TicketController {

	@Autowired
	TicketServiceImpl service;
	
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
}
