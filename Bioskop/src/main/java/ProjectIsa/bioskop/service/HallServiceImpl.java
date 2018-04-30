package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import ProjectIsa.bioskop.domain.Hall;
import ProjectIsa.bioskop.repository.HallRepositoryImpl;

public class HallServiceImpl implements HallServiceInterface{
	@Autowired
	HallRepositoryImpl repository;
	
	@Override
	public Collection<Hall> getHalls() {
		return repository.getHalls();
	}

	@Override
	public Hall addHall(Hall hall) {
		repository.addHall(hall);
		return hall;
	}

	@Override
	public void deleteHall(Hall hall) {
		repository.deleteHall(hall);
	}

	@Override
	public Hall getHall(String id) {
		return repository.getHall(id);
	}
}
