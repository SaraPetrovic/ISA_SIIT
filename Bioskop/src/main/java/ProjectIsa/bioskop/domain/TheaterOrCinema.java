package ProjectIsa.bioskop.domain;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class TheaterOrCinema {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String name;
	@OneToOne(optional = false)
	private Adresa adress;
	@Column(nullable = false)
	private String description;
	@OneToMany(cascade = CascadeType.ALL)
	private ArrayList<Ticket> fastRezTicket;
	@OneToMany(cascade = CascadeType.ALL)
	private ArrayList<Projection> projections;
	@Column(nullable = false)
	private double averageMark;
	@OneToMany(cascade = CascadeType.ALL)
	private ArrayList<Hall> halls;
	@Column(nullable = false)
	private boolean isCinema;
	
	public TheaterOrCinema() {
		
	}

	public TheaterOrCinema(String name, Adresa adress, String description, ArrayList<Ticket> fastRezTicket,
			ArrayList<Projection> projections, double averageMark, ArrayList<Hall> halls, boolean isCinema, String segment) {
		super();
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.fastRezTicket = fastRezTicket;
		this.projections = projections;
		this.averageMark = averageMark;
		this.halls = halls;
		//this.id = id;
		this.isCinema = isCinema;
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

	public ArrayList<Ticket> getFastRezTicket() {
		return fastRezTicket;
	}

	public void setFastRezTicket(ArrayList<Ticket> fastRezTicket) {
		this.fastRezTicket = fastRezTicket;
	}

	public ArrayList<Projection> getProjections() {
		return projections;
	}

	public void setProjections(ArrayList<Projection> projections) {
		this.projections = projections;
	}

	public double getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(double averageMark) {
		this.averageMark = averageMark;
	}

	public ArrayList<Hall> getHalls() {
		return halls;
	}

	public void setHalls(ArrayList<Hall> halls) {
		this.halls = halls;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isCinema() {
		return isCinema;
	}

	public void setCinema(boolean isCinema) {
		this.isCinema = isCinema;
	}
	
	
	
	
}
