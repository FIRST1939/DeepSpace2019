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
      requires(Robot.elevator);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
   
      double move = Robot.oi.gamepad.getRightY();
      Robot.elevator.set(move);

      if(Robot.elevator.usePID()){
        Robot.oi.gamepad.a.whenPressed(new ElevatorToBottomPID());
        Robot.oi.gamepad.b.whenPressed(new ElevatorToMiddlePID());
        Robot.oi.gamepad.y.whenPressed(new ElevatorToTopPID());
     }
     else{
        Robot.oi.gamepad.b.whenPressed(new ElevatorToMiddle());
        Robot.oi.gamepad.y.whenPressed(new ElevatorToTop());
     }
  }

  @Override
  protected boolean isFinished() {
        return false;
  }

  @Override
  protected void end() {
        Robot.elevator.stop();
  }

  @Override
  protected void interrupted() {
        Robot.elevator.stop();
  }
}
