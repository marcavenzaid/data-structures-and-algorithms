package animalkingdom;

import java.awt.Color;
import java.util.Random;

public class Bear extends Critter {
	private final Color color;
	private Direction direction;
	private String denotation;
	
	public Bear() {
		this.denotation = "/";
		boolean isPolar = new Random().nextBoolean();
		this.color = (isPolar)? Color.WHITE : Color.LIGHT_GRAY;
	}
	
	@Override
	public Action getMove(CritterInfo info) {
		setDirection(info.getDirection());
		
		if(info.frontThreat()) {
			return Action.INFECT;
		} else if (info.getFront() == Neighbor.SAME || info.getFront() == Neighbor.WALL 
				|| info.getFront() == Neighbor.OTHER) {
			return Action.LEFT;
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
		if(getDirection() == Direction.WEST) {
			setDenotation("\\");
			return "\\";
		} else if (getDirection() == Direction.EAST) {
			setDenotation("/");
			return "/";
		} else {
			return getDenotation();
		}		
	}
	
	private Direction getDirection() {
		return direction;
	}
	
	private void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	private String getDenotation() {
		return denotation;
	}
	
	private void setDenotation(String string) {
		this.denotation = string;
	}
}
