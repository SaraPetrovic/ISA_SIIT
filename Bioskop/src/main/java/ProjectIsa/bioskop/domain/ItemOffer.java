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
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"user_id", "item_ad_id"}))

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
	private ItemAd itemAd;
	
	
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
	public ItemAd getItem() {
		return itemAd;
	}
	public void setItem(ItemAd item) {
		this.itemAd = item;
	}
	public ItemOffer(User user, Double price, ItemAd item) {
		super();
		this.user = user;
		this.price = price;
		this.itemAd = item;
	}
	public ItemOffer() {
	}
	
	
	
}
