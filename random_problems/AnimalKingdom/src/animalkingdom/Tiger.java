package animalkingdom;

import java.awt.Color;
import java.util.Random;

public class Tiger extends Critter {
	private Color[] colors = {Color.YELLOW, Color.ORANGE, Color.RED};
	private Color currentColor;
	private int colorChangeCounter;
	
	public Tiger() {
		this.currentColor = randomColor();
	}
	
	@Override
	public Action getMove(CritterInfo info) {			
		if(info.frontThreat()) {
			return Action.INFECT;
		} else if(info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
			return Action.LEFT;
		} else if(info.getFront() == Neighbor.SAME) {
			return Action.RIGHT;
		} else {
			return (new Random().nextBoolean())? Action.HOP: Action.RIGHT;
		}		
	}
	
	@Override
	public Color getColor() {
		if(colorChangeCounter % 3 == 0) {
			currentColor = randomColor();
			colorChangeCounter = 0;
		}
		
		colorChangeCounter++;
				
		return currentColor; 
	}
	
	@Override
	public String toString() {
		return "=";
	}
	
	private Color randomColor() {
		return colors[new Random().nextInt(colors.length)];
	}
}
