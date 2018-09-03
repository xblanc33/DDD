package fr.ubordeaux.ao.referencemanagement.infrastructure.persistence.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class CreateDataBase {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String MYSQL_URL = "jdbc:mysql://localhost/";
    static final String PRODUCTS_URL = "jdbc:mysql://localhost/PRODUCTS";

    static final String USER = "root";
    static final String PASS = "root";

    static Connection connection;
    
    
    public static void main(String[] args) {
        try {
            createConnection(MYSQL_URL);
            createDatabase();

            createConnection(PRODUCTS_URL);
            createTable(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    private static void createConnection(String url) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, USER, PASS);
    }

    private static void createDatabase() throws SQLException {
        Statement stmt = connection.createStatement();            
        String sql = "CREATE DATABASE PRODUCTS";
        stmt.executeUpdate(sql);
    }

    private static void createTable() throws SQLException {
        Statement stmt = connection.createStatement();            
        String sql = "CREATE TABLE REFERENCE (id VARCHAR(36) not NULL, " +
        " name VARCHAR(255), " + 
        " description VARCHAR(255), " + 
        " PRIMARY KEY ( id ))";
        stmt.executeUpdate(sql);
    }
}