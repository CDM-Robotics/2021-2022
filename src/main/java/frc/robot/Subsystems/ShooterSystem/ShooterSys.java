// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.ShooterSystem;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Subsystems.IntakeSystem.IntakeSys;
import frc.robot.Subsystems.Vision.VisionLimelight;

/** The Shooter system is resposible for the 2 shooter motors, the motor that rotates and aims the shooter, and the motors that moves the balls into the shooter */
public class ShooterSys implements Subsystem {

    private static ShooterSys mShooterSys;

    private WPI_TalonSRX mSerializer_Master; 
     
    private WPI_TalonSRX mSerializerHorizontal_Slave0;

    //private CANSparkMax mShooter_Master; 
    //private CANSparkMax mShooter_Slave0; 

    private WPI_TalonFX mShooter_Master; 
    private WPI_TalonFX mShooter_Slave0; 

    private WPI_TalonSRX mShooterRotate_Master; 

    


    public static ShooterSys getInstance() {

        if (mShooterSys == null) {

            mShooterSys = new ShooterSys();
        }

        return mShooterSys;
    }

    public ShooterSys() {

        mSerializer_Master = new WPI_TalonSRX(ShooterSysConstants.SERIALIZER_MAIN_MASTER); 
        mSerializerHorizontal_Slave0 = new WPI_TalonSRX(ShooterSysConstants.SERIALIZER_HORIZONTAL_MASTER);

        //mShooter_Master = new CANSparkMax(ShooterSysConstants.SHOOTER_RIGHT_MASTER, MotorType.kBrushless);
        //mShooter_Slave0 = new CANSparkMax(ShooterSysConstants.SHOOTER_LEFT_SLAVE0, MotorType.kBrushless);

        mShooter_Master = new WPI_TalonFX(ShooterSysConstants.SHOOTER_RIGHT_MASTER); 
        mShooter_Slave0 = new WPI_TalonFX(ShooterSysConstants.SHOOTER_LEFT_SLAVE0); 

        mShooterRotate_Master = new WPI_TalonSRX(ShooterSysConstants.SHOOTER_ROTATE_MASTER);

        configMotors();

    }

    private void configMotors() {

        mShooter_Slave0.follow(mShooter_Master);

        mSerializer_Master.setInverted(ShooterSysConstants.SERIALIZER_MAIN_MASTER_isInverted);
        mSerializerHorizontal_Slave0.setInverted(ShooterSysConstants.SERIALIZER_HORIZONTAL_SLAVE0_isInverted);

        mShooter_Master.setInverted(ShooterSysConstants.SHOOTER_RIGHT_MASTER_isInverted);
        mShooter_Slave0.setInverted(ShooterSysConstants.SHOOTER_LEFT_SLAVE0_isInverted);

        mShooterRotate_Master.setInverted(ShooterSysConstants.SHOOTER_ROTATE_MASTER_isInverted);
        mShooterRotate_Master.setSelectedSensorPosition(0);

    }

    private double speed = 0; 
    private int u = 0; 
    public void RunShooterSys(boolean runShooter, boolean shoot) {

        if (runShooter) {
     
            mSerializer_Master.set(ControlMode.PercentOutput, 1);
            mSerializerHorizontal_Slave0.set(ControlMode.PercentOutput, 1);
            //mShooter_Master.set(1);
        }else{
            mSerializer_Master.set(ControlMode.PercentOutput, 0);
            mSerializerHorizontal_Slave0.set(ControlMode.PercentOutput, 0);

            //mShooter_Master.set(0);
        }

        if (shoot) {

            speed -= 0.73; //0.73
            speed = Math.abs(speed); 
        }
        mShooter_Master.set(speed);

    }

    public void runSerializer(boolean run) {

        if (run) {

            mSerializer_Master.set(ControlMode.PercentOutput, 1);
        }else {

            mSerializer_Master.set(ControlMode.PercentOutput, 0);
        }
    } 


    public void aimShooter(double y, boolean isAimAssist) {

        double shooterRotate = 0; 

        if (isAimAssist == true) {

            shooterRotate = aimAssist(); 
        }else{

            shooterRotate = y/2; 
        }

        double currentTicks = IntakeSys.getInstance().getTicks(); 

        VisionLimelight.getInstance().printValues();

        if (Math.abs(currentTicks) > ShooterSysConstants.SHOOTER_TICK_RANGE) {
            
            if ((currentTicks > 0) && (shooterRotate > 0)) {
                shooterRotate = 0; 
            } else if ((currentTicks < 0) && (shooterRotate < 0)) {
                shooterRotate = 0; 
            }
        }

        mShooterRotate_Master.set(ControlMode.PercentOutput, shooterRotate);
    }

    public double aimAssist() {

        double xOffset = VisionLimelight.getInstance().getXoffset(); 
        double xCorrection = 0; 

        if(xOffset > 0.5) {

            xCorrection = 0.07 + Math.abs(xOffset/100); 
            //xCorrection = +0.19 + ((Math.abs(xOffset)/100) * 3); 
        } else if(xOffset < -0.5) {

            xCorrection = -0.07 -Math.abs(xOffset/100); 
            //xCorrection = -0.19 - ((Math.abs(xOffset)/100) * 3); 
        }

        return xCorrection; 
    }

    private int count = 0; 
    public void printVal(String print ,double out) {

        if (count % 25 == 0) {

            System.out.println(print + out);
        }

        count++;
    }
}
