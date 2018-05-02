package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.TheaterOrCinema;
import ProjectIsa.bioskop.repository.TheaterOrCinemaRepository;
@Service
public class TheaterOrCinemaService implements TheaterOrCinemaServiceInterface{

	@Autowired
	TheaterOrCinemaRepository repository;
	
	@Override
	public Collection<TheaterOrCinema> getTheaterOrCinemas() {
		Collection<TheaterOrCinema> cinemas = repository.getTheaterOrCinemas();
		if(cinemas.size() == 0) {
			return null;
		}
		return cinemas;
	}

	@Override
	public TheaterOrCinema addTheaterOrCinema(TheaterOrCinema tc) {
		Collection<TheaterOrCinema> entities = repository.getTheaterOrCinemas();
		for (TheaterOrCinema entity : entities ){
			if (entity.getName().equals(tc.getName())){
				return null;
			}
		}
		return repository.addTheaterOrCinema(tc);
	}

	@Override
	public void deleteTheaterOrCinema(TheaterOrCinema tc) {
		repository.deleteTheaterOrCinema(tc);
	}

	@Override
	public TheaterOrCinema getTheaterOrCinema(Long id) {
		return repository.getTheaterOrCinema(id);
	}

}
