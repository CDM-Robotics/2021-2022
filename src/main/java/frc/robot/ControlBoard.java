// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Utility.LogitechJoystick;

/** This is where all the controll systems are created and initialized. Control systems would be things like the joysticks */
public class ControlBoard {

    // creates a blank control board object
    private static ControlBoard mControlBoard;

    //Drive stick is the joystick for driving the robot
    public LogitechJoystick mDriveStick; 
    private static int DRIVE_USB_PORT = 0;



    /**Checks for the existence of a ControlBoard object. If one does not exist it creates one. Returns the ControlBoard object */
    public static ControlBoard getInstance() {
        if (mControlBoard == null) {
            mControlBoard = new ControlBoard();
        }
        return mControlBoard;
    }

    private ControlBoard() {

        mDriveStick = new LogitechJoystick(DRIVE_USB_PORT);
    }

}
