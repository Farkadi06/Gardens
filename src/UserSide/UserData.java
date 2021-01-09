package UserSide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Login.LoginSession;
import Login.MySqlConnection;
import net.proteanit.sql.DbUtils;

public class UserData {
	private static Connection myConn;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	
	public static void isDataAvailable(String username, JTable table) {
		try {
			
			myConn = MySqlConnection.getConnection();
			String mySqlQuery = "SELECT  `AsignmentID` ,`Zone`, `Plant`, `Watering`, `Fertlizing` FROM `assignments` WHERE `Gardner` = '"+LoginSession.Nickname+"';";
					
			preparedStatement = myConn.prepareStatement(mySqlQuery);
			resultSet = preparedStatement.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
			
			
		}catch(Exception exception) {
			JOptionPane.showMessageDialog(null, "Database error: "+exception.getMessage());
		}
		
	}
	
	
	public static void comboBoxData( JComboBox<Integer> AsignmentsComboBox,String username ) {
		try {
			
			myConn = MySqlConnection.getConnection();
			String mySqlQuery = "SELECT  `AsignmentID` ,`Zone`, `Plant`, `Watering`, `Fertlizing` FROM `assignments` WHERE `Gardner` = '"+username+"';";
					
			preparedStatement = myConn.prepareStatement(mySqlQuery);
			resultSet = preparedStatement.executeQuery();

			AsignmentsComboBox.removeAllItems();
			
			while(resultSet.next()) {
				AsignmentsComboBox.addItem(resultSet.getInt("AsignmentID"));
			}
			
		}catch(Exception exception) {
			JOptionPane.showMessageDialog(null, "Database error: "+exception.getMessage());
		}
	}
	
}
