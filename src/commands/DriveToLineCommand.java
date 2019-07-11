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
import robotCore.DigitalInput;
import robotCore.Logger;
import robotWpi.command.Command;

/**
 *
 */
public class DriveToLineCommand extends Command 
{
	private static final double k_power = 0.5;
	private static final int k_leftSensorPin = 10;
	private DigitalInput    m_leftSensor = new DigitalInput(k_leftSensorPin);
    public DriveToLineCommand() 
    {
    	Logger.Log("DriveToLineCommand", 3, "DriveToLineCommand()");
    	
        // Use requires() here to declare subsystem dependencies
//        requires(Robot.m_exampleSubsystem);
    	requires(Robot.m_driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Logger.Log("DriveToLineCommand", 2, "initialize()");
    	Robot.m_driveSubsystem.SetSpeed(k_power, k_power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Logger.Log("DriveToLineCommand", -1, "execute()");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	Logger.Log("DriveToLineCommand", -1, "isFinished()");
        
    	return (m_leftSensor.get());
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Logger.Log("DriveToLineCommand", 2, "end()");
    	Robot.m_driveSubsystem.SetSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Logger.Log("DriveToLineCommand", 2, "interrupted()");
    }
}
