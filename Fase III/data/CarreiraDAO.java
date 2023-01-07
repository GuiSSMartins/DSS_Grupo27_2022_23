package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import business.SubUtilizadores.Carreira;

public class CarreiraDAO implements Map<String, Carreira> {

    private static CarreiraDAO singleton = null;

    public static CarreiraDAO getInstance() {

        if (CarreiraDAO.singleton == null) {

            CarreiraDAO.singleton = new CarreiraDAO();
        }

        return CarreiraDAO.singleton;
    }

    public CarreiraDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS carreiras (" +
                    "Id varchar(245) NOT NULL PRIMARY KEY," + // Id = NomeCampeonato + Email
                    "Pontuacao int NOT NULL," +
                    "NomeCampeonato varchar(510) NOT NULL," +
                    "Email varchar(100) NOT NULL," +
                    "FOREIGN KEY(Email) REFERENCES utilizadores(Email))";
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
            stm.executeUpdate("TRUNCATE carreiras");
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
                ResultSet rs = stm.executeQuery("SELECT Id FROM carreiras WHERE Id='" + key.toString() + "'")) {
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
        Carreira t = (Carreira) value;
        return this.containsKey(t.getID());
    }

    @Override
    public Set<Entry<String, Carreira>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Carreira>> entrySet() not implemented!");
    }

    @Override
    public Carreira get(Object key) {
        Carreira t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM carreiras WHERE Id ='" + key.toString() + "'")) {
            if (rs.next()) { // A chave existe na tabela
                t = new Carreira(rs.getString("nomeCampeonato"), rs.getInt("Pontuacao"),
                        rs.getString("Email"));
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
    public Carreira put(String arg0, Carreira arg1) {
        Carreira t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {

            String id = arg1.getID();
            String NomeCampeonato = arg1.getNomeCampeonato();
            String Email = arg1.getEmail();
            int pontuacao = arg1.getPontuacao();

            stm.executeUpdate(
                    "INSERT INTO carreiras " +
                            "VALUES ('" + id + "', " +
                            pontuacao + ", '" +
                            NomeCampeonato + "', '" +
                            Email + "') ON DUPLICATE KEY UPDATE Id = '" + id + "'");

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Carreira> m) {
        for (Carreira t : m.values()) {
            this.put(t.getID(), t);
        }

    }

    @Override
    public Carreira remove(Object key) {
        Carreira t = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {

            stm.executeUpdate("DELETE FROM carreiras WHERE Id='" + key + "'");
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
                ResultSet rs = stm.executeQuery("SELECT count(*) FROM carreiras")) {
            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    @Override
    public Collection<Carreira> values() {
        Collection<Carreira> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT Id FROM carreiras")) {
            while (rs.next()) {
                String idt = rs.getString("Id");
                Carreira t = this.get(idt);
                res.add(t);
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    public List<Carreira> getCarreiras(String email) {
        List<Carreira> res = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM carreiras where Email='" + email + "'")) {
            while (rs.next()) {
                String idt = rs.getString("Id");
                Carreira t = this.get(idt);
                res.add(t);
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    public void updatePontuacao(String id, int pontuacao) {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm
                        .executeQuery("UPDATE carreiras SET Pontuacao='" + pontuacao + "' WHERE Id='" + id + "'")) {
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public void povoar() {

        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {
            
                    /* 
            if (this.size() == 0) {
                String sql = "INSERT INTO carreiras (Id,Pontuacao,NomeCampeonato,Email) " +
                        "Values ('camp1p1',0,'camp1circ1','user1')";
                stm.executeUpdate(sql);
            }*/

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

    }

}
