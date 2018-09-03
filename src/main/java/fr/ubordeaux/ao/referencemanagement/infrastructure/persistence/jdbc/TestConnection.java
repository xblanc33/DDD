package fr.ubordeaux.ao.referencemanagement.infrastructure.persistence.jdbc;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class TestConnection {
    public static void main (String [] args ) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://127.0.0.1:3306/sys?user=root&password=root";
        Connection conn = DriverManager.getConnection(connectionUrl);
        ResultSet rs = conn.prepareStatement("show tables").executeQuery();
 
        while(rs.next()){
            String s = rs.getString(1);
            System.out.println(s);
        }
    }
}
 