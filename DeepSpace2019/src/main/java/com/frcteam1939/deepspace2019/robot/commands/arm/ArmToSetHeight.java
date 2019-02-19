/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.arm;

import com.frcteam1939.deepspace2019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArmToSetHeight extends Command {

  double time;

  public ArmToSetHeight() {
    requires(Robot.arm);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.arm.set(0.75);
    time = this.timeSinceInitialized();
  }

  @Override
  protected boolean isFinished() {
    return Robot.arm.isAtSetHeight() || time > 2.5;
  }

  @Override
  protected void end() {
    Robot.arm.set(0);
  }

  @Override
  protected void interrupted() {
    Robot.arm.set(0);
  }
}
