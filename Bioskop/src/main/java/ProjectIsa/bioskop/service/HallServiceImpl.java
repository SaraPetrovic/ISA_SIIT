package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import ProjectIsa.bioskop.domain.Hall;
import ProjectIsa.bioskop.domain.TheaterOrCinema;
import ProjectIsa.bioskop.repository.HallRepositoryImpl;

public class HallServiceImpl implements HallServiceInterface{
	@Autowired
	HallRepositoryImpl repository;
	
	@Override
	public Collection<Hall> getHalls() {
		Collection<Hall> halls = repository.getHalls();
		if(halls.size() == 0) {
			return null;
		}
		return halls;
	}

	@Override
	public Hall addHall(Hall hall) {
		Collection<Hall> halls = repository.getHalls();
		for (Hall h : halls ){
			if (h.getId().equals(hall.getId())){
				return null;
			}
		}
		return repository.addHall(hall);
		
		/*
		if(getHallById(hall.getId()) == null) {
			return repository.addHall(hall);
		}
		return null;*/
	}

	@Override
	public void deleteHall(Hall hall) {
		repository.deleteHall(hall);
	}

	@Override
	public Hall getHallById(String id) {
		return repository.getHall(id);
	}
}
