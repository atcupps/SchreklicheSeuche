package core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.Tile.TileType;



public class Simulation extends BasicGameState 

{
	int id;
	
	public static int time;
	public static float days;
	public static boolean isDay;
	
	public static float R_0, PERIOD, I_PER_C, INCUBATION, D_PER_I;
	
	public static final int MAX_RESIDENTS = 5;
	public static final int MAX_WORKERS = 10;
	public static final int TICK = 50;
	public static final int tileX = 13;
	public static final int tileY = 7;
	
	private MapType mapType;
	private static Tile[][] tiles;
	private ArrayList<Person> people;
	private ArrayList<Tile> homes;
	private ArrayList<Tile> workplaces;
	private ArrayList<Tile> streets;
	
	private ArrayList<Integer> totalInfected, currentlyInfected, totalDeaths;
	
	Simulation(int id) 
	{
		this.id = id;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		
		
		///TEMPOERARRY:
		R_0 = 2.0f;
		PERIOD = 10;
		I_PER_C = R_0 / PERIOD / 7;
		D_PER_I = 0.2f;
		INCUBATION = 3;
		
		
		time = 0;
		days = 1;
		isDay = false;
		
		homes = new ArrayList<Tile>();
		workplaces = new ArrayList<Tile>();
		streets = new ArrayList<Tile>(); 
		
		
		mapType = MapType.GRID;
		tiles = new Tile[tileX][tileY];
		TileType t = Tile.TileType.STREET;
		for (int i = 0; i < tileX; i++) {
			for (int b = 0; b < tileY; b++) {
				if (i % 2 == 0 || b % 2 == 0) {
					t = Tile.TileType.STREET;
				} else {
					if (Math.random() * 10 > 6.5) {
						t = Tile.TileType.WORKPLACE;
					} else {
						t = Tile.TileType.HOME;
					}
				}
				tiles[i][b] = new Tile((gc.getWidth() * 2 / 3) - 30 - (100 * (6 - i)), (gc.getHeight() / 2) - (100 * (3 - b)), 100, 100, t, i, b);
				if (t.equals(Tile.TileType.STREET)) { streets.add(tiles[i][b]); }
				if (t.equals(Tile.TileType.WORKPLACE)) { workplaces.add(tiles[i][b]); }	
				if (t.equals(Tile.TileType.HOME)) { homes.add(tiles[i][b]); }
			}
		}
		
		people = new ArrayList<Person>();
		
		for (int i = 0; i < tileX; i++) {
			for (int b = 0; b < tileY; b++) {
				Tile tile = tiles[i][b];
				if (tile.getType().equals(Tile.TileType.HOME)) {
					while (tile.getResidents().size() < tile.getMaxPeople()) {
						Person p = new Person(tile);
						people.add(p);
						tile.addResident(p);
						tile.addPerson(p);
//						for (int g = 0; i < 5; i++) {
//							Tile workplace = workplaces.get((int)(Math.random() * workplaces.size()));
//							if (workplace.getWorkers().size() < workplace.getMaxPeople()) {
//								p.addWorkplace(workplace);
//								workplace.addWorker(p);
//								break;
//							}
//						}
					}
				}
			}
		}
		
		for (Tile w : workplaces) {
			for (int i = 0; i < people.size(); i++) {
				Person p = people.get((int)(Math.random() * people.size()));
				if (w.getWorkers().size() < w.getMaxPeople()) {
					if (!p.hasWorkplace()) {
						p.addWorkplace(w);
						w.addWorker(p);
					}
				}
				else break;
			}
		}
		
		people.get((int)(Math.random() * people.size())).infect();
	
		totalInfected = new ArrayList<Integer>();
		currentlyInfected = new ArrayList<Integer>();
		totalDeaths = new ArrayList<Integer>();
		
		totalInfected.add(1);
		currentlyInfected.add(1);
		totalDeaths.add(0);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.setBackground(new Color(45,45,45));
				
		g.setColor(new Color(255,255,255));
		g.drawString("Day " + days + " at " + (time / (TICK)) + ":00 " + "    R_naught: " + R_0 + "    Incubation time: " + INCUBATION + " days    Infection period: " + PERIOD, 30, 90);
		g.setColor(new Color(255,255,255));
		g.drawString("Susceptible: Not yet infected by disease", 30, 105);
		g.setColor(new Color(180,180,0));
		g.drawString("Infectious Asymptomatic: No current symptoms, spreads disease at a lower rate", 30, 120);
		g.setColor(new Color(255,0,0));
		g.drawString("Infectious Symptomatic: Clinical symptoms, spreads disease at a higher rate", 30, 135);
		g.setColor(new Color(0,0,0));
		g.drawString("Dead: Killed by disease", 30, 150);
		g.setColor(new Color(130,0,130));
		g.drawString("Recovered: Survived disease infection, cannot be reinfected", 30, 165);
		
		
		for (int i = 0; i < tileX; i++) {
			for (int b = 0; b < tileY; b++) {
				tiles[i][b].render(g);
			}
		}
		for (Person p : people) {
			p.drawPerson(g);
		}
		

		g.setColor(new Color(238, 255, 130));
		g.drawLine(60, 200, 60, 480);
		g.drawLine(60, 480, 360, 480);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
//		time++;
//		if (time == 120) {
//			time = 0;
//			days+= 0.5;
//			isDay = isDay ? false : true;
//			for (Person p : people) {
//				p.update();
//			}
//		}
		
		time++;
		for (Person p : people) {
			p.updatePosition();
		}
		if (time % (TICK / 4) == 0) {
			for (Person p : people) {
				if (!p.getStatus().equals(Person.Status.DEAD)) {
					p.setNewTile();
				}
			}
		}
		if (time % TICK == 0) {
			for (int i = 0; i < tileX; i++) {
				for (int b = 0; b < tileY; b++) {
					tiles[i][b].updateInfectionsInTile();
				}
			}
			for (Person p : people) {
				if (!p.getStatus().equals(Person.Status.DEAD)) {
					p.update();
				}
			}
		}
		if (time % (24 * TICK) == 0) {
			days++;
			time = 0;
			int totalInf = 0;
			int curInf = 0;
			int totalDead = 0;
			for (Person p : people) {
				if (!p.getStatus().equals(Person.Status.SUSCEPTIBLE)) totalInf++;
				if (p.getStatus().equals(Person.Status.INFECTIOUS) || p.getStatus().equals(Person.Status.SYMPTOMATIC)) curInf++;
				if (p.getStatus().equals(Person.Status.DEAD)) totalDead++;
			}
			totalInfected.add(totalInf);
			currentlyInfected.add(curInf);
			totalDeaths.add(totalDead);
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

	public enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	
	public static int getTime() { return time; }
	public static Tile getTile(int i, int b) { return tiles[i][b]; }
	
	// Returns the ID code for this game state
	public int getID() 
	{
		return id;
	}
}
