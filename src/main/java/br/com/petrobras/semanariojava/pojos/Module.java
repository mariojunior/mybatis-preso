package br.com.petrobras.semanariojava.pojos;

import java.util.List;

/**
 * 
 * @author mariojunior
 *
 */
public class Module {
	
	private Long id;
	private String name;
	
	private System system;
	private List<Routine> routines;
	
	
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
	public System getSystem() {
		return system;
	}
	public void setSystem(System system) {
		this.system = system;
	}
	public List<Routine> getRoutines() {
		return routines;
	}
	public void setRoutines(List<Routine> routines) {
		this.routines = routines;
	}
	
}
