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
public class Hall {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false, unique=true)
	private String name;
	@Column(nullable = false)
	private int maxRow;
	@Column(nullable = false)
	private int maxColumn;
	@ManyToOne(optional = false)
	private TheaterOrCinema theaterOrCinema;
	
	
	public Hall() {
		
	}
	
	public Hall(int row, int column, TheaterOrCinema theaterOrCinema, String id) {
		super();
		this.maxRow = row;
		this.maxColumn = column;
		this.theaterOrCinema = theaterOrCinema;
		this.name = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String id) {
		this.name = id;
	}
	public TheaterOrCinema getTheaterOrCinema() {
		return theaterOrCinema;
	}
	public void setTheaterOrCinema(TheaterOrCinema theaterOrCinema) {
		this.theaterOrCinema = theaterOrCinema;
	}

	public int getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public int getMaxColumn() {
		return maxColumn;
	}

	public void setMaxColumn(int maxColumn) {
		this.maxColumn = maxColumn;
	}
}
