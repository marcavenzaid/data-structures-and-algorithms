package battleship;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
	private int shipsCount;
	
	Player(int shipCount) {
		this.shipsCount = shipCount;
	}
	
	public int getShipsCount() {
		return shipsCount;
	}
	
	public void decreaseShipsCount() {
		shipsCount--;
	}
	
	public void deployShip(Ocean ocean) {
		Scanner scan = new Scanner(System.in);
		int x, y;
		
		System.out.println("Deploy your ships.");
		System.out.println("Enter x and y coordinates where you want to place your ship.");
        System.out.println("Seperate x and y coordinates by a space. Example: 1 2\n");  
		
		for(int i = 0; i < getShipsCount(); i++) {
        	System.out.print("Enter x and y coordinates for ship " + (i + 1) + ": ");
			
        	do {
				x = getInputCoordinates(ocean, scan);
				y = getInputCoordinates(ocean, scan);
				checkIfWithinBounds(ocean, x, y);
				checkDuplicateCoordinates(ocean, x, y);
			} while(!ocean.isWithinBoundary(x, y) || hasShip(ocean, x, y));
	        
	    	ocean.setElement(Util.PLAYER, x, y);
			System.out.println("Ship deployed at coordinate (" + x + ", " + y + ").");
		}
		
		System.out.println();
	}
	
	public void attack(Ocean ocean, Enemy enemy) {
		Scanner scan = new Scanner(System.in);
		int x, y;
		
		System.out.print("Enter x and y coordinates to shoot: ");
		
		do {
			x = getInputCoordinates(ocean, scan);
			y = getInputCoordinates(ocean, scan);
			
			checkIfWithinBounds(ocean, x, y);
		} while(!ocean.isWithinBoundary(x, y));
		
		if(ocean.getElement(x, y) == Util.ENEMY) {						
			ocean.setElement(Util.ENEMY_HIT, x, y);
			enemy.decreaseShipsCount();
			System.out.println("Boom! You sunk a ship!");
		} else if(ocean.getElement(x, y) == Util.PLAYER) {			
			ocean.setElement(Util.PLAYER_HIT, x, y);
			shipsCount--;
			System.out.println("Oh no, you sunk your own ship :(");
		} else if(ocean.getElement(x, y) == Util.NONE){			
			ocean.setElement(Util.NONE_HIT, x, y);
			System.out.println("Sorry, you missed.");
		} else {
			// if NONE_SYMBOL, PLAYER_SYMBOL, NONE_HIT_SYMBOL, PLAYER_HIT_SYMBOL, ENEMY_HIT_SYMBOL
			System.out.println("Sorry, you missed.");
		}
	}
	
	private int getInputCoordinates(Ocean ocean, Scanner scan) {
		boolean isValid = false;
		int coordinate = -1;		

		do {
			try {
				coordinate = scan.nextInt();
				isValid = true;
			} catch (InputMismatchException e) {
				scan.next();
			}
		} while(!isValid);
		
		return coordinate;
	}
	
	private void checkIfWithinBounds(Ocean ocean, int x, int y) {
		if(!ocean.isWithinBoundary(x, y)) {
    		System.out.print("Coordinate is out of bounds. Re-enter coordinates: ");
    	}
	}
	
	private void checkDuplicateCoordinates(Ocean ocean, int x, int y) {
		if(ocean.isWithinBoundary(x, y) && hasShip(ocean, x, y)) {
    		System.out.print("You already deployed a ship at this coordinate. Re-enter coordinates: "); 
    	}
	}
	
	private boolean hasShip(Ocean ocean, int x, int y) {
		return ocean.getElement(x, y) == Util.PLAYER;
	}
}
