package ProjectIsa.bioskop.domain;

import java.awt.Image;
import java.sql.Time;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Projection {
	private String name;
	private String actors;
	private String type;
	private String producer;
	private int filmDuration;
	private Image img;
	private double mark;
	private String description;
	private ArrayList<Integer> halls;
	private Time terms;
	private int price;
	private long id;
	private boolean isFilm;
	private ArrayList<Ticket> tickets;
	
	public Projection() {
		
	}
	
	public Projection(String name, String actors, String type, String producer, int filmDuration) {
		super();
		this.name = name;
		this.actors = actors;
		this.type = type;
		this.producer = producer;
		this.filmDuration = filmDuration;
	}
	
	public Projection(String name, String actors, String type, String producer, int filmDuration, Image img,
			double mark, String description, ArrayList<Integer> halls, Time terms, int price, long id,
			boolean isFilm, ArrayList<Ticket> tickets) {
		super();
		this.name = name;
		this.actors = actors;
		this.type = type;
		this.producer = producer;
		this.filmDuration = filmDuration;
		this.img = img;
		this.mark = mark;
		this.description = description;
		this.halls = halls;
		this.terms = terms;
		this.price = price;
		this.id = id;
		this.isFilm = isFilm;
		this.tickets = tickets;
	}
	
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}

	public boolean getIsFilm() {
		return isFilm;
	}
	
	public void setIsFilm(boolean isFilm) {
		this.isFilm = isFilm;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getFilmDuration() {
		return filmDuration;
	}

	public void setFilmDuration(int filmDuration) {
		this.filmDuration = filmDuration;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Integer> getHalls() {
		return halls;
	}

	public void setHalls(ArrayList<Integer> hall) {
		this.halls = hall;
	}

	public Time getTerms() {
		return terms;
	}

	public void setTerms(Time terms) {
		this.terms = terms;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
