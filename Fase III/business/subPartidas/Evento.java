package business.subPartidas;

import java.util.ArrayList;
import java.util.List;

public class Evento {
	private int idSeccao;
	private int volta;
	private int acontecimento;

	/**
	 * acidente - 0
	 * ultrapassagem - 1
	 * foste ultrapassado - 2
	 * avaria - 3
	 */

	private List<String> idjogadoresEnvolvidos;

	public Evento(int volta, int idSeccao, int acontecimento) {
		this.volta = volta;
		this.idSeccao = idSeccao;
		this.acontecimento = acontecimento;
		this.idjogadoresEnvolvidos = new ArrayList<>();
	}

	public Evento(int volta, int idSeccao, int acontecimento, List<String> idjogadoresEnvolvidos) {
		this.volta = volta;
		this.idSeccao = idSeccao;
		this.acontecimento = acontecimento;
		this.idjogadoresEnvolvidos = List.copyOf(idjogadoresEnvolvidos);
	}

	public Evento(Evento e) {
		this.volta = e.getVolta();
		this.idSeccao = e.getIDSeccao();
		this.acontecimento = e.getAcontecimento();
		this.idjogadoresEnvolvidos = e.getIdJogadoresEnvolvidos();

	}

	public void addJogador(String jogador) {
		this.idjogadoresEnvolvidos.add(jogador);
	}

	public int getIDSeccao() {
		return this.idSeccao;
	}

	public int getVolta() {
		return this.volta;
	}

	public int getAcontecimento() {
		return this.acontecimento;
	}

	public List<String> getIdJogadoresEnvolvidos() {
		List<String> res = new ArrayList<>();
		for (String s : this.idjogadoresEnvolvidos) {
			res.add(String.valueOf(s));
		}
		return res;
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n||| ");
		if (this.acontecimento == 0) {
			sb.append("Acidente");
		} else if (this.acontecimento == 1) {
			sb.append("Ultrapassagem");
		} else if (this.acontecimento == 2) {
			sb.append("Foste ultrapassado");
		} else if (this.acontecimento == 3) {
			sb.append("Avariou");
		}
		sb.append(this.idjogadoresEnvolvidos);
		sb.append("\n");
		return sb.toString();
	}

	public Evento clone() {
		return new Evento(this);
	}
}