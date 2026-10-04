package battleship;

public class Ocean {
	private int[][] oceanMap;
    
    Ocean(int[][] oceanMap) {
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
    
    public void display(Player player, Enemy enemy) {
    	System.out.println();
    	
        horizontalTicks();
         
        for(int i = 0; i < oceanMap.length; i++) {
            System.out.print(i + " |");
            
            for(int j = 0; j < oceanMap[0].length; j++) {
                if (oceanMap[i][j] == Util.PLAYER) {
                	System.out.print(Util.PLAYER_SYMBOL);
                } else if (oceanMap[i][j] == Util.ENEMY || oceanMap[i][j] == Util.NONE) {
                	System.out.print(Util.NONE_SYMBOL);
                } else if (oceanMap[i][j] == Util.PLAYER_HIT) {
                	System.out.print(Util.PLAYER_HIT_SYMBOL);
                } else if (oceanMap[i][j] == Util.ENEMY_HIT) {
                	System.out.print(Util.ENEMY_HIT_SYMBOL);
                } else if (oceanMap[i][j] == Util.NONE_HIT) {
                	System.out.print(Util.NONE_HIT_SYMBOL);
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
