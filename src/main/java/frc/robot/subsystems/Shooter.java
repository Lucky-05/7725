// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.util.LimelightHelpers;
import frc.robot.Constants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.ShooterConstants.RackConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase;
public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  CANSparkMax motor0;
  CANSparkMax motor1;
  double setPoint;
  
  SparkPIDController motor0Controller;
   SparkPIDController motor1Controller;
  public Shooter() {
  motor0 = new CANSparkMax(Constants.ShooterConstants.motor0Id, MotorType.kBrushless);    
  motor1 = new CANSparkMax(Constants.ShooterConstants.motor1Id, MotorType.kBrushless);
    
  motor0.setIdleMode(CANSparkBase.IdleMode.kCoast);
  motor1.setIdleMode(CANSparkBase.IdleMode.kCoast);

  motor0.restoreFactoryDefaults();
  motor1.restoreFactoryDefaults();
  //motor0.setSmartCurrentLimit(ShooterConstants.currentLimit);
  //motor1.setSmartCurrentLimit(ShooterConstants.currentLimit);
  
  motor0.setIdleMode(CANSparkBase.IdleMode.kCoast);
  motor1.setIdleMode(CANSparkBase.IdleMode.kCoast);

  motor0.restoreFactoryDefaults();
  motor1.restoreFactoryDefaults();

  motor0.setSmartCurrentLimit(ShooterConstants.currentLimit);
  motor1.setSmartCurrentLimit(ShooterConstants.currentLimit);
  
  motor0Controller = motor0.getPIDController();

  motor0Controller.setP(Constants.ShooterConstants.motor0kP);
  motor0Controller.setD(Constants.ShooterConstants.motor0kD);
  motor0Controller.setI(Constants.ShooterConstants.motor0kI);

  
  motor1Controller = motor1.getPIDController();
  motor1Controller.setP(Constants.ShooterConstants.motor1kP);
  motor1Controller.setD(Constants.ShooterConstants.motor1kD);
  motor1Controller.setI(Constants.ShooterConstants.motor1kI);


  
  
  
 // SparkPIDController controller = new SparkPIDController(motor0);
  }
  public void setSpeed1(){
    motor0.set(-0.75);
    motor1.set(-0.75);
  }

  public void setSpeed2(){
    motor0.set(-0.5);
    motor1.set(-0.5);
  }

  public void setSpeed3(){
    motor0.set(-0.85);
    motor1.set(-0.85);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Motor0 Temperature", motor0.getMotorTemperature());
    SmartDashboard.putNumber("Motor0 Temperature", motor1.getMotorTemperature());
    SmartDashboard.putNumber("Shooter Velocity", getMotorVelocity());
  }

  public void desiredRpm(double distance){

    //tune equation for angle and velocity
   double setPoint = distance*1000+1100;
    this.setPoint = setPoint;
    motor0Controller.setReference(setPoint, ControlType.kVelocity);
  }

  public void setSpeed0(){
    motor0.set(0);
    motor1.set(0);
  }
  
  public double getMotorVelocity(){
    return motor0.getEncoder().getVelocity();
  }
  
  
  public boolean desiredState(){
    boolean result = false; 

    if(setPoint*ShooterConstants.threshold < getMotorVelocity() || setPoint*1.05>getMotorVelocity()){
      result = true;
    }
    return result;
  }

  public double getDistance(){
    return (RackConstants.speakerHeigt-RackConstants.cameraHeight)/(Math.tan(LimelightHelpers.getTY("limelight"))*Math.cos(LimelightHelpers.getTX("limelight")));
  }
}
