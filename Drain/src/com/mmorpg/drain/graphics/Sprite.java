package com.mmorpg.drain.graphics;

public class Sprite {

	// size of Sprite
	public final int SIZE;
	// location of Sprite in the Sprite sheet
	private int x, y;
	// array to hold the image
	public int[] pixels;
	// define which Spritesheet this Sprite is part of
	private SpriteSheet sheet;

	// Create sprite for Grass
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		// location in pixels will be x(or y) times 16(pixel size)
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}

	// access the SpriteSheet pixels and get the right Sprite
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
