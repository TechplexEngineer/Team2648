/*----------------------------------------------------------------------------*
 *
 * Basic Robot Code Compiled By Blake from Team 2648
 *
 * Questions? blake (at) team2648 [dot] com
 *
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj;

//import edu.wpi.first.wpilibj.camera.AxisCamera;

import edu.wpi.first.wpilibj.camera.AxisCamera;


/* Imports are not needed when the package is edu.wpi.first.wpilibj; contrary to that of the basic netbeans frc project
import edu.wpi.first.wpilibj.RobotDrive;*/
public class LunacyBot extends SimpleRobot
{
    private RobotDrive drivetrain;
    private Joystick leftStick; //One Joystick controls the robot
    private DigitalInput index; //This is a digital input from our limit switch, allows for the indexing of the lift
    private Relay horn; // Oh Yes we have a horn!
    private Victor lift; // The actual lift, to raise the balls to the kicker
    private Victor roller; // Brings the Orbit balls into the front of the base of the lift
    private Victor kicker; // The "chainsaw" typeish kicker that throws the balls out of the top of our robot.
    private boolean debug = false;
    private AxisCamera ac;

    public LunacyBot()
    {
        ac = AxisCamera.getInstance();
        drivetrain = new RobotDrive(1, 2); // create RobotDrive
        leftStick = new Joystick(1); // and joysticks
        index = new DigitalInput(1); // and the Digital IO
        horn = new Relay(4); // and the Horn
        horn.setDirection(Relay.Direction.kForward );
        lift = new Victor(3); // and the lift, its only a victor because we burnt out like 3 Jaguars
        roller = new Victor(5); // and the rollers,
        kicker = new Victor(6); // and our chainsaw
    }

    public void autonomous()
    {
        stop();
        for (int i = 0; i < 4; i++) {
            d("FWD");
            drivetrain.setLeftRightMotorSpeeds(1.0, 1.0);
            Timer.delay(1);
        }
        for (int i = 0; i < 11; i++) {
            d("Spinning");
            drivetrain.setLeftRightMotorSpeeds(-1.0, 1.0);
            Timer.delay(1);
        }
        d("done");
        stop();
    }

    public void operatorControl()
    {
        getWatchdog().setEnabled(false); // Stupid WATCHDOG
        while (isOperatorControl()) //Lets loop while in operator control
        {
            //getWatchdog().feed();
            //getWatchdog.feed();
            //ac.getImage();
            roller.set(1); //run front rolers
            //kicker.set(1); prevent jerking
            //set kicker direction(button to change direction [hold or toggle])
            if (leftStick.getTrigger())
            {
                lift.set(-1);
            }
            else if (index.get())
            {
                lift.set(-1);
            }
            else
                lift.set(0);

            //Kicker code
            if (leftStick.getRawButton(7))
                kicker.set(1);
            else
                kicker.set(-1);

            System.out.println(leftStick.getThrottle());
            //Horn code
            if (leftStick.getRawButton(3))
            {
                horn.set(Relay.Value.kOn);
                //horn.set((leftStick.getThrottle()) * (0.5) + (0.5));// math issue
            }
            else
                horn.set(Relay.Value.kOff);

            //make mr robo drive
            drivetrain.arcadeDrive(leftStick);
            //slow dwn he iterations, seems to work smoohely
            Timer.delay(0.005);
        }
        d("END OC");
        stop();
    }

    /**
     * Stops all motors
     *
     */
    public void stop()
    {
        drivetrain.drive(0.0, 0.0);
        lift.set(0);
        roller.set(0);
        kicker.set(0);
        horn.set(Relay.Value.kOff);
    }

    /**
     * Only System.out.println when debug = true.
     */
    public void d(String str)
    {
        if (debug)
            System.out.println(str);
    }
}
