// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Pivot;

public class PivotCommand extends Command {
  /** Creates a new IntakeCommand. */
  Pivot pivot;
  boolean right; 
  boolean left;
  public PivotCommand(Pivot pivot,  boolean right, boolean left) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.right = right; 
    this.left = left;
    this.pivot = pivot;
    addRequirements(pivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(right)
    pivot.setSpeedP();
    else if(left){
    pivot.setSpeedN();
    }
  }
    

  @Override
  public void end(boolean interrupted) {
    pivot.setSpeed0();
  }

 

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
