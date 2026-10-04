// This defines a simple class of critters that infect whenever they can and
// otherwise just spin around, looking for critters to infect. This simple
// strategy turns out to be surprisingly successful.

package animalkingdom;

import java.awt.Color;

public class FlyTrap extends Critter {
	private final Color color = Color.RED;
	
	@Override
    public Action getMove(CritterInfo info) {
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else {
            return Action.LEFT;
        }
    }

	@Override
    public Color getColor() {
        return color;
    }

	@Override
    public String toString() {
        return "+";
    }
}