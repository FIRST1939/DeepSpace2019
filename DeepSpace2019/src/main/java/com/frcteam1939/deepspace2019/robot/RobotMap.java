
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot;

public class RobotMap {
  
  // Talons/Victors
	public static final int leftFrontTalon = 24;
	public static final int leftBackVictor = 34;
	public static final int rightFrontTalon = 29;
  public static final int rightBackVictor = 35;
  public static final int elevatorSpark = 7;
  public static final int armTalon = 14;
  public static final int rollerTalon = 26;
  public static final int skiTalon = 16;
  public static final int climberWheelsTalon = 22;
  
  // Solenoids
	public static final int PCM = 0;
  public static final int manipulatorAngleSolenoid = 7;
  public static final int manipulatorCenterGrabSolenoid = 6;
  public static final int climberSolenoid = 1;

  // Analog Input
  public static final int pressureSensor = 0;
  public static final int distanceSensor = 1;

  // Digital Input
  public static final int armSetHeightHallEffect = 0;
  public static final int cargoAtBottomBanner = 1;
  public static final int elevatorAtBottomHallEffect = 2;
  public static final int armAtBottomHallEffect = 3;
  // public static final int elevatorAtMiddleHallEffect = 3;
  // public static final int elevatorAtTopHallEffect = 4;
  public static final int cargoAtTopBanner = 5;
  public static final int cargoLimitSwitch = 6;

  //Digital Output
  public static final int light4 =4;
  public static final int light7 =7;
  public static final int light8 =8;
  public static final int light9 =9;

}
