
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ProjectIsa.bioskop.domain.ItemOffer;
import ProjectIsa.bioskop.domain.ThematicItem;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.service.ThematicItemService;

@RestController
public class ThematicItemController {
	
	public final static String  DEFAULT_IMAGE_FOLDER = "src/main/webapp/images/";
	@Autowired
	private ThematicItemService itemService;
	@Autowired
	private HttpServletRequest request;
	@RequestMapping(
			value = "/api/items",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ThematicItem>> getItems() {
		
		
		Collection<ThematicItem> items = itemService.getItems();


		return new ResponseEntity<Collection<ThematicItem>>(items,
				HttpStatus.OK);
	}
	@RequestMapping(
			value= "/api/item/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<ThematicItem> getItem(@PathVariable("id") Long id){
		
		ThematicItem item = itemService.getItem(id);
		if (item != null){
			return new ResponseEntity<ThematicItem>(item, HttpStatus.OK); 
		}else{
			return new ResponseEntity<ThematicItem>(item, HttpStatus.NOT_FOUND);
		}
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
	public ResponseEntity<ThematicItem> reserveItem(@RequestBody ThematicItem item){
		System.out.println("\n\n\n\nId itema = " + item.getId() + "\n\n\n\n");
		Long versionBefore = itemService.getItem(item.getId()).getVersion();
		while (true){
			try{
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				if (user == null){
					return null;
				}
				ThematicItem reservedItem = itemService.reserve(item, user);
				//ako su iste verzije znaci da nije doslo do promene tj da broj preostalih itema = 0
				if (reservedItem.getVersion() == versionBefore){
					System.out.println("\n\n\n\nVracam konfilkt\n\n\n\n");

					return new ResponseEntity<ThematicItem>(reservedItem, HttpStatus.CONFLICT);
				}else{
					return new ResponseEntity<ThematicItem>(reservedItem, HttpStatus.OK);
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
		ThematicItem item = itemService.getItem(offer.getItem().getId());
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
		List<ItemOffer> offers = itemService.getItemsByUser(user);
		return new ResponseEntity<List<ItemOffer>>(offers, HttpStatus.OK);
	}


}

	





