package ProjectIsa.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjectIsa.bioskop.domain.ItemReservation;
import ProjectIsa.bioskop.domain.OfficialItem;
import ProjectIsa.bioskop.domain.User;

public interface ItemReservationRepository extends JpaRepository<ItemReservation, Long> {
		ItemReservation findByUserAndOfficialItem(User user, OfficialItem item);
}
