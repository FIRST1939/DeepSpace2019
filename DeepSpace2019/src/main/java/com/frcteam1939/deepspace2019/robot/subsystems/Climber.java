/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.subsystems; //package declaration

import edu.wpi.first.wpilibj.command.Subsystem; //import the Subsystem template class
import com.ctre.phoenix.motorcontrol.ControlMode; //imports Control Mode for the motor controllers
import com.ctre.phoenix.motorcontrol.NeutralMode; //imports the NeutralMode for the motor controllers
import com.ctre.phoenix.motorcontrol.can.TalonSRX; //import the TalonSRX Class
import com.frcteam1939.deepspace2019.robot.RobotMap;//import robotmap file for motor controller ids
import com.frcteam1939.deepspace2019.robot.commands.climber.ClimberJoystickControl;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climber extends Subsystem {
    TalonSRX skiTalon = new TalonSRX(RobotMap.skiTalon); //construct the skiTalon, which controls the skis
    CANSparkMax rackGearSpark = new CANSparkMax(RobotMap.rackGearSpark, MotorType.kBrushless); //construct the rackGearTalon, which controls the ascent and descent of the climber wheels
    TalonSRX climberWheelsTalon = new TalonSRX(RobotMap.climberWheelsTalon); //construct the climberWheelsTalon, which controls the motor which powers the climber wheels

    /**
     * The constructor. This initializes various default settings for the motor controllers.
     */
    public Climber() {
    }

    /**
     * Sets the default command at initialization.
     */
    @Override
     public void initDefaultCommand() {
       setDefaultCommand(new ClimberJoystickControl());
    }

    /**
     * Sets the percent output of the skiTalon.
     * @param value is the percent output to run the motor at, between 0 and 1.
     */
    public void setSkiTalon(double value) {
      skiTalon.set(ControlMode.PercentOutput, value);
    }
  
    /**
     * Sets the percent output of the rackGearSpark.
     */
    public void setRackGearSpark(double value) {
      rackGearSpark.set(value);
    }
  
    /**
     * Sets the percent output of the climberWheelsTalon.
     */
    public void setClimberWheelsTalon(double value){
      climberWheelsTalon.set(ControlMode.PercentOutput, value);
    }

    /**
     * Sets the skiTalon's neutral state as break.
    */
    public void setSkiTalonEnableBrakeMode() {
      skiTalon.setNeutralMode(NeutralMode.Brake);
    }
    /**
     * Sets the rackGearSpark's neutral state as break.
    */

    public void setRackGearSparkEnableBrakeMode(){
      rackGearSpark.setNeutralMode(NeutralMode.Brake);
    }
    /**
     * Sets the climberWheelsTalon's neutral state as break.
    */
    public void setClimberWheelsTalonEnableBrakeMode(){
      climberWheelsTalon.setNeutralMode(NeutralMode.Brake);
    }

    /**
     * Sets the skiTalon's neutral state as coast.
     */
    public void setSkiTalonDisableBrakeMode() {
      skiTalon.setNeutralMode(NeutralMode.Coast);
    }
  
    /**
     * Sets the rackGearSpark's neutral state as coast.
    */
    public void setRackGearSparkDisableBrakeMode(){
      rackGearSpark.setNeutralMode(NeutralMode.Coast);
    }
  
    /**
     * Sets the climberWheelsTalon's neutral state as coast.
    */
    public void setClimberWheelsTalonDisableBrakeMode(){
      climberWheelsTalon.setNeutralMode(NeutralMode.Coast);
    }
}
