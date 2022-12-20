package business.SubUtilizadores;

import data.CarreiraDAO;

public class Jogador extends Utilizador{
	private String versaoJogo;
	private CarreiraDAO carreiraDAO;

	public Jogador() {
		super();
		this.carreiraDAO = CarreiraDAO.getInstance();
	}

	public Jogador(String versaoJogo) {
		super();
		this.versaoJogo = versaoJogo;
		this.carreiraDAO = CarreiraDAO.getInstance();
	}

	public Jogador(String email, String password, String nome, String versaoJogo) {
		super(email, password, nome);
		this.versaoJogo = versaoJogo;
		this.carreiraDAO = CarreiraDAO.getInstance();
	}

	public void setVersaoJogo(String versaoJogo) {
		this.versaoJogo = versaoJogo;
	}

	public String getVersaoJogo() {
		return this.versaoJogo;
	}
}