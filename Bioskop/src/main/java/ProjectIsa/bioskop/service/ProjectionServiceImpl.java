package ProjectIsa.bioskop.service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.MovieOrPerformance;
import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.repository.CinemaDBRepository;
import ProjectIsa.bioskop.repository.HallDBRepository;
import ProjectIsa.bioskop.repository.MovieDBRepository;
import ProjectIsa.bioskop.repository.ProjectionsDBRepository;

@Service
public class ProjectionServiceImpl implements ProjectionServiceInterface {
	@Autowired
	ProjectionsDBRepository repository;
	@Autowired
	CinemaDBRepository cinemaRepository;
	@Autowired
	HallDBRepository hallRepository;
	@Autowired
	MovieDBRepository movieRepository;
	
	@Override
	public Collection<Projection> getProjections() {
		Collection<Projection> projections = repository.findAll();
		if(projections.size() == 0) {
			return null;
		}
		return projections;
	}

	@Override
	public String addProjection(Projection projection) {
		
		if(projection.getDate() == null || projection.getPrice() == 0){
			return "Please enter all required data!";
		}
		

		String projDate = null;
		try{
			projDate = projection.getDate().split("T")[0] + " " + projection.getDate().split("T")[1];
		}catch(Exception e) {
			return "Not valid date input!";
		}
		//ako je unesen datum projekcije pre trenutnog, return null
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date currentDate = new Date();
		try {
			if((sdf.parse(projDate)).before(currentDate)) {
				return "Not valid date input!";
			}
		} catch (ParseException e) {
			return e.getMessage();
		}
		
		String datum = projDate.split(" ")[0];
		String vreme = projDate.split(" ")[1];
		projection.setName(projection.getMovieOrPerformance().getName() + " " + datum + " " + vreme + "h");
		
		//vreme pocetka u minutima
		int start = Integer.parseInt(vreme.split(":")[0]) * 60 + Integer.parseInt(vreme.split(":")[1]);
		
		//ako u odredjenom bioskopu postoje dve proj sa istim nazivom u istoj sali, return null
		for(Projection p : repository.findAll()) {
			if(projection.getTheaterOrCinema().getName().equals(p.getTheaterOrCinema().getName()) 
					&& projection.getName().equals(p.getName())
					&& projection.getHall().getName().equals(p.getHall().getName())) {
				
				return "Occupied hall in that term!";
			}
		}
		
		// treba da se odradi provera sale, datuma i vremena!!
		for(Projection proj : repository.findAll()) {
			String date = proj.getDate().split("T")[0];
			String time = proj.getDate().split("T")[1];
			int hours = Integer.parseInt(time.split(":")[0]);
			int minutes = Integer.parseInt(time.split(":")[1]);
			//vreme kraja u minutima
			int end = hours * 60 + minutes + proj.getMovieOrPerformance().getFilmDuration() + 15;
			
			if(projection.getTheaterOrCinema().getName().equals(proj.getTheaterOrCinema().getName())
					&& projection.getHall().getName().equals(proj.getHall().getName())
					&& datum.equals(date)) {
				if(!(end <= start)) {
					return "Occupied hall in that term!";
				}
			}
		}
		
		//da li je hall iz izabranog bioskopa
		if(!(projection.getHall().getTheaterOrCinema().getName().equals(projection.getTheaterOrCinema().getName()))) {
			return "The chosen hall is not from selected cinema!";
		}

		repository.save(projection);
		return null;
	}

	@Override
	public void deleteProjection(Projection projection) {
		repository.delete(projection);
	}

	@Override
	public Projection getProjection(Long id) {
		Projection proj = repository.findById(id);
		return proj;
	}

	@Override
	public Projection getProjectionByName(String name) {
		Projection proj = repository.findByName(name);
		return proj;
	}

	public Projection changeProjection(Projection projection, Projection newProjection) {
		
		if (!projection.getName().equals(newProjection.getName())){
			Projection proj = repository.findByName(newProjection.getName());
			if (proj != null){
				return null;	//vec postoji
			}
		}
		
		//ako u odredjenom bioskopu postoje dve proj sa istim nazivom u istoj sali, return null
		for(Projection p : repository.findAll()) {
			if(newProjection.getTheaterOrCinema().getName().equals(p.getTheaterOrCinema().getName()) 
					&& newProjection.getName().equals(p.getName())
					&& newProjection.getHall().getName().equals(p.getHall().getName())) {
				
				return null;
			}
		}
		projection.setName(newProjection.getName());
		projection.setDate(newProjection.getDate());
		projection.setPrice(newProjection.getPrice());
		projection.setHall(newProjection.getHall());
		projection.setMovieOrPerformance(newProjection.getMovieOrPerformance());
		projection.setTheaterOrCinema(newProjection.getTheaterOrCinema());
		repository.save(projection);
		return projection;
	}

	@Override
	public List<Projection> getCinemasProjections(Long id) {
		List<Projection> ret = repository.getAllByCinemaId(id);
		return ret;
	}

	@Override
	public Projection makeReservation(Projection proj) {
		
		return repository.save(proj);
	}
	
	
	
}
