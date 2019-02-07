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
import com.frcteam1939.deepspace2019.robot.commands.arm.ArmGamepadControl;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {

  private TalonSRX talon = new TalonSRX(RobotMap.armTalon);

  public PIDController armPID;
  private static final double armF = 0.0;
  private static final double armP = 0.0;
  private static final double armI = 0.0;
  private static final double armD = 0.0;

  private static final double MAX_OUTPUT = 0.25;

  private AnalogInput ai = new AnalogInput(RobotMap.potentiometer);
  private AnalogPotentiometer potentiometer = new AnalogPotentiometer(ai, 360, 0);

  public Arm(){
    potentiometer.setPIDSourceType(PIDSourceType.kDisplacement);
    armPID = new PIDController(armP, armI, armD, armF, potentiometer, output -> {});
    armPID.setInputRange(-180, 180);
    armPID.setContinuous(true);
    armPID.setOutputRange(-MAX_OUTPUT, MAX_OUTPUT);
    armPID.setSetpoint(0);
    armPID.enable();

    talon.configNominalOutputForward(+0);
		talon.configNominalOutputReverse(-0);
		talon.configPeakOutputForward(+1);
    talon.configPeakOutputReverse(-1);
    talon.enableVoltageCompensation(true);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArmGamepadControl());
  }

  public void set(double value){
    talon.set(ControlMode.PercentOutput, value);
  }

  public double getPotentiometer(){
    return potentiometer.get();
  }
}
