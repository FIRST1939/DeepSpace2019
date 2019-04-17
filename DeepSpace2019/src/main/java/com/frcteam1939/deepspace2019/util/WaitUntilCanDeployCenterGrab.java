/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.util;

import com.frcteam1939.deepspace2019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class WaitUntilCanDeployCenterGrab extends Command {
  public WaitUntilCanDeployCenterGrab() {
    requires(Robot.manipulator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.lights.yellowBlink();
  }

  @Override
  protected boolean isFinished() {
    return Robot.manipulator.hasHatchPanel() || Robot.oi.gamepad.leftButton.get();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
