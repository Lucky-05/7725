package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.*;
import frc.robot.Constants.IntakeConstants;
public class Intake extends SubsystemBase {
    //First we declare the master motors which are the front motors
    private final CANSparkMax motor;
    //Min and Max velocity
    private static final double minOutput = -0.5;
    private static final double maxOutput = 0.5;
    
    public Intake() {
        //Set Motors
        motor = new CANSparkMax(Constants.IntakeConstants.motorId, CANSparkMax.MotorType.kBrushless);
        motor.setInverted(false);
        motor.setSmartCurrentLimit(IntakeConstants.intakeCurrentLimit);
        
      
      
        // Set the output range of the PID controller
        motor.getPIDController().setOutputRange(minOutput, maxOutput);
    }

    public void intakeSpeed(double speed){
        motor.set(speed);
        SmartDashboard.putNumber("Temperature:", motor.getMotorTemperature());
    }

     @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Intake Motor temperature", motor.getMotorTemperature());
  }

   
}