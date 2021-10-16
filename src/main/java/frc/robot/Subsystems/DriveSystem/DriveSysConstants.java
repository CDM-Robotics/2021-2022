// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.DriveSystem;

/** 
 * The tallon numbers, base percent out, and other constants for the drive system 
 * */
public class DriveSysConstants {


    //Talon numbers corresponding to physical talons on the robot. 
    public static final int LEFT_TALON_MASTER = 14;
    public static final int LEFT_TALON_SLAVE0 = 13;
    public static final int RIGHT_TALON_MASTER = 2;
    public static final int RIGHT_TALON_SLAVE0 = 1;

    public static final boolean LEFT_TALON_MASTER_SENSOR_PHASE = true;  // 2020-02-16
    public static final boolean LEFT_TALON_SLAVE0_SENSOR_PHASE = true;

    public static final boolean RIGHT_TALON_MASTER_SENSOR_PHASE = true;  // 2020-02-16
    public static final boolean RIGHT_TALON_SLAVE0_SENSOR_PHASE = true;

    //Sets inverted status to true of false. True means the motor is inverted. 
    public static final boolean LEFT_TALON_MASTER_isInverted = true;
    public static final boolean RIGHT_TALON_MASTER_isInverted = false;

    public static final double DRIVE_MIN_PERCENT_OUT = 0.2; 
}
