/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uidesign;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author RABIUL
 */
public class SqlManagerCRUD {

    Connection conn = null;
    Statement statement1 = null;
    ResultSet rs = null;

    public int categoriesAdd(String c_name, int action) throws SQLException {
        int flag = 0;
        Validation valid = new Validation();

        conn = DBConnection.connectDb();

        if (conn != null) {
            String insertSql = "";
            try {
                if (action == 0) {
                    insertSql = "INSERT INTO categories (c_name) values (?)";
                } else if (action == 1) {
                    insertSql = "INSERT INTO unit (u_name) values (?)";
                }

                String isValid = valid.categoryValidate(c_name,conn,action);
                if(isValid.equals("no")){
                    PreparedStatement preparedStmt = conn.prepareStatement(insertSql);

                    preparedStmt.setString(1, c_name);

                    preparedStmt.execute();
                    flag = 1;
                }
                else{
                    flag = 0;
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.close();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Database connection failed!!!");
        }
        return flag;
    }
    
    //Delete Operation
    public int categoriesDelete(String c_id, int action) throws SQLException {
        int flag = 0;
        Validation valid = new Validation();

        conn = DBConnection.connectDb();

        if (conn != null) {
            String deleteSql = "";
            try {
                if (action == 0) {
                    deleteSql = "delete from categories where c_id = '"+c_id+"' ";
                } else if (action == 1) {
                    deleteSql = "delete from unit where u_id = '"+c_id+"'";
                }
                    statement1 = conn.createStatement();
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Delete this category?","Warning",dialogButton);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        statement1.executeUpdate(deleteSql);
                    
                        flag = 1;
                    }
                    
                
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.close();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Database connection failed!!!");
        }
        return flag;
    }
    //categories edit
    public int categoriesEdit(String c_id,String c_name, int action) throws SQLException {
        int flag = 0;
        conn = DBConnection.connectDb();

        if (conn != null) {
            String updateSql = "";
            try {
                if (action == 0) {
                    updateSql = "update categories set c_name = '"+c_name+"' where c_id='"+c_id+"'";
                } else if (action == 1) {
                    updateSql = "update unit set u_name = '"+c_name+"' where u_id='"+c_id+"'";
                }

                statement1 = conn.createStatement();
                statement1.executeUpdate(updateSql);
                flag = 1;
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                statement1.close();
                conn.close();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Database connection failed!!!");
        }
        return flag;
    }
}
