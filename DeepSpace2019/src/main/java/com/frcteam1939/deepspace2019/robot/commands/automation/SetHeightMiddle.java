/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.automation;

import com.frcteam1939.deepspace2019.robot.commands.arm.ArmToSetHeight;
import com.frcteam1939.deepspace2019.robot.commands.elevator.ElevatorToMiddle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetHeightMiddle extends CommandGroup {

  public SetHeightMiddle() {
    addParallel(new ArmToSetHeight());
    addSequential(new ElevatorToMiddle());
  }
}