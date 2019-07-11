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
public class DriveForDistanceCommand extends Command 
{
	double m_power;
	double m_distance;
	private static final double k_scale = 0.001;
	private Encoder m_leftEncoder;
	private Encoder m_rightEncoder;
    public DriveForDistanceCommand(double power, double distance) 
    {
    	Logger.Log("DriveForDistanceCommand", 3, "DriveForDistanceCommand()");
    	 m_power        = power;
         m_distance    = (distance*4000)/32;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    	Logger.Log("DriveForDistanceCommand", 2, "initialize()");
    	m_leftEncoder    = Robot.m_driveSubsystem.GetLeftEncoder().Clone();
        m_rightEncoder    = Robot.m_driveSubsystem.GetRightEncoder().Clone();
        
        m_leftEncoder.reset();
        m_rightEncoder.reset();
    	Robot.m_driveSubsystem.SetSpeed(m_power, m_power);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Logger.Log("DriveForDistanceCommand", -1, "execute()");
    	int leftDistance    = m_leftEncoder.get();
        int    rightDistance    = m_rightEncoder.get();
        int    deltaDistance    = rightDistance - leftDistance;
        Robot.m_driveSubsystem.SetSpeed(m_power + deltaDistance*k_scale, m_power - deltaDistance*k_scale);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	Logger.Log("DriveForDistanceCommand", -1, "isFinished()");
        //Logger.Log("DriveForDistanceCommand", 2, ""+Robot.m_driveSubsystem.GetLeftEncoder().get());
    	return (m_leftEncoder.get() >= m_distance);
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Logger.Log("DriveForDistanceCommand", 2, "end()");
    	Robot.m_driveSubsystem.SetSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Logger.Log("DriveForDistanceCommand", 2, "interrupted()");
    }
}
