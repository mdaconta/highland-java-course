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

public class PotholeFillerKarelAnswer extends SuperKarel {
	public void run() {
		int depthOfPothole= 0;
		int widthOfPothole = 0;
		printDirection();
		printBlocked();
		
		while (frontIsClear()) {
			move();
			
			if (!rightIsBlocked()) {
				// pothole
				depthOfPothole = 0;
				turnRight();
				move(); // jumped into pothole
				depthOfPothole = depthOfPothole + 1;
				
				while (frontIsClear()) {
					depthOfPothole = depthOfPothole + 1;
					System.out.println("depthOfPothole: " + depthOfPothole);
					putOneBeeper();
					move();
				}
				
				// at bottom of pothole
				widthOfPothole = 1;
				
				turnLeft();
				while (frontIsClear()) {
					widthOfPothole = widthOfPothole + 1;
					System.out.println("widthOfPothole:" + widthOfPothole);
					putOneBeeper();
					move();
				}
			
				if (widthOfPothole <= 2) {
				
					turnLeft();
					while (frontIsClear() && rightIsBlocked()) {
						putOneBeeper();
						move();
					}
				
					turnRight(); 
				} else {
					// width of pothole > 2
					// need to turnaround and fill more
					putOneBeeper();
					
					for (int d = 0; d < depthOfPothole-1; d++) {
						// go up one and fill the row
						if (facingEast() && frontIsBlocked()) { // at right wall
							turnLeft();
							move();
							turnRight();
						}
						
						if (facingWest() && frontIsBlocked()) { // at left wall
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
	
	public boolean noPothole() {		
		//turnRight();
		return rightIsBlocked();
	}
}
