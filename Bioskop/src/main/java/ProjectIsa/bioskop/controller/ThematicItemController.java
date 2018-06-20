
package ProjectIsa.bioskop.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ProjectIsa.bioskop.domain.ItemAd;
import ProjectIsa.bioskop.domain.ItemOffer;
import ProjectIsa.bioskop.domain.OfficialItem;
import ProjectIsa.bioskop.domain.ThematicItem;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.domain.UserType;
import ProjectIsa.bioskop.repository.ItemAdRepository;
import ProjectIsa.bioskop.repository.ItemOfferRepository;
import ProjectIsa.bioskop.service.ThematicItemService;
import ProjectIsa.bioskop.service.UserService;

@RestController
public class ThematicItemController {
	@Autowired
	ItemOfferRepository offerRepo;
	@Autowired
	ItemAdRepository itemRepo;
	public final static String  DEFAULT_IMAGE_FOLDER = "src/main/webapp/images/";
	@Autowired
	private ThematicItemService itemService;
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;
	@RequestMapping(
			value ="api/items/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<ItemAd> getItemAd(@PathVariable(value = "id") Long id){
		System.out.println("\n\nid = " + id + "\n\n\n");
		ItemAd item = itemService.getItemAd(id);
		return new ResponseEntity<ItemAd>(item, HttpStatus.OK);
	}
	@RequestMapping(
			value ="api/offers/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<ItemOffer>> getItemOffers(@PathVariable(value = "id") Long id){
		
		ItemAd item = itemService.getItemAd(id);
		List<ItemOffer> offers = item.getOffers();
		return new ResponseEntity<List<ItemOffer>>(offers, HttpStatus.OK);
	}
	@RequestMapping(
			value="api/offer/accept/{id}"
			)
	public ResponseEntity<ItemOffer> acceptOffer(@PathVariable(value = "id") Long id){
		
		User sessionUser = (User)request.getSession().getAttribute("user");
		try{
		ItemOffer acceptedOffer = itemService.acceptOffer(id, sessionUser);
		
		if (acceptedOffer != null){
			return new ResponseEntity<ItemOffer>(acceptedOffer, HttpStatus.OK);
		}else{
			return new ResponseEntity<ItemOffer>(acceptedOffer, HttpStatus.CONFLICT);

		}
		}catch(ObjectOptimisticLockingFailureException e){
			return new ResponseEntity<ItemOffer>(new ItemOffer(), HttpStatus.CONFLICT);
		}
			
		
	}
	
