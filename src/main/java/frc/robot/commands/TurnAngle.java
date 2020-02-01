/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GyroPigeon;

public class TurnAngle extends CommandBase {

  private Drivetrain m_drivetrain;
  private GyroPigeon m_pigeon;
  private double m_requestedAngle;

  /**
   * Creates a new TurnAngle.
   */
  public TurnAngle(Drivetrain drivetrain, GyroPigeon pigeon, double angle) {
    m_drivetrain = drivetrain;
    m_pigeon = pigeon;
    m_requestedAngle = angle;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_pigeon.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_requestedAngle>1) {
        //this will turn the robot right     
        m_drivetrain.tankDrive(0.5, -0.5, false);   
    }
    else if(m_requestedAngle<-1) {
        //this will turn the robot left
        m_drivetrain.tankDrive(-0.5, 0.5, false);    
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      if(m_requestedAngle>1) {
        return (m_pigeon.getAngle() > m_requestedAngle);
      }
      else if(m_requestedAngle<-1){
        return (m_pigeon.getAngle() < m_requestedAngle);
      }
      else {
          return true;
      }
  }
}