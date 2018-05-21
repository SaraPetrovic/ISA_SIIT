package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import ProjectIsa.bioskop.domain.ItemAd;
import ProjectIsa.bioskop.domain.ItemOffer;
import ProjectIsa.bioskop.domain.User;

public interface ItemOfferRepository extends Repository<ItemOffer, Long> {
	ItemOffer save(ItemOffer offer);
	List<ItemOffer> findAll();
	ItemOffer findByUserAndItemAd(User user, ItemAd item);
	List<ItemOffer > findByUser(User user);

}
