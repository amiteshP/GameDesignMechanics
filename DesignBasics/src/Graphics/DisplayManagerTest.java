package Graphics;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;

public class DisplayManagerTest extends JFrame{
	
	private static final long DEMO_TIME = 5000;
	
	public static void main(String args[]) {
		
		DisplayMode displayMode;
		
		if(args.length == 3) {
			displayMode = new DisplayMode(
								Integer.parseInt(args[0]),
								Integer.parseInt(args[1]),
								Integer.parseInt(args[2]),
								DisplayMode.REFRESH_RATE_UNKNOWN);
		}
		else {
			displayMode = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		}
		
		DisplayManagerTest test = new DisplayManagerTest();
		test.run(displayMode);
	}
	
	public void run(DisplayMode displayMode) {
		
		setBackground(Color.BLUE);
		setForeground(Color.WHITE);
		setFont(new Font("Dialog", Font.PLAIN, 24));
		
		DisplayManager dMag = new DisplayManager();
		try {
			dMag.setFullScreen(displayMode, this);
			try {
				Thread.sleep(DEMO_TIME);
			}
			catch(InterruptedException ex) {}
		}
		finally {
			dMag.restoreScreen();
		}
	}
	
	/**
	 * Graphics paint functionality with anti-aliasing
	 */
	public void paint(Graphics g) {
		if(g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
		g.drawString("Hey Jude! Welcome to the Realm of the Mad God!",  20,  50);
	}
}
