package ProjectIsa.bioskop.service;

import java.util.Collection;

import ProjectIsa.bioskop.domain.TheaterOrCinema;

public interface TheaterOrCinemaServiceInterface {
	Collection<TheaterOrCinema> getTheaterOrCinemas();
	TheaterOrCinema addTheaterOrCinema(TheaterOrCinema tc);
	void deleteTheaterOrCinema(TheaterOrCinema tc);
	TheaterOrCinema getTheaterOrCinema(Long id);
	
	TheaterOrCinema changeInstitution(String institutionName, TheaterOrCinema newInstitution);
	
}
