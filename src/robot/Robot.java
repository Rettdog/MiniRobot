/*
 *	  Copyright (C) 2016  John H. Gaby
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, version 3 of the License.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *    
 *    Contact: robotics@gabysoft.com
 */

package robot;

import commands.DriveForTimeCommand;
import robotCore.DigitalInput;
import robotCore.Encoder;
import robotCore.IterativeRobot;
import robotCore.Logger;
import robotCore.PWMMotor;
import robotCore.STM32;
import robotCore.SmartMotor;
import robotCore.SmartMotor.SmartMotorMode;
import robotCore.Timer;
import robotWpi.command.Scheduler;
import subsystem.DriveSubsystem;
import subsystem.ExampleSubsystem;

public class Robot extends IterativeRobot 
{
	public static ExampleSubsystem m_exampleSubsystem;
	public static OI m_OI;
	public static DriveSubsystem m_driveSubsystem;
	private Timer m_timer = new Timer();
	
	Robot()
	{
		Logger.Log("Robot", 2, "Robot()");
	}
	
	/**
	 * Called once to initialize the robot
	 */
	@Override
    public void robotInit() 
    {
		Logger.Log("Robot", 2, "robotiInit()");
		
		m_exampleSubsystem = new ExampleSubsystem();
		m_driveSubsystem= new DriveSubsystem();
		m_OI = new OI();
    }
    
	/*
	 * Called at the start of autonomous mode
	 */
	@Override
    public void autonomousInit() 
    {
		Logger.ResetElapsedTime();
		Logger.Log("Robot", 2, "autonomousInit()");
    }

    /**
     * Called periodically during autonomous
     */
	@Override
    public void autonomousPeriodic() 
    {
		Logger.Log("Robot",  -1, "autonomousPeriodic()");
		
		Scheduler.getInstance().run();
		
		Sleep(10);
    }

	/**
	 * Called at the start of teleop mode
	 */
	@Override
	public void teleopInit()
	{
		Logger.ResetElapsedTime();
		Logger.Log("Robot", 2, "teleopInit()");
		
	}
	
	/**
     * Called periodically during operator control
     */
	@Override
    public void teleopPeriodic()
	{
		Logger.Log("Robot", -1, "teleopPeriodic()");
		Scheduler.getInstance().run();
		
		Sleep(10);
    }
	
	/**
	 * Called a the start of test mode
	 */
	@Override
	public void testInit()
	{
		Logger.ResetElapsedTime();
		Logger.Log("Robot", 2, "testInit()");
	}
    
    /**
     * Called periodically during test mode
     */
	@Override
    public void testPeriodic() 
	{
		Logger.Log("Robot", -1, "testPeriodic()");
		
		Sleep(10);
    }
 	
	/**
	 * Main program entry point
	 * 
	 */
    public static void main(String args[]) 
    {
    	Robot Robot = new Robot();
    	
    	Robot.Start(args);
    }

}
