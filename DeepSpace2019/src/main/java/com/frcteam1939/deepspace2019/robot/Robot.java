/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot;

import com.frcteam1939.deepspace2019.robot.subsystems.Climber;
import com.frcteam1939.deepspace2019.robot.commands.elevator.TuneElevatorPID;
import com.frcteam1939.deepspace2019.robot.subsystems.Arm;
import com.frcteam1939.deepspace2019.robot.subsystems.Lights;

import com.frcteam1939.deepspace2019.robot.subsystems.Drivetrain;
import com.frcteam1939.deepspace2019.robot.subsystems.Manipulator;
import com.frcteam1939.deepspace2019.robot.subsystems.Elevator;
import com.frcteam1939.deepspace2019.robot.subsystems.SmartDashboardSubsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  public static Drivetrain drivetrain;
  public static Elevator elevator;
  public static SmartDashboardSubsystem smartDashboard;
  public static Climber climber;
  public static Arm arm;
  public static Lights lights;
  public static Manipulator manipulator;

	static {
		try {
      drivetrain = new Drivetrain();
      elevator = new Elevator();
      smartDashboard = new SmartDashboardSubsystem();
      climber = new Climber();
      arm = new Arm();
      lights = new Lights();
      manipulator = new Manipulator();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

  public static OI oi = new OI();
  private static AnalogInput pressureSensor = new AnalogInput(RobotMap.pressureSensor);

  Command autonomousCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();

  @Override
  public void robotInit() {

    System.out.println("\n==========================================");
    System.out.println("Destination: Deep Space 2019 Intializing");

    // chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Autonomous Chooser", chooser);
    SmartDashboard.putData("Elevator PID", new TuneElevatorPID());

    UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
    cam.setResolution(180, 120);
    cam.setBrightness(10);
    
    System.out.println("           Finished Intializing");
		System.out.println("==========================================/n");
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
    Robot.drivetrain.disableBrakeMode();
    Robot.elevator.disableBrakeMode();
    Robot.arm.enableBrakeMode();
    Robot.manipulator.enableBrakeMode();
    Robot.lights.rainbow();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    Robot.lights.rainbow();
  }

  @Override
  public void autonomousInit() {
    autonomousCommand = chooser.getSelected();

    if (autonomousCommand != null) {
      autonomousCommand.start();
    }

    Robot.manipulator.manipulatorLower();
    Robot.manipulator.deployCenterGrab();
    Robot.manipulator.centerGrabDeployed = true;
    Robot.manipulator.centerGrabIn = false;

    Robot.drivetrain.enableBrakeMode();
    Robot.elevator.enableBrakeMode();
    Robot.arm.enableBrakeMode();
    Robot.manipulator.enableBrakeMode();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }

    Robot.drivetrain.enableBrakeMode();
    Robot.elevator.enableBrakeMode();
    Robot.arm.enableBrakeMode();
    Robot.manipulator.enableBrakeMode();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }

  public static double getPressure() {
		return 250.0 * (pressureSensor.getVoltage() / 5.0) - 25.0;
	}
}
