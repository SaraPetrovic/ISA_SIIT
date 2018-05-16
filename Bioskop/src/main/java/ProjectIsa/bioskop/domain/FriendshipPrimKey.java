package ProjectIsa.bioskop.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FriendshipPrimKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private long userID1;
	@Column(nullable = false)
	private long userID2;
	
	public FriendshipPrimKey() {
		
	}
	
	public FriendshipPrimKey(long userID1, long userID2) {
		super();
		this.userID1 = userID1;
		this.userID2 = userID2;
	}

	public long getUserID1() {
		return userID1;
	}

	public void setUserID1(long userID1) {
		this.userID1 = userID1;
	}

	public long getUserID2() {
		return userID2;
	}

	public void setUserID2(long userID2) {
		this.userID2 = userID2;
	}
	
	
	
	
}
