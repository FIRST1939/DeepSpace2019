/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.climber;

import com.frcteam1939.deepspace2019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetClimberWheelsMotorSpeed extends Command {

  private double speed;

  public SetClimberWheelsMotorSpeed(double value) {
    requires(Robot.climber);
    speed = value;
  }

  @Override
  protected void initialize() {
    
  }

  @Override
  protected void execute() {
    Robot.climber.setClimberWheelsTalon(speed);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    Robot.climber.setClimberWheelsTalon(0);
  }

  @Override
  protected void interrupted() {
    Robot.climber.setClimberWheelsTalon(0);
  }
}
