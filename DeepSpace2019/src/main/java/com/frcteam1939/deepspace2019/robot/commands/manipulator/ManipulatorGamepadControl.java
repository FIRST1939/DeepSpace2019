/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.manipulator;

import com.frcteam1939.deepspace2019.robot.Robot;
import com.frcteam1939.deepspace2019.robot.subsystems.Manipulator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManipulatorGamepadControl extends Command {

  private boolean manipulatorIsDown = false;
	private boolean manipulatorIsUp = true;
  private boolean wasPressed = false;
  
  public ManipulatorGamepadControl() {
    requires(Robot.manipulator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {

    Robot.oi.gamepad.rightTrigger.whenPressed(new IntakeCargo());
    Robot.oi.gamepad.leftTrigger.whenPressed(new IntakeHatchPanel());
    Robot.oi.gamepad.leftButton.whenPressed(new OutputHatchPanel());

    if (Robot.oi.gamepad.rightButton.get()){
      Robot.manipulator.setRoller(Manipulator.OUT_SPEED);
    }
    else {
      Robot.manipulator.setRoller(0);
    }

    if (Robot.oi.gamepad.x.get() && !wasPressed) {
			wasPressed = true;
			if (manipulatorIsUp) {
				Robot.manipulator.manipulatorLower();
				manipulatorIsDown = true;
				manipulatorIsUp = false;
			}
			else if (manipulatorIsDown) {
				Robot.manipulator.manipulatorRaise();
				manipulatorIsUp = true;
				manipulatorIsDown = false;
			}
		}

		if (!Robot.oi.gamepad.x.get()) {
			wasPressed = false;
		}
		else {
			wasPressed = true;
		}

		SmartDashboard.putBoolean("Manipulator is Raised", manipulatorIsUp);
		SmartDashboard.putBoolean("Manipulator is Lowered", manipulatorIsDown);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
