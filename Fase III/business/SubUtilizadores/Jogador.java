package business.SubUtilizadores;

import data.CarreiraDAO;

public class Jogador extends Utilizador {
	private int versaoJogo; // 0 -> base; 1 -> premium;
	private CarreiraDAO carreiraDAO;
	private boolean bot;

	public Jogador() {
		super();
		this.carreiraDAO = CarreiraDAO.getInstance();
	}

	public Jogador(int versaoJogo, boolean bot) {
		super();
		this.versaoJogo = versaoJogo;
		this.carreiraDAO = CarreiraDAO.getInstance();
		this.bot = bot;
	}

	public Jogador(String email, String password, String nome, int versaoJogo, boolean bot) {
		super(email, password, nome);
		this.versaoJogo = versaoJogo;
		this.carreiraDAO = CarreiraDAO.getInstance();
		this.bot = bot;
	}

	public void setVersaoJogo(int versaoJogo) {
		this.versaoJogo = versaoJogo;
	}

	public int getVersaoJogo() {
		return this.versaoJogo;
	}

	/*
	 * Caso o jogador já possua pontuação para o campeonato passado, apenas atualiza
	 * caso a nova pontuação seja maior
	 * Caso não tenha, adiciona à carreira
	 */
	public void registaPontuacao(String nomeCampeonato, int pontuacao) {

		String email = super.getEmail();

		// Só atualiza carreira se não for bot
		if (carreiraDAO.containsKey(nomeCampeonato.concat(email))) {
			Carreira c = carreiraDAO.get(nomeCampeonato.concat(email));
			if (c.getPontuacao() < pontuacao) {
				carreiraDAO.updatePontuacao(nomeCampeonato.concat(email), pontuacao);
			}
		} else {
			Carreira c = new Carreira(nomeCampeonato, pontuacao, email);
			carreiraDAO.put(nomeCampeonato.concat(email), c);
		}
	}

}