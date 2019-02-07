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
import com.frcteam1939.deepspace2019.robot.RobotMap;
import com.frcteam1939.deepspace2019.robot.commands.elevator.ElevatorGamepadControl;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
//PID values that correct elevator height.
  private static final int TIMEOUT_MS = 0;
  private static final double MAX_SPEED = 50000;
  private static final double UNITS_PER_INCH = 22000;

  //The tuned PID values for the elevator for fine control
  private static final double P = 0;
  private static final double I = 0;
  private static final double D = 0;

  boolean PIDmode = false;


  private static int elevatorIndex = 0;
  
//a true or false value that returns if the elevator is moving up or down
  private boolean isRaising = false;
	private boolean isLowering = false;

  private TalonSRX talon = new TalonSRX(RobotMap.elevatorTalon);//initializes elevator talon

  public Elevator(){

    this.talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,TIMEOUT_MS);
    this.talon.setSensorPhase(true);
    this.talon.selectProfileSlot(elevatorIndex, 0);
    this.talon.configNominalOutputForward(+0, TIMEOUT_MS);
		this.talon.configNominalOutputReverse(-0, TIMEOUT_MS);
		this.talon.configPeakOutputForward(+1, TIMEOUT_MS);
		this.talon.configPeakOutputReverse(-1, TIMEOUT_MS);
		this.talon.configAllowableClosedloopError(elevatorIndex, 1000, TIMEOUT_MS);
		this.talon.configMotionCruiseVelocity((int) (MAX_SPEED * 0.5), TIMEOUT_MS);
		this.talon.configMotionAcceleration((int) (MAX_SPEED * 0.5), TIMEOUT_MS);
		this.talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, TIMEOUT_MS);
		this.talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, TIMEOUT_MS);

    //Set up PID for elevator
    this.talon.config_kP(elevatorIndex, P, TIMEOUT_MS);
		this.talon.config_kI(elevatorIndex, I, TIMEOUT_MS);
		this.talon.config_kD(elevatorIndex, D, TIMEOUT_MS);

  }
 

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    this.setDefaultCommand(new ElevatorGamepadControl());
  }

  public void set(double value) { // sets the talon to a specific percent output 
		this.talon.set(ControlMode.PercentOutput, value);

		if (value > 0) {
			this.isRaising = true;
			this.isLowering = false;
		} else {
			this.isRaising = false;
			this.isLowering = true;
		}
  }
  
  public void setPID(double P, double I, double D) {
    if(usePID()){
		this.talon.selectProfileSlot(elevatorIndex, 0);
		this.talon.config_kP(elevatorIndex, P, TIMEOUT_MS);
		this.talon.config_kI(elevatorIndex, I, TIMEOUT_MS);
    this.talon.config_kD(elevatorIndex, D, TIMEOUT_MS);
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

  
  public void setHeight(double height){//sets the current height of the elevator to the new desired height

      double newHeight = (height - this.getHeight())* UNITS_PER_INCH;
      this.talon.set(ControlMode.MotionMagic, newHeight);

      if (newHeight > 0) {
        this.isRaising = true;
        this.isLowering = false;
      } else {
        this.isRaising = false;
        this.isLowering = true;
      }

  }

  public double getRawUnits() {//returns the raw encoder values
		return this.talon.getSelectedSensorPosition(0);
  }
  
  public double getHeight() {//returns the position of the encoder in inches
		return this.talon.getSelectedSensorPosition(0) / UNITS_PER_INCH;
  }
  
  public double getSpeed() {// returns velocity of the talon.
		return this.talon.getSelectedSensorVelocity(0);
  }

  public void zeroEncoder() {
		this.talon.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
	}
  
  public void enableBrakeMode() {
		this.talon.setNeutralMode(NeutralMode.Brake);
	}

	public void disableBrakeMode() {
		this.talon.setNeutralMode(NeutralMode.Coast);
  }
  
  public void stop() {
		this.set(0);
	}
  public boolean isRaising() {
		return this.isRaising;
	}

	public boolean isLowering() {
		return this.isLowering;
	}

}
