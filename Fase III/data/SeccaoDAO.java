package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.*;

import business.subCatálogos.Seccao;

public class SeccaoDAO implements Map<Integer,Seccao>{
    private static SeccaoDAO singleton = null;

    public static SeccaoDAO getInstance() {
        
        if (SeccaoDAO.singleton == null) {
            
            SeccaoDAO.singleton = new SeccaoDAO();
        }

        return SeccaoDAO.singleton;
    }

    //seccao(__id__, _nomecircuito_, tipo, posicao, gdu) 

    public SeccaoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("CREATE TABLE IF NOT EXISTS seccoes (id INT NOT NULL AUTO_INCREMENT, tipo VARCHAR(10) NOT NULL, posicao INT NOT NULL, comprimento DOUBLE NOT NULL, GDU INT NOT NULL, nomeCircuito VARCHAR(255) NOT NULL, PRIMARY KEY (Id), FOREIGN KEY(nomeCircuito) REFERENCES circuitos(nome));");
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

    public List<Seccao> getSeccoes(String nomeCircuito){
        List<Seccao> res = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM seccoes where NomeCircuito='"+nomeCircuito+"'")) { 
            while (rs.next()) {
                Integer idt = rs.getInt("Id");
                Seccao t = this.get(idt);
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
        Seccao t = (Seccao) value;
		return this.containsKey(t.getID());
    }

    @Override
    public Set<Entry<Integer, Seccao>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<Integer,Seccao>> entrySet() not implemented!");
    }

    @Override
    public Seccao get(Object key) {
        Seccao t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM seccoes WHERE Id='"+key.toString()+"'")) {
            
            new Seccao(rs.getInt("Id"), rs.getString("Tipo"), rs.getInt("GDU"), rs.getInt("Posicao"), rs.getString("NomeCircuito"), rs.getDouble("Comprimento"));
        
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
    public Seccao remove(Object key) {
        Seccao t = this.get(key);
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
    public Collection<Seccao> values() {
        Collection<Seccao> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Id FROM seccoes")) { 
            while (rs.next()) {
                Integer idt = rs.getInt("Id");
                Seccao t = this.get(idt);
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
    public Seccao put(Integer key, Seccao value) {
        Seccao t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            int Id = value.getID();
            String Tipo = value.getTipo();
            int GDU = value.getGDU();
            int posicao = value.getPosicao();
            String nomeCircuito = value.getNomeCircuito();
            double comprimento = value.getComprimento();
            stm.executeUpdate(
                    "INSERT INTO utilizadores " +
                            "VALUES ("+ Id + ", '"+
                            nomeCircuito +"', '"+
                            Tipo +"', "+
                            posicao +", "+
                            comprimento +", "+
                            GDU + ")");

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Seccao> m) {
        for(Seccao t : m.values()) {
            this.put(t.getID(), t);
        }
        
    }

    public void povoar() {

    }

}
