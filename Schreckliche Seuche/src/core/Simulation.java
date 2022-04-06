package core;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.Building.BuildingType;



public class Simulation extends BasicGameState 

{
	int id;
	
	public static int time;
	
	MapType mapType;
	ArrayList<Building> residences, workplaces;
	
	Simulation(int id) 
	{
		this.id = id;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		
		time = 0;
		
		mapType = MapType.GRID;
		
		residences = new ArrayList<Building>();
		workplaces = new ArrayList<Building>();
		
		for (int i = 0; i < 5; i++) {
			residences.add(new Building(BuildingType.HOUSE,100 + 80 * i,300));
		}
//		for (int i = 0; i < 2; i++) {
//			residences.add(new Building(BuildingType.APARTMENT,500 + 250 * i, 800));
//		}
		
		for (int i = 0; i < 4; i++) {
			workplaces.add(new Building(BuildingType.WORKPLACE_SMALL,1500, 300 + 100 * i));
		}
		workplaces.add(new Building(BuildingType.WORKPLACE_LARGE,1100,400));
		
		
		for (Building r : residences) {
			if (r.getNumOccupants() < r.getCapacity()) {
				r.addOccupant();
				r.addPerson(new Person(r,null));
			}
		}
		
//		switch(mapType) {
//		case GRID: 
//			
//		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		for (Building r : residences) {
			r.render(g);
		}
		for (Building w : workplaces) {
			w.render(g);
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		time++;
	}

	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		
	}
	
	public enum MapType {
		GRID,
		CITY,
		SUBURBS,
		RURAL,
		MIXED
	}
	
	public enum Road {
		MAIN,
		SECONDARY,
		LOCAL
	}

	// Returns the ID code for this game state
	public int getID() 
	{
		return id;
	}
}