package data;

// mysql -u root -p
// CREATE USER 'dss'@'localhost' IDENTIFIED BY 'dssgrupo27';
// GRANT ALL PRIVILEGES ON * . * TO 'dss'@'localhost';
// mysql -u dss -p
// CREATE DATABASE dss;

public class DAOconfig {
    static final String USERNAME = "dss";                       
    static final String PASSWORD = "dssgrupo27";
    private static final String DATABASE = "dss";
    // private static final String DRIVER = "jdbc:mariadb";        // Usar para MariaDB
    private static final String DRIVER = "jdbc:mysql";        // Usar para MySQL
    static final String URL = DRIVER+"://localhost:3306/"+DATABASE;
}