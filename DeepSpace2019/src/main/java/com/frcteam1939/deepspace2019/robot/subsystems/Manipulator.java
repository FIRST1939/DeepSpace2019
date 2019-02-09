/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.deepspace2019.robot.RobotMap;
import com.frcteam1939.deepspace2019.robot.commands.manipulator.ManipulatorGamepadControl;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Manipulator extends Subsystem {
  
  public static final double SPEED = 1.0;

  private TalonSRX talon = new TalonSRX(RobotMap.rollerTalon);

  private Solenoid angleSolenoid = new Solenoid(RobotMap.manipulatorAngleSolenoid);
  private Solenoid shootSolenoid = new Solenoid(RobotMap.manipulatorShootSolenoid);
  private Solenoid velcroSolenoid = new Solenoid(RobotMap.manipulatorVelcroSolenoid);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManipulatorGamepadControl());
  }

  public void setRoller(double value){
    talon.set(ControlMode.PercentOutput, value);
  }

  public void manipulatorRaise(){
    angleSolenoid.set(false);
  }

  public void manipulatorLower(){
    angleSolenoid.set(true);
  }

  public void shootHatchPanel(){
    shootSolenoid.set(true);
  }

  public void retractShootSolenoid(){
    shootSolenoid.set(false);
  }

  public void grabHatchPanel(){
    velcroSolenoid.set(true);
  }

  public void retractVelcroSolenoid(){
    velcroSolenoid.set(false);
  }
}
