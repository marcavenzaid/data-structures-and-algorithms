package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Enemy extends Player {
	private int shipsCount;
	private List<List<Integer>> attackedCoordinates = new ArrayList<List<Integer>>();
	
	Enemy(int shipsCount) {
		super(shipsCount);
		this.shipsCount = shipsCount;
	}
	
	public void deployShips(Ocean ocean) {
		Random rand = new Random();
		boolean isValidCoordinates;
		
		System.out.println("Enemy is deploying ships..");
		
		for(int i = 0; i < shipsCount; i++) {
			isValidCoordinates = false;
			
			do {
				int x = rand.nextInt(ocean.getColumnCount());
				int y = rand.nextInt(ocean.getRowCount());
					
				if(ocean.getElement(x, y) == Util.NONE) {
					ocean.setElement(Util.ENEMY, x, y);
	        		System.out.println("Enemy ship " + (i + 1) + " deployed!");
	        		isValidCoordinates = true;
				}
			} while(!isValidCoordinates);
		}
		
		System.out.println();
	}
	
	public void attack(Ocean ocean, Player player) {
		Random rand = new Random();
		int x, y;
		
		do {
			x = rand.nextInt(ocean.getColumnCount());
			y = rand.nextInt(ocean.getRowCount());
		} while (attackedCoordinates.contains(Arrays.asList(x, y)));
		
		attackedCoordinates.add(Arrays.asList(x, y));
		
		if(ocean.getElement(x, y) == Util.ENEMY) {			
			ocean.setElement(Util.ENEMY_HIT, x, y);
			decreaseShipsCount();
			System.out.println("Boom! The enemy sunk one of its own ship!");
		} else if(ocean.getElement(x, y) == Util.PLAYER) {			
			ocean.setElement(Util.PLAYER_HIT, x, y);
			player.decreaseShipsCount();
			System.out.println("Oh no, the enemy sunk one of your ship :(");
		} else {
			System.out.println("The enemy missed.");
		}
	}
}
