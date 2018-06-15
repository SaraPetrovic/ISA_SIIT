package ProjectIsa.bioskop.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Ticket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private int red;
	@Column(nullable = false)
	private int kolona;
	//@ManyToOne(optional = false)
	//private TheaterOrCinema theaterOrCinema;
	@ManyToOne(optional = false)
	private Projection projection;
	@OneToOne(optional=false, cascade = CascadeType.ALL)
	private User user;
		
	public Ticket() {
		
	}

	public Ticket(Long id, int row, int column, Projection projection, User user) {
		super();
		this.id = id;
		this.red = row;
		this.kolona = column;
		this.projection = projection;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int row) {
		this.red = row;
	}

	public int getKolona() {
		return kolona;
	}

	public void setKolona(int column) {
		this.kolona = column;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
