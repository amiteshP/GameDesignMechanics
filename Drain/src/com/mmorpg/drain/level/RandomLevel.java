package com.mmorpg.drain.level;

import java.util.Random;

public class RandomLevel extends Level {

	private final Random random = new Random();

	public RandomLevel(int width, int height) {
		super(width, height);

	}

	// generate the random level
	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// generate out of 4 values randomly
				tiles[x + y * width] = random.nextInt(4);
			}
		}
	}

}
