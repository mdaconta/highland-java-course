/* File: HurdleJumpingKarel.java
 * This program has Karel jumping eight hurdles each of arbitrary
 * height.
 */
import stanford.karel.*;

public class HurdleJumpingKarel extends SuperKarel {

	public void run() {
		for(int i = 0; i < 8; i++) {
			if(frontIsClear()) {
				//No hurdle to clear, so just move
				move();
			} else {
				jumpOneHurdle();
			}
		}
	}

	/* Moves Karel up and over the hurdle.
	 * 
	 * Precondition:  Karel is at facing hurdle, facing East.
	 * Postcondition: Karel has the hurdle to her back, facing East.
	 */
	private void jumpOneHurdle() {
		ascendOneHurdle();
		move();
		descendOneHurdle();
	}

	/* Moves Karel up to the top of the hurdle.
	 * 
	 * Precondition:  Karel is at facing hurdle, facing East.
	 * Postcondition: Karel is as high as the hurdle (but not over) facing East.
	 */
	private void ascendOneHurdle() {
		turnLeft();
		while(rightIsBlocked()) {
			move();
		}
		turnRight();
	}
	
	/* Moves Karel back down to the ground.
	 * 
	 * Precondition:  Karel is as high as the hurdle (but not over) facing East.
	 * Postcondition: Karel has the hurdle to her back, facing East.
	 */
	private void descendOneHurdle() {
		turnRight();
		moveToWall();
		turnLeft();
	}
	
	/* Moves Karel to the nearest wall.
	 */
	private void moveToWall() {
		while (frontIsClear()) {
			move();
		}
	}
}
