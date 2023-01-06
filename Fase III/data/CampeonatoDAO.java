package data;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.*;

import business.subCatálogos.Campeonato;

public class CampeonatoDAO implements Map<String, Campeonato> {

    private static CampeonatoDAO singleton = null;

    public static CampeonatoDAO getInstance() {
        
        if (CampeonatoDAO.singleton == null) {
            
            CampeonatoDAO.singleton = new CampeonatoDAO();
        }

        return CampeonatoDAO.singleton;
    }

 private CampeonatoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();) {
            stm.executeUpdate("CREATE TABLE IF NOT EXISTS campeonatos ("
                    + "Id varchar(510) NOT NULL PRIMARY KEY," //Nome campeonato + nome circuito
                    + "Nome VARCHAR(255) NOT NULL,"
                    + "nomeCircuito VARCHAR(255) NOT NULL FOREIGN KEY REFERENCES circuitos(Nome))"
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE campeonatos");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                stm.executeQuery("SELECT Id FROM campeonatos WHERE Id='"+key+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        Campeonato t = (Campeonato) value;
		return this.containsKey(t.getNome());
    }

    @Override
    public Set<Entry<String, Campeonato>> entrySet() {
      throw new NullPointerException("public Set<Map.Entry<String,Campeonato>> entrySet() not implemented!");
    }

    @Override
    public Campeonato get(Object key) {
        Campeonato t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM campeonatos WHERE Nome='"+key.toString()+"'")) {
                t = new Campeonato(key.toString());
            while (rs.next()) {
                String circuito = rs.getString("nomeCircuito");
                t.addCircuitos(circuito);
            }
        
        } catch (SQLException e) {
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
       throw new NullPointerException("Not implemented!");
    }

    @Override
    public Campeonato put(String arg0, Campeonato arg1) {
        Campeonato t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            String nome = arg1.getNome();
            List<String> circuitos = arg1.getCircuitos();

            for(String nomeCircuito : circuitos) {
                String id = nome.concat(nomeCircuito);

                stm.executeUpdate(
                        "INSERT INTO campeonatos " +
                                "VALUES ('"+ id + "', '"+
                                nome+"', '"+
                                nomeCircuito + ") '");
            }

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Campeonato> m) {
        for(Campeonato t : m.values()) {
            this.put(t.getNome(), t);
        }
    }

    @Override
    public Campeonato remove(Object key) {
        Campeonato t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            
            stm.executeUpdate("DELETE FROM campeonatos WHERE Nome='"+key.toString()+"'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(distinct Nome) FROM campeonatos")) {
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    @Override
    public Collection<Campeonato> values() {
        Map<String, Campeonato> campeonatos = new HashMap<>();
        Collection<Campeonato> res = new HashSet<>();

        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM campeonatos")) { 
            while (rs.next()) {
                String nome = rs.getString("Nome");
                if(!campeonatos.containsKey(nome)){
                    campeonatos.put(nome, get(nome));
                }
            }
            
            res = campeonatos.values();

        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }


    
}
