package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Hall;
import ProjectIsa.bioskop.domain.TheaterOrCinema;
import ProjectIsa.bioskop.repository.CinemaDBRepository;
import ProjectIsa.bioskop.repository.HallDBRepository;
@Service
public class HallServiceImpl implements HallServiceInterface{
	@Autowired
	HallDBRepository repository;
	CinemaDBRepository cinemaRepository;
	
	@Override
	public Collection<Hall> getHalls() {
		Collection<Hall> halls = repository.findAll();
		if(halls.size() == 0) {
			return null;
		}
		return halls;
	}

	@Override
	public String addHall(Hall hall) {
		
		if(hall.getName() == "" || hall.getTheaterOrCinema() == null) {
			return "Please enter all required data!";
		}
		
		//ako u odredjenom bioskopu postoji hall sa ispit nazivom, return null
		for(Hall h : repository.findAll()) {
			if(hall.getTheaterOrCinema().getName().equals(h.getTheaterOrCinema().getName()) && hall.getName().equals(h.getName())) {
				return "Hall with the same name already exists!";
			}
		}
		
		repository.save(hall);
		return null;
		
	}

	@Override
	public void deleteHall(Hall hall) {
		repository.delete(hall);
	}

	@Override
	public Hall getHallByName(String name) {
		return repository.findByName(name);
	}
	@Override
	public Hall getHallById(Long id) {
		return repository.findById(id);
	}
}
