package Graphics;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class DisplayManager {
	
	private GraphicsDevice device;
	
	public DisplayManager() {
		//getting the graphics device
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = environment.getDefaultScreenDevice();
				
	}
	
	/**
	 * Returns a list of compatible displayModes
	 */
	public DisplayMode[] getCompatibleDisplaymodes() {
		return device.getDisplayModes();
	}
	
	/**
	 * Returns first compatible displayMode, null otherwise.
	 */
	public DisplayMode findFirstCompatibleDisplayMode(DisplayMode modes[]) {
		DisplayMode goodModes[] = device.getDisplayModes();
		for(int i=0; i<modes.length; i++) {
			for(int j=0; j<goodModes.length; j++) {
				if(displayModesMatch(modes[i], goodModes[j])) {
					return modes[i];
				}
			}
		}
		return null;
	}
	
	//Return current display mOde
	public DisplayMode getCurrentDisplayMode() {
		return device.getDisplayMode();
	}
	
	//Compare two displays
	public boolean displayModesMatch(DisplayMode mode1, DisplayMode mode2) {
		if((mode1.getWidth()!=mode2.getWidth())|| (mode1.getHeight()!=mode2.getHeight()))
			return false;
		if(mode1.getBitDepth()!=DisplayMode.BIT_DEPTH_MULTI && mode2.getBitDepth()!=DisplayMode.BIT_DEPTH_MULTI && mode1.getBitDepth()!=mode2.getBitDepth())
				return false;
		if(mode1.getRefreshRate()!=DisplayMode.REFRESH_RATE_UNKNOWN && mode2.getRefreshRate()!=DisplayMode.REFRESH_RATE_UNKNOWN && mode1.getRefreshRate()!=mode2.getRefreshRate())
			return false;
		return true;
	}
	
	/**
	 * Enter full screen, change the display mode
	 * @param displayMode
	 * @param window
	 */
	public void setFullScreen(DisplayMode displayMode, JFrame window) {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setIgnoreRepaint(true);
		frame.setResizable(false);
		
		//set JFrame to full screen window
		device.setFullScreenWindow(frame);
		
		if(displayMode!=null && device.isDisplayChangeSupported()) {
			try {
				device.setDisplayMode(displayMode);
			}
			catch(IllegalArgumentException ex) {
				//illegal mode for this device
			}
		}
		frame.createBufferStrategy(2);
	}
	
	/**
	 * Gets the graphics context for the display
	 * @return
	 */
	public Graphics2D getGraphics() {
		Window window = device.getFullScreenWindow();
		if(window!=null) {
			BufferStrategy  bufferStrategy = window.getBufferStrategy();
			return (Graphics2D)bufferStrategy.getDrawGraphics();
		}
		return null;
	}
	
	
	/**
	 * Updates the display
	 */
	public void update() {
		Window window = device.getFullScreenWindow();
		if(window!=null) {
			BufferStrategy strategy = window.getBufferStrategy();
			if(!strategy.contentsLost()) {
				strategy.show();
			}
		}
		//syncs display
		Toolkit.getDefaultToolkit().sync();
	}
	
	//Returns the window currently used in full screen mode
	public Window getFullScreenWindow() {
		return device.getFullScreenWindow();
	}
	
	/**
	 * Returns the width of the current screen in full screen mode
	 */
	public int getWidth() {
		Window window = device.getFullScreenWindow();
		if(window!=null) {
			return window.getWidth();
		}
		return 0;
	}
	
	/**
	 * Returns the height of the current window in full screen mode
	 */
	public int getHeight() {
		Window window = device.getFullScreenWindow();
		if(window!=null) {
			return window.getHeight();
		}
		return 0;
	}
	
	
	/**
	 * Restore screen's display mode
	 */
	public void restoreScreen() {
		Window window = device.getFullScreenWindow();
		if(window!=null) {
			window.dispose();
		}
		device.setFullScreenWindow(null);
	}	
	
	/**
	 * Creates an image compatible with the display
	 */
	public BufferedImage createCompatibleImage(int w, int h, int transparency) {
		Window window = device.getFullScreenWindow();
		if(window!=null) {
			GraphicsConfiguration gc = window.getGraphicsConfiguration();
			return gc.createCompatibleImage(w,  h, transparency);
		}
		return null;
	}
}
























