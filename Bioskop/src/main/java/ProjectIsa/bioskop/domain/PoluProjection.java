package ProjectIsa.bioskop.domain;

public class PoluProjection {
	private String date;
	private int price;
	private String hallName;
	private String movieName;
	private String projectionName;
	
	public PoluProjection() {
		
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
	public PoluProjection(String date, int price, String hallName, String movieName, String projectionName) {
		super();
		this.date = date;
		this.price = price;
		this.hallName = hallName;
		this.movieName = movieName;
		this.projectionName = projectionName;
	}
	public String getProjectionName() {
		return projectionName;
	}
	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
	}
	
	
	
}
