package core;

import org.newdawn.slick.Graphics;

public class Person {

	private Status status;
	private Building home, workplace;
	
	public Person(Building home, Building workplace) {
		status = Status.SUSCEPTIBLE;
		this.home = home;
		this.workplace = workplace;
	}
	
	public void update() {
		if (Simulation.time / 1200 % 2 == 0) {
			System.out.println("Day");
		} else {
			System.out.println("Night");
		}
	}
	
	public void render(Graphics g) {
		
	}
	
	public enum Status {
		SUSCEPTIBLE,
		EXPOSED,
		INFECTIOUS,
		DEAD
	}
}
