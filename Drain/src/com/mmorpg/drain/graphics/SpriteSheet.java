package com.mmorpg.drain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class to manage spritesheets
 * 
 * @author ampathak
 *
 */
public class SpriteSheet {

	// path of spritesheet
	private String path;
	// size of sheet
	public final int SIZE;
	// hold our image
	public int[] pixels;

	// create spritesheet
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);

	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		this.pixels = new int[SIZE * SIZE];
		load();
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			// To deal with the image as a set of individual pixel rather than the whole
			// image
			int w = image.getWidth();
			int h = image.getHeight();
			// load image into pixels array
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
