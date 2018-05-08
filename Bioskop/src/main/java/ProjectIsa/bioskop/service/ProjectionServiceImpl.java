package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.repository.ProjectionsDBRepository;

@Service
public class ProjectionServiceImpl implements ProjectionServiceInterface {
	@Autowired
	ProjectionsDBRepository repository;
	
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
		//Projection proj = repository.getProjectionByName(name);
		//return proj;
		return null;
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
	
	
	
}
