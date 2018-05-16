package ProjectIsa.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjectIsa.bioskop.domain.ItemReservation;

public interface ItemReservationRepository extends JpaRepository<ItemReservation, Long> {
		
}
