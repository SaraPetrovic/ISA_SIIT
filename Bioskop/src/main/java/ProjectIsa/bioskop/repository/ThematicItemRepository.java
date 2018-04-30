package ProjectIsa.bioskop.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import ProjectIsa.bioskop.domain.ThematicItem;
@Repository
public class ThematicItemRepository implements ThematicItemRepositoryInterface{
	private final ConcurrentMap<Long, ThematicItem> items = new ConcurrentHashMap<Long, ThematicItem>();

	@Override
	public Collection<ThematicItem> getItems() {
		// TODO Auto-generated method stub
		return items.values();
	}

	@Override
	public ThematicItem getItem(Long id) {
		// TODO Auto-generated method stub
		ThematicItem item = items.get(id);
		if (item == null){
			System.out.println("item null");
		}
		return items.get(id);
	}

	@Override
	public ThematicItem createItem(ThematicItem item) {
		// TODO Auto-generated method stub
		if (item.getId() == null){
			item.setId(999L);
		}
		return items.put(item.getId(), item);
	}

	@Override
	public ThematicItem changeItem(ThematicItem item) {
		// TODO Auto-generated method stub
		Long id = item.getId();
		if (id != null){
			items.put(id, item);
		}
		return item;
	}

	@Override
	public void deleteItem(Long id) {
		if (id != null){
			items.remove(id);
		}
		
	}
	
}
