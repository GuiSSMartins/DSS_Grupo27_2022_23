package business.subPartidas;

import java.util.ArrayList;
import java.util.List;

public class Evento {
	private int idSeccao;
	private int acontecimento;

	/**
	 * acidente - 0
	 * ultrapassagem - 1
	 * foste ultrapassado - 2
	 * despiste - 3
	 * avaria - 4
	 */

	private List<String> idjogadoresEnvolvidos;


	public Evento() {
		this.idjogadoresEnvolvidos = new ArrayList<>();
	}

	public void addJogador(String jogador) {
		this.idjogadoresEnvolvidos.add(jogador);
	}

	public int getIDSeccao(){
		return this.idSeccao;
	}


	public boolean equals(Object aObject) {
		if (this == aObject) {
			return true;
		} else if (aObject instanceof Evento) {
			Evento lEventoObject = (Evento) aObject;
			boolean lEquals = true;
			lEquals &= this.idSeccao == lEventoObject.idSeccao;
			lEquals &= this.acontecimento == lEventoObject.acontecimento;
			lEquals &= this.idjogadoresEnvolvidos == lEventoObject.idjogadoresEnvolvidos;
			return lEquals;
		}
		return false;
	}
}