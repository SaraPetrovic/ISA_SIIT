package ProjectIsa.bioskop.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void deleteTicket(Ticket ticket) {
		repository.delete(ticket);
	}

	@Override
	public Ticket getTicket(Long id) {
		return repository.findById(id);
	}

	@Override
	public Ticket reserve(Ticket ticket, User user) {

		if (user == null || user.getUserType() != UserType.REGISTEREDUSER){
			return null;
		}
		
		Ticket ticketToReserve = repository.findOne(ticket.getId());
		User userWhoReserve = userRepository.findById(user.getId());
		ticketToReserve.setUser(userWhoReserve);
		ticketToReserve.setReserved(true);
		Projection projection = projectionRepository.findById(ticketToReserve.getProjection().getId());
		projection.addTicket(ticketToReserve);
		projectionRepository.save(projection);
		repository.save(ticketToReserve);
		return ticketToReserve;
		
	}

}
