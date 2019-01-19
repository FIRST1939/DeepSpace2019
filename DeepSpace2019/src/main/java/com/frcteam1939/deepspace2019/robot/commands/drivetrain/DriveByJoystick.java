/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.commands.drivetrain;

import com.frcteam1939.deepspace2019.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByJoystick extends Command {
  private static double DEAD_BAND = 0.1;
  private static double ROTATE_DEAD_BAND = 0.15;

	public DriveByJoystick() {
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {
  }

	@Override
	protected void execute() {

		double move = Robot.oi.left.getY();
		double rotate = Robot.oi.right.getX();
		double strafe = Robot.oi.left.getX();

		boolean turbo = Robot.oi.left.getRawButton(1) || Robot.oi.right.getRawButton(1);

		if (Math.abs(move) < DEAD_BAND) {
			move = 0;
		} else {
			if (turbo) {
				move = map(move, 0, 1.0);
			} else {
				move = map(move, 0, 0.5);
			}
		}
		if (Math.abs(rotate) < ROTATE_DEAD_BAND) {
			rotate = 0;
		} else {
			if (turbo) {
				rotate = map(rotate, 0, 0.8);
			} else {
				rotate = map(rotate, 0, 0.4);
			}
		}
		if (Math.abs(strafe) < 0.5) {
			Robot.drivetrain.sidewinderUp();
			strafe = 0;
		} else {
			Robot.drivetrain.sidewinderDown();
			if (turbo) {
				strafe = map(strafe, 0.5, 1.0, 0.3, 1.0);
			} else {
				strafe = map(strafe, 0.5, 1.0, 0.3, 0.7);
			}
		}

		Robot.drivetrain.drive(move, rotate, strafe);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.stop();
	}

	private static double map(double x, double out_min, double out_max) {
		return map(x, DEAD_BAND, 1.0, out_min, out_max);
	}

	private static double map(double x, double in_min, double in_max, double out_min, double out_max) {
		boolean negative = x < 0;
		double newValue = (Math.abs(x) - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
		if (negative) {
			return -newValue;
		} else {
			return newValue;
		}
	}

}
