package function;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.Game;



public class Slider

{
	
	float x;
	float y;
	float height;
	float width;
	Image button = null;
	boolean clicked;
	int time;
	Image back = null;
	
	public Slider(float x, float y, float width, float height, Image button, Image back)
	{
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.button = button;
		clicked = false;
		time = 0;
		this.back = back;
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		
		button.setFilter(Image.FILTER_NEAREST);
		back.setFilter(Image.FILTER_NEAREST);
		
	}

	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		back.draw(x-(button.getWidth()*4), y + (button.getHeight()), width*4, height/4);
		button.draw(x, y, width, height);
		
		
	}
	


	public void update(GameContainer gc) throws SlickException
	{	
		
		if (time > 0) {
			time--;
		}
		if (time == 0) {
			clicked = false;
		}
		if (selected()) {
			x = Mouse.getX();
			System.out.println(x);
		}
	}

	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		
	}
	
	public boolean click() {
		return clicked;
		
	}
	
	public boolean selected() {
		if(Mouse.isButtonDown(0)) {
			
			if (Mouse.getX() > x && Mouse.getX() < (x + width) && Mouse.getY() > y && Mouse.getY() < (y + height)) {
				System.out.println("segeg");
				return true;	
			}
		}
		return true;
	}
	
	public void mousePressed(int buttonClick, int mx, int my)
	{
//		if (buttonClick == Input.MOUSE_LEFT_BUTTON) {
//			if ( mx > x && mx < (x + width) && my > y && my < (y + height)) {
//				if (time == 0) {
//					time = 60;
//					clicked = true;
//					System.out.println("wtrg");
//				}
//			}
//		}
	}

	// Returns the ID code for this game state
//	public int getID() 
//	{
//		return id;
//	}
}
