package Graphics;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class AnimationTest {
	static public void main(String args[]) {
		AnimationTest animTest = new AnimationTest();
		animTest.run();
	}
	
	private static final DisplayMode POSSIBLE_MODES[] = {
			new DisplayMode(1920, 1080, 32, 0),
			new DisplayMode(800, 600, 32, 0),
			new DisplayMode(800, 600, 24, 0),
			new DisplayMode(800, 600, 16, 0),
			new DisplayMode(640, 480, 32, 0),
			new DisplayMode(640, 480, 24, 0),
			new DisplayMode(640, 480, 16, 0)
	};
	
	private static final long DEMO_TIME = 5000;
	
	private DisplayManager displayManager;
	private Image bgImage;
	private AnimationManager animManager;
	
	private static final String RESOURCE_PATH = "C:\\Users\\ampathak\\Documents\\My Documents\\GameDesignMechanics\\DesignBasics\\Resources\\allsrc\\ch02src\\images\\";
	
	public void loadImages() {
		bgImage = loadImage(RESOURCE_PATH+"background.jpg");
		Image player1 = loadImage(RESOURCE_PATH+"player1.png");
		Image player2 = loadImage(RESOURCE_PATH+"player2.png");
		Image player3 = loadImage(RESOURCE_PATH+"player3.png");
		
		//create Animation
		animManager = new AnimationManager();
		animManager.addFrame(player1, 250);
		animManager.addFrame(player2, 150);
		animManager.addFrame(player1, 150);
		animManager.addFrame(player2, 150);
		animManager.addFrame(player3, 200);
		animManager.addFrame(player2, 150);
		
	}
	
	private Image loadImage(String fileName) {
		return new ImageIcon(fileName).getImage();
	}
	
	public void run() {
		displayManager = new DisplayManager();
		try {
			DisplayMode displayMode = displayManager.findFirstCompatibleDisplayMode(POSSIBLE_MODES);
			displayManager.setFullScreen(displayMode, new JFrame());
			loadImages();
			animationLoop();
		}
		finally {
			displayManager.restoreScreen();
		}
	}
	
	public void animationLoop() {
		long startTime = System.currentTimeMillis();
		long currTime = startTime;
		
		while(currTime-startTime<DEMO_TIME) {
			long elapsedTime = System.currentTimeMillis() - currTime;
			currTime+=elapsedTime;
			
			animManager.update(elapsedTime);
			
			Graphics2D g = displayManager.getGraphics();
			draw(g);
			g.dispose();
			
			try {
				Thread.sleep(20);
			}
			catch(InterruptedException ex) {}
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(bgImage, 0,  0, null);
		g.drawImage(animManager.getImage(),  0,  0,  null);
	}
	
}


















