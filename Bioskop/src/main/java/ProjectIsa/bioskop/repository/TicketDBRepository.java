package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjectIsa.bioskop.domain.Ticket;

public interface TicketDBRepository extends JpaRepository<Ticket, Long>{
	Ticket save(Ticket ticket);
	List<Ticket> findAll();
	void delete(Ticket ticket);
	Ticket findById(Long id);

}
