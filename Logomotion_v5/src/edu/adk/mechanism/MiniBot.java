/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.adk.mechanism;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Techplex Engineer
 */
public class MiniBot implements Mechanism
{
	SpeedController bot	= new Victor(5);

	/**
	 * Calls the MBDS stop method
	 */
	public void initialize()
	{
		stop();
	}

	/**
	 * Deploy's the minibot, simply runs the motor for a soecified amount of time.
	 */
	public void deploy()
	{
		bot.set(1);
		Timer.delay(1.5);
		bot.set(0);
	}
	/**
	 * Stops all the MBDS's motors
	 */
	public void stop()
	{
		bot.set(0);
	}

}
