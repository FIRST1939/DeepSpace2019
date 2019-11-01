/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.subsystems;

import com.frcteam1939.deepspace2019.robot.Robot;
import com.frcteam1939.deepspace2019.robot.RobotMap;
import com.frcteam1939.deepspace2019.robot.commands.elevator.ElevatorGamepadControl;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

  public double MAX_VEL = 2000;
  public double MIN_VEL = 0;
  public double MAX_OUTPUT = 1;
  public double MIN_OUTPUT = -1;
  public double MAX_RPM = 5700;//HOLDER
  public double MAX_ACCL = 1500;//HOLDER
  public double ALLOWED_ERR = .01;
  public double UNITS_PER_INCH = 0.1;


  // The tuned PID values for the elevator for finite control
  public double P = 0;
  public double I = 0;
  public double D = 0;

  public double izone = 0;
  public double F = 0;

  private static final double STALL_CURRENT = 131;
  private static final double STALL_TORQUE = 2.41;
  
  boolean PIDmode = false;

  public static final  int smartMotionSlot = 0;

   // Initializes elevator talon & Sensors
  private CANSparkMax spark = new CANSparkMax(RobotMap.elevatorSpark, MotorType.kBrushless);
  public CANPIDController sparkPID = spark.getPIDController();
  public CANEncoder sparkEncoder = spark.getEncoder();

  private DigitalInput isAtBottom = new DigitalInput(RobotMap.elevatorAtBottomHallEffect);
  // private DigitalInput isAtMiddle = new DigitalInput(RobotMap.elevatorAtMiddleHallEffect);
  // private DigitalInput isAtTop = new DigitalInput(RobotMap.elevatorAtTopHallEffect);

  public Elevator(){
   
    //***** ENABLE PID ****\\
    this.enablePID();
    //***** ENABLE PID ****\\


    // Sets up PID for elevator
    sparkPID.setP(P);
    sparkPID.setI(I);
    sparkPID.setD(D);
    sparkPID.setIZone(izone);//Set the IZone range of the PIDF controller on the SPARK MAX. This value specifies the range the |error| must be within for the integral constant to take effect.
    sparkPID.setFF(F);
    sparkPID.setOutputRange(MIN_OUTPUT, MAX_OUTPUT);
    
  }
 
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ElevatorGamepadControl());
  }

  // Positive is moving up
  // Negative is moving down
  public void set(double value) { 
    spark.set(-value);
    if((value>0) && Robot.manipulator.hasCargo()){
      Robot.lights.orangeUp();
    }
    else if((value<0) && Robot.manipulator.hasCargo()){
      Robot.lights.orangeDown();
    }
  }
  
  public void setPID(double P, double I, double D) {
    if(usePID()){
     
      sparkPID.setSmartMotionMaxVelocity(MAX_VEL, smartMotionSlot);
      sparkPID.setSmartMotionMinOutputVelocity(MIN_VEL, smartMotionSlot);
      sparkPID.setSmartMotionMaxAccel(MAX_ACCL, smartMotionSlot);
      sparkPID.setSmartMotionAllowedClosedLoopError(ALLOWED_ERR, smartMotionSlot);

    }
  }

  public boolean usePID(){
    return PIDmode;
  }

  public void enablePID(){
    PIDmode = true;
  }

  public void diablePID(){
    PIDmode = false;
  }

  // Sets the current height of the elevator to the new desired height
  public void setHeight(double height){
      double newHeight = (height - getHeight()) * UNITS_PER_INCH;
      spark.set(newHeight);
  }
  public void setHeightPID(double height){
    double newHeight = (height - getHeight()) * UNITS_PER_INCH;
    sparkPID.setReference(newHeight, ControlType.kSmartMotion);
}

  public void setEncoderHeight(double height){
    double newHeight = height * UNITS_PER_INCH;
    sparkEncoder.setPosition((int) newHeight);
  }

  // Returns the position of the encoder in raw encoder values
  public double getRawUnits() {
    return sparkEncoder.getPosition();
  }
  
  // Returns the position of the encoder in inches
  public double getHeight() {
    return sparkEncoder.getPosition()/ UNITS_PER_INCH;
  }
  
  // Returns the velocity of the talon.
  public double getSpeed() {
    return sparkEncoder.getVelocity();
  }
  
  public void enableBrakeMode() {
    spark.setIdleMode(IdleMode.kBrake);
	}

	public void disableBrakeMode() {
    spark.setIdleMode(IdleMode.kCoast);
  }
  
  public void stop() {
		set(0);
  }

	public boolean isAtBottom() {
		return !isAtBottom.get();
  }
  
  // public boolean isAtMiddle(){
    // return !isAtMiddle.get();
  // }

  // public boolean isAtTop(){
    // return !isAtTop.get();
  // }
}
