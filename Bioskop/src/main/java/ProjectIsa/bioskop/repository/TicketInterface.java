package ProjectIsa.bioskop.repository;

import java.util.Collection;

import ProjectIsa.bioskop.domain.Ticket;

public interface TicketInterface {
	Collection<Ticket> getTickets();
	Ticket addTicket(Ticket ticket);
	void deleteTicket(Ticket ticket);
	Ticket getTicket(Long id);
}
