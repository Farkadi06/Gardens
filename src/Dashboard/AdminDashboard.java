package Dashboard;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Login.LoginEvent;
import Login.LoginSession;
import Login.Logout;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminDashboard {

	public JFrame frame;
	private  final JLabel usernameAdm = new JLabel(".....");;
	private  final JLabel adm = new JLabel("....."); ;

	/**
	 * Launch the application.
	 */
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
