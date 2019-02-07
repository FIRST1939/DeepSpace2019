/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.elevator;

import com.frcteam1939.deepspace2019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetElevatorMotorSpeed extends Command {
      private double speed = 0;

  public SetElevatorMotorSpeed(double value) {
      requires(Robot.elevator);
      this.speed = value;
  }

  @Override
  protected void initialize() {
      Robot.elevator.set(speed);
  }

  @Override
  protected void execute() {
      Robot.elevator.set(speed);
  }

  @Override
  protected boolean isFinished() {
      return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
