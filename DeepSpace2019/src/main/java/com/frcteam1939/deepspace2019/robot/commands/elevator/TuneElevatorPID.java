/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.elevator;

import com.frcteam1939.deepspace2019.robot.Robot;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TuneElevatorPID extends Command {
  public TuneElevatorPID() {
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
 
    SmartDashboard.putNumber("P Gain", Robot.elevator.P);
    SmartDashboard.putNumber("I Gain", Robot.elevator.I);
    SmartDashboard.putNumber("D Gain", Robot.elevator.D);
    SmartDashboard.putNumber("I Zone", Robot.elevator.izone);
    SmartDashboard.putNumber("Feed Forward", Robot.elevator.F);
    SmartDashboard.putNumber("Max Output", Robot.elevator.MAX_OUTPUT);
    SmartDashboard.putNumber("Min Output", Robot.elevator.MIN_OUTPUT);

    // display Smart Motion coefficients
    SmartDashboard.putNumber("Max Velocity", Robot.elevator.MAX_VEL);
    SmartDashboard.putNumber("Min Velocity", Robot.elevator.MIN_VEL);
    SmartDashboard.putNumber("Max Acceleration", Robot.elevator.MAX_ACCL);
    SmartDashboard.putNumber("Allowed Closed Loop Error", Robot.elevator.ALLOWED_ERR);
    SmartDashboard.putNumber("Set Position", 0);
    SmartDashboard.putNumber("Set Velocity", 0);

    // button to toggle between velocity and smart motion modes
    SmartDashboard.putBoolean("Mode", true);
    
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
    double maxV = SmartDashboard.getNumber("Max Velocity", 0);
    double minV = SmartDashboard.getNumber("Min Velocity", 0);
    double maxA = SmartDashboard.getNumber("Max Acceleration", 0);
    double allE = SmartDashboard.getNumber("Allowed Closed Loop Error", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != Robot.elevator.P)) { Robot.elevator.sparkPID.setP(p); Robot.elevator.P = p; }
    if((i != Robot.elevator.I)) { Robot.elevator.sparkPID.setI(i); Robot.elevator.I = i; }
    if((d != Robot.elevator.D)) { Robot.elevator.sparkPID.setD(d); Robot.elevator.D = d; }
    if((iz != Robot.elevator.izone)) { Robot.elevator.sparkPID.setIZone(iz); Robot.elevator.izone = iz; }
    if((ff != Robot.elevator.F)) { Robot.elevator.sparkPID.setFF(ff); Robot.elevator.F = ff; }
    if((max != Robot.elevator.MAX_OUTPUT) || (min != Robot.elevator.MIN_OUTPUT)) { 
      Robot.elevator.sparkPID.setOutputRange(min, max); 
      Robot.elevator.MIN_OUTPUT = min; Robot.elevator.MAX_OUTPUT = max; 
    }
    if((maxV != Robot.elevator.MAX_VEL)) { Robot.elevator.sparkPID.setSmartMotionMaxVelocity(maxV,0); Robot.elevator.MAX_VEL = maxV; }
    if((minV != Robot.elevator.MIN_VEL)) { Robot.elevator.sparkPID.setSmartMotionMaxVelocity(minV,0); Robot.elevator.MIN_VEL = minV; }
    if((maxA != Robot.elevator.MAX_ACCL)) { Robot.elevator.sparkPID.setSmartMotionMaxAccel(maxA,0); Robot.elevator.MAX_ACCL = maxA; }
    if((allE != Robot.elevator.ALLOWED_ERR)) { Robot.elevator.sparkPID.setSmartMotionAllowedClosedLoopError(allE,0); allE = Robot.elevator.ALLOWED_ERR; }

    double setPoint, processVariable;
    boolean mode = SmartDashboard.getBoolean("Mode", false);
    if(mode) {
      setPoint = SmartDashboard.getNumber("Set Velocity", 0 );
      Robot.elevator.sparkPID.setReference(setPoint, ControlType.kVelocity);
      processVariable = Robot.elevator.sparkEncoder.getVelocity();
    } else {
      setPoint = SmartDashboard.getNumber("Set Position", 0);
      /**
       * As with other PID modes, Smart Motion is set by calling the
       * setReference method on an existing pid object and setting
       * the control type to kSmartMotion
       */
      Robot.elevator.sparkPID.setReference(setPoint, ControlType.kSmartMotion);
      processVariable = Robot.elevator.sparkEncoder.getPosition();
    }
    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
