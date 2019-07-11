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

import robotCore.Logger;
import robotWpi.command.Command;

/**
 *
 */
public class ExampleCommand extends Command 
{
    public ExampleCommand() 
    {
    	Logger.Log("ExampleCommand", 3, "ExampleCommand()");
    	
        // Use requires() here to declare subsystem dependencies
//        requires(Robot.m_exampleSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Logger.Log("ExampleCommand", 2, "initialize()");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Logger.Log("ExampleCommand", -1, "execute()");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	Logger.Log("ExampleCommand", -1, "isFinished()");
        
    	return (false);
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Logger.Log("ExampleCommand", 2, "end()");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Logger.Log("ExampleCommand", 2, "interrupted()");
    }
}
