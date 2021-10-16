// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Utility;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechJoystick extends Joystick {

    public LogitechJoystick(int portNumber) {
        super(portNumber);
    }

    /**
     * This function gets the Y from the josytick and multplies it by -1 so that
     * pushing the joystick forward will result in positive numbers rather than the
     * usual negative numbers.
     * 
     * @return
     */
    public double getInvertedY() {
        return -getY();
    }

}