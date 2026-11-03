import java.awt.Color;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.Point;
import java.lang.reflect.*;

public class MOOCAnimalKingdom {
    public static void main(String[] args) {
        MOOCAnimalKingdomCritterFrame frame = new MOOCAnimalKingdomCritterFrame(60, 40);

        frame.add(30, MOOCAnimalKingdomBear.class);
        frame.add(30, MOOCAnimalKingdomTiger.class);
        frame.add(30, MOOCAnimalKingdomWhiteTiger.class);
        frame.add(30, MOOCAnimalKingdomGiant.class);
        frame.add(30, MOOCAnimalKingdomNinjaCat.class);

        frame.add(30, MOOCAnimalKingdomFlyTrap.class);
        frame.add(30, MOOCAnimalKingdomFood.class);

        frame.start();
    }
}

class MOOCAnimalKingdomCritterFrame extends JFrame {
    private MOOCAnimalKingdomCritterModel myModel;
    private MOOCAnimalKingdomCritterPanel myPicture;
    private javax.swing.Timer myTimer;
    private JButton[] counts;
    private JButton countButton;
    private boolean started;
    private static boolean created;
    
    public MOOCAnimalKingdomCritterFrame(int width, int height) {
        // this prevents someone from trying to create their own copy of
        // the GUI components
        if (created)
            throw new RuntimeException("Only one world allowed");
        created = true;

        // create frame and model
        setTitle("CSE142 critter simulation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        myModel = new MOOCAnimalKingdomCritterModel(width, height);

        // set up critter picture panel
        myPicture = new MOOCAnimalKingdomCritterPanel(myModel);
        add(myPicture, BorderLayout.CENTER);

        addTimer();

        constructSouth();

        // initially it has not started
        started = false;
    }

    // construct the controls and label for the southern panel
    private void constructSouth() {
        // add timer controls to the south
        JPanel p = new JPanel();

        final JSlider slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                double ratio = 1000.0 / (1 + Math.pow(slider.getValue(), 0.3));
                myTimer.setDelay((int) (ratio - 180));
            }
        });
        slider.setValue(20);
        p.add(new JLabel("slow"));
        p.add(slider);
        p.add(new JLabel("fast"));

        JButton b1 = new JButton("start");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myTimer.start();
            }
        });
        p.add(b1);
        JButton b2 = new JButton("stop");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myTimer.stop();
            }
        });
        p.add(b2);
        JButton b3 = new JButton("step");
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doOneStep();
            }
        });
        p.add(b3);
        
        // add debug button
        JButton b4 = new JButton("debug");
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myModel.toggleDebug();
                myPicture.repaint();
            }
        });
        p.add(b4);

        // add 100 button
        JButton b5 = new JButton("next 100");
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                multistep(100);
            }
        });
        p.add(b5);

        add(p, BorderLayout.SOUTH);
    }

    // starts the simulation...assumes all critters have already been added
    public void start() {
        // don't let anyone start a second time and remember if we have started
        if (started) {
            return;
        }
        // if they didn't add any critters, then nothing to do
        if (myModel.getCounts().isEmpty()) {
            System.out.println("nothing to simulate--no critters");
            return;
        }
        started = true;
        addClassCounts();
        myModel.updateColorString();
        pack();
        setVisible(true);
    }

    // add right-hand column showing how many of each critter are alive
    private void addClassCounts() {
        Set<Map.Entry<String, Integer>> entries = myModel.getCounts();
        JPanel p = new JPanel(new GridLayout(entries.size() + 1, 1));
        counts = new JButton[entries.size()];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = new JButton();
            p.add(counts[i]);
        }

        // add simulation count
        countButton = new JButton();
        countButton.setForeground(Color.BLUE);
        p.add(countButton);

        add(p, BorderLayout.EAST);
        setCounts();
    }

    private void setCounts() {
        // Set<Map.Entry<String, Integer>> entries = myModel.getCounts();
        int i = 0;
        int max = 0;
        int maxI = 0;
        for (Map.Entry<String, Integer> entry: myModel.getCounts()) {
            String s = String.format("%s =%4d", entry.getKey(),
                                     (int) entry.getValue());
            counts[i].setText(s);
            counts[i].setForeground(Color.BLACK);
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxI = i;
            }
            i++;
        }
        counts[maxI].setForeground(Color.RED);
        String s = String.format("step =%5d", myModel.getSimulationCount());
        countButton.setText(s);
    }

    // add a certain number of critters of a particular class to the simulation
    public void add(int number, Class<? extends MOOCAnimalKingdomCritter> c) {
        // don't let anyone add critters after simulation starts
        if (started) {
            return;
        }
        // temporarily turning on started flag prevents critter constructors
        // from calling add
        started = true;
        myModel.add(number, c);
        started = false;
    }

    // post: creates a timer that calls the model's update
    //       method and repaints the display
    private void addTimer() {
        ActionListener updater = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doOneStep();
            }
        };
        myTimer = new javax.swing.Timer(0, updater);
        myTimer.setCoalesce(true);
    }

    // one step of the simulation
    private void doOneStep() {
        myModel.update();
        setCounts();
        myPicture.repaint();
    }

    // advance the simulation until step % n is 0
    private void multistep(int n) {
        myTimer.stop();
        do {
            myModel.update();
        } while (myModel.getSimulationCount() % n != 0);
        setCounts();
        myPicture.repaint();
    }
}

