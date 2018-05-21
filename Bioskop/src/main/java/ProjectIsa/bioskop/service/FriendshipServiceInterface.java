package ProjectIsa.bioskop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ProjectIsa.bioskop.domain.Friendship;
import ProjectIsa.bioskop.domain.FriendshipPrimKey;
import ProjectIsa.bioskop.domain.User;

public interface FriendshipServiceInterface {

	List<Friendship> getAllFriendships();
	List<Friendship> getUsersFriendships(long userID);
	//boolean friendshipExists(FriendshipPrimKey primKey);
	Friendship getFriendshipByKey(FriendshipPrimKey primKey);
	Friendship addFriendship(Friendship friendship, HttpServletRequest request);
	
	List<Friendship> nadjiPoKljucu(long userID);
	
	Friendship acceptFriendship(Friendship friendship, long loggedUserID);
	Friendship declineFriendship(Friendship friendship, long loggedUserID);
	
}
