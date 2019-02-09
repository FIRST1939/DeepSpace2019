/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.arm;

import com.frcteam1939.deepspace2019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetArmAngle extends Command {

  private double angle;

  public SetArmAngle(double angle) {
    requires(Robot.arm);
    this.angle = angle;
  }

  @Override
  protected void initialize() {
    Robot.arm.armPID.reset();
    Robot.arm.armPID.enable();

    double newAngle = angle - Robot.arm.getPotentiometer();
    Robot.arm.armPID.setSetpoint(newAngle);
  }

  @Override
  protected void execute() {
    Robot.arm.set(Robot.arm.armPID.get());
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(angle - Robot.arm.getPotentiometer()) < 0.25;
  }

  @Override
  protected void end() {
    Robot.arm.stop();
  }

  @Override
  protected void interrupted() {
    Robot.arm.stop();
  }
}
