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
//        Maneuver driveF;
//        Maneuver driveB;
//        Maneuver cap;
//        Maneuver spitt;
//        Maneuver finish;
	//define joysticks here:
	Joystick js1 = new Joystick(1);
	Joystick js2 = new Joystick(2);
	//define timers here:
	Timer timer = new Timer();
	DigitalInput haveTube = new DigitalInput(10);
	DigitalInput p_mid = new DigitalInput(1);
	DigitalInput p_lft = new DigitalInput(2);
	DigitalInput p_rt = new DigitalInput(3);
	double armGoTo, clawGoTo;
	boolean mid, lft, rt;

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
		System.out.println("Entering Auto: ");

//                drive.goForwardSlow();
//
//                Timer.delay(5);
//                arm.elbowUp();
//
//                Timer.delay(3.75);
//                drive.stop();
//                arm.elbowRun(0);
//
//                claw.wristRun(-.25);
//                Timer.delay(.5);
//                claw.wristRun(0);
//
//
//                claw.spitterOut();
//                Timer.delay(1);
//
//
//                arm.elbowDwn();
//                Timer.delay(1);
//                claw.spitterRun(0);
//                Timer.delay(.35);
//
//                arm.elbowRun(0);
//
//                //
//                drive.goBackwardSlow();
//                Timer.delay(1);
//                drive.stop();
//
//                arm.elbowDwn();
//                Timer.delay(1.5);
//                arm.elbowRun(0);
//
//
//                drive.goRight();
//                Timer.delay(1.5);
//                drive.stop();

		while (isAutonomous() && isEnabled())
		{
			Timer.delay(.05);
			mid = p_mid.get();
			lft = p_lft.get();
			rt = p_rt.get();
			System.out.println("Mid: "+mid + " | Lft: "+lft+ " | Rt: "+rt);
			if (mid || lft || rt) //line following possible
			{
				if (mid && lft && rt) //@ T
				{
					//Backup from the T
					drive.goBackwardSlow();
					Timer.delay(1.25);
					drive.stop();

					//Raise Arm
					arm.elbowUp();
					Timer.delay(3.5);
					arm.elbowRun(0);

					//Go FWD
					drive.goForwardSlow();
					Timer.delay(.25);
					drive.stop();

					//Lower Wrist
					claw.wristRun(-.25);
					Timer.delay(.5);
					claw.wristRun(0);

					//Hang Tube
					claw.spitterOut();
					Timer.delay(2);
					claw.spitterRun(0);

					//Lower arm
					arm.elbowDwn();
					Timer.delay(1.5);
					arm.elbowRun(0);

					//Backup Slow
					drive.goBackwardSlow();
					Timer.delay(5);
					drive.stop();

					//Raise claw
					claw.wristRun(.5);
					Timer.delay(1);
					claw.wristRun(0);

					

					//Turn arround -- Gyro Here
					drive.goRight();
					Timer.delay(1);
					drive.stop();


					return;



				}

				if (mid)
				{
					if (lft)
					{
						System.out.println("Turn LFT");
						drive.setLeftRightMotorSpeeds(0, -.5);
					}
					else if (rt)
					{
						System.out.println("Turn RT");
						drive.setLeftRightMotorSpeeds(-.5, 0);
					}
					else
					{
						System.out.println("Straight");
					drive.setLeftRightMotorSpeeds(-.25, -.25);
					}

				}
			} else
			{
				drive.stop();
				//Other Code
				System.out.println("Other Code");
				break;

//				//Forward
//				drive.goForwardSlow();
//
//				Timer.delay(5);
//				arm.elbowUp();
//
//				Timer.delay(3.75);
//				drive.stop();
//				arm.elbowRun(0);
//
//				claw.wristRun(-.25);
//				Timer.delay(.5);
//				claw.wristRun(0);
//
//
//				claw.spitterOut();
//				Timer.delay(1);
//
//
//				arm.elbowDwn();
//				Timer.delay(1);
//				claw.spitterRun(0);
//				Timer.delay(.35);
//
//				arm.elbowRun(0);
//
//				//
//				drive.goBackwardSlow();
//				Timer.delay(1);
//				drive.stop();
//
//				arm.elbowDwn();
//				Timer.delay(1.5);
//				arm.elbowRun(0);
//
//
//				drive.goRight();
//				Timer.delay(1.5);
//				drive.stop();
			}

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
//              int arm_elbow_up = 6;
//              int arm_elbow_dwn = 8;
//              int claw_wrist_up = 5;
//              int claw_wrist_dwn = 7;
//
//              int claw_spitter_in = 1;
//              int claw_spitter_out = 4;
//
//              int claw_spitter_rotate_up = 2;
//              int claw_spitter_rotate_dwn = 3;

		//Joystick Controls
//              int arm_elbow_up = 3;
//              int arm_elbow_dwn = 2;
//              int claw_wrist_up = 5;
//              int claw_wrist_dwn = 4;
//
//              int claw_spitter_in = 7;//+trigger
//              int claw_spitter_out = 6;
//
//              int claw_spitter_rotate_up = 8;
//              int claw_spitter_rotate_dwn = 9;
//
//              int move_act = 11;

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

//			dslcd.println(DriverStationLCD.Line.kMain6, 1, " Elbow Pos: " + arm.getPos());
//			dslcd.println(DriverStationLCD.Line.kUser2, 1, " Wrist Pos: " + claw.getPos());
//
//			dslcd.updateLCD();

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


//                              else if (js1.getRawButton(claw_spitter_rotate_up))
//                                      claw.spitterRotateTubeUp();
//                              else if (js1.getRawButton(claw_spitter_rotate_dwn))
//                                      claw.spitterRotateTubeDwn();
//                              else
//                                      claw.spitterRun(0);

			if (js1.getRawButton(deploy_mini))
			{
				MBDS.deploy();

			}

			// 90 100
			if (timer.get() > 85)
			{
				ds.setDigitalOut(1, false);
				ds.setDigitalOut(2, true);
			} else
			{
				ds.setDigitalOut(1, true);
				ds.setDigitalOut(2, false);
			}
			//10, 12

			//System.out.println("BatVolt: "+ds.getBatteryVoltage());
			//System.out.println(timer.get());

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
}
