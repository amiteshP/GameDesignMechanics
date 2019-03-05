package com.mmorpg.drain.graphics;

import java.util.Random;

import com.mmorpg.drain.level.tile.Tile;

public class Screen {

	private int width;
	private int height;
	public int pixels[];

	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;

	// integer array to keep track of each tile
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // 4096 tiles

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		// colorize tiles randomly
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			// choose a random color for each tile
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	// randomize tile color again
	public void randomizeTileColor() {
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			// choose a random color for each tile
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			// tile movement
			int yp = y + yOffset;
			if (yp < 0 || yp >= height)
				continue;
			for (int x = 0; x < width; x++) {
				int xp = x + xOffset;
				if (xp < 0 || xp >= width)
					continue;
			
				 // declare single tile size(16X16 here) int tileIndex = (x/16) + (y/16) * 64;
				  //same in bitwise 
				int tileIndex = ( (xp>>4) & MAP_SIZE_MASK )+( (yp>>4) & MAP_SIZE_MASK ) * MAP_SIZE;
				
				pixels[(xp) + (yp) * width] = tiles[tileIndex];//Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];// 
			}
		}
	}

	// render tile to a particular position on screen
	public void renderTile(int xp, int yp, Tile tile) {
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			// set absolute position(relative to the entire game world)
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					break;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
}
