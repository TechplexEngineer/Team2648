/*----------------------------------------------------------------------------*
 *
 * Basic Robot Code Compiled By Blake from Team 2648
 *
 * Questions? blake (at) team2648 [dot] com
 *
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Timer;


/* Imports are not needed when the package is edu.wpi.first.wpilibj; contrary to that of the basic netbeans frc project
import edu.wpi.first.wpilibj.RobotDrive;*/
public class SimpleSoccer extends SimpleRobot {

    private RobotDrive drivetrain;
    private Joystick LS; //One Joystick controls the robot
    private Joystick RS; //controls camera
    private Jaguar winch; // The winch that controls the lift
    private Victor roller; // Controls the soccer balls to placement in front of kicker
    private Victor kicker; // The kicker
    private Victor spinner; // The motor that releases the arm
    //private AxisCamera ac;
    private DigitalInput liftDwn;
    private DigitalInput kickerReady;
    private DigitalInput switch1;
    private DigitalInput switch2;
    private DigitalInput haveBall;
    private Gyro gSensor;
    private DriverStationLCD dsLCD;
    private DriverStation ds;
    private int count, zone;
    private String str1, str2, str3;
    private boolean driveCount = true;
    private Timer time;

    public SimpleSoccer() {
        getWatchdog().setEnabled(false); // Stupid WATCHDOG
        dsLCD = DriverStationLCD.getInstance();
        ds = DriverStation.getInstance();

        drivetrain = new RobotDrive(1, 2); // create RobotDrive
        LS = new Joystick(1); // and joysticks
        RS = new Joystick(2);

        liftDwn = new DigitalInput(14);
        kickerReady = new DigitalInput(13);
        switch1 = new DigitalInput(1);
        switch2 = new DigitalInput(2);
        haveBall = new DigitalInput(5);

        winch = new Jaguar(3); // and the winch
        kicker = new Victor(5); // and the kicker
        roller = new Victor(4); // aned the roller
        spinner = new Victor(6); // aned the roller

        gSensor = new Gyro(1);

        str1 = new String("");
        str2 = new String("");
        str3 = new String("");
        zone = 2;


        time = new Timer();


//        ac = AxisCamera.getInstance();
//        ac.writeResolution(AxisCamera.ResolutionT.k320x240);
//        ac.writeColorLevel(50);
//        ac.writeCompression(0);
//        //ac.writeWhiteBalance(AxisCamera.WhiteBalanceT.hold);
//        ac.writeBrightness(0);


    }

    public void autonomous() {
        dsLCD.updateLCD();
        getWatchdog().setEnabled(false); // Stupid WATCHDOG
        count = 0;  // represents number of kicks
        zone = 2;

        while (isAutonomous()) {


            if (switch1.get() && switch2.get()) //both up
            {
                zone = 1;
            } else if (switch1.get() && !switch2.get()) //up dwn
            {
                zone = 2;
            } else if (!switch1.get() && !switch2.get()) //dwn dwn
            {
                zone = 3;
            } else {
                zone = 2;
            }

            dsLCD.println(DriverStationLCD.Line.kMain6, 1, "");
            dsLCD.println(DriverStationLCD.Line.kUser2, 1, "");
            dsLCD.println(DriverStationLCD.Line.kUser3, 1, "");
            //dsLCD.println(DriverStationLCD.Line.kUser4, 1, "");

            ds.setDigitalOut(1, !haveBall.get());

            str1 = "count: " + count;
            str2 = "zone: " + zone;
            dsprint(str1, 1);
            dsprint(str2, 15);

//            str1 = "switch 1" + switch1;
//            dsprint(str1, 30);
//
//            str2 = "switch 2" + switch2;
//            dsprint(str2, 45);

            //One ball
            //Driveforeward, kick, turn, backup

            //Two Balls
            // fwd, kick, fwd, kick, turn, backup

            //Three Balls
            // fwd, kick, fwd, kick, fwd, kick, turn, backup
            str3 = "Current Gyro: " + gSensor.getAngle();
            dsLCD.println(DriverStationLCD.Line.kUser4, 1, str3);
            reloadKicker();
            if (count < zone) {

                if (!haveBall.get()) {
                    drivetrain.setLeftRightMotorSpeeds(0, 0);
                    if (kickerReady.get()) {
                        kick();
                        Timer.delay(1);

                        count++;
                    }

                } else {
                    drivetrain.setLeftRightMotorSpeeds(-.4, -.4);
                }
            } else {
                str3 = "Current Gyro: " + gSensor.getAngle();
                dsLCD.println(DriverStationLCD.Line.kUser4, 1, str3);

//                if (gSensor.getAngle() > -100 && gSensor.getAngle() < 10) {
//                    drivetrain.setLeftRightMotorSpeeds(.75, -.75);
////                    reloadKicker();
//
//                } else
                //{
                    reloadKicker();
                    if (kickerReady.get()) {
                        return;
                    } else if (driveCount) {
                        drivetrain.setLeftRightMotorSpeeds(.5, .5);
                        Timer.delay(.5);
                        drivetrain.setLeftRightMotorSpeeds(0, 0);
                        Timer.delay(.25);
                        stop();
                        driveCount = false;
                    }


                    //count = 0;



                //}
            }
        }
    }

