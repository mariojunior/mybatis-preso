package br.com.petrobras.semanariojava.pojos;

import java.util.List;

/**
 * 
 * @author mariojunior
 *
 */
public class System{

	private Long id;
	private String name;
	private List<Module> modules;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
}
