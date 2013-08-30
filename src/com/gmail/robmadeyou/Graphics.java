package com.gmail.robmadeyou;

import java.util.ArrayList;

import com.gmail.robmadeyou.Effects.Animate;
import com.gmail.robmadeyou.Effects.TextureLoader;


public class Graphics {
	/*
	 * Static graphics
	 */
	
	
	
	/*
	 * Animations!
	 */
	
	static Animate standingR;
	static Animate standingL;
	
	static Animate walkingR;
	static Animate walkingL;
	static Animate runningR;
	static Animate runningL;
	static Animate crouchR;
	static Animate crouchL;
	
	
	public static void setUpAnimations(){
		
		ArrayList<Integer> listStanding = new ArrayList<Integer>();
		listStanding.add(TextureLoader.createTexture("res/player/standing.png", 0, 0, 32, 39));
		listStanding.add(TextureLoader.createTexture("res/player/standing.png", 34, 0, 33, 39));
		listStanding.add(TextureLoader.createTexture("res/player/standing.png", 69, 0, 33, 39));
		listStanding.add(TextureLoader.createTexture("res/player/standing.png", 104, 0, 33, 39));
		listStanding.add(TextureLoader.createTexture("res/player/standing.png", 139, 0, 33, 39));
		listStanding.add(TextureLoader.createTexture("res/player/standing.png", 174, 0, 33, 39));
		listStanding.add(TextureLoader.createTexture("res/player/standing.png", 209, 0, 32, 39));
		
			
		standingR = new Animate(listStanding, 12, 0, true, false);
		standingL = new Animate(listStanding, 12, 0, true, true);
		
		ArrayList<Integer> listRunning = new ArrayList<Integer>();
		listRunning.add(TextureLoader.createTexture("res/player/running.png", 0, 0, 27, 37));
		listRunning.add(TextureLoader.createTexture("res/player/running.png", 28, 0, 33, 37));
		listRunning.add(TextureLoader.createTexture("res/player/running.png", 63, 0, 43, 37));
		listRunning.add(TextureLoader.createTexture("res/player/running.png", 109, 0, 33, 37));
		listRunning.add(TextureLoader.createTexture("res/player/running.png", 146, 0, 28, 37));
		listRunning.add(TextureLoader.createTexture("res/player/running.png", 175, 0, 27, 37));
		listRunning.add(TextureLoader.createTexture("res/player/running.png", 203, 0, 26, 37));
		
		runningR = new Animate(listRunning, 5, 0, true);
		runningL = new Animate(listRunning, 5, 0, true, true);
		
	}
}
