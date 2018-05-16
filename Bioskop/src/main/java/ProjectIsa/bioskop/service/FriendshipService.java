package ProjectIsa.bioskop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Friendship;
import ProjectIsa.bioskop.domain.FriendshipPrimKey;
import ProjectIsa.bioskop.domain.FriendshipStatus;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.repository.FriendshipDBRepository;

@Service
public class FriendshipService implements FriendshipServiceInterface {

	@Autowired
	private FriendshipDBRepository friendshipRepository;
	
	@Override
	public List<Friendship> getUsersFriendships(long userID) {
		
		return friendshipRepository.findAllByPrimKey_UserID1OrPrimKey_UserID2(userID, userID);
	}


	@Override
	public Friendship addFriendship(Friendship friendship, HttpServletRequest request) {
		
		long userID1, userID2;
		
		User loggedUser = (User) request.getSession().getAttribute("user");
		
		userID1 = loggedUser.getId();
		userID2 = friendship.getPrimKey().getUserID1();
		
		// resava se redundantnost -> userID1 UVEK MANJI od userID2
		if (userID1 < userID2) {
			friendship.getPrimKey().setUserID1(userID1);
			friendship.getPrimKey().setUserID2(userID2);
		} else {
			friendship.getPrimKey().setUserID1(userID2);
			friendship.getPrimKey().setUserID2(userID1);
		}
		
		Friendship friendshipCheck = this.getFriendshipByKey(friendship.getPrimKey());
		
		
		if (friendshipCheck != null) {
			// vrati NULL ili sta vec -> friendship vec postoji
			return null;
		} else {
			// dodaj friendship!
			friendship.setActionUserID(userID1); 	// logged user salje friend request!
			friendship.setStatus(FriendshipStatus.PENDING);
		}
		
		
		
		return friendshipRepository.save(friendship);
	}

	
	@Override
	public Friendship getFriendshipByKey(FriendshipPrimKey primKey) {
		
		return friendshipRepository.findByPrimKey(primKey);
	}



	@Override
	public List<Friendship> nadjiPoKljucu(long userID) {
		
		return friendshipRepository.nadjiPoKljucu(userID);
	}



	
}
