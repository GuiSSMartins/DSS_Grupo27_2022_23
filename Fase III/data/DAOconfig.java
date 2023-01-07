package data;

// mysql -u root -p
// CREATE USER 'dss'@'localhost' IDENTIFIED BY 'dssgrupo27';
// GRANT ALL PRIVILEGES ON * . * TO 'dss'@'localhost';
// mysql -u dss -p
// CREATE DATABASE dss;

public class DAOconfig {
    public static final String USERNAME = "dss";                       
    public static final String PASSWORD = "dssgrupo27";
    private static final String DATABASE = "dss";
    // private static final String DRIVER = "jdbc:mariadb";        // Usar para MariaDB
    private static final String DRIVER = "jdbc:mysql";        // Usar para MySQL
    public static final String URL = DRIVER+"://localhost:3306/"+DATABASE;
}