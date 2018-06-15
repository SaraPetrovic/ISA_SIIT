package ProjectIsa.bioskop.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Ticket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private int row1;
	@Column(nullable = false)
	private int column1;
	//@ManyToOne(optional = false)
	//private TheaterOrCinema theaterOrCinema;
	@ManyToOne(optional = false)
	private Projection projection;
	@OneToOne(optional=false, cascade = CascadeType.ALL)
	private User user;
		
	public Ticket() {
		
	}

	public Ticket(Long id, int row, int column, Projection projection) {
		super();
		this.id = id;
		this.row1 = row;
		this.column1 = column;
		this.projection = projection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRow() {
		return row1;
	}

	public void setRow(int row) {
		this.row1 = row;
	}

	public int getColumn() {
		return column1;
	}

	public void setColumn(int column) {
		this.column1 = column;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}
	
	
	
}
