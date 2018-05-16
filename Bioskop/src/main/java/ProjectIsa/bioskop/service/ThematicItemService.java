package ProjectIsa.bioskop.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ProjectIsa.bioskop.domain.ItemOffer;
import ProjectIsa.bioskop.domain.ThematicItem;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.repository.ItemOfferRepository;
import ProjectIsa.bioskop.repository.ThematicItemRepository;
import ProjectIsa.bioskop.repository.UserDBRepository;
@Service
@Transactional
public class ThematicItemService implements ItemService {
	@Autowired
	EntityManager em;
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
		return itemRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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
		ThematicItem item = itemRepository.findOne(offer.getItem().getId());
		if (item == null){
			return null;
		}
		//mora save, proveri da li moze bez toga
		itemRepository.save(item);
		userRepository.save(user);
		ItemOffer itemOffer = offerRepository.save(offer);
		return itemOffer;
	}

	@Override
	public List<ItemOffer> getOffers() {
		// TODO Auto-generated method stub
		return offerRepository.findAll();
	}
	@Override
	@Transactional(readOnly = false)
	public ThematicItem reserve(ThematicItem item){
		
		ThematicItem itemToReserve =  itemRepository.findOne(item.getId());
		if (itemToReserve.getQuantity() > 0){
			itemToReserve.setQuantity(itemToReserve.getQuantity() - 1);
			return itemRepository.save(itemToReserve);			
		}
		return itemToReserve;
			
	}
}
