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
import ProjectIsa.bioskop.domain.Ticket;
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
	public String addTheaterOrCinema(TheaterOrCinema tc) {
		
		if(tc.getName() == "" || tc.getDescription() == "" || tc.getAdress().getCity() == "" || tc.getAdress().getStreet() == "") {
			return "Please enter all required data!";
		}
		if (tc.getAdress() != null) {
			addAddress(tc.getAdress());
		}
		
		TheaterOrCinema t = repository.findByName(tc.getName());
		if (t != null) {
			return "Cinema/theater with the same name already exists!";
		}
		
		repository.save(tc);
		return null;
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
	public String changeInstitution(TheaterOrCinema institution, TheaterOrCinema newInstitution) {
		
		if(newInstitution.getName() == "" || newInstitution.getDescription() == "" || newInstitution.getAdress().getCity() == "" 
				|| newInstitution.getAdress().getStreet() == "") {
			return "Please enter all required data!";
		}
		
		if (!institution.getName().equals(newInstitution.getName())){
			TheaterOrCinema cinema = repository.findByName(newInstitution.getName());
			if (cinema != null){
				return "Cinema/theater with the same name already exists!";
			}
		}
		institution.setName(newInstitution.getName());
		institution.setDescription(newInstitution.getDescription());
		institution.setAdress(newInstitution.getAdress());
		repository.save(institution.getAdress());
		repository.save(institution);
		return null;
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
	public String changeRepertoar(TheaterOrCinema theaterOrCinema, Long projectionId) {

		Projection projection = projectionRepository.findById(projectionId);
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date currentDate = new Date();
		Date projDate = null;
		try {
			projDate = sdf.parse(projection.getDate().split("T")[0] + " " + projection.getDate().split("T")[1]);
		} catch (ParseException e) {
			return e.getMessage();
		}
		
		long millisecondsPerDay = 24 * 60 * 60 * 1000;
		
		//brisanje samo 24h pre odrzavanja projekcije
		//date.getTime() - how meny milliseconds have passed since this date
		if(!(Math.abs(currentDate.getTime() - projDate.getTime()) > millisecondsPerDay)) {
			return "You can not delete projection!";
		}
		/*
		if(projection.getTickets().size() != 0) {
			for(Ticket t: projection.getTickets()) {
				if(t.isReserved() == true) {
					return "";
				}
			}
			
		}*/
		theaterOrCinema.removeProjection(projection);
		projectionRepository.delete(projection);
		repository.save(theaterOrCinema);
		return null;
	}

	

}
