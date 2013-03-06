package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.petrobras.semanariojava.dao.ComplexBatchDao;
import br.com.petrobras.semanariojava.pojos.User;

/**
 * 
 * @author mariojunior
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:app-config-test.xml"})
public class ComplexBatchStatementTest {

	@Autowired
	private ComplexBatchDao dao;
	

	private String getRandomUUIDPart() {
		return UUID.randomUUID().toString().split("-")[0];
	}
	
	@Test
	public void testInsertUsersBatch() throws Exception {
		Integer result = 0;
		
		List<User> users = new ArrayList<User>();
		
		for (int i = 0; i < 10000; i++) {
			User u = new User();
			u.setName("Mario " + getRandomUUIDPart());
			u.setEmail("mario.junior@"+ getRandomUUIDPart() + ".com");
			
			users.add(u);
		}
		
		result = dao.insertBatch(users);
		Assert.assertTrue(result > 0);
		
	}
	
	
}
