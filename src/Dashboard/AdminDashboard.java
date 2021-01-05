package Dashboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Login.LoginEvent;
import Login.LoginSession;
import Login.Logout;
import Login.MySqlConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSplitPane;

public class AdminDashboard {

	public JFrame frame;
	private  final JLabel usernameAdm = new JLabel(".....");;
	private  final JLabel adm = new JLabel("....."); 
	private JTextField PlantName;
	private JTextField GardnerName;;

	Connection myConn = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	private JTable table;
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboard window = new AdminDashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminDashboard() {
		initialize();
		
		adm.setText(LoginSession.Usertype);
		usernameAdm.setText(LoginSession.Nickname);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(10, 104, 743, 357);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Gardner  :");
		lblNewLabel_2.setBounds(21, 47, 93, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Zone : ");
		lblNewLabel_3.setBounds(21, 85, 93, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Plant : ");
		lblNewLabel_4.setBounds(21, 128, 93, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Watering/day :");
		lblNewLabel_5.setBounds(460, 66, 93, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Fertlizing/day :");
		lblNewLabel_6.setBounds(460, 110, 93, 14);
		panel.add(lblNewLabel_6);
		
		PlantName = new JTextField();
		PlantName.setBounds(111, 125, 250, 20);
		panel.add(PlantName);
		PlantName.setColumns(10);
		
		JComboBox ZoneNameComboBox = new JComboBox();
		ZoneNameComboBox.setModel(new DefaultComboBoxModel(new String[] {"Zone A.1", "Zone A.2", "Zone A.3", "Zone B.1", "Zone B.2", "Zone B.3", "Zone C.1 ", "Zone C.2"}));
		ZoneNameComboBox.setBounds(111, 81, 250, 22);
		panel.add(ZoneNameComboBox);
		
		JSpinner NbrWatering = new JSpinner();
		NbrWatering.setBounds(581, 63, 83, 20);
		panel.add(NbrWatering);
		
		JSpinner NbrFertlizing = new JSpinner();
		NbrFertlizing.setBounds(581, 107, 83, 20);
		panel.add(NbrFertlizing);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(216, 204, 304, 105);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnAdd = new JButton("Insert");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String mySqlQuerry = "INSERT INTO `assignments"
							+ "`(`Gardner`, `Zone`, `Plant`, `Watering`, `Fertlizing`)"
							+ " VALUES (?,?,?,?,?);";
					myConn = MySqlConnection.getConnection();
					
					preparedStatement = myConn.prepareStatement(mySqlQuerry);
					preparedStatement.setString(1, GardnerName.getText());
					preparedStatement.setString(2, ZoneNameComboBox.getSelectedItem().toString());
					preparedStatement.setString(3, PlantName.getText());
					preparedStatement.setString(4, NbrWatering.getValue().toString());
					preparedStatement.setString(5, NbrFertlizing.getValue().toString());
					
					preparedStatement.executeUpdate();

					JOptionPane.showMessageDialog(null, "Inserted Succesfully!");
					
				}catch(Exception exception) {
					JOptionPane.showMessageDialog(null,"Error: "+ exception);

					
				}
				
			}
		});


		btnAdd.setBounds(10, 11, 89, 38);
		panel_1.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(190, 11, 104, 38);
		panel_1.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String mySqlQuerry = "UPDATE `assignments` SET `Zone`=? ,`Plant`=? ,`Watering`=? ,`Fertlizing`=? WHERE Gardner =? ;";
					
					myConn = MySqlConnection.getConnection();
					preparedStatement = myConn.prepareStatement(mySqlQuerry);
					
					preparedStatement.setString(1, ZoneNameComboBox.getSelectedItem().toString());
					preparedStatement.setString(2, PlantName.getText());
					preparedStatement.setString(3, NbrWatering.getValue().toString());
					preparedStatement.setString(4, NbrFertlizing.getValue().toString());
					preparedStatement.setString(5, GardnerName.getText());
					
					
					preparedStatement.executeUpdate();

					JOptionPane.showMessageDialog(null, "Updated Succesfully!");
					
				}catch(Exception exception) {
					JOptionPane.showMessageDialog(null,"Error: "+ exception);

					
				}
				
				
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String mySqlQuerry = "DELETE FROM `assignments` WHERE Gardner =?  ;";
					
					myConn = MySqlConnection.getConnection();
					preparedStatement = myConn.prepareStatement(mySqlQuerry);
					
					
					preparedStatement.setString(1, GardnerName.getText());
					
					
					preparedStatement.executeUpdate();

					JOptionPane.showMessageDialog(null, "Deleted Succesfully!");
					
				}catch(Exception exception) {
					JOptionPane.showMessageDialog(null,"Error: "+ exception);

					
				}
				
			}
		});

		
		btnDelete.setBounds(10, 56, 89, 38);
		panel_1.add(btnDelete);
		
		JButton btnShow = new JButton("Show");
		
		btnShow.setBounds(190, 56, 104, 38);
		panel_1.add(btnShow);
		
		GardnerName = new JTextField();
		GardnerName.setBounds(111, 44, 250, 20);
		panel.add(GardnerName);
		GardnerName.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setBounds(10, 470, 743, 266);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 11, 723, 244);
		panel_2.add(table);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 779, 786);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(10, 11, 743, 68);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName : ");
		lblNewLabel.setBackground(new Color(192, 192, 192));
		lblNewLabel.setBounds(26, 11, 114, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usertype : ");
		lblNewLabel_1.setBackground(new Color(192, 192, 192));
		lblNewLabel_1.setBounds(26, 36, 114, 14);
		panel.add(lblNewLabel_1);
		
	
		usernameAdm.setForeground(new Color(0, 128, 0));
		usernameAdm.setBackground(new Color(192, 192, 192));
		usernameAdm.setBounds(161, 11, 46, 14);
		panel.add(usernameAdm);
		
		
		adm.setBackground(new Color(192, 192, 192));
		adm.setForeground(Color.RED);
		adm.setBounds(161, 36, 46, 14);
		panel.add(adm);
		
		JLabel LogoutAdm = new JLabel("");
		LogoutAdm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginEvent loginEvent = new LoginEvent();
				Logout.logOut(frame, loginEvent);
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/icons/logoutIcon.png")).getImage();
		LogoutAdm.setIcon(new ImageIcon(img));
		LogoutAdm.setBounds(701, 23, 32, 27);
		panel.add(LogoutAdm);
	}
}
