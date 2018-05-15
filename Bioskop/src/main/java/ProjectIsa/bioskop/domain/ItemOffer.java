package ProjectIsa.bioskop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class ItemOffer {
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
