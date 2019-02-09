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
import com.frcteam1939.deepspace2019.robot.commands.ClimberJoystickControl;

public class Climber extends Subsystem {
    TalonSRX skiTalon = new TalonSRX(RobotMap.skiTalon); //construct the skiTalon, which controls the skis
    TalonSRX rackGearTalon = new TalonSRX(RobotMap.rackGearTalon); //construct the rackGearTalon, which controls the ascent and descent of the climber wheels
    TalonSRX climberWheelsTalon = new TalonSRX(RobotMap.climberWheelsTalon); //construct the climberWheelsTalon, which controls the motor which powers the climber wheels

    /**
     * The constructor. This initializes various default settings for the motor controllers.
     */
    public Climber() {
        //set the lower limit for forward and backward power output on each talon to 0
        skiTalon.configNominalOutputForward(0);
        rackGearTalon.configNominalOutputForward(0);
        climberWheelsTalon.configNominalOutputForward(0);
        skiTalon.configNominalOutputReverse(0);
        rackGearTalon.configNominalOutputReverse(0);
        climberWheelsTalon.configNominalOutputReverse(0);
        //set peak output for forward and backward power on each talon to +1 for forward and -1 for backward
        skiTalon.configPeakOutputForward(+1);
        rackGearTalon.configPeakOutputForward(+1);
        climberWheelsTalon.configPeakOutputForward(+1);
        skiTalon.configPeakOutputReverse(-1);
        rackGearTalon.configPeakOutputReverse(-1);
        climberWheelsTalon.configPeakOutputReverse(-1);
        //enable voltage compensation on each talon, which adjusts motor output when voltage varies
        skiTalon.enableVoltageCompensation(true);
        rackGearTalon.enableVoltageCompensation(true);
        climberWheelsTalon.enableVoltageCompensation(true);
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
     * Sets the percent output of the rackGeatTalon.
     */
    public void setRackGearTalon(double value) {
      rackGearTalon.set(ControlMode.PercentOutput, value);
    }
    /**
     * Sets the percent output of the climberWheelsTalon.
     */
    public void setClimberWheels(double value){
      climberWheelsTalon.set(ControlMode.PercentOutput, value);
    }

    /**
     * Sets the skiTalon's neutral state as break.
    */
    public void setSkiTalonEnableBrakeMode() {
      skiTalon.setNeutralMode(NeutralMode.Brake);
    }
    /**
     * Sets the rackGearTalon's neutral state as break.
    */
    public void setRackGearTalonEnableBrakeMode(){
      rackGearTalon.setNeutralMode(NeutralMode.Brake);
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
     * Sets the rackGearTalon's neutral state as coast.
    */
    public void setRackGearTalonDisableBrakeMode(){
      rackGearTalon.setNeutralMode(NeutralMode.Coast);
    }
    /**
     * Sets the climberWheelsTalon's neutral state as coast.
    */
    public void setClimberWheelsTalonDisableBrakeMode(){
      climberWheelsTalon.setNeutralMode(NeutralMode.Coast);
    }

}
