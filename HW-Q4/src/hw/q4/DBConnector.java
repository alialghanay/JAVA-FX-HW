/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw.q4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alialghanay
 */
public class DBConnector {
    
    private static Connection conn;  
    private static final String url = "jdbc:mysql://localhost:3306/eedb";
    private static final String user = "root";
    private static final String password =  "";
    
    public DBConnector()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception while getting connection: " + ex.getMessage());
        }
   
    }
    
    public static Connection getConnection() {
        return conn;
    }
    
    public static void stopConnection() {
        try {
           conn.close();
        } catch (SQLException ex) {
            System.out.println("Exception while getting connection: " + ex.getMessage());
        }
    }
    
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS `employee` (" +
                "`eid` int(11) NOT NULL," +
                "`first_name` varchar(255) NOT NULL," +
                "`last_name` varchar(255) NOT NULL," +
                "`address` varchar(255) NOT NULL," +
                "`city` varchar(255) NOT NULL," +
                "`phone_nam` varchar(255) NOT NULL," +
                "`sex` tinyint(1) NOT NULL," +
                "`dob` date NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;" +
                "ALTER TABLE `employee` ADD PRIMARY KEY (`eid`);" +
                "ALTER TABLE `employee` MODIFY `eid` int(11) NOT NULL AUTO_INCREMENT; COMMIT;";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(query);
            System.out.println("Table `employee` created or already exists.");
        } catch (SQLException ex) {
            System.out.println("Exception while creating table: " + ex.getMessage());
        }
    }
    
     public void deleteTable() {
        String query = "DROP TABLE IF EXISTS `employee`;";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(query);
            System.out.println("Table `employee` deleted if it existed.");
        } catch (SQLException ex) {
            System.out.println("Exception while deleting table: " + ex.getMessage());
        }
    }
     
    public void truncateTbale() {
        String query = "TRUNCATE TABLE `employee`;";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(query);
            System.out.println("Table `employee` truncated.");
        } catch (SQLException ex) {
            System.out.println("Exception while truncating table: " + ex.getMessage());
        }
    }

}
