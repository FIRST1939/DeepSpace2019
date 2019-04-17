/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.deepspace2019.robot.Robot;
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
  

  private AnalogInput distanceSensor = new AnalogInput(RobotMap.distanceSensor);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManipulatorGamepadControl());
  }

  public void setRoller(double value){
    talon.set(ControlMode.PercentOutput, value);
    if((Math.abs(value))>0){
      Robot.lights.orangeBlink();
    }
  }

  public void manipulatorRaise(){
    angleSolenoid.set(false);
  }

  public void manipulatorLower(){
    angleSolenoid.set(true);
  }

  public void deployCenterGrab(){
    centerGrabSolenoid.set(true);
    Robot.lights.solidGreen();
  }

  public void retractCenterGrab(){
    centerGrabSolenoid.set(false);
    Robot.lights.solidRed();
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
  
  public boolean hasCargo(){
    return (Robot.manipulator.cargoIsAtBottom() && Robot.manipulator.cargoIsAtTop());
  }

  public boolean hasHatchPanel(){
    if(limit.get()){
      Robot.lights.solidYellow();
    }
    return limit.get();

  }
}
