package ProjectIsa.Bioskop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
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
	
	@Test
	public void testAddFriendship() {
		List<Friendship> friendships = friendshipService.getAllFriendships();

		int countBefore = friendships.size();
		long userID1 = Integer.toUnsignedLong(1);
		long userID2 = Integer.toUnsignedLong(2);
		FriendshipPrimKey pk = new FriendshipPrimKey();
		pk.setUserID1(userID2);

		Friendship fs = new Friendship(pk, FriendshipStatus.PENDING, userID1);

		User loggedUser = userService.getUser(userID1);
		request.getSession().setAttribute("user", loggedUser);
		friendshipService.addFriendship(fs, request);
		
		friendships = friendshipService.getAllFriendships();
		
		assertThat(friendships).hasSize(countBefore + 1);
		friendshipService.removeFriendship(fs);
	}
	
	@Test
	public void testFindOne() {
		// OVDE SAMO ZAMENI, kada odlucimo koji ce uvek biti u bazi......
		long userID1 = Integer.toUnsignedLong(1);
		long userID2 = Integer.toUnsignedLong(2);
		FriendshipPrimKey pk = new FriendshipPrimKey();
		pk.setUserID1(userID2);

		Friendship fs = new Friendship(pk, FriendshipStatus.PENDING, userID1);

		User loggedUser = userService.getUser(userID1);
		request.getSession().setAttribute("user", loggedUser);
		friendshipService.addFriendship(fs, request);
		
		Friendship found = friendshipService.getFriendshipByKey(pk);
		assertThat(found).isNotNull();
		
		friendshipService.removeFriendship(found);
		
		// npr ako doda samog sebe..
		pk.setUserID1(userID1);
		pk.setUserID2(userID1);
		
		Friendship notFound = friendshipService.getFriendshipByKey(pk);
		assertThat(notFound).isNull();
		
	}
	
}
