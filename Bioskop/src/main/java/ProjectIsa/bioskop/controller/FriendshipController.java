package ProjectIsa.bioskop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ProjectIsa.bioskop.domain.Friendship;
import ProjectIsa.bioskop.domain.FriendshipPrimKey;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.service.FriendshipService;
import ProjectIsa.bioskop.service.UserServiceImpl;

@RestController
public class FriendshipController {

	@Autowired
	private FriendshipService friendshipService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(
			value = "/api/friendships/{userID}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Friendship>> getUsersFriendships(@PathVariable("userID") int userID) {
		List<Friendship> ret = friendshipService.getUsersFriendships(Integer.toUnsignedLong(userID));
		
		return new ResponseEntity<List<Friendship>>(ret, HttpStatus.OK);
	}
	
/*	@RequestMapping(
			value = "/api/friendships/nadjiSve",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Friendship>> nadjiSve() {
		List<Friendship> ret = friendshipService.nadjiSve();
		return new ResponseEntity<List<Friendship>>(ret, HttpStatus.OK);
	}
*/	
	@RequestMapping(
			value = "/api/friendships/findByUser/{userID}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Friendship>> nadjiPoKljucu(@PathVariable("userID") int userID) {
		List<Friendship> ret = friendshipService.nadjiPoKljucu(Integer.toUnsignedLong(userID));
		
		return new ResponseEntity<List<Friendship>>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/api/friendships/addFriendship/{username}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Friendship> addFriendship(@PathVariable("username") String username) {
		
		// PAZI NA USERNAME ZBOG .COM !!
		
		User exists = userService.getUser(username);
		Friendship friendship = new Friendship();
		
		// user you want to add doesn't exist!
		if (exists == null) {
			return new ResponseEntity<Friendship>(friendship, HttpStatus.NOT_FOUND);
		}
		
		FriendshipPrimKey pk = new FriendshipPrimKey();
		pk.setUserID1(exists.getId());
		friendship.setPrimKey(pk);
		
		Friendship fsAdded = friendshipService.addFriendship(friendship, request);
		
		
		if (fsAdded == null) {
			return new ResponseEntity<Friendship>(fsAdded, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Friendship>(fsAdded, HttpStatus.CREATED);
		
		
	}
	
}
