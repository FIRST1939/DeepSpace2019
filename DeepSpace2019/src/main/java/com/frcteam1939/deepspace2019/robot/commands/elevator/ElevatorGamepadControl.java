/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.elevator;
import com.frcteam1939.deepspace2019.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class ElevatorGamepadControl extends Command {
  public ElevatorGamepadControl() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.requires(Robot.elevator);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if(Robot.elevator.usePID()){//checks to see if PID controll is enabled
       Robot.elevator.enablePID();//enables PID control of height
    }
    else{Robot.elevator.diablePID();}//disables PID control
   
    
    double move = -Robot.oi.gamepad.getRightY();
    Robot.elevator.set(move);

    if (Robot.oi.gamepad.back.get()) {
			Robot.elevator.stop();
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.elevator.stop();
  }
}
