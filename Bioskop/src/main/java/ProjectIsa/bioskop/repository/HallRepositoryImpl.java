package ProjectIsa.bioskop.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import ProjectIsa.bioskop.domain.Hall;

@Repository
public class HallRepositoryImpl implements HallRepositoryInterface {
	
	private Collection<Hall> halls = new ArrayList<Hall>();
	
	@Override
	public Collection<Hall> getHalls() {
		return halls;
	}

	@Override
	public Hall addHall(Hall hall) {
		halls.add(hall);
		return hall;
	}

	@Override
	public void deleteHall(Hall hall) {
		halls.remove(hall);
		
	}

	@Override
	public Hall getHall(String id) {
		for(Hall h:halls) {
			if(h.getName().equals(id)) {
				return h;
			}
		}
		return null;
	}
}
