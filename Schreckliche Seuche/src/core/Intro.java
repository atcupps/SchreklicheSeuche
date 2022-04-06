package core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Function.Button;
import Function.Slider;


public class Intro extends BasicGameState 



{
	int id;
	
	Image hello = null;
	
	Button button;
	Slider slider;
	
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
		
		hello = new Image("res/rectangle.png");
		hello.setFilter(Image.FILTER_NEAREST);
		
		slider = new Slider(400f,400f,100f,100f, hello);
		button = new Button(100f,100f,100f,100f, hello);
		
		sliders.add(slider);
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
			//TEMPORARY
			sbg.enterState(2);
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
