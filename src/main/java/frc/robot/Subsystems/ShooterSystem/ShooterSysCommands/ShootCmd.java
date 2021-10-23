// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.ShooterSystem.ShooterSysCommands;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Subsystems.ShooterSystem.ShooterSys;
import frc.robot.Subsystems.ShooterSystem.ShooterSysConstants;
import frc.robot.Utility.LogitechJoystick;

public class ShootCmd implements Command {

  private LogitechJoystick mStick; 

  private static ShooterSys mShooterSys; 

  public ShootCmd(LogitechJoystick stick) {

    mStick = stick; 

    mShooterSys = ShooterSys.getInstance(); 
   
  }

  public Set<Subsystem> getRequirements() {
    HashSet<Subsystem> requirements = new HashSet<Subsystem>();
    requirements.add(mShooterSys);
    return requirements;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {

    boolean runShooter = mStick.getRawButton(ShooterSysConstants.SHOOT_BUTTON); 
    double yaw = mStick.getX();

    mShooterSys.RunShooterSys(runShooter);
    mShooterSys.aimShooter(yaw/2);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

}
