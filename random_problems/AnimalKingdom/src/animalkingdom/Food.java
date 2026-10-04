package animalkingdom;

import java.awt.Color;

public class Food extends Critter {
	private final Color color = Color.GREEN;
	
	@Override
    public Action getMove(CritterInfo info) {
        return Action.INFECT;
    }

	@Override
    public Color getColor() {
        return color;
    }

	@Override
    public String toString() {
        return ".";
    }
}
