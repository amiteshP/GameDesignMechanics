package Graphics;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ImageManager extends JFrame{
	
	static public void main(String args[]) {
		
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
		
		ImageManager imageManager = new ImageManager();
		imageManager.run(displayMode);
		/**Toolkit toolKit = Toolkit.getDefaultToolkit();	
		//ImageIcon class uses the "mediaTracker" component to watch the image load.
		ImageIcon icon = new ImageIcon("/DesignBasics/Resources/allsrc/ch02src/images/player1.png");
		Image image = icon.getImage();**/
	}
	
	private static final int FONT_SIZE = 24;
	private static final int DEMO_TIME = 10000;
	private static final int FONT_STYLE = Font.BOLD;
	
	private static final String RESOURCE_PATH = "C:\\Users\\ampathak\\Documents\\My Documents\\GameDesignMechanics\\DesignBasics\\Resources\\allsrc\\ch02src\\images\\";
	
	private DisplayManager displayManager;
	private Image bgImage;
	private Image transparentImage;
	private boolean imagesLoaded;
	
	public void run(DisplayMode displayMode) {
		setBackground(Color.BLACK);
		setForeground(Color.BLUE);
		
		setFont(new Font("Dialog", FONT_STYLE, FONT_SIZE));
		
		imagesLoaded = false;
		
		displayManager = new DisplayManager();
		try {
			displayManager.setFullScreen(displayMode, this);
			loadImages();
			try {
				Thread.sleep(DEMO_TIME);
			}
			catch(InterruptedException ex) {}
		}
		finally {
			displayManager.restoreScreen();
		}
	}
	
	public void loadImages() {
		bgImage = loadImage(RESOURCE_PATH+"background.jpg");
		transparentImage = loadImage(RESOURCE_PATH+"transparent.png");
		imagesLoaded = true;
		//signal AWT to repaint this window
		repaint();
	}
	
	private Image loadImage(String fileName) {
		return new ImageIcon(fileName).getImage();
	}
	
	public void paint(Graphics g) {
		//set Text-Antialiasing
		if(g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
								RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
		//draw images
		if(imagesLoaded) {
			//Set bg image to full-screen
			g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
			drawImage(g, transparentImage, 500, 100, "Sticky Guy");
		}
		else {
			g.drawString("Loading Images...", 5, FONT_SIZE);
		}
	}
	
	public void drawImage(Graphics g, Image image, int x, int y, String caption) {
		g.drawImage(image,  x, y, null);
		g.drawString(caption,  x+5,  y+FONT_SIZE+image.getHeight(null));
	}
}














