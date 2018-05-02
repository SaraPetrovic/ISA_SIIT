package ProjectIsa.bioskop.domain;

import java.sql.Time;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class MovieOrPerformance {
	private String name;
	private String actors;
	private String type;
	private String producer;
	private int filmDuration;
	private String img;
	private double averageRating;
	private String description;
	private long id;
	private boolean isFilm;
	
	public MovieOrPerformance() {
		
	}
	
	public MovieOrPerformance(String name, String actors, String type, String producer, int filmDuration) {
		super();
		this.name = name;
		this.actors = actors;
		this.type = type;
		this.producer = producer;
		this.filmDuration = filmDuration;
	}
	
	public MovieOrPerformance(String name, String actors, String type, String producer, int filmDuration, String img,
			double mark, String description, ArrayList<Integer> halls, Time terms, long id,
			boolean isFilm, ArrayList<Ticket> tickets) {
		super();
		this.name = name;
		this.actors = actors;
		this.type = type;
		this.producer = producer;
		this.filmDuration = filmDuration;
		this.img = img;
		this.averageRating = mark;
		this.description = description;
		this.id = id;
		this.isFilm = isFilm;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double mark) {
		this.averageRating = mark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
