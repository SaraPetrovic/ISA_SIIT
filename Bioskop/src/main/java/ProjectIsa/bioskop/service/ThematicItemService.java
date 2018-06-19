package ProjectIsa.bioskop.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ProjectIsa.bioskop.domain.ItemAd;
import ProjectIsa.bioskop.domain.ItemOffer;
import ProjectIsa.bioskop.domain.ItemReservation;
import ProjectIsa.bioskop.domain.OfficialItem;
import ProjectIsa.bioskop.domain.ThematicItem;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.domain.UserType;
import ProjectIsa.bioskop.repository.ItemAdRepository;
import ProjectIsa.bioskop.repository.ItemOfferRepository;
import ProjectIsa.bioskop.repository.ItemReservationRepository;
import ProjectIsa.bioskop.repository.OfficialItemRepository;
import ProjectIsa.bioskop.repository.ThematicItemRepository;
import ProjectIsa.bioskop.repository.UserDBRepository;
@Service
@Transactional
public class ThematicItemService implements ItemService {
	@Autowired
	EntityManager em;
	@Autowired
	EmailService emailService;
	@Autowired
	private ThematicItemRepository itemRepository;
	@Autowired
	private UserDBRepository userRepository;
	@Autowired
	private ItemOfferRepository offerRepository;
	@Autowired 
	private ItemReservationRepository reservationRepository;
	@Autowired
	private ItemAdRepository itemAdRepository;
	@Autowired
	private OfficialItemRepository officialItemRepository;
	
