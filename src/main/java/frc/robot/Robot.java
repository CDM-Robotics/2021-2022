// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;



import java.io.*;
import java.util.ArrayList;
import java.util.logging.*;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Subsystems.DriveSystem.DriveSystem;
import frc.robot.Subsystems.DriveSystem.driveSysCommands.ArcadeDriveCmd;
import frc.robot.Subsystems.IntakeSystem.IntakeSys;
import frc.robot.Subsystems.IntakeSystem.IntakeSysCommands.extendIntakeCmd;
import frc.robot.Subsystems.IntakeSystem.IntakeSysCommands.runIntakeCmd;
import frc.robot.logging.JLogWrapper;
import frc.robot.logging.LogWrapper;
import frc.robot.logging.LogWrapper.FileType;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private CommandScheduler mScheduler;

  private LogWrapper mLog;

	private JLogWrapper mJLog;



  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    // initializes the java logging system
    //configLogging();
    //mLog.alarm("-------     Robot initialization     -------");

    // creates an instance on the scheduler and sets it to mScheduler
    mScheduler = CommandScheduler.getInstance();

    // creates an instance of the drive system so that its methods can be called within this class. 
    DriveSystem.getInstance();
    IntakeSys.getInstance(); 

  }


  /* private void configLogging() {

    mLog = new LogWrapper(FileType.ROBOT, "Robot", frc.robot.logging.LogWrapper.Permission.ALL);
    
    try {
      File dir = Filesystem.getDeployDirectory();
      String logFile = "NOTFOUND"; 
      if (dir.isDirectory()) {
        logFile = dir.getAbsolutePath() + "/logging.properties";
      }
      System.out.println("**********  logConfig: " + logFile + "  *********************");
			FileInputStream configFile = new FileInputStream(logFile);
			LogManager.getLogManager().readConfiguration(configFile);
    } catch (IOException ex) {
      System.out.println("WARNING: Could not open logging configuration file");
			System.out.println("WARNING: Logging not configured (console output only)");
    }

    mJLog = new JLogWrapper(Robot.class.getName());
  }  */
  
  @Override
  public void robotPeriodic() {
  
  }


  /**This function is called once as the autonomus period initializes    */
  @Override
  public void autonomousInit() {
 

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {

    //mLog.alarm("TelopInit");

		// Cancel all events
		mScheduler.cancelAll();

		// start independent Threads
		//startThreads();

		// Schedule commands
    ArcadeDriveCmd arcadeDriveCmd = new ArcadeDriveCmd(ControlBoard.getInstance().mDriveStick);
    runIntakeCmd runIntakeCmd = new runIntakeCmd(ControlBoard.getInstance().mDriveStick);
    //extendIntakeCmd extendIntakeCmd = new extendIntakeCmd(ControlBoard.getInstance().mDriveStick);
    
    //ParallelCommandGroup intakeCommandGroup = new ParallelCommandGroup(runIntakeCmd, extendIntakeCmd); 
    mScheduler.schedule(arcadeDriveCmd);
    mScheduler.schedule(runIntakeCmd);
    //mScheduler.schedule(extendIntakeCmd);
    
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    mScheduler.run();
    
    /* try {
			mScheduler.run();
			// mLog.periodicDebug(10, "Left", DriveSys.getInstance().getLeftCurnPosInches(), "Right", DriveSys.getInstance().getRightCurnPosInches());
			
			// mLog.periodicDebug(10, "X", position.getPositionVector2D().getX(), "Y", position.getPositionVector2D().getY(), "angle", position.getAngle2D().getDegrees());
		} catch (Exception ex) {
			mJLog.severe(ex, "Robot.teleopPeriodic:  exception: " + ex.getMessage());
		}  */
    
		}

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
