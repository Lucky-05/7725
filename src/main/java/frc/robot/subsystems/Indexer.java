// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  /** Creates a new Indexer. */
  CANSparkMax motor;
  public Indexer() {
    motor = new CANSparkMax(Constants.IndexerConstants.motorId, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setIndexerSpeed(boolean isDesired){
    if(isDesired) motor.set(0.25);
    else{
      motor.set(0);
    }
  }

  public void setIndexerSpeed(){
    motor.set(0.25);
  }
  public void setIndexerSpeed0(){
    motor.set(0);
  }
}
