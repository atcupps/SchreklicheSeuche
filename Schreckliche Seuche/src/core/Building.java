package core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Building {

	private BuildingType bt;
	private int livingCapacity, workingCapacity, curOccupants;
	private ArrayList<Person> occupants, workers;
	private float x, y, w, h;
	
	public Building(BuildingType bt, float x, float y) {
		this.bt = bt;
		this.x = x;
		this.y = y;
		switch(bt) {
		case HOUSE:
			livingCapacity = 3;
			workingCapacity = 4;
			w = 70;
			h = 70;
			break;
		case APARTMENT:
			livingCapacity = 160;
			w = 200;
			h = 130;
			break;
		case WORKPLACE_SMALL:
			workingCapacity = 10;
			w = 70;
			h = 70;
			break;
		case WORKPLACE_LARGE:
			workingCapacity = 60;
			w = 200;
			h = 130;
			break;
		}
		
		occupants = new ArrayList<Person>();
		workers = new ArrayList<Person>();
		curOccupants = 0;
	}
	
	public void update() {
		if (bt.equals(BuildingType.HOUSE)) {
			if (!Simulation.isDay) {
				for (int i = 0; i < occupants.size(); i++) {
					if (occupants.get(i).getStatus().equals(Person.Status.INFECTIOUS)) {
						for (int b = 0; b < occupants.size(); b++) {
							if (b != i) {
								if ((float)(Math.random() * 1) < Simulation.I_PER_C / 2) {
									if (occupants.get(b).getStatus().equals(Person.Status.SUSCEPTIBLE)) {
										occupants.get(b).infect();
									}
								}
							}
						}
					}
					else if (occupants.get(i).getStatus().equals(Person.Status.SYMPTOMATIC)) {
						for (int b = 0; b < occupants.size(); b++) {
							if (b != i) {
								if ((float)(Math.random() * 1) < Simulation.I_PER_C * 3 / 2) {
									if (occupants.get(b).getStatus().equals(Person.Status.SUSCEPTIBLE)) {
										occupants.get(b).infect();
									}
								}
							}
						}
					}
				}
			}
			else if (Simulation.isDay) {
				for (int i = 0; i < workers.size(); i++) {
					if (workers.get(i).getStatus().equals(Person.Status.INFECTIOUS)) {
						for (int b = 0; b < workers.size(); b++) {
							if (b != i) {
								if ((float)(Math.random() * 1) < Simulation.I_PER_C / 2) {
									if (workers.get(b).getStatus().equals(Person.Status.SUSCEPTIBLE)) {
										workers.get(b).infect();
									}
								}
							}
						}
					}
					else if (workers.get(i).getStatus().equals(Person.Status.SYMPTOMATIC)) {
						for (int b = 0; b < workers.size(); b++) {
							if (b != i) {
								if ((float)(Math.random() * 1) < Simulation.I_PER_C * 3 / 2) {
									if (workers.get(b).getStatus().equals(Person.Status.SUSCEPTIBLE)) {
										workers.get(b).infect();
									}
								}
							}
						}
					}
				}
			}
		}
		else if (bt.equals(BuildingType.WORKPLACE_SMALL)) {
			if (Simulation.isDay) {
				for (int i = 0; i < workers.size(); i++) {
					if (workers.get(i).getStatus().equals(Person.Status.INFECTIOUS)) {
						for (int b = 0; b < workers.size(); b++) {
							if (b != i) {
								if ((float)(Math.random() * 1) < Simulation.I_PER_C / 2) {
									if (workers.get(b).getStatus().equals(Person.Status.SUSCEPTIBLE)) {
										workers.get(b).infect();
									}
								}
							}
						}
					}
					else if (workers.get(i).getStatus().equals(Person.Status.SYMPTOMATIC)) {
						for (int b = 0; b < workers.size(); b++) {
							if (b != i) {
								if ((float)(Math.random() * 1) < Simulation.I_PER_C * 3 / 2) {
									if (workers.get(b).getStatus().equals(Person.Status.SUSCEPTIBLE)) {
										workers.get(b).infect();
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	public void render(Graphics g) {
		switch(bt) {
		case HOUSE: 
			g.setColor(new Color(0,55,55));
			break;
		case APARTMENT:
			g.setColor(new Color(233,48,20));
			break;
		case WORKPLACE_SMALL:
			g.setColor(new Color(33,134,222));
			break;
		case WORKPLACE_LARGE:
			g.setColor(new Color(35,32,255));
			break;
		}
		g.fillRect(x, y, w, h);
		
		if (bt.equals(BuildingType.HOUSE)) {
			if (!Simulation.isDay) {
				int i = 0;
				for (Person o : occupants) {
					if (i == 0) {
						o.drawPerson(g, x + ((w / 2) / 4) * 2, y + ((h / 2) / 4) * 2);
					} else
					if (i == 1) {
						o.drawPerson(g, x + ((w / 2) / 4) * 5, y + ((h / 2) / 4) * 2);
					} else
					if (i == 2) {
						o.drawPerson(g, x + ((w / 2) / 4) * 3.5f, y + ((h / 2) / 4) * 5);
					}
					i++;
				}
			}
			if (Simulation.isDay) {
				int i = 0;
				for (Person o : workers) {
					if (i == 0) {
						o.drawPerson(g, x + ((w / 2) / 4) * 2, y + ((h / 2) / 4) * 2);
					} else
					if (i == 1) {
						o.drawPerson(g, x + ((w / 2) / 4) * 5, y + ((h / 2) / 4) * 2);
					} else
					if (i == 2) {
						o.drawPerson(g, x + ((w / 2) / 4) * 3.5f, y + ((h / 2) / 4) * 5);
					}
					i++;
				}
			}
		}
		
		if (bt.equals(BuildingType.WORKPLACE_SMALL)) {
			if (Simulation.isDay) {
				int b = 0;
				for (Person o : workers) {
					if (b < 5) {
						o.drawPerson(g, x + 10 + 10 * b, y + 20);
					} else {
						o.drawPerson(g, x + 10 + 10 * (b % 5), y + h - 20);
					}
					b++;
				}
			}
		}
	}
	
	public void addResident(Person person) {
		occupants.add(person);
	}
	
	public void addWorker(Person person) {
		workers.add(person);
	}
	
	public void addOccupant() {
		curOccupants++;
	}
	
	public int getNumOccupants() {
		return curOccupants;
	}
	
	public void removeLivingCapacity() {
		livingCapacity--;
	}
	
	public void removeWorkingCapacity() {
		workingCapacity--;
	}
	
	public int getLivingCapacity() {
		return livingCapacity;
	}

	public int getWorkingCapacity() {
		return workingCapacity;
	}
	
	public ArrayList<Person> getOccupants() {
		return occupants;
	}
	
	public enum BuildingType {
		HOUSE,
		APARTMENT,
		WORKPLACE_SMALL,
		WORKPLACE_LARGE
	}
	
	public float[] getCenter() {
		float[] center = {x + (w / 2) , y + (h / 2)};
		return center;
	}
}