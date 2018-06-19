package ProjectIsa.bioskop.domain;

import java.util.Date;

public class PoluProjection {
	private String date;
	private int price;
	private String hallName;
	private String movieName;
	private String projectionForChange;
	private String projectionName; //new projection name
	private String cinema;
	
	public PoluProjection() {
		
	}
	public PoluProjection(String date, int price, String hallName, String movieName, String projectionName, String newName, String cinema) {
		super();
		this.date = date;
		this.price = price;
		this.hallName = hallName;
		this.movieName = movieName;
		this.projectionForChange = projectionName;
		this.projectionName = newName;
		this.cinema = cinema;
	}
	public String getCinema() {
		return cinema;
	}

	public void setCinema(String cinema) {
		this.cinema = cinema;
	}

	public String getProjectionName() {
		return projectionName;
	}
	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
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
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getProjectionForChange() {
		return projectionForChange;
	}
	public void setProjectionForChange(String projectionName) {
		this.projectionForChange = projectionName;
	}
	
	
	
}
