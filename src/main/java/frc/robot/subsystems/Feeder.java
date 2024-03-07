// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Feeder extends SubsystemBase {
  /** Creates a new Feeder. */
  CANSparkMax motor; 
  public Feeder() {
    motor = new CANSparkMax(ShooterConstants.feederIdMotor, MotorType.kBrushless);
    motor.restoreFactoryDefaults();
    motor.setInverted(false);
    motor.clearFaults();

  }

  public void feed(){
    motor.set(0.6);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
