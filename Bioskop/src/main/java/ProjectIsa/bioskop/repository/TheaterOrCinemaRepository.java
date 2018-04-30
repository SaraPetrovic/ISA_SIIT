package ProjectIsa.bioskop.repository;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Repository;

import ProjectIsa.bioskop.domain.TheaterOrCinema;

@Repository
public class TheaterOrCinemaRepository implements TheaterOrCinemaRepositoryInterface{

	private Collection<TheaterOrCinema> cinemas = new ArrayList<TheaterOrCinema>();
	
	@Override
	public Collection<TheaterOrCinema> getTheaterOrCinemas() {
		return cinemas;
	}

	@Override
	public TheaterOrCinema addTheaterOrCinema(TheaterOrCinema tc) {
		cinemas.add(tc);
		return tc;
	}

	@Override
	public void deleteTheaterOrCinema(TheaterOrCinema tc) {
		cinemas.remove(tc);
	}

	@Override
	public TheaterOrCinema getTheaterOrCinema(Long id) {

		for (TheaterOrCinema tc : cinemas){
			if (tc.getId() == id){
				return tc;
			}
		}
		return null;
	}

}
