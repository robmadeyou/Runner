package com.gmail.robmadeyou;

import com.gmail.robmadeyou.Screen.GameType;

public class Main {
	
	public static void main(String args[]){
		
		Screen.create(800, 512, "Runner", GameType.SIDE_SCROLLER, false);
		
		Screen.setWorldDimensionsInBlocks(1000, 40);
		
		Screen.setUpWorld();
		
		Graphics.setUpAnimations();
		Game.startGame();
		
		while(!Screen.isAskedToClose()){
			Screen.update(60);
			
			Game.loop();
			
			Screen.refresh();
		}
		Screen.destroy();
	}
	
}
