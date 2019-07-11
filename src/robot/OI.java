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

import commands.DriveCourseCommand;
import commands.DriveForDistanceCommand;
import commands.DriveForTimeCommand;
import commands.DriveToLineCommand;
import commands.EscapeCommand;
import commands.TestMotorSpeedCommand;
import commands.TestSpeedControlCommand;
import commands.TurnCommand;
import robotCore.Joystick;
import robotWpi.buttons.Button;
import robotWpi.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
     Joystick m_stick = new Joystick(0);
     Button m_trigger = new JoystickButton(m_stick, 1);
     Button m_testButton = new JoystickButton(m_stick, 2);
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    public OI()
    {
//    	m_trigger.whenPressed(new ExampleCommand());
    	m_trigger.whenPressed(new DriveForTimeCommand(.75, 2));
    	m_testButton.whenPressed(new EscapeCommand());
    }
}

