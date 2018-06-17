package ProjectIsa.bioskop.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TheaterOrCinema implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, unique=true)
	private String name;
	@OneToOne(optional = false)
	private Adresa adress;
	@Column(nullable = false)
	private String description;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ticket> fastRezTicket;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Projection> projections;
	@Column(nullable = false)
	private double averageMark;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Hall> halls;
	@Column(nullable = false)
	private boolean isCinema;
	
	public TheaterOrCinema() {
		
	}
	
	public TheaterOrCinema(String name, Adresa adress, String description) {
		this.name = name;
		this.adress = adress;
		this.description = description;
	}
	
	public TheaterOrCinema(Long id, String name, Adresa adress, String description, List<Ticket> fastRezTicket,
			List<Projection> projections, double averageMark, List<Hall> halls, boolean isCinema) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.fastRezTicket = fastRezTicket;
		this.projections = projections;
		this.averageMark = averageMark;
		this.halls = halls;
		this.isCinema = isCinema;
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

	public Adresa getAdress() {
		return adress;
	}

	public void setAdress(Adresa adress) {
		this.adress = adress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Ticket> getFastRezTicket() {
		return fastRezTicket;
	}

	public void setFastRezTicket(List<Ticket> fastRezTicket) {
		this.fastRezTicket = fastRezTicket;
	}

	public List<Projection> getProjections() {
		return projections;
	}

	public void setProjections(List<Projection> projections) {
		this.projections = projections;
	}

	public double getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(double averageMark) {
		this.averageMark = averageMark;
	}

	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}

	public boolean isCinema() {
		return isCinema;
	}

	public void setCinema(boolean isCinema) {
		this.isCinema = isCinema;
	}

	public void addHall(Hall hall) {
		halls.add(hall);
		hall.setTheaterOrCinema(this);
	}
	public void removeHall(Hall hall) {
		halls.remove(hall);
	}
	public void addProjection(Projection projection) {
		this.projections.add(projection);
		projection.setTheaterOrCinema(this);
	}

	public void removeProjection(Projection projection) {
		this.projections.remove(projection);
	}
	
	
}
