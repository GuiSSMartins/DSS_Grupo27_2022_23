import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.*;

/**
 * Versão incompleta de um DAO para carroHibrido
 *
 * Tabelas a criar na BD: ver método getInstance
 *
 * @author JFC
 * @version 20201208
 */
public class carroHibridoDAO implements Map<String, Turma> {
    private static carroHibridoDAO singleton = null;

    /**
     * Primary key = marca + modelo
     * PC1  : String marca, String modelo, int cilindrada, int potencia
     * PC2  : String marca, String modelo, int cilindrada, int potencia, int p_mecanica
     * SC   : String marca, String modelo, int cilindrada, int potencia
     * GT   : String marca, String modelo, int cilindrada, int potencia
     * PC1H : String marca, String modelo, int cilindrada, int potencia, int motor_eletrico
     * PC2H : String marca, String modelo, int cilindrada, int potencia, int p_mecanica, int motor_eletrico
     * GTH  : String marca, String modelo, int cilindrada, int potencia, int motor_eletrico
     */

    private carroHibridoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            sql="CREATE TABLE IF NOT EXISTS gth (" +
                "Num integer NOT NULL PRIMARY KEY INCREMENTAL," + 
                "Marca varchar(45) DEFAULT NULL," +
                "Modelo varchar(45) DEFAULT NULL," +
                "Cilindrada integer DEFAULT NULL," +
                "Potencia integer DEFAULT NULL," +
                "Eletrico integer DEFAULT NULL";
            stm.executeUpdate(sql);
            sql="CREATE TABLE IF NOT EXISTS c1h (" +
                "Num integer NOT NULL PRIMARY KEY INCREMENTAL," + 
                "Marca varchar(45) DEFAULT NULL," +
                "Modelo varchar(45) DEFAULT NULL," +
                "Cilindrada integer DEFAULT NULL," +
                "Potencia integer DEFAULT NULL," +
                "Eletrico integer DEFAULT NULL";
            stm.executeUpdate(sql);
            sql="CREATE TABLE IF NOT EXISTS c2h (" +
                "Num integer NOT NULL PRIMARY KEY INCREMENTAL," + 
                "Marca varchar(45) DEFAULT NULL," +
                "Modelo varchar(45) DEFAULT NULL," +
                "Cilindrada integer DEFAULT NULL," +
                "Potencia integer DEFAULT NULL," +
                "Eletrico integer DEFAULT NULL";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Implementação do padrão Singleton
     *
     * @return devolve a instância única desta classe
     */
    public static carroHibridoDAO getInstance() {
        if (carroHibridoDAO.singleton == null) {
            carroHibridoDAO.singleton = new carroHibridoDAO();
        }
        return carroHibridoDAO.singleton;
    }

    /**
     * @return número de carroHibrido na base de dados
     */
    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM carroHibrido")) {
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

    /**
     * Método que verifica se existem carroHibrido
     *
     * @return true se existirem 0 carroHibrido
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Método que cerifica se um id de turma existe na base de dados
     *
     * @param key id da turma
     * @return true se a turma existe
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT Id FROM carroHibrido WHERE Id='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    /**
     * Verifica se uma turma existe na base de dados
     *
     * Esta implementação é provisória. Devia testar o objecto e não apenas a chave.
     *
     * @param value ...
     * @return ...
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    @Override
    public boolean containsValue(Object value) {
        Turma t = (Turma) value;
        return this.containsKey(t.getId());
    }

    /**
     * Obter uma turma, dado o seu id
     *
     * @param key id da turma
     * @return a turma caso exista (null noutro caso)
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    @Override
    public Turma get(Object key) {
        Turma t = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM carroHibrido WHERE Id='"+key+"'")) {
            if (rs.next()) {  // A chave existe na tabela
                // Reconstruir a colecção de alunos da turma
                Collection<String> alunos = getAlunosTurma(key.toString(), stm);

                // Reconstruir a Sala
                Sala s = null;
                String sql = "SELECT * FROM salas WHERE Num='"+rs.getString("Sala")+"'";
                try (ResultSet rsa = stm.executeQuery(sql)) {
                    if (rsa.next()) {  // Encontrou a sala
                        s = new Sala(rs.getString("Sala"),
                                     rsa.getString("Edificio"),
                                     rsa.getInt("Capacidade"));
                    } else {
                        // BD inconsistente!! Sala não existe - tratar com excepções.
                    } // catch é feito no try inicial - este try serve para fechar o ResultSet automaticamente
                      // Nota: abrir um novo ResultSet no mesmo Statement fecha o ResultSet anterior
                }

                // Reconstruir a turma cokm os dados obtidos da BD 
                t = new Turma(rs.getString("Id"), s, alunos);
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    /**
     * Método que obtém a lista de alunos da turma
     *
     * @param tid o id da turma
     * @return a lista de alunos da turma
     */
    private Collection<String> getAlunosTurma(String tid, Statement stm) throws SQLException {
        Collection<String> alunos = new TreeSet<>();
        try (ResultSet rsa = stm.executeQuery("SELECT Num FROM alunos WHERE Turma='"+tid+"'")) {
            while(rsa.next()) {
                alunos.add(rsa.getString("Num"));
            }
        } // execepção é enviada a quem chama o método - este try serve para fechar o ResultSet automaticamente
        return alunos;
    }

