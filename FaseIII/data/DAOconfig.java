/*
 *  DISCLAIMER: Este código foi criado para discussão e edição durante as aulas práticas de DSS, representando
 *  uma solução em construção. Como tal, não deverá ser visto como uma solução canónica, ou mesmo acabada.
 *  É disponibilizado para auxiliar o processo de estudo. Os alunos são encorajados a testar adequadamente o
 *  código fornecido e a procurar soluções alternativas, à medida que forem adquirindo mais conhecimentos.
 */
package DSS_Grupo27_2022_23.FaseIII.data;

// mysql -u root -p
// CREATE USER 'dss'@'localhost' IDENTIFIED BY 'dssgrupo27';
// GRANT ALL PRIVILEGES ON * . * TO 'dss'@'localhost';
// mysql -u dss -p
// CREATE DATABASE dss;
// sudo service mysqld start

public class DAOconfig {
    static final String USERNAME = "dss";                       
    static final String PASSWORD = "dssgrupo27";
    private static final String DATABASE = "dss";
    // private static final String DRIVER = "jdbc:mariadb";        // Usar para MariaDB
    private static final String DRIVER = "jdbc:mysql";        // Usar para MySQL
    static final String URL = DRIVER+"://localhost:3306/"+DATABASE;
}
