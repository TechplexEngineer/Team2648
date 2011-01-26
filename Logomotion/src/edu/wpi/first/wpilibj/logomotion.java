/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.
/*
 * Team 2648
 * Logomotion
 */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class logomotion extends SimpleRobot {

	RobotDrive drivetrain;
	Jaguar r1, r2, l1, l2;
	Joystick j1;
	DigitalInput middle, right, left;
	AnalogChannel light;
	Encoder leftEncoder, rightEncoder;
	DriverStation ds;
	
	DigitalModule four;
	I2C ls;

	public logomotion() {
		getWatchdog().setEnabled(false);
		r1 = new Jaguar(1);//Motor controllers for 1 - 4 PWM Slots in the DS
		r2 = new Jaguar(2);
		l1 = new Jaguar(3);
		l2 = new Jaguar(4);
		drivetrain = new RobotDrive(l1, l2, r1, r2);//Groups MCs together to run

		j1 = new Joystick(1);//joystick in usb port 1

		middle = new DigitalInput(1);//photoswitches
		right = new DigitalInput(2);
		left = new DigitalInput(3);
		light = new AnalogChannel(1);


		leftEncoder = new Encoder(8, 9);//Encoder
		rightEncoder = new Encoder(6, 7);//Encoder
		ds = DriverStation.getInstance();
		four = new DigitalModule(4);
		ls=new I2C(four,0);



		//rtEn = new Encoder(4,5);

	}

	/**
	 * This function is called once each time the robot enters autonomous mode.
	 */
	public void autonomous() {
	}

	/**
	 * This function is called once each time the robot enters operator control.
	 */
	public void operatorControl() {
		getWatchdog().setEnabled(false);
//		leftEncoder.reset();
//		rightEncoder.reset();

		while (isOperatorControl() )
		{
			System.out.println(light.getVoltage() + "\n");

		
			//System.out.println("junk");
			//System.out.println(" rt: " + right.get() + " mid: " + middle.get() + " Lft: " + left.get()+"\n LeftE: "+leftEncoder.getDirection());
			//System.out.println(leftEncoder.getRaw() + "");

			leftEncoder.start();
			rightEncoder.start();
			byte[] byte1 = new byte[100];
//			ls.read(0, 7,  byte1);
//			//ls.read(1,8,int[])
//
//			for(int i= 0; i < 100; i++)
//				System.out.println(byte1[i]);



			//int REncoder = rtEn.get();
			//System.out.println("Left Endoder: " + leftEncoder.get());
			//System.out.println("Right Endoder: " + rightEncoder.get());


			//System.out.println("DigitalIn: " + !ds.getDigitalIn(1));

			boolean rt = right.get();//get Input from Photoswitches
			boolean mid = middle.get();
			boolean lft = left.get();

			if (mid || lft || rt) {
				ds.setDigitalOut(1, true);
			} else {
				ds.setDigitalOut(1, false);
			}
			if (!ds.getDigitalIn(1)) {

				leftEncoder.reset();
				rightEncoder.reset();

				if (mid && lft && rt) //@ y
				{
					drivetrain.setLeftRightMotorSpeeds(-.25, 0);
					Timer.delay(.5);
//					while(!(mid || lft || rt))
//					{
//						drivetrain.setLeftRightMotorSpeeds(-.25, 0);
//						Timer.delay(.05);
//					}
				} else if (mid || lft || rt) {

					if (lft) {
						//
						drivetrain.setLeftRightMotorSpeeds(-.125, -.25);
					} else if (rt) {
						//
						drivetrain.setLeftRightMotorSpeeds(-.25, -.125);
					} else {
						//straight
						drivetrain.setLeftRightMotorSpeeds(-.25, -.25);
					}
				} else {
					drivetrain.setLeftRightMotorSpeeds(0, 0);
				}
			} else {
				drivetrain.arcadeDrive(j1);
			}


			

			//			if (mid) {
//				drivetrain.setLeftRightMotorSpeeds(-.25, -.25);
//			} else if (lft) {
//				drivetrain.setLeftRightMotorSpeeds(-.125, -.25);
//			} else if (!rt) {
//				drivetrain.setLeftRightMotorSpeeds(-.25, -.125);
//			}


//			if(mid)
//			{
//				drivetrain.setLeftRightMotorSpeeds(-.25, -.25);
//				//drivetrain.drive(-.25, 0);
//				if(lft)
//					drivetrain.setLeftRightMotorSpeeds(-.125, -.25);
//					//drivetrain.drive(-.25, -.25);
//				if(rt)
//					drivetrain.setLeftRightMotorSpeeds(-.25, -.125);
//					//drivetrain.drive(-.25, .25);
//			}
//			else
//				drivetrain.setLeftRightMotorSpeeds(0, 0);
			//drivetrain.drive(0, 0);



			//drivetrain.arcadeDrive(j1); //modifies the robotdrive controlls to a specific orentation
		}
	}
}
