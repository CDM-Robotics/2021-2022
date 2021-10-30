// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Vision;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/** Add your docs here. */
public class VisionLimelight {

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    /**shows the X offset from center */
    private NetworkTableEntry tx = table.getEntry("tx");
    /**shows the Y offset from center */
    private NetworkTableEntry ty = table.getEntry("ty");
    private NetworkTableEntry ta = table.getEntry("ta");

    //read values periodically
    
    private double area = ta.getDouble(0.0);

    //post to smart dashboard periodically
    //SmartDashboard.putNumber("LimelightX", x);
    //SmartDashboard.putNumber("LimelightY", y);
    //SmartDashboard.putNumber("LimelightArea", area);

    private static VisionLimelight mVisionLimelight;

    public static VisionLimelight getInstance() {

        if (mVisionLimelight == null) {

            mVisionLimelight = new VisionLimelight(); 
        }

        return mVisionLimelight;
    }

    public double getXoffset() {

        return tx.getDouble(0.0); 
    }

    public double getYoffset() {

        return ty.getDouble(0.0);
    }

    public double getDistanceToTarget() {

        double distance = 0; 

        distance = (VisionLimelightConstants.FEILD_TARGET_HEIGHT - VisionLimelightConstants.LIMELIGHT_MOUNTING_HEIGHT) / Math.tan(VisionLimelightConstants.LIMELIGHT_MOUNTING_ANGLE + getYoffset());


        return distance; 
    }

    private int count = 0; 

    public void printValues() {

        if (count % 5 == 0) {

            System.out.println("X:" + getXoffset() + " Y:" + getYoffset() +" Distance:" + getDistanceToTarget());
        }




    }
}
