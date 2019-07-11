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
public class TestMotorSpeedCommand extends Command 
{
	double    m_power;
    public TestMotorSpeedCommand() 
    {
    	Logger.Log("TestMotorSpeedCommand", 3, "TestMotorSpeedCommand()");
    	
        // Use requires() here to declare subsystem dependencies
//        requires(Robot.m_exampleSubsystem);
    	 requires(Robot.m_driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Logger.Log("TestMotorSpeedCommand", 3, "...,Time,Power,Left Speed,Right Speed");
    	m_power    = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Logger.Log("TestMotorSpeedCommand", -1, "execute()");
    	Robot.m_driveSubsystem.SetPower(m_power, m_power);
        
        m_power    += 0.01;
        
        Robot.Sleep(40);
        Logger.Log("TestMotorSpeedCommand", 3, String.format(",%d,%.2f,%d,%d", 
                Logger.GetElapsedTime(), 
                m_power,
                Robot.m_driveSubsystem.GetLeftEncoder().getSpeed(), 
                Robot.m_driveSubsystem.GetRightEncoder().getSpeed()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	Logger.Log("TestMotorSpeedCommand", -1, "isFinished()");
        
    	return (m_power>=1.3);
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Logger.Log("TestMotorSpeedCommand", 2, "end()");
    	Robot.m_driveSubsystem.SetPower(0,  0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Logger.Log("TestMotorSpeedCommand", 2, "interrupted()");
    }
}
