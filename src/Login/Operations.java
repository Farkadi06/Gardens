package Login;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Operations {

	private static Connection myConn;
	private static PreparedStatement preparedStatement;

	public static boolean showTasks(String username, String password,String usertype) {
		try {
			myConn = MySqlConnection.getConnection();
			String mySqlQuery = "SELECT UID, Usertype,Username FROM login WHERE Username = '"+username+"' AND Password = '"+password+"' AND Usertype = '"+usertype+"';";
					
			preparedStatement = myConn.prepareStatement(mySqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				LoginSession.UID = resultSet.getInt("UID");
				LoginSession.Usertype =resultSet.getString("Usertype");
				LoginSession.Nickname = resultSet.getString("Username");
				
				return true;
			}
			
		}catch(Exception exception) {
			JOptionPane.showMessageDialog(null, "Database error: "+exception.getMessage());
		}
		return false;
	}
}
