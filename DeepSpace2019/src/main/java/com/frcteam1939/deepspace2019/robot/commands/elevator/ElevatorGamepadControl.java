/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.elevator;
import com.frcteam1939.deepspace2019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class ElevatorGamepadControl extends Command {
  public ElevatorGamepadControl() {
      requires(Robot.elevator);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
   
   double move = -Robot.oi.gamepad.getRightY();
   if (Robot.elevator.isAtBottom() && move < 0){
     move = 0;
   }
   Robot.elevator.set(move);
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
