package ProjectIsa.bioskop.service;

import java.util.Collection;

import ProjectIsa.bioskop.domain.Hall;

public interface HallServiceInterface {
	Collection<Hall> getHalls();
	String addHall(Hall hall);
	void deleteHall(Hall hall);
	Hall getHallById(Long id);
	Hall getHallByName(String name);
}
