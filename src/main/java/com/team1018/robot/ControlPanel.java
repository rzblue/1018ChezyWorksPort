package com.team1018.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Ryan Blue
 *         <p>
 *         This class represents the control panel for the robot.
 */
public class ControlPanel {
    private static ControlPanel instance;

    private final Joystick translateStick;
    private final Joystick rotateStick;
    private final Joystick buttonPanel;

    private ControlPanel() {
        translateStick = new Joystick(Constants.TRANSLATE_STICK_USB);
        rotateStick = new Joystick(Constants.ROTATE_STICK_USB);
        buttonPanel = new Joystick(Constants.BUTTON_PANEL_USB);
    }

    public static ControlPanel getInstance() {
        if(instance == null) {
            instance = new ControlPanel();
        }
        return instance;
    }

    public double getXTranslate() {
        return translateStick.getX();
    }

    public double getYTranslate() {
        return translateStick.getY();
    }

    public double getZRotate() {
        return rotateStick.getX();
    }

    public boolean getClimbUp() {
        return buttonPanel.getRawButton(Constants.CLIMB_UP_BTN[0]) && buttonPanel.getRawButton(Constants.CLIMB_UP_BTN[1]);
    }

    public boolean getClimbDown() {
        return buttonPanel.getRawButton(Constants.CLIMB_DOWN_BTN[0]) && buttonPanel.getRawButton(Constants.CLIMB_DOWN_BTN[1]);
    }

    public boolean getBrakes() {
        return buttonPanel.getRawButton(Constants.BRAKES_BTN);
    }

    public boolean getFlipper() {
        return buttonPanel.getRawButton(Constants.FLIPPER_BTN);
    }

    public boolean getPaddles() {
        return buttonPanel.getRawButton(Constants.PADDLES_BTN[0]) && buttonPanel.getRawButton(Constants.PADDLES_BTN[1]);
    }

    public boolean getAutoAlign() {
        return translateStick.getRawButton(Constants.AUTO_ALIGN_DRIVER_BTN) && buttonPanel.getRawButton(Constants.AUTO_ALIGN_OPERATOR_BTN);
    }

    public boolean getSwitchDriveMode() {
        return rotateStick.getRawButton(Constants.CHANGE_DRIVE_MODE_DRIVER_BTN);
    }
}
