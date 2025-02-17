/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.climber;

import com.frcteam1939.deepspace2019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberJoystickControl extends Command {
  public ClimberJoystickControl() {
    requires(Robot.climber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double wheelsValue = 0;

    if (Robot.oi.right.getRawButton(9) || Robot.oi.right.getRawButton(8)){
      wheelsValue = 0.5;
    }

    if (Robot.oi.right.getRawButton(11)){
      Robot.climber.frontClimberDown();
    }

    if (Robot.oi.right.getRawButton(10)){
      Robot.climber.frontClimberUp();
    }

    if (Robot.oi.right.getRawButton(6)){
      Robot.climber.backClimberDown();
    }

    if (Robot.oi.right.getRawButton(7)){
      Robot.climber.backClimberUp();
    }
  
    Robot.climber.setClimberWheelsTalon(wheelsValue);
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
