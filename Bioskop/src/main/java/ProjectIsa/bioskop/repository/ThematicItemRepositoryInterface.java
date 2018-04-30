package ProjectIsa.bioskop.repository;

import java.util.Collection;

import ProjectIsa.bioskop.domain.ThematicItem;

public interface ThematicItemRepositoryInterface {
	
		Collection<ThematicItem> getItems();
		ThematicItem getItem(Long id);
		ThematicItem createItem(ThematicItem item);
		ThematicItem changeItem(ThematicItem item);
		void deleteItem(Long id);
}
