package animalkingdom;

import java.awt.Color;
import java.util.Random;

public class NinjaCat extends Critter {
	private final Color color = Color.MAGENTA;
	
	@Override
	public Action getMove(CritterInfo info) {
		Random rand = new Random();
				
		if(info.frontThreat()) {
			return Action.INFECT;
		} else if(info.leftThreat()) {
			return Action.RIGHT;
		} else if(info.backThreat()) {
			return Action.HOP;
		} else if(info.rightThreat()) {
			return Action.LEFT;
		}
		
		int randomMove = rand.nextInt(3);
		
		if(randomMove == 1) {
			return Action.LEFT;
		} else if(randomMove == 2) {
			return Action.RIGHT;
		} else {
			return Action.HOP;
		}
	}
	
	@Override
	public Color getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		return "*";
	}
}
