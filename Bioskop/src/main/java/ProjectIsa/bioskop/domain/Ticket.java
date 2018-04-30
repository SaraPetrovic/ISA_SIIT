package ProjectIsa.bioskop.domain;

import org.springframework.stereotype.Component;

@Component
public class Ticket {
	private int seat;
	private int row;
	private int column;
	private double price;
	private long id;
	
	public Ticket(){
		
	}
	public Ticket(int seat, int row, int column, double price, long id) {
		super();
		this.seat = seat;
		this.row = row;
		this.column = column;
		this.price = price;
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
