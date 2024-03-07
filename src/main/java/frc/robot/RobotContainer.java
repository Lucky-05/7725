package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    SendableChooser <Command> autoChooser;
    //private final AutoCommand auto = new AutoCommand();
    //private final Command autoDrive = auto.Auto0(); 
    /*Subsystems */
    private Intake mIntake = new Intake();
    private Pivot mPivot = new Pivot();
    private Shooter shooter = new Shooter();
    private Indexer indexer = new Indexer();
    private Rack rack = new Rack();
    private Feeder feeder = new Feeder();
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick mechanisms = new Joystick(1);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;
    
    //private final int speedAxis = XboxController.Axis.kRightTrigger.value;

    /* Driver Buttons */
    private final JoystickButton robotCentric =
        new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    private final JoystickButton zeroGyro1 = 
        new JoystickButton(driver, XboxController.Button.kX.value);
    private final JoystickButton zeroGyro2 = 
        new JoystickButton(driver, XboxController.Button.kA.value);
    private final JoystickButton resetWheels = 
        new JoystickButton(driver, XboxController.Button.kB.value);
    
        /*Mechanisms */
              private final JoystickButton aim = 
        new JoystickButton(mechanisms, XboxController.Button.kA.value);
        private final JoystickButton rackUp = new JoystickButton(mechanisms, XboxController.Button.kRightBumper.value);
        private final JoystickButton rackDown = new JoystickButton(mechanisms, XboxController.Button.kLeftBumper.value);
        private final JoystickButton intakeButton = new JoystickButton(mechanisms,  XboxController.Button.kB.value);
        private final JoystickButton spitIntakeButton = new JoystickButton(mechanisms,  XboxController.Button.kY.value);

        private final JoystickButton intakeDown = new JoystickButton(mechanisms, XboxController.Button.kRightStick.value);
        private final JoystickButton intakeUp = new JoystickButton(mechanisms, XboxController.Button.kLeftStick.value);


        

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();

    
    private SendableChooser<Command> m_chooser = new SendableChooser<>();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        //m_chooser.addOption("Basic Auto", new basicAuto(s_Swerve));
        m_chooser.addOption("Nothing", new InstantCommand());
        SmartDashboard.putData(m_chooser);
        SmartDashboard.putNumber("SpeedLimit", 1);


        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis),
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis),
                () -> robotCentric.getAsBoolean()
            )
        );
        
        
        configureButtonBindings();

        
        autoChooser  = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Mode", autoChooser);
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro1.and(zeroGyro2).onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        resetWheels.onTrue(new InstantCommand(() -> s_Swerve.resetModulesToAbsolute()));
       // intakeIn.whileTrue(new IntakeCommand(mIntake));
        aim.whileTrue(new ShooterCommand(shooter));
        if(shooter.desiredState()){
            new ParallelCommandGroup(new IndexerCommand(indexer), new FeederCommand(feeder));
        }
        
        rackUp.whileTrue(new RackCommand(rack, true, false));
        rackDown.whileTrue(new RackCommand(rack, false, true));
        intakeButton.whileTrue(new IntakeCommand(mIntake,0.7));
        spitIntakeButton.whileTrue(new IntakeCommand(mIntake, -0.7));
        intakeDown.whileTrue(new PivotCommand(mPivot,true, false));
        intakeUp.whileTrue(new PivotCommand(mPivot,false, true));

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // Will run in autonomous
        return autoChooser.getSelected();
        
    }
}