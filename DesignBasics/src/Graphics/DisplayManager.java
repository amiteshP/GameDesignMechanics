package Graphics;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

import javax.swing.JFrame;

public class DisplayManager {
	
	private GraphicsDevice device;
	
	public DisplayManager() {
		//getting the graphics device
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = environment.getDefaultScreenDevice();
				
	}
	
	/**
	 * Enter full screen, change the display mode
	 * @param displayMode
	 * @param window
	 */
	public void setFullScreen(DisplayMode displayMode, JFrame window) {
		window.setUndecorated(true);
		window.setResizable(false);
		
		//set JFrame to full screen window
		device.setFullScreenWindow(window);
		
		if(displayMode!=null && device.isDisplayChangeSupported()) {
			try {
				device.setDisplayMode(displayMode);
			}
			catch(IllegalArgumentException ex) {
				//illegal mode for this device
			}
		}
	}
	
	//Returns the window currently used in full screen mode
	public Window getFullScreenWindow() {
		return device.getFullScreenWindow();
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
}
