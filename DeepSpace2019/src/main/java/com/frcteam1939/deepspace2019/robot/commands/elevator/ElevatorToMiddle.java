/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.elevator;

import com.frcteam1939.deepspace2019.robot.Robot;
import com.frcteam1939.deepspace2019.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ElevatorToMiddle extends CommandGroup {
  private static final double TIME_TO_MIDDLE = .5;

  public ElevatorToMiddle() {
    if(Robot.elevator.usePID()){
      addSequential(new ElevatorToTopPID());
    } 
    else{
      addSequential(new SetElevatorMotorSpeed(1));
      addSequential(new Wait(TIME_TO_MIDDLE));
      addSequential(new SetElevatorMotorSpeed(0));
    }
  }
}
