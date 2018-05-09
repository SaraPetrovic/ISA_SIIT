package ProjectIsa.bioskop.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectIsa.bioskop.domain.Hall;
import ProjectIsa.bioskop.repository.HallDBRepository;
@Service
public class HallServiceImpl implements HallServiceInterface{
	@Autowired
	HallDBRepository repository;
	
	@Override
	public Collection<Hall> getHalls() {
		Collection<Hall> halls = repository.findAll();
		if(halls.size() == 0) {
			return null;
		}
		return halls;
	}

	@Override
	public Hall addHall(Hall hall) {
		
		//Collection<Hall> halls = repository.findAll();
		
	/*	for (Hall h : halls ){
			if (h.getName().equals(hall.getName())){
				return null;
			}
		}*/
		return repository.save(hall);
		
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
