/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.elevator;

import com.frcteam1939.deepspace2019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import com.frcteam1939.deepspace2019.robot.DistanceConstants;

public class ElevatorToMiddlePID extends Command {
  public ElevatorToMiddlePID() {}

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.elevator.setHeightPID(DistanceConstants.elevatorLevelTwoPosition);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.elevator.set(0);
  }

  @Override
  protected void interrupted() {
    Robot.elevator.set(0);
  }
}
