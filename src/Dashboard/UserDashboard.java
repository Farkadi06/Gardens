package Dashboard;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Login.LoginEvent;
import Login.LoginSession;
import Login.Logout;
import Login.MySqlConnection;
import UserSide.UserData;
import UserSide.UserTasks;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class UserDashboard {
	private JLabel UsernameUsr = new JLabel(".....");
	private JLabel Usr = new JLabel(".....");
	public JFrame frame;
	private static JPanel mPanelTasks;
	private JPanel mPanelInstructions;
	private JTable table;
	private JComboBox<Integer> mAsignmentsComboBox;
	
	Connection myConn = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDashboard window = new UserDashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Tasks method
	/*
	public static void TaskCreator() {
		
			List<JPanel> TaskPrototypes = new ArrayList<JPanel>(UserTasks.Zones.size());
			List<JLabel>  ZonesLabels = new ArrayList<JLabel>(UserTasks.Zones.size());
			List<JLabel>  PlantsLabels = new ArrayList<JLabel>(UserTasks.Plants.size());
			List<JLabel>  WateringsLabels = new ArrayList<JLabel>(UserTasks.Watering.size());
			List<JLabel>  FertlizingsLabels = new ArrayList<JLabel>(UserTasks.Fertlizing.size());

			
		for (int i= 0 ;i<UserTasks.Zones.size();i++) {
			TaskPrototypes.add(new JPanel());
			ZonesLabels.add(new JLabel(UserTasks.Zones.get(i)));
			PlantsLabels.add(new JLabel(UserTasks.Plants.get(i)));
			WateringsLabels.add(new JLabel(UserTasks.Watering.get(i).toString()));
			FertlizingsLabels.add( new JLabel(UserTasks.Fertlizing.get(i).toString()));
			
			
			TaskPrototypes.get(i).setForeground(UIManager.getColor("Button.foreground"));
			TaskPrototypes.get(i).setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			TaskPrototypes.get(i).setBounds(10, 56, 718, 91);
			mPanelTasks.add(TaskPrototypes.get(i));
			TaskPrototypes.get(i).setLayout(null);
			
			
			ZonesLabels.get(i).setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
			ZonesLabels.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			ZonesLabels.get(i).setBounds(10, 11, 110, 24);
			TaskPrototypes.get(i).add(ZonesLabels.get(i));
			
			
			PlantsLabels.get(i).setFont(new Font("Source Sans Pro", Font.ITALIC, 17));
			PlantsLabels.get(i).setBounds(20, 46, 150, 27);
			TaskPrototypes.get(i).add(PlantsLabels.get(i));
			
			JLabel lblNewLabel_4 = new JLabel("Watering/day : ");
			lblNewLabel_4.setBounds(203, 20, 117, 14);
			TaskPrototypes.get(i).add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Fertlizing/day :");
			lblNewLabel_5.setBounds(213, 54, 117, 14);
			TaskPrototypes.get(i).add(lblNewLabel_5);
			
			
			WateringsLabels.get(i).setFont(new Font("Tahoma", Font.BOLD, 13));
			WateringsLabels.get(i).setBounds(330, 19, 46, 14);
			TaskPrototypes.get(i).add(WateringsLabels.get(i));
			
			
			FertlizingsLabels.get(i).setFont(new Font("Tahoma", Font.BOLD, 13));
			FertlizingsLabels.get(i).setBounds(330, 54, 46, 14);
			TaskPrototypes.get(i).add(FertlizingsLabels.get(i));
			
		}
		
	}*/

	/**
	 * Create the application.
	 */
	public UserDashboard() {
		initialize();
		
		setIconImage();
		
		UsernameUsr.setText(LoginSession.Nickname);
		Usr.setText(LoginSession.Usertype);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 92, 743, 433);
		frame.getContentPane().add(tabbedPane);
		
		mPanelTasks = new JPanel();
		mPanelTasks.setForeground(UIManager.getColor("Button.foreground"));
		tabbedPane.addTab("Tasks", null, mPanelTasks, null);
		mPanelTasks.setLayout(null);
		
		JButton btnShowTasks = new JButton("Show tasks");
		btnShowTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					UserData.isDataAvailable(LoginSession.Nickname,table);
					UserData.comboBoxData(mAsignmentsComboBox,LoginSession.Nickname);
					
					
				}catch(Exception exp){
					JOptionPane.showMessageDialog(frame,"Error : "+ exp);	
				}
				
			}
		});
		btnShowTasks.setBounds(620, 11, 108, 34);
		mPanelTasks.add(btnShowTasks);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 718, 120);
		mPanelTasks.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Asignment ID", "Plant name", "Zone name", "Watering/day", "Fertlizing/day"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(89);
		table.getColumnModel().getColumn(4).setPreferredWidth(87);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(72, 11, 341, 71);
		mPanelTasks.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Asignment ID : ");
		lblNewLabel_2.setBounds(10, 26, 95, 14);
		panel.add(lblNewLabel_2);
		
		mAsignmentsComboBox = new JComboBox();
		mAsignmentsComboBox.setBounds(101, 22, 43, 22);
		panel.add(mAsignmentsComboBox);
		
		JButton btnNewButton = new JButton("Done");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String mySqlQuerry = "DELETE FROM `assignments` WHERE `AsignmentID` =?  ;";
					
					myConn = MySqlConnection.getConnection();
					preparedStatement = myConn.prepareStatement(mySqlQuerry);
					
					
					preparedStatement.setString(1, mAsignmentsComboBox.getSelectedItem().toString());
					
					
					preparedStatement.executeUpdate();

					JOptionPane.showMessageDialog(null, "Task Succesfully Done! : "+mAsignmentsComboBox.getName());
					
					UserData.isDataAvailable(LoginSession.Nickname, table);
					
				}catch(Exception exception) {
					JOptionPane.showMessageDialog(null,"Error: "+ exception);

					
				}
			}
		});
		btnNewButton.setBounds(206, 22, 89, 23);
		panel.add(btnNewButton);
		
		mPanelInstructions = new JPanel();
		tabbedPane.addTab("Instruction", null, mPanelInstructions, null);
		mPanelInstructions.setLayout(null);
	}

	private void setIconImage() {
		// TODO Auto-generated method stub

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/agriculture.png")));
		frame.setTitle("User Dashboard");
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 779, 575);
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
		
		
		UsernameUsr.setForeground(new Color(0, 128, 0));
		UsernameUsr.setBackground(new Color(192, 192, 192));
		UsernameUsr.setBounds(161, 11, 91, 14);
		panel.add(UsernameUsr);
		
		
		Usr.setBackground(new Color(192, 192, 192));
		Usr.setForeground(Color.RED);
		Usr.setBounds(161, 36, 91, 14);
		panel.add(Usr);
		
		

		
		JLabel LogoutUsr = new JLabel("");
		LogoutUsr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginEvent loginEvent = new LoginEvent(); 
				Logout.logOut(frame, loginEvent);
				
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/icons/logoutIcon.png")).getImage();
		LogoutUsr.setIcon(new ImageIcon(img));
		LogoutUsr.setBounds(701, 23, 32, 27);
		panel.add(LogoutUsr);
	}
}
