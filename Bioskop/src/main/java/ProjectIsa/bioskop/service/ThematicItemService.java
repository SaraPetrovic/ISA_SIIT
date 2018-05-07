package ProjectIsa.bioskop.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.ItemOffer;
import ProjectIsa.bioskop.domain.ThematicItem;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.repository.ItemOfferRepository;
import ProjectIsa.bioskop.repository.ThematicItemRepository;
import ProjectIsa.bioskop.repository.UserDBRepository;
@Service
public class ThematicItemService implements ItemService {
	
	@Autowired
	private ThematicItemRepository itemRepository;
	@Autowired
	private UserDBRepository userRepository;
	@Autowired
	private ItemOfferRepository offerRepository;
	@Override
	public Collection<ThematicItem> getItems() {
		
		return itemRepository.findAll();
	}

	@Override
	public ThematicItem getItem(Long id) {
		// TODO Auto-generated method stub
		return itemRepository.findById(id);
	}

	@Override
	public ThematicItem addNewItem(ThematicItem item) {
		if (item.getPrice() < 0){
			return null;
		}
		return itemRepository.save(item);
	}

	@Override
	public ThematicItem changeItem(ThematicItem item) {
		// TODO Auto-generated method stub
		return itemRepository.save(item);
	}

	@Override
	public void deleteItem(ThematicItem item) {
		itemRepository.delete(item);
		
	}

	@Override
	public ItemOffer addItemOffer(ItemOffer offer) {
		User user = userRepository.findByUsername(offer.getUser().getUsername());
		if (user == null){
			return null;
		}
		ThematicItem item = itemRepository.findById(offer.getItem().getId());
		if (item == null){
			return null;
		}
		ItemOffer itemOffer = offerRepository.save(offer);
		return itemOffer;
	}

	@Override
	public List<ItemOffer> getOffers() {
		// TODO Auto-generated method stub
		return offerRepository.findAll();
	}



}
