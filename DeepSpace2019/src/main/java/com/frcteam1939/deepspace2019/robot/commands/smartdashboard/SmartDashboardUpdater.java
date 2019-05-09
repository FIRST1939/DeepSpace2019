/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.smartdashboard;

import com.frcteam1939.deepspace2019.robot.Robot;
import com.frcteam1939.deepspace2019.robot.commands.automation.*;
import com.frcteam1939.deepspace2019.robot.commands.elevator.ElevatorToTop;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardUpdater extends Command {

  public SmartDashboardUpdater() {
    requires(Robot.smartDashboard);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {

    // Adds data into the Smart Dashboard to display on the drivestation computer

		SmartDashboard.putNumber("Left Voltage", Robot.drivetrain.getLeftVoltage());
		SmartDashboard.putNumber("Left Percent Output", Robot.drivetrain.getLeftPercentOutput());

		SmartDashboard.putNumber("Right Voltage", Robot.drivetrain.getRightVoltage());
    SmartDashboard.putNumber("Right Percent Output", Robot.drivetrain.getRightPercentOutput());
    
    // SmartDashboard.putNumber("Heading", Robot.drivetrain.getHeading());
    
    SmartDashboard.putBoolean("Arm Is At Set Height", Robot.arm.isAtSetHeight());
    SmartDashboard.putBoolean("Arm Is At Bottom", Robot.arm.armAtBottom());

    SmartDashboard.putNumber("Elevator Revolutions", Robot.elevator.getRawUnits());
    SmartDashboard.putNumber("Elevator Height", Robot.elevator.getHeight());
    SmartDashboard.putNumber("Elevator Speed", Robot.elevator.getSpeed());
    SmartDashboard.putBoolean("Elevator Is At Bottom", Robot.elevator.isAtBottom());

    SmartDashboard.putBoolean("Cargo is at Bottom", Robot.manipulator.cargoIsAtBottom());
    SmartDashboard.putBoolean("Cargo is at Top", Robot.manipulator.cargoIsAtTop());
    SmartDashboard.putBoolean("Can Deploy Center Grab", Robot.manipulator.hasHatchPanel());

    SmartDashboard.putBoolean("Center Grab is Deployed", Robot.manipulator.centerGrabDeployed);
    SmartDashboard.putBoolean("Center Grab is Retracted", Robot.manipulator.centerGrabIn);

    SmartDashboard.putNumber("Vision Horizontal Error", Robot.drivetrain.limelight.getHorizontalAngleError());
    SmartDashboard.putNumber("Vision Vertical Error", Robot.drivetrain.limelight.getVerticalAngleError());
    SmartDashboard.putNumber("Vision Target Area", Robot.drivetrain.limelight.getTargetArea());
    SmartDashboard.putNumber("Vision Target Distance", Robot.drivetrain.limelight.getTargetDistance());
    SmartDashboard.putNumber("Vision Target Exact Distance", Robot.drivetrain.limelight.getTargetExactDistance());
    SmartDashboard.putNumber("Vision Latency", Robot.drivetrain.limelight.getLatency());
    SmartDashboard.putNumber("Vision Skew", Robot.drivetrain.limelight.getSkew());
    SmartDashboard.putNumber("Pov", Robot.oi.gamepad.getPOV(0));
   // SmartDashboard.putNumber("degrees", Robot.oi.gamepad.getDirectionDegrees());
    SmartDashboard.putNumber("Vision Pipeline Number", Robot.drivetrain.limelight.getPipeline());
    SmartDashboard.putNumber("Vision Camera Mode", Robot.drivetrain.limelight.getCamMode());
    SmartDashboard.putNumber("Vision LED Mode", Robot.drivetrain.limelight.getLEDMode());

    SmartDashboard.putBoolean("Vision Has Found Target", Robot.drivetrain.limelight.isTargetFound());

    SmartDashboard.putNumber("Pressure", Robot.getPressure());

    SmartDashboard.putNumber("Skis Current", Robot.climber.getSkisCurrent());
    SmartDashboard.putData("Systems Check", new SystemsCheck());
    SmartDashboard.putData("Elevator to top", new ElevatorToTop());
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
