package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Ticket;
import ProjectIsa.bioskop.repository.TicketDBRepository;

@Service
public class TicketServiceImpl implements TicketServiceInterface{

	@Autowired
	TicketDBRepository repository;
	
	@Override
	public Collection<Ticket> getTickets() {
		return repository.findAll();
	}

	@Override
	public Ticket addTicket(Ticket ticket) {
		repository.save(ticket);
		return ticket;
	}

	@Override
	public void deleteTicket(Ticket ticket) {
		repository.delete(ticket);
	}

	@Override
	public Ticket getTicket(Long id) {
		return repository.findById(id);
	}

}
