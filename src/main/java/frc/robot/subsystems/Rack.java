// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.util.LimelightHelpers;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.ShooterConstants.RackConstants;

public class Rack extends SubsystemBase {
  /** Creates a new Rack. */
  CANSparkMax motor = new CANSparkMax(ShooterConstants.RackConstants.rackMotorID, MotorType.kBrushless);
  SparkPIDController controller;
  public Rack() {

    motor.restoreFactoryDefaults();
    motor.setInverted(false);
    motor.setIdleMode(IdleMode.kBrake);
    controller = motor.getPIDController(); 

    controller.setD(RackConstants.kD);
    controller.setI(RackConstants.kI);
    controller.setP(RackConstants.kP); 
   motor.setSmartCurrentLimit(ShooterConstants.RackConstants.rackCurrentLimit);
    
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

  public void setAngle(double angle){

    double rotations = (angle*RackConstants.rackGearRatio)/RackConstants.anglePerRotation;
    controller.setReference(rotations, ControlType.kPosition);
  }

  public double getAngle(double distanceToTarget){
    //Cambiar m y b
    return 3.5*Math.atan((RackConstants.speakerHeigt-RackConstants.cameraHeight)/distanceToTarget)+3;
  }

  public double getDistance(){
    return (RackConstants.speakerHeigt-RackConstants.cameraHeight)/(Math.tan(LimelightHelpers.getTY("limelight"))*Math.cos(LimelightHelpers.getTX("limelight")));
  }

  
}
