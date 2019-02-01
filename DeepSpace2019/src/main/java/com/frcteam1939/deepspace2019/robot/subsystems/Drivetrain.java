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
import com.frcteam1939.deepspace2019.robot.commands.drivetrain.DriveByJoystick;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {

  private static final int WHEEL_DIAMETER = 6;
  private static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;

  private static final int MAX_SPEED = 0;

  private static final int CPR = 0;

  private static final int posIndex = 0;
  private static final double posP = 0;
  private static final double posI = 0;
  private static final double posD = 0;

  private TalonSRX frontLeft = new TalonSRX(RobotMap.leftFrontTalon);
  private TalonSRX backLeft = new TalonSRX(RobotMap.leftBackTalon);
  private TalonSRX frontRight = new TalonSRX(RobotMap.rightFrontTalon);
  private TalonSRX backRight = new TalonSRX(RobotMap.rightBackTalon);
  private CANSparkMax sidewinder = new CANSparkMax(RobotMap.sidewinderSpark, MotorType.kBrushless);

  private DoubleSolenoid sidewinderSolenoid = new DoubleSolenoid(RobotMap.sidewinderUpSolenoid, RobotMap.sidewinderDownSolenoid);

  public Drivetrain(){
    setupMasterTalons();
    backLeft.follow(frontLeft);
    backRight.follow(frontRight);
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveByJoystick());
  }

  // Get Methods 

  public double getLeftPosition(){
    return frontLeft.getSelectedSensorPosition();
  }

  public double getRightPosition(){
    return frontRight.getSelectedSensorPosition();
  }

  public double getLeftVelocity(){
    return frontLeft.getSelectedSensorVelocity();
  }

  public double getRightVelocity(){
    return frontRight.getSelectedSensorVelocity();
  }

  // Set Methods

  public void setPercentOutput(double leftPercent, double rightPercent){
    frontLeft.set(ControlMode.PercentOutput, leftPercent);
    frontRight.set(ControlMode.PercentOutput, rightPercent);
  }

  public void stop(){
    setPercentOutput(0, 0);
  }

  public void strafe(double value){
    sidewinder.set(value);
  }

  public void sidewinderUp(){
    sidewinderSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void sidewinderDown(){
    sidewinderSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void drive(double moveValue, double rotateValue, double strafeValue) {
		// Strafe with Sidewinder
		strafe(strafeValue);

		// Calculate left and right speeds from move and rotate values
		double leftMotorSpeed;
		double rightMotorSpeed;
		if (moveValue > 0.0) {
			if (rotateValue > 0.0) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			} else {
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		} else {
			if (rotateValue > 0.0) {
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			} else {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}

		// Tell Drivetrain to move
    setPercentOutput(leftMotorSpeed, rightMotorSpeed);
    
		SmartDashboard.putNumber("Move Output", moveValue);
		SmartDashboard.putNumber("Turn Output", rotateValue);
		SmartDashboard.putNumber("Strafe Output", strafeValue);
  }

  public void enableBrakeMode(){
    frontLeft.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);
    frontRight.setNeutralMode(NeutralMode.Brake);
    frontLeft.setNeutralMode(NeutralMode.Brake);
  }

  public void disableBrakeMode(){
    frontLeft.setNeutralMode(NeutralMode.Coast);
    backLeft.setNeutralMode(NeutralMode.Coast);
    frontRight.setNeutralMode(NeutralMode.Coast);
    frontLeft.setNeutralMode(NeutralMode.Coast);
  }

  public void setPositionPID(double P, double I, double D){
    frontLeft.selectProfileSlot(posIndex, 0);
    frontRight.selectProfileSlot(posIndex, 0);
    frontLeft.config_kP(posIndex, P);
    frontLeft.config_kI(posIndex, I);
    frontLeft.config_kD(posIndex, D);
    frontRight.config_kP(posIndex, P);
    frontRight.config_kI(posIndex, I);
    frontRight.config_kD(posIndex, D);
  }

  // Private Convenience Methods

  private void setupMasterTalons(){
    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    frontRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    setPositionPID(posP, posI, posD);
    frontLeft.enableVoltageCompensation(true);
    frontRight.enableVoltageCompensation(true);
		frontLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10);
		frontLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10);
		frontRight.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10);
		frontRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10);
  }
}