	@Override
	public Collection<ThematicItem> getItems() {
		
		return itemRepository.findAll();
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	@Override
	public ItemOffer addItemOffer(ItemOffer offer,User user) {
		ItemAd item = itemAdRepository.findOne(offer.getItem().getId());
		offer.setItem(item);
		User userWhoOffer = userRepository.findById(user.getId());
		offer.setUser(userWhoOffer);
		if (user == null || item == null || offer.getPrice() < 0){
			return null;
		}
		// proverava se da li u bazi postoji ponuda korisnika
		ItemOffer offerToAdd = offerRepository.findByUserAndItemAd(user, item);

		

		if (offerToAdd == null){
			//napravi novu ponudu
			
			ItemOffer itemOffer = offerRepository.save(offer);
			return itemOffer;
		}else{
			//update ponude
			offerToAdd.setPrice(offer.getPrice());
			ItemOffer itemOffer = offerRepository.save(offerToAdd);
			return itemOffer;

		}
		
	}

	@Override
	public List<ItemOffer> getOffers() {
		// TODO Auto-generated method stub
		return offerRepository.findAll();
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public OfficialItem reserve(OfficialItem item, User user){
		OfficialItem itemToReserve =  officialItemRepository.findOne(item.getId());

		ItemReservation itemReservation = reservationRepository.findByUserAndOfficialItem(user, itemToReserve);
		if (itemReservation == null){
			if (itemToReserve.getQuantity() > 0){
				itemToReserve.setQuantity(itemToReserve.getQuantity() - 1);
				OfficialItem reservedItem = officialItemRepository.save(itemToReserve);
				itemReservation = new ItemReservation();
				itemReservation.setItem(reservedItem);
				itemReservation.setUser(user);
				String message = "You successfully reserved " + itemToReserve.getName();
				new Thread(new Runnable() {
				     @Override
					public void run() {
							emailService.sendSimpleMessage(user.getEmail(), "Item reservation", message);

				     }
				}).start();
				User userDB = userRepository.findByUsername(user.getUsername());
				userDB.getItemReservations().add(itemReservation);
				userRepository.save(user);
			
				return reservedItem;			
			}else{
				return itemToReserve;
			}
			
			// ukoliko vec postoji rezervacija vrati null, korisnik ne moze da napravi 2 rezervacije
		}else{
			return null;
		}
		
			
	}
	@Override
	public List<ItemOffer> getItemsByUser (User user){
		User loggedUser = userRepository.findByUsername(user.getUsername());
		return loggedUser.getItemOffers();
		
	}

	@Override
	public ItemAd getItemAd(Long id) {
		// TODO Auto-generated method stub
		return  itemAdRepository.findOne(id);
		
	}



	@Override
	public OfficialItem getOfficialItem(Long id) {
		// TODO Auto-generated method stub
		return officialItemRepository.findOne(id);
	}



	@Override
	public Collection<OfficialItem> getOfficialItems() {
		// TODO Auto-generated method stub
		return officialItemRepository.findAll();
	}



	@Override
	public Collection<ItemAd> getItemAds() {
		// TODO Auto-generated method stub
		return itemAdRepository.findAll();
	}



	@Override
	public List<ItemAd> getUserAds(User user) {
		// TODO Auto-generated method stub
		User u = userRepository.findById(user.getId());
		return u.getAds();
	}



	@Override
	public ItemOffer getOffer(Long id) {
		// TODO Auto-generated method stub
		return offerRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public ItemOffer acceptOffer(Long id, User sessionUser) {
		ItemOffer acceptedOffer = offerRepository.findOne(id);
		List<ItemOffer> offers = offerRepository.findByItemAd(acceptedOffer.getItem());
		//duboko kopiranje zato sto se item brise iz baze
		ItemOffer response = new ItemOffer(acceptedOffer);
		
		if (acceptedOffer != null && sessionUser != null && sessionUser.getId() == acceptedOffer.getItem().getOwner().getId()){
			try{
				for (ItemOffer offer : offers ){
					if (offer == acceptedOffer){
						emailService.sendSimpleMessage(acceptedOffer.getUser().getEmail(), "Your bid for cinema prop", "You successfully bought item " 
					+ acceptedOffer.getItem().getName() + " for " + acceptedOffer.getPrice() + "$." );
					}else{
						emailService.sendSimpleMessage(offer.getUser().getEmail(), "Your bid for cinema prop", "You lost on acution for item " 
								+ acceptedOffer.getItem().getName() + " -- price  " + offer.getPrice() + "$." );
					}
					offerRepository.delete(offer);
					
				}
				itemAdRepository.delete(acceptedOffer.getItem());
				return response;
			}catch(Exception e){
				System.out.println("\n\nexception se desio" + e.getClass()+ "\n\n");
				return null;
			}
		}else{
			return null;
		}
	}



	@Override
	public ItemAd makeApproval(ItemAd item, Boolean approval) {
		// TODO Auto-generated method stub
		if (item != null){
			item.setApproved(approval);
			if (approval == true){
				new Thread(new Runnable() {
				     @Override
					public void run() {
				    	 emailService.sendSimpleMessage(item.getOwner().getEmail(), "Item Accepted", "Your ad for item " + item .getName() + " has been approved by admin");

				     }
				}).start();
			}else{
				new Thread(new Runnable() {
				     @Override
					public void run() {
						emailService.sendSimpleMessage(item.getOwner().getEmail(), "Item Declined", "Your ad for item " + item.getName() + " has been declined by admin");
				     }
				}).start();
				itemAdRepository.delete(item);
			}
			return item;
		}else{
			return null;
		}
	}




	@Override
	public List<ItemAd> findAdByNameOrDescriptrionContaining(String param) {
		List<ItemAd> items = itemAdRepository.findByNameContainingOrDescriptionContainingAllIgnoringCase(param, param);
		return items;
	}



	@Override
	public List<OfficialItem> findByNameOrDescriptrionContaining(String param) {
		List<OfficialItem> items = officialItemRepository.findByNameContainingOrDescriptionContainingAllIgnoringCase(param, param);
		return items;
	}



	@Override
	public List<ItemOffer> findOffersByItem(ItemAd item) {
		List<ItemOffer> offers = offerRepository.findAllByItemAdOrderByPriceDesc(item);
		return offers;
	}



	@Override
	public List<ItemAd> findApproved(Boolean approved) {
		List<ItemAd> items = itemAdRepository.findByApproved(approved);
		return items;
	}


	@Override
	public OfficialItem addOfficialItem(OfficialItem item) {
		OfficialItem addedItem = officialItemRepository.save(item);
		return addedItem;
	}


	@Override
	public void deteItem(OfficialItem item) {
		officialItemRepository.delete(item);
		
	}


	
	public OfficialItem updateOfficialItem(OfficialItem itemToChange, OfficialItem newItem) {
		itemToChange.setDescription(newItem.getDescription());
		itemToChange.setName(newItem.getName());
		itemToChange.setPrice(newItem.getPrice());
		itemToChange.setQuantity(newItem.getQuantity());
		OfficialItem updatedItem = officialItemRepository.save(itemToChange);
		return updatedItem;
	}


	



	
}
