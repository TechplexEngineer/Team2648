package edu.adk.mechanism;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Techplex Engineer
 */
public class Drive implements Mechanism{
    private static final double SPEED_STOP            =  0.0;
    private static final double SPEED_FWD_MAX         =  1;
    private static final double SPEED_REV_MAX         = -1;

	Jaguar  left_front	= new Jaguar(1);
	Jaguar	left_rear	= new Jaguar(2);
	Jaguar	right_front = new Jaguar(3);
	Jaguar	right_rear	= new Jaguar(4);

	RobotDrive drivetrain = new RobotDrive(left_front, left_rear, right_front, right_rear);

	Encoder left_encoder = new Encoder(3, 4);
	Encoder right_encoder = new Encoder(5, 6);

    public void initialize(){
        stop();

		left_encoder.reset();
		right_encoder.reset();

    }
    /**
	 * Equivalent to arcade drive
	 * @param y value from -1 to 1
	 * @param x value from -1 to 1
	 */
    public void run(double y,double x){
        drivetrain.arcadeDrive(y,x,false);
    }

	public void runScaled(double y,double x){
        drivetrain.arcadeDrive(y*1.5,x*1.5,false);
    }
	/**
	 * Equivalent to arcade drive
	 * @param j1 joystick in question
	 */
	public void run(Joystick j1){
        drivetrain.arcadeDrive(j1,false);
	}
	/**
	 * Equivalent to arcade drive
	 * @param j1 joystick in question
	 * @param halfSpeed if true values fro joystic are cut in half
	 */
	public void run(Joystick j1, boolean halfSpeed){
        this.run((j1.getX()/2),(j1.getY()/2));
	}

	/**
	 * This acts a programatical transmission, allowing the user to divide their speed by the gear variable
	 * @param hid joystick to use
	 * @param gear integer to divide by
	 */
	public void run(Joystick hid, int gear){
        this.run((hid.getX()/gear),(hid.getY()/gear));
	}



    /**
	 * Stops the drivetrain
	 */
    public void stop(){
        drivetrain.arcadeDrive(SPEED_STOP,SPEED_STOP,false);
    }

	/**
	 * Full speed ahead
	 */
    public void goForward() {
        drivetrain.arcadeDrive(SPEED_FWD_MAX, SPEED_STOP, false);
    }

	/**
	 * Full speed reverse
	 */
    public void goBackward() {
        drivetrain.arcadeDrive(SPEED_REV_MAX, SPEED_STOP, false);
    }

	/**
	 * Hard right turn
	 */
    public void goLeft() {
        drivetrain.arcadeDrive(SPEED_STOP, SPEED_FWD_MAX, false);
    }

	/**
	 * hard left turn
	 */
    public void goRight() {
        drivetrain.arcadeDrive(SPEED_STOP, SPEED_REV_MAX, false);
    }
	/**
	 * ??
	 */
    public void goRightBackward() {
        drivetrain.arcadeDrive(SPEED_REV_MAX, SPEED_FWD_MAX, false);
    }
	/**
	 * ??
	 */
    public void goLeftBackward() {
        drivetrain.arcadeDrive(SPEED_REV_MAX, SPEED_REV_MAX, false);
    }
	/**
	 * ??
	 */
    public void goForwardLeft() {
        drivetrain.arcadeDrive(SPEED_FWD_MAX, SPEED_FWD_MAX, false);
    }
	/**
	 * ??
	 */
    public void goForwardRight() {
        drivetrain.arcadeDrive(SPEED_FWD_MAX, SPEED_REV_MAX, false);
    }
}
