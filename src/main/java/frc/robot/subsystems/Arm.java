/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

 /* public static final double floorLevel = 1.0;
  public static final double upLevel = 3.0;
  public static final double rocketLevel1 = 2.0;  */

  private Talon motor = new Talon(Constants.armWinch);
  private AnalogInput stringPot = new AnalogInput(Constants.stringPot);

  public Arm() {

  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("String Pot", stringPot.getVoltage() );
  } 

  public void setWinchSpeed(double speed) {
    motor.setSpeed(speed);
  }

}
