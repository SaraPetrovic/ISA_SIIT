package ProjectIsa.bioskop.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import ProjectIsa.bioskop.domain.Ticket;

@Repository
public class TicketRepository implements TicketInterface {

	private Collection<Ticket> tickets = new ArrayList<Ticket>();
	
	@Override
	public Collection<Ticket> getTickets() {
		return tickets;
	}

	@Override
	public Ticket addTicket(Ticket ticket) {
		tickets.add(ticket);
		return ticket;
	}

	@Override
	public void deleteTicket(Ticket ticket) {
		tickets.remove(ticket);
	}

	@Override
	public Ticket getTicket(Long id) {
		for(Ticket t: tickets) {
			if(t.getId() == id) {
				return t;
			}
		}
		return null;
	}

}
