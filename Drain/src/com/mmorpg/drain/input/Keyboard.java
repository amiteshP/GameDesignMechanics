package com.mmorpg.drain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class for all keyboard listener events
 * @author ampathak
 *
 */
public class Keyboard  implements KeyListener{

	//"keys" stores each key on the keyboard
	//store the states as pressed, released
	private boolean[] keys = new boolean[120];	//limit of char array
	//boolean vars for storing key stroke
	public boolean up, down, left, right;
	
	//check if a particular key is pressed or released
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		
		/**for(int i=0; i<keys.length; i++) {
			if(keys[i]) {
				System.out.println("Key Pressed: "+ i);
			}
		}**/
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		//set the key pressed in the arrays of keys
		keys[arg0.getKeyCode()] = true;		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		//set the key released in the arrays of keys
		keys[arg0.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}

}
