package Login;

import Dashboard.AdminDashboard;
import Dashboard.UserDashboard;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class LoginEvent {

	public JFrame frame;
	private JTextField UsernameText;
	private JTextField PasswordText;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginEvent window = new LoginEvent();
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
	public LoginEvent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 452, 339);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(10, 11, 414, 60);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 82, 416, 142);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		UsernameText = new JTextField();
		UsernameText.setBounds(106, 11, 300, 22);
		panel_1.add(UsernameText);
		UsernameText.setColumns(10);
		
		PasswordText = new JTextField();
		PasswordText.setBounds(106, 51, 300, 22);
		panel_1.add(PasswordText);
		PasswordText.setColumns(10);
		
		JComboBox<String> UserTypeCombo = new JComboBox<String>();
		UserTypeCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"Admin ", "User"}));
		UserTypeCombo.setBounds(106, 91, 300, 22);
		panel_1.add(UserTypeCombo);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(10, 15, 86, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(10, 55, 86, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Login as");
		lblNewLabel_3.setBounds(10, 95, 74, 14);
		panel_1.add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 235, 414, 54);
		frame.getContentPane().add(panel_2);
		
		JButton LoginBtn = new JButton("Login");
		LoginBtn.setText("Login");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String usernameStr = UsernameText.getText();
					String passwordStr = PasswordText.getText();
					String usertypeStr = UserTypeCombo.getSelectedItem().toString();
					
					if(Operations.isLogin(usernameStr,passwordStr,usertypeStr)) {
					
						if (usertypeStr.equals("User"))
							{
								new UserDashboard().frame.setVisible(true);
								frame.dispose();}	
						
						else  {
							
									new AdminDashboard().frame.setVisible(true);
									frame.dispose();}
									
				
						}
					
					 else {
							JOptionPane.showMessageDialog(frame, "Please type the correction Username or Password!");
						}
					
					
				}catch(Exception exception){
					JOptionPane.showMessageDialog(frame, "Please Type correct information");
					
				}
			}
		});
		
		
		JButton CloseBtn = new JButton("Close");
		CloseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(LoginBtn, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(CloseBtn, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(CloseBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
						.addComponent(LoginBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
