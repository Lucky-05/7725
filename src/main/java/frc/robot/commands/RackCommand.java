// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Rack;

public class RackCommand extends Command {
  /** Creates a new RackCommand. */
  Rack rack;
  boolean rb;
  boolean rl;
  
  public RackCommand(Rack rack, boolean rl, boolean rb) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.rb = rb; 
    this.rl = rl; 
    this.rack = rack;
    addRequirements(rack);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(rb == true){
    rack.setSpeedP();
    }
    else if (rl == true){
      rack.setSpeedN();
    }
    
   // rack.setAngle(rack.getAngle(rack.getDistance()));
    
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    rack.setSpeed0();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
