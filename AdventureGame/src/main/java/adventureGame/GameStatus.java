package adventureGame;

public class GameStatus {

	private boolean playerAlive;
	private String currentDir;
	
	public GameStatus() {
		this.playerAlive = true;
		this.currentDir = "";
	}

	public boolean isPlayerAlive() {
		return playerAlive;
	}

	public void setPlayerAlive(boolean playerAlive) {
		this.playerAlive = playerAlive;
	}

	public String getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(String currentDir) {
		this.currentDir = currentDir;
	}
	
	
	
}
