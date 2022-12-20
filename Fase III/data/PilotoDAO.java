package data;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import business.subCatálogos.Piloto;

public class PilotoDAO implements Map<String, Piloto>{

    private static PilotoDAO singleton = null;

    public static PilotoDAO getInstance() {
        
        if (PilotoDAO.singleton == null) {
            
            PilotoDAO.singleton = new PilotoDAO();
        }

        return PilotoDAO.singleton;
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
    public Set<Entry<String, Piloto>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Piloto get(Object key) {
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
    public Piloto put(String arg0, Piloto arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Piloto> m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Piloto remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<Piloto> values() {
        // TODO Auto-generated method stub
        return null;
    }

 
    
}
