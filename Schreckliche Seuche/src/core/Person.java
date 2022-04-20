package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Person {

	private Status status;
	private Building curBuilding, home, workplace;
	boolean isDay;
	private int halfDaysInfected;
	
	public Person(Building home, Building workplace) {
		status = Status.SUSCEPTIBLE;
		isDay = true;
		this.home = home;
		this.workplace = workplace;
		
		halfDaysInfected = 0;
	}
	
	public void update() {
		if (status.equals(Status.INFECTIOUS) || status.equals(Status.SYMPTOMATIC)) {
			halfDaysInfected++;
		}
		if (halfDaysInfected > Simulation.INCUBATION * 2) {
			status = Status.SYMPTOMATIC;
		}
		if (halfDaysInfected > Simulation.PERIOD * 2) {
			status = Status.RECOVERED;
		}
	}
	
	public void drawPerson(Graphics g, float x, float y) {
		switch(status) {
		case SUSCEPTIBLE:
			g.setColor(new Color(255,255,255));
			break;
		case INFECTIOUS:
			g.setColor(new Color(180,180,0));
			break;
		case SYMPTOMATIC:
			g.setColor(new Color(255,0,0));
			break;
		case DEAD:
			g.setColor(new Color(0,0,0));
			break;
		case RECOVERED:
			g.setColor(new Color(130,0,130));
		}
		g.fillOval(x, y, 9, 9);
	}
	
	public void render(Graphics g) {
//		if (status.equals(Status.SUSCEPTIBLE)) {
//			g.setColor(new Color(255,255,255,40));
//			g.fillOval(home.getCenter()[0] - 15, home.getCenter()[1] - 15, 30, 30);
//		} else
//		if (status.equals(Status.INFECTIOUS)) {
//			g.setColor(new Color(100,100,0,40));
//			g.fillOval(home.getCenter()[0] - 15, home.getCenter()[1] - 15, 30, 30);
//		} else
//		if (status.equals(Status.SYMPTOMATIC)) {
//			g.setColor(new Color(255,0,0,40));
//			g.fillOval(home.getCenter()[0] - 15, home.getCenter()[1] - 15, 30, 30);
//		}
	}
	
	public void infect() {
		status = Status.INFECTIOUS;
	}
	
	public enum Status {
		SUSCEPTIBLE,
		INFECTIOUS,
		SYMPTOMATIC,
		DEAD,
		RECOVERED
	}
	
	public Status getStatus() {
		return status;
	}
}