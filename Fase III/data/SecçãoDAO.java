package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.*;

import business.subCatálogos.Secção;

public class SecçãoDAO implements Map<Integer,Secção>{
    private static SecçãoDAO singleton = null;

    public static SecçãoDAO getInstance() {
        
        if (SecçãoDAO.singleton == null) {
            
            SecçãoDAO.singleton = new SecçãoDAO();
        }

        return SecçãoDAO.singleton;
    }

    //seccao(__id__, _nomecircuito_, tipo, posicao, gdu) 

    public SecçãoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS seccoes (" +
                    "Id int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "NomeCircuito varchar(100) NOT NULL FOREIGN KEY REFERENCES circuitos(Nome)," +
                    "Tipo varchar(10) NOT NULL," +
                    "Posicao int NOT NULL," +
                    "GDU int NOT NULL)";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE seccoes");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public List<Secção> getSecções(String nomeCircuito){
        List<Secção> res = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM seccoes where NomeCircuito='"+nomeCircuito+"'")) { 
            while (rs.next()) {
                Integer idt = rs.getInt("Id");
                Secção t = this.get(idt);
                res.add(t);
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                stm.executeQuery("SELECT Id FROM seccoes WHERE Id='"+key.toString()+"'")) {
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
        Secção t = (Secção) value;
		return this.containsKey(t.getID());
    }

    @Override
    public Set<Entry<Integer, Secção>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<Integer,Seccao>> entrySet() not implemented!");
    }

    @Override
    public Secção get(Object key) {
        Secção t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM seccoes WHERE Id='"+key+"'")) {
            
            new Secção(rs.getInt("Id"), rs.getString("Tipo"), rs.getInt("GDU"), rs.getInt("Posicao"), rs.getString("NomeCircuito"));
        
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
    public Set<Integer> keySet() {
        throw new NullPointerException("Not implemented!");
    }

    @Override
    public Secção remove(Object key) {
        Secção t = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            
            stm.executeUpdate("DELETE FROM seccoes WHERE Id='"+key+"'");
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
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM seccoes")) {
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
    public Collection<Secção> values() {
        Collection<Secção> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Id FROM seccoes")) { 
            while (rs.next()) {
                Integer idt = rs.getInt("Id");
                Secção t = this.get(idt);
                res.add(t);
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Secção put(Integer key, Secção value) {
        Secção t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            int Id = value.getID();
            String Tipo = value.getTipo();
            int GDU = value.getGDU();
            int posicao = value.getPosicao();
            String nomeCircuito = value.getNomeCircuito();
            stm.executeUpdate(
                    "INSERT INTO utilizadores " +
                            "VALUES ('"+ Id + "', '"+
                            nomeCircuito +"', '"+
                            Tipo +"', '"+
                            GDU +"', '"+
                            posicao + ") '");

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Secção> m) {
        for(Secção t : m.values()) {
            this.put(t.getID(), t);
        }
        
    }


}
