package ProjectIsa.Bioskop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import ProjectIsa.bioskop.domain.ItemAd;
import ProjectIsa.bioskop.domain.ItemOffer;
import ProjectIsa.bioskop.domain.ThematicItem;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.repository.ItemAdRepository;
import ProjectIsa.bioskop.repository.ItemOfferRepository;
import ProjectIsa.bioskop.service.ItemService;
import ProjectIsa.bioskop.service.ThematicItemService;
import ProjectIsa.bioskop.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

	
	@Autowired
	ThematicItemService service;
	@Autowired
	UserService userService;
	@Autowired
	ItemOfferRepository offerRepository;
	@Autowired
	ItemAdRepository itemAdRepository;
	@Autowired
	ItemService itemService;
	@Test
	public void testPrice(){
		List<ThematicItem> items = (List<ThematicItem>)service.getItems();
		//creating new item with higher price than zero
		Long id = 5l;
		ThematicItem item = new ThematicItem(id , "Star wars", 1000, "Opis", "");
		service.addNewItem(item);
		List<ThematicItem> itemsAfter = (List<ThematicItem>)service.getItems();
		assertThat(items).hasSize(itemsAfter.size() - 1);
	}
	@Test
	public void testPrice2(){
		List<ThematicItem> items = (List<ThematicItem>)service.getItems();
		//creating new item with lower price than zero
		Long id = 5l;
		ThematicItem item = new ThematicItem(id , "Star wars", -5, "Opis", "");
		service.addNewItem(item);
		List<ThematicItem> itemsAfter = (List<ThematicItem>)service.getItems();
		assertThat(items).hasSize(itemsAfter.size());
	}
	/* ispravi
	@Test
	public void testItemOffer(){
		ThematicItem item = service.getItem(1L); // default item in db
		User user = userService.getUser("admin"); //default user in db
		double price = 400;
		ItemOffer offer = new ItemOffer(user, price, item);
		
		List<ItemOffer> itemsBefore = service.getOffers();
		
		service.addItemOffer(offer);
		
		List<ItemOffer> itemsAfter = service.getOffers();
		
		assertThat(itemsBefore).hasSize(itemsAfter.size() - 1);
	}
	
	@Test
	public void testItemOffer1(){
		ThematicItem item = service.getItem(1L); // default item in db
		User user = new User("ime", "prezime", "aaaaaaaaaaaaaaaaa", "asd", UserType.REGISTEREDUSER, new Adresa(), "asd@gmail.com"); // user koji ne postoji
		double price = 400;
		ItemOffer offer = new ItemOffer(user, price, item);
		
		List<ItemOffer> itemsBefore = service.getOffers();
		
		service.addItemOffer(offer);
		
		List<ItemOffer> itemsAfter = service.getOffers();
		
		assertThat(itemsBefore).hasSize(itemsAfter.size());
	}
	*/
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testOfferAcceptence(){
		User owner = userService.getUser("ftn");
		User userWhoOffer = userService.getUser("admin");
		ItemAd item = itemAdRepository.findOne(2L);
		
		ItemOffer offer1 = offerRepository.findOne(1L);
		ItemOffer offer2 = offerRepository.findOne(1L);
		assertThat(offer1 != null);
		assertThat(offer2 != null);
		offer2.setPrice(33D);
		offer1.setPrice(10D);
		//simlucacija prihvatanja ponude
		
		//offer1.setPrice(44D);
		offerRepository.save(offer1);
		offerRepository.save(offer2);
		
		//offerRepository.save(offer1);

		ItemOffer offer3 = offerRepository.findByUserAndItemAd(userWhoOffer, item);
		System.out.println(offer3.getItem().getName());
	}


}
