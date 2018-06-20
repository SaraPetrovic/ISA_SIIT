package ProjectIsa.bioskop.service;

import java.util.Collection;
import java.util.List;

import ProjectIsa.bioskop.domain.Hall;
import ProjectIsa.bioskop.domain.Projection;

public interface ProjectionServiceInterface {
	Collection<Projection> getProjections();
	String addProjection(Projection projection);
	void deleteProjection(Projection projection);
	Projection getProjection(Long id);
	Projection getProjectionByName(String name);
	
	String changeProjection(Projection proj, Hall hall);

	List<Projection> getCinemasProjections(Long id);

}
