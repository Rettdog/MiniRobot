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

package commands;

import robot.Robot;
import robotCore.Logger;
import robotWpi.command.Command;

/**
 *
 */
public class TestSpeedControlCommand extends Command 
{
	private static final double k_speed = 0.5;
    public TestSpeedControlCommand() 
    {
    	Logger.Log("TestSpeedControlCommand", 3, "TestSpeedControlCommand()");
    	
        // Use requires() here to declare subsystem dependencies
//        requires(Robot.m_exampleSubsystem);
    	requires(Robot.m_driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Logger.Log("TestSpeedControlCommand", 2, "initialize()");
    	Logger.Log("TestSpeedControlCommand", 3, "...,Time,Speed,Left Speed, Right Speed");
        
        Robot.m_driveSubsystem.SetSpeed(k_speed,  k_speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Logger.Log("TestSpeedControlCommand", -1, "execute()");
    	Logger.Log("TestSpeedControl", 3, String.format(",%d,%.2f,%d,%d", 
                Logger.GetElapsedTime(),
                k_speed,
                Robot.m_driveSubsystem.GetLeftEncoder().getSpeed(),
                Robot.m_driveSubsystem.GetRightEncoder().getSpeed()));

Robot.Sleep(40);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	Logger.Log("TestSpeedControlCommand", -1, "isFinished()");
        
    	return (false);
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Logger.Log("TestSpeedControlCommand", 2, "end()");
    	Robot.m_driveSubsystem.SetSpeed(0,  0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Logger.Log("TestSpeedControlCommand", 2, "interrupted()");
    }
}
