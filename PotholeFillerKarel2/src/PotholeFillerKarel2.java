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

public class PotholeFillerKarel2 extends SuperKarel {

	boolean inPothole;
	int lengthOfPothole=0;
	int widthOfPothole=1;
	int numberOfPotholes = 4;
	
	public void run() {		
		
		while (frontIsClear()) {
			move();
			
			if (!rightIsBlocked()) {
				// in pothole
				inPothole = true;
				turnRight();
				move();
				lengthOfPothole = lengthOfPothole + 1;
				
				while (frontIsClear()) {
					putOneBeeper();
					move();
					lengthOfPothole = lengthOfPothole + 1;
				}

				// here we are at the bottom of the pothole
				System.out.println("Length of the Pothole is: " + lengthOfPothole);
				
				turnLeft();
				while (frontIsClear()) {
					putOneBeeper();
					move();
					widthOfPothole = widthOfPothole + 1;
				}
				putOneBeeper();
				
				System.out.println("Width of the Pothole is: " + widthOfPothole);
			
				if (widthOfPothole <= 2) {
					turnLeft();
					while (frontIsClear() && rightIsBlocked()) {
						putOneBeeper();
						move();
					}
					turnRight();
				} else {
					// need to turnaround and fille more
					System.out.println("Width of the Pothole is: " + widthOfPothole);
					System.out.println("Length of the Pothole is: " + lengthOfPothole);
					printDirection();
					for (int w = 0; w < widthOfPothole; w++) {
						// go up one and fill the row
						if (facingEast() && frontIsBlocked()) { // at a wall
							turnLeft();
							move();
							turnRight();
						}
						
						if (facingWest() && frontIsBlocked()) { // at a wall
							turnRight();
							move(); // go up
							turnLeft();
						}
						
						turnAround();
						putOneBeeper();
						while (frontIsClear()) {
							move();
							putOneBeeper();
						}
					}

					System.out.println("Out of filling a large pothole!");
					// move up out of the pothole to be ready for next
					turnLeft();
					move();
					turnRight();
				}
				inPothole = false;
				
				if (!inPothole) {
					lengthOfPothole = 0;
					widthOfPothole = 1;
				}
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
}
