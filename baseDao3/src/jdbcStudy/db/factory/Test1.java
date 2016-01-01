package jdbcStudy.db.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1
{
    private void connect1()
    {
        /**
         * 1.注册驱动
         * 
         */
        
        try
        {
            /*
             * 1.mysql.com
             * 2.java database connection
             * 3.Driver.class
             */
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
            System.out.println("not find Driver");
        }
        
        StringBuffer url = new StringBuffer();
        /*
         * 协议:jdbc
         * 子协议:mysql
         * 数据源标示:a.数据库来源的地址;b.端口号;c数据库的名字
         * 
         */
        url.append("jdbc:mysql://localhost:3306/hibernate");
        url.append("?useUnicode=true&characterEncoding=utf-8");
        String userName = "root";
        String password = "2011211961";
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
         try
        {
            conn = DriverManager.getConnection(url.toString(), userName, password);
            stm  = conn.createStatement();
            String sql = "select * from BOOK";
            rs = stm.executeQuery(sql);
            if (rs == null)
            {
                System.out.println("rs is null");
                return;
            }
            boolean next = rs.next();
            while(next)
            {
                System.out.println(rs.getInt("ID"));
                next = rs.next();
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("get connection fail");
        }
         finally
         {
             //rs stm conn 分开关闭,防止因为一个异常了另外两个关不了
             try
            {
                if (rs != null)
                 {
                     rs.close();
                 }
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
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
         }
        try
        {
            if (conn != null)
            {
                conn.close();
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    
    

    }
    public static void main(String[] args)
    {
        new Test1().connect1();
    }
}
