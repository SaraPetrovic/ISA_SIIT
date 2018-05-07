package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import ProjectIsa.bioskop.domain.ItemOffer;

public interface ItemOfferRepository extends Repository<ItemOffer, Long> {
	ItemOffer save(ItemOffer offer);
	List<ItemOffer> findAll();
}
