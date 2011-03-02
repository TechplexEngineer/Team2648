/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.adk.maneuver;

import edu.adk.mechanism.Drive;

/**
 *
 * @author mtidd
 */
public class DriveForward extends Maneuver{
    Drive drive;
    
    public DriveForward(Drive drive, Maneuver pass, Maneuver fail, Maneuver timeout, double maxTime){
        super(pass,fail,timeout,maxTime);
        this.drive = drive;
    }

    protected void run() {
        drive.goForward();
    }

    public void stop() {
        drive.stop();
    }
}
