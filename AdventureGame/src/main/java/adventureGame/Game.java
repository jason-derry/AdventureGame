package adventureGame;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	public GameStatus status = new GameStatus();
	private Coordinates playerStart = new Coordinates(2,2);
	private Coordinates mapSize = new Coordinates(5,5);
	private Coordinates playerPosition = playerStart;

	List<Feature> features = new ArrayList<>();
	Feature treasure = new Feature(3,4,"Treasure",true);
	
	public void generateFeatures() {
	
		Feature test = new Feature(1,1,"Test",false);
		
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
	
	public double nearestFeature() {
		
		double distance;
		int distX = playerPosition.getX() - treasure.getX();
		int distY = playerPosition.getY() - treasure.getY();
		distance = Math.pow(distX,2) + Math.pow(distY,2);
		distance = Math.pow(distance, 0.5);
		if (distance % 1 != 0)
			distance = (int)distance + 0.5;
		
		return distance;
	}
	
	public void feature(int i) {
		
		if(features.get(i).isVisited() == false) {
			System.out.println("You arrive at " + features.get(i).getName() + ".");
			features.get(i).setVisited(true);
		}
		else
			System.out.println("You find yourself back at " + features.get(i).getName() + ".");

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
			for (int i = 0; i < features.size(); i++) {
				if((playerPosition.getX() == features.get(i).getX() && playerPosition.getY() == features.get(i).getY()))
					feature(i);
			}
			System.out.println("The dial reads: " + nearestFeature() +"m.");
			System.out.println(playerPosition.toString());
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
		
		System.out.println("You see a box sitting on the plain.   Its filled with treasure!  You win!  The end.");
		status.setPlayerAlive(false);
		
	}
	
	public String start() {
		
		generateFeatures();
		String text = ("Grey foggy clouds float oppressively close to you, reflected in the murky grey water which reaches up your shins.\n\n" +
				"Try \"north\",\"south\",\"east\",or \"west\".\n\n" + 
				"You notice a small watch-like device in your left hand. \n\n" + 
				"It has hands like a watch, but the hands don't seem to tell time.\n\n" +
				"The dial reads: " + nearestFeature() +"m.");
		
		return text;
	}
	
}
