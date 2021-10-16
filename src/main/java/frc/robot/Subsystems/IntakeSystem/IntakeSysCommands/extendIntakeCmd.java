// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.IntakeSystem.IntakeSysCommands;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Subsystems.IntakeSystem.IntakeSys;
import frc.robot.Subsystems.IntakeSystem.IntakeSysConstants;
import frc.robot.Utility.LogitechJoystick;

public class extendIntakeCmd implements Command {

  private LogitechJoystick mStick; 

  private IntakeSys mIntakeSys;

  public extendIntakeCmd(LogitechJoystick stick) {

    mStick = stick; 

    mIntakeSys = IntakeSys.getInstance(); 
    
  }

  public Set<Subsystem> getRequirements() {
    HashSet<Subsystem> requirements = new HashSet<Subsystem>();
    requirements.add(mIntakeSys);
    return requirements;
  }


  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {

    boolean isPressed0 = mStick.getRawButtonPressed(IntakeSysConstants.INTAKE_EXTEND_BUTTON);
    
     if(isPressed0) {
        mIntakeSys.ToggleIntake_isExtended();
     }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

}