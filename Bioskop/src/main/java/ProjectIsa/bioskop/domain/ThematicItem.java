package ProjectIsa.bioskop.domain;

import org.springframework.stereotype.Component;

@Component
public class ThematicItem {
	private Long id;
	private String name;
	private double price;
	private String description;
	private String picture;
	
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
