package battleship;

public class Util {
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
