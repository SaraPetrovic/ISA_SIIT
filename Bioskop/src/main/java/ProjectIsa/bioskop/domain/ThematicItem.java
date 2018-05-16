package ProjectIsa.bioskop.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;


@Entity
public class ThematicItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = true)
	private String picture;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private boolean isOfficial;
	@Column(nullable = true)
	private User owner;

	@Version
	private Long version;
	
	public boolean isOfficial() {
		return isOfficial;
	}
	public void setOfficial(boolean isOfficial) {
		this.isOfficial = isOfficial;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public ThematicItem(Long id, String name, double price, String description, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.picture = picture;
	}
	public ThematicItem() {
		super();
	}
	
	
}
