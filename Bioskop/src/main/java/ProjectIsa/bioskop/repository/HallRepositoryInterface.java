package ProjectIsa.bioskop.repository;

import java.util.Collection;

import ProjectIsa.bioskop.domain.Hall;

public interface HallRepositoryInterface {
	Collection<Hall> getHalls();
	Hall addHall(Hall hall);
	void deleteHall(Hall hall);
	Hall getHall(String id);
}
