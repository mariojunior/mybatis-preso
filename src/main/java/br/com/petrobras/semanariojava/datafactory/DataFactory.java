package br.com.petrobras.semanariojava.datafactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.petrobras.semanariojava.dao.ComplexBatchDao;
import br.com.petrobras.semanariojava.pojos.Module;
import br.com.petrobras.semanariojava.pojos.Routine;
import br.com.petrobras.semanariojava.pojos.System;
import br.com.petrobras.semanariojava.pojos.User;
import br.com.petrobras.semanariojava.pojos.UserRoutine;
import br.com.petrobras.semanariojava.pojos.UserSystem;

/**
 * ATENÇÃO: ESSA CLASSE É USADA APENAS E SOMENTE APENAS PARA POPULAR
 * DADOS NA TABELA PARA DEMONSTRAÇÃO.
 * 
 * A IDÉIA É FAZER COM Q O SPRING EXECUTE O MÉTODO build() 
 * PARA CRIAR OS REGISTROS APENAS UMA VEZ.
 * 
 * APÓS TER SIDO EXECUTADO, VC PRECISA COMENTAR AS ANNOTATIONS DO SPRING
 * PARA IGNORAR ESSA CLASSE.
 * 
 * @author mariojunior
 *
 */
//@Component
public class DataFactory {
	static final Logger log = LoggerFactory.getLogger(DataFactory.class);

//	@Autowired
	private ComplexBatchDao dao;
	
	
//	@PostConstruct
	public void build() throws Exception {
		int usersResult 		= 0;
		int systemsResult 		= 0;
		int modulesResult 		= 0;
		int routinesResult 		= 0;
		int usersRoutinesResult	= 0;
		int usersSystemsResult 	= 0;
		
		
		log.debug("Criando os usuários em batch insert...");
		List<User> users = buildUsers(10);
		usersResult = dao.fillTablesBatch("insertUser", users);
		log.debug(usersResult + " usuários criados com sucesso!");
		
		log.debug("Criando sistemas em batch insert...");
		List<System> systems = buildSystems(3);
		systemsResult = dao.fillTablesBatch("insertSystem", systems);
		log.debug(systemsResult + " sistemas criados com sucesso!");
		
		log.debug("Criando modulos em batch insert...");
		List<Module> modules = buildModules(4, systems);
		modulesResult = dao.fillTablesBatch("insertModule", modules);
		log.debug(modulesResult + " módulos criados com sucesso!");
		
		log.debug("Criando rotinas em batch insert...");
		List<Routine> routines = buildRoutines(4, modules);
		routinesResult = dao.fillTablesBatch("insertRoutine", routines);	
		log.debug(routinesResult + " rotinas criados com sucesso!");
		
		log.debug("Criando Usuarios x Rotinas em batch insert...");
		List<UserRoutine> usersRoutines = buildUsersRoutines(users, routines);
		usersRoutinesResult = dao.fillTablesBatch("insertUsersRoutines", usersRoutines);	
		log.debug(usersRoutinesResult + " Usuarios x Rotinas criados com sucesso!");
		
		log.debug("Criando Usuarios x Sistemas em batch insert...");
		List<UserSystem> usersSystems = buildUsersSystems(users, systems);
		usersSystemsResult = dao.fillTablesBatch("insertUsersSystems", usersSystems);	
		log.debug(usersSystemsResult + " Usuarios x Sistemas criados com sucesso!");
		
		
		
		log.debug("##### Banco de Dados populado com sucesso! Não se esqueça de retirar essa classe depois.");
		
		
	}
	
	
	//criando usuários
	private List<User> buildUsers(int count) {
		List<User> list = new ArrayList<User>();
				
		for (int i = 0; i < count; i++) {
			User u = new User();
			u.setName("Mario " + i);
			u.setEmail("mario.junior." + i + "@teste.com");
			
			list.add(u);
		}
		
		return list;
	}
	
	
	//criando sistemas
	private List<System> buildSystems(int count) {
		List<System> list = new ArrayList<System>();
		
		for (int i = 0; i < count; i++) {
			System s = new System();
			s.setName("Sistema " + i);
			
			list.add(s);
		}
		
		return list;
	}

	//criando modulos
	private List<Module> buildModules(int count, List<System> systems) {
		List<Module> list = new ArrayList<Module>();
		
		for (System system : systems) { //cria-se módulos para cada sistema
			for (int i = 0; i < count; i++) {
				Module m = new Module();
				m.setName("Modulo " + i + "." + system.getId());
				m.setSystem(system);
				list.add(m);
			}			
		}
		
		return list;
	}
	
	
	//criando rotinas
	private List<Routine> buildRoutines(int count, List<Module> modules) {
		List<Routine> list = new ArrayList<Routine>();
		
		for (Module module : modules) { //cria-se Rotinas para cada modulo
			for (int i = 0; i < count; i++) {
				Routine r = new Routine();
				r.setName("Modulo " + i + "." + module.getId());
				r.setModule(module);
				list.add(r);
			}			
		}
		
		return list;
	}
	
	
	
	
	
	
	//ligando usuarios a sistemas
	private List<UserSystem> buildUsersSystems(List<User> users, List<System> systems) {
		List<UserSystem> result = new ArrayList<UserSystem>();
		
		for (User user : users) {
			for (System system : systems) {
				UserSystem us = new UserSystem();
				us.setUser(user);
				us.setSystem(system);
				
				result.add(us);
			}
		}
		
		return result;
	}
	
	
	//ligando usuarios a rotinas
	private List<UserRoutine> buildUsersRoutines(List<User> users, List<Routine> routines) {
		List<UserRoutine> result = new ArrayList<UserRoutine>();
		
		for (User user : users) {
			for (Routine routine: routines) {
				UserRoutine ur = new UserRoutine();
				ur.setUser(user);
				ur.setRoutine(routine);
				
				result.add(ur);
			}
		}
		
		return result;
	}
	
	
	
}
