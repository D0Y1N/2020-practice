/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	//Subsystems
	private final Drivetrain m_drivetrain = new Drivetrain();
	private final Arm m_arm = new Arm();
	private final Cargo m_cargo = new Cargo();
	
	//Commands
	private final TeleopCargo m_teleopCargo = new TeleopCargo(m_cargo);

	// OI Devices
	private  final XboxController m_driverController = new XboxController(Constants.driveController);
    private final Joystick m_driverLeftJoystick = new Joystick(Constants.driverJoyLeft);
    private final Joystick m_driverRightJoystick = new Joystick(Constants.driverJoyRight);
    private  final XboxController m_operatorController = new XboxController(Constants.operatorController);


	 /**
     * Joystick drive commands
     */
    private final Command m_tankJoystick = new RunCommand(
            () -> m_drivetrain.tankDrive(-m_driverLeftJoystick.getY(), -m_driverRightJoystick.getY()), m_drivetrain);
    private final Command m_arcadeJoystick = new RunCommand(
            () -> m_drivetrain.arcadeDrive(-m_driverRightJoystick.getY(), m_driverRightJoystick.getX()), m_drivetrain);
    private final Command m_splitArcadeJoystick = new RunCommand(
            () -> m_drivetrain.arcadeDrive(-m_driverLeftJoystick.getY(), m_driverRightJoystick.getX()), m_drivetrain);
    /**
     * Controller drive commands
     */
    private final Command m_tankController = new RunCommand(
            () -> m_drivetrain.tankDrive(-m_driverController.getY(Hand.kLeft), -m_driverController.getY(Hand.kRight)),
            m_drivetrain);
    private final Command m_arcadeController = new RunCommand(
            () -> m_drivetrain.arcadeDrive(-m_driverController.getY(Hand.kRight), m_driverController.getX(Hand.kRight)),
            m_drivetrain);
    private final Command m_splitArcadeController = new RunCommand(
            () -> m_drivetrain.arcadeDrive(-m_driverController.getY(Hand.kLeft), m_driverController.getX(Hand.kRight)),
			m_drivetrain);
			
	/**
	 * Teleop Arm and Cargo Commands
	 */
	private final Command m_TeleopArm = new RunCommand(
		() -> m_arm.setWinchSpeed(-m_operatorController.getY(Hand.kLeft)),
		m_arm);

	private final Command m_TeleopCargo = new RunCommand(
		() -> m_cargo.setWheelSpeed(-m_operatorController.getY(Hand.kRight)),
		m_cargo);

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Default Commands
		m_drivetrain.setDefaultCommand(m_splitArcadeJoystick);
		m_arm.setDefaultCommand(m_TeleopArm);
		m_cargo.setDefaultCommand(m_TeleopCargo);

		CameraServer.getInstance().startAutomaticCapture(0);
		CameraServer.getInstance().startAutomaticCapture(1);
		
		configureButtonBindings();
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		/**
         * Buttons to trigger driving with joysticks
         */
        new JoystickButton(m_driverRightJoystick, 5).whenPressed(m_tankJoystick);
        new JoystickButton(m_driverRightJoystick, 6).whenPressed(m_arcadeJoystick);
        new JoystickButton(m_driverRightJoystick, 4).whenPressed(m_splitArcadeJoystick);
        /**
         * Buttons to trigger driving with Xbox controller
         */
        new JoystickButton(m_driverController, XboxController.Button.kX.value).whenPressed(m_tankController);
        new JoystickButton(m_driverController, XboxController.Button.kY.value).whenPressed(m_arcadeController);
        new JoystickButton(m_driverController, XboxController.Button.kB.value).whenPressed(m_splitArcadeController);
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous
		return null;
	}
}
