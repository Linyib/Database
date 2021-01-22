import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;
//JDBC语句操作数据库
public class db3 {
    //更新地区数据
    public void Insertinto(String name, int confirmed, int recovered, int deaths, LocalDateTime data){
        String url = "jdbc:mysql://localhost:3306/db1?serverTimezone=UTC&useSSL=false";
        String user = "root";
        String pass = "admin";
// 获取连接
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
// 访问数据库
        Statement stmt= null;
        try {
            stmt = conn.createStatement();
            stmt.execute("use EpidemicData;");
            int count=stmt.executeUpdate("replace into localdata(NAME,confirmed,recovered,deaths,updated) values('" + name + "'," + confirmed +"," + recovered +"," + deaths +",'" + data +"')");
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
// 关闭连接:
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //更新国家总数据
    public void InsertintoAll(String name, int confirmed, int recovered, int deaths){
        String url = "jdbc:mysql://localhost:3306/db1?serverTimezone=UTC&useSSL=false";
        String user = "root";
        String pass = "admin";
// 获取连接
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//访问数据库
        //5.获取执行sql的对象 Statement

        Statement stmt= null;
        try {
            stmt = conn.createStatement();
            stmt.execute("use EpidemicData;");
            int count=stmt.executeUpdate("replace into country(NAME,confirmed,recovered,deaths) values('" + name + "'," + confirmed +"," + recovered +"," + deaths +")");
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


// 关闭连接
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //查询地区数据
    public void Qurey(String area){
        String url = "jdbc:mysql://localhost:3306/db1?serverTimezone=UTC&useSSL=false";
        String user = "root";
        String pass = "admin";
// 获取连接
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//访问数据库
        //5.获取执行sql的对象 Statement

        Statement stmt= null;
        try {
            stmt = conn.createStatement();
            stmt.execute("use EpidemicData;");
            ResultSet resultSet =stmt.executeQuery("select * from localdata where name='"+area+"'");
            //5.遍历ResultSet结果集,获取数据,打印.
            while (resultSet.next()){
                Integer confirmed=resultSet.getInt("confirmed");
                Integer recovered=resultSet.getInt("recovered");
                Integer deaths=resultSet.getInt("deaths");
                Date updated= resultSet.getDate("updated");
                System.out.println(area+' '+"确诊病例:" +confirmed+' '+"恢复人数：" +recovered+' '+"死亡人数:" +deaths+' '+"数据更新时间：" +updated);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


// 关闭连接
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
