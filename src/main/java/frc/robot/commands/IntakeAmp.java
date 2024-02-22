// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeAmp extends Command {
  /** Creates a new IntakeAmp. */
private Intake mIntake;
  public IntakeAmp(Intake mIntake) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.mIntake = mIntake;
    addRequirements(mIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mIntake.intakeSpeed(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mIntake.intakeSpeed(-0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mIntake.intakeSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
