/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.subsystems; //package declaration

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem; //import the Subsystem template class
import com.ctre.phoenix.motorcontrol.ControlMode; //imports Control Mode for the motor controllers
import com.ctre.phoenix.motorcontrol.NeutralMode; //imports the NeutralMode for the motor controllers
import com.ctre.phoenix.motorcontrol.can.TalonSRX; //import the TalonSRX Class
import com.frcteam1939.deepspace2019.robot.Robot;
import com.frcteam1939.deepspace2019.robot.RobotMap;//import robotmap file for motor controller ids
import com.frcteam1939.deepspace2019.robot.commands.climber.ClimberJoystickControl;

public class Climber extends Subsystem {

    TalonSRX climberWheelsTalon = new TalonSRX(RobotMap.climberWheelsTalon); //construct the climberWheelsTalon, which controls the motor which powers the climber wheels

    private DoubleSolenoid backClimberSolenoid = new DoubleSolenoid(RobotMap.climberDownSolenoid, RobotMap.climberUpSolenoid);
    private DoubleSolenoid frontClimberSolenoid = new DoubleSolenoid(RobotMap.frontClimberUpSolenoid, RobotMap.frontClimberDownSolenoid);
    /**
     * The constructor. This initializes various default settings for the motor controllers.
     */
    public Climber() {
      climberWheelsTalon.setInverted(true);
    }

    /**
     * Sets the default command at initialization.
     */
    @Override
     public void initDefaultCommand() {
       setDefaultCommand(new ClimberJoystickControl());
    }

    /**
     * Sets the percent output of the climberWheelsTalon.
     */
    public void setClimberWheelsTalon(double value){
      climberWheelsTalon.set(ControlMode.PercentOutput, value);
    }


    /**
     * Sets the climberWheelsTalon's neutral state as break.
    */
    public void setClimberWheelsTalonEnableBrakeMode(){
      climberWheelsTalon.setNeutralMode(NeutralMode.Brake);
    }
  
    /**
     * Sets the climberWheelsTalon's neutral state as coast.
    */
    public void setClimberWheelsTalonDisableBrakeMode(){
      climberWheelsTalon.setNeutralMode(NeutralMode.Coast);
    }

    public void frontClimberDown(){
      frontClimberSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void frontClimberUp(){
      frontClimberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void backClimberDown(){
      backClimberSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void backClimberUp(){
      backClimberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}
