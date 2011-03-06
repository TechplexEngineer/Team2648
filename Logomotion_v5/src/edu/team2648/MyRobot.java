package edu.team2648;

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
	MiniBot MBDS = new MiniBot();
	DriverStation ds = DriverStation.getInstance();
	DriverStationLCD dslcd = DriverStationLCD.getInstance();
	//define maneuvers here
	Maneuver driveF;
	Maneuver driveB;
	Maneuver cap;
	Maneuver spitt;
	Maneuver finish;
	//define joysticks here:
	Joystick js1 = new Joystick(1);
	Joystick js2 = new Joystick(2);
	//define timers here:
	Timer timer = new Timer();
	DigitalInput haveTube = new DigitalInput(10);
	double armGoTo, clawGoTo;

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

//		Autonomous autonomous = null;
//		//setup the autonomous twice to make sure everything gets initialized correctly
//		for (int i = 0; i < 2; i++)
//		{
//
//
//			//---------AUTONOMOUS TREE--------------
//			//CustomManeuver(mechanism1, mechanism2,...., passManeuver,failManeuver,timeoutManeuver,maxTime)
//
//			/**
//			 * Drive Forward
//			 *
//			 * mechanisms: drive
//			 * pass: null
//			 * fail: null
//			 * timeout: Cap
//			 * time: 2
//			 */
//			driveF = new DriveForward(drive, null, null, cap, 2.0);
//			/**
//			 * Cap - Lower arm
//			 *
//			 * mechanisms: arm
//			 * pass: null
//			 * fail: null
//			 * timeout: Finish
//			 * time: 2
//			 */
//			cap = new Cap(arm, null, null, finish, 2.0);
//
//
//			//spitt = new Spitt(claw, null, null, finish, 2.0);
//			/**
//			 * Finish
//			 *
//			 * mechanisms: drive
//			 * pass:null
//			 * finish:null
//			 * timeout:null
//			 * time: 2
//			 */
//			finish = new DriveBackward(drive, null, null, null, 2.0);
//
//			//initialize the autonomous with the root maneuver
//			autonomous = new Autonomous(driveF);
//		}
//
//		//run the autonomous mode
//		while (isAutonomous() && isEnabled() && autonomous != null)
//		{
//			autonomous.run();
//			if (autonomous.finished)
//				break;
//			Timer.delay(0.005);
//		}
//		initialize();

		drive.goForwardSlow();

		Timer.delay(5);
		arm.elbowUp();

		Timer.delay(3.75);
		drive.stop();
		arm.elbowRun(0);

		claw.wristRun(-.25);
		Timer.delay(.5);
		claw.wristRun(0);

		//
//		claw.spitterOut();
//		Timer.delay(2);
//		claw.spitterRun(0);
//
//		arm.elbowDwn();
//		Timer.delay(.35);
//		arm.elbowRun(0);

		claw.spitterOut();
		Timer.delay(1);


		arm.elbowDwn();
		Timer.delay(1);
		claw.spitterRun(0);
		Timer.delay(.35);

		arm.elbowRun(0);

		//
		drive.goBackwardSlow();
		Timer.delay(1);
		drive.stop();

		arm.elbowDwn();
		Timer.delay(1.5);
		arm.elbowRun(0);


		drive.goRight();
		Timer.delay(1.5);
		drive.stop();


		/*
		 * First:  Drive line following until T
		 *
		 * Second: Backup ~ 4 feet
		 *
		 * Third:  Raise arm
		 *
		 * Fourth: Tilt claw
		 *
		 * Fifth:  Excrete Tube
		 */


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

		//Logitech 3D BA JS
		int arm_elbow_up = 5;
		int arm_elbow_dwn = 3;

		int claw_wrist_up = 6;
		int claw_wrist_dwn = 4;

		int claw_spitter_in = 2;//+trigger
		int claw_spitter_out = 1;

		int claw_spitter_rotate_up = 8;
		int claw_spitter_rotate_dwn = 9;

		int move_act = 11; //move arm to selector switch pos

		int deploy_mini = 12;
		timer.reset();
		timer.start();



		while (isOperatorControl() && isEnabled())
		{
			drive.runThrottled(js1);

			dslcd.println(DriverStationLCD.Line.kMain6, 1, " Elbow Pos: " + arm.getPos());
			dslcd.println(DriverStationLCD.Line.kUser2, 1, " Wrist Pos: " + claw.getPos());

			dslcd.updateLCD();

			if (js1.getRawButton(10))
			{
				arm.printPos();
				claw.printPos();
			}

			if (js1.getRawButton(move_act))
			{

				dwn = ds.getDigitalIn(5);
				//System.out.println("Result of dwn: " + dwn);
				up = ds.getDigitalIn(3);
				//System.out.println("Result of up: " + up);
				high = ds.getDigitalIn(2);
				//System.out.println("Result of high: " + high);


				armGoTo = position.armGo(up, dwn, high);
				System.out.println("Moving Arm To:" + armGoTo);
				while (js1.getRawButton(11) && !arm.at(armGoTo))
				{
					System.out.println("Moving Arm from " + arm.getPos());
					arm.moveTo(armGoTo);
					Timer.delay(.05);
				}
				// so now that the arm is in the correct position

				//move the claw

				clawGoTo = position.clawGo(up, dwn, high);
				System.out.println("Moving Claw To:" + clawGoTo);
				while (js1.getRawButton(move_act) && !claw.at(clawGoTo))
				{
					System.out.println("Moving Claw from " + claw.getPos());
					claw.moveTo(clawGoTo);
					Timer.delay(.05);
				}

				//Now that the claw is in the correct position, stop everything,
				//and call the driver a dummie
				arm.elbowRun(0);
				claw.wristRun(0);

				while (js1.getRawButton(move_act))
				{
					System.out.println("The action has been completed");
					Timer.delay(.025);
				}

				//Legacy was here
			} else // So, the operator isn't pressing Button#11
			{
				// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
				// Control the elbow
				if (js1.getRawButton(arm_elbow_up))
					arm.elbowUp();
				else if (js1.getRawButton(arm_elbow_dwn))
					arm.elbowDwn();
				else
					arm.elbowRun(0);

				// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
				// Control the wrist
				if (js1.getRawButton(claw_wrist_up))
					claw.wristUp();
				else if (js1.getRawButton(claw_wrist_dwn))
					claw.wristDwn();
				else
					claw.wristRun(0);
			}


			// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
			//Control for the spitter

			if (js1.getRawButton(claw_spitter_out))
				claw.spitterOut();
			else if (js1.getRawButton(claw_spitter_in))
				claw.spitterIn(!haveTube.get());
			
			else if (js1.getRawAxis(6) > .8)
				claw.spitterRotateTubeUp();
			else if (js1.getRawAxis(6) < -.8)
				claw.spitterRotateTubeDwn();
			else
				claw.spitterRun(0);


//				else if (js1.getRawButton(claw_spitter_rotate_up))
//					claw.spitterRotateTubeUp();
//				else if (js1.getRawButton(claw_spitter_rotate_dwn))
//					claw.spitterRotateTubeDwn();
//				else
//					claw.spitterRun(0);

			if (js1.getRawButton(deploy_mini))
			{
				MBDS.deploy();

			}
			if(timer.get()>85)
			{
				ds.setDigitalOut(10,true);
				ds.setDigitalOut(12,true);
			}
			else
			{
				ds.setDigitalOut(10,false);
				ds.setDigitalOut(12,false);
			}
			//10, 12

			System.out.println("BatVolt: "+ds.getBatteryVoltage());


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
	//legacy - Ms Luces code
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
