package br.com.petrobras.semanariojava.pojos;

/**
 * 
 * @author mariojunior
 *
 */
public class Routine {
	
	private Long id;
	private String name;
	
	private Module module;

	
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

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
}
