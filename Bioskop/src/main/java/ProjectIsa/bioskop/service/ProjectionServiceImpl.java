package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.repository.ProjectionRepositoryImpl;

@Service
public class ProjectionServiceImpl implements ProjectionServiceInterface {
	@Autowired
	ProjectionRepositoryImpl repository;
	
	@Override
	public Collection<Projection> getProjections() {
		Collection<Projection> projections = repository.getProjections();
		if(projections.size() == 0) {
			return null;
		}
		return projections;
	}

	@Override
	public Projection addProjection(Projection projection) {
		if(getProjectionByName(projection.getMovieOrPerformance().getName()) != null) {
			repository.addProjection(projection);
			return projection;
		}
		
		return null;
	}

	@Override
	public void deleteProjection(Projection projection) {
		repository.deleteProjection(projection);
	}

	@Override
	public Projection getProjection(Long id) {
		Projection proj = repository.getProjection(id);
		return proj;
	}

	@Override
	public Projection getProjectionByName(String name) {
		Projection proj = repository.getProjectionByName(name);
		return proj;
	}

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
	
	
	
}
