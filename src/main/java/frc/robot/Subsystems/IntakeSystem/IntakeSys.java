// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.IntakeSystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.Subsystem;

/** The intake system is responsible for the motor that drives the intake, the 2 pistons that extend and retract the intake, and the motor that drives the balls int the holding area. */
public class IntakeSys implements Subsystem {

    private WPI_TalonSRX mIntakeMaster;     

    private static IntakeSys mIntakeSys; 

    public static IntakeSys getInstance() {

        if (mIntakeSys == null) {

            mIntakeSys = new IntakeSys();
        }

        return mIntakeSys;
    }

    private IntakeSys() {

        mIntakeMaster = new WPI_TalonSRX(IntakeSysConstants.INTAKE_MASTER_MOTOR_NUMBER); 

        configMotors();
    }

    private void configMotors() {

        mIntakeMaster.setInverted(IntakeSysConstants.INTAKE_MASTER_isInverted);

        mIntakeMaster.getSensorCollection().setQuadraturePosition(0, 1); 
        mIntakeMaster.setSensorPhase(false);
        //mCereal.setInverted(IntakeSysConstants.CERIAL_MOTOR_isInverted); 
    }


    public void runIntake(boolean in, boolean out) {

        if (in) {

            mIntakeMaster.set(ControlMode.PercentOutput, IntakeSysConstants.INTAKE_MASTER_SPEED_PERCENT);

        } else if (out) {
            mIntakeMaster.set(ControlMode.PercentOutput,-1 * IntakeSysConstants.INTAKE_MASTER_SPEED_PERCENT);
        } else {

            mIntakeMaster.set(ControlMode.PercentOutput, 0);
        }
    }

    public double getTicks() {

        return mIntakeMaster.getSensorCollection().getQuadraturePosition(); 
    }

    
}
