// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.DriveSystem;

import java.io.Console;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Subsystem;
//import frc.robot.logging.LogWrapper;


/** 
 * Organizes the different methods for motor control 
 */
public class DriveSystem implements Subsystem  {



    // masters are the leads here. The Left or Right Master directs the Left or Right follower respectivly. 
    private WPI_TalonFX mLeft_Master;
    private WPI_TalonFX mRight_Master;

    // followers are created to follow masters
    private WPI_TalonFX mLeft_Slave0;
    private WPI_TalonFX mRight_Slave0;

    /**This holds the methods that are used to drive the robot */
    

    // This is the blank drive system object. Once it becomes initialized it will serve as the drive system for the whole robot. 
    private static DriveSystem mDriveSys;

    //private LogWrapper mLog; 

    /**
     * The get instance method of the drive system allows the drive system to be used by the rest of the program. 
     */
    public static DriveSystem getInstance() {

        //Checks to see if a drive system object already exists. 
        //Creates a new drive system if one doesnt already exist. 
        //Returns the drive system object. 
        if (mDriveSys == null) {

            mDriveSys = new DriveSystem();
        }
        return mDriveSys; 
    }

    /**
     * This is the base constuctor for the drive system. It has no parameters and when it is called it creates a drive system object, sets the constants of the motors. 
     */
    private DriveSystem() {


        mLeft_Master = new WPI_TalonFX(DriveSysConstants.LEFT_TALON_MASTER);
        mLeft_Slave0 = new WPI_TalonFX(DriveSysConstants.LEFT_TALON_SLAVE0);
        mRight_Master = new WPI_TalonFX(DriveSysConstants.RIGHT_TALON_MASTER);
        mRight_Slave0 = new WPI_TalonFX(DriveSysConstants.RIGHT_TALON_SLAVE0);

        configMotors();
    }

    /**
     * The configMotors() fuction does the following:
     * Sets the followers to follow the Masters, 
     * Defines which motors are inverted,
     * 
     */
    private void configMotors() {

        mLeft_Slave0.follow(mLeft_Master);
        mRight_Slave0.follow(mRight_Master);

        mLeft_Master.setInverted(DriveSysConstants.LEFT_TALON_MASTER_isInverted);
        mLeft_Slave0.setInverted(InvertType.FollowMaster);
        mRight_Master.setInverted(DriveSysConstants.RIGHT_TALON_MASTER_isInverted);
        mRight_Slave0.setInverted(InvertType.FollowMaster);

        mLeft_Master.setSensorPhase(DriveSysConstants.LEFT_TALON_MASTER_SENSOR_PHASE);
        mLeft_Slave0.setSensorPhase(DriveSysConstants.LEFT_TALON_SLAVE0_SENSOR_PHASE);
        mRight_Master.setSensorPhase(DriveSysConstants.RIGHT_TALON_MASTER_SENSOR_PHASE);
        mRight_Slave0.setSensorPhase(DriveSysConstants.RIGHT_TALON_SLAVE0_SENSOR_PHASE);


    
        
        
    }
    
    
    double leftMag = 0; 
    double rightMag = 0; 
    boolean magIsPositive;
    boolean yawIsPositive;

    public void arcadeDrive(double mag, double yaw, double speed) {
        
        
        //mLog.periodicPrint("mag: " + mag + "  yaw: " + yaw + "  maxPercentOut: " + speed, 25);

        speed = speed/5;
        magIsPositive = (mag > 0);
        yawIsPositive = (yaw > 0); 

        yaw = 1 - Math.abs(yaw);
        
        if (yaw > 0.7) {// to drive straight forward or backward

            leftMag = mag * speed; 
            rightMag = mag * speed; 

        }else if ((yaw < 0.15) && (Math.abs(mag)< 0.25)) {// to turn in place 

            if (yawIsPositive) {

                leftMag = speed / 1.3;
                rightMag = speed * -1 / 1.3;
            } else {

                leftMag = speed * -1 / 1.3;
                rightMag = speed / 1.3;
            }

        } else { // to turn and move. 
        
            mag = DriveSysConstants.DRIVE_MIN_PERCENT_OUT + ((mag * mag) * (1 - DriveSysConstants.DRIVE_MIN_PERCENT_OUT));
            
            if (magIsPositive) {

                if (yawIsPositive) {

                    leftMag = mag * speed; 
                    rightMag = mag * (0.5 + (yaw/2)) * speed; 
                }else{

                    rightMag = mag * speed; 
                    leftMag = mag * (0.5 + (yaw/2)) * speed; 
                }

            }else{

                if (yawIsPositive) {

                    leftMag = mag * speed * -1; 
                    rightMag = mag * (0.5 + (yaw/2)) * speed * -1;
                }else{

                    rightMag = mag * speed * -1; 
                    leftMag = mag * (0.5 + (yaw/2)) * speed * -1; 
                }
            }     
        }  

        mLeft_Master.set(ControlMode.PercentOutput, leftMag);
        mRight_Master.set(ControlMode.PercentOutput, rightMag);

        //mRoboDrive.arcadeDrive(mag/10, yaw/4, true);

        //mLog.periodicPrint("Mag: " + mag + " yaw: " + yaw, 20);
        
    }

    

    
}
