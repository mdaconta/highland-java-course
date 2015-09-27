/* File: InverseBeeperKarel.java
 * 
 * Given a world with one street (i.e. row), have Karel swap
 * the states of the beepers in each spot (so if there is a
 * beeper, pick it; if there is no beeper, put one).
 */

import stanford.karel.*;

public class InverseBeepersKarel extends SuperKarel {

	public void run() {
		while(frontIsClear()) {
			swapBeeperState();
			move();
		}
		swapBeeperState();
	}
	
	/*
	 * Swaps the state of a beeper for a given corner (i.e. cell)
	 */
	private void swapBeeperState() {
		if (beepersPresent()) {
			pickBeeper();
		} else {
			putBeeper();
		}
	}
}