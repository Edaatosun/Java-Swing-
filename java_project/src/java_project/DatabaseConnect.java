package java_project;


import java.sql.*;

public class DatabaseConnect {
	
	private final String host = "localhost";
    private final int port = 3306;
    private final String dbName = "java";
    private final String userName = "root";
    private final String password = "123456";
    
    
    Connection myCon;
    Statement myStat;
    
    
    // veri bağlantısını kapattık
    public void close(){
        try {
            myCon.close();
            myStat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("Veri tabanı bağlantısı kapatıldı");
        
    }
    // bağlantı yapmak için connecting methodunu çağırıyoruz
    public DatabaseConnect() {
        connecting();

    }
    //
    public void connecting(){
        try {
            this.myCon = (Connection) DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, userName, password);
            this.myStat = myCon.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
          
        }
    }
    
    
    // ekle,sil,güncelle gibi işlemlerde sorgunun çalışması için yazılmıştır.
    
    public void islemler(String sorgu) {
        try {
            myStat.executeUpdate(sorgu);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
    
    
    //Veritabanından veri çekmek için yazılmıştır. 
    
    public ResultSet baglanti(String sorgu) {
        connecting();
        try {
            ResultSet myRs = myStat.executeQuery(sorgu);
            return myRs;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return null;

    }
    
    
    

}
