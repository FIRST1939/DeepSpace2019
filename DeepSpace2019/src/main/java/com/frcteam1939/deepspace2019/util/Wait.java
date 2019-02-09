/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.util;

import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {

  private double timeout;

  public Wait(double timeout) {
    this.timeout = timeout;
  }

  @Override
  protected void initialize() {
    this.setTimeout(timeout);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return this.isTimedOut();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