    public void operatorControl() {
        getWatchdog().setEnabled(false); // Stupid WATCHDOG
        time.reset();
        time.start();

        while (isOperatorControl()) //Let's loop while in operator control
        {

            reloadKicker();
            //dsprint(new Double(gSensor.getAngle()).toString());
            //ds.println(DriverStationLCD.Line.kMain6, 1, new Double(gSensor.getAngle()).toString() );
            //ds.updateLCD();

            //drivers station swiches? controllers



//=============================================================================
            // Kicker Code
            if (LS.getTrigger()) {
                kick();

            }
            reloadKicker();

//=============================================================================
            // Winch Code
            if (liftDwn.get()) {
                winchOnlyUp();
            } else {
                winchUpAndDown();
            }
//=============================================================================
            //Drive Code
            //make mr robo drive
            if (liftDwn.get()) {
                drivetrain.arcadeDrive(LS);
            } else {
                drivetrain.arcadeDrive(RS);
            }
//=============================================================================
            //(HUD) heads up display code

            ds.setDigitalOut(1, !haveBall.get());

//=============================================================================
            //slow dwn he iterations, seems to work smoohely
            //Timer.delay(0.005);

//            dsLCD.println(DriverStationLCD.Line.kUser4, 1, new Double(time.get()).toString());
//            dsLCD.println(DriverStationLCD.Line.kUser3, 1, new String("" + (time.get() > 90)));
//            dsLCD.updateLCD();

            if (time.get() > 5)
            {
                ds.setDigitalOut(2, true);
            }
        }

        stop();
    }

    public void kick() {
        if (liftDwn.get()) {
            kicker.set(.75);
            //==================
            // This will allow for roller back speed
            // aka kicker distance
            //roller.set(-1);
            //Timer.delay(.2);
            //==================

            // disable kicking when travling over bump!!!!
        } else {
            reloadKicker();
        }

    }

    public void stop() {   // sets all motors to spped zero
        kicker.set(0);
        roller.set(0);
        drivetrain.drive(0, 0);
        winch.set(0);
    }

    public void reloadKicker() {   // Automatically recock the kicker
        if (!kickerReady.get()) {
            kicker.set(0.75);
        } else {
            kicker.set(0);
            if (liftDwn.get()) {
                roller.set(-.4);
            } else {
                roller.set(0);
            }
        }

    }

    private void winchOnlyUp() {   // Only allow the arm to move up
        if (RS.getRawButton(3)) {
            spinner.set(-1);
        } else if (RS.getRawButton(7)) {
            winch.set(1);
        } else {
            spinner.set(0);
            winch.set(0);
        }
    }

    private void winchUpAndDown() {
        if (RS.getRawButton(2)) {
            winch.set(-1);
        } else if (RS.getRawButton(3)) {
            spinner.set(-1);
        } else if (RS.getRawButton(7)) {
            winch.set(1);
        } else {
            spinner.set(0);
            winch.set(0);
        }
    }

    private void dsprint(String str, int col) {
        dsLCD.println(DriverStationLCD.Line.kMain6, col, str);
        dsLCD.updateLCD();
    }
}
