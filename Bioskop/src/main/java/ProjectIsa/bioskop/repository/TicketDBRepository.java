package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjectIsa.bioskop.domain.Ticket;

public interface TicketDBRepository extends JpaRepository<Ticket, Long>{
	@Override
	Ticket save(Ticket ticket);
	@Override
	List<Ticket> findAll();
	@Override
	void delete(Ticket ticket);
	Ticket findById(Long id);

}
