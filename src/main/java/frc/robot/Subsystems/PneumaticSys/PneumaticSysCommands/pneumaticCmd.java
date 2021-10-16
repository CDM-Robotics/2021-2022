// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.PneumaticSys.PneumaticSysCommands;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.PneumaticSys.PneumaticSys;
import frc.robot.Subsystems.PneumaticSys.PneumaticSysConstants;
import frc.robot.Utility.LogitechJoystick;

/** Add your docs here. */
public class pneumaticCmd implements Command {

  private LogitechJoystick mStick;

  private PneumaticSys mPneumaticSys;

  public pneumaticCmd(LogitechJoystick stick) {

    mStick = stick; 

    mPneumaticSys = PneumaticSys.getInstance();  
    
  }

  public Set<Subsystem> getRequirements() {
    HashSet<Subsystem> requirements = new HashSet<Subsystem>();
    requirements.add(mPneumaticSys);
    return requirements;
  }


  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    
    //boolean isPressed = mStick.getRawButton(IntakeSysConstants.INTAKE_RUN_BUTTON);
    //boolean out_isPressed = mStick.getRawButton(IntakeSysConstants.INTAKE_EXPEL_BUTTON);

    if(mStick.getRawButtonPressed(PneumaticSysConstants.INTAKE_EXTEND_BUTTON)) {

      mPneumaticSys.ToggleIntake_isExtended();
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }



}
