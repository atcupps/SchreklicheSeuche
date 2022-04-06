package core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Building {

	private BuildingType bt;
	private int capacity, curOccupants;
	private ArrayList<Person> occupants;
	private float x, y;
	
	public Building(BuildingType bt, float x, float y) {
		this.bt = bt;
		this.x = x;
		this.y = y;
		switch(bt) {
		case HOUSE:
			capacity = 3;
			break;
		case APARTMENT:
			capacity = 160;
			break;
		case WORKPLACE_SMALL:
			capacity = 10;
			break;
		case WORKPLACE_LARGE:
			capacity = 80;
			break;
		}
		
		occupants = new ArrayList<Person>();
	}
	
	public void render(Graphics g) {
		switch(bt) {
		case HOUSE: 
			g.setColor(new Color(255,255,255));
			g.fillRect(x, y, 70, 70);
			break;
		case APARTMENT:
			g.setColor(new Color(233,48,20));
			g.fillRect(x, y, 200, 130);
			break;
		case WORKPLACE_SMALL:
			g.setColor(new Color(33,134,222));
			g.fillRect(x, y, 70, 70);
			break;
		case WORKPLACE_LARGE:
			g.setColor(new Color(35,32,255));
			g.fillRect(x, y, 200, 130);
			break;
		}
	}
	
	public void addPerson(Person person) {
		occupants.add(person);
	}
	
	public void addOccupant() {
		curOccupants++;
	}
	
	public int getNumOccupants() {
		return curOccupants;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public enum BuildingType {
		HOUSE,
		APARTMENT,
		WORKPLACE_SMALL,
		WORKPLACE_LARGE
	}
	
}
