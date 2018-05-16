package ProjectIsa.bioskop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.domain.TheaterOrCinema;
import ProjectIsa.bioskop.repository.CinemaDBRepository;
import ProjectIsa.bioskop.repository.ProjectionsDBRepository;

@Service
public class ProjectionServiceImpl implements ProjectionServiceInterface {
	@Autowired
	ProjectionsDBRepository repository;
	@Autowired
	CinemaDBRepository cinemaRepository;
	
	@Override
	public Collection<Projection> getProjections() {
		Collection<Projection> projections = repository.findAll();
		if(projections.size() == 0) {
			return null;
		}
		return projections;
	}

	@Override
	public Projection addProjection(Projection projection) {
		// treba da se odradi provera sale i vremena!!
		// i pazi dole u toj funkciji getProjectionByName sam isto zakomentarisao sve, vraca null

		
		//if(getProjectionByName(projection.getMovieOrPerformance().getName()) == null) {
		//	repository.save(projection);
		//	return projection;
	//	}
		
		for(TheaterOrCinema cinema : cinemaRepository.findAll()) {
			if(cinema.getName().equals(projection.getTheaterOrCinema().getName())) {
				cinema.addProjection(projection);
				//cinemaRepository.save(cinema);
			}
		}
		
		repository.save(projection);
		return projection;
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
/*
	@Override
	public Boolean termCheck(Projection projection) {
		for(Projection p: getProjections()) {
			if(projection.getMovieOrPerformance().getName() == p.getMovieOrPerformance().getName()
					&& projection.getHall() == p.getHall()) {
				if(projection.getDate() == p.getDate()) {
					return false;
				}//else if(){
					
				//}
			}
		}
		return true;
	}
	*/

	public Projection changeProjection(Projection projection, Projection newProjection) {
		
		if (!projection.getName().equals(newProjection.getName())){
			Projection proj = repository.findByName(newProjection.getName());
			if (proj != null){
				return null;	//vec postoji
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
	
	
	
}
