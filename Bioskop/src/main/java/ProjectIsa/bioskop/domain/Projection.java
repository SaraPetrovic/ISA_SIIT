package ProjectIsa.bioskop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Projection {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String date;
	@Column(nullable = false)
	private int price;
	@OneToMany(cascade = CascadeType.ALL)
	private Hall hall;
	@Column(nullable = false)
	private MovieOrPerformance movieOrPerformance;
	
	
	public Projection() {
		
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Projection(String date, int price, Hall hall, MovieOrPerformance movieOrPerformance, Long id) {
		super();
		this.date = date;
		this.price = price;
		this.hall = hall;
		this.movieOrPerformance = movieOrPerformance;
		this.id = id;
	}
	
	
}
