package data;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.sql.*;

import business.subCatálogos.C1Hibrido;
import business.subCatálogos.C2Hibrido;
import business.subCatálogos.GTHibrido;
import business.subCatálogos.Hibrido;

public class CarroHibridoDAO implements Map<Integer, Hibrido>{

    private static CarroHibridoDAO singleton = null;

    public static CarroHibridoDAO getInstance() {
        
        if (CarroHibridoDAO.singleton == null) {
            
            CarroHibridoDAO.singleton = new CarroHibridoDAO();
        }

        return CarroHibridoDAO.singleton;
    }

    private CarroHibridoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
            Statement stm = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS carroshibridos("
                    + "id INT NOT NULL AUTO_INCREMENT,"
                    + "marca VARCHAR(255) NOT NULL,"
                    + "modelo VARCHAR(255) NOT NULL,"
                    + "cilindrada INT NOT NULL,"
                    + "potencia INT NOT NULL,"
                    + "fiabilidade INT NOT NULL,"
                    + "pac DOUBLE NOT NULL,"
                    + "taxadeterioracao DOUBLE NULL"
                    + "potenciaEletrica INT NOT NULL,"
                    + "PRIMARY KEY (id))";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
            Statement stm = conn.createStatement();
            stm.executeUpdate("TRUNCATE carroshibridos");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        } 
    }

    @Override
    public boolean containsKey(Object key) {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM carroshibridos WHERE id='" + key.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public boolean containsValue(Object value) {
        if (value.getClass() != Hibrido.class) {
            return false;
        }
        return containsKey(((Hibrido) value).getID());
    }

    @Override
    public Set<Entry<Integer, Hibrido>> entrySet() {
        Set<Entry<Integer, Hibrido>> set = new HashSet<>();
        Hibrido h = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM carroshibridos";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String modelo = rs.getString("Modelo");
                if (modelo.equals("C1")) {  
                    h = new C1Hibrido(rs.getInt("id"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"),rs.getDouble("pac"), rs.getInt("potenciaEletrica"));
                }
                else if (modelo.equals("C2")) {  
                    h = new C2Hibrido(rs.getInt("id"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"),rs.getDouble("pac"), rs.getInt("potenciaEletrica"));
                }
                else
                {
                    h = new GTHibrido(rs.getInt("Id"),rs.getString("Marca"), rs.getString("Modelo"), rs.getInt("Cilindrada"), rs.getInt("Potencia"), rs.getInt("Fiabilidade"), rs.getDouble("Pac"), rs.getDouble("taxaDeteriorizacao"),  rs.getInt("potenciaEletrica"));
                }
                set.add(new AbstractMap.SimpleEntry<>(h.getID(), h));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return set;
    }

    @Override
    public Hibrido get(Object key) {
        Hibrido h = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM carroshibridos WHERE id='" + key.toString() + "'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                String modelo = rs.getString("Modelo");
                if (modelo.equals("C1")) {  
                    h = new C1Hibrido(rs.getInt("id"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"),rs.getDouble("pac"), rs.getInt("potenciaEletrica"));
                }
                else if (modelo.equals("C2")) {  
                    h = new C2Hibrido(rs.getInt("id"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"),rs.getDouble("pac"), rs.getInt("potenciaEletrica"));
                }
                else
                {
                    h = new GTHibrido(rs.getInt("Id"),rs.getString("Marca"), rs.getString("Modelo"), rs.getInt("Cilindrada"), rs.getInt("Potencia"), rs.getInt("Fiabilidade"), rs.getDouble("Pac"), rs.getDouble("taxaDeteriorizacao"),  rs.getInt("potenciaEletrica"));
                }
                
                return h;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> set = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
            Statement stm = conn.createStatement();
            String sql = "SELECT id FROM carroshibridos";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                set.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return set;
    }

    @Override
    public Hibrido put(Integer arg0, Hibrido arg1) {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
            Statement stm = conn.createStatement();
            String modelo = arg1.getModelo();
            String sql = null;
            if (modelo.equals("C1")) {  
                sql = "INSERT INTO carroshibridos (id, marca, modelo, cilindrada, potencia, fiabilidade, pac, potenciaEletrica) VALUES ('" + arg1.getID() + "', '" + arg1.getMarca() + "', '" + arg1.getModelo() + "', '" + arg1.getCilindrada() + "', '" + arg1.getPotenciaCombustao() + "', '" + arg1.getFiabilidade() + "', '" + arg1.getPAC() + "', '" + arg1.getPotenciaEletrico() + "')";
            }
            else if (modelo.equals("C2")) {  
                sql = "INSERT INTO carroshibridos (id, marca, modelo, cilindrada, potencia, fiabilidade, pac, potenciaEletrica) VALUES ('" + arg1.getID() + "', '" + arg1.getMarca() + "', '" + arg1.getModelo() + "', '" + arg1.getCilindrada() + "', '" + arg1.getPotenciaCombustao() + "', '" + arg1.getFiabilidade() + "', '" + arg1.getPAC() + "', '" + arg1.getPotenciaEletrico() + "')";
            }
            else
            {
                GTHibrido arg = (GTHibrido) arg1;
                sql = "INSERT INTO carroshibridos (id, marca, modelo, cilindrada, potencia, fiabilidade, pac, taxaDeteriorizacao, potenciaEletrica) VALUES ('" + arg.getID() + "', '" + arg.getMarca() + "', '" + arg.getModelo() + "', '" + arg.getCilindrada() + "', '" + arg.getPotenciaCombustao() + "', '" + arg.getFiabilidade() + "', '" + arg.getPAC() + "', '" + arg.getTaxaDeterioracao() + "', '" + arg.getPotenciaEletrico() + "')";
            }
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return arg1;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Hibrido> m) {
        for (Hibrido h : m.values()) {
            put(h.getID(), h);
        }   
    }

    @Override
    public Hibrido remove(Object key) {
        Hibrido h = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
            Statement stm = conn.createStatement();
            String sql = "DELETE FROM carroshibridos WHERE id='" + key.toString() + "'";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return h;
    }

    @Override
    public int size() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
            Statement stm = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM carroshibridos";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return 0;
    }

    @Override
    public Collection<Hibrido> values() {
        Collection<Hibrido> col = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
            Statement stm = conn.createStatement();
            String sql = "SELECT * FROM carroshibridos";
            ResultSet rs = stm.executeQuery(sql);
            Hibrido h = null;
            while (rs.next()) {
                String modelo = rs.getString("Modelo");
                if (modelo.equals("C1")) {  
                    h = new C1Hibrido(rs.getInt("id"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"),rs.getDouble("pac"), rs.getInt("potenciaEletrica"));
                }
                else if (modelo.equals("C2")) {  
                    h = new C2Hibrido(rs.getInt("id"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("cilindrada"), rs.getInt("potencia"), rs.getInt("fiabilidade"),rs.getDouble("pac"), rs.getInt("potenciaEletrica"));
                }
                else
                {
                    h = new GTHibrido(rs.getInt("Id"),rs.getString("Marca"), rs.getString("Modelo"), rs.getInt("Cilindrada"), rs.getInt("Potencia"), rs.getInt("Fiabilidade"), rs.getDouble("Pac"), rs.getDouble("taxaDeteriorizacao"),  rs.getInt("potenciaEletrica"));
                }
                col.add(h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return null;
    }

 
    
}
