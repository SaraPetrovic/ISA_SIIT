package ProjectIsa.bioskop.domain;

public class ChangedMovie {
	private String name;
	private String actors;
	private String type;
	private String producer;
	private int filmDuration;
	private String img;
	private String description;
	private String selectMovie;
	
	public ChangedMovie(){
		
	}

	public ChangedMovie(String name, String actors, String type, String producer, int filmDuration, String img,
			String description, String selectMovie) {
		super();
		this.name = name;
		this.actors = actors;
		this.type = type;
		this.producer = producer;
		this.filmDuration = filmDuration;
		this.img = img;
		this.description = description;
		this.selectMovie = selectMovie;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSelectMovie() {
		return selectMovie;
	}

	public void setSelectMovie(String selectMovie) {
		this.selectMovie = selectMovie;
	}
	
	
}
