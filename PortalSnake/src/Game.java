import java.util.*;

/**
 * The Game class represents a running instance of the PortalSnake game. It
 * keeps track of the Snake object, lists of Apple, Rock, and PortalPair
 * objects, the current score, and whether the player has won.
 * 
 * The game engine (which we've written for you) will create a new instance of
 * this class when the player chooses a level to play. 
 * 
 * At each iteration of the game loop, the game engine calls the update() method
 * in the Game class. The update() method tells each of the objects in the game
 * to update itself based on the rules of the game. It then checks if the game
 * is over or not.
 */
public class Game 
{	
	// Store the instances of the game objects that you create for your game
	// in these member variables: 
	private Snake snake;
	private ArrayList<Apple> apples;		
	//private ArrayList<Rock> rocks;			
	private ArrayList<PortalPair> portals;	
	private int controlVariable;
	private int score;
	private Rock r;
	private Apple newApple;
	private PortalPair pairA;
	private PortalPair pairB;
	private PortalPair pairC;

	// Random number generated seeded according to class
	Random rnd = new Random();

	// Create member variables to track the controlType, score (ie number
	// of apples eaten by the snake), and whether the game has been won
	// or lost here.

	/**
	 * 
	 * TODO: Have students implement this
	 * 
	 * @param level - "RANDOM" or descriptions of the object to load
	 * @param controlType - 1: classic, 2: analog, 3: slither
	 */
	public Game(String level, int controlType) {
		createRandomLevel();
		// Initialize all member variables
		controlVariable = controlType;
		// Create a random level when level contains: RANDOM
		// Otherwise load the objects described in the string level
	}

	/**
	 * TODO: Have students implement this.
	 * create a new level with randomly positioned:
	 * Snake(1), Rocks(20), Apples(8), and PortalPairs(3)
	 */
	public void createRandomLevel()
	{
		// create a snake: positioned as specified in the write-up
		snake = new Snake((Engine.getWidth()/2), (Engine.getHeight()/2));
		// create 20 randomly positioned rocks
		//rocks = new ArrayList<Rock>();
		//int a = 0;
		//while(a < 5){
		//a++;
		//r = new Rock(rnd.nextFloat()*Engine.getWidth(), 
		//	rnd.nextFloat()*Engine.getHeight());
		//rocks.add(r);
		//}		
		// create 8 randomly positioned apples
		apples = new ArrayList<Apple>();
		int i = 0;
		while(i < 8){
			i++;
			newApple = new Apple(rnd.nextFloat()*Engine.getWidth(), 
					rnd.nextFloat()*Engine.getHeight());
			apples.add(newApple);
		}
		// create 3 randomly positioned portal pairs
		portals = new ArrayList<PortalPair>();

		pairA = new PortalPair("A", 
				rnd.nextFloat()*Engine.getWidth(), 
				rnd.nextFloat()*Engine.getHeight(), 
				rnd.nextFloat()*Engine.getWidth(), 
				rnd.nextFloat()*Engine.getHeight());
		portals.add(pairA);
		pairB = new PortalPair("B", 
				rnd.nextFloat()*Engine.getWidth(), 
				rnd.nextFloat()*Engine.getHeight(), 
				rnd.nextFloat()*Engine.getWidth(), 
				rnd.nextFloat()*Engine.getHeight());
		portals.add(pairB);
		pairC = new PortalPair("C", 
				rnd.nextFloat()*Engine.getWidth(), 
				rnd.nextFloat()*Engine.getHeight(), 
				rnd.nextFloat()*Engine.getWidth(), 
				rnd.nextFloat()*Engine.getHeight());
		portals.add(pairC);
	}

	/**
	 * Loads a level from a String description.
	 * 
	 * Initializes all of the class private fields which hold the Snake object
	 * and the lists of Apple, Rock, and PortalPair objects from the provided
	 * String which contains  
	 * 
	 * TODO: Implement this method
	 * 
	 * @param level - a string containing the names and locations of objects
	 */
	public void loadLevel(String level)
	{
		// Initialize Rock, Apple, and PortalPair ArrayLists
		// Create a new scanner to read the level description		
		// Loop through lines in the level description
		// Get the next line
		// Split the line into tokens			
		// Determine the type of object to add to the level
		// If it's a snake, create a new snake at the x and y
		// coordinates specified by the second and third tokens

		// If it's an apple, create a new apple at the x and y
		// coordinates specified by the second and third tokens, and add
		// it to the list of apples

		// If it's a rock, create a new rock at the x and y coordinates
		// specified by the second and third tokens and add it to the
		// list of rocks

		// If it's a portal pair, create a new PortalPair with the
		// name equal to the second token, with the first portal at the
		// x and y coordinates specified by the third and fourth
		// tokens, and the second portal at the x and y coordinates
		// specified by the fifth and sixth tokens

		// If it's anything else, ignore it.

		// Close the scanner		
	}

	/**
	 * Updates the game objects.
	 * 
	 * Goes through each of the objects--the snake, rocks, apples, and portals--
	 * and tells them to behave according to the rules of the game. This method
	 * returns true if the game should continue, or false if the game is over.
	 * 
	 * TODO: Implement this
	 * 
	 * @return - false if the game is over, otherwise true
	 */
	public boolean update()
	{
		// Tell the snake to update itself
		snake.updateMoveDirection(controlVariable);
		// Tell the snake to die if it's colliding with itself
		snake.dieIfCollidingWithOwnBody();
		// For each rock, tell the rock to kill the snake if the two are
		// colliding
		for (int i = 0; i < 20; i++) {
			//rocks.get(i).killSnakeIfColliding(snake);
		}
		// For each apple, tell the apple to be eaten by the snake if the two
		// are colliding, and if so update the score
		for (int i = 0; i < 8; i++) {
			if (apples.get(i).getEatenBySnakeIfColliding(snake)) {
				score++;
			}
		}
		// For each portal pair, tell the pair to teleport the snake if the two
		// are colliding
		for (int i = 0; i < 3; i++) {
			portals.get(i).teleportSnakeIfColliding(snake);
		}
		// Check for win/loss

		// If the score is equal to the number of apples, make sure playerHasWon()
		// will return true and then return false 
		if (playerHasWon()) {
			playerHasWon();
			score = 0;
			return false;
		}
		// Else, if the snake is dead, make sure playerHasWon() will return false
		// and then return false  
		if (snake.getGraphicObject().getType() == "DEAD") {
			playerHasWon();
			return false;
		}
		// If the game isn't over, return true
		return true;
	}

	/**
	 *  Returns true if the player has won
	 * 
	 * TODO: Implement this
	 * 
	 * @return true when the player has won, and false when they have lost or
	 * the game is not over
	 */
	public boolean playerHasWon()
	{
		if (getScore() == 8) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Returns the player's score.
	 * 
	 * TODO: Implement this.
	 * 
	 * @return the current score (number of apples eaten)
	 */
	public int getScore() {
		return score;
	}	

	/**
	 * There is nothing left to implement in this method, it simply calls
	 * Engine.startEngineAndGame(), which in turn starts the Engine and creates
	 * an instance of this Game class.  The engine will then repeatedly call
	 * the update() method on this Game until it returns false.
	 * 
	 * If you want to turn off the logging you can change the parameter being
	 * passed to startEngineAndGame to false.  
	 *  
	 * @param args - command line arguments
	 */
	public static void main(String[] args)
	{
		Application.startEngineAndGame(true);
	}
}
