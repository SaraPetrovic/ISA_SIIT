package ProjectIsa.bioskop.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"projection_id"}))
public class Ticket{
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private int row;
	@Column(nullable = false)
	private int column;
	//@ManyToOne(optional = false)
	//private TheaterOrCinema theaterOrCinema;
	@JsonBackReference
	@ManyToOne(optional = false)
	private Projection projection;
		
	public Ticket() {
		
	}

	public Ticket(Long id, int row, int column, Projection projection) {
		super();
		this.id = id;
		this.row = row;
		this.column = column;
		this.projection = projection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}
	
	
	
}
