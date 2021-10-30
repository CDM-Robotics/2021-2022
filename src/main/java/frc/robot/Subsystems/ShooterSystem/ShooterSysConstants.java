// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.ShooterSystem;

import edu.wpi.first.wpilibj.Joystick;

/** Add your docs here. */
public class ShooterSysConstants {

    public static final int SERIALIZER_MAIN_MASTER = 10;
    public static final int SERIALIZER_MAIN_SLAVE0 = 0;
    public static final int SERIALIZER_HORIZONTAL_MASTER = 11; 

    public static final int SHOOTER_RIGHT_MASTER = 12; 
    public static final int SHOOTER_LEFT_SLAVE0 = 15;

    public static final int SHOOTER_ROTATE_MASTER = 4; 

    public static final boolean SERIALIZER_MAIN_MASTER_isInverted = false; 
    public static final boolean SERIALIZER_MAIN_SLAVE0_isInverted = false; 
    public static final boolean SERIALIZER_HORIZONTAL_SLAVE0_isInverted = false; 

    public static final boolean SHOOTER_RIGHT_MASTER_isInverted = false; 
    public static final boolean SHOOTER_LEFT_SLAVE0_isInverted = true;

    public static final boolean SHOOTER_ROTATE_MASTER_isInverted = false; 

    public static final int  SHOOT_BUTTON = 1; 
    public static final int  ENABLE_AIM_ASSIST_BUTTON = 2; 

    public static final double MAX_PERCENT_OUT = 0.5; 

}
