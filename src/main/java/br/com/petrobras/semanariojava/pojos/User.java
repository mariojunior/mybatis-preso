package br.com.petrobras.semanariojava.pojos;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author mariojunior
 *
 */
public class User implements Serializable {

	private Long id;
	private String name;
	private String email;
	private List<System> systems;
	private List<Routine> allowedRoutines;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<System> getSystems() {
		return systems;
	}
	public void setSystems(List<System> systems) {
		this.systems = systems;
	}
	public List<Routine> getAllowedRoutines() {
		return allowedRoutines;
	}
	public void setAllowedRoutines(List<Routine> allowedRoutines) {
		this.allowedRoutines = allowedRoutines;
	}

}
