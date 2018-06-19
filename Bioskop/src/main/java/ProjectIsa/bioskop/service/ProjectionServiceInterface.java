package ProjectIsa.bioskop.service;

import java.util.Collection;
import java.util.List;

import ProjectIsa.bioskop.domain.Projection;

public interface ProjectionServiceInterface {
	Collection<Projection> getProjections();
	Projection addProjection(Projection projection);
	void deleteProjection(Projection projection);
	Projection getProjection(Long id);
	Projection getProjectionByName(String name);
	
	Projection changeProjection(Projection proj, Projection newProj);
	//Boolean termCheck(Projection projection);
	List<Projection> getCinemasProjections(Long id);
}
