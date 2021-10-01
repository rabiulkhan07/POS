/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uidesign;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author RABIUL
 */
public class StockManager {
    Connection conn = null;
    Statement statement1 = null;
    ResultSet rs = null;
    
    public String stockDetails(String p_id,String article,String b_price){
        String msg = "";
        try {
            //Create DataBase Connection
            conn = DBConnection.connectDb();
            if(conn !=null){
            //Count Total Admin in the list
            int totalID = 0;
            String quantity = "";
            String getDataSql = "";
            if(article.equals("0")){
                getDataSql = "select id,quantity from stock where product_id='"+p_id+"'";
            
            }else{
               getDataSql = "select id,quantity from stock where product_id='"+p_id+"' and article='"+article+"' and buying_price='"+b_price+"'";
             
            }
            statement1 = conn.createStatement();
            rs = statement1.executeQuery(getDataSql);
            
            while(rs.next()){
                totalID = rs.getInt("id");
                quantity = rs.getString("quantity");
            }
            if(totalID > 0){
                msg = "yes/"+quantity;
            }
            else{
                msg = "No/0";
            }
            
            try{
                rs.close();
                statement1.close();
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            }
            }
            else{
                JOptionPane.showMessageDialog(null, "Database Conection Failed!!!");
            }
        }catch(Exception e){
            
        }
        return msg;
    }
    
}
