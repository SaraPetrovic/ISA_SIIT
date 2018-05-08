package ProjectIsa.bioskop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;


@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private int row;
	@Column(nullable = false)
	private int column;
	@ManyToOne(optional = false)
	private TheaterOrCinema theaterOrCinema;
	@ManyToOne(optional = false)
	private MovieOrPerformance projekcija;
		
	public Ticket() {
		
	}
	
	
	
	public Ticket(int row, int column, TheaterOrCinema theaterOrCinema, MovieOrPerformance projekcija) {
		super();
		this.row = row;
		this.column = column;
		this.theaterOrCinema = theaterOrCinema;
		this.projekcija = projekcija;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
	
}
