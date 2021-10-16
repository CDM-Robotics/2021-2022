// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.ShooterSystem;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.Subsystem;

/** The Shooter system is resposible for the 2 shooter motors, the motor that rotates and aims the shooter, and the motor that moves the balls into the shooter */
public class ShooterSys implements Subsystem {

    private static ShooterSys mShooterSys;

    //The left and right shooter motors 
    private WPI_TalonFX mShooterLeft;
    private WPI_TalonFX mShooterRight; 

    //Shooter aim and rotation motor
    private WPI_VictorSPX mTurretMotor;
    
    //reload motor
    private WPI_VictorSPX mReloadMotor; 


    public static ShooterSys getInstance() {

        if (mShooterSys == null) {

            mShooterSys = new ShooterSys();
        }

        return mShooterSys;
    }

    public ShooterSys() {

        mShooterLeft = new WPI_TalonFX(ShooterSysConstants.LEFT_SHOOTER_MOTOR); 
        mShooterRight = new WPI_TalonFX(ShooterSysConstants.RIGHT_SHOOTER_MOTOR);

        mTurretMotor = new WPI_VictorSPX(ShooterSysConstants.TURRET_ROTATION_MOTOR);

        mReloadMotor = new WPI_VictorSPX(ShooterSysConstants.SHOOTER_RELOAD_MOTOR);



    }

    private void configMotors() {

        mShooterLeft.setInverted(ShooterSysConstants.LEFT_SHOOTER_MOTOR_isInverted);
        mShooterRight.setInverted(ShooterSysConstants.RIGHT_SHOOTER_MOTOR_isInverted);


    }
}
