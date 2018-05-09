package ProjectIsa.bioskop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class MovieOrPerformance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false, unique=true)
	private String name;
	@Column(nullable = false)
	private String actors;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	private String producer;
	@Column(nullable = false)
	private int filmDuration;
	@Column(nullable = false)
	private String img;
	@Column(nullable = false)
	private double averageRating;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
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

	public MovieOrPerformance(String name, String actors, String type, String producer, int filmDuration,
			String img, double averageRating, String description, boolean isFilm) {
		super();
		this.name = name;
		this.actors = actors;
		this.type = type;
		this.producer = producer;
		this.filmDuration = filmDuration;
		this.img = img;
		this.averageRating = averageRating;
		this.description = description;
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
