package edu.adk.robot;

import edu.adk.core.Autonomous;

import edu.adk.mechanism.*;
import edu.adk.maneuver.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 * What goes here?
 * @author Techplex Engineer
 */
public class MyRobot extends SimpleRobot
{
	//define mechanisms here:
	//2 cims per wheel

	Drive drive = new Drive();
	//Servo Y, 2 Window motors
	Arm arm = new Arm();
	//Vic top, Vic Bot
	Claw claw = new Claw();
	DriverStation ds = DriverStation.getInstance();
	DriverStationLCD dslcd = DriverStationLCD.getInstance();
	//define maneuvers here
	Maneuver driveF;
	Maneuver driveB;
	Maneuver cap;
	Maneuver finish;
	//define joysticks here:
	Joystick js1 = new Joystick(1);
	Joystick js2 = new Joystick(2);
	//define timers here:
	Timer timer = new Timer();
	DigitalInput tubeSaverLimitSw = new DigitalInput(10);

	/**
	 * RobotInit
	 *
	 * this is only called one time when the robot initializes
	 * it should contain things that only need to be initialized once
	 */
	protected void robotInit()
	{
		initialize();
	}

	/**
	 * Initialize
	 *
	 * a general use initialize function
	 * good for reseting the robot prior to autonomous or operator control
	 */
	public void initialize()
	{
		getWatchdog().setEnabled(false);

		//initialize timer
		timer.reset();
		timer.start();

		//initialize mechanisms
		drive.initialize();
		arm.initialize();
		claw.initialize();
	}

	/**
	 * Autonomous
	 *
	 * This function is called when the robot enters autonomous
	 */
	public void autonomous()
	{
		initialize();

		Autonomous autonomous = null;
		//setup the autonomous twice to make sure everything gets initialized correctly
		for (int i = 0; i < 2; i++)
		{
			//initialize the autonomous with the root maneuver
			autonomous = new Autonomous(driveF);

			//---------AUTONOMOUS TREE--------------
			//CustomManeuver(mechanism1, mechanism2,...., passManeuver,failManeuver,timeoutManeuver,maxTime)

			/**
			 * Drive Forward
			 *
			 * mechanisms: drive
			 * pass: null
			 * fail: null
			 * timeout: Cap
			 * time: 2
			 */
			driveF = new DriveForward(drive, null, null, cap, 2.0);
			/**
			 * Cap
			 *
			 * mechanisms: arm
			 * pass: null
			 * fail: null
			 * timeout: Finish
			 * time: 2
			 */
			cap = new Cap(arm, null, null, finish, 2.0);
			/**
			 * Finish
			 *
			 * mechanisms: drive
			 * pass:null
			 * finish:null
			 * timeout:null
			 * time: 2
			 */
			finish = new DriveBackward(drive, null, null, null, 2.0);
		}

		//run the autonomous mode
		while (isAutonomous() && isEnabled() && autonomous != null)
		{
			autonomous.run();
			if (autonomous.finished)
				break;
			Timer.delay(0.005);
		}
		initialize();
	}

