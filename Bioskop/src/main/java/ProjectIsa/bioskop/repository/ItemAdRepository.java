package ProjectIsa.bioskop.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import ProjectIsa.bioskop.domain.ItemAd;

public interface ItemAdRepository extends JpaRepository<ItemAd, Long> {
	List<ItemAd> findByNameContainingOrDescriptionContainingAllIgnoringCase(String param, String param2);
	List<ItemAd> findByApproved(Boolean approved);
}
