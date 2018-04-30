package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.repository.ProjectionRepository;

@Service
public class ProjectionServiceImpl implements ProjectionServiceInterface{
	@Autowired
	ProjectionRepository repository;
	
	@Override
	public Collection<Projection> getProjections() {
		Collection<Projection> projections = repository.getProjections();
		return projections;
	}

	@Override
	public Projection addProjection(Projection projection) {
		repository.addProjection(projection);
		return projection;
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

}
