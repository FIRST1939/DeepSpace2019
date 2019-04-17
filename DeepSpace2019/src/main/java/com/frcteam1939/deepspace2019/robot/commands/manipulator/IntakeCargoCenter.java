/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.manipulator;

import com.frcteam1939.deepspace2019.robot.Robot;
import com.frcteam1939.deepspace2019.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeCargoCenter extends CommandGroup {

  public IntakeCargoCenter() {
    addSequential(new IntakeCargo());
    addSequential(new Wait(0.5));
    if (Robot.manipulator.cargoIsAtBottom() && !Robot.manipulator.cargoIsAtTop()){
      addSequential(new CargoMoveUp());
    }
    else if (!Robot.manipulator.cargoIsAtBottom() && Robot.manipulator.cargoIsAtTop()){
      addSequential(new CargoMoveDown());
    }
  }
}