/*
 * File: BlankKarel.java
 * ---------------------
 * This is a blank Karel project that you can change at will. Remember, if you change
 * the class name, you'll need to change the filename so that it matches.
 * If you want to make the program load with a specific world, make the
 * world file named the same as this class. For example, when this program is
 * run, it would try to load BlankKarel.w if there is one.
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component.Identifier;
import acm.program.*;
import stanford.karel.*;

public class JoystickKarel extends SuperKarel {
    private ArrayList<Controller> foundControllers;
    
	public void run() {
		foundControllers = new ArrayList<>();
        searchForControllers();
        
        // If at least one controller was found we start showing controller data on window.
        if(!foundControllers.isEmpty())
        {
        		System.out.println("We have a controller!  Let's go!!");
        		while(true)
            {
                    // Currently selected controller.
                    Controller controller = foundControllers.get(0);

                    // Pull controller for current data, and break while loop if controller is disconnected.
                    if( !controller.poll() ){
                        System.out.println("Controller is disconnected!");
                        break;
                    }
                    
                    // X axis and Y axis
                    float xAxis = 0;
                    float yAxis = 0;
                            
                    // Go trough all components of the controller.
                    Component[] components = controller.getComponents();
                    for(int i=0; i < components.length; i++)
                    {
                        Component component = components[i];
                        Identifier componentIdentifier = component.getIdentifier();  
                        
                        // Axes
                        if(component.isAnalog()){
                            float axisValue = component.getPollData();
                            
                            // X axis
                            if(componentIdentifier == Component.Identifier.Axis.X){
                                xAxis = axisValue;
                                continue; // Go to next component.
                            }
                            // Y axis
                            if(componentIdentifier == Component.Identifier.Axis.Y){
                                yAxis = axisValue;
                                continue; // Go to next component.
                            }
                        }
                        
                    }
                    
                    moveKarel(xAxis, yAxis);
                    
                    // We have to give processor some rest.
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        // do nothing
                    }

                }
        }
        else
        		System.out.println("No controller found!");
		
	}
	
    private void searchForControllers() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for(int i = 0; i < controllers.length; i++){
            Controller controller = controllers[i];
            
            if (
                    controller.getType() == Controller.Type.STICK || 
                    controller.getType() == Controller.Type.GAMEPAD || 
                    controller.getType() == Controller.Type.WHEEL ||
                    controller.getType() == Controller.Type.FINGERSTICK
               )
            {
                // Add new controller to the list of all controllers.
                foundControllers.add(controller);                
            }
        }
    }
    
    public void moveKarel(float xAxis, float yAxis)
    {
    		System.out.println("In moveKarel(x:" + xAxis + ",y:" + yAxis +")");
    		if (yAxis == -1.0f) {
    			goNorth();
    		}
    		
    		if (yAxis == 1.0f)
    		{
    			goSouth();
    		}
    		
    		if (xAxis == 1.0f) 
    		{
    			goEast();
    		}
    		
    		if (xAxis == -1.0f)
    		{
    			goWest();
    		}
    }
    
    public void goNorth() {
    		while (this.notFacingNorth()) {
    			turnLeft();
    		}
    		move();
    }
    
    public void goSouth() {
		while (this.notFacingSouth()) {
			turnLeft();
		}
		move();
    }
    
    public void goEast() {
		while (this.notFacingEast()) {
			turnLeft();
		}
		move();
    }

    public void goWest() {
		while (this.notFacingWest()) {
			turnLeft();
		}
		move();
    }
}
