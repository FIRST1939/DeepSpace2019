/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.automation;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import com.frcteam1939.deepspace2019.robot.subsystems.Climber;
import com.frcteam1939.deepspace2019.robot.Robot;
import com.frcteam1939.deepspace2019.robot.commands.manipulator.*;
import com.frcteam1939.deepspace2019.robot.commands.elevator.*;
import com.frcteam1939.deepspace2019.robot.commands.arm.ArmToBottom;
import com.frcteam1939.deepspace2019.robot.commands.arm.ArmToSetHeight;
import com.frcteam1939.deepspace2019.robot.commands.elevator.TuneElevatorPID;
import com.frcteam1939.deepspace2019.robot.subsystems.Arm;
import com.frcteam1939.deepspace2019.robot.subsystems.Lights;

import com.frcteam1939.deepspace2019.robot.subsystems.Drivetrain;
import com.frcteam1939.deepspace2019.robot.subsystems.Manipulator;
import com.frcteam1939.deepspace2019.util.Wait;
import com.frcteam1939.deepspace2019.robot.subsystems.Elevator;

public class SystemsCheck extends CommandGroup {
  /**
   * Add your docs here.
   */
  
  public SystemsCheck() {
   addSequential(new ArmToSetHeight());
   addSequential(new ArmToBottom());
   addSequential(new LowerManipulator());
   addSequential(new DeployCenterGrab());
   addSequential(new RetractCenterGrab());
   addSequential(new RaiseManipulator());
   addSequential(new ArmToSetHeight());
   addSequential(new SetElevatorMotorSpeed(.5));
   addSequential(new Wait(1));
   addSequential(new SetElevatorMotorSpeed(0));
   addSequential(new ArmToSetHeight());
   addSequential(new ElevatorToBottom());
   addSequential(new SetElevatorMotorSpeed(0));
   addSequential(new ArmToBottom());
   addSequential(new IntakeCargoCenter());
   addSequential(new ArmToSetHeight());
   addSequential(new OutputCargo());
  }
}
