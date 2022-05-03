package core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import function.Button;
import function.Slider;


public class Intro extends BasicGameState 



{
	int id;
	
	Image hello = null;
	Image slider1 = null;
	

	
	Button button;
	Slider slider;
	Slider slider2;
	
	public ArrayList<Button> buttons;
	public ArrayList<Slider> sliders;
	
	Intro(int id) 
	{
		this.id = id;
		buttons = new ArrayList<Button>();
		sliders = new ArrayList<Slider>();
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		
		hello = new Image("res/Enter.png");
		hello.setFilter(Image.FILTER_NEAREST);
		
		slider1 = new Image("res/rectangle.png");
		slider1.setFilter(Image.FILTER_NEAREST);
		
		slider = new Slider(700f,600f,100f,15f, hello, slider1, 15);
		slider2 = new Slider(200f,500f,300f,20f, hello, slider1, 50);
		button = new Button(400f,200f,400f,200f, hello);
		
		sliders.add(slider);
		sliders.add(slider2);
		buttons.add(button);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		for(Button b : buttons) {
			b.render(gc, g);
		}
		
		for(Slider b : sliders) {
			b.render(gc, g);
		}
		
		g.setColor(new Color(200,200,200));
		
		if (button.click()) {
			g.drawRect(300, 300, 400, 400);
			sbg.enterState(1);
		}
		
	}
	


	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		for(Button b : buttons) {
			b.update(gc);
		}
		for(Slider b : sliders) {
			b.update(gc);
		}
	}

	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		
	}
	
	public void mousePressed(int buttonClick, int mx, int my)
	{
		for(Button b : buttons) {
			b.mousePressed(buttonClick, mx, my);
		}
	}

	// Returns the ID code for this game state
	public int getID() 
	{
		return id;
	}
}
