package com.team1018.robot;

import edu.wpi.first.wpilibj.CounterBase;

/**
 * @author Ryan Blue
 */
public class Constants {
    //PWM outputs
    public final static int REAR_RIGHT_DRIVE_PWM = 0;
    public final static int REAR_LEFT_DRIVE_PWM = 1;
    public final static int FRONT_RIGHT_DRIVE_PWM = 2;
    public final static int FRONT_LEFT_DRIVE_PWM = 3;

    public final static int FLIPPER_PWM = 4;

    public final static int CLIMBER_LOWER_PWM = 5;
    public final static int CLIMBER_UPPER_PWM = 6;

    //Pneumatic Solenoids
    public final static int BRAKES_UP_SLD = 0;
    public final static int BRAKES_DOWN_SLD = 1;
    public final static int PADDLES_IN_SLD = 2;
    public final static int PADDLES_OUT_SLD = 3;

    //Sensors

    public final static int RIGHT_ULTRASONIC_PING_DIO = 0;
    public final static int RIGHT_ULTRASONIC_ECHO_DIO = 1;
    public final static int LEFT_ULTRASONIC_PING_DIO = 2;
    public final static int LEFT_ULTRASONIC_ECHO_DIO = 3;

    public final static int RIGHT_ENCODER_A_DIO = 4;
    public final static int RIGHT_ENCODER_B_DIO = 5;
    public final static boolean RIGHT_ENCODER_REV = true;
    public final static int LEFT_ENCODER_A_DIO = 6;
    public final static int LEFT_ENCODER_B_DIO = 7;
    public final static boolean LEFT_ENCODER_REV = false;
    public final static CounterBase.EncodingType ENCODING_TYPE = CounterBase.EncodingType.k4X;
    public final static double WHEEL_DIAMETER_IN = 6;
    public final static int ENCODER_COUNTS_PER_REVOLUTION = 256;
    public final static double INCHES_PER_TICK = (WHEEL_DIAMETER_IN * Math.PI) / ENCODER_COUNTS_PER_REVOLUTION;

    public final static int FLIPPER_BANNER_DIO = 9;

    public final static int TRANSLATE_STICK_USB = 0;
    public final static int ROTATE_STICK_USB = 1;
    public final static int BUTTON_PANEL_USB = 2;

    public final static int BRAKES_BTN = 10;
    public final static int FLIPPER_BTN = 15;
    public final static int[] CLIMB_UP_BTN = {2, 16};
    public final static int[] CLIMB_DOWN_BTN = {1, 11};
    public final static int[] PADDLES_BTN = {2, 11};
    public final static int AUTO_ALIGN_OPERATOR_BTN = 13;
    public final static int AUTO_ALIGN_DRIVER_BTN = 1;
    public final static int CHANGE_DRIVE_MODE_DRIVER_BTN = 1;

    public static final double LOOPER_PERIOD = 0.01;

    public static final double AUTO_UPDATE_RATE_HZ = 50;
    public static final double LOOP_RATE_HZ = 100;
}
