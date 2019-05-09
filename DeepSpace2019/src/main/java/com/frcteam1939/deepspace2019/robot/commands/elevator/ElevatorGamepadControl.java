/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.elevator;
import com.frcteam1939.deepspace2019.robot.Robot;
import com.frcteam1939.deepspace2019.robot.commands.automation.SetHeightMiddle;
import com.frcteam1939.deepspace2019.robot.commands.automation.SetHeightTop;
import com.frcteam1939.deepspace2019.util.Wait;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;


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

   
   if(Robot.oi.gamepad.getPOV(0)==0.0){
    Scheduler.getInstance().add(new ElevatorToTop());
   }
   else if(Robot.oi.gamepad.getPOV(0)==90.0){
    Scheduler.getInstance().add(new ElevatorToMiddle());
   }

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
