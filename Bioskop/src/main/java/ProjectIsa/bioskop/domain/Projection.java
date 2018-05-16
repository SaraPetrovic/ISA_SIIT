package ProjectIsa.bioskop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Projection {
	
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
	
	public Projection() {
		
	}
	public Projection(String naziv, String date, int price, Hall hall, MovieOrPerformance movieOrPerformance, TheaterOrCinema theaterOrCinema) {
		super();
		this.name = naziv;
		this.date = date;
		this.price = price;
		this.hall = hall;
		this.movieOrPerformance = movieOrPerformance;
		this.theaterOrCinema = theaterOrCinema;
	}
	public TheaterOrCinema getTheaterOrCinema() {
		return theaterOrCinema;
	}
	public void setTheaterOrCinema(TheaterOrCinema theaterOrCinema) {
		this.theaterOrCinema = theaterOrCinema;
	}
	public String getName() {
		return name;
	}
	public void setName(String naziv) {
		this.name = naziv;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
}
