package UserSide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;



import Login.MySqlConnection;

public class UserData {
	
	public static boolean isDataAvailable(String username) {
		try {
			Connection myConn = MySqlConnection.getConnection();
			String mySqlQuery = "SELECT `Zone`, `Plant`, `Watering`, `Fertlizing` FROM `assignments` WHERE `Gardner` = '"+username+"';";
					
			PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				UserTasks.Zones.add(resultSet.getString("Zone"));
				UserTasks.Plants.add(resultSet.getString("Plant"));
				UserTasks.Watering.add(resultSet.getInt("Watering"));
				UserTasks.Fertlizing.add(resultSet.getInt("Fertlizing"));
				UserTasks.isDone.add(false);
				return true;
			}
			
		}catch(Exception exception) {
			JOptionPane.showMessageDialog(null, "Database error: "+exception.getMessage());
		}
		return false;
	}
	
}
