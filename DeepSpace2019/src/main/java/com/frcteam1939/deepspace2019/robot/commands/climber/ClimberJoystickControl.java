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
    double rackGearValue = 0;
    double skisValue = 0;

    if (Robot.oi.left.getRawButton(6)){
      wheelsValue = 0.5;
    }

    if (Robot.oi.left.getRawButton(11)){
      skisValue = -1;
    }

    if (Robot.oi.right.getRawButton(6)){
      rackGearValue = 0.75;
    }

    if (Robot.oi.right.getRawButton(11)){
      rackGearValue = -0.75;
    }
    if(Robot.oi.left.getRawButton(10)){
      skisValue = 1;
    }
  

    Robot.climber.setClimberWheelsTalon(wheelsValue);
    Robot.climber.setSkiTalon(skisValue);
    Robot.climber.setRackGearSpark(rackGearValue);
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
