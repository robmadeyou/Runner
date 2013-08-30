package com.gmail.robmadeyou;

import java.util.ArrayList;
import java.util.Random;

import com.gmail.robmadeyou.Block.BlockAir;
import com.gmail.robmadeyou.Block.BlockStone;
import com.gmail.robmadeyou.Effects.Animate;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.TextureLoader;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.World.World;

public class Game {
	public static boolean isGameRunning = false;
	public static boolean isGameOver = false;
	public static boolean counterFinished = false;
	
	static Player player;
	
	static Animate activeAnimation;
	
	static int lastPlayerWidth = 0;
	static String direction = "right";
	
	static int counter = 0;
	static int counterMax = 20;
	
	static int currentX = 0;
	static int currentCounter;
	
	
	public static void init(){
		generateWorld();
		player = (Player) Engine.addEntity(new Player(64, Screen.getHeight() / 2, 32, 64));
		currentCounter = 3;
		activeAnimation = Graphics.standingR;
		new Thread(startCounter).start();
	}
	
	public static void generateWorld(){
		Random random = new Random();
		int currentHeight = 2;
		for(int i = 0; i < Screen.WorldWidth; i++){
			
			
			int chance = random.nextInt(5);
			if(chance == 0){
				boolean upOrDown = random.nextBoolean();
				System.out.println(upOrDown);
				if(upOrDown){
					if(currentHeight > 2){
						currentHeight--;
					}
				}else{
					if(currentHeight < 20){
						currentHeight++;
					}
				}
			}
			for(int j = 0; j < Screen.WorldHeight; j++){
				if(j < Screen.WorldHeight - currentHeight){
					World.blockList.setBlock(new BlockAir(i, j));
				}else{
					World.blockList.setBlock(new BlockStone(i, j));
				}
			}
		}
		
		for(int i = 0; i < Screen.WorldWidth; i++){
			/*
			* Do random generation here, not really the prettiest but eh, should do for now
			*/
			int option = random.nextInt(10);
			if(option == 1){
				World.blockList.setBlock(new BlockStone(i, Screen.WorldHeight - 5));
			}else if(option == 2){
				World.blockList.setBlock(new BlockStone(i, Screen.WorldHeight - 3));
				World.blockList.setBlock(new BlockStone(i, Screen.WorldHeight - 4));
			}
		}
	}
	/*
	 * Game loop where graphics and the world is being updated
	 */
	public static void loop(){
		if(!isGameOver){
			//Controls the world collapsing on the player
			Text.drawString(currentCounter  +"", Screen.getWidth() / 2, Screen.getHeight() / 2, Layer.GUILayer(), 1, 1, Color.Black, true, false);
			if(currentCounter == 0){
				counter++;
				if(counter >= counterMax){
					counter = 0;
					for(int i = 0; i < Screen.WorldHeight; i++){
						World.blockList.setBlock(new BlockStone(currentX, i));
					}
					currentX++;
				}
				if(player.getY() < 0){
					isGameOver = true;
					gameOver();
				}
			}
			/*
			 * Math to solve the different width of textures for player
			 */
			int thisPlayerWidth = TextureLoader.getTextureWidth(activeAnimation.getTextureID());
			int difference = thisPlayerWidth - lastPlayerWidth;
			
			player.setX(player.getX() - difference);
			player.setWidth(thisPlayerWidth);
			
			
			lastPlayerWidth = thisPlayerWidth;
			
			if(activeAnimation.getInverts()){
				player.setTextureInverts(true);
			}else{
				player.setTextureInverts(false);
			}
			player.setTexture(activeAnimation.getTextureID());
			
			if(Keyboard.isKeyDown(Keyboard.Key.LeftArrow)){
				direction = "left";
				activeAnimation = Graphics.runningL;
			}else if(Keyboard.isKeyDown(Keyboard.Key.RightArrow)){
				direction = "right";
				activeAnimation = Graphics.runningR;
			}else{
				if(direction.equals("right")){
					activeAnimation = Graphics.standingR;
				}else{
					activeAnimation = Graphics.standingL;
				}
			}
			
			
			
		}else{
			if(currentCounter == 0){
				counter++;
				counterMax--;
				if(counter >= counterMax){
					counter = 0;
					for(int i = 0; i < Screen.WorldHeight; i++){
						World.blockList.setBlock(new BlockStone(currentX, i));
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
