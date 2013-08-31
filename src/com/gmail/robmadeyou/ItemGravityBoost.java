package com.gmail.robmadeyou;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Emitter;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Item.Item;
import com.gmail.robmadeyou.World.World;

public class ItemGravityBoost extends Item{

	public ItemGravityBoost(double x, double y, int width, int height, int id,
			int Texture) {
		super(x, y, width, height, id, Texture);
	}
	
	public void onActivate(Player player){
		if(player != null){
			Engine.removeItem(this);
			World.gravityModifier /= 1.1;
			Game.message = "Graaaviiittyyyy!!!!";
			Emitter emit = Engine.addNewEmitter(new Emitter(this.getX(), this.getY(), 10, 0.02f, 1f, 2, Color.Red));
			emit.setRepeat(false);
		}
	}
	
	public void onUpdate(){
		gravityEffect();
		if(isPressed()){
			onActivate(Game.player);
		}
	}
}
