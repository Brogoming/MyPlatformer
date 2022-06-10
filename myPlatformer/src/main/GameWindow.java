package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe;

	public GameWindow(GamePanel gamePanel) { //constructor

		jframe = new JFrame();
		
		jframe.setTitle("My platformer");

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the program when x is pressed
		jframe.add(gamePanel); //adds the panel to the window
		//jframe.setLocationRelativeTo(null); //put the window in the center of the screen
		jframe.setResizable(false); //we don't want to resize the window
		jframe.pack(); //tells jFrame to fit the size of the window to the panel
		jframe.setVisible(true); //shows the window, needs to be at the bottom
		jframe.addWindowFocusListener(new WindowFocusListener() { //when we lose and gain the game's focus

			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				gamePanel.getGame().windowFocusLost();
			}

			@Override
			public void windowLostFocus(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}