    /**
     * Insere uma turma na base de dados
     *
     * ATENÇÂO: Esta implementação é provisória.
     * Falta devolver o valor existente (caso exista um)
     * Falta remover a sala anterior, caso esteja a ser alterada
     * Deveria utilizar transacções...
     *
     * @param key o id da turma
     * @param t a turma
     * @return para já retorna sempre null (deverá devolver o valor existente, caso exista um)
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    @Override
    public Turma put(String key, Turma t) {
        Turma res = null;
        Sala s = t.getSala();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            // Actualizar a Sala
            stm.executeUpdate(
                    "INSERT INTO salas " +
                                "VALUES ('"+ s.getNumero()+ "', '"+
                                            s.getEdificio()+"', "+
                                            s.getCapacidade()+") " +
                                "ON DUPLICATE KEY UPDATE Edificio=Values(Edificio), " +
                                                        "Capacidade=Values(Capacidade)");

            // Actualizar a turma
            stm.executeUpdate(
                    "INSERT INTO carroHibrido VALUES ('"+t.getId()+"', '"+s.getNumero()+"') " +
                                "ON DUPLICATE KEY UPDATE Sala=VALUES(Sala)");

            // Actualizar os alunos da turma
            Collection<String> oldAl = getAlunosTurma(key, stm);
            Collection<String> newAl = t.getAlunos();
            newAl.removeAll(oldAl);         // Alunos que entram na turma, em relação ao que está na BD
            oldAl.removeAll(t.getAlunos()); // Alunos que saem da turma, em relação ao que está na BD
            try (PreparedStatement pstm = conn.prepareStatement("UPDATE alunos SET Turma=? WHERE Num=?")) {
                // Remover os que saem da turma (colocar a NULL a coluna que diz qual a turma dos alunos)
                pstm.setNull(1, Types.VARCHAR);
                for (String a: oldAl) {
                    pstm.setString(2, a);
                    pstm.executeUpdate();
                }
                // Adicionar os que entram na turma (colocar o Id da turma na coluna Turma da tabela alunos)
                pstm.setString(1, t.getId());
                for (String a: newAl) {
                    pstm.setString(2, a);
                    pstm.executeUpdate();
                }
            }

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    /**
     * Remover uma turma, dado o seu id
     *
     * NOTA: Não estamos a apagar a sala...
     *
     * @param key id da turma a remover
     * @return a turma removida
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    @Override
    public Turma remove(Object key) {
        Turma t = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             PreparedStatement pstm = conn.prepareStatement("UPDATE alunos SET Turma=? WHERE Num=?")) {
            // retirar os alunos da turma
            pstm.setNull(1, Types.VARCHAR);
            for (String na: t.getAlunos()) {
                pstm.setString(2, na);
                pstm.executeUpdate();
            }
            // apagar a turma
            stm.executeUpdate("DELETE FROM carroHibrido WHERE Id='"+key+"'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    /**
     * Adicionar um conjunto de carroHibrido à base de dados
     *
     * @param carroHibrido as carroHibrido a adicionar
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    @Override
    public void putAll(Map<? extends String, ? extends Turma> carroHibrido) {
        for(Turma t : carroHibrido.values()) {
            this.put(t.getId(), t);
        }
    }

    /**
     * Apagar todas as carroHibrido
     *
     * @throws NullPointerException Em caso de erro - deveriam ser criadas exepções do projecto
     */
    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("UPDATE alunos SET Turma=NULL");
            stm.executeUpdate("TRUNCATE carroHibrido");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * NÃO IMPLEMENTADO!
     * @return ainda nada!
     */
    @Override
    public Set<String> keySet() {
        throw new NullPointerException("Not implemented!");
    }

    /**
     * @return Todos as carroHibrido da base de dados
     */
    @Override
    public Collection<Turma> values() {
        Collection<Turma> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Id FROM carroHibrido")) { // ResultSet com os ids de todas as carroHibrido
            while (rs.next()) {
                String idt = rs.getString("Id"); // Obtemos um id de turma do ResultSet
                Turma t = this.get(idt);                    // Utilizamos o get para construir as carroHibrido uma a uma
                res.add(t);                                 // Adiciona a turma ao resultado.
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    /**
     * NÃO IMPLEMENTADO!
     * @return ainda nada!
     */
    @Override
    public Set<Entry<String, Turma>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Aluno>> entrySet() not implemented!");
    }
}
