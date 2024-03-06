// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.pathplanner.lib.commands.FollowPathHolonomic;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

public class AutoCommand extends Command {
  /** Creates a new AutoCommand. */
  Swerve swerve = new Swerve();
  public Command DrivePastLine(){
    
    PathPlannerPath path = PathPlannerPath.fromPathFile("Speaker Stage Note");

     return new FollowPathHolonomic(
                path,
                swerve::getPose, // Robot pose supplier
                swerve::getSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
                swerve::driveRobotRelative, // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds
                Constants.Swerve.pathFollowerConfig,
                () -> {
                    // Boolean supplier that controls when the path will be mirrored for the red
                    // alliance
                    // This will flip the path being followed to the red side of the field.
                    // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

                    var alliance = DriverStation.getAlliance();
                    if (alliance.isPresent()) {
                        return alliance.get() == DriverStation.Alliance.Red;
                    }
                    return false;
                },
                swerve // Reference to this subsystem to set requirements
        );
    }

    public Command Auto0(){
      String autoName = "Auto0";
      Pose2d pose =  PathPlannerAuto.getStaringPoseFromAutoFile(autoName);

      swerve.swerveOdometry.resetPosition(swerve.getHeading(), swerve.getModulePositions(), 
      pose);
      
      return new PathPlannerAuto(autoName);
  }

   
  }

