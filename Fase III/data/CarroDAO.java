package data;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import business.subCatálogos.*;

public class CarroDAO implements Map<Integer, Carro> {

    private static CarroDAO singleton = null;

    public static CarroDAO getInstance() {

        if (CarroDAO.singleton == null) {

            CarroDAO.singleton = new CarroDAO();
        }

        return CarroDAO.singleton;
    }

    // carros(__id__, categoria, marca, modelo, cilindrada, potencia, pac,
    // fiabilidade)
    // public Carro(String marca, String modelo, int cilindrada, int potencia, int
    // fiabilidade, int id, double PAC)

    public CarroDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS carros (" +
                    "Id int NOT NULL PRIMARY KEY," +
                    "Categoria varchar(3) NOT NULL," +
                    "Marca varchar(100) NOT NULL," +
                    "Modelo varchar(100) NOT NULL," +
                    "Cilindrada int NOT NULL," +
                    "Potencia int NOT NULL," +
                    "Pac double NOT NULL," +
                    "Fiabilidade int NOT NULL," +
                    "Hibrido int NOT NULL," + // 1 sim, 0 não
                    "PotenciaEletrica int," +
                    "TaxaDeterioracao float)";
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
            stm.executeUpdate("TRUNCATE carros");
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
                ResultSet rs = stm.executeQuery("SELECT Id FROM carros WHERE Id='" + key.toString() + "'")) {
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
        Carro u = (Carro) value;
        return this.containsKey(u.getID());
    }

