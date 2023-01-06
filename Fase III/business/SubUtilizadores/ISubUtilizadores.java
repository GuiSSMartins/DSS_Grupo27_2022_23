package business.SubUtilizadores;

public interface ISubUtilizadores {
    
    public boolean validaAcesso(String email, String password);
    public boolean validaEmail(String email);
    public boolean registarUtilizador(String email, String nome, String password, int versaoJogo, int jogador);
    public boolean validaCodigo(String email, String codigo);
    public boolean updatePassword(String email, String password);
    public String verificaVersao(String email);
    public boolean registarPontuacao(String idJogador, String nomeCampeonato, int pontuacao);
    public boolean alterarVersaoJogador(String idJogador);
}
