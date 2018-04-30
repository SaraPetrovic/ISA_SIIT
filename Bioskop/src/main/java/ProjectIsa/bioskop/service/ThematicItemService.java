package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.ThematicItem;
import ProjectIsa.bioskop.repository.ThematicItemRepository;
@Service
public class ThematicItemService implements ItemService {
	
	@Autowired
	private ThematicItemRepository repository;
	
	
	@Override
	public Collection<ThematicItem> getItems() {
		
		return repository.getItems();
	}

	@Override
	public ThematicItem getItem(Long id) {
		// TODO Auto-generated method stub
		return repository.getItem(id);
	}

	@Override
	public ThematicItem createItem(ThematicItem item) {
		
		return repository.createItem(item);
	}

	@Override
	public ThematicItem changeItem(ThematicItem item) {
		// TODO Auto-generated method stub
		return repository.changeItem(item);
	}

	@Override
	public void deleteItem(Long id) {
		repository.deleteItem(id);
		
	}

}