interface CritterInfo {
    public MOOCAnimalKingdomCritter.Neighbor getFront();
    public MOOCAnimalKingdomCritter.Neighbor getBack();
    public MOOCAnimalKingdomCritter.Neighbor getLeft();
    public MOOCAnimalKingdomCritter.Neighbor getRight();
    public MOOCAnimalKingdomCritter.Direction getDirection();
    public boolean frontThreat();
    public boolean backThreat();
    public boolean leftThreat();
    public boolean rightThreat();
}

class MOOCAnimalKingdomCritterModel {
    // The following constant indicates how often infect should fail for
    // critters who didn't hop on their previous move (0.0 means no advantage,
    // 1.0 means 100% advantage)
    public static final double HOP_ADVANTAGE = 0.2; // 20% advantage

    private int height;
    private int width;
    private MOOCAnimalKingdomCritter[][] grid;
    private Map<MOOCAnimalKingdomCritter, PrivateData> info;
    private SortedMap<String, Integer>critterCount;
    private boolean debugView;
    private int simulationCount;
    private static boolean created;
    
    public MOOCAnimalKingdomCritterModel(int width, int height) {
        // this prevents someone from trying to create their own copy of
        // the GUI components
        if (created)
            throw new RuntimeException("Only one world allowed");
        created = true;

        this.width = width;
        this.height = height;
        grid = new MOOCAnimalKingdomCritter[width][height];
        info = new HashMap<MOOCAnimalKingdomCritter, PrivateData>();
        critterCount = new TreeMap<String, Integer>();
        this.debugView = false;
    }

    public Iterator<MOOCAnimalKingdomCritter> iterator() {
        return info.keySet().iterator();
    }

    public Point getPoint(MOOCAnimalKingdomCritter c) {
        return info.get(c).p;
    }

    public Color getColor(MOOCAnimalKingdomCritter c) {
        return info.get(c).color;
    }

    public String getString(MOOCAnimalKingdomCritter c) {
        return info.get(c).string;
    }

    public void add(int number, Class<? extends MOOCAnimalKingdomCritter> critter) {
        Random r = new Random();
        MOOCAnimalKingdomCritter.Direction[] directions = MOOCAnimalKingdomCritter.Direction.values();
        
        if (info.size() + number > width * height) {
        	throw new RuntimeException("adding too many critters");
        }            
        
        for (int i = 0; i < number; i++) {
            MOOCAnimalKingdomCritter next;
            
            try {
                next = makeCritter(critter);
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + critter + " does not have" +
                                   " the appropriate constructor.");
                System.out.println(e);
                System.exit(1);
                return;
            } catch (Exception e) {
                System.out.println("ERROR: " + critter + " threw an " +
                                   " exception in its constructor.");
                System.out.println(e);
                System.exit(1);
                return;
            }
            
            int x, y;
            
            do {
                x = r.nextInt(width);
                y = r.nextInt(height);
            } while (grid[x][y] != null);
            
            grid[x][y] = next;
            
            MOOCAnimalKingdomCritter.Direction d = directions[r.nextInt(directions.length)];
            info.put(next, new PrivateData(new Point(x, y), d));
        }
        
        String name = critter.getName();
        
