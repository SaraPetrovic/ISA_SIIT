package ProjectIsa.bioskop.domain;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class TheaterOrCinema {
	private String name;
	private Adresa adress;
	private String description;
	private ArrayList<Ticket> fastRezTicket;
	private ArrayList<Projection> projections;
	//SEGMENTI I MESTA U SALI
	private Long id;
	private boolean isCinema;
	
	public TheaterOrCinema() {
		
	}
	public TheaterOrCinema(String name, Adresa adress, String description, ArrayList<Ticket> fastRezTicket,
			ArrayList<Projection> projections, Long id, boolean isCinema) {
		super();
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.fastRezTicket = fastRezTicket;
		this.projections = projections;
		this.id = id;
		this.isCinema = isCinema;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Adresa getAdress() {
		return adress;
	}
	public void setAdress(Adresa adress) {
		this.adress = adress;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Ticket> getFastRezTicket() {
		return fastRezTicket;
	}
	public void setFastRezTicket(ArrayList<Ticket> fastRezTicket) {
		this.fastRezTicket = fastRezTicket;
	}
	public ArrayList<Projection> getProjections() {
		return projections;
	}
	public void setProjections(ArrayList<Projection> projections) {
		this.projections = projections;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isCinema() {
		return isCinema;
	}
	public void setCinema(boolean isCinema) {
		this.isCinema = isCinema;
	}
	
	
	
}
