package ProjectIsa.bioskop.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;




@Entity
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, unique=true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false, name = "usertype")
	@Enumerated(EnumType.STRING)
	private UserType userType;
	@OneToOne(optional = false)
	private Adresa address;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false, name = "firstname")
	private String firstName;
	@Column(nullable = false, name = "lastname")
	private String lastName;
	@Column(nullable = false)
	private Boolean isFirstLogin;
	@OneToMany(fetch = FetchType.LAZY , mappedBy = "user")
	private Set<ItemOffer> itemOffers;



	public Long getId() {
		return id;
	}
	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}
	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public User() {
		super();
	}
	public User(String firstName,String lastName, String username, String password, UserType userType, Adresa address, String email) {
		super();
		this.firstName = firstName;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.address = address;
		this.email = email;
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public Adresa getAddress() {
		return address;
	}
	public void setAddress(Adresa address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
