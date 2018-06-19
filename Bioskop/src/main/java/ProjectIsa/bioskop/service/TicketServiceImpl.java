package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.domain.Ticket;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.repository.ProjectionsDBRepository;
import ProjectIsa.bioskop.repository.TicketDBRepository;

@Service
public class TicketServiceImpl implements TicketServiceInterface{

	@Autowired
	TicketDBRepository repository;
	@Autowired
	ProjectionsDBRepository projectionRepository;
	
	@Override
	public Collection<Ticket> getTickets() {
		return repository.findAll();
	}

	@Override
	public Ticket addTicket(Ticket ticket) {

		Projection projection = projectionRepository.findByName(ticket.getProjection().getName());
		
		// PROVERA SLOBODNIH MESTA U SALI ZA TU PROJEKCIJU
		
		ticket.setProjection(projection);

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

	@Override
	public Ticket reserve(Ticket ticket, User user) {
		return null;
		
	}

}
