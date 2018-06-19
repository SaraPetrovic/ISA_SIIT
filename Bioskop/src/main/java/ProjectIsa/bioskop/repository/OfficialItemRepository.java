package ProjectIsa.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjectIsa.bioskop.domain.OfficialItem;

public interface OfficialItemRepository extends JpaRepository<OfficialItem, Long>{
	List<OfficialItem> findByNameContainingOrDescriptionContainingAllIgnoringCase(String name, String desc);
}