	@RequestMapping(
			value = "/api/officialItems",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<OfficialItem>> getItems() {
		
		
		Collection<OfficialItem> items = itemService.getOfficialItems();


		return new ResponseEntity<Collection<OfficialItem>>(items,
				HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/officialItems",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OfficialItem> addItem(@RequestBody OfficialItem item) {
		
		
		OfficialItem addedItem = itemService.addOfficialItem(item);


		return new ResponseEntity<OfficialItem>(addedItem,
				HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/officialItems",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OfficialItem> ChangeItem(@RequestBody OfficialItem newItem) {
		
		OfficialItem itemToChange = itemService.getOfficialItem(newItem.getId());
		if (itemToChange != null){
			OfficialItem updatedItem = itemService.updateOfficialItem(itemToChange, newItem);
			return new ResponseEntity<OfficialItem>(updatedItem,
					HttpStatus.OK);
		}else{
			return new ResponseEntity<OfficialItem>(itemToChange, HttpStatus.NOT_FOUND);
		}

		
	}
	@RequestMapping(
			value = "/api/officialItem/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OfficialItem> getItem(@PathVariable("id") Long id) {
		OfficialItem item = itemService.getOfficialItem(id);
		if (item != null){
			return new ResponseEntity<OfficialItem>(item, HttpStatus.OK);
		}else{
			return new ResponseEntity<OfficialItem>(item, HttpStatus.NOT_FOUND);

		}
		
	}

	@RequestMapping(
			value = "/api/officialItems/{search}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<OfficialItem>> searchOfficialItemsByNameAndDescription(@PathVariable("search") String searchParam) {
		
		
		List<OfficialItem> items = itemService.findByNameOrDescriptrionContaining(searchParam);


		return new ResponseEntity<Collection<OfficialItem>>(items,
				HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/itemAds",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemAd>> getItemAds() {
		
		
		List<ItemAd> items = itemService.getItemAds();
		for (ItemAd a : items){
			System.out.println("\n\nowner: " + a.getOwner().getFirstName());
		}

		return new ResponseEntity<List<ItemAd>>(items,
				HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/itemAds",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItemAd> addItemAd(@RequestBody ItemAd itemAd) {
		User user =(User) request.getSession().getAttribute("user");

		
		ItemAd item = itemService.addItemAd(user, itemAd);


		return new ResponseEntity<ItemAd>(item,
				HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/itemAds/{search}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ItemAd>> searchItemAdsByNameAndDescription(@PathVariable("search") String searchParam) {
		
		System.out.println("\n\nsearch param is : " + searchParam + "\n\n");
		List<ItemAd> items = itemService.findAdByNameOrDescriptrionContaining(searchParam);


		return new ResponseEntity<Collection<ItemAd>>(items,
				HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/MyAds",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ItemAd>> getMyAds() {
		
		User user =(User) request.getSession().getAttribute("user");
		User userDB = userService.getUser(user.getId());
		Collection<ItemAd> items = itemService.getUserAds(userDB);
		


		return new ResponseEntity<Collection<ItemAd>>(items,
				HttpStatus.OK);
	}
	
	
	

	@RequestMapping(
			value = "/api/createItem",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<ThematicItem> createItem(@RequestBody ThematicItem item){
		ThematicItem createdItem = itemService.addNewItem(item);
		return new ResponseEntity<ThematicItem>(createdItem, HttpStatus.OK);
	}
	

	@RequestMapping(value = "api/items/reserve",
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE,
					method = RequestMethod.POST)
	public ResponseEntity<OfficialItem> reserveItem(@RequestBody OfficialItem item){
		System.out.println("\n\n\n\nId itema = " + item.getId() + "\n\n\n\n");
		Long versionBefore = itemService.getOfficialItem(item.getId()).getVersion();
		while (true){
			try{
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				if (user == null || user.getUserType() != UserType.REGISTEREDUSER){
					return new ResponseEntity<OfficialItem>(item, HttpStatus.BAD_REQUEST);
				}
				OfficialItem reservedItem = itemService.reserve(item, user);
				if (reservedItem != null){
					if (reservedItem.getVersion() == versionBefore){
						//nema vise itema u ponudi ukoliko su iste verzije
						return new ResponseEntity<OfficialItem>(reservedItem, HttpStatus.NOT_FOUND);
					}else{
						return new ResponseEntity<OfficialItem>(reservedItem, HttpStatus.OK);
					}
				}else{
					//korisnik je vec rezervisao
					return new ResponseEntity<OfficialItem>(reservedItem, HttpStatus.CONFLICT);
				}
				
			}catch(ObjectOptimisticLockingFailureException e){
				continue;
				//ukoliko se desi exception
			}
		}
		
		
	}
	@RequestMapping(value = "api/items/makeOffer",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<?> makeOffer(@RequestBody ItemOffer offer){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null){
			System.out.println("\n\n\n\nALERT\n\n\n\n");
		}
		try{
			ItemOffer offerToAdd = itemService.addItemOffer(offer, user);
			return new ResponseEntity<ItemOffer>(offerToAdd, HttpStatus.OK);
		}catch(ObjectOptimisticLockingFailureException e){
			return new ResponseEntity<ItemOffer>(offer, HttpStatus.CONFLICT);
		}
		
		

	}
	@RequestMapping(value = "api/items/myOffers",
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemOffer>> getMyOffers(){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		//List<ItemOffer> offers = itemService.getItemsByUser(user);
		User dbUser = userService.getUser(user.getId());
		List<ItemOffer> offers = dbUser.getItemOffers();
		return new ResponseEntity<List<ItemOffer>>(offers, HttpStatus.OK);
	}
	@RequestMapping(value = "api/items/offers",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemOffer>> getOffers(){
		

		List<ItemOffer> offers = itemService.getOffers();
		//List<ItemOffer> offers = user.getItemOffers();
		return new ResponseEntity<List<ItemOffer>>(offers, HttpStatus.OK);
	}
	@RequestMapping(value = "api/items/offers/{ItemAdId}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemOffer>> getOffersByItem(@PathVariable("ItemAdId") Long id){
		
		ItemAd item = itemService.getItemAd(id); 
		List<ItemOffer> offers = itemService.findOffersByItem(item);
		//List<ItemOffer> offers = user.getItemOffers();
		return new ResponseEntity<List<ItemOffer>>(offers, HttpStatus.OK);
	}
	@RequestMapping(value = "api/items/approve/{id}/{approval}")
	public ResponseEntity<ItemAd> approveItemAd(@PathVariable("id") Long itemId, @PathVariable("approval") Boolean approval){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ItemAd itemToApprove = itemService.getItemAd(itemId); 
		if (user != null && user.getUserType() == UserType.FANZONEADMIN){
			
			ItemAd approvedItem = itemService.makeApproval(itemToApprove, approval);
			if (approvedItem != null){
				return new ResponseEntity<ItemAd>(approvedItem, HttpStatus.OK);
			}else{
				return new ResponseEntity<ItemAd>(itemToApprove, HttpStatus.NOT_FOUND);
			}
			
		}else{
			return new ResponseEntity<ItemAd>(itemToApprove, HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "api/items/unapproved",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemAd>> getUnapprovedItems(){
		
		List<ItemAd> items = itemService.findApproved(false); 
		//List<ItemOffer> offers = user.getItemOffers();
		return new ResponseEntity<List<ItemAd>>(items, HttpStatus.OK);
	}
	@RequestMapping(value = "api/uploadImage",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE,
			method = RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
            try {
            	String newName = UUID.randomUUID().toString() + ".jpg";
            	System.out.println("\n\nsyze : " + file.getSize() + "\n\n");
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(DEFAULT_IMAGE_FOLDER  + newName )));
                stream.write(bytes);
                stream.close();
                
        		
                return newName;
            } catch (Exception e) {
                return "Not Found";
            }
        } else {
            return "Not Found";
        }
    

	}


	
}

	





