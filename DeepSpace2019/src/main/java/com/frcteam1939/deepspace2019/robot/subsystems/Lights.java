/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.deepspace2019.robot.subsystems;

import com.frcteam1939.deepspace2019.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * This class controlls the lights connected to an Arduino through the Roborios
 *  Digital Output ports. The Arduino converts the true or false signals into the correct light coloring on the NeoPixels
 */

 /**
  * 1)    Robot disabled – rainbow pattern - TTTT
    2)    Center grab retracted – solid green - TTTF
    3)    Center grab fired – solid red - TTFT
    4)    Sucking in cargo (absolute value of motor output on roller greater than 0) - orange blinking - TFTT
    5)    Has cargo (both banner sensors tripped) – solid orange - FTTT
    6)    Auto hatch panel button clicked - yellow blinking - TTFF
    7)    Has hatch panel (buttons/limit switches clicked) – solid yellow - TFFT
    8)    Is climbing (motor output of anything on the climber greater than 0) – solid purple - FFTT
    9)    Ascending with hatch panel (either four bar or elevator lifting) – yellow ascending - FTTF
    10)    Descending with hatch panel (either four bar or elevator lowering) – yellow descending - TFTF
    11)    Ascending with cargo (either four bar or elevator lifting) – orange ascending - FTFT
    12)    Descending with cargo (either four bar or elevator lowering) – orange descending - TFFF
    13)    Vision tracking mode enabled – solid blue - FTFF
    14)    Vision can see a target – blinking blue - FFTF
    15)    Robot is lined up with vision target within a 5 degree error - ? - FFFT
  */
  
public class Lights extends Subsystem {
  private DigitalOutput light1 = new DigitalOutput(RobotMap.light4);
  private DigitalOutput light2 = new DigitalOutput(RobotMap.light7);
  private DigitalOutput light3 = new DigitalOutput(RobotMap.light8);
  private DigitalOutput light4 = new DigitalOutput(RobotMap.light9);
 
  public void rainbow(){
    light1.set(true);
    light2.set(true);
    light3.set(true);
    light4.set(true);
  }
  public void solidGreen(){
    light1.set(true);
    light2.set(true);
    light3.set(true);
    light4.set(false);
  }
  public void solidRed(){
    light1.set(true);
    light2.set(true);
    light3.set(false);
    light4.set(true);
  }
  public void orangeBlink(){
    light1.set(true);
    light2.set(false);
    light3.set(true);
    light4.set(true);
  }
  public void solidOrange(){
    light1.set(false);
    light2.set(true);
    light3.set(true);
    light4.set(true);
  }
  public void yellowBlink(){
    light1.set(true);
    light2.set(true);
    light3.set(false);
    light4.set(false);
  }
  public void solidYellow(){
    light1.set(true);
    light2.set(false);
    light3.set(false);
    light4.set(true);
  }
  public void solidPurple(){
    light1.set(false);
    light2.set(false);
    light3.set(true);
    light4.set(true);
  }
  public void yellowUp(){
    light1.set(false);
    light2.set(true);
    light3.set(true);
    light4.set(false);
  }
  public void yellowDown(){
    light1.set(true);
    light2.set(false);
    light3.set(true);
    light4.set(false);
  }
  public void orangeUp(){
    light1.set(false);
    light2.set(true);
    light3.set(false);
    light4.set(true);
  }
  public void orangeDown(){
    light1.set(true);
    light2.set(false);
    light3.set(false);
    light4.set(false);
  }
  public void solidBlue(){
    light1.set(false);
    light2.set(true);
    light3.set(false);
    light4.set(false);
  }
  public void blueBlink(){
    light1.set(false);
    light2.set(false);
    light3.set(true);
    light4.set(false);
  }
  public void blueUp(){
    light1.set(false);
    light2.set(false);
    light3.set(false);
    light4.set(true);
  }
  //One more slot open.


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