        if (!critterCount.containsKey(name)) {
        	critterCount.put(name, number);
        } else {
        	critterCount.put(name, critterCount.get(name) + number);
        }            
    }

    private MOOCAnimalKingdomCritter makeCritter(Class<? extends MOOCAnimalKingdomCritter> critter) throws Exception {
        Constructor<?> c = critter.getConstructors()[0];
        return (MOOCAnimalKingdomCritter) c.newInstance();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getAppearance(MOOCAnimalKingdomCritter c) {
        // Override specified toString if debug flag is true
        if (!debugView) {
        	return info.get(c).string;
        } else {
            PrivateData data = info.get(c);
            if (data.direction == MOOCAnimalKingdomCritter.Direction.NORTH) return "^";
            else if (data.direction == MOOCAnimalKingdomCritter.Direction.SOUTH) return "v";
            else if (data.direction == MOOCAnimalKingdomCritter.Direction.EAST) return ">";
            else return "<";
        }
    }
    
    public void toggleDebug() {
        this.debugView = !this.debugView;
    }

    private boolean inBounds(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    private boolean inBounds(Point p) {
        return inBounds(p.x, p.y);
    }

    // returns the result of rotating the given direction clockwise
    private MOOCAnimalKingdomCritter.Direction rotate(MOOCAnimalKingdomCritter.Direction d) {
        if (d == MOOCAnimalKingdomCritter.Direction.NORTH) {
        	return MOOCAnimalKingdomCritter.Direction.EAST;
        } else if (d == MOOCAnimalKingdomCritter.Direction.SOUTH) {
        	return MOOCAnimalKingdomCritter.Direction.WEST;
        } else if (d == MOOCAnimalKingdomCritter.Direction.EAST) {
        	return MOOCAnimalKingdomCritter.Direction.SOUTH;
        } else {
        	return MOOCAnimalKingdomCritter.Direction.NORTH;
        }
    }

    private Point pointAt(Point p, MOOCAnimalKingdomCritter.Direction d) {
        if (d == MOOCAnimalKingdomCritter.Direction.NORTH) {
        	return new Point(p.x, p.y - 1);
        } else if (d == MOOCAnimalKingdomCritter.Direction.SOUTH) {
        	return new Point(p.x, p.y + 1);
        } else if (d == MOOCAnimalKingdomCritter.Direction.EAST) {
        	return new Point(p.x + 1, p.y);
        } else {
        	return new Point(p.x - 1, p.y);
        }
    }

    private Info getInfo(PrivateData data, Class<? extends MOOCAnimalKingdomCritter> original) {
        MOOCAnimalKingdomCritter.Neighbor[] neighbors = new MOOCAnimalKingdomCritter.Neighbor[4];
        MOOCAnimalKingdomCritter.Direction d = data.direction;
        boolean[] neighborThreats = new boolean[4];
        
        for (int i = 0; i < 4; i++) {
            neighbors[i] = getStatus(pointAt(data.p, d), original);
            
            if (neighbors[i] == MOOCAnimalKingdomCritter.Neighbor.OTHER) {
                Point p = pointAt(data.p, d);
                PrivateData oldData = info.get(grid[p.x][p.y]);
                neighborThreats[i] = d == rotate(rotate(oldData.direction));
            }
            
            d = rotate(d);
        }
        
        return new Info(neighbors, data.direction, neighborThreats);
    }

    private MOOCAnimalKingdomCritter.Neighbor getStatus(Point p, Class<? extends MOOCAnimalKingdomCritter> original) {
        if (!inBounds(p)){
        	return MOOCAnimalKingdomCritter.Neighbor.WALL;
        } else if (grid[p.x][p.y] == null) {
        	return MOOCAnimalKingdomCritter.Neighbor.EMPTY;
        } else if (grid[p.x][p.y].getClass() == original) {
        	return MOOCAnimalKingdomCritter.Neighbor.SAME;
        } else {
        	return MOOCAnimalKingdomCritter.Neighbor.OTHER;
        }
    }

    public void update() {
        simulationCount++;
        Object[] list = info.keySet().toArray();
        Collections.shuffle(Arrays.asList(list));

        // This keeps track of critters that are locked and cannot be 
        // infected this turn. The happens when: 
        // * A Critter is infected
        // * A Critter hops
        Set<MOOCAnimalKingdomCritter> locked = new HashSet<MOOCAnimalKingdomCritter>();
        
        for (int i = 0; i < list.length; i++) {
            MOOCAnimalKingdomCritter next = (MOOCAnimalKingdomCritter)list[i];
            PrivateData data = info.get(next);
            if (data == null) {
                // Happens when creature was infected earlier in this round.
                continue;
            }
            // Clear any prior setting for having hopped in the past.
            boolean hadHopped = data.justHopped;
            data.justHopped = false;
            Point p = data.p;
            Point p2 = pointAt(p, data.direction);

            
            // Try to perform the critter's action.
            MOOCAnimalKingdomCritter.Action move = next.getMove(getInfo(data, next.getClass()));
            
            if (move == MOOCAnimalKingdomCritter.Action.LEFT) {
            	data.direction = rotate(rotate(rotate(data.direction)));
            }                
            else if (move == MOOCAnimalKingdomCritter.Action.RIGHT) {
            	data.direction = rotate(data.direction);
            }                
            else if (move == MOOCAnimalKingdomCritter.Action.HOP) {
                if (inBounds(p2) && grid[p2.x][p2.y] == null) {
                    grid[p2.x][p2.y] = grid[p.x][p.y];
                    grid[p.x][p.y] = null;
                    data.p = p2;
                    locked.add(next); //successful hop locks a critter from
                                      // being infected for the rest of the
                                      // turn
                    data.justHopped = true;  // remember a successful hop
                }
            } else if (move == MOOCAnimalKingdomCritter.Action.INFECT) {
                if (inBounds(p2) && grid[p2.x][p2.y] != null
                    && grid[p2.x][p2.y].getClass() != next.getClass()
                    && !locked.contains(grid[p2.x][p2.y])
                    && (hadHopped || Math.random() >= HOP_ADVANTAGE)) {
                    MOOCAnimalKingdomCritter other = grid[p2.x][p2.y];
                    // remember the old critter's private data
                    PrivateData oldData = info.get(other);
                    // then remove that old critter
                    String c1 = other.getClass().getName();
                    critterCount.put(c1, critterCount.get(c1) - 1);
                    String c2 = next.getClass().getName();
                    critterCount.put(c2, critterCount.get(c2) + 1);
                    info.remove(other);
                    // and add a new one to the grid
                    try {
                        grid[p2.x][p2.y] = makeCritter(next.getClass());
                        // This critter has been infected and is now locked
                        // for the rest of this turn
                        locked.add(grid[p2.x][p2.y]);
                    } catch (Exception e) {
                        throw new RuntimeException("" + e);
                    }
                    // and add to the map
                    info.put(grid[p2.x][p2.y], oldData);
                    // but it's new, so it didn't just hop
                    oldData.justHopped = false;
                }
            }
        }
        updateColorString();
    }

    // calling this method causes each critter to update the stored color and
    // text for toString; should be called each time update is performed and
    // once before the simulation begins
    public void updateColorString() {
        for (MOOCAnimalKingdomCritter next : info.keySet()) {
            info.get(next).color = next.getColor();
            info.get(next).string = next.toString();
        }
    }

    public Set<Map.Entry<String, Integer>> getCounts() {
        return Collections.unmodifiableSet(critterCount.entrySet());
    }

    public int getSimulationCount() {
        return simulationCount;
    }

    private class PrivateData {
        public Point p;
        public MOOCAnimalKingdomCritter.Direction direction;
        public Color color;
        public String string;
        public boolean justHopped;

        public PrivateData(Point p, MOOCAnimalKingdomCritter.Direction d) {
            this.p = p;
            this.direction = d;
        }

        public String toString() {
            return p + " " + direction;
        }
    }

    // An object used to query a critter's state (neighbors, direction)
    private static class Info implements CritterInfo {
        private MOOCAnimalKingdomCritter.Neighbor[] neighbors;
        private MOOCAnimalKingdomCritter.Direction direction;
        private boolean[] neighborThreats;

        public Info(MOOCAnimalKingdomCritter.Neighbor[] neighbors, MOOCAnimalKingdomCritter.Direction d,
                    boolean[] neighborThreats) {
            this.neighbors = neighbors;
            this.direction = d;
            this.neighborThreats = neighborThreats;
        }

        public MOOCAnimalKingdomCritter.Neighbor getFront() {
            return neighbors[0];
        }

        public MOOCAnimalKingdomCritter.Neighbor getBack() {
            return neighbors[2];
        }

        public MOOCAnimalKingdomCritter.Neighbor getLeft() {
            return neighbors[3];
        }

        public MOOCAnimalKingdomCritter.Neighbor getRight() {
            return neighbors[1];
        }

        public MOOCAnimalKingdomCritter.Direction getDirection() {
            return direction;
        }

        public boolean frontThreat() {
            return neighborThreats[0];
        }
        
        public boolean backThreat() {
            return neighborThreats[2];
        }
            
        public boolean leftThreat() {
            return neighborThreats[3];
        }
        
        public boolean rightThreat() {
            return neighborThreats[1];
        }
    }
}

class MOOCAnimalKingdomCritterPanel extends JPanel {
    private MOOCAnimalKingdomCritterModel myModel;
    private Font myFont;
    private static boolean created;

    public static final int FONT_SIZE = 12;

    public MOOCAnimalKingdomCritterPanel(MOOCAnimalKingdomCritterModel model) {
        // this prevents someone from trying to create their own copy of
        // the GUI components
        if (created) {
        	throw new RuntimeException("Only one world allowed");
        }
        
        created = true;

        myModel = model;
        // construct font and compute char width once in constructor
        // for efficiency
        myFont = new Font("Monospaced", Font.BOLD, FONT_SIZE + 4);
        setBackground(Color.CYAN);
        setPreferredSize(new Dimension(FONT_SIZE * model.getWidth() + 20,
                                       FONT_SIZE * model.getHeight() + 20));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(myFont);
        Iterator<MOOCAnimalKingdomCritter> i = myModel.iterator();
        
        while (i.hasNext()) {
            MOOCAnimalKingdomCritter next = i.next();
            Point p = myModel.getPoint(next);
            String appearance = myModel.getAppearance(next);
            g.setColor(Color.BLACK);
            g.drawString("" + appearance, p.x * FONT_SIZE + 11, p.y * FONT_SIZE + 21);
            g.setColor(myModel.getColor(next));
            g.drawString("" + appearance, p.x * FONT_SIZE + 10, p.y * FONT_SIZE + 20);
        }
    }
}

class MOOCAnimalKingdomBear extends MOOCAnimalKingdomCritter {
	private final Color color;
	private Direction direction;
	private String denotation;
	
	public MOOCAnimalKingdomBear() {
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

class MOOCAnimalKingdomCritter {
    public MOOCAnimalKingdomCritter() {

    }

    public static enum Neighbor {
        WALL, EMPTY, SAME, OTHER
    };

    public static enum Action {
        HOP, LEFT, RIGHT, INFECT
    };

    public static enum Direction {
        NORTH, SOUTH, EAST, WEST
    };

    // This method should be overriden (default action is turning left)
    public Action getMove(CritterInfo info) {
        return Action.LEFT;
    }

    // This method should be overriden (default color is black)
    public Color getColor() {
        return Color.BLACK;
    }

    // This method should be overriden (default display is "?")
    public String toString() {
        return "?";
    }

    // This prevents critters from trying to redefine the definition of
    // object equality, which is important for the simulator to work properly.
    public final boolean equals(Object other) {
        return this == other;
    }
}

class MOOCAnimalKingdomFlyTrap extends MOOCAnimalKingdomCritter {
	private final Color color = Color.RED;
	
    public MOOCAnimalKingdomFlyTrap() {

    }

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

class MOOCAnimalKingdomFood extends MOOCAnimalKingdomCritter {
	private final Color color = Color.GREEN;
	
    public MOOCAnimalKingdomFood() {

    }

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

class MOOCAnimalKingdomGiant extends MOOCAnimalKingdomCritter {
	private final Color color = Color.PINK;
	private String denotations[] = {"O", "0", "8"};
	private String currentDenotation;
	private int denotationsCounter;
	
    public MOOCAnimalKingdomGiant() {
    	this.currentDenotation = denotations[0];
    	this.denotationsCounter = 0;
    }

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

class MOOCAnimalKingdomNinjaCat extends MOOCAnimalKingdomCritter {
	private final Color color = Color.MAGENTA;
    
    public MOOCAnimalKingdomNinjaCat() {

    }
	
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

class MOOCAnimalKingdomTiger extends MOOCAnimalKingdomCritter {
	private Color[] colors = {Color.YELLOW, Color.ORANGE, Color.RED};
	private Color currentColor;
	private int colorChangeCounter;
	
	public MOOCAnimalKingdomTiger() {
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

class MOOCAnimalKingdomWhiteTiger extends MOOCAnimalKingdomTiger {
	private final Color color = Color.WHITE;
	private boolean hasInfected;
	
    public MOOCAnimalKingdomWhiteTiger() {
    	this.hasInfected = false;
    }

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