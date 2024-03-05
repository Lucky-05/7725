package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder.Type;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.*;
public class Pivot extends SubsystemBase {
    //First we declare the master motors which are the front motors
    private final CANSparkMax motor;
    
    //Define your position setpoints
    //Min and Max velocity
    private static final double minOutput = -0.75;
    private static final double maxOutput = 0.75;
    //TargetAngle and target position represent the current objective of the subsytem.

    public Pivot() {
        //Set Motors
        motor = new CANSparkMax(Constants.IntakeConstants.pivotIntakeId, CANSparkMax.MotorType.kBrushless);
        motor.setInverted(false);
        //Create Settings
        //Pid Settings.
        motor.getPIDController().setP(Constants.IntakeConstants.motorkP);
        motor.getPIDController().setI(Constants.IntakeConstants.motorkI);
        motor.getPIDController().setD(Constants.IntakeConstants.motorkD);
        motor.setSmartCurrentLimit(30);
        // Set the output range of the PID controller
        motor.getPIDController().setOutputRange(minOutput, maxOutput);
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