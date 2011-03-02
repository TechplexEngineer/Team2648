/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.adk.maneuver;

import edu.adk.mechanism.Arm;

/**
 *
 * @author mtidd
 */
public class Cap extends Maneuver {
    
    Arm arm;
    
    public Cap(Arm arm, Maneuver pass, Maneuver fail, Maneuver timeout, double maxTime){
        super(pass,fail,timeout,maxTime);
        this.arm = arm;
    }
    
    protected void run() {
        arm.elbowDwn();
    }

    public void stop() {
    }

}
