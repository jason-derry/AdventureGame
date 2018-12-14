package adventureGame;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	public GameStatus status = new GameStatus();
	private Coordinates playerStart = new Coordinates(2,2);
	private Coordinates mapSize = new Coordinates(5,5);
	private Coordinates playerPosition = playerStart;

	List<Feature> features = new ArrayList<>();
	
	Feature treasure = new Feature(playerStart.getX(),playerStart.getY(),"Treasure",true);
	
	public void randomTreasure() {
		
		while(treasure.getX() == playerStart.getX() && treasure.getY() == playerStart.getY()) {
			treasure.setX((int)(Math.random()*5));
			treasure.setY((int)(Math.random()*5));
		}
	}
	
	public void generateFeatures() {
		
		Feature test = new Feature(0,1,"a really big tree",false);
		
		features.add(treasure);
		features.add(test);

	}
	
	public void north() {
		playerPosition.setY(playerPosition.getY()+1);
		status.setCurrentDir("North");
	}
	public void south() {
		playerPosition.setY(playerPosition.getY()-1);
		status.setCurrentDir("South");
	}
	public void east() {
		playerPosition.setX(playerPosition.getX()+1);
		status.setCurrentDir("East");
	}
	public void west() {
		playerPosition.setX(playerPosition.getX()-1);
		status.setCurrentDir("West");
	}
	
	public double calcDistance(int i, List<Feature> list) {
		
		double distance;
		int distX = playerPosition.getX() - list.get(i).getX();
		int distY = playerPosition.getY() - list.get(i).getY();
		
		distance = Math.pow(distX,2) + Math.pow(distY,2);
		distance = Math.pow(distance, 0.5);
		if (distance % 1 != 0)
			distance = (int)distance + 0.5;
		
		return distance;
	}
	
	public double nearestFeature() {
		
		double distance = 0;
		List<Feature> closest = new ArrayList<>();
		closest.add(treasure);
		for (int i = 0; i < features.size()-1; i++) {
			if(calcDistance(i+1, features) < calcDistance(0, closest)) {
				closest.clear();
				closest.add(features.get(i+1));
			}
		}
		distance = calcDistance(0,closest);
		
		return distance;
	}
	
	public void feature(int i) {
		
		if(features.get(i).isVisited() == false) {
			System.out.println("\nYou arrive at " + features.get(i).getName() + ".\n");
			features.get(i).setVisited(true);
		}
		else
			System.out.println("\nYou find yourself back at " + features.get(i).getName() + ".\n");

	}
	
	public void printPosition() {
		System.out.println("The dial reads: " + nearestFeature() +"m.");
		System.out.println(playerPosition.toString() + "\n");
	}
	
	public void checkPosition() {
		
		if(playerPosition.getX() >= mapSize.getX() || playerPosition.getX() < 0 || playerPosition.getY() >= mapSize.getY() || playerPosition.getY() < 0)
			System.out.println("But there is nothing further " + status.getCurrentDir() + ". You turn back.");
		
		if(playerPosition.getX() >= mapSize.getX()) 
			playerPosition.setX(mapSize.getX()-1);
		else if(playerPosition.getX() < 0) 
			playerPosition.setX(0);
		
		if(playerPosition.getY() >= mapSize.getY()) 
			playerPosition.setY(mapSize.getY()-1);
		else if(playerPosition.getY() < 0) 
			playerPosition.setY(0);

		if(playerPosition.getX() == treasure.getX() && playerPosition.getY() == treasure.getY()) {
			treasure();
		}
		else {
			for (int i = 1; i < features.size(); i++) {
				if((playerPosition.getX() == features.get(i).getX() && playerPosition.getY() == features.get(i).getY()))
					feature(i);
			}
			printPosition();
		}
	}
	
	public Coordinates move(String direction) {

		if(direction.equalsIgnoreCase("north") || direction.equalsIgnoreCase("n")) {
			north();
			System.out.println("You travel " + status.getCurrentDir() + ".");
			checkPosition();
		} else if(direction.equalsIgnoreCase("south") || direction.equalsIgnoreCase("s")) {
			south();
			System.out.println("You travel " + status.getCurrentDir() + ".");
			checkPosition();
		} else if(direction.equalsIgnoreCase("east") || direction.equalsIgnoreCase("e")) {
			east();
			System.out.println("You travel " + status.getCurrentDir() + ".");
			checkPosition();
		} else if(direction.equalsIgnoreCase("west") || direction.equalsIgnoreCase("w")) {
			west();
			System.out.println("You travel " + status.getCurrentDir() + ".");
			checkPosition();
		} else
			System.out.println("That is not a valid direction.");
			
		return playerPosition;
	}
	
	public void treasure() {
		
		System.out.println("\nYou see a box sitting on the plain.   Its filled with treasure!  You win!  The end.\n");
		status.setPlayerAlive(false);
		
	}
	
	public String start() {
		
		randomTreasure();
		generateFeatures();
		System.out.println(treasure.toString());
		String text = ("Grey foggy clouds float oppressively close to you, reflected in the murky grey water which reaches up your shins.\n\n" +
				"Try \"north\",\"south\",\"east\",or \"west\".\n\n" + 
				"You notice a small watch-like device in your left hand. \n\n" + 
				"It has hands like a watch, but the hands don't seem to tell time.\n");
		
		return text;
	}
	
}
