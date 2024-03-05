// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Rack extends SubsystemBase {
  /** Creates a new Rack. */
  CANSparkMax motor = new CANSparkMax(ShooterConstants.rackMotorID, MotorType.kBrushless);
  
  public Rack() {

    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
   
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Rack Motor temperature", motor.getMotorTemperature());
  }

  public void setSpeedP(){
    motor.set(0.1);
  }

  public void setSpeedN(){
    motor.set(-0.1);
  }

  public void setSpeed0(){
    motor.set(0);
  }
}
