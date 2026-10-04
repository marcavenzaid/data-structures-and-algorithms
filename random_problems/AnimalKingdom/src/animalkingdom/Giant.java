package animalkingdom;

import java.awt.Color;

public class Giant extends Critter {
	private final Color color = Color.PINK;
	private String denotations[] = {"O", "0", "8"};
	private String currentDenotation;
	private int denotationsCounter;
	
	@Override
	public Action getMove(CritterInfo info) {
		if(info.frontThreat()) {
			return Action.INFECT;
		} else if (info.getFront() == Neighbor.EMPTY) {
			return Action.HOP;
		} else {
			return Action.RIGHT;
		}
	}
	
	@Override
	public Color getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		if(denotationsCounter % 6 == 0) {
			currentDenotation = (denotationsCounter == 0)? denotations[0] : denotations[denotationsCounter / 6 - 1];
			
			if(denotationsCounter == denotations.length * 6) {
				denotationsCounter = 0;
			}
		}
		
		denotationsCounter++;

		return currentDenotation;
	}
}
