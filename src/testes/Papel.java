package testes;

import java.io.Serializable;

public class Papel implements Serializable, JsonSerializable {

	private static final long serialVersionUID = 78584297937740303L;
	private int id;
	private String nome;

	public Papel() {
		super();
	}

	public Papel(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return JsonUtil.toJsonString(this);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
