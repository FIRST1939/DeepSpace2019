/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot;

import com.frcteam1939.deepspace2019.util.Gamepad;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
  public final Joystick left = new Joystick(0);
  public final Joystick right = new Joystick(1);
  public final Gamepad gamepad = new Gamepad(2);
}
