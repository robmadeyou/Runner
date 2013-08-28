package com.gmail.robmadeyou;

import com.gmail.robmadeyou.Screen.GameType;

public class Main {
	
	public static void main(String args[]){
		
		Screen.create(800, 512, "Runner", GameType.SIDE_SCROLLER, false);
		Screen.toggleVSync();
		Screen.setWorldDimensionsInBlocks(100, 0);
		
		Screen.setUpWorld();
		
		Game.startGame();
		while(!Screen.isAskedToClose()){
			Screen.update(60);
			
			Game.loop();
			
			Screen.refresh();
		}
		Screen.destroy();
	}
	
}
