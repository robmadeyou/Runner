package com.gmail.robmadeyou;

import java.util.Random;

import com.gmail.robmadeyou.Block.BlockAir;
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
	static int counterMax = 20;
	static int currentX = 0;
	static int currentCounter;
	
	public static void init(){
		generateWorld();
		player = (Player) Engine.addEntity(new Player(64, Screen.getHeight() / 2, 32, 64));
		currentCounter = 3;
		new Thread(startCounter).start();
	}
	
	public static void generateWorld(){
		Random random = new Random();
		
		for(int i = 0; i < Screen.WorldWidth; i++){
			for(int j = 0; j < Screen.WorldHeight; j++){
				if(j != Screen.WorldHeight - 1 && j != Screen.WorldHeight - 2){
					World.blockList[i][j] = new BlockAir(i, j);
				}else{
					World.blockList[i][j] = new BlockStone(i,j);
				}
			}
		}
		
		for(int i = 0; i < Screen.WorldWidth; i++){
			/*
			* Do random generation here, not really the prettiest but eh, should do for now
			*/
			int option = random.nextInt(10);
			if(option == 1){
				System.out.println("yaay");
				World.blockList[i][Screen.WorldHeight - 5] = new BlockStone(i, Screen.WorldHeight - 5);
			}else if(option == 2){
				World.blockList[i][Screen.WorldHeight - 3] = new BlockStone(i, Screen.WorldHeight - 3);
				World.blockList[i][Screen.WorldHeight - 4] = new BlockStone(i, Screen.WorldHeight - 4);
			}
		}
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
