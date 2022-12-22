
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
