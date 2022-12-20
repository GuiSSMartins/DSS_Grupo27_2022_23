package business.SubUtilizadores;

public abstract class Utilizador {
	private String email;
	private String password;
	private String nome;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Utilizador() {

	}

	public Utilizador(String email, String password, String nome){
		this.email = email;
		this.password = password;
		this.nome = nome;
	}



	public int hashCode() {
		int lHashCode = 0;
		if ( this.email != null ) {
			lHashCode += this.email.hashCode();
		}
		if ( this.password != null ) {
			lHashCode += this.password.hashCode();
		}
		if ( this.nome != null ) {
			lHashCode += this.nome.hashCode();
		}
		if ( lHashCode == 0 ) {
			lHashCode = super.hashCode();
		}
		return lHashCode;
	}

	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Utilizador) {
			Utilizador lUtilizadorObject = (Utilizador) aObject;
			boolean lEquals = true;
			lEquals &= ((this.email == lUtilizadorObject.email)
				|| (this.email != null && this.email.equals(lUtilizadorObject.email)));
			lEquals &= ((this.password == lUtilizadorObject.password)
				|| (this.password != null && this.password.equals(lUtilizadorObject.password)));
			lEquals &= ((this.nome == lUtilizadorObject.nome)
				|| (this.nome != null && this.nome.equals(lUtilizadorObject.nome)));
			return lEquals;
		}
		return false;
	}
}
