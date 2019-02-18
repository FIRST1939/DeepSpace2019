
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
  public static final int sidewinderSpark = 5;
  public static final int elevatorSpark = 7;
  public static final int armTalon = 14;
  public static final int rollerTalon = 26;
  public static final int skiTalon = 16;
  public static final int rackGearSpark = 6;
  public static final int climberWheelsTalon = 22;
  
  // Solenoids
	public static final int PCM = 0;
  public static final int sidewinderUpSolenoid = 0;
  public static final int sidewinderDownSolenoid = 1;
  public static final int manipulatorAngleSolenoid = 7;
  public static final int manipulatorShootSolenoid = 5;
  public static final int manipulatorVelcroSolenoid = 6;

  // Analog Input
  public static final int pressureSensor = 0;
  public static final int potentiometer = 1;

  // Digital Input
  public static final int hasCargoBanner = 1;
  public static final int elevatorAtBottomHallEffect = 2;

}