package adventureGame;

public class Feature extends Coordinates {
	
	private String name;
	private boolean visited;
	private boolean endsGame;

	public Feature(int x, int y, String name, boolean endsGame) {
		super(x, y);
		this.name = name;
		this.visited = false;
		this.endsGame = endsGame;
	}

	public String getName() {
		return name;
	}
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean endsGame() {
		return endsGame;
	}

	@Override
	public String toString() {
		return name + " is at (" + this.getX() + ", " + this.getY() + ").";
	}
	
	
	
}
