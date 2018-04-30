package ProjectIsa.bioskop.repository;

import java.util.Collection;

import ProjectIsa.bioskop.domain.Projection;

public interface ProjectionInterface {
	Collection<Projection> getProjections();
	Projection addProjection(Projection projection);
	void deleteProjection(Projection projection);
	Projection getProjection(Long id);
}
