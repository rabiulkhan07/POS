package uidesign;

import java.sql.*;

/**
 *
 * @author RABIUL
 */
public class DBConnection {

    Connection conn = null;

    public static Connection connectDb() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pos_system", "root", "password");
            System.out.print("Connected\n");
            return conn;
        } catch (Exception e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }

    }
}
