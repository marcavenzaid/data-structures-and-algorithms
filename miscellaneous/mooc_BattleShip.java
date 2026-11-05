import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.InputMismatchException;
import java.util.Scanner;

public class mooc_BattleShip {
    private static final int OCEAN_SIZE_X = 10;
    private static final int OCEAN_SIZE_Y = 10;
    private static final int SHIPS_COUNT = 5;
    
    public static void main(String[] args) {
        MOOCBattleShipOcean ocean = new MOOCBattleShipOcean(new int [OCEAN_SIZE_Y][OCEAN_SIZE_X]);
        MOOCBattleShipPlayer player = new MOOCBattleShipPlayer(SHIPS_COUNT);
        MOOCBattleShipEnemy enemy = new MOOCBattleShipEnemy(SHIPS_COUNT);
        
        System.out.println("**** Welcome to Battle Ships game ****");
        ocean.display(player, enemy); 
        
        player.deployShip(ocean);
        enemy.deployShips(ocean);
        
        System.out.println("Battle Start!");
        ocean.display(player, enemy);
        
        while (player.getShipsCount() != 0 && enemy.getShipsCount() != 0) {
            System.out.println("YOUR TURN");
            player.attack(ocean, enemy);
            
            System.out.println("ENEMY'S TURN");
            enemy.attack(ocean, player);	
            
            ocean.display(player, enemy);
        }
        
        if(enemy.getShipsCount() == 0) {
            System.out.println("YOU WIN :)");
        } else {
            System.out.println("YOU LOSE :(");
        }
    }
}

class MOOCBattleShipUtil {
	public enum Element {
		NONE, PLAYER, ENEMY, NONE_HIT, PLAYER_HIT, ENEMY_HIT
	}
	
	public static final int NONE = Element.NONE.ordinal();
	public static final int PLAYER = Element.PLAYER.ordinal();
	public static final int ENEMY = Element.ENEMY.ordinal();
	public static final int NONE_HIT = Element.NONE_HIT.ordinal();
	public static final int PLAYER_HIT = Element.PLAYER_HIT.ordinal();
	public static final int ENEMY_HIT = Element.ENEMY_HIT.ordinal();
	
	public static final char NONE_SYMBOL = ' ';
	public static final char PLAYER_SYMBOL = '@';
	public static final char NONE_HIT_SYMBOL = '-';
	public static final char PLAYER_HIT_SYMBOL = 'X';
	public static final char ENEMY_HIT_SYMBOL = '!';
}

class MOOCBattleShipPlayer {
	private int shipsCount;
	
	MOOCBattleShipPlayer(int shipCount) {
		this.shipsCount = shipCount;
	}
	
	public int getShipsCount() {
		return shipsCount;
	}
	
	public void decreaseShipsCount() {
		shipsCount--;
	}
	
	public void deployShip(MOOCBattleShipOcean ocean) {
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
	        
	    	ocean.setElement(MOOCBattleShipUtil.PLAYER, x, y);
			System.out.println("Ship deployed at coordinate (" + x + ", " + y + ").");
		}
		
		System.out.println();
	}
	
	public void attack(MOOCBattleShipOcean ocean, MOOCBattleShipEnemy enemy) {
		Scanner scan = new Scanner(System.in);
		int x, y;
		
		System.out.print("Enter x and y coordinates to shoot: ");
		
		do {
			x = getInputCoordinates(ocean, scan);
			y = getInputCoordinates(ocean, scan);
			
			checkIfWithinBounds(ocean, x, y);
		} while(!ocean.isWithinBoundary(x, y));
		
		if(ocean.getElement(x, y) == MOOCBattleShipUtil.ENEMY) {						
			ocean.setElement(MOOCBattleShipUtil.ENEMY_HIT, x, y);
			enemy.decreaseShipsCount();
			System.out.println("Boom! You sunk a ship!");
		} else if(ocean.getElement(x, y) == MOOCBattleShipUtil.PLAYER) {			
			ocean.setElement(MOOCBattleShipUtil.PLAYER_HIT, x, y);
			shipsCount--;
			System.out.println("Oh no, you sunk your own ship :(");
		} else if(ocean.getElement(x, y) == MOOCBattleShipUtil.NONE){			
			ocean.setElement(MOOCBattleShipUtil.NONE_HIT, x, y);
			System.out.println("Sorry, you missed.");
		} else {
			// if NONE_SYMBOL, PLAYER_SYMBOL, NONE_HIT_SYMBOL, PLAYER_HIT_SYMBOL, ENEMY_HIT_SYMBOL
			System.out.println("Sorry, you missed.");
		}
	}
	
	private int getInputCoordinates(MOOCBattleShipOcean ocean, Scanner scan) {
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
	
	private void checkIfWithinBounds(MOOCBattleShipOcean ocean, int x, int y) {
		if(!ocean.isWithinBoundary(x, y)) {
    		System.out.print("Coordinate is out of bounds. Re-enter coordinates: ");
    	}
	}
	
	private void checkDuplicateCoordinates(MOOCBattleShipOcean ocean, int x, int y) {
		if(ocean.isWithinBoundary(x, y) && hasShip(ocean, x, y)) {
    		System.out.print("You already deployed a ship at this coordinate. Re-enter coordinates: "); 
    	}
	}
	
	private boolean hasShip(MOOCBattleShipOcean ocean, int x, int y) {
		return ocean.getElement(x, y) == MOOCBattleShipUtil.PLAYER;
	}
}

