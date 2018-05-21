
package ProjectIsa.bioskop.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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

import ProjectIsa.bioskop.service.ThematicItemService;
import ProjectIsa.bioskop.service.UserService;

@RestController
public class ThematicItemController {
	
	public final static String  DEFAULT_IMAGE_FOLDER = "src/main/webapp/images/";
	@Autowired
	private ThematicItemService itemService;
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;
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
			value = "/api/itemAds",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ItemAd>> getItemAds() {
		
		
		Collection<ItemAd> items = itemService.getItemAds();


		return new ResponseEntity<Collection<ItemAd>>(items,
				HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/MyAds",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ItemAd>> getMyAds() {
		
		
		Collection<ItemAd> items = itemService.getItemAds();


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
	
	@RequestMapping(value = "api/uploadImage",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			method = RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile file,
							@RequestParam("itemImage") String imageName) {
		if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(DEFAULT_IMAGE_FOLDER  + imageName )));
                stream.write(bytes);
                stream.close();
                System.out.println("Uploadujem...");

                return "You successfully uploaded " + "images/file.jpg" + "!";
            } catch (Exception e) {
                return "You failed to upload " + "images/file.jpg" + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + "images/file.jpg" + " because the file was empty.";
        }
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
				if (user == null){
					return null;
				}
				OfficialItem reservedItem = itemService.reserve(item, user);
				if (reservedItem != null){
					if (reservedItem.getVersion() == versionBefore){
						System.out.println("\n\n\n\nVracam konfilkt\n\n\n\n");
	
						return new ResponseEntity<OfficialItem>(reservedItem, HttpStatus.CONFLICT);
					}else{
						return new ResponseEntity<OfficialItem>(reservedItem, HttpStatus.OK);
					}
				}else{
					return null;
				}
				
			}catch(ObjectOptimisticLockingFailureException e){
				
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
		ItemAd item = itemService.getItemAd(offer.getItem().getId());
		offer.setItem(item);
		offer.setUser(user);
		ItemOffer offerToAdd = itemService.addItemOffer(offer);
		return new ResponseEntity<ItemOffer>(offerToAdd, HttpStatus.OK);

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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		List<ItemOffer> offers = itemService.getOffers();
		//List<ItemOffer> offers = user.getItemOffers();
		return new ResponseEntity<List<ItemOffer>>(offers, HttpStatus.OK);
}


}

	





