package ProjectIsa.bioskop.service;

import java.util.Collection;

import ProjectIsa.bioskop.domain.ThematicItem;

public interface ItemService {

	Collection<ThematicItem> getItems();
	ThematicItem getItem(Long id);
	ThematicItem addNewItem(ThematicItem item);
	ThematicItem changeItem(ThematicItem item);
	void deleteItem(ThematicItem item);
}
