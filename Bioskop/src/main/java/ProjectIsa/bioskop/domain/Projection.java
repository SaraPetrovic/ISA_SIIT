package ProjectIsa.bioskop.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Projection {
	private Date date;
	private int price;
	private Hall hall;
	private MovieOrPerformance movieOrPerformance;
	private Long id;
	
	public Projection() {
		
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public Projection(Date date, int price, Hall hall, MovieOrPerformance movieOrPerformance, Long id) {
		super();
		this.date = date;
		this.price = price;
		this.hall = hall;
		this.movieOrPerformance = movieOrPerformance;
		this.id = id;
	}
	
	
}
