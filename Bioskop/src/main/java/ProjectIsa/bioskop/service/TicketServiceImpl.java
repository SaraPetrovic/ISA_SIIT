package ProjectIsa.bioskop.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.domain.Ticket;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.domain.UserType;
import ProjectIsa.bioskop.repository.ProjectionsDBRepository;
import ProjectIsa.bioskop.repository.TicketDBRepository;
import ProjectIsa.bioskop.repository.UserDBRepository;

@Service
public class TicketServiceImpl implements TicketServiceInterface{

	@Autowired
	TicketDBRepository repository;
	@Autowired
	ProjectionsDBRepository projectionRepository;
	@Autowired
	UserDBRepository userRepository;
	
	@Override
	public Collection<Ticket> getTickets() {
		return repository.findAll();
	}

	@Override
	public String addTicket(Ticket ticket) {

		if(ticket.getNewPrice() == 0) {
			return "Please enter all required data!";
		}
		Projection projection = projectionRepository.findById(ticket.getProjection().getId());
		
		ticket.setProjection(projection);
		ticket.setReserved(false);
		ticket.setFastTicket(true);
		repository.save(ticket);
		projection.addTicket(ticket);
		projectionRepository.save(projection);
		
		List<Ticket> tickets = projection.getTickets();
		System.out.println("BROJ KARATA " + tickets.size());
		
		return null;
	}

	@Override
	public Ticket deleteTicket(Long id) {
		Ticket ticket = repository.findById(id);
		Projection p = ticket.getProjection();
		p.getTickets().remove(ticket);
		projectionRepository.save(p);
		repository.delete(ticket);
		return ticket;
	}

	@Override
	public Ticket getTicket(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Ticket reserve(Ticket ticket, User user) {
		
		Ticket ticketToReserve = repository.findOne(ticket.getId());
		//User userWhoReserve = userRepository.findById(user.getId());
		ticketToReserve.setUser(user);
		ticketToReserve.setReserved(true);
		
		repository.save(ticketToReserve);
		return ticketToReserve;
		
	}

}
