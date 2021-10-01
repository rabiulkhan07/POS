/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uidesign;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author DeeptoTV
 */
public class SqlManager {

    Connection conn = null;
    Statement statement1 = null;
    ResultSet rs = null;

    public void userInfoInsert(String userName, String password,String type) {
        String userType = type;
        try {
            //Create DataBase Connection
            conn = DBConnection.connectDb();
            //Count Total Admin in the list
            int totalID = 0;
            String getDataSql = "select count(id) from users where user_type='Admin'";
            statement1 = conn.createStatement();
            rs = statement1.executeQuery(getDataSql);
            
            while(rs.next()){
                totalID = rs.getInt(1);
            }
            try{
                rs.close();
                statement1.close();
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            }
            
            //if there is a admin in the list then can not new sign up
            if(totalID == 0){
                String insertSql = "INSERT INTO users (user_type,user_name,password) values (?,?,?)";
                PreparedStatement preparedStmt = conn.prepareStatement(insertSql);

                preparedStmt.setString (1,userType);
                preparedStmt.setString (2,userName);
                preparedStmt.setString (3,password);

                preparedStmt.execute();
                JOptionPane.showMessageDialog(null, "you are successfully signed up");
            }
            else{
                JOptionPane.showMessageDialog(null, "Sorry!! There is a admin in the list.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Connection Failed!!");
        }finally{
            try{
                if (conn != null) {
                    conn.close();
                }
                
            }catch(Exception e){
                
            }//finish catch
        }

    }//finish userInfoInsert method
    
    public void userInfoInsert_setting(String userName, String password,String type) {
        String userType = type;
        try {
            //Create DataBase Connection
            conn = DBConnection.connectDb();
            //Count Total Admin in the list
          
            //if there is a admin in the list then can not new sign up
           
                String insertSql = "INSERT INTO users (user_type,user_name,password) values (?,?,?)";
                PreparedStatement preparedStmt = conn.prepareStatement(insertSql);

                preparedStmt.setString (1,userType);
                preparedStmt.setString (2,userName);
                preparedStmt.setString (3,password);

                preparedStmt.execute();
                JOptionPane.showMessageDialog(null, "User Successfully created!!!");
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Connection Failed!!");
        }finally{
            try{
                if (conn != null) {
                    conn.close();
                }
                
            }catch(Exception e){
                
            }//finish catch
        }

    }//finish userInfoInsert method
    
    //Login method
    
    static int AD_USER_ID=0;
    static String username="";
    static String password="";
   // private static Object userList;
    public static String getUserName(){
        return username;
    }
    public static int getUserID(){
        return AD_USER_ID;
    }
    
    public static boolean loginUser(String userName, String pass,String userType) throws SQLException, UnknownHostException{
        
        username="";
        password="";
        AD_USER_ID=0;
        boolean isFound=false;
        Connection conn = null;
        conn=DBConnection.connectDb();
        if(conn!=null){
            //JOptionPane.showMessageDialog(null, "connection", "Login Error", JOptionPane.ERROR_MESSAGE);
        
            ResultSet rs=null;
            PreparedStatement pstmt=null;		
            String sql = "select id from users where user_type = '"+userType+"' and "
                    + "user_name = '"+userName+"' and password='"+pass+"'";

            
            pstmt = conn.prepareStatement(sql);
            try {
                    rs = pstmt.executeQuery();
                    if(rs.next()){	
                        isFound= true;
                        AD_USER_ID=rs.getInt(1);
                        username=userName;
                        password=pass;
                        //System.getProperty("user.name");
                       // InetAddress.getLocalHost().getHostName();
                        //System.out.println(InetAddress.getLocalHost().getHostName());
                    }
                    rs.close();
                    pstmt.close();
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            finally{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "DataBase Connection Problem!!!", "Server Error", JOptionPane.ERROR_MESSAGE);

        }
          
        return isFound;
    }
    
    public String getLastID() throws SQLException{
        String lastId = "";
        
        conn = DBConnection.connectDb();
        
         int totalID = 0;
         if(conn != null){
            String getDataSql = "select count(id) from products";
            statement1 = conn.createStatement();
            rs = statement1.executeQuery(getDataSql);
            
            while(rs.next()){
                totalID = rs.getInt(1);
            }
            try{
                rs.close();
                statement1.close();
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            }
        
        statement1 = conn.createStatement();
        if(totalID > 0){
            String maxDate = "";
            String sql_max = "select max(date) as date from products where quantity > 0";
            rs = statement1.executeQuery(sql_max);
            
            while(rs.next()){
                maxDate = rs.getString("date");
            }
            try{
                rs.close();
                statement1.close();
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            }
            statement1 = conn.createStatement();
            String sql = "select product_id from products where date = '"+maxDate+"'";
            rs = statement1.executeQuery(sql);
            
            while(rs.next()){
                lastId = rs.getString("product_id");
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
            lastId = "0";
        }
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Database Connection Failed");
        }
        
        return lastId;
    }
    public String getProductID(String p_id) throws SQLException{
        String flag = "no";
        
        conn = DBConnection.connectDb();
        
         int totalID = 0;
         if(conn != null){
            String getDataSql = "select count(product_id) from products where product_id='"+p_id+"'";
            statement1 = conn.createStatement();
            rs = statement1.executeQuery(getDataSql);
            
            while(rs.next()){
                totalID = rs.getInt(1);
            }
            try{
                rs.close();
                statement1.close();
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            }
        
        statement1 = conn.createStatement();
        if(totalID > 0){
            flag = "yes";
        }
        else{
            flag = "no";
        }
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Database Connection Failed");
        }
         return flag;
    }

}
