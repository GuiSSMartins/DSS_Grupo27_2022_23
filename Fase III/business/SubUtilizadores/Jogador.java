package business.SubUtilizadores;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AppConfigurationEntry;

public class Jogador extends Utilizador{
	private int versaoJogo; // Versão do Jogo -> 1 : Base; 2 : Premium; 3: NENHUM (bot)
	private List<Carreira> carreiras; // null se for bot CUIDADO
	private int pontuacaoGlobal;

	public Jogador() {
		super();
		this.carreiras = new ArrayList<>();
		this.pontuacaoGlobal = 0;
	}

	public Jogador(int versaoJogo) {
		super();
		this.versaoJogo = versaoJogo;
		this.carreiras = new ArrayList<>();
	}

	public Jogador(String email, String password, String nome, int versaoJogo) {
		super(email, password, nome);
		this.versaoJogo = versaoJogo;
		this.carreiras = new ArrayList<>();
	}

	public void setVersaoJogo(int versaoJogo) {
		this.versaoJogo = versaoJogo;
	}

	public int getVersaoJogo() {
		return this.versaoJogo;
	}

	/*Caso o jogador já possua pontuação para o campeonato passado, apenas atualiza caso a nova pontuação seja maior
	  Caso não tenha, adiciona à carreira*/
	public void registaPontuacao(String nomeCampeonato, int pontuacao){
		// Carreira novos_resultados
	}
	
		/*String email = super.getEmail();
		if(carreira.containsKey(nomeCampeonato.concat(email))){
			Carreira c = carreiraDAO.get(nomeCampeonato.concat(email));
			if(c.getPontuacao() < pontuacao) {
				carreiraDAO.updatePontuacao(nomeCampeonato.concat(email), pontuacao);
			}
		}
		else{
			Carreira c = new Carreira(nomeCampeonato, pontuacao, email);
			carreiraDAO.put(nomeCampeonato.concat(email),c);
		}
	}*/

	
}