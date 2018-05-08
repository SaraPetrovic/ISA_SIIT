package ProjectIsa.bioskop.service;

import java.util.List;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.TheaterOrCinema;

public interface TheaterOrCinemaServiceInterface {
	List<TheaterOrCinema> getTheaterOrCinemas();
	TheaterOrCinema addTheaterOrCinema(TheaterOrCinema tc);
	void deleteTheaterOrCinema(TheaterOrCinema tc);
	TheaterOrCinema getTheaterOrCinema(Long id);
	
	TheaterOrCinema changeInstitution(String institutionName, TheaterOrCinema newInstitution);
	
	Adresa addAddress(Adresa address);
	
}
