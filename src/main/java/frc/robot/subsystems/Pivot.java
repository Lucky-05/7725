package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.*;
import frc.robot.Constants.IntakeConstants;
public class Pivot extends SubsystemBase {
    //First we declare the master motors which are the front motors
    private final CANSparkMax motor;
    
    //Define your position setpoints
    //Min and Max velocity
    private static final double minOutput = -0.75;
    private static final double maxOutput = 0.75;
    private SparkPIDController controller;
    //TargetAngle and target position represent the current objective of the subsytem.

    public Pivot() {
        //Set Motors
        motor = new CANSparkMax(Constants.IntakeConstants.PivotConstants.pivotIntakeId, CANSparkMax.MotorType.kBrushless);
        motor.setInverted(false);
        controller = motor.getPIDController();
        //Create Settings
        //Pid Settings.
        controller.setP(Constants.IntakeConstants.PivotConstants.motorkP);
        controller.setI(Constants.IntakeConstants.PivotConstants.motorkI);
        controller.setD(Constants.IntakeConstants.PivotConstants.motorkD);
        motor.setSmartCurrentLimit(IntakeConstants.PivotConstants.pivotCurrentLimit);
        // Set the output range of the PID controller
        controller.setOutputRange(minOutput, maxOutput);
       // controller.setReference(maxOutput, null)
    }

    public void setSpeedP(){
        motor.set(0.3);
    }
    public void setSpeedN(){
        motor.set(-0.3);
    }

     public void setSpeed0(){
        motor.set(0);
    }

   }