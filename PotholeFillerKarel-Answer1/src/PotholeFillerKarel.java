/*
 * File: BlankKarel.java
 * ---------------------
 * This is a blank Karel project that you can change at will. Remember, if you change
 * the class name, you'll need to change the filename so that it matches.
 * If you want to make the program load with a specific world, make the
 * world file named the same as this class. For example, when this program is
 * run, it would try to load BlankKarel.w if there is one.
 */

import acm.program.*;
import stanford.karel.*;

public class PotholeFillerKarel extends SuperKarel {
	public void run() {
		
		printDirection();
		printBlocked();
		
		while (frontIsClear()) {
			move();
			
			if (!rightIsBlocked()) {
				// pothole
				turnRight();
				move();
				
				while (frontIsClear()) {
					putOneBeeper();
					move();
				}
				
				turnLeft();
				while (frontIsClear()) {
					putOneBeeper();
					move();
				}
			
				turnLeft();
				while (frontIsClear() && rightIsBlocked()) {
					putOneBeeper();
					move();
				}
				turnRight();
			}			
		}				
	}
	
	public void putOneBeeper() {
		if (!beepersPresent())
			putBeeper();
	}
	
	public void printBlocked() {
		if (frontIsBlocked())
			System.out.println("front: " + frontIsBlocked());
		if (leftIsBlocked())
			System.out.println("left: " + leftIsBlocked());
		if (rightIsBlocked())
			System.out.println("right: " + rightIsBlocked());
	}
	
	public void printDirection() {
		if (facingNorth())
			System.out.println("Facing north.");
		if (facingEast())
			System.out.println("Facing east.");
		if (facingSouth())
			System.out.println("Facing south.");
		if (facingWest())
			System.out.println("Facing west.");
	}
	
	public boolean noPothole() {		
		//turnRight();
		return rightIsBlocked();
	}
}
