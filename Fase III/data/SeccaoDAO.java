package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.*;

import business.subCatálogos.Seccao;

public class SeccaoDAO implements Map<Integer, Seccao> {
    private static SeccaoDAO singleton = null;

    public static SeccaoDAO getInstance() {

        if (SeccaoDAO.singleton == null) {

            SeccaoDAO.singleton = new SeccaoDAO();
        }

        return SeccaoDAO.singleton;
    }

    // seccao(__id__, _nomecircuito_, tipo, posicao, gdu)

    public SeccaoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {

            stm.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS seccoes (Id INT NOT NULL AUTO_INCREMENT, tipo VARCHAR(10) NOT NULL, posicao INT NOT NULL, comprimento DOUBLE(5,2) NOT NULL, GDU INT NOT NULL, nomeCircuito VARCHAR(255) NOT NULL, PRIMARY KEY (Id), FOREIGN KEY(nomeCircuito) REFERENCES circuitos(nome))");
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

    public List<Seccao> getSeccoes(String nomeCircuito) {
        List<Seccao> res = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM seccoes where NomeCircuito='" + nomeCircuito + "'")) {
            while (rs.next()) {
                Seccao t = new Seccao(rs.getInt("Id"), rs.getString("tipo"), rs.getInt("GDU"), rs.getInt("posicao"), rs.getString("nomeCircuito"), rs.getDouble("comprimento"));
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
                ResultSet rs = stm.executeQuery("SELECT Id FROM seccoes WHERE Id='" + key.toString() + "'")) {
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
                ResultSet rs = stm.executeQuery("SELECT * FROM seccoes WHERE nomeCircuito='" + key.toString() + "'")) {
 

            new Seccao(rs.getInt("Id"), rs.getString("Tipo"), rs.getInt("GDU"), rs.getInt("Posicao"),
                    rs.getString("NomeCircuito"), rs.getDouble("Comprimento"));

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

            stm.executeUpdate("DELETE FROM seccoes WHERE Id='" + key + "'");
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
                            "VALUES (" + Id + ", '" +
                            nomeCircuito + "', '" +
                            Tipo + "', " +
                            posicao + ", " +
                            comprimento + ", " +
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
        for (Seccao t : m.values()) {
            this.put(t.getID(), t);
        }

    }

    public void povoar() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {

            if (this.size() == 0) {

                String sql = "INSERT INTO seccoes (tipo,posicao,comprimento,GDU,nomeCircuito)" +
                        " Values ('chicane',0,5.5,3,'circ1')," +
                        "('chicane',1,5.5,3,'circ1')," +
                        "('chicane',3,5.5,3,'circ1')," +
                        "('chicane',9,5.5,3,'circ1')," +
                        "('reta',2,5.5,3,'circ1')," +
                        "('reta',5,5.5,3,'circ1')," +
                        "('reta',8,5.5,3,'circ1')," +
                        "('reta',11,5.5,3,'circ1')," +
                        "('reta',12,5.5,3,'circ1')," +
                        "('curva',4,5.5,3,'circ1')," +
                        "('curva',6,5.5,3,'circ1')," +
                        "('curva',7,5.5,3,'circ1')," +
                        "('curva',10,5.5,3,'circ1')," +
                        "('curva',13,5.5,3,'circ1')," +
                        "('reta',0,5.5,3,'circ2')," +
                        "('reta',1,5.5,3,'circ2')," +
                        "('reta',3,5.5,3,'circ2')," +
                        "('reta',9,5.5,3,'circ2')," +
                        "('chicane',2,5.5,3,'circ2')," +
                        "('chicane',5,5.5,3,'circ2')," +
                        "('chicane',8,5.5,3,'circ2')," +
                        "('chicane',11,5.5,3,'circ2')," +
                        "('chicane',12,5.5,3,'circ2')," +
                        "('curva',4,5.5,3,'circ2')," +
                        "('curva',6,5.5,3,'circ2')," +
                        "('curva',7,5.5,3,'circ2')," +
                        "('curva',10,5.5,3,'circ2')," +
                        "('curva',13,5.5,3,'circ2')," +
                        "('curva',0,5.5,3,'circ3')," +
                        "('curva',1,5.5,3,'circ3')," +
                        "('curva',3,5.5,3,'circ3')," +
                        "('curva',9,5.5,3,'circ3')," +
                        "('reta',2,5.5,3,'circ3')," +
                        "('reta',5,5.5,3,'circ3')," +
                        "('reta',8,5.5,3,'circ3')," +
                        "('reta',11,5.5,3,'circ3')," +
                        "('reta',12,5.5,3,'circ3')," +
                        "('chicane',4,5.5,3,'circ3')," +
                        "('chicane',6,5.5,3,'circ3')," +
                        "('chicane',7,5.5,3,'circ3')," +
                        "('chicane',10,5.5,3,'circ3')," +
                        "('chicane',13,5.5,3,'circ3')";
                stm.executeUpdate(sql);
            }

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

    }

}
