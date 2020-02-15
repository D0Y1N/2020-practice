/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.*;
import frc.robot.Constants;

public class DSolenoid extends SubsystemBase {

    DoubleSolenoid doubleSolenoid = new DoubleSolenoid(Constants.doubleSolenoidA, Constants.doubleSolenoidB);

    public DSolenoid() {

    }

    public void switchDS() {
        if (doubleSolenoid.get() != DoubleSolenoid.Value.kReverse) {
          doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
        else if(doubleSolenoid.get() != DoubleSolenoid.Value.kForward) {
            doubleSolenoid.set(DoubleSolenoid.Value.kForward);
          }
      }
}