package commands;

import robotCore.Logger;
import robotWpi.command.CommandGroup;

public class DriveCourseCommand extends CommandGroup {
	public DriveCourseCommand() 
    {
        Logger.Log("DriveCourseCommand", 3, "DriveCourseCommand()");
        
        int turn90 = 80;
        double speed = 0.5;
        
        addSequential(new DriveForDistanceCommand(speed, 20));
        addSequential(new PauseCommand(speed));
        addSequential(new TurnCommand(speed, turn90));
        addSequential(new PauseCommand(speed));
        addSequential(new DriveForDistanceCommand(speed, 10));
        addSequential(new PauseCommand(speed));
        addSequential(new TurnCommand(speed, turn90));
        addSequential(new PauseCommand(speed));
        addSequential(new DriveForDistanceCommand(speed, 20));
        addSequential(new PauseCommand(speed));
        addSequential(new TurnCommand(speed, turn90));
        addSequential(new PauseCommand(speed));
        addSequential(new DriveForDistanceCommand(speed, 10));
        addSequential(new PauseCommand(speed));
        addSequential(new TurnCommand(speed, turn90));
        
        
    }
}
