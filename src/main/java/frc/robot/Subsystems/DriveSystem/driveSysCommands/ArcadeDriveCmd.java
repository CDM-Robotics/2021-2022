// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.DriveSystem.driveSysCommands;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Subsystems.DriveSystem.DriveSystem;
import frc.robot.Utility.LogitechJoystick;

public class ArcadeDriveCmd implements Command {

  private LogitechJoystick mStick; 
  
  private DriveSystem mDriveSystem;

  /**
   * Specify the the command requires the DriveSys subsystem
   */
  public ArcadeDriveCmd(LogitechJoystick stick) {
      // requires(DriveSys.getInstance());
      mStick = stick;
      mDriveSystem = DriveSystem.getInstance();
  }

  public Set<Subsystem> getRequirements() {

    HashSet<Subsystem> requirements = new HashSet<Subsystem>();
    requirements.add(mDriveSystem);
    return requirements;
  }
      
  
   int count = 0; 
  /**
   * Execute is called by the scheduler until the command returns finished or the
   * OI stops requesting - for example if the whileHeld() button command is used
   */
  public void execute() {
      double mag = mStick.getInvertedY();
      double yaw = mStick.getX();
      double maxSpeed = 1 - mStick.getThrottle(); 

      if (count % 50 == 0) {

        System.out.println(maxSpeed);
      }
     

      // mag = -mag;// y comes out from stick as negative when going forward, so convert
      // reduce sensitivity on turn
      // mag = mag * 0.55;

      //mLog.periodicDebug(20, "Y", mag, "X", yaw);
      mDriveSystem.arcadeDrive(mag, yaw, maxSpeed);

      
  }

  /**
   * @return Return true when command is completed
   */
  @Override
  public boolean isFinished() {
      return false;
  }

}