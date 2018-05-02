package ProjectIsa.bioskop.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.Repository;
import ProjectIsa.bioskop.domain.ThematicItem;

public interface ThematicItemRepository extends Repository<ThematicItem, Long>{
	ThematicItem save(ThematicItem item);
	List<ThematicItem> findAll();
	void delete(ThematicItem item);
	ThematicItem findById(Long id);
	
}