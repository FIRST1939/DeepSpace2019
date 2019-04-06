/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.manipulator;

import com.frcteam1939.deepspace2019.robot.Robot;
import com.frcteam1939.deepspace2019.robot.subsystems.Manipulator;

import edu.wpi.first.wpilibj.command.Command;

public class CargoMoveUp extends Command {
  public CargoMoveUp() {
    requires(Robot.manipulator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.manipulator.setRoller(Manipulator.CARGO_UP_SPEED);
  }

  @Override
  protected boolean isFinished() {
    return Robot.manipulator.cargoIsAtTop();
  }

  @Override
  protected void end() {
    Robot.manipulator.setRoller(0);
  }

  @Override
  protected void interrupted() {
    Robot.manipulator.setRoller(0);
  }
}
