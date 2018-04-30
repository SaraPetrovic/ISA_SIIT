package ProjectIsa.bioskop.domain;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Hall {
	private ArrayList<ArrayList<Integer>> seats;
	private TheaterOrCinema theaterOrCinema;
	private String id;
	
	public Hall() {
		
	}
	public Hall(ArrayList<ArrayList<Integer>> seats, TheaterOrCinema theaterOrCinema, String id) {
		super();
		this.seats = seats;
		this.theaterOrCinema = theaterOrCinema;
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TheaterOrCinema getTheaterOrCinema() {
		return theaterOrCinema;
	}
	public void setTheaterOrCinema(TheaterOrCinema theaterOrCinema) {
		this.theaterOrCinema = theaterOrCinema;
	}
	public ArrayList<ArrayList<Integer>> getSeats() {
		return seats;
	}

	public void setSeats(ArrayList<ArrayList<Integer>> seats) {
		this.seats = seats;
	}
}
