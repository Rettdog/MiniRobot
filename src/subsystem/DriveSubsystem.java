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

package subsystem;

import commands.ArcadeDriveCommand;
import robotCore.Encoder;
import robotCore.Logger;
import robotCore.PWMMotor;
import robotCore.SmartMotor.SmartMotorMode;
import robotWpi.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	private static final int k_rightMotorPWMPin = 6;
	private static final int k_rightMotorDirPin = 7;
	private static final int k_leftMotorPWMPin = 5;
	private static final int k_leftMotorDirPin = 4;

	private static final int k_leftEncoderPin1 = 0;
	private static final int k_leftEncoderPin2 = 1;
	private static final int k_rightEncoderPin1 = 2;
	private static final int k_rightEncoderPin2 = 3;

	private Encoder m_rightEncoder = new Encoder(robotCore.Encoder.EncoderType.Analog, k_rightEncoderPin1,
			k_rightEncoderPin2);
	private Encoder m_leftEncoder = new Encoder(robotCore.Encoder.EncoderType.Analog, k_leftEncoderPin1,
			k_leftEncoderPin2);
	private static final int k_maxSpeed = 1400;
	private PWMMotor m_leftMotor = new PWMMotor(k_rightMotorPWMPin, k_rightMotorDirPin);
	private PWMMotor m_rightMotor = new PWMMotor(k_leftMotorPWMPin, k_leftMotorDirPin);
	private static final double k_F = 0.0007;
	private static final double k_P                = 0.00025;
	private static final double k_I                = 0.0002;
    private static final double k_IZone            = 100;
	public DriveSubsystem() {
		m_leftEncoder.setInverted(true);
		m_leftMotor.setFTerm(k_F);
		m_rightMotor.setFTerm(k_F);
		m_leftMotor.setFeedbackDevice(m_leftEncoder);
		m_rightMotor.setFeedbackDevice(m_rightEncoder);
		m_leftMotor.setMaxSpeed(k_maxSpeed);
		m_rightMotor.setMaxSpeed(k_maxSpeed);
		m_leftMotor.setPTerm(k_P);
        m_rightMotor.setPTerm(k_P);

        m_leftMotor.setITerm(k_I);;
        m_rightMotor.setITerm(k_I);;
        m_leftMotor.setIZone(k_IZone);
        m_rightMotor.setIZone(k_IZone);
	}

	public Encoder GetLeftEncoder() {
		return (m_leftEncoder);
	}

	public Encoder GetRightEncoder() {
		return (m_rightEncoder);
	}

	public void initDefaultCommand() {
		Logger.Log("DriveSubsystem", 2, "initDefaultCommand()");
		setDefaultCommand(new ArcadeDriveCommand());
	}

	public void SetPower(double leftpower, double rightpower) {
		m_leftMotor.setControlMode(SmartMotorMode.Power);
		m_rightMotor.setControlMode(SmartMotorMode.Power);
		m_rightMotor.set(rightpower);
		m_leftMotor.set(leftpower);
	}
	public void SetSpeed(double leftSpeed, double rightSpeed)
    {
        m_leftMotor.setControlMode(SmartMotorMode.Speed);
        m_rightMotor.setControlMode(SmartMotorMode.Speed);
        
        m_leftMotor.set(leftSpeed);
        m_rightMotor.set(rightSpeed);
    }
}
