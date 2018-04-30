package ProjectIsa.bioskop.domain;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Ticket {
	private ArrayList<String> seat;
	private TheaterOrCinema theaterOrCinema;
	private MovieOrPerformance projekcija;
	private Long id;
	
	public Ticket() {
		
	}
	public Ticket(ArrayList<String> seat, TheaterOrCinema theaterOrCinema, MovieOrPerformance projekcija, Long id) {
		super();
		this.seat = seat;
		this.theaterOrCinema = theaterOrCinema;
		this.projekcija = projekcija;
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ArrayList<String> getSeat() {
		return seat;
	}
	public void setSeat(ArrayList<String> seat) {
		this.seat = seat;
	}
	public TheaterOrCinema getTheaterOrCinema() {
		return theaterOrCinema;
	}
	public void setTheaterOrCinema(TheaterOrCinema theaterOrCinema) {
		this.theaterOrCinema = theaterOrCinema;
	}
	public MovieOrPerformance getProjekcija() {
		return projekcija;
	}
	public void setProjekcija(MovieOrPerformance projekcija) {
		this.projekcija = projekcija;
	}
	
}
