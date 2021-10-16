// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.PneumaticSys;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Subsystem;

/** The intake system is responsible for the motor that drives the intake, the 2 pistons that extend and retract the intake, and the motor that drives the balls int the holding area. */
public class PneumaticSys implements Subsystem {

    private Solenoid mIntakePiston0; 
    private Solenoid mIntakePiston1;
    // private Compressor mCompressor;

    //private Spark mCereal; 

    private boolean mIntake_isExtended = false;   
    

    private static PneumaticSys mPneumaticSys; 

    public static PneumaticSys getInstance() {

        if (mPneumaticSys == null) {

            mPneumaticSys = new PneumaticSys();
        }

        return mPneumaticSys;
    }

    private PneumaticSys() {
        mIntakePiston0 = new Solenoid(0,PneumaticSysConstants.INTAKE_PISTON_0_PCM_NUM); 
        mIntakePiston1 = new Solenoid(0,PneumaticSysConstants.INTAKE_PISTON_1_PCM_NUM); 

        //mCompressor = new Compressor(IntakeSysConstants.COMPRESSOR_MODULE_NUMBER); 
    }


    public void ToggleIntake_isExtended() {

        if (mIntake_isExtended) {
            mIntake_isExtended = false; 
            mIntakePiston0.set(false);
            mIntakePiston1.set(true);
        }else{
            mIntakePiston0.set(true);
            mIntakePiston1.set(false);
            mIntake_isExtended = true; 
        }

    }
    
}
