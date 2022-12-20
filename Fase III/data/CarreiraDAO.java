package data;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import business.SubUtilizadores.Carreira;

public class CarreiraDAO implements Map<String, Carreira>{

    private static CarreiraDAO singleton = null;

    public static CarreiraDAO getInstance() {
        
        if (CarreiraDAO.singleton == null) {
            
            CarreiraDAO.singleton = new CarreiraDAO();
        }

        return CarreiraDAO.singleton;
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
    public Set<Entry<String, Carreira>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Carreira get(Object key) {
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
    public Carreira put(String arg0, Carreira arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Carreira> m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Carreira remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<Carreira> values() {
        // TODO Auto-generated method stub
        return null;
    }


    
}
