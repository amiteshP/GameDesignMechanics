package Graphics;

import java.awt.Image;
import java.util.ArrayList;

public class AnimationManager {
	private ArrayList frames;
	private int currFrameIndex;
	private long animTime;
	private long totalDuration;
	
	public AnimationManager(){
		frames = new ArrayList();
		totalDuration = 0;
		start();
	}
	
	//add an image to the animation list with specified time
	public synchronized void addFrame(Image image, long duration) {
		totalDuration+=duration;
		frames.add(new AnimationFrame(image, totalDuration));
	}
	
	//Starts animation from beginning and over
	public synchronized void start() {
		animTime = 0;
		currFrameIndex = 0;
	}
	
	//Updates current frames animation image(if needed)
	public synchronized void update(long elapsedTime) {
		if(frames.size()>1) {
			animTime+=elapsedTime;
			
			if(animTime>=totalDuration) {
				animTime = animTime%totalDuration;
				currFrameIndex = 0;
			}
			
			while(animTime>getFrame(currFrameIndex).endTime) {
				currFrameIndex++;
			}
		}
	}
	
	//return animation frame's current iamge
	public synchronized Image getImage() {
		if(frames.size() ==0) {
			return null;
		}
		else
			return getFrame(currFrameIndex).image;
	}
	
	private AnimationFrame getFrame(int i) {
		return (AnimationFrame)frames.get(i);
	}
	
	private class AnimationFrame {
		Image image;
		long endTime;
		
		public AnimationFrame(Image image, long endTime) {
			this.image = image;
			this.endTime = endTime;
		}
	}
}
