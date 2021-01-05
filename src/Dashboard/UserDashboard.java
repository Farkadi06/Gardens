package Dashboard;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Login.LoginEvent;
import Login.LoginSession;
import Login.Logout;


import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserDashboard {
	private JLabel UsernameUsr = new JLabel(".....");
	private JLabel Usr = new JLabel(".....");
	public JFrame frame;

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

	/**
	 * Create the application.
	 */
	public UserDashboard() {
		initialize();
		
		UsernameUsr.setText(LoginSession.Nickname);
		Usr.setText(LoginSession.Usertype);
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
