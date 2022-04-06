package core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
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
	public static float days;
	public static boolean isDay;
	
	public static float R_0, PERIOD, I_PER_C, INCUBATION;
	
	MapType mapType;
	ArrayList<Building> residences, workplaces;
	ArrayList<Person> people;
	
	Simulation(int id) 
	{
		this.id = id;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		
		
		///TEMPOERARRY:
		R_0 = 5.0f;
		PERIOD = 10;
		I_PER_C = R_0 / PERIOD / 7;
		INCUBATION = 3;
		
		
		time = 0;
		days = 0;
		isDay = false;
		
		mapType = MapType.GRID;
		
		residences = new ArrayList<Building>();
		workplaces = new ArrayList<Building>();
		people = new ArrayList<Person>();
		
		for (int i = 0; i < 6; i++) {
			residences.add(new Building(BuildingType.HOUSE,100 + 80 * i,200));
			residences.add(new Building(BuildingType.HOUSE,100 + 80 * i,300));
		}
//		for (int i = 0; i < 2; i++) {
//			residences.add(new Building(BuildingType.APARTMENT,500 + 250 * i, 800));
//		}
		
		for (int i = 0; i < 4; i++) {
			workplaces.add(new Building(BuildingType.WORKPLACE_SMALL,1500, 300 + 100 * i));
		}
//		workplaces.add(new Building(BuildingType.WORKPLACE_LARGE,1100,400));
		
		
		for (Building r : residences) {
			while (r.getLivingCapacity() > 0) {
				Building workplace = r;
				for (int i = 0; i < 5; i++) {
					Building w = workplaces.get((int)(Math.random() * workplaces.size()));
					if (w.getWorkingCapacity() > 0) {
						workplace = w;
						workplace.removeWorkingCapacity();
						break;
					}
				}
				Person person = new Person(r,workplace);	
				r.addResident(person);
				r.removeLivingCapacity();
				workplace.addWorker(person);
				people.add(person);
			}
		}
		
		people.get((int)(Math.random() * people.size())).infect();
		
//		switch(mapType) {
//		case GRID: 
//			
//		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.setBackground(new Color(45,45,45));
		
		g.setColor(new Color(0,155,155));
		g.drawString("Homes", 100, 175);
		g.setColor(new Color(33,134,222));
		g.drawString("Workplaces", 1500, 275);
		
		
		g.setColor(new Color(255,255,255));
		g.drawString("Days: " + days + "    R_naught: " + R_0 + "    Incubation time: " + INCUBATION + " days    Infection period: " + PERIOD, 30, 90);
		
		g.setColor(new Color(255,255,255));
		g.drawString("Susceptible: Not yet infected by disease", 30, 580);
		g.setColor(new Color(180,180,0));
		g.drawString("Infectious Asymptomatic: No current symptoms, spreads disease at a lower rate", 30, 660);
		g.setColor(new Color(255,0,0));
		g.drawString("Infectious Symptomatic: Clinical symptoms, spreads disease at a higher rate", 30, 740);
		g.setColor(new Color(0,0,0));
		g.drawString("Dead: Killed by disease", 30, 820);
		g.setColor(new Color(130,0,130));
		g.drawString("Recovered: Survived disease infection, cannot be reinfected", 30, 900);
		
		for (Building r : residences) {
			r.render(g);
		}
		for (Building w : workplaces) {
			w.render(g);
		}
		for (Person p : people) {
			p.render(g);
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		time++;
		if (time == 120) {
			time = 0;
			days+= 0.5;
			isDay = isDay ? false : true;
			for (Building r : residences) {
				r.update();
			}
			for (Building w : workplaces) {
				w.update();
			}
			for (Person p : people) {
				p.update();
			}
		}
		
//		if (Simulation.time / 1200 % 2 == 0) {
//			for (Building r : residences) {
//				r.update();
//			}
//		} else {
//			for (Building w : workplaces) {
//				w.update();
//			}
//		}
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
