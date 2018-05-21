package ProjectIsa.bioskop.service;

import java.util.Collection;
import java.util.List;

import ProjectIsa.bioskop.domain.ItemAd;
import ProjectIsa.bioskop.domain.ItemOffer;
import ProjectIsa.bioskop.domain.OfficialItem;
import ProjectIsa.bioskop.domain.ThematicItem;
import ProjectIsa.bioskop.domain.User;

public interface ItemService {

	Collection<ThematicItem> getItems();
	ItemAd getItemAd(Long id);
	OfficialItem getOfficialItem(Long id);
	Collection<OfficialItem> getOfficialItems();
	Collection<ItemAd> getItemAds();
	ThematicItem addNewItem(ThematicItem item);
	ThematicItem changeItem(ThematicItem item);
	void deleteItem(ThematicItem item);
	ItemOffer addItemOffer(ItemOffer offer);
	List<ItemOffer> getOffers();
	List<ItemAd> getUserAds(User user);
	OfficialItem reserve(OfficialItem item, User user);
	List<ItemOffer> getItemsByUser(User user);
	
}
