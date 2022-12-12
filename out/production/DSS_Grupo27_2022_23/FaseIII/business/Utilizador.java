public class Utilizador {
	public Jogador _é_um;
	public Username _identificado_por;

	public int hashCode() {
		int lHashCode = 0;
		if ( this._é_um != null ) {
			lHashCode += this._é_um.hashCode();
		}
		if ( this._identificado_por != null ) {
			lHashCode += this._identificado_por.hashCode();
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
			lEquals &= ((this._é_um == lUtilizadorObject._é_um)
				|| (this._é_um != null && this._é_um.equals(lUtilizadorObject._é_um)));
			lEquals &= ((this._identificado_por == lUtilizadorObject._identificado_por)
				|| (this._identificado_por != null && this._identificado_por.equals(lUtilizadorObject._identificado_por)));
			return lEquals;
		}
		return false;
	}
	public abstract class Utilizador {
		private String _email;
		private String _password;
		private String _nome;

		public int hashCode() {
			int lHashCode = 0;
			if ( this._email != null ) {
				lHashCode += this._email.hashCode();
			}
			if ( this._password != null ) {
				lHashCode += this._password.hashCode();
			}
			if ( this._nome != null ) {
				lHashCode += this._nome.hashCode();
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
				lEquals &= ((this._email == lUtilizadorObject._email)
					|| (this._email != null && this._email.equals(lUtilizadorObject._email)));
				lEquals &= ((this._password == lUtilizadorObject._password)
					|| (this._password != null && this._password.equals(lUtilizadorObject._password)));
				lEquals &= ((this._nome == lUtilizadorObject._nome)
					|| (this._nome != null && this._nome.equals(lUtilizadorObject._nome)));
				return lEquals;
			}
			return false;
		}
	}
	public class Jogador extends Utilizador.Utilizador {
		private String _versaoJogo;
		private Utilizador.Carreira _carreira;

		public void alteraVersao() {
			throw new UnsupportedOperationException();
		}

		public int hashCode() {
			int lHashCode = 0;
			if ( this._versaoJogo != null ) {
				lHashCode += this._versaoJogo.hashCode();
			}
			if ( this._carreira != null ) {
				lHashCode += this._carreira.hashCode();
			}
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof Jogador) {
				Jogador lJogadorObject = (Jogador) aObject;
				boolean lEquals = true;
				lEquals &= ((this._versaoJogo == lJogadorObject._versaoJogo)
					|| (this._versaoJogo != null && this._versaoJogo.equals(lJogadorObject._versaoJogo)));
				lEquals &= ((this._carreira == lJogadorObject._carreira)
					|| (this._carreira != null && this._carreira.equals(lJogadorObject._carreira)));
				return lEquals;
			}
			return false;
		}
	}
	public class Administrador extends Utilizador.Utilizador {

		public int hashCode() {
			int lHashCode = 0;
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof Administrador) {
				Administrador lAdministradorObject = (Administrador) aObject;
				boolean lEquals = true;
				return lEquals;
			}
			return false;
		}
	}
	public class Carreira {
		private Map<String, int> _resultados;

		public int hashCode() {
			int lHashCode = 0;
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof Carreira) {
				Carreira lCarreiraObject = (Carreira) aObject;
				boolean lEquals = true;
				lEquals &= this._resultados == lCarreiraObject._resultados;
				return lEquals;
			}
			return false;
		}
	}
	public class SSUtilizadores {
		public Utilizador.Utilizador _utilizador;

		public int hashCode() {
			int lHashCode = 0;
			if ( this._utilizador != null ) {
				lHashCode += this._utilizador.hashCode();
			}
			if ( lHashCode == 0 ) {
				lHashCode = super.hashCode();
			}
			return lHashCode;
		}

		public boolean equals(Object aObject) {
			if (this == aObject) {
				return true;
			} else if (aObject instanceof SSUtilizadores) {
				SSUtilizadores lSSUtilizadoresObject = (SSUtilizadores) aObject;
				boolean lEquals = true;
				lEquals &= ((this._utilizador == lSSUtilizadoresObject._utilizador)
					|| (this._utilizador != null && this._utilizador.equals(lSSUtilizadoresObject._utilizador)));
				return lEquals;
			}
			return false;
		}
	}
	public interface IGestUtilizadores {

		public boolean validaAcesso(String aEmail, String aPassword);

		public boolean validaEmail(String aEmail);

		public boolean registarUtilizador(String aEmail, String aNome, String aPassword);

		public boolean validaCodigo(String aEmail, String aCodRecuperacao);

		public boolean updatePassword(String aEmail, String aPassword);

		public boolean verificaVersao(String aIdJogador);

		public void registarPontuacao(String aIdJogador, String aNomeCampeonato, int aPontuacao);

		public void alteraVersaoJogador(String aIdJogador);

		public int hashCode();

		public boolean equals(Object aObject);
	}
}