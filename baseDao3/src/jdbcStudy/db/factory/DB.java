package jdbcStudy.db.factory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB
{
    private static DB dbInstance;
    public static DB getDbInstance()
    {
        /**
         * 单例模式,
         */
        if (dbInstance == null)
        {
            synchronized (DB.class)
            {
                if (dbInstance == null)
                {
                    dbInstance = new DB();
                }   
            }
        }
        return dbInstance;
    }

    public static void setDbInstance(DB dbInstance)
    {
        DB.dbInstance = dbInstance;
    }

    public static Connection getConnection()
    {
        try
        {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e)
        {
            System.out.println(" registerDriver fail");
        }
        
        String url = "jdbc:mysql://localhost:3306/hibernate?useUnicode=true&characterEncoding=utf-8";
        String userName = "root";
        String password = "2011211961";
        
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e)
        {
            return null;
        }
        return conn;
    }
    
    public static void CloseConnection(Connection conn,Statement stm,ResultSet rs)
    {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try
        {
            if (stm != null)
            {
                stm.close();
            }
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try
        {
            if (conn != null)
            {
                conn.close();
            }
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
     
}
