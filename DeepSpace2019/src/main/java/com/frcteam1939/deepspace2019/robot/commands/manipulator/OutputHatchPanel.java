/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.manipulator;

import com.frcteam1939.deepspace2019.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OutputHatchPanel extends CommandGroup {

  public OutputHatchPanel() {
    addSequential(new ShootHatchPanel());
    addSequential(new Wait(0.5));
    addSequential(new RetractShootSolenoid());
  }
}
