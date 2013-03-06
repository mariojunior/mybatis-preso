package br.com.petrobras.semanariojava.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.petrobras.semanariojava.pojos.User;

/**
 * 
 * @author mariojunior
 *
 */
public interface IUserDao {

	@Transactional(readOnly=true)
	User get(Serializable id);

	@Transactional(readOnly=true)
	List<User> list();
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	User insert(User u);
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	Integer update(User u);
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	Integer delete(User u);
	
	@Transactional(readOnly=true)
	User getUserAndAllInformation(Serializable id);
}
