/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.adk.maneuver;

import edu.adk.camera.Target;
import edu.adk.camera.Tracker;
import edu.adk.mechanism.Drive;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 *
 * @author mtidd
 */
public class CameraDrive extends Maneuver{
    
    Drive drive;
    Tracker tracker = new Tracker();

    public CameraDrive(Drive drive, Maneuver pass,Maneuver fail,Maneuver timeout,double maxTime){
        super(pass,fail,timeout,maxTime);
        this.drive = drive;
    }
    
    protected void run() {
        AxisCamera.getInstance().freshImage();
        try {
            Target target = tracker.getTarget();
            double y_speed = -.9 + target.getYPosition();
            double x_speed = target.getXPosition();
            if(target.getYPosition() > -.9 )
                drive.run(-y_speed, x_speed);
            else if(target.getXPosition() > .5 && target.getXPosition() < -.5)
                drive.run(0,x_speed);
            
        } catch (AxisCameraException e) {
        } catch (NIVisionException e) {
        }
    }


    public void stop() {
        drive.stop();
    }

}
