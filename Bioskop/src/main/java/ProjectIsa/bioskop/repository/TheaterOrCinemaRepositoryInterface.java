package ProjectIsa.bioskop.repository;

import java.util.Collection;

import ProjectIsa.bioskop.domain.TheaterOrCinema;

public interface TheaterOrCinemaRepositoryInterface {
	Collection<TheaterOrCinema> getTheaterOrCinemas();
	TheaterOrCinema addTheaterOrCinema(TheaterOrCinema tc);
	void deleteTheaterOrCinema(TheaterOrCinema tc);
	TheaterOrCinema getTheaterOrCinema(Long id);
	
	TheaterOrCinema findByName(String name);
}
