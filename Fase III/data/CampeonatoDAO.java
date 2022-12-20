package data;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import business.subCatálogos.Campeonato;

public class CampeonatoDAO implements Map<String, Campeonato> {

    private static CampeonatoDAO singleton = null;

    public static CampeonatoDAO getInstance() {
        
        if (CampeonatoDAO.singleton == null) {
            
            CampeonatoDAO.singleton = new CampeonatoDAO();
        }

        return CampeonatoDAO.singleton;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<Entry<String, Campeonato>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Campeonato get(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<String> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Campeonato put(String arg0, Campeonato arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Campeonato> m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Campeonato remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<Campeonato> values() {
        // TODO Auto-generated method stub
        return null;
    }


    
}
