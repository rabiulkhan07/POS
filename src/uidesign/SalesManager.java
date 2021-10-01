/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uidesign;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author RABIUL
 */
public class SalesManager {
    Connection conn = null;
    Statement statement1 = null;
    ResultSet rs = null;

    public int reciptNo() throws SQLException {
        conn = DBConnection.connectDb();
        int recipt_no = 0;
        if (conn != null) {
            try {
                String query = "select max(recipt_no) from sell";
                statement1 = conn.createStatement();
                rs = statement1.executeQuery(query);
                while (rs.next()) {
                    recipt_no = rs.getInt(1);
                }

                if (recipt_no==0) {
                    recipt_no = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rs.close();
                statement1.close();
                conn.close();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Database Connection failed!!!");
        }
        return recipt_no;
    }
}
