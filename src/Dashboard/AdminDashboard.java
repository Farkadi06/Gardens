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
import javax.swing.JScrollPane;

import Login.LoginEvent;
import Login.LoginSession;
import Login.Logout;
import Login.MySqlConnection;
import Login.Operations;
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
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.JFormattedTextField;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.components.JSpinField;
import com.toedter.calendar.JCalendar;
import java.awt.FlowLayout;

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
	private JTextField NewUsername;
	private JTextField NewPassword;
	private JTextField textField;
	private JTable tableGardners;
	private JTable tablePlant;
	private JComboBox<String> GardnerNameComboBox;
	
	
	
	
	
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 103, 743, 633);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Affect Asignements", null, panel, null);
		panel.setBackground(new Color(220, 220, 220));
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
					preparedStatement.setString(1, GardnerNameComboBox.getSelectedItem().toString());
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
							preparedStatement.setString(5, GardnerNameComboBox.getSelectedItem().toString());
							
							
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
									
									
									preparedStatement.setString(1, GardnerNameComboBox.getSelectedItem().toString());
									
									
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
								btnShow.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										try {
											String mySqlQuerry = "SELECT * FROM `assignments`;";
											
											myConn = MySqlConnection.getConnection();
											preparedStatement = myConn.prepareStatement(mySqlQuerry);					
											resultSet = preparedStatement.executeQuery();

											table.setModel(DbUtils.resultSetToTableModel(resultSet));
											Operations.comboBoxData(GardnerNameComboBox);

											
										}catch(Exception exception) {
											JOptionPane.showMessageDialog(null,"Error: "+ exception);

											
										}
										
									}
								});
								
										
										btnShow.setBounds(190, 56, 104, 38);
										panel_1.add(btnShow);
										
										GardnerName = new JTextField();
										GardnerName.setBounds(111, 44, 250, 20);
										panel.add(GardnerName);
										GardnerName.setColumns(10);
										
										JPanel panel_2 = new JPanel();
										panel_2.setBounds(10, 335, 718, 259);
										panel.add(panel_2);
										panel_2.setBackground(new Color(192, 192, 192));
										panel_2.setLayout(null);
										
										JScrollPane scrollPane = new JScrollPane();
										scrollPane.setBounds(10, 11, 698, 237);
										panel_2.add(scrollPane);
										
										table = new JTable();
										table.setBorder(new CompoundBorder());
										scrollPane.setViewportView(table);
										
										GardnerNameComboBox = new JComboBox();
										GardnerNameComboBox.setBounds(371, 28, 132, 22);
										panel.add(GardnerNameComboBox);
										
										JPanel panel_3 =  new JPanel();
										tabbedPane.addTab("New Gardners and Plants", null, panel_3, null);
										panel_3.setLayout(null);
										
										JPanel panel_4 = new JPanel();
										panel_4.setBorder(new TitledBorder(null, "New Gardner", TitledBorder.CENTER, TitledBorder.TOP, null, null));
										panel_4.setBounds(10, 29, 718, 200);
										panel_3.add(panel_4);
										panel_4.setLayout(null);
										
										JLabel lblNewLabel_7 = new JLabel("Username : ");
										lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
										lblNewLabel_7.setBounds(25, 33, 84, 24);
										panel_4.add(lblNewLabel_7);
										
										NewUsername = new JTextField();
										NewUsername.setBounds(168, 36, 160, 20);
										panel_4.add(NewUsername);
										NewUsername.setColumns(10);
										
										JLabel lblNewLabel_7_1 = new JLabel("Password : ");
										lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
										lblNewLabel_7_1.setBounds(25, 80, 84, 24);
										panel_4.add(lblNewLabel_7_1);
										
										NewPassword = new JTextField();
										NewPassword.setBounds(168, 83, 160, 20);
										panel_4.add(NewPassword);
										NewPassword.setColumns(10);
										
										JPanel panel_5 = new JPanel();
										panel_5.setBounds(409, 11, 299, 111);
										panel_4.add(panel_5);
										panel_5.setLayout(null);
										
										JScrollPane scrollPane_1 = new JScrollPane();
										scrollPane_1.setBounds(10, 11, 279, 89);
										panel_5.add(scrollPane_1);
										
										tableGardners = new JTable();
										tableGardners.setModel(new DefaultTableModel(
											new Object[][] {
												{null, null},
												{null, null},
												{null, null},
												{null, null},
											},
											new String[] {
												"Gardners username", "Garners ID"
											}
										));
										scrollPane_1.setViewportView(tableGardners);
										
										JPanel panel_8 = new JPanel();
										panel_8.setBounds(25, 136, 671, 53);
										panel_4.add(panel_8);
										panel_8.setLayout(null);
										
										JButton InsertUser = new JButton("Insert ");
										InsertUser.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												
												try {
													String mySqlQuerry = "INSERT INTO `login`(`Username`, `Password`,`Usertype`, `Nickname`, `Email`) VALUES (?,?,'User','Test','Test@gmail.com');";
													myConn = MySqlConnection.getConnection();
													
													preparedStatement = myConn.prepareStatement(mySqlQuerry);
													preparedStatement.setString(1,NewUsername.getText());
													preparedStatement.setString(2, NewPassword.getText());
													
													preparedStatement.executeUpdate();

													JOptionPane.showMessageDialog(null, "Inserted Succesfully!");
													
												}catch(Exception exception) {
													JOptionPane.showMessageDialog(null,"Error: "+ exception);

													
												}
												
												
												
											}
										});
										InsertUser.setBounds(22, 11, 146, 31);
										panel_8.add(InsertUser);
										
										JButton btnShow_1 = new JButton("Show");
										btnShow_1.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												
												try {
													String mySqlQuerry = "SELECT `Username`,`UID` FROM `login` WHERE `Usertype` ='User';";
													
													myConn = MySqlConnection.getConnection();
													preparedStatement = myConn.prepareStatement(mySqlQuerry);					
													resultSet = preparedStatement.executeQuery();

													tableGardners.setModel(DbUtils.resultSetToTableModel(resultSet));
													
												}catch(Exception exception) {
													JOptionPane.showMessageDialog(null,"Error: "+ exception);

													
												}
												
											}
										});
										btnShow_1.setBounds(515, 11, 146, 31);
										panel_8.add(btnShow_1);
										
										JPanel panel_4_1 = new JPanel();
										panel_4_1.setLayout(null);
										panel_4_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "New Plant", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
										panel_4_1.setBounds(10, 240, 718, 354);
										panel_3.add(panel_4_1);
										
										JLabel lblNewLabel_7_2 = new JLabel("Plant name :");
										lblNewLabel_7_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
										lblNewLabel_7_2.setBounds(25, 33, 84, 24);
										panel_4_1.add(lblNewLabel_7_2);
										
										textField = new JTextField();
										textField.setColumns(10);
										textField.setBounds(170, 36, 160, 20);
										panel_4_1.add(textField);
										
										JPanel panel_6 = new JPanel();
										panel_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration Date :", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
										panel_6.setBounds(25, 79, 278, 180);
										panel_4_1.add(panel_6);
										panel_6.setLayout(null);
										
										JCalendar calendar = new JCalendar();
										calendar.setBounds(38, 23, 189, 146);
										panel_6.add(calendar);
										
										JPanel panel_7 = new JPanel();
										panel_7.setBounds(400, 54, 308, 210);
										panel_4_1.add(panel_7);
										panel_7.setLayout(null);
										
										JScrollPane scrollPane_2 = new JScrollPane();
										scrollPane_2.setBounds(10, 11, 288, 188);
										panel_7.add(scrollPane_2);
										
										tablePlant = new JTable();
										tablePlant.setModel(new DefaultTableModel(
											new Object[][] {
												{null, null},
											},
											new String[] {
												"Plant name", "Planting date"
											}
										));
										scrollPane_2.setViewportView(tablePlant);
		
		
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
		usernameAdm.setBounds(161, 11, 99, 14);
		panel.add(usernameAdm);
		
		
		adm.setBackground(new Color(192, 192, 192));
		adm.setForeground(Color.RED);
		adm.setBounds(161, 36, 99, 14);
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
