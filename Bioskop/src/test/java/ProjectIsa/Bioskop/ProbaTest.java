package ProjectIsa.Bioskop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import ProjectIsa.bioskop.domain.ThematicItem;
import ProjectIsa.bioskop.service.ThematicItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProbaTest {

	
	@Autowired
	ThematicItemService service;
	/*
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void reservationTest2(){
		ThematicItem item1 = service.getItem(1L);
		ThematicItem item2 = service.getItem(1L);
		item1.setQuantity(item1.getQuantity() - 1);
		item2.setQuantity(item2.getQuantity() - 1);
		//item2.setDescription("abcd");
		
		service.addNewItem(item1);
		service.addNewItem(item2);
	
	}
	*/


}
