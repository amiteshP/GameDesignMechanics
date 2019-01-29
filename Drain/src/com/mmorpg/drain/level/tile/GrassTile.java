package com.mmorpg.drain.level.tile;

import com.mmorpg.drain.graphics.Screen;
import com.mmorpg.drain.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
	}

	// render the tile
	public void render(int x, int y, Screen screen) {
		//call screen: render tile method
		screen.renderTile(x,  y,  this);
	}
}
