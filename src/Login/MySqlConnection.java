package Login;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class MySqlConnection {

	public static Connection getConnection() throws Exception{
		String dbRoot = "jdbc:mysql://";
		String hostName = "localhost:3306/";
		String dbName = "projetjardinage";
		String dbUrl = dbRoot+hostName+dbName;
		
		String hostUserName = "Oussama";
		String hostPassword = "123a";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection myConn = (Connection) DriverManager.getConnection(dbUrl,hostUserName,hostPassword);
		
		return myConn;
	}
 }
