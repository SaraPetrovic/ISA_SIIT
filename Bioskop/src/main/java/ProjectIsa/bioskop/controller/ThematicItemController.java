
package ProjectIsa.bioskop.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ProjectIsa.bioskop.domain.ThematicItem;
import ProjectIsa.bioskop.service.ThematicItemService;

@RestController
public class ThematicItemController {
	
	public final static String  DEFAULT_IMAGE_FOLDER = "src/main/webapp/images/";
	@Autowired
	private ThematicItemService itemService;

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
                return "You successfully uploaded " + "images/file.jpg" + "!";
            } catch (Exception e) {
                return "You failed to upload " + "images/file.jpg" + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + "images/file.jpg" + " because the file was empty.";
        }
    

	}
}