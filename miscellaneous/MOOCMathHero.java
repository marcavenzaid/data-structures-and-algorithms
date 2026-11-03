import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

public class MOOCMathHero extends MOOCMathHeroListeningGameComponent
{
	protected MOOCMathHeroWorld world;
	protected String typed;

	public MOOCMathHero()
	{
		super(MOOCMathHeroUtil.MAX_R*2,MOOCMathHeroUtil.MAX_R*2);
		world = MOOCMathHeroWorld.getWorld();
		typed = "";
		start();
	}

	public void update()
	{
		world.update();
	}

	public void draw(Graphics g)
	{
		world.draw(g);
		g.setColor(Color.BLACK);
		g.drawString(typed,5,30);
	}

	public void keyTyped(KeyEvent ke)
	{
		if(ke.getKeyChar()=='\n'&&typed.length()>0)
		{
			if(typed.length()==7)
				world.tryKey(Integer.parseInt(typed));
			else
				world.player.addExp(world.getLevel().process(Integer.parseInt(typed)));
			typed = "";
		}
		if(Character.isDigit(ke.getKeyChar()))
			typed=typed+ke.getKeyChar();
	}

	public static void main(String[] args)
	{
		new MOOCMathHero().makeTestWindow();
	}
}

class MOOCMathHeroAddition extends MOOCMathHeroEnemy
{
	protected String problem;
	protected int solution;

	public MOOCMathHeroAddition()
	{
		super(.6);
		int n1 = (int)(Math.random()*9)+1;
		int n2 = (int)(Math.random()*9)+1;
		solution = n1+n2;
		problem = ""+n1+"+"+n2;
	}

	public String getProblem()
	{
		return problem;
	}

	public int getSolution()
	{
		return solution;
	}
}

class MOOCMathHeroBigAddition extends MOOCMathHeroEnemy
{
	protected String problem;
	protected int solution;

	public MOOCMathHeroBigAddition()
	{
		super(.3);
		int n1 = (int)(Math.random()*90)+10;
		int n2 = (int)(Math.random()*90)+10;
		solution = n1+n2;
		problem = ""+n1+"+"+n2;
		radius = 15;
	}

	public String getProblem()
	{
		return problem;
	}

	public int getSolution()
	{
		return solution;
	}
}

class MOOCMathHeroBigMultiplication extends MOOCMathHeroEnemy
{
	protected String problem;
	protected int solution;

	public MOOCMathHeroBigMultiplication()
	{
		super(.05);
		int n1 = (int)(Math.random()*90)+10;
		int n2 = (int)(Math.random()*90)+10;
		solution = n1*n2;
		problem = ""+n1+"*"+n2;
		radius = 20;
	}

	public String getProblem()
	{
		return problem;
	}

	public int getSolution()
	{
		return solution;
	}
}

class MOOCMathHeroDivision extends MOOCMathHeroEnemy
{
	protected String problem;
	protected int solution;

	public MOOCMathHeroDivision()
	{
		super(.3);
		int n1 = (int)(Math.random()*9)+1;
		int n2 = (int)(Math.random()*9)+1;
		solution = n1;
		problem = ""+(n1*n2)+"/"+n2;
		radius = 8;
		color = java.awt.Color.RED;
	}

	public String getProblem()
	{
		return problem;
	}

	public int getSolution()
	{
		return solution;
	}
}

abstract class MOOCMathHeroEnemy
{
	protected double r,t,speed;
	protected Color color = Color.BLACK;
	protected int radius = 5;
	private boolean dying1 = false;
	private boolean dying2 = false;
	private boolean dead = false;
	private int alpha = 255;
	private int arrowR = MOOCMathHeroUtil.PLAYER_RADIUS+MOOCMathHeroUtil.ARROW_LENGTH;

	public MOOCMathHeroEnemy(double speed)
	{
		this.speed = speed;
		r = MOOCMathHeroUtil.MAX_R;
		t = Math.random()*Math.PI*2;
	}

	public int x()
	{
		return MOOCMathHeroUtil.MAX_R+(int)(Math.cos(t)*r+.5);
	}

	public int y()
	{
		return MOOCMathHeroUtil.MAX_R+(int)(Math.sin(t)*r+.5);
	}

	private int arrowX1()
	{
		return MOOCMathHeroUtil.MAX_R+(int)(Math.cos(t)*arrowR+.5);
	}

	private int arrowY1()
	{
		return MOOCMathHeroUtil.MAX_R+(int)(Math.sin(t)*arrowR+.5);
	}

	private int arrowX2()
	{
		return MOOCMathHeroUtil.MAX_R+(int)(Math.cos(t)*(arrowR-MOOCMathHeroUtil.ARROW_LENGTH)+.5);
	}

	private int arrowY2()
	{
		return MOOCMathHeroUtil.MAX_R+(int)(Math.sin(t)*(arrowR-MOOCMathHeroUtil.ARROW_LENGTH)+.5);
	}

	public void die()
	{
		dying1 = true;
	}

	public boolean dead()
	{
		return dead;
	}

	public boolean hitting()
	{
		return r == MOOCMathHeroUtil.PLAYER_RADIUS+radius && !dying1;
	}

	public void update()
	{
		if(!dying2)
		{
			r-=speed;
			if(r<MOOCMathHeroUtil.PLAYER_RADIUS+radius)
				r = MOOCMathHeroUtil.PLAYER_RADIUS+radius;
		}

		if(dying2)
		{
			alpha/=1.1;
			if(alpha==0)
				dead = true;
		}
		else if(dying1)
		{
			arrowR+=MOOCMathHeroUtil.ARROW_SPEED;
			if(arrowR>=r)
				dying2 = true;
		}
	}

	public abstract String getProblem();
	public abstract int getSolution();

	public void draw(Graphics g)
	{
		g.setColor(color);
		g.drawString(getProblem(), x()-radius, y()-radius);
		g.fillOval(x()-radius, y()-radius, radius*2, radius*2);
		if(dying1)
		{
			g.setColor(Color.BLACK);
			g.drawLine(arrowX1(),arrowY1(),arrowX2(),arrowY2());
		}
		if(dying2)
		{
			color = new Color(color.getRed(),color.getGreen(),color.getBlue(),alpha);
		}
	}
}

abstract class MOOCMathHeroGameComponent extends JPanel
{
	public static int WIDTH, HEIGHT;
	protected BufferedImage background = null;
	public int delay = 25;

	/**
	 * Constructs a GameComponent with a width of w, and a height of h.
	 *
	 * @param  w  width
	 * @param  h  height
	 * @see         JPanel
	 */
	public MOOCMathHeroGameComponent(int w, int h)
	{
		super();
		WIDTH = w;
		HEIGHT = h;
		setSize(WIDTH, HEIGHT);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.WHITE);
		setVisible(true);
	}

	public void start()
	{
		Thread t = new Thread()
		{
			public void run()
			{
				while(true)
				{
					long time = System.currentTimeMillis();
					if(background == null)
					{
						background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
						background.getGraphics().setColor(Color.WHITE);
						background.getGraphics().fillRect(0,0,WIDTH,HEIGHT);
					}

					requestFocus();

					//update game state
					standardUpdates();
					update();

					//draw stuff
					standardDraw(getCanvas());
					draw(background.getGraphics());
					refreshImage();

					time = System.currentTimeMillis()-time;
					try
					{
						if(delay-(int)time > 0)
							sleep(delay-(int)time);
					}
					catch(Exception ex)
					{
					}
				}
			}
		};
		try{Thread.sleep(500);}catch(Exception ex){}
		t.start();
	}

	//get a blank image to draw onto
	private Graphics getCanvas()
	{
		if(background == null)
		{
			background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		}
		background.getGraphics().setColor(Color.WHITE);
		background.getGraphics().fillRect(0,0,WIDTH,HEIGHT);
		return background.getGraphics();
	}

	//take the canvas that you have drawn on and draw it onto the component
	private void refreshImage()
	{
		if(background != null)
		{
			if(getGraphics() != null)
			{
				getGraphics().drawImage(background,0,0,null);
			}
		}
	}

	/**
	 * Creates a {@link JFrame} that contains this GameComponent.
	 *
	 * @return	the {@link JFrame} created
	 */
	public JFrame makeTestWindow()
	{
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		return frame;
	}

	/**
	 * Creates a fullscreen {@link JFrame} that contains this GameComponent.
	 * <br>*Note that the width and height of the component must be 640x480
	 *
	 * @return	the {@link JFrame} created
	 */
	public JFrame makeFullScreenWindow()
	{
		JFrame frame = new JFrame();
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		// GraphicsConfiguration gc = device.getDefaultConfiguration();
  		DisplayMode oldDisplayMode = device.getDisplayMode();
  	    DisplayMode newDisplayMode = new DisplayMode(640, 480, (oldDisplayMode.getBitDepth()), (oldDisplayMode.getRefreshRate()));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(this, 0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setVisible(true);
		if(device.isFullScreenSupported())
		{
			device.setFullScreenWindow(frame);
			device.setDisplayMode(newDisplayMode);
		}
		else
		{
			System.out.println("ARGS! NO FULLSCRENE!");
		}
		return frame;
	}

	/**
	 * Preforms the standard updates of the component.(Preformed befor {@link #update()})
	 */
	public void standardUpdates()
	{
	}

	/**
	 * The method that draws the component.
	 *
	 * @param	g 	the {@link Graphics} on which the component will be drawn
	 */
	public abstract void draw(Graphics g);

	/**
	 * Draws the sandard parts of the component. (Preformed befor {@link #draw(Graphics)})
	 *
	 * @param	g 	the {@link Graphics} on which the component will be drawn
	 */
	public void standardDraw(Graphics g)
	{
	}


	/**
	 * The method that updates the component.
	 */
	public abstract void update();
}

class MOOCMathHeroLevel
{
	protected Vector<MOOCMathHeroEnemy> enemies;
	protected Vector<MOOCMathHeroEnemy> actives;
	protected int active;
	public int key;

	public MOOCMathHeroLevel(int active, int key, Vector<MOOCMathHeroEnemy> enemies)
	{
		this.active = active;
		this.key = key;
		this.enemies = enemies;
		actives = new Vector<MOOCMathHeroEnemy>();
	}

	public int process(int solution)
	{
		int killed = 0;
		for(int i = 0; i < actives.size(); i++)
		{
			if(actives.get(i).getSolution()==solution)
			{
				actives.get(i).die();
				killed++;
			}
		}
		return killed;
	}

	public int getHitting()
	{
		int hitting = 0;
		for(int i = 0; i < actives.size(); i++)
			if(actives.get(i).hitting())
				hitting++;
		return hitting;
	}

	public boolean finished()
	{
		return actives.size()==0&&enemies.size()==0;
	}

	public void update()
	{
		if(actives.size()<active&&enemies.size()>0&&Math.random()<MOOCMathHeroUtil.ENEMY_FREQUENCY*active)
		{
			actives.add(enemies.remove((int)(Math.random()*enemies.size())));
		}
		for(int i = 0; i < actives.size(); i++)
		{
			actives.get(i).update();
			if(actives.get(i).dead())
			{
				actives.remove(i);
				i--;
			}
		}
	}

	public void drawEnemies(Graphics g)
	{
		for(int i = 0; i < actives.size(); i++)
		{
			actives.get(i).draw(g);
		}
	}
}

abstract class MOOCMathHeroListeningGameComponent extends MOOCMathHeroGameComponent implements MouseListener, MouseMotionListener, KeyListener
{
	public boolean mousePressed1 = false, mousePressed2 = false, mousePressed3 = false;
	public ArrayList<String> keysPressed = new ArrayList<String>();
	public boolean debug = false;

	/**
	 * The x location of the mouse.
	 */
	public int mouseX = 0;
	/**
	 * The y location of the mouse.
	 */
	public int mouseY = 0;

	/**
	 * Constructs a ListeningGameComponent with a width of w, and a height of h.
	 *
	 * @param  w  width
	 * @param  h  height
	 * @see         JPanel
	 */
	public MOOCMathHeroListeningGameComponent(int w, int h)
	{
		super(w,h);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}

	/**
	 * The method that draws the component.
	 *
	 * @param	g 	the {@link Graphics} on which the component will be drawn
	 */
	public abstract void draw(Graphics g);


	/**
	 * The method that updates the component.
	 */
	public abstract void update();

	/**
	 * Does nothing. Activated when the mouse is pressed and released.
	 * @param	e	a mouse event
	 */
	public void mouseClicked(MouseEvent e){}

	/**
	 * Does nothing. Activated when the mouse enters the component.
	 * @param	e	a mouse event
	 */
	public void mouseEntered(MouseEvent e){}

	/**
	 * Does nothing. Activated when the mouse exits the component.
	 * @param	e	a mouse event
	 */
	public void mouseExited(MouseEvent e){}

	/**
	 * Updates the mouse variables.
	 * @param	e	a mouse event
	 */
	public void mousePressed(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)
			mousePressed1 = true;
		if(e.getButton() == MouseEvent.BUTTON2)
			mousePressed2 = true;
		if(e.getButton() == MouseEvent.BUTTON3)
			mousePressed3 = true;
	}


	/**
	 * Updates the mouse variables.
	 * @param	e	a mouse event
	 */
	public void mouseReleased(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)
			mousePressed1 = false;
		if(e.getButton() == MouseEvent.BUTTON2)
			mousePressed2 = false;
		if(e.getButton() == MouseEvent.BUTTON3)
			mousePressed3 = false;
	}


	/**
	 * Updates the mouse variables.
	 * @param	e	a mouse event
	 */
	public void mouseDragged(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)
			mousePressed1 = !mousePressed1;
		if(e.getButton() == MouseEvent.BUTTON2)
			mousePressed2 = !mousePressed2;
		if(e.getButton() == MouseEvent.BUTTON3)
			mousePressed3 = !mousePressed3;
		mouseX = e.getX();
		mouseY = e.getY();
	}


	/**
	 * Updates the mouse variables.
	 * @param	e	a mouse event
	 */
	public void mouseMoved(MouseEvent e)
	{
		mousePressed1 = false;
		mousePressed2 = false;
		mousePressed3 = false;

		mouseX = e.getX();
		mouseY = e.getY();
	}


	/**
	 * Updates the keyboard variables.
	 * @param	e	a key event
	 */
	public void keyPressed(KeyEvent e)
	{
		if(debug)
			System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
		keysPressed.add(KeyEvent.getKeyText(e.getKeyCode()));
	}

	/**
	 * Updates the keyboard variables.
	 * @param	e	a key event
	 */
	public void keyReleased(KeyEvent e)
	{
		for(int i = 0; i < keysPressed.size(); i++)
		{
			if(keysPressed.get(i).equals(KeyEvent.getKeyText(e.getKeyCode())))
			{
				keysPressed.remove(i);
				i--;
			}
		}
	}

	/**
	 * Updates the keyboard variables.
	 * @param	e	a key event
	 */
	public void keyTyped(KeyEvent e){}

	/**
	 * Returns weather a mouse button is pressed.
	 * @param	b	button number
	 * @return	true if the button is pressed
	 */
	public boolean isMousePressed(int b)
	{
		if(b == 1)
			return mousePressed1;
		else if(b == 2)
			return mousePressed2;
		else if(b == 3)
			return mousePressed3;

		return false;
	}

	/**
	 * Returns weather any mouse button is pressed.
	 * @return	true if the button is pressed
	 */
	public boolean isMousePressed()
	{
		if(mousePressed1)
			return mousePressed1;
		else if(mousePressed2)
			return mousePressed2;
		else if(mousePressed3)
			return mousePressed3;

		return false;
	}

	/**
	 * Returns weather a mouse button is pressed.
	 * @param	k	the key pressed (Ex. "A", "B", "C"...")
	 * @return	true if the key is pressed
	 */
	public boolean isKeyPressed(String k)
	{
		for(int i = 0; i < keysPressed.size(); i++)
		{
			if(keysPressed.get(i).equalsIgnoreCase(k))
			{
				return true;
			}
		}
		return false;
	}

	//returns the number of keys pressed.
	public int getKeysPressed()
	{
		return keysPressed.size();
	}

	public void resetKeys()
	{
		keysPressed = new ArrayList<String>();
	}
}

class MOOCMathHeroMultiplication extends MOOCMathHeroEnemy
{
	protected String problem;
	protected int solution;

	public MOOCMathHeroMultiplication()
	{
		super(.4);
		int n1 = (int)(Math.random()*9)+1;
		int n2 = (int)(Math.random()*9)+1;
		solution = n1*n2;
		problem = ""+n1+"*"+n2;
		radius = 8;
	}

	public String getProblem()
	{
		return problem;
	}

	public int getSolution()
	{
		return solution;
	}
}

class MOOCMathHeroPlayer
{
	protected int maxHealth = 100;
	protected int health = maxHealth;
	protected int exp = 0;
	protected int expToNextLevel = 10;
	protected int level = 1;

	public void hit(int amount)
	{
		health-=amount;
	}

	public void heal()
	{
		health = maxHealth;
	}

	public boolean dead()
	{
		return health <= 0;
	}

	public void addExp(int amount)
	{
		exp+=amount;
		while(exp>expToNextLevel)
			levelUp();
	}

	public void levelUp()
	{
		exp-=expToNextLevel;
		expToNextLevel*=2;
		maxHealth+=25;
		level++;
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(MOOCMathHeroUtil.MAX_R-MOOCMathHeroUtil.PLAYER_RADIUS,MOOCMathHeroUtil.MAX_R-MOOCMathHeroUtil.PLAYER_RADIUS,MOOCMathHeroUtil.PLAYER_RADIUS*2,MOOCMathHeroUtil.PLAYER_RADIUS*2);
		g.setColor(Color.RED);
		g.fillRect(MOOCMathHeroUtil.MAX_R-MOOCMathHeroUtil.PLAYER_RADIUS,MOOCMathHeroUtil.MAX_R-MOOCMathHeroUtil.PLAYER_RADIUS-6,MOOCMathHeroUtil.PLAYER_RADIUS*2,5);
		g.setColor(Color.GREEN);
		g.fillRect(MOOCMathHeroUtil.MAX_R-MOOCMathHeroUtil.PLAYER_RADIUS,MOOCMathHeroUtil.MAX_R-MOOCMathHeroUtil.PLAYER_RADIUS-6,MOOCMathHeroUtil.PLAYER_RADIUS*2*health/maxHealth,5);
		g.setColor(Color.BLUE);
		g.fillRect(MOOCMathHeroUtil.MAX_R-MOOCMathHeroUtil.PLAYER_RADIUS,MOOCMathHeroUtil.MAX_R-MOOCMathHeroUtil.PLAYER_RADIUS-1,
			MOOCMathHeroUtil.PLAYER_RADIUS*2*exp/expToNextLevel,1);
	}
}

class MOOCMathHeroSubtraction extends MOOCMathHeroEnemy
{
	protected String problem;
	protected int solution;

	public MOOCMathHeroSubtraction()
	{
		super(.4);
		int n1 = (int)(Math.random()*9)+1;
		int n2 = (int)(Math.random()*9)+1;
		solution = n1;
		problem = ""+(n1+n2)+"-"+n2;
		color = java.awt.Color.RED;
	}

	public String getProblem()
	{
		return problem;
	}

	public int getSolution()
	{
		return solution;
	}
}

class MOOCMathHeroUtil
{
	public static final int MAX_R = 250;
	public static final int PLAYER_RADIUS = 10;
	public static final int ARROW_LENGTH = 5;
	public static final double ARROW_SPEED = 7;
	public static final double ENEMY_FREQUENCY = .01;
}

class MOOCMathHeroWorld
{
	public MOOCMathHeroPlayer player;
	public MOOCMathHeroLevel[] levels;
	public int level = 0;
	public boolean win = false;
	public boolean lose = false;

	public MOOCMathHeroWorld(MOOCMathHeroPlayer player, MOOCMathHeroLevel... levels)
	{
		this.player = player;
		this.levels = levels;
	}

	public void tryKey(int key)
	{
		for(int i = 0; i < levels.length; i++)
		{
			if(levels[i].key==key)
			{
				level = i;
				i = levels.length;
			}
		}
	}

	public MOOCMathHeroLevel getLevel()
	{
		return levels[level];
	}

	public void update()
	{
		if(!win && !lose)
		{
			getLevel().update();
			player.hit(getLevel().getHitting());
			if(player.dead())
				lose = true;
			if(getLevel().finished()&& !lose)
			{
				player.heal();
				level++;
				if(level==levels.length)
				{
					win = true;
					level--;
				}
			}
		}
	}

	public void draw(Graphics g)
	{
		getLevel().drawEnemies(g);
		player.draw(g);
		g.setColor(Color.BLACK);
		g.drawString("Level: "+(level+1),5,15);
		g.drawString("Key: "+getLevel().key,MOOCMathHeroUtil.MAX_R*2-90,15);
		if(win)
		{
			g.setColor(Color.GREEN);
			g.setFont(new Font("Verdana",Font.BOLD,14));
			g.drawString("You WIN!",140,150);
		}
		if(lose)
		{
			g.setColor(Color.RED);
			g.setFont(new Font("Verdana",Font.BOLD,14));
			g.drawString("You lose.",140,150);
		}
	}

