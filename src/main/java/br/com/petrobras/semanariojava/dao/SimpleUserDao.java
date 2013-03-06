package br.com.petrobras.semanariojava.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.petrobras.semanariojava.pojos.User;

/**
 * 
 * @author mariojunior
 *
 */
@Repository
public class SimpleUserDao implements IUserDao {

	static final Logger log = LoggerFactory.getLogger(SimpleUserDao.class);
	
	@Autowired
	private SqlSession sqlSession;

	
	public User get(Serializable id) {
		User result = sqlSession.selectOne("getUser", id);
		return result;
	}
	
	public List<User> list() {
		List<User> result = null;
		try {
			result = sqlSession.selectList("listUser");
		} catch (Exception e) {
			log.error("Error on \"list()\" method.", e);
		}
		
		return result;
	}
	
	public User insert(User u) {
		try {
			int count = sqlSession.insert("insertUser", u);
			log.debug(count + " register(s) are inserted.");
		} catch (Exception e) {
			log.error("Error on \"insert()\" method.", e);
		}
		
		return u;
	}

	public Integer update(User u) {
		Integer result = null;
		try {
			result = sqlSession.update("updateUser", u);
		} catch (Exception e) {
			log.error("Error on \"update()\" method.", e);
		}
		
		return result;
	}

	public Integer delete(User u) {
		Integer result = null;
		try {
			result = sqlSession.delete("deleteUser", u);
		} catch (Exception e) {
			log.error("Error on \"delete()\" method.", e);
		}
		
		return result;
	}

	
	public User getUserAndAllInformation(Serializable id) {
		User result = null;
		try {
			result = (User) sqlSession.selectOne("selectUserWithSystemsAndRoutines", id);
		} catch (Exception e) {
			log.error("Error on \"getUserAndAllInformation()\" method.", e);
		}
		
		return result;
	}

	
}
