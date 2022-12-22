package data;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import business.SubUtilizadores.*;

public class UtilizadorDAO implements Map<String,Utilizador>{

    private static UtilizadorDAO singleton = null;

    public static UtilizadorDAO getInstance() {
        
        if (UtilizadorDAO.singleton == null) {
            
            UtilizadorDAO.singleton = new UtilizadorDAO();
        }

        return UtilizadorDAO.singleton;
    }

    public UtilizadorDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS utilizadores (" +
                    "Email varchar(100) NOT NULL PRIMARY KEY," +
                    "Password varchar(100) NOT NULL," +
                    "Nome varchar(100) NOT NULL)" +
                    "Jogador int NOT NULL" +
                    "VersaoJogo varchar(10)";
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
            stm.executeUpdate("TRUNCATE utilizadores");
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
                stm.executeQuery("SELECT Email FROM utilizadores WHERE Email='"+key.toString()+"'")) {
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
        Utilizador u = (Utilizador) value;
        return this.containsKey(u.getEmail());
    }

    @Override
    public Set<Entry<String, Utilizador>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Utilizador>> entrySet() not implemented!");
    }

    @Override
    public Utilizador get(Object key) {
        Utilizador t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM utilizadores WHERE Email='"+key+"'")) {
            if (rs.next()) {  // A chave existe na tabela
                int jogador = rs.getInt("Jogador");
                if (jogador == 1) {
                    t = new Jogador(rs.getString("Email"), rs.getString("Password"), rs.getString("Nome"), rs.getString("VersaoJogo"));
                }
                else {
                    t = new Administrador(rs.getString("Email"), rs.getString("Password"), rs.getString("Nome"));
                }
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
    public void putAll(Map<? extends String, ? extends Utilizador> utilizadores) {
        for(Utilizador t : utilizadores.values()) {
            this.put(t.getEmail(), t);
        }
    }

    @Override
    public Utilizador remove(Object key) {
        Utilizador t = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            
            stm.executeUpdate("DELETE FROM utilizadores WHERE Email='"+key+"'");
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
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM utilizadores")) {
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

    public Collection<Utilizador> values() {
        Collection<Utilizador> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Email FROM utilizadores")) { 
            while (rs.next()) {
                String idt = rs.getString("Email");
                Utilizador t = this.get(idt);
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
    public Utilizador put(String arg0, Utilizador arg1) {
        Utilizador t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            String email = arg1.getEmail();
            String nome = arg1.getNome();
            String password = arg1.getPassword();
            int jogador = 0;
            String versaoJogo = null; //arg1.getVersaoJogo();

            if (arg1 instanceof Jogador) {
                jogador = 1;
                Jogador j = (Jogador) arg1;
                versaoJogo = j.getVersaoJogo();
            }

            stm.executeUpdate(
                    "INSERT INTO utilizadores " +
                            "VALUES ('"+ email + "', '"+
                            password +"', '"+
                            nome +"', '"+
                            jogador +"', '"+
                            versaoJogo + ") '");

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }
}