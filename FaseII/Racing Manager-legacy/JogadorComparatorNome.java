
/**
 * Write a description of class JogadorComparatorNome here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Comparator;
import java.io.Serializable;

public class JogadorComparatorNome implements Comparator<Jogador>, Serializable
{
    
    public int compare(Jogador j1, Jogador j2)
    {
        int res = j1.compareTo(j2);
        return res!=0? res : j1.getNome().compareTo(j2.getNome());
    }
    
}
