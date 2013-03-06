package br.com.petrobras.semanariojava.dao;

import java.util.List;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.com.petrobras.semanariojava.pojos.User;

/**
 * 
 * @author mariojunior
 *
 */
@Repository
public class ComplexBatchDao {
	static final Logger log = LoggerFactory.getLogger(ComplexBatchDao.class);
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	
	public Integer insertBatch(List<User> users) throws Exception
	{
		Integer result = 0;
		List<BatchResult> listResults = null;
		
		SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
		
		try {
			for (User user : users) {
				result += session.insert("insertUser", user);
			}
			
			listResults = session.flushStatements();
			log.debug("###### Insert rows affected: " + ((BatchResult) listResults.get(0)).getUpdateCounts().length);
			
		} catch (Exception e) {
			session.rollback();
			log.error("Error in \"insertBatch()\" method: ", e);
		} finally {
			session.close();
		}
		
		return result;
	}
	
	
	
	
//	 -------
	
	
	
	
	//Insert Batch usado para popular todas as tabelas para demonstração criado para esse fim.
	// é igual ao método acima, única diferença é que o nome da instrução é passado dinamicamente para reaproveitar o mesmo método.
	// lembrando que isso foi feito apenas para demonstração.
	public Integer fillTablesBatch(String insertNameInstruction, List list) throws Exception
	{
		Integer result = 0;
		List<BatchResult> listResults = null;
		
		SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
		
		try {
			for (Object o : list) {
				result += session.insert(insertNameInstruction, o);
			}
			
			listResults = session.flushStatements(); //flush já faz o commit automaticamente, nao precisa chamar session.commit() depois.
			
			//em caso de operações Batch, o resultado é guardado no objeto BatchResult.
			result = ((BatchResult) listResults.get(0)).getUpdateCounts().length;
			log.debug("###### Total \"insert\" rows affected: " + result);
			
		} catch (Exception e) {
			session.rollback();
			log.error("Error in \"fillTablesBatch()\" method: ", e);
		} finally {
			session.close();
		}
		
		return result;
	}
	
	

}
