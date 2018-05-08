package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import ProjectIsa.bioskop.domain.Ticket;

public interface TicketDBRepository extends Repository<Ticket, Long>{
	Ticket save(Ticket ticket);
	List<Ticket> findAll();
	void delete(Ticket ticket);
	Ticket findById(Long id);

}