class MOOCBattleShipEnemy extends MOOCBattleShipPlayer {
	private int shipsCount;
	private List<List<Integer>> attackedCoordinates = new ArrayList<List<Integer>>();
	
	MOOCBattleShipEnemy(int shipsCount) {
		super(shipsCount);
		this.shipsCount = shipsCount;
	}
	
	public void deployShips(MOOCBattleShipOcean ocean) {
		Random rand = new Random();
		boolean isValidCoordinates;
		
		System.out.println("Enemy is deploying ships..");
		
		for(int i = 0; i < shipsCount; i++) {
			isValidCoordinates = false;
			
			do {
				int x = rand.nextInt(ocean.getColumnCount());
				int y = rand.nextInt(ocean.getRowCount());
					
				if(ocean.getElement(x, y) == MOOCBattleShipUtil.NONE) {
					ocean.setElement(MOOCBattleShipUtil.ENEMY, x, y);
	        		System.out.println("Enemy ship " + (i + 1) + " deployed!");
	        		isValidCoordinates = true;
				}
			} while(!isValidCoordinates);
		}
		
		System.out.println();
	}
	
	public void attack(MOOCBattleShipOcean ocean, MOOCBattleShipPlayer player) {
		Random rand = new Random();
		int x, y;
		
		do {
			x = rand.nextInt(ocean.getColumnCount());
			y = rand.nextInt(ocean.getRowCount());
		} while (attackedCoordinates.contains(Arrays.asList(x, y)));
		
		attackedCoordinates.add(Arrays.asList(x, y));
		
		if(ocean.getElement(x, y) == MOOCBattleShipUtil.ENEMY) {			
			ocean.setElement(MOOCBattleShipUtil.ENEMY_HIT, x, y);
			decreaseShipsCount();
			System.out.println("Boom! The enemy sunk one of its own ship!");
		} else if(ocean.getElement(x, y) == MOOCBattleShipUtil.PLAYER) {			
			ocean.setElement(MOOCBattleShipUtil.PLAYER_HIT, x, y);
			player.decreaseShipsCount();
			System.out.println("Oh no, the enemy sunk one of your ship :(");
		} else {
			System.out.println("The enemy missed.");
		}
	}
}

class MOOCBattleShipOcean {
	private int[][] oceanMap;
    
    MOOCBattleShipOcean(int[][] oceanMap) {
        this.oceanMap = oceanMap;
    }
    
    public int getColumnCount() {
    	return oceanMap[0].length;
    }
    
    public int getRowCount() {
    	return oceanMap.length;
    }
    
    public int getElement(int x, int y) {
    	return oceanMap[y][x];
    }
    
    public void setElement(int value, int x, int y) {
    	oceanMap[y][x] = value;
    }
    
    public boolean isWithinBoundary(int x, int y) {
    	return x >= 0 && x < getColumnCount() && y >= 0 && y < getRowCount();
    }
    
    public void display(MOOCBattleShipPlayer player, MOOCBattleShipEnemy enemy) {
    	System.out.println();
    	
        horizontalTicks();
         
        for(int i = 0; i < oceanMap.length; i++) {
            System.out.print(i + " |");
            
            for(int j = 0; j < oceanMap[0].length; j++) {
                if (oceanMap[i][j] == MOOCBattleShipUtil.PLAYER) {
                	System.out.print(MOOCBattleShipUtil.PLAYER_SYMBOL);
                } else if (oceanMap[i][j] == MOOCBattleShipUtil.ENEMY || oceanMap[i][j] == MOOCBattleShipUtil.NONE) {
                	System.out.print(MOOCBattleShipUtil.NONE_SYMBOL);
                } else if (oceanMap[i][j] == MOOCBattleShipUtil.PLAYER_HIT) {
                	System.out.print(MOOCBattleShipUtil.PLAYER_HIT_SYMBOL);
                } else if (oceanMap[i][j] == MOOCBattleShipUtil.ENEMY_HIT) {
                	System.out.print(MOOCBattleShipUtil.ENEMY_HIT_SYMBOL);
                } else if (oceanMap[i][j] == MOOCBattleShipUtil.NONE_HIT) {
                	System.out.print(MOOCBattleShipUtil.NONE_HIT_SYMBOL);
                } else {
                    System.out.print(getElement(i, j));
                }
                
                if(j != oceanMap[0].length - 1) {
                	System.out.print(' ');
                }
            }
            System.out.println("| " + i);
        }
        
        horizontalTicks();
        
        System.out.println("\nYour ships: " + player.getShipsCount() + " | Enemy Ships: " + enemy.getShipsCount());
    	System.out.println("-----------------------------------\n");
    }
    
    private void horizontalTicks() {
        System.out.print("   ");
        
        for(int i = 0; i < oceanMap[0].length; i++) {
            System.out.print(i);
            
            if(i != oceanMap[0].length - 1) {
            	System.out.print(' ');
            }
        }
        
        System.out.println();
    }
}
