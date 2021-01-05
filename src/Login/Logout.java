package Login;

import javax.swing.JFrame;

public class Logout {
	public static void logOut(JFrame context,LoginEvent loginScreen) {
		LoginSession.isLoggedIn = false;
		context.setVisible(false);
		loginScreen.frame.setVisible(true);
		
		
	}

}
