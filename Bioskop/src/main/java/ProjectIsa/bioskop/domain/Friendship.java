package ProjectIsa.bioskop.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Friendship {

	@EmbeddedId
	// FriendshipPrimKey  = userID1 + userID2
	private FriendshipPrimKey primKey;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private FriendshipStatus status;
	
	@Column(nullable = false)
	private long actionUserID;
	

	
	public Friendship() {
		
	}
	
	public Friendship(FriendshipPrimKey primKey, FriendshipStatus status, long actionUserID) {
		super();
		this.primKey = primKey;
		this.status = status;
		this.actionUserID = actionUserID;
	}

	public FriendshipPrimKey getPrimKey() {
		return primKey;
	}

	public void setPrimKey(FriendshipPrimKey primKey) {
		this.primKey = primKey;
	}

	public FriendshipStatus getStatus() {
		return status;
	}

	public void setStatus(FriendshipStatus status) {
		this.status = status;
	}

	public long getActionUserID() {
		return actionUserID;
	}

	public void setActionUserID(long actionUserID) {
		this.actionUserID = actionUserID;
	}


	
	
	
}

