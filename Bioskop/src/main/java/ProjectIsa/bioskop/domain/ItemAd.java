package ProjectIsa.bioskop.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class ItemAd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = true)
	private String picture;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY , mappedBy = "itemAd", cascade = CascadeType.REMOVE)
	private List<ItemOffer> offers;

	@ManyToOne(optional = false)
	private User owner;

	@Version
	private Long version;
	
	public List<ItemOffer> getOffers() {
		return offers;
	}
	public void setOffers(List<ItemOffer> offers) {
		this.offers = offers;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
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
	public ItemAd(Long id, String name, String description, String picture, User owner, Long version) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.picture = picture;
		this.owner = owner;
		this.version = version;
	}
	public ItemAd() {
		super();
	}
	
	
}
