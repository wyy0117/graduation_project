package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Conn {

	public static Connection getconn()throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/graduation","root","123456");
		return conn;
	}
	public static Statement getst() throws Exception {
		Connection conn=Conn.getconn();
		Statement st=conn.createStatement();
		return st;
	}
}
