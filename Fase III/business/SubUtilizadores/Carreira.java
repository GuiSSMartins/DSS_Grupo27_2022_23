package business.SubUtilizadores;

public class Carreira {
    private String id; // Id = NomeCampeonato + Email
    private String nomeCampeonato;
    private int pontuacao;
    private String email;

    public String getID() {
        return this.id;
    }

    public String getNomeCampeonato() {
        return this.nomeCampeonato;
    }

    public void setNomeCampeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Carreira(){
        
    }

    public Carreira(String nomeCampeonato, int pontuacao, String email){
        this.nomeCampeonato = nomeCampeonato;
        this.pontuacao = pontuacao;
        this.email = email;
        this.id = this.nomeCampeonato.concat(email);
    }

    public Carreira(String nomeCampeonato, int pontuacao){
        this.nomeCampeonato = nomeCampeonato;
        this.pontuacao = pontuacao;
    }
    

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
            Carreira c = (Carreira) aObject;
        
            return (c.getID().equals(this.id) &&
                    c.getEmail().equals(this.email) && 
                    c.getNomeCampeonato().equals(this.nomeCampeonato) && 
                    c.getPontuacao() == this.pontuacao);
        }
        return false;
    }
}