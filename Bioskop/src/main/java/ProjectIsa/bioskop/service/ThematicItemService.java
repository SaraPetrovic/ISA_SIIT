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
		
		return repository.findAll();
	}

	@Override
	public ThematicItem getItem(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public ThematicItem addNewItem(ThematicItem item) {
		if (item.getPrice() < 0){
			return null;
		}
		return repository.save(item);
	}

	@Override
	public ThematicItem changeItem(ThematicItem item) {
		// TODO Auto-generated method stub
		return repository.save(item);
	}

	@Override
	public void deleteItem(ThematicItem item) {
		repository.delete(item);
		
	}

}
