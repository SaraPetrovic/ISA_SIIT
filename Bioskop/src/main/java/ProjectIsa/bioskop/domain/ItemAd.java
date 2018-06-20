package ProjectIsa.bioskop.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ProjectIsa.bioskop.controller.CustomJsonDateDeserializer;
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
	@Column(nullable = false)
	private Boolean approved;
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date expiryDate;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY , mappedBy = "itemAd", cascade = CascadeType.REMOVE)
	private List<ItemOffer> offers;

	@ManyToOne(optional = false)
	private User owner;
	@Column
	Boolean active;
	@Version
	private Long version;
	
	
	


	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean isApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
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
	
	
	public Date getExpiryDate() {
		return expiryDate;
	}
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Boolean getApproved() {
		return approved;
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
