/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.deepspace2019.robot.Robot;
import com.frcteam1939.deepspace2019.robot.RobotMap;
import com.frcteam1939.deepspace2019.robot.commands.elevator.ElevatorGamepadControl;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {

  private static final double MAX_SPEED = 50000;
  private static final double UNITS_PER_INCH = 22000;

  // The tuned PID values for the elevator for finite control
  private static final double P = 0;
  private static final double I = 0;
  private static final double D = 0;

  private static final double F = 0;

  //private static final STALL_CURRENT = 131;
  //private static final double STALL_TORQUE = 2.41;
  

  boolean PIDmode = false;

  private static int elevatorIndex = 0;

   // Initializes elevator talon & Sensors
  private TalonSRX talon = new TalonSRX(RobotMap.elevatorTalon);
  private DigitalInput isAtBottom = new DigitalInput(RobotMap.elevatorAtBottom);
  private DigitalInput isAtTop = new DigitalInput(RobotMap.elevatorAtTop);
  private DigitalInput isAtMiddle= new DigitalInput(RobotMap.elevatorAtMiddle);

   
   


  public Elevator(){

    //***** ENABLE PID ****\\
    this.enablePID();
    //***** ENABLE PID ****\\

    talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    talon.selectProfileSlot(elevatorIndex, 0);
    talon.configNominalOutputForward(+0);
		talon.configNominalOutputReverse(-0);
		talon.configPeakOutputForward(+1);
		talon.configPeakOutputReverse(-1);
		talon.configAllowableClosedloopError(elevatorIndex, 1000);
		talon.configMotionCruiseVelocity((int) (MAX_SPEED * 0.5));
		talon.configMotionAcceleration((int) (MAX_SPEED * 0.5));
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10);
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10);

    // Sets up PID for elevator
    talon.config_kP(elevatorIndex, P);
		talon.config_kI(elevatorIndex, I);
    talon.config_kD(elevatorIndex, D);


  
   
    //LiveWindow.addActuator("Elevator", "PIDSubsytem Controller",this.P);
  }
 
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ElevatorGamepadControl());
  }

  // Sets the talon to a specific percent output 
  public void set(double value) { 
		talon.set(ControlMode.PercentOutput, value);
  }
  
  public void setPID(double P, double I, double D) {
    if(usePID()){
	  	talon.selectProfileSlot(elevatorIndex, 0);
		  talon.config_kP(elevatorIndex, P);
		  talon.config_kI(elevatorIndex, I);
      talon.config_kD(elevatorIndex, D);
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
      talon.set(ControlMode.MotionMagic, newHeight);
  }

  // Returns the position of the encoder in raw encoder values
  public double getRawUnits() {
		return talon.getSelectedSensorPosition(0);
  }
  
  // Returns the position of the encoder in inches
  public double getHeight() {
		return talon.getSelectedSensorPosition(0) / UNITS_PER_INCH;
  }
  
  // Returns the velocity of the talon.
  public double getSpeed() {
		return talon.getSelectedSensorVelocity(0);
  }
  
  public void enableBrakeMode() {
		talon.setNeutralMode(NeutralMode.Brake);
	}

	public void disableBrakeMode() {
		talon.setNeutralMode(NeutralMode.Coast);
  }
  
  public void stop() {
		set(0);
  }

  public boolean isAtTop() {
		return isAtTop.get();
	}

	public boolean isAtBottom() {
		return isAtBottom.get();
	}

	public boolean isAtMiddle() {
		return isAtMiddle.get();
	}

	
}
