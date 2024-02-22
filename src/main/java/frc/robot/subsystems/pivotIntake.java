// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class pivotIntake extends SubsystemBase {
  /** Creates a new pivotIntake. */
  CANSparkMax motor;
  SparkPIDController controller;
  RelativeEncoder encoder;
  public pivotIntake() {
  motor = new CANSparkMax(Constants.IntakeConstants.pivotIntakeId, MotorType.kBrushless);
  encoder = motor.getEncoder();
  motor.restoreFactoryDefaults();

  controller = motor.getPIDController();

  controller.setP(Constants.IntakeConstants.motorkP);
  controller.setD(Constants.IntakeConstants.motorkD);
  controller.setI(Constants.IntakeConstants.motorkI);
  encoder.getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("PivoteIntake", motor.getMotorTemperature());
  }

}
