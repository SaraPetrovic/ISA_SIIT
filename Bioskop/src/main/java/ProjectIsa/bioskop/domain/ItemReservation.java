package ProjectIsa.bioskop.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"official_item_id", "user_id"}))
public class ItemReservation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	private OfficialItem officialItem;
	@JsonBackReference
	@ManyToOne(optional = false)
	private User user;

	
	
	
	public ItemReservation() {

	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ItemReservation(OfficialItem item, User user) {
		super();
		this.officialItem = item;
		this.user = user;
	}
	public OfficialItem getItem() {
		return officialItem;
	}
	public void setItem(OfficialItem item) {
		this.officialItem  = item;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
