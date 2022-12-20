package data;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import business.subCatálogos.Carro;

public class CarroDAO implements Map<Integer,Carro>{

    private static CarroDAO singleton = null;

    public static CarroDAO getInstance() {
        
        if (CarroDAO.singleton == null) {
            
            CarroDAO.singleton = new CarroDAO();
        }

        return CarroDAO.singleton;
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
    public Set<Entry<Integer, Carro>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Carro get(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<Integer> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Carro put(Integer arg0, Carro arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Carro> m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Carro remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<Carro> values() {
        // TODO Auto-generated method stub
        return null;
    }


    
}
