package ProjectIsa.bioskop.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.domain.TheaterOrCinema;
import ProjectIsa.bioskop.repository.CinemaDBRepository;
import ProjectIsa.bioskop.repository.ProjectionsDBRepository;
@Service
public class TheaterOrCinemaService implements TheaterOrCinemaServiceInterface{

	@Autowired
	CinemaDBRepository repository;
	@Autowired
	ProjectionsDBRepository projectionRepository;
	
	@Override
	public List<TheaterOrCinema> getTheaterOrCinemas() {
		List<TheaterOrCinema> cinemas = repository.findAll();
		if(cinemas.size() == 0) {
			return null;
		}
		return cinemas;
	}

	@Override
	public TheaterOrCinema addTheaterOrCinema(TheaterOrCinema tc) {
		
		if(tc.getName() == "" || tc.getDescription() == "" || tc.getAdress().getCity() == "" || tc.getAdress().getStreet() == "") {
			return null;
		}
		if (tc.getAdress() != null) {
			addAddress(tc.getAdress());
		}
		
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
	public TheaterOrCinema changeInstitution(TheaterOrCinema institution, TheaterOrCinema newInstitution) {
		
		if (!institution.getName().equals(newInstitution.getName())){
			TheaterOrCinema cinema = repository.findByName(newInstitution.getName());
			if (cinema != null){
				return null;
			}
		}
		institution.setName(newInstitution.getName());
		institution.setDescription(newInstitution.getDescription());
		institution.setAdress(newInstitution.getAdress());
		repository.save(institution.getAdress());
		repository.save(institution);
		return institution;
	}
	
	
	@Override
	public Adresa addAddress(Adresa address) {

		Adresa newAddress = repository.save(address);
		return newAddress;
	}

	@Override
	public TheaterOrCinema findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public TheaterOrCinema changeRepertoar(TheaterOrCinema theaterOrCinema, Long projectionId) {

		Projection projection = projectionRepository.findById(projectionId);
		
		//brisanje samo 24h pre odrzavanja projekcije
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date currentDate = new Date();
		Date projDate = null;
		try {
			projDate = sdf.parse(projection.getDate().split("T")[0] + " " + projection.getDate().split("T")[1]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long millisecondsPerDay = 24 * 60 * 60 * 1000;
		
		//date.getTime() - how meny milliseconds have passed since this date
		if(!(Math.abs(currentDate.getTime() - projDate.getTime()) > millisecondsPerDay)) {
			return null;
		}
		
		theaterOrCinema.removeProjection(projection);
		projectionRepository.delete(projection);
		repository.save(theaterOrCinema);
		return theaterOrCinema;
	}

	

}
