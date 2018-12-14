package adventureGame;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	public GameStatus status = new GameStatus();
	private Coordinates playerStart = new Coordinates(4,4);
	private Coordinates mapSize = new Coordinates(9,9);
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
		
		Feature tree = new Feature(0,1,"the really big tree",false);
		Feature wall = new Feature(8,6,"the uninteresting wall",false);
		
		features.add(treasure);
		features.add(tree);
		features.add(wall);

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
			System.out.println("That is not a valid direction. Try \"north\", \"east\", \"west\" or \"south\".");
			
		return playerPosition;
		
	}
	
	public void treasure() {
		
		System.out.println("\nYou notice a chest in the middle of the plains. You open it and inside you find mounds of riches. Congratulations! You win!\n");
		status.setPlayerAlive(false);
		
	}
	
	public String start() {
		
		randomTreasure();
		generateFeatures();

		String text = ("After what feels like countless hours of exploring, you find yourself in the midst of a murky swamp with no recollection of how you got here.\n" +
				"You reach into your pocket and discover that you have with you a small pocket watch type device.\n" + 
				"Displayed on the face are hands like a watch, but it doesn't seem like it is telling the time.\n" +
				"Also on the face of the watch are two numbers.\n\n" +
				"Which direction will you travel?\n");
		
		return text;
		
	}
}
