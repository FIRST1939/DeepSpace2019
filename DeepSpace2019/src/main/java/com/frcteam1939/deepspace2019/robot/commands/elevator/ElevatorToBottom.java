/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.elevator;

import com.frcteam1939.deepspace2019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorToBottom extends Command {

  double time;

  public ElevatorToBottom() {
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.elevator.set(-1);
    time = this.timeSinceInitialized();
  }

  @Override
  protected boolean isFinished() {
    return Robot.elevator.isAtBottom() || time > 3.0;
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
