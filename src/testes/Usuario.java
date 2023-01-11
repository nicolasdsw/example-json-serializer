package testes;

import java.io.Serializable;

public class Usuario implements Serializable, JsonSerializable {

	private static final long serialVersionUID = -8454332108872213685L;
	private long id;
	private String nome;
	private Papel papel;
	private String email;
	private Integer anoNascimento;

	public Usuario() {
	}

	public Usuario(String nome, String email, int anoNascimento) {
		this.nome = nome;
		this.email = email;
		this.anoNascimento = anoNascimento;
	}

	@Override
	public String toString() {
		return JsonUtil.toJsonString(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAnoNascimento() {
		return anoNascimento;
	}

	public void setAnoNascimento(int anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}
}
