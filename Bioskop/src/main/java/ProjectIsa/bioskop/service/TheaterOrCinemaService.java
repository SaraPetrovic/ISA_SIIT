package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.TheaterOrCinema;
import ProjectIsa.bioskop.repository.CinemaDBRepository;
@Service
public class TheaterOrCinemaService implements TheaterOrCinemaServiceInterface{

	@Autowired
	CinemaDBRepository repository;
	
	@Override
	public Collection<TheaterOrCinema> getTheaterOrCinemas() {
		Collection<TheaterOrCinema> cinemas = repository.findAll();
		if(cinemas.size() == 0) {
			return null;
		}
		return cinemas;
	}

	@Override
	public TheaterOrCinema addTheaterOrCinema(TheaterOrCinema tc) {
		
		TheaterOrCinema t = repository.findByName(tc.getName());
		if (t != null) {
			return null;
		}
		
		return repository.save(tc);
	}

	@Override
	public void deleteTheaterOrCinema(TheaterOrCinema tc) {
		repository.delete(tc);
	}

	@Override
	public TheaterOrCinema getTheaterOrCinema(Long id) {
		return repository.findById(id);
	}

	@Override
	public TheaterOrCinema changeInstitution(String institutionName, TheaterOrCinema newInstitution) {
		
		for(TheaterOrCinema cinema : getTheaterOrCinemas()) {
			if(cinema.getName().equals(institutionName)) {
				
			}
		}
		
		return null;
	}
	

}
