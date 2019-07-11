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

import java.util.Random;

import robot.Robot;
import robotCore.DigitalInput;
import robotCore.Logger;
import robotCore.Timer;
import robotWpi.command.Command;

/**
 *
 */
public class EscapeCommand extends Command 
{
	private State m_state;
	private static final double k_driveSpeed    = 0.5;
private static final int k_leftSensorPin    = 10;
private Timer        m_timer              = new Timer();
    private DigitalInput    m_leftSensor = new DigitalInput(k_leftSensorPin);
    private static final double k_backingTime    = 0.5;
    private static final double k_turnSpeed        = 0.5;
    private static final double k_minTurnTime    = 0.5;
    private Random            m_random        = new Random(System.currentTimeMillis());
    private double            m_turnTime;
    public EscapeCommand() 
    {
    	Logger.Log("EscapeCommand", 3, "EscapeCommand()");
    	
        // Use requires() here to declare subsystem dependencies
//        requires(Robot.m_exampleSubsystem);
    	requires(Robot.m_driveSubsystem);
    }
    private enum State
    {
    	DriveForward,
    	BackUp,
    	Turn
    }
    private void DriveForward()
    {
Logger.Log("EscapeCommand", 2, "DriveForward()");
        
        m_state    = State.DriveForward;
        
        Robot.m_driveSubsystem.SetSpeed(k_driveSpeed, k_driveSpeed);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Logger.Log("EscapeCommand", 2, "initialize()");
    	DriveForward();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Logger.Log("EscapeCommand", -1, "execute()");
    	switch (m_state)
        {
        case DriveForward:
        	DrivingForward();
            break;
            
        case BackUp:
        	Backing();
            break;
            
        case Turn:
        	Turning();
            break;
        }
    }
    private void DrivingForward()
    {
    	if (m_leftSensor.get())
        {
            Logger.Log("EscapeCommand", 2, "BackUp");
            
            m_state    = State.BackUp;
            
            Robot.m_driveSubsystem.SetSpeed(-k_driveSpeed, -k_driveSpeed);
            
            m_timer.reset();
        }
    }

    
    private void Backing()
    {
        if (m_timer.get() >= k_backingTime)
        {
            Logger.Log("EsapeCommand", 2, "Turn");
            
            m_state    = State.Turn;
            
            if (m_random.nextInt(2) == 1)
            {
                Robot.m_driveSubsystem.SetSpeed(k_turnSpeed, -k_turnSpeed);        // Turn right
            }
            else
            {
                Robot.m_driveSubsystem.SetSpeed(-k_turnSpeed, k_turnSpeed);        // Turn left
            }
            
            m_turnTime    = k_minTurnTime + m_random.nextDouble();
            
            m_timer.reset();
        }
    }
    protected void Turning()
    {
        if (m_timer.get() >= m_turnTime)
        {
            DriveForward();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	Logger.Log("EscapeCommand", -1, "isFinished()");
        
    	return (false);
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Logger.Log("EscapeCommand", 2, "end()");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Logger.Log("EscapeCommand", 2, "interrupted()");
    }
}
