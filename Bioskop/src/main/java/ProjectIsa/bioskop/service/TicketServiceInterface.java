package ProjectIsa.bioskop.service;

import java.util.Collection;

import ProjectIsa.bioskop.domain.Ticket;
import ProjectIsa.bioskop.domain.User;

public interface TicketServiceInterface {
	Collection<Ticket> getTickets();
	String addTicket(Ticket ticket);
	Ticket deleteTicket(Long id);
	Ticket getTicket(Long id);
	Ticket reserve(Ticket ticket, User user);
	String decline(Ticket t);
	
}
