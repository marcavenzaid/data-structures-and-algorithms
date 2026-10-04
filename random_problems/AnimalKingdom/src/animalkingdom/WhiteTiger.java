package animalkingdom;

import java.awt.Color;

public class WhiteTiger extends Tiger {
	private final Color color = Color.WHITE;
	private boolean hasInfected;
	
	@Override
	public Action getMove(CritterInfo info) {
		Action move = super.getMove(info);
		
		if(move == Action.INFECT) {
			hasInfected = true;
		}
		
		return move;
	}
	
	@Override
	public Color getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		if(hasInfected) {
			return super.toString();
		}
		
		return "I";
	}
}
