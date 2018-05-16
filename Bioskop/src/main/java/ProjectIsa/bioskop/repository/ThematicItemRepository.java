package ProjectIsa.bioskop.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import ProjectIsa.bioskop.domain.ThematicItem;

public interface ThematicItemRepository extends JpaRepository<ThematicItem, Long>{
	//ThematicItem save(ThematicItem item);
	//List<ThematicItem> findAll();
	//void delete(ThematicItem item);
	//ThematicItem findById(Long id);


	
}