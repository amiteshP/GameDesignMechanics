package com.mmorpg.drain.level.tile;

import com.mmorpg.drain.graphics.Screen;
import com.mmorpg.drain.graphics.Sprite;

public class Tile {

	// define position of tile
	public int x, y;

	// attach Sprite to the tile
	public Sprite sprite;

	public static Tile grass = new GrassTile(Sprite.grass);

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	// render the tile
	public void render(int x, int y, Screen screen) {

	}

	// Tile is solid or not
	public boolean solid() {
		return false;
	}
}
