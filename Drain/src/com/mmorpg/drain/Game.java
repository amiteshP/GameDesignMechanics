package com.mmorpg.drain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.mmorpg.drain.graphics.Screen;
import com.mmorpg.drain.input.Keyboard;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	// specify window resolution specifics
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;

	private JFrame frame;

	private Keyboard key;

	private Thread thread;

	// Create our instance of BufferedImage
	// This will help us store the current image and manipulate it later in pixel
	// wise fashion
	// We use original width and height rather than the scaled up, so as to give it
	// a retro effect.
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	// Creating a pixel array to store and manipulate bufferedImage contents
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	// to keep running the Game Loop
	private boolean running = false;

	private Screen screen;

	public static String title = "Drain";

	public Game() {
		// specify dimension of our canvas
		Dimension size = new Dimension(width * scale, height * scale);
		// set preferred dimension
		setPreferredSize(size);

		// to manipulate those pixels on the screen
		screen = new Screen(width, height);

		// initialize JFrame
		frame = new JFrame();

		// initialize keyboard
		key = new Keyboard();

		// Add our keyboard to our JFrame component
		addKeyListener(key);
	}

	// Starts the Game Thread
	public synchronized void start() {
		thread = new Thread(this, "display");
		running = true;
		thread.start();
	}

	// Stops the Game thread
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// Called by Runnable on start()
	public void run() {
		// game loop entry

		// variable to store time...
		long lastTime = System.nanoTime();
		// time var to track per sec for FPS
		long timer = System.currentTimeMillis();

		// so update is done 60 times a sec
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;

		// count how many frames we have time to render per sec
		int frames = 0;
		// how many times updates is called per sec
		int updates = 0;

		// set the focus to the screen on startup
		requestFocus();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			// render the game to the screen
			render();
			frames++;
			// every time timer hits one sec(1000ms == 1s)
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("Updates: UPS: " + updates + " Frames: FPS: " + frames);
				// just for display
				frame.setTitle(title + " | " + "UPS: " + updates + " FPS: " + frames);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	// offsets for map movement
	int x = 0, y = 0;

	public void update() {
		// randomize tile color
		screen.randomizeTileColor();

		// check the updates on keyboard set in each update loop
		key.update();
		// move up
		if (key.up)
			y--;
		// move down
		if (key.down)
			y++;
		// move left
		if (key.left)
			x--;
		// move right
		if (key.right)
			x++;
	}

	public void render() {
		// Creating and getting Buffer Strategy
		// Query?: why don't we do this once outside the Game loop?
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		// clear our previous screen remains
		screen.clear();
		// now render on to the blank screen
		screen.render(x, y);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		// Initializing Graphics
		// Create a link bw this bufferStrategy and the graphics context
		Graphics g = bs.getDrawGraphics();
		// set the custom color of graphics context
		g.setColor(new Color(80, 40, 100));
		// fill a rect, use default get width and height methods to
		// get the exact screen dimensions everytime
		g.fillRect(0, 0, getWidth(), getHeight());

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		// release all graphics resources
		g.dispose();

		// show this buffer's contents to the screen
		bs.show();

	}

	static public void main(String args[]) {

		Game game = new Game();

		// set resizable to false(error prone graphics otherwise)
		game.frame.setResizable(false);
		game.frame.setTitle(game.title);
		// add the "game" component of canvas(as it extends it) to fill the frame
		game.frame.add(game);
		// set our frame to the preferred size we have specified earlier
		game.frame.pack();
		// set default close
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set the frame location to centre
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		// starting the game...
		game.start();
	}
}
