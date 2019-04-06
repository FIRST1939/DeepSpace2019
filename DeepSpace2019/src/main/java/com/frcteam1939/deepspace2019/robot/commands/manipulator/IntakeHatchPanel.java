/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.manipulator;

import com.frcteam1939.deepspace2019.util.WaitUntilCanDeployCenterGrab;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeHatchPanel extends CommandGroup {

  public IntakeHatchPanel() {
    addSequential(new WaitUntilCanDeployCenterGrab());
    addSequential(new DeployCenterGrab());
  }
}