	public static MOOCMathHeroWorld getWorld()
	{
		MOOCMathHeroPlayer player = new MOOCMathHeroPlayer();

		//Level 1
		Vector<MOOCMathHeroEnemy> l1e = new Vector<MOOCMathHeroEnemy>();
		for(int i = 0; i < 10; i ++) l1e.add(new MOOCMathHeroAddition());
		MOOCMathHeroLevel l1 = new MOOCMathHeroLevel(2,1234567,l1e);

		//Level 2
		Vector<MOOCMathHeroEnemy> l2e = new Vector<MOOCMathHeroEnemy>();
		for(int i = 0; i < 8; i ++) l2e.add(new MOOCMathHeroAddition());
		for(int i = 0; i < 5; i ++) l2e.add(new MOOCMathHeroMultiplication());
		MOOCMathHeroLevel l2 = new MOOCMathHeroLevel(2,6394658,l2e);

		//Level 3
		Vector<MOOCMathHeroEnemy> l3e = new Vector<MOOCMathHeroEnemy>();
		for(int i = 0; i < 5; i ++) l3e.add(new MOOCMathHeroAddition());
		for(int i = 0; i < 8; i ++) l3e.add(new MOOCMathHeroMultiplication());
		MOOCMathHeroLevel l3 = new MOOCMathHeroLevel(3,1563826,l3e);

		//Level 4
		Vector<MOOCMathHeroEnemy> l4e = new Vector<MOOCMathHeroEnemy>();
		for(int i = 0; i < 7; i ++) l4e.add(new MOOCMathHeroAddition());
		for(int i = 0; i < 7; i ++) l4e.add(new MOOCMathHeroMultiplication());
		for(int i = 0; i < 3; i ++) l4e.add(new MOOCMathHeroSubtraction());
		MOOCMathHeroLevel l4 = new MOOCMathHeroLevel(3,1927462,l4e);

		//Level 5
		Vector<MOOCMathHeroEnemy> l5e = new Vector<MOOCMathHeroEnemy>();
		for(int i = 0; i < 7; i ++) l5e.add(new MOOCMathHeroAddition());
		for(int i = 0; i < 7; i ++) l5e.add(new MOOCMathHeroMultiplication());
		for(int i = 0; i < 5; i ++) l5e.add(new MOOCMathHeroSubtraction());
		for(int i = 0; i < 3; i ++) l5e.add(new MOOCMathHeroDivision());
		MOOCMathHeroLevel l5 = new MOOCMathHeroLevel(3,3728465,l5e);

		//Level 6
		Vector<MOOCMathHeroEnemy> l6e = new Vector<MOOCMathHeroEnemy>();
		for(int i = 0; i < 7; i ++) l6e.add(new MOOCMathHeroAddition());
		for(int i = 0; i < 7; i ++) l6e.add(new MOOCMathHeroMultiplication());
		for(int i = 0; i < 7; i ++) l6e.add(new MOOCMathHeroSubtraction());
		for(int i = 0; i < 5; i ++) l6e.add(new MOOCMathHeroDivision());
		MOOCMathHeroLevel l6 = new MOOCMathHeroLevel(3,7384920,l6e);

		//Level 7
		Vector<MOOCMathHeroEnemy> l7e = new Vector<MOOCMathHeroEnemy>();
		for(int i = 0; i < 5; i ++) l7e.add(new MOOCMathHeroBigAddition());
		for(int i = 0; i < 7; i ++) l7e.add(new MOOCMathHeroMultiplication());
		for(int i = 0; i < 7; i ++) l7e.add(new MOOCMathHeroSubtraction());
		for(int i = 0; i < 7; i ++) l7e.add(new MOOCMathHeroDivision());
		MOOCMathHeroLevel l7 = new MOOCMathHeroLevel(3,6374198,l7e);

		//Level 8
		Vector<MOOCMathHeroEnemy> l8e = new Vector<MOOCMathHeroEnemy>();
		for(int i = 0; i < 5; i ++) l8e.add(new MOOCMathHeroBigAddition());
		for(int i = 0; i < 1; i ++) l8e.add(new MOOCMathHeroBigMultiplication());
		for(int i = 0; i < 7; i ++) l8e.add(new MOOCMathHeroSubtraction());
		for(int i = 0; i < 7; i ++) l8e.add(new MOOCMathHeroDivision());
		MOOCMathHeroLevel l8 = new MOOCMathHeroLevel(3,1738295,l8e);

		return new MOOCMathHeroWorld(player,l1,l2,l3,l4,l5,l6,l7,l8);
	}
}