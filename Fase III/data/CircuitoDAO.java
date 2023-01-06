package data;

import java.sql.Connection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.sql.*;

import business.subCatálogos.Circuito;

public class CircuitoDAO implements Map<String,Circuito>{

    private static CircuitoDAO singleton = null;

    public static CircuitoDAO getInstance() {
        
        if (CircuitoDAO.singleton == null) {
            
            CircuitoDAO.singleton = new CircuitoDAO();
        }

        return CircuitoDAO.singleton;
    }

    private CircuitoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();) {   

            stm.executeUpdate("CREATE TABLE IF NOT EXISTS circuitos ("
                    + "nome VARCHAR(255) NOT NULL,"
                    + "nvoltas INT NOT NULL,"
                    + "comprimento DOUBLE NOT NULL,"
                    + "ncurvas INT NOT NULL,"
                    + "nretas INT NOT NULL,"
                    + "nchicanes INT NOT NULL,"
                    + "PRIMARY KEY (nome))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();) {
            stm.executeUpdate("TRUNCATE circuitos");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT nome FROM circuitos WHERE nome='"+key.toString()+"'")) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Circuito t = (Circuito) value;
        return this.containsKey(t.getNome());
    }

    @Override
    public Set<Entry<String, Circuito>> entrySet() {
        Set<Entry<String, Circuito>> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT nome FROM circuitos")) { 
            while (rs.next()) {
                String idt = rs.getString("nome");
                Circuito t = this.get(idt);
                res.add(new AbstractMap.SimpleEntry<>(idt, t));
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Circuito get(Object key) {
        Circuito t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM circuitos WHERE nome='"+key.toString()+"'")) { 
            if (rs.next()) {
                t = new Circuito(rs.getString("nome"), rs.getInt("nvoltas"), rs.getDouble("comprimento"), rs.getInt("ncurvas"), rs.getInt("nchicanes"), rs.getInt("nretas"));
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Set<String> keySet() {
        Set<String> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT nome FROM circuitos")) { 
            while (rs.next()) {
                String idt = rs.getString("nome");
                res.add(idt);
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Circuito put(String arg0, Circuito arg1) {
        Circuito t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            String nome = arg1.getNome();
            int nVoltas = arg1.getNVoltas();    
            double comprimento = arg1.getComprimento();       
            int nCurvas = arg1.getNCurvas();      
            int nRetas = arg1.getNRetas();
            int nChicanes = arg1.getNChicanes();  

            stm.executeUpdate(
                    "INSERT INTO circuitos " +
                            "VALUES ('"+ nome + "', "+
                            nVoltas +", "+
                            comprimento +", "+
                            nCurvas +", "+
                            nRetas +", "+
                            nChicanes + ")");

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
        
    }

    @Override
    public void putAll(Map<? extends String, ? extends Circuito> m) {
         for (Circuito p : m.values()) {
            this.put(p.getNome(), p);
        }
    }

    @Override
    public Circuito remove(Object key) {
        Circuito p = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {
            stm.executeUpdate("DELETE FROM circuitos WHERE nome='" + key.toString() + "'");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public int size() {
       int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM circuitos")) {
            while (rs.next()) {
                i++;
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    @Override
    public Collection<Circuito> values() {
        Collection<Circuito> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT nome FROM circuitos")) { 
            while (rs.next()) {
                String idt = rs.getString("nome");
                Circuito t = this.get(idt);
                res.add(t);
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    public void povoar() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            Statement stm = conn.createStatement()) {

            // 1º Circuito


        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        } 
    }

}
