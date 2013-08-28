package com.gmail.robmadeyou;

import com.gmail.robmadeyou.Block.BlockStone;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.World.World;

public class Game {
	public static boolean isGameRunning = false;
	public static boolean isGameOver = false;
	public static boolean counterFinished = false;
	
	static Player player;
	
	static int counter = 0;
	static int counterMax = 50;
	static int currentX = 0;
	static int currentCounter;
	
	public static void init(){
		player = (Player) Engine.addEntity(new Player(64, 0, 32, 64));
		currentCounter = 3;
		new Thread(startCounter).start();
	}
	
	public static void loop(){
		if(!isGameOver){
			Text.drawString(currentCounter  +"", Screen.getWidth() / 2, Screen.getHeight() / 2, Layer.GUILayer(), 1, 1, Color.Black, true, false);
			if(currentCounter == 0){
				counter++;
				if(counter >= counterMax){
					counter = 0;
					for(int i = 0; i < Screen.WorldHeight; i++){
						World.blockList[currentX][i] = new BlockStone(currentX, i);
					}
					currentX++;
				}
				if(player.getY() < 0){
					isGameOver = true;
					gameOver();
				}
			}
		}else{
			if(currentCounter == 0){
				counter++;
				counterMax--;
				if(counter >= counterMax){
					counter = 0;
					for(int i = 0; i < Screen.WorldHeight; i++){
						World.blockList[currentX][i] = new BlockStone(currentX, i);
					}
					currentX++;
				}
			}
		}
	}
	public static void gameOver(){
		player = null;
	}
	public static void startGame(){
		init();
	}
	public static Runnable startCounter = new Runnable(){
		public void run() {
			while(currentCounter > 0){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				currentCounter--;
			}
		}
	};
}
