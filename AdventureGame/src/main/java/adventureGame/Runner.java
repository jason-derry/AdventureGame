package adventureGame;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		String cont = "";

		while(true) {
			
			Game fun = new Game();
			System.out.println(fun.start());
			fun.printPosition();
			
			while(fun.status.isPlayerAlive() == true) {
				String direction = sc.next();
				fun.move(direction);
			}
			
			System.out.println("Play again?(Y/N)");
			cont = sc.next();
			
			if (cont.equalsIgnoreCase("n") || cont.equalsIgnoreCase("no"))
				break;
		}

	}

}
