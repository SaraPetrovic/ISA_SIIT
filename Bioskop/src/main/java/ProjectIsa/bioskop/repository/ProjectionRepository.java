package ProjectIsa.bioskop.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import ProjectIsa.bioskop.domain.Projection;

@Repository
public class ProjectionRepository implements ProjectionInterface{

	private Collection<Projection> projections = new ArrayList<Projection>();
	
	@Override
	public Collection<Projection> getProjections() {
		//Projection p =  new Projection("sa", "dfsf", "dsf", "dss", 125);
		//projections.add(p);
		return projections;
	}

	@Override
	public Projection addProjection(Projection projection) {
		projections.add(projection);
		return projection;
	}

	@Override
	public void deleteProjection(Projection projection) {
		projections.remove(projection);		
	}

	@Override
	public Projection getProjection(Long id) {
		for (Projection p: projections){
			if (p.getId() == id){
				return p;
			}
		}
		return null;
	}
	

}
