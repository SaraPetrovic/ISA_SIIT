package ProjectIsa.bioskop.service;

import java.util.Collection;

import ProjectIsa.bioskop.domain.Projection;

public interface ProjectionServiceInterface {
	Collection<Projection> getProjections();
	String addProjection(Projection projection);
	void deleteProjection(Projection projection);
	Projection getProjection(Long id);
	Projection getProjectionByName(String name);
	
	Projection changeProjection(Projection proj, Projection newProj);
}
