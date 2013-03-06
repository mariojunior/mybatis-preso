package tests;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.petrobras.semanariojava.dao.IUserDao;
import br.com.petrobras.semanariojava.pojos.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:app-config-test.xml"})
public class UserCrudTest {

	@Autowired
	private IUserDao dao;
	
	private static Long LAST_ID_INSERTED;
	
	
	private String getRandomUUIDPart() {
		return UUID.randomUUID().toString().split("-")[0];
	}
	
	@Test
	public void testInsert() {
		User u = new User();
		u.setName("Mario Junior " + getRandomUUIDPart());
		u.setEmail("mariojunior@" + getRandomUUIDPart() + ".com");
		
		User result = dao.insert(u);
		
		Assert.assertTrue(result.getId() > 0);
		LAST_ID_INSERTED = result.getId();
	}
	
	@Test
	public void testGet() {
		User result = dao.get(LAST_ID_INSERTED);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testUpdate() {
		User user = dao.get(LAST_ID_INSERTED);
		user.setName(user.getName() + " updated.");
		
		Assert.assertTrue(dao.update(user) == 1);
	}
	
	@Test
	public void testList() {
		List<User> result = dao.list();
		Assert.assertTrue(result.size() > 0);
		
	}
	
	@Test
	public void testDelete() {
		User user = dao.get(LAST_ID_INSERTED);
		Assert.assertTrue(dao.delete(user) == 1);
	}
	
	@Test
	public void testGetUserAndAllInformation() {
		User result = dao.getUserAndAllInformation(15077);
		Assert.assertNotNull(result);
	}
	
}
