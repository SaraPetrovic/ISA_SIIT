package ProjectIsa.bioskop.service;

import java.util.Collection;

import ProjectIsa.bioskop.domain.Ticket;
import ProjectIsa.bioskop.domain.User;

public interface TicketServiceInterface {
	Collection<Ticket> getTickets();
	Ticket addTicket(Ticket ticket);
	void deleteTicket(Ticket ticket);
	Ticket getTicket(Long id);
	Ticket reserve(Ticket ticket, User user);
}
