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



public class Settings extends BasicGameState 

{
	int id;
	
	Image background = null;
	
	Image hello = null;
	
	Image sliderBack = null;
	Image sliderClick = null;
	
	Button button;
	
	Slider popDensity;
	Slider rNaught;
	
	public static float rNaughtF;
	public static int popDensityF;
	
	
	public ArrayList<Slider> sliders;
	
	public ArrayList<Button> buttons;
	
	Settings(int id) 
	{
		this.id = id;
		buttons = new ArrayList<Button>();
		sliders = new ArrayList<Slider>();
		
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		
		hello = new Image("res/launchButton.png");
		hello.setFilter(Image.FILTER_NEAREST);
		
		background = new Image("res/settingBackgroundNEW.png");
		background.setFilter(Image.FILTER_NEAREST);
		
		button = new Button(50f,50f,200f,100f, hello);
		
		
		sliderBack = new Image("res/slider.png");
		sliderBack.setFilter(Image.FILTER_NEAREST);
		
		sliderClick = new Image("res/launchButton.png");
		sliderClick.setFilter(Image.FILTER_NEAREST);
		
		popDensity = new Slider(150f,900f,400f,15f, sliderClick, sliderBack, 6);
		
		rNaught = new Slider(1150f,800f,700f,15f, sliderClick, sliderBack, 53);
		
		buttons.add(button);
		sliders.add(popDensity);
		sliders.add(rNaught);
		
		popDensityF = 1;
		rNaughtF = 0;
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		background.draw(0,0,gc.getWidth(),gc.getHeight());
		
		for(Button b : buttons) {
			b.render(gc, g);
		}
		for(Slider b : sliders) {
			b.render(gc, g);
		}
		
		g.setColor(new Color(200,200,200));
		
		if (button.click()) {
			g.drawRect(300, 300, 100, 100);
			sbg.enterState(2);
		}
		g.setColor(new Color(0,0,0));
		g.drawString("" + popDensityF, 175, 950);
		g.drawString("" + rNaughtF, 1175, 950);
		
	}
	


	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		for(Button b : buttons) {
			b.update(gc);
		}
		for(Slider b : sliders) {
			b.update(gc);
		}
		
		popDensityF = (int) popDensity.categorySelected();
		rNaughtF = rNaught.categorySelected()/5;
		System.out.println(rNaughtF);
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
