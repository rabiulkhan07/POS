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
public class CalculationOperations {
    Connection conn = null;
    Statement statement1 = null;
    ResultSet rs = null;
    public double totalCalulatio(double quantity,double b_price,double discount,double vat) {
        
        double totalD = (quantity * b_price) + (vat / 100);
        double total = totalD - (discount / 100);

        return total;
    }
    public double stockUpdate(String given_qnty,String id,String product_id,String b_price) throws SQLException{
        String qnty_stock = "",qnty_product = "";
        double quantity = 0;
        conn = DBConnection.connectDb();
        if(conn != null){
            String qnty_stock_query = "select quantity from stock where product_id='"+product_id+"'";
            statement1 = conn.createStatement();
            rs = statement1.executeQuery(qnty_stock_query);
            while (rs.next()) {
                qnty_stock = rs.getString("quantity");
            }
            rs.close();
            statement1.close();
            
            String qnty_products_query = "select quantity from products where id='"+id+"'";
            statement1 = conn.createStatement();
            rs = statement1.executeQuery(qnty_products_query);
            while (rs.next()) {
                qnty_product = rs.getString("quantity");
            }
            rs.close();
            statement1.close();
            
            quantity = (Double.parseDouble(qnty_stock)-Double.parseDouble(qnty_product)) + Double.parseDouble(given_qnty);
            
        }else{
            JOptionPane.showMessageDialog(null, "Database Connection Failed!!!");
        }
        return quantity;
    }
    
}