    @Override
    public Set<Entry<Integer, Carro>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<Integer,Carro>> entrySet() not implemented!");
    }

    // public Carro(String marca, String modelo, int cilindrada, int potencia, int
    // fiabilidade, int id, double PAC)
    @Override
    public Carro get(Object key) {
        Carro t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM carros WHERE Id='" + key + "'")) {
            if (rs.next()) { // A chave existe na tabela
                String modelo = rs.getString("Modelo");
                int hibrido = rs.getInt("Hibrido");
                if (modelo.equals("C1")) {
                    if (hibrido == 1) {
                        t = new C1Hibrido(rs.getInt("Id"), rs.getString("Marca"), rs.getString("Modelo"),
                                rs.getInt("Potencia"), rs.getInt("Fiabilidade"), rs.getDouble("Pac"),
                                rs.getInt("PotenciaEletrica"));
                    } else {
                        t = new C1(rs.getInt("Id"), rs.getString("Marca"), rs.getString("Modelo"),
                                rs.getInt("Potencia"), rs.getInt("Fiabilidade"), rs.getDouble("Pac"));
                    }

                } else if (modelo.equals("C2")) {
                    if (hibrido == 1) {
                        t = new C2Hibrido(rs.getInt("Id"), rs.getString("Marca"), rs.getString("Modelo"),
                                rs.getInt("Cilindrada"), rs.getInt("Potencia"), rs.getInt("Fiabilidade"),
                                rs.getDouble("Pac"), rs.getInt("PotenciaEletrica"));
                    } else {
                        t = new C2(rs.getInt("Id"), rs.getString("Marca"), rs.getString("Modelo"),
                                rs.getInt("Cilindrada"), rs.getInt("Potencia"), rs.getInt("Fiabilidade"),
                                rs.getDouble("Pac"));
                    }
                } else if (modelo.equals("SC")) {
                    t = new SC(rs.getInt("Id"), rs.getString("Marca"), rs.getString("Modelo"), rs.getInt("Potencia"),
                            rs.getInt("Fiabilidade"), rs.getDouble("Pac"));
                } else {
                    if (hibrido == 1) {
                        t = new GTHibrido(rs.getInt("Id"), rs.getString("Marca"), rs.getString("Modelo"),
                                rs.getInt("Cilindrada"), rs.getInt("Potencia"), rs.getInt("Fiabilidade"),
                                rs.getDouble("Pac"), rs.getDouble("TaxaDeterioracao"), rs.getInt("PotenciaEletrica"));
                    } else {
                        t = new GT(rs.getInt("Id"), rs.getString("Marca"), rs.getString("Modelo"),
                                rs.getInt("Cilindrada"), rs.getInt("Potencia"), rs.getInt("Fiabilidade"),
                                rs.getDouble("Pac"), rs.getDouble("TaxaDeterioracao"));

                    }
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
    public Set<Integer> keySet() {
        throw new NullPointerException("Not implemented!");
    }

    @Override
    public Carro put(Integer arg0, Carro arg1) {
        Carro t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {

            int id = arg1.getID();
            String categoria = null;
            String marca = arg1.getMarca();
            String modelo = arg1.getModelo();
            int cilindrada = arg1.getCilindrada();
            int potencia = arg1.getPotencia();
            double pac = arg1.getPAC();
            int fiabilidade = arg1.getFiabilidade();
            double taxaDeterioracao = 0;
            int hibrido = 0;
            int potenciaeletrica = 0;

            if (arg1 instanceof C1) {
                categoria = "C1";

                if (arg1 instanceof C1Hibrido) {
                    hibrido = 1;
                    C1Hibrido c1 = (C1Hibrido) arg1;
                    potenciaeletrica = c1.getPotenciaEletrico();
                }
            } else if (arg1 instanceof C2) {
                categoria = "C2";

                if (arg1 instanceof C2Hibrido) {
                    hibrido = 1;
                    C2Hibrido c2 = (C2Hibrido) arg1;
                    potenciaeletrica = c2.getPotenciaEletrico();
                }

            } else if (arg1 instanceof GT) {
                GT j = (GT) arg1;
                categoria = "GT";
                taxaDeterioracao = j.getTaxaDeterioracao();

                if (arg1 instanceof GTHibrido) {
                    hibrido = 1;
                    GTHibrido gt = (GTHibrido) arg1;
                    potenciaeletrica = gt.getPotenciaEletrico();
                }
            } else { // SC
                categoria = "SC";

            }

            stm.executeUpdate(
                    "INSERT INTO utilizadores " +
                            "VALUES ('" + id + "', '" +
                            categoria + "', '" +
                            marca + "', '" +
                            modelo + "', '" +
                            cilindrada + "', '" +
                            potencia + "', '" +
                            pac + "', '" +
                            fiabilidade + "', '" +
                            hibrido + "', '" +
                            potenciaeletrica + "', '" +
                            taxaDeterioracao + "')");

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Carro> m) {
        for (Carro t : m.values()) {
            this.put(t.getID(), t);
        }
    }

    @Override
    public Carro remove(Object key) {
        Carro t = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {

            stm.executeUpdate("DELETE FROM carros WHERE Id='" + key + "'");
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
                ResultSet rs = stm.executeQuery("SELECT count(*) FROM carros")) {
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
    public Collection<Carro> values() {
        Collection<Carro> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT Id FROM carros")) {
            while (rs.next()) {
                int idt = rs.getInt("Id");
                Carro t = this.get(idt);
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

            if (this.size() == 0) {

                // 1º Carro
                stm.executeUpdate(
                        "INSERT INTO carros " +
                                "VALUES (1, " + // id
                                "'C2', " + // categoria
                                "'Ferrari', " + // marca
                                "'488 GTE', " + // modelo
                                "3902, " + // cilindrada
                                "661, " + // potencia
                                "0.2, " + // pac
                                "0.8, " + // fiabilidade
                                "0, " + // hibrido (neste acso, não é híbrido)
                                "0, " + // potenciaeletrica
                                "0.1) ON DUPLICATE KEY UPDATE Id = 1"); // taxaDeteriorcao

                String sql = "INSERT INTO carros (Id,Categoria,Marca,Modelo,Cilindrada,Potencia,Pac,Fiabilidade,Hibrido,PotenciaEletrica,TaxaDeterioracao)"
                        +
                        " Values (2,'C1','marca1','modelo1',1000,600,0.4,0.7,0,0,0)," +
                        "(3,'C2','marca1','modelo1',4000,1000,0.5,0.6,1,100,0.06)," +
                        "(4,'GT','marca1','modelo1',3000,1000,0.6,0.3,0,200,0.03)," +
                        "(5,'SC','marca1','modelo1',2500,1000,0.9,0.4,0,0,0)";

                stm.executeUpdate(sql);
            }

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

}