	/**
	 * OperatorControl
	 *
	 * This function is called when the robot enters operator control
	 */
	public void operatorControl()
	{
		initialize();
		//run the operator control code

		boolean up;
		boolean dwn;
		boolean high;

		//Game Pad coltrols
//		int arm_elbow_up = 6;
//		int arm_elbow_dwn = 8;
//		int claw_wrist_up = 5;
//		int claw_wrist_dwn = 7;
//
//		int claw_spitter_in = 1;
//		int claw_spitter_out = 4;
//
//		int claw_spitter_rotate_up = 2;
//		int claw_spitter_rotate_dwn = 3;

		//Joystick Controls
//		int arm_elbow_up = 3;
//		int arm_elbow_dwn = 2;
//		int claw_wrist_up = 5;
//		int claw_wrist_dwn = 4;
//
//		int claw_spitter_in = 7;//+trigger
//		int claw_spitter_out = 6;
//
//		int claw_spitter_rotate_up = 8;
//		int claw_spitter_rotate_dwn = 9;
//
//		int move_act = 11;

		//ZJ
		int arm_elbow_up = 5;
		int arm_elbow_dwn = 3;

		int claw_wrist_up = 6;
		int claw_wrist_dwn = 4;

		int claw_spitter_in = 1;//+trigger
		int claw_spitter_out = 2;

		int claw_spitter_rotate_up = 8;
		int claw_spitter_rotate_dwn = 9;

		int move_act = 11;

		boolean a_elbow_up;
		boolean a_elbow_dwn;

		boolean c_wrist_up;
		boolean c_wrist_dwn;

		boolean c_spitter_in;
		boolean c_spitter_out;

		boolean c_spitter_rotate_up;
		boolean c_spitter_rotate_dwn;

		boolean haveTube;

		boolean debug;

		boolean p_low;
		boolean p_mid;
		boolean p_high;
		boolean p_mod;

		boolean act;






		while (isOperatorControl() && isEnabled())
		{

			a_elbow_up = js1.getRawButton(arm_elbow_up);
			a_elbow_dwn = js1.getRawButton(arm_elbow_dwn);

			c_wrist_up = js1.getRawButton(claw_wrist_up);
			c_wrist_dwn = js1.getRawButton(claw_wrist_dwn);

			c_spitter_in = js1.getRawButton(claw_spitter_in);
			c_spitter_out = js1.getRawButton(claw_spitter_out);

			if (js1.getRawAxis(6) > .25)
			{
				c_spitter_rotate_up = true;
				c_spitter_rotate_dwn = false;
			} else if (js1.getRawAxis(6) < .25)
			{
				c_spitter_rotate_dwn = true;
				c_spitter_rotate_up = false;
			} else
			{
				c_spitter_rotate_up = false;
				c_spitter_rotate_dwn = false;
			}

			haveTube = !tubeSaverLimitSw.get();

			debug = js1.getRawButton(10);

			p_low = ds.getDigitalIn(5);
			p_mid = ds.getDigitalIn(5) && ds.getDigitalIn(3);
			p_high = ds.getDigitalIn(3);
			p_mod = ds.getDigitalIn(2);


			act = js1.getRawButton(move_act);

			drive.run(js1.getX(), js1.getY());



			//This is for debugging purposes.
			if (debug)
			{
				arm.printPos();
				claw.printPos();

				dslcd.println(DriverStationLCD.Line.kMain6, 1, " Elbow Pos: " + arm.getPos());
				dslcd.println(DriverStationLCD.Line.kUser2, 1, " Wrist Pos: " + claw.getPos());

				dslcd.updateLCD();
			}

			//This should get the arm to the desired position as set by the
			//switches that attach to the cypress board
			if (act)
			{

//				String pos = "";
//				if (up == dwn)
//					//Move arm to mid
//					pos = "mid";
//				else if (up && !dwn)
//					//Move to pos top
//					pos = "top";
//				else if (!up && dwn)
//					//move to pos bot
//					pos = "bot";


//				double armPos = tpl.position.armGo(pos, high);
//
//				System.out.println("Moving Arm To:" + armPos);
//				while (js1.getRawButton(11) && !arm.at(armPos))
//				{
//					System.out.println("Moving Arm from " + arm.getPos());
//					arm.moveTo(armPos);
//					Timer.delay(.05);
//				}
//				// so now that the arm is in the correct position
//
//				//move the claw
//
//				double clawPos = tpl.position.clawGo(pos, high);
//				System.out.println("Moving Claw To:" + clawPos);
//				while (js1.getRawButton(move_act) && !claw.at(clawPos))
//				{
//					System.out.println("Moving Claw from " + claw.getPos());
//					claw.moveTo(clawPos);
//					Timer.delay(.05);
//				}

				//Now that the claw is in the correct position, stop everything,
				//and call the driver a dummie
//				arm.elbowRun(0);
//				claw.wristRun(0);

				while (js1.getRawButton(move_act))
				{
					System.out.println("Stop Pushing the button Dummie!");
					Timer.delay(.025);
				}

				//Legacy was here
			} else // So, the operator isn't pressing Button#11
			{
				// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
				// Control the elbow
				if (a_elbow_up)
					arm.elbowUp();
				else if (a_elbow_dwn)
					arm.elbowDwn();
				else
					arm.elbowRun(0);

				// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
				// Control the wrist
				if (c_wrist_up)
					claw.wristUp();
				else if (c_wrist_dwn)
					claw.wristDwn();
				else
					claw.wristRun(0);
			}

			// WRITE THE CODE FOR THE LIMIT SWITCH SO WE DON'T GET THE TUBES CAUGHT!
			/*
			 * here is miss luce's attempt:
			 *
			 * if(Limit switch is pressed)   //WE HAVE A TUBE
			 *     trigger makes claw.spitterout()
			 * else
			 *     trigger makes claw.spitterin()
			 */

			// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
			//Control for the spitter
			/* if(tubeSaver.get())
			 *		//Only allow out
			 * else
			 *		// both directions
			 *
			 */

			if (c_spitter_out)
				claw.spitterOut();
			else if (c_spitter_in && !haveTube)
				claw.spitterIn();
			else if (c_spitter_rotate_up)
				claw.spitterRotateTubeUp();
			else if (c_spitter_rotate_dwn)
				claw.spitterRotateTubeDwn();
			else
				claw.spitterRun(0);


			//junk
			Timer.delay(0.005);
		}
	}

	/**
	 * Disabled
	 *
	 * this is called while the robot is disabled.
	 */
	public void disabled()
	{
		initialize();
		while (isDisabled() && !isEnabled())
		{
		}
	}
	//legacy
	//
	//				if (up == dwn) {
//					//Move arm to mid
//					if (arm.moveToMiddle(high)) {
//						claw.moveToMiddle(high);
//					}
//				} else if (up && !dwn) {
//					//Move to pos top
//					if (arm.moveToTop(high)) {
//						claw.moveToTop(high);
//					}
//				} else if (!up && dwn) {
//					//move to pos bot
//					if (arm.moveToBottom(high)) {
//						claw.moveToBottom(high);
//					}
//				}
	// junk
	//
//
//			arm.wristRun(js2.getX());
//			//arm.elbowRun(js2.getY());
//
//			if(js1.getRawButton(2))
//			{
//				claw.in();
//				Timer.delay(0.05);
//			}
//			else if(js1.getRawButton(3))
//			{
//				claw.out();
//				Timer.delay(0.05);
//			}
//			else
//				claw.run(0);
//
//
//
//			if(js1.getRawButton(6))
//				arm.elbowUp();
//			else if(js1.getRawButton(7))
//				arm.elbowDwn();
//			else
//				arm.elbowRun(0);
//			if (js1.getTrigger()) {
//				boolean there =  arm.moveToPickup();
//				ds.setDigitalOut(1, there);
//			}
}
