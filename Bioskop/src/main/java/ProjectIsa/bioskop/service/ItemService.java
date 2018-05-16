package ProjectIsa.bioskop.service;

import java.util.Collection;
import java.util.List;

import ProjectIsa.bioskop.domain.ItemOffer;
import ProjectIsa.bioskop.domain.ItemReservation;
import ProjectIsa.bioskop.domain.ThematicItem;
import ProjectIsa.bioskop.domain.User;

public interface ItemService {

	Collection<ThematicItem> getItems();
	ThematicItem getItem(Long id);
	ThematicItem addNewItem(ThematicItem item);
	ThematicItem changeItem(ThematicItem item);
	void deleteItem(ThematicItem item);
	ItemOffer addItemOffer(ItemOffer offer);
	List<ItemOffer> getOffers();

	ThematicItem reserve(ThematicItem item, User user);
	List<ItemOffer> getItemsByUser(User user);
	
}
