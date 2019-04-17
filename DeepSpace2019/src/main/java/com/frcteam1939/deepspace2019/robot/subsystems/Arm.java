/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.deepspace2019.robot.RobotMap;
import com.frcteam1939.deepspace2019.robot.commands.arm.ArmGamepadControl;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

  private TalonSRX talon = new TalonSRX(RobotMap.armTalon);

  private DigitalInput setHeightHallEffect = new DigitalInput(RobotMap.armSetHeightHallEffect);
  private DigitalInput bottomHallEffect = new DigitalInput(RobotMap.armAtBottomHallEffect);

  private static final double MAX_OUTPUT = 0.25;

  public Arm(){

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

  // Positive is moving up
  // Negative is moving down
  public void set(double value){
    talon.set(ControlMode.PercentOutput, -value);
  }

  public void stop(){
    set(0);
  }

  public void enableBrakeMode(){
    talon.setNeutralMode(NeutralMode.Brake);
  }

  public void disableBrakeMode(){
    talon.setNeutralMode(NeutralMode.Coast);
  }

  public boolean isAtSetHeight(){
    return !setHeightHallEffect.get();
  }

  public boolean armAtBottom(){
    return !bottomHallEffect.get();
  }
}
