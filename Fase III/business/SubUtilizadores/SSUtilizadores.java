package business.SubUtilizadores;

import data.UtilizadorDAO;

public class SSUtilizadores implements ISubUtilizadores {
    private UtilizadorDAO utilizadores;

    public SSUtilizadores() {
        this.utilizadores = UtilizadorDAO.getInstance();
    }

    /*
     * Verificar que utilizador existe e que a passe está correta
     * true -> existe e está correta
     * false -> n existe ou n está correta
     */
    public boolean validaAcesso(String email, String password) {
        boolean res = false;

        if (utilizadores.containsKey(email)) {
            Jogador u = utilizadores.get(email);
            if (u.getPassword().equals(password))
                res = true;
        }

        return res;
    }

    /*
     * Verificar que o e-mail é válido -> não há outro registado com este e-mail
     * true -> é válido
     * false -> não é válido
     */
    public boolean validaEmail(String email) {
        boolean res = true;

        if (utilizadores.containsKey(email)) {
            res = false;
        }

        return res;
    }

    /*
     * Regista utilizador
     * true -> registo bem sucedido
     * false -> e-mail n é válido
     * 
     * jogador 1 -> jogador
     * jogador 0 = adm
     * 
     */
    public boolean registarUtilizador(String email, String nome, String password, int versaoJogo, int jogador) {
        boolean res = false;
        Utilizador u = null;

        if (validaEmail(email)) { // e-mail é válido
            if (jogador == 1) {
                u = new Jogador(email, password, nome, versaoJogo);
            } else {
                u = new Administrador(email, password, nome);
            }

            utilizadores.put(email, u);
            res = true;
        }

        return res;
    }

    public boolean validaCodigo(String email, String codigo) {
        return true;
    }

    /*
     * Regista a nova password
     * true -> tudo certo
     * false -> email não existe
     */
    public boolean updatePassword(String email, String password) {
        boolean res = false;

        if (utilizadores.containsKey(email)) {
            res = true;
            utilizadores.updatePassword(email, password);
        }

        return res;
    }

    /*
     * Retorna versão de jogo para o utilizador com o email passado como argumento
     * Retorna null porque o utilizador não existe ou porque se trata de um
     * administrador
     */
    public String verificaVersao(String email) {
        String versao = null;

        if (utilizadores.containsKey(email)) {
            Utilizador u = utilizadores.get(email);
            if (u instanceof Jogador) {
                Jogador j = (Jogador) u;
                int v = j.getVersaoJogo();
                if (v == 1)
                    versao = "Premium";
                else
                    versao = "Base";
            }
        }

        return versao;
    }

    /*
     * Regista pontuação do jogador para aquele campeonato, caso o jogador já tenha
     * jogado naquele campeonato, atualiza a pontuação
     */
    public boolean registarPontuacao(String idJogador, String nomeCampeonato, int pontuacao) {
        boolean res = false;

        if (utilizadores.containsKey(idJogador)) {
            Utilizador u = utilizadores.get(idJogador);
            if (u instanceof Jogador) {
                Jogador j = (Jogador) u;
                j.registaPontuacao(nomeCampeonato, pontuacao);
                res = true;
            }
        }

        return res;
    }

    /*
     * Altera para versão oposta ao que o jogador possui
     * true -> alterado
     * false -> jogador n existe ou id é de adm
     */
    public boolean alterarVersaoJogador(String idJogador) {
        boolean res = false;

        if (utilizadores.containsKey(idJogador)) {
            Utilizador u = utilizadores.get(idJogador);
            if (u instanceof Jogador) {
                Jogador j = (Jogador) u;
                int versaoAtual = j.getVersaoJogo();
                int versaoAtt = 0;
                if (versaoAtual == 0)
                    versaoAtt = 1;
                utilizadores.updateVersao(idJogador, versaoAtt);
                res = true;
            }

        }

        return res;
    }

}