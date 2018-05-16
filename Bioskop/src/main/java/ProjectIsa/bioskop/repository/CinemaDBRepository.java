package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import ProjectIsa.bioskop.domain.Adresa;
import ProjectIsa.bioskop.domain.Projection;
import ProjectIsa.bioskop.domain.TheaterOrCinema;

public interface CinemaDBRepository extends Repository<TheaterOrCinema, Long>{
	TheaterOrCinema save(TheaterOrCinema cinema);
	List<TheaterOrCinema> findAll();
	void delete(TheaterOrCinema cinema);
	TheaterOrCinema findByName(String name);
	TheaterOrCinema findById(Long id);
	Adresa save(Adresa adresa);
	}
