package data;

import java.sql.Connection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.sql.*;

import business.subCatálogos.Piloto;

public class PilotoDAO implements Map<String, Piloto> {

    private static PilotoDAO singleton = null;

    public static PilotoDAO getInstance() {

        if (PilotoDAO.singleton == null) {

            PilotoDAO.singleton = new PilotoDAO();
        }

        return PilotoDAO.singleton;
    }

    public PilotoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();) {

            stm.executeUpdate("CREATE TABLE IF NOT EXISTS pilotos ("
                    + "nome VARCHAR(255) NOT NULL,"
                    + "cts DOUBLE(5,2) NOT NULL,"
                    + "sva DOUBLE(5,2) NOT NULL,"
                    + "PRIMARY KEY (nome))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE pilotos");
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
                ResultSet rs = stm.executeQuery("SELECT nome FROM pilotos WHERE nome='" + key.toString() + "'")) {
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
        if (value.getClass() != Piloto.class) {
            return false;
        }
        return this.containsKey(((Piloto) value).getNome());
    }

    @Override
    public Set<Entry<String, Piloto>> entrySet() {
        Set<Entry<String, Piloto>> set = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM pilotos")) {
            while (rs.next()) {
                Piloto p = new Piloto(rs.getString("nome"), rs.getDouble("cts"), rs.getDouble("sga"));
                set.add(new AbstractMap.SimpleEntry<>(p.getNome(), p));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return set;
    }

    @Override
    public Piloto get(Object key) {
        Piloto p = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM pilotos WHERE nome='" + key.toString() + "'")) {
            if (rs.next()) {
                p = new Piloto(rs.getString("nome"), rs.getDouble("cts"), rs.getDouble("sga"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT nome FROM pilotos")) {
            while (rs.next()) {
                set.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return set;
    }

    @Override
    public Piloto put(String arg0, Piloto arg1) {
        Piloto p = this.get(arg0);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO pilotos (nome, cts, sga) VALUES ('" + arg1.getNome() + "', " + arg1.getCTS()
                    + ", " + arg1.getSVA() + ")");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Piloto> m) {
        for (Piloto p : m.values()) {
            this.put(p.getNome(), p);
        }
    }

    @Override
    public Piloto remove(Object key) {
        Piloto p = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {
            stm.executeUpdate("DELETE FROM pilotos WHERE nome='" + key.toString() + "'");
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
                ResultSet rs = stm.executeQuery("SELECT * FROM pilotos")) {
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
    public Collection<Piloto> values() {
        Collection<Piloto> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM pilotos")) {
            while (rs.next()) {
                Piloto p = new Piloto(rs.getString("nome"), rs.getDouble("cts"), rs.getDouble("sva"));
                col.add(p);
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return col;
    }

    public void povoar() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {

            if (this.size() == 0) {

                // 1º Carro
                stm.executeUpdate(
                        "INSERT INTO pilotos " +
                                "VALUES ('Battery Voltar', " + // nome
                                "0.5, " + // cts
                                "0.55)"); // sva

                String sql = "INSERT INTO pilotos (nome,cts,sva)" +
                        " Values ('Charlie Leclerco',0.51,0.5)," +
                        "('Maximo Vestrapo',0.55,0.5)," +
                        "('Goatifi',0.45,0.9)";
                stm.executeUpdate(sql);

            }

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

}
