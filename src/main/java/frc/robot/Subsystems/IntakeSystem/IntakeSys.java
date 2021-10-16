// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.IntakeSystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.Subsystem;

/** The intake system is responsible for the motor that drives the intake, the 2 pistons that extend and retract the intake, and the motor that drives the balls int the holding area. */
public class IntakeSys implements Subsystem {

    private WPI_TalonSRX mIntakeMaster; 

    private Solenoid mIntakePiston0; 
    private Solenoid mIntakePiston1;
    // private Compressor mCompressor;

    //private Spark mCereal; 

    private boolean mIntake_isExtended = false;   
    

    private static IntakeSys mIntakeSys; 

    public static IntakeSys getInstance() {

        if (mIntakeSys == null) {

            mIntakeSys = new IntakeSys();
        }

        return mIntakeSys;
    }

    private IntakeSys() {

        mIntakeMaster = new WPI_TalonSRX(IntakeSysConstants.INTAKE_MASTER_MOTOR_NUMBER); 

        mIntakePiston0 = new Solenoid(0,IntakeSysConstants.INTAKE_PISTON_0_PCM_NUM); 
        mIntakePiston1 = new Solenoid(0,IntakeSysConstants.INTAKE_PISTON_1_PCM_NUM); 

        //mCompressor = new Compressor(IntakeSysConstants.COMPRESSOR_MODULE_NUMBER); 

        
        configMotors();
    }

    private void configMotors() {

        mIntakeMaster.setInverted(IntakeSysConstants.INTAKE_MASTER_isInverted);

        //mCereal.setInverted(IntakeSysConstants.CERIAL_MOTOR_isInverted); 
    }


    public void takeInBalls(boolean isPressed) {

        if (isPressed) {

            mIntakeMaster.set(ControlMode.PercentOutput, IntakeSysConstants.INTAKE_MASTER_SPEED_PERCENT);
        } else {

            mIntakeMaster.set(ControlMode.PercentOutput, 0);
        }
    }

    public void makeCereal() {


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
