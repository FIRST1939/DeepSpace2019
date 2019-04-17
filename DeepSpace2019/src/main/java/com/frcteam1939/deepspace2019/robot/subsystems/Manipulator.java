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

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Manipulator extends Subsystem {
  
  public static final double IN_SPEED = -1.0;
  public static final double OUT_SPEED = -0.75;
  public static final double FINGER_OUT_SPEED = 0.4;
  public static final double FINGER_IN_SPEED = -1.0;
  public static final double CARGO_UP_SPEED = -0.2;
  public static final double CARGO_DOWN_SPEED = 0.2;

  public boolean centerGrabIn = true;
  public boolean centerGrabDeployed = false;

  private TalonSRX talon = new TalonSRX(RobotMap.rollerTalon);

  private Solenoid angleSolenoid = new Solenoid(RobotMap.manipulatorAngleSolenoid);
  private Solenoid centerGrabSolenoid = new Solenoid(RobotMap.manipulatorCenterGrabSolenoid);

  private DigitalInput topBanner = new DigitalInput(RobotMap.cargoAtTopBanner);
  private DigitalInput bottomBanner = new DigitalInput(RobotMap.cargoAtBottomBanner);
  private DigitalInput limit = new DigitalInput(RobotMap.cargoLimitSwitch);
  
  private DigitalOutput light1 = new DigitalOutput(RobotMap.light4);
  private DigitalOutput light2 = new DigitalOutput(RobotMap.light7);
  private DigitalOutput light3 = new DigitalOutput(RobotMap.light8);
  private DigitalOutput light4 = new DigitalOutput(RobotMap.light9);

  private AnalogInput distanceSensor = new AnalogInput(RobotMap.distanceSensor);

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

  public void deployCenterGrab(){
    centerGrabSolenoid.set(true);
    light1.set(true);
    light2.set(true);
    light3.set(true);
    light4.set(true);
  }

  public void retractCenterGrab(){
    centerGrabSolenoid.set(false);
    light1.set(false);
    light2.set(false);
    light3.set(false);
    light4.set(false);
  }

  public double getDistanceSensorReading(){
    return distanceSensor.getVoltage();
  }

  public boolean cargoIsAtBottom(){
    return !bottomBanner.get();
  }

  public boolean cargoIsAtTop(){
    return !topBanner.get();
  }

  public boolean canDeployCenterGrab(){
    return limit.get();
  }
}
