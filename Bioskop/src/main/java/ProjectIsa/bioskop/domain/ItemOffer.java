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
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"user_id", "item_id"}))

public class ItemOffer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private User user;
	@Column(nullable = false)
	private Double price;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private ThematicItem item;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public ThematicItem getItem() {
		return item;
	}
	public void setItem(ThematicItem item) {
		this.item = item;
	}
	public ItemOffer(User user, Double price, ThematicItem item) {
		super();
		this.user = user;
		this.price = price;
		this.item = item;
	}
	public ItemOffer() {
	}
	
	
	
}
