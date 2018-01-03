package com.team1018.robot;

import com.team1018.robot.loops.Looper;
import com.team1018.robot.subsystems.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    Drive drive = Drive.getInstance();
    GearFlipper gearFlipper = GearFlipper.getInstance();
    Brakes brakes = Brakes.getInstance();
    Paddles paddles = Paddles.getInstance();
    Climber climber = Climber.getInstance();

    ControlPanel controlPanel = ControlPanel.getInstance();

    Looper enabledLooper = new Looper();
    Looper disabledLooper = new Looper();

    boolean previousSwitchDriveModeValue = false;
    boolean fieldOriented;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        enabledLooper.addLoop(gearFlipper.getLoop());
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
        setStartingPosition();
        stopAll();
        enabledLooper.stop();
        disabledLooper.start();
    }

    /**
     * This function is called once at the start of auto
     */
    @Override
    public void autonomousInit() {
        disabledLooper.stop();
        enabledLooper.start();
        setCompPosition();
    }

    /**
     * This function is called once at the start of teleop
     */
    @Override
    public void teleopInit() {
        disabledLooper.stop();
        enabledLooper.start();
        setCompPosition();
    }

    /**
     * This function is called periodically during disabled
     */
    @Override
    public void disabledPeriodic() {
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        if(controlPanel.getSwitchDriveMode() && !previousSwitchDriveModeValue) {
            fieldOriented = !fieldOriented;
        }
        previousSwitchDriveModeValue = controlPanel.getSwitchDriveMode();
        drive.setOpenLoop(controlPanel.getXTranslate(), controlPanel.getYTranslate(), controlPanel.getZRotate(), fieldOriented);
        gearFlipper.overrideController(controlPanel.getFlipper());
        brakes.setBrakes(controlPanel.getBrakes());
        paddles.setPaddlesIn(controlPanel.getPaddles());
        if(controlPanel.getClimbUp()) {
            climber.setClimbUp();
        } else if(controlPanel.getClimbDown()) {
            climber.setClimbDown();
        } else {
            climber.stopClimber();
        }

    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }

    /**
     * Sets robot in it's match-ready position
     */
    public void setCompPosition() {
        paddles.setPaddlesIn(false);
    }

    /**
     * Sets robot in it's legal starting state
     */
    public void setStartingPosition() {
        brakes.setBrakes(false);
        paddles.setPaddlesIn(true);
    }

    /**
     * Stops all actuators
     */
    public void stopAll() {
        drive.stop();
        gearFlipper.stop();
        brakes.stop();
        paddles.stop();
    }
}
