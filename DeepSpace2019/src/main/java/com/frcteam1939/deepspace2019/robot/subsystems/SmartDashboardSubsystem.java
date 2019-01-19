/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.subsystems;

import com.frcteam1939.deepspace2019.robot.commands.smartdashboard.SmartDashboardUpdater;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SmartDashboardSubsystem extends Subsystem {

  @Override
  public void initDefaultCommand() {
		Command command = new SmartDashboardUpdater();
		command.setRunWhenDisabled(true);

		setDefaultCommand(command);
  }
}
