/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BobotANN;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class DatabaseManager {
    private Connection conn = null;
    
    private final String user = "root";
    private final String password = "";
    
    public DatabaseManager(){}
    
    public void set_KoneksiDatabase(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prediksi_penumpangka", user, password);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean cekKesamaanParameter(String sql){
        int sum;
        boolean flag = false;
        
        try {            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            result.next();
            sum = result.getInt(1);
            if(sum != 0){
                flag = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    
    public int getSumData(String sql){
        int sum = 0;
        
        try {            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            result.next();
            sum = result.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sum;
    }
    
    public void insertParameterBp(String sql, int percobaan, double epoch, double alpha, double error){
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, percobaan);
            statement.setInt(2, (int)epoch);
            statement.setDouble(3, alpha);
            statement.setDouble(4, error);
            
            int rowsInserted = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int SelectSingleOrder(String sql){
        int id = 0;
        
        try {            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            result.next();
            id = result.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public int get_maxValueBobot(String sql){
        int max = 0;
        
        try {            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            result.next();
            max = result.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return max;
    }

    public int get_jumlahDataBobotV(String sql){
        int jumlah = 0;
        
        try {            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            result.next();
            jumlah = result.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jumlah;
    }
    
    public int get_jumlahDataBobotW(String sql){
        int jumlah = 0;
        
        try {            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            result.next();
            jumlah = result.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jumlah;
    }
    
    public void insertDataBobotV(String sql, double bobotV, int id, int percobaan, int input, int hidden){
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, percobaan);
            statement.setInt(3, input);
            statement.setInt(4, hidden);
            statement.setDouble(5, bobotV);
            
            int rowsInserted = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertDataBobotW(String sql, double bobotW, int id, int percobaan, int hidden, int output){
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, percobaan);
            statement.setInt(3, hidden);
            statement.setInt(4, output);
            statement.setDouble(5, bobotW);
            
            int rowsInserted = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteDataBobotV(String sql, int dataHapus){
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, dataHapus);            
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteDataBobotW(String sql, int dataHapus){
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, dataHapus);            
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Double> getDataBobot(String sql){
        ArrayList<Double> dataBobot;
        
        dataBobot = new ArrayList<>();
        try {            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()){
                dataBobot.add(result.getDouble(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dataBobot;
    }
    
    public void updateIdBobotV(String sql, int idBaru, int idLama){
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idBaru);            
            statement.setInt(2, idLama);            
            
            int rowsUpdated = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateIdBobotW(String sql, int idBaru, int idLama){
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idBaru);
            statement.setInt(2, idLama);
            
            int rowsUpdated = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String[]> getDataParameterBp(String sql){
        ArrayList<String[]> Datas;
        
        Datas = new ArrayList<>();
        
        try {            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()){
                String[] Data = new String[4];
                
                Data[0] = String.valueOf(result.getInt(1));
                Data[1] = String.valueOf(result.getInt(2));
                Data[2] = String.valueOf(result.getDouble(3));
                Data[3] = String.valueOf(result.getDouble(4));
                
                Datas.add(Data);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Datas;
    }
    
    public ArrayList<String[]> getDataParameterBPLM(String sql){
        ArrayList<String[]> Datas;
        
        Datas = new ArrayList<>();
        
        try {            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()){
                String[] Data = new String[4];
                
                Data[0] = String.valueOf(result.getInt(1));
                Data[1] = String.valueOf(result.getInt(2));
                Data[2] = String.valueOf(result.getDouble(3));
                Data[3] = String.valueOf(result.getDouble(4));
                
                Datas.add(Data);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Datas;
    }
}
