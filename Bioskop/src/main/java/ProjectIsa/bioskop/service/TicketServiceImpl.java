package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Ticket;
import ProjectIsa.bioskop.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketServiceInterface{

	@Autowired
	TicketRepository repository;
	
	@Override
	public Collection<Ticket> getTickets() {
		return repository.getTickets();
	}

	@Override
	public Ticket addTicket(Ticket ticket) {
		repository.addTicket(ticket);
		return ticket;
	}

	@Override
	public void deleteTicket(Ticket ticket) {
		repository.deleteTicket(ticket);
	}

	@Override
	public Ticket getTicket(Long id) {
		return repository.getTicket(id);
	}

}
