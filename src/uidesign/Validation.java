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
public class Validation {

    Connection conn = null;
    Statement statement1 = null;
    ResultSet rs = null;

    public String categoryValidate(String name,Connection conn,int action) throws SQLException {
        String flag = "yes";
        //conn = DBConnection.connectDb();
        if (conn != null) {
            try {
                int totalID = 0;
                String getDataSql = "";
                if (action == 0) {
                    getDataSql = "select count(c_id) from categories where c_name like '"+name+"'";
                }
                else if (action == 1) {
                    getDataSql = "select count(u_id) from unit where u_name like '"+name+"'";
                }
                statement1 = conn.createStatement();
                rs = statement1.executeQuery(getDataSql);

                while (rs.next()) {
                    totalID = rs.getInt(1);
                }
                if(totalID == 0){
                    flag = "no";
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rs.close();
                statement1.close();
                //conn.close();
            }
        } else {
            JOptionPane.showMessageDialog(null, "DataBase Connection Failed");
        }

        return flag;
    }
}
