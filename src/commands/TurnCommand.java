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
import robotCore.Encoder;
import robotCore.Logger;
import robotWpi.command.Command;

/**
 *
 */
public class TurnCommand extends Command 
{
	private double m_speed;
	private double m_angle;
	private Encoder m_leftEncoder;
    private Encoder m_rightEncoder;
    public TurnCommand(double speed, double angle) 
    {
    	Logger.Log("TurnCommand", 3, "TurnCommand()");
    	m_speed=speed;
    	m_angle = (angle*5100)/360;
        // Use requires() here to declare subsystem dependencies
//        requires(Robot.m_exampleSubsystem);
    	requires(Robot.m_driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Logger.Log("TurnCommand", 2, "initialize()");
    	m_leftEncoder = Robot.m_driveSubsystem.GetLeftEncoder();
    	m_rightEncoder = Robot.m_driveSubsystem.GetRightEncoder();
    	m_leftEncoder.reset();
    	m_rightEncoder.reset();
    	if (m_angle < 0)
        {
            Robot.m_driveSubsystem.SetSpeed(-m_speed, m_speed);    // Turn left
        }
        else
        {
            Robot.m_driveSubsystem.SetSpeed(m_speed, -m_speed);    // Turn right
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Logger.Log("TurnCommand", -1, "execute()");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	Logger.Log("TurnCommand", -1, "isFinished()");
    	int    delta    = m_leftEncoder.get() - m_rightEncoder.get();
    	return (Math.abs(delta) >= Math.abs(m_angle));
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Logger.Log("TurnCommand", 2, "end()");
    	Robot.m_driveSubsystem.SetSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Logger.Log("TurnCommand", 2, "interrupted()");
    }
}
