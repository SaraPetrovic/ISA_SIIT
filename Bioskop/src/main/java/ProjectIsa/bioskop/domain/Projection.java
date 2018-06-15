package ProjectIsa.bioskop.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Projection{
	
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, unique=true)
	private String name;
	@Column(nullable = false)
	private String date;
	@Column(nullable = false)
	private int price;
	@ManyToOne(optional = false)
	private Hall hall;
	@ManyToOne(optional = false)
	private MovieOrPerformance movieOrPerformance;
	@ManyToOne(optional = false)
	private TheaterOrCinema theaterOrCinema;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ticket> fastRezTicket;
	
	public Projection() {
		
	}

	public Projection(Long id, String name, String date, int price, Hall hall, MovieOrPerformance movieOrPerformance,
			TheaterOrCinema theaterOrCinema, List<Ticket> fastRezTicket) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.price = price;
		this.hall = hall;
		this.movieOrPerformance = movieOrPerformance;
		this.theaterOrCinema = theaterOrCinema;
		this.fastRezTicket = fastRezTicket;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public MovieOrPerformance getMovieOrPerformance() {
		return movieOrPerformance;
	}

	public void setMovieOrPerformance(MovieOrPerformance movieOrPerformance) {
		this.movieOrPerformance = movieOrPerformance;
	}

	public TheaterOrCinema getTheaterOrCinema() {
		return theaterOrCinema;
	}

	public void setTheaterOrCinema(TheaterOrCinema theaterOrCinema) {
		this.theaterOrCinema = theaterOrCinema;
	}
	public void addTicket(Ticket ticket) {
		this.fastRezTicket.add(ticket);
		ticket.setProjection(this);
	}
	public void removeTicket(Ticket ticket) {
		this.fastRezTicket.remove(ticket);
	}
	
}
