// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Subsystems.DriveSystem.DriveSystem;
import frc.robot.Subsystems.IntakeSystem.IntakeSys;
import frc.robot.Subsystems.PneumaticSys.PneumaticSys;
import frc.robot.Subsystems.ShooterSystem.ShooterSys;
import frc.robot.Subsystems.Vision.VisionLimelight;

public class auto implements Command {

  private DriveSystem mDriveSystem;
  private ShooterSys mShooterSys; 
  private PneumaticSys mPneumaticSys;

  private int autoCount = 0;
  public auto() {

    mDriveSystem = DriveSystem.getInstance();
    mShooterSys = ShooterSys.getInstance(); 
    mPneumaticSys = PneumaticSys.getInstance(); 

  }

  @Override
  public Set<Subsystem> getRequirements() {
    
    HashSet<Subsystem> requirements = new HashSet<Subsystem>();
    requirements.add(mDriveSystem);
    requirements.add(mShooterSys); 
    requirements.add(mPneumaticSys);
    return requirements;
  }


  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {

    if (autoCount == 0 && (ShooterSys.getInstance().getSpeed() == 0)) {

      mShooterSys.RunShooterSys(false , true, false);
    }
    autoCount++; 
    
    if ((autoCount > 400) && (autoCount < 600)) {
      mDriveSystem.arcadeDrive(0.30, 0, 2, false);
    } else {
      mDriveSystem.arcadeDrive(0, 0, 2, false);
    }


    
    if ((autoCount >= 150) && (autoCount < 600)) {

      if (VisionLimelight.getInstance().isInFrame() == 1) {

        mShooterSys.aimShooter(0, true);

        if ((autoCount > 500) && (Math.abs(VisionLimelight.getInstance().getXoffset()) < 2)) {

          mShooterSys.RunShooterSys(true , false, true);
        }
      }
      
    }
 
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }



}
