package battleship;

public class BattleShip {
	private static final int OCEAN_SIZE_X = 10;
	private static final int OCEAN_SIZE_Y = 10;
	private static final int SHIPS_COUNT = 5;
	
	public static void main(String[] args) {
        Ocean ocean = new Ocean(new int [OCEAN_SIZE_Y][OCEAN_SIZE_X]);
        Player player = new Player(SHIPS_COUNT);
        Enemy enemy = new Enemy(SHIPS_COUNT);
        
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
