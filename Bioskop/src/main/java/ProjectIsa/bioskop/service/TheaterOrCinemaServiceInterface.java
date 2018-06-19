package ProjectIsa.bioskop.service;

import java.util.List;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.domain.TheaterOrCinema;

public interface TheaterOrCinemaServiceInterface {
	List<TheaterOrCinema> getTheaterOrCinemas();
	String addTheaterOrCinema(TheaterOrCinema tc);
	void deleteTheaterOrCinema(TheaterOrCinema tc);
	TheaterOrCinema getTheaterOrCinema(Long id);
	
	String changeInstitution(TheaterOrCinema institution, TheaterOrCinema newInstitution);
	
	Adresa addAddress(Adresa address);
	
	TheaterOrCinema findByName(String name);

	String changeRepertoar(TheaterOrCinema cinema, Long projectionId);
}
