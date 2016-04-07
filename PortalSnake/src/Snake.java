import java.util.ArrayList;

/**
 * The Snake class represents the player-controlled snake. 
 *
 * The Game class instantiates this class exactly once, when a new level is
 * loaded.
 */
public class Snake 
{
	// Private variables to hold the GraphicObject associated with the snake's head
	// and an ArrayList of GraphicObject associated with the snake's body
	private GraphicObject snakeHead;
	private ArrayList<GraphicObject> bodyParts;

	/**
	 * Initializes a new Snake object at the specified (x,y) position.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param x		the initial x position of the snake
	 * @param y		the initial y position of the snake
	 */
	public Snake(float x, float y)
	{
		// Initialize new ArrayList to hold body segments
		bodyParts = new ArrayList<GraphicObject>();
		// Initialize the head
		snakeHead = new GraphicObject("HEAD", x, y);
		// Set the speed of the head
		snakeHead.setSpeed(4);
		// Set the direction of the head
		snakeHead.setDirection(90);
		// Add the head to the list of body segments
		bodyParts.add(snakeHead);
		// Add four body segments (grow the snake four times)
		grow();
		grow();
		grow();
		grow();
	}

	/**
	 * Returns the GraphicObject associated with the head of this snake.
	 *  
	 * TODO: Implement this.
	 * 
	 * @return the GraphicObject associated with the head of this snake
	 */
	public GraphicObject getGraphicObject() {
		return snakeHead;
	}

	/**
	 * Grows the snake by one body segment.
	 * 
	 * TODO: Implement this.
	 */
	public void grow()
	{
		GraphicObject body;
		int x = Engine.getWidth() / 2;
		int y = Engine.getHeight() / 2;
		GraphicObject lastSegment;
		// Create a new GraphicObject with type "BODY"
		body = new GraphicObject("BODY", x, y);
		// Find the last body segment in the list of body segments
		lastSegment = bodyParts.get(bodyParts.size() - 1); 
		// Set the leader of the new body segment to be the last body segment
		body.setLeader(lastSegment);
		// Add the new body segment to the end of the list of body segments
		bodyParts.add(body);
	}

	/**
	 * Reads keyboard input and changes the snake's direction as necessary.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param controlType - 1: classic, 2: analog, 3: slither
	 */
	public void updateMoveDirection(int controlType)
	{
		float direction = snakeHead.getDirection();

		// if controlType is one
		if (controlType == 1) {
			// implementation for controlType one
			if (Engine.isKeyPressed("RIGHT")) {
				snakeHead.setDirection(direction - 90);
			} else if (Engine.isKeyPressed("LEFT") == true) {
				snakeHead.setDirection(direction + 90);
			}
		}

		// if controlType is two
		if (controlType == 2) {
			// implementation for controlType two
			if(Engine.isKeyPressed("RIGHT")) {
				snakeHead.setDirection(direction - 6);
			}
			if (Engine.isKeyHeld("RIGHT")) {	
				snakeHead.setDirection(direction - 6);
			}
			if(Engine.isKeyPressed("LEFT")) {
				snakeHead.setDirection(direction + 6);
			}
			if (Engine.isKeyHeld("LEFT")) {
				snakeHead.setDirection(direction + 6);
			}
		}

		// if controlType is three
		if (controlType == 3) {
			// implementation for controlType three
			if (Engine.isKeyPressed("SPACE")) {
				snakeHead.setDirection(direction + 6);
			}
			if (Engine.isKeyHeld("SPACE")) {
				snakeHead.setDirection(direction + 6);
			}
			if (Engine.isKeyPressed("SPACE")) {
				snakeHead.setDirection(direction - 6);
			}
			if (Engine.isKeyHeld("SPACE") == false) {
				snakeHead.setDirection(direction - 6);
			}
		}
	}

	/**
	 * Kills the snake if the head is colliding with any of the body segments.
	 * 
	 * TODO: Implement this.
	 */
	public void dieIfCollidingWithOwnBody()
	{
		//boolean GraphicObject.isCollidingWith(GraphicObject other)
		// For each game object in the body...		
		// if the head is colliding with this segment...		
		// tell the snake to die.
		
		for (int i = 1; i < bodyParts.size(); i++) {
			if (snakeHead.isCollidingWith(bodyParts.get(i))) {
				snakeHead.setType("DEAD");
				bodyParts.get(i).setType("DEAD");
			}
		}
	}

	/**
	 * Kills the snake.
	 * 
	 * TODO: Implement this.
	 */
	/**
	 * 
	 */
	public void die()
	{
		// Set the head's type to "DEAD"
		snakeHead.setType("DEAD");
		// For each GraphicObject in the snake's body, set its type to "DEAD"
		for (int i = 1; i < bodyParts.size(); i++) {
			bodyParts.get(i).setType("DEAD");
		}
	}

	/**
	 * Returns true if the snake is dead.
	 * 
	 * TODO: Implement this.
	 * 
	 * @return true if the snake is dead, false otherwise
	 */
	public boolean isDead() {
		if (snakeHead.getType().equals("DEAD")) {
			return true;
		} else {
			return false;
		}
	}
}
