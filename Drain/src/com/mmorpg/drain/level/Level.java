package com.mmorpg.drain.level;

import com.mmorpg.drain.graphics.Screen;

/**
 * Primary Level class
 * 
 * @author ampathak
 *
 */
public class Level {

	// for Randomly Generating Levels
	protected int width, height;

	// To store Tile indexes
	protected int[] tiles;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}

	// load level from Path
	public Level(String path) {
		loadLevel(path);
	}

	// for random generation
	protected void generateLevel() {

	}

	// load level from a path
	private void loadLevel(String path) {

	}

	// update the level entities
	public void update() {

	}

	// to keep the day-night time cycle
	// or similar time based events
	private void time() {

	}

	// render the level
	public void render(int xScroll, int yScroll, Screen screen) {

	}
}
