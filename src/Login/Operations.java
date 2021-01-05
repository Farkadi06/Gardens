package Login;

import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Operations {

	public static boolean isLogin(String username, String password,String usertype) {
		try {
			Connection myConn = MySqlConnection.getConnection();
			String mySqlQuery = "SELECT UID, Usertype,Nickname FROM login WHERE Username = '"+username+"' AND Password = '"+password+"' AND Usertype = '"+usertype+"';";
					
			PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				LoginSession.UID = resultSet.getInt("UID");
				LoginSession.Usertype =resultSet.getString("Usertype");
				LoginSession.Nickname = resultSet.getString("Nickname");
				
				return true;
			}
			
		}catch(Exception exception) {
			JOptionPane.showMessageDialog(null, "Database error: "+exception.getMessage());
		}
		return false;
	}
}
