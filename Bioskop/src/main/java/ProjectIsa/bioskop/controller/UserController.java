package ProjectIsa.bioskop.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.service.UserService;

@RestController
public class UserController {
	public final static String  DEFAULT_IMAGE_FOLDER = "src/main/webapp/images/";
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private UserService userService;
	@RequestMapping(
			value = "/api/users",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers() {
		
		
		Collection<User> users = userService.getUsers();


		return new ResponseEntity<Collection<User>>(users,
				HttpStatus.OK);
	}
	@RequestMapping(
			value= "/api/users/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") String id){
		
		User user = userService.getUser(id);
		if (user != null){
			return new ResponseEntity<User>(user, HttpStatus.OK); 
		}else{
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(
			value = "/api/users",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user){


		User newUser = userService.addUser(user);
		
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}
	@RequestMapping(
			value = "/api/admins",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
	public ResponseEntity<List<User>> addUser(){


		List<User> admins = userService.findAdmins();
		
		return new ResponseEntity<List<User>>(admins, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/changePassword", 
					produces = MediaType.APPLICATION_JSON_VALUE,
					method = RequestMethod.POST
					)
	public ResponseEntity<User> changePassword(@RequestBody String newPassword){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Boolean success = userService.changePassword(user, newPassword);
		if (success){
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else{
			return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
		}
	}
	@RequestMapping(value = "api/changeProfile", 
					produces = MediaType.APPLICATION_JSON_VALUE,
					method = RequestMethod.POST)
	public ResponseEntity<User> changeProfile(@RequestBody User changedUser){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user"); 
		
		User newUser = userService.changeProfile(user, changedUser);
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}

	@RequestMapping(value = "api/profile",
					produces = MediaType.APPLICATION_JSON_VALUE,
					method = RequestMethod.GET)
	public ResponseEntity<User> getUserProfile(){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null){
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else{
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		
	}
	@RequestMapping(value = "api/uploadProfileImage",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<User> uploadImage(@RequestParam("file") MultipartFile file) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (!file.isEmpty()) {
            try {
            	String newName = UUID.randomUUID().toString() + ".jpg";
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(DEFAULT_IMAGE_FOLDER  + newName )));
                stream.write(bytes);
                stream.close();
                
        		User newUser = userService.changePicure(user, newName);
                return new ResponseEntity<User>(newUser, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        }
    

	}

	
	@RequestMapping(value = "api/getLoggedUser",
					produces = MediaType.APPLICATION_JSON_VALUE,
					method = RequestMethod.GET)
	public ResponseEntity<User> getLoggedUser(){
		
		User loggedUser = (User) request.getSession().getAttribute("user");
		if (loggedUser == null) {	
			return null;
		}
		return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "api/getFriends",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getFriends() {
		List<User> friendsList = userService.getFriendsOfUser(request);
		
		if (friendsList == null) {
			return new ResponseEntity<List<User>>(friendsList, HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<List<User>>(friendsList, HttpStatus.OK);
		}
	}
}