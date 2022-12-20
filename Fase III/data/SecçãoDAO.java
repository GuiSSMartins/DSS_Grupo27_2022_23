package data;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import business.subCatálogos.Secção;

public class SecçãoDAO implements Map<String,Secção>{
    private static SecçãoDAO singleton = null;

    public static SecçãoDAO getInstance() {
        
        if (SecçãoDAO.singleton == null) {
            
            SecçãoDAO.singleton = new SecçãoDAO();
        }

        return SecçãoDAO.singleton;
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
    public Set<Entry<String, Secção>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Secção get(Object key) {
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
    public Secção put(String arg0, Secção arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Secção> m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Secção remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<Secção> values() {
        // TODO Auto-generated method stub
        return null;
    }


}
