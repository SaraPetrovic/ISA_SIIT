package ProjectIsa.Bioskop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ProjectIsa.bioskop.domain.Friendship;
import ProjectIsa.bioskop.domain.FriendshipPrimKey;
import ProjectIsa.bioskop.domain.FriendshipStatus;
import ProjectIsa.bioskop.domain.User;
import ProjectIsa.bioskop.service.FriendshipService;
import ProjectIsa.bioskop.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipServiceTest {

	@Autowired
	FriendshipService friendshipService;
	@Autowired
	UserService userService;
	@Autowired
	HttpServletRequest request;
	
	public void testAddFriendship() {
		List<Friendship> friendships = friendshipService.getAllFriendships();
		int countBefore = friendships.size();
		FriendshipPrimKey pk = new FriendshipPrimKey(1, 5);
		Friendship fs = new Friendship(pk, FriendshipStatus.PENDING, 1);
		
		//request.setAttribute("user", );
		//friendshipService.addFriendship(friendship, request);
	}
	
}
