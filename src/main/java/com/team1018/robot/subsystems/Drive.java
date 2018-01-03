package com.team1018.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.team1018.robot.Constants;
import com.team1018.robot.loops.Loop;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Ryan Blue
 */
public class Drive extends Subsystem implements PIDOutput {

    private static Drive instance;
    private final TalonSRX frontRight, frontLeft, rearRight, rearLeft;

    private final RobotDrive robotDrive;
    private final AHRS navX;

    private final Encoder rightEncoder;
    private final Encoder leftEncoder;

    private final Ultrasonic leftUltrasonic;
    private final Ultrasonic rightUltrasonic;

    private final PIDController turnController;

    private RobotBase base;
    private DriveMode mode;

    private double angleSetpoint;
    private double pidRotationOutput;

    private Loop loop = new Loop() {
        @Override
        public void onStart() {

        }

        @Override
        public void loop() {
            turnController.setSetpoint(angleSetpoint);
            switch(mode) {
                case OPEN_LOOP:
                    break;
                case PID_DRIVE:
                    break;
                case PID_ROTATE:
                    updateRotation(pidRotationOutput);
                    break;
            }
        }

        @Override
        public void onStop() {
            stop();
        }
    };

    private Drive() {
        mode = DriveMode.OPEN_LOOP;
        frontLeft = new TalonSRX(Constants.FRONT_LEFT_DRIVE_PWM);
        rearLeft = new TalonSRX(Constants.REAR_LEFT_DRIVE_PWM);
        frontRight = new TalonSRX(Constants.FRONT_RIGHT_DRIVE_PWM);
        rearRight = new TalonSRX(Constants.REAR_RIGHT_DRIVE_PWM);
        frontRight.setInverted(true);
        rearRight.setInverted(true);
        robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
        navX = new AHRS(SPI.Port.kMXP);
        leftEncoder = new Encoder(Constants.LEFT_ENCODER_A_DIO, Constants.LEFT_ENCODER_B_DIO, Constants.LEFT_ENCODER_REV, Constants.ENCODING_TYPE);
        rightEncoder = new Encoder(Constants.RIGHT_ENCODER_A_DIO, Constants.RIGHT_ENCODER_B_DIO, Constants.RIGHT_ENCODER_REV, Constants.ENCODING_TYPE);
        leftEncoder.setDistancePerPulse(Constants.INCHES_PER_TICK);
        rightEncoder.setDistancePerPulse(Constants.INCHES_PER_TICK);
        leftUltrasonic = new Ultrasonic(Constants.LEFT_ULTRASONIC_PING_DIO, Constants.LEFT_ULTRASONIC_ECHO_DIO);
        rightUltrasonic = new Ultrasonic(Constants.RIGHT_ULTRASONIC_PING_DIO, Constants.RIGHT_ULTRASONIC_ECHO_DIO);
        turnController = new PIDController(0, 0, 0, navX, this);
    }

    public static Drive getInstance() {
        if(instance == null) instance = new Drive();
        return instance;
    }

    public void setDriveMode(DriveMode mode) {
        stop();
        switch(mode) {
            case OPEN_LOOP:
                turnController.disable();
                break;
            case PID_DRIVE:
                turnController.enable();
            case PID_ROTATE:
                turnController.enable();

        }
        this.mode = mode;
    }

    public void setOpenLoop(double xMagnitude, double yMagnitude, double zRotation, boolean fieldOriented) {
        setDriveMode(DriveMode.OPEN_LOOP);
        robotDrive.mecanumDrive_Cartesian(xMagnitude, yMagnitude, zRotation, fieldOriented ? navX.getYaw() : 0);
    }

    public void setPIDDrive(double magnitude, double driveAngle, double rotateAngle) {
        setDriveMode(DriveMode.PID_DRIVE);
        setAngleSetpoint(rotateAngle);
        robotDrive.mecanumDrive_Polar(magnitude, driveAngle, pidRotationOutput);
    }

    public void setPIDRotate(double angle) {
        setDriveMode(DriveMode.PID_ROTATE);
        setAngleSetpoint(angle);
    }

    private void updateRotation(double rotation) {
        robotDrive.mecanumDrive_Cartesian(0, 0, rotation, 0);
    }

    private void setAngleSetpoint(double angleSetpoint) {
        this.angleSetpoint = angleSetpoint;
    }

    @Override
    public void outputToSD() {
        SmartDashboard.putString("Drive Mode", mode.name());
    }

    @Override
    public void stop() {
        robotDrive.stopMotor();
    }

    public double getLeftEncoderDistance() {
        return leftEncoder.getDistance();
    }

    public double getRightEncoderDistance() {
        return rightEncoder.getDistance();
    }

    public double getLargestEncoderDistance() {
        return rightEncoder.getDistance() > leftEncoder.getDistance() ? rightEncoder.getDistance() : leftEncoder.getDistance();
    }

    public void resetEncoders() {
        rightEncoder.reset();
        leftEncoder.reset();
    }

    public double getLeftUltrasonicRangeInches() {
        return leftUltrasonic.getRangeInches();
    }

    public double getRightUltrasonicRangeInches() {
        return rightUltrasonic.getRangeInches();
    }

    public double getAverageUltrasonicRangeInches() {
        return (leftUltrasonic.getRangeInches() + rightUltrasonic.getRangeInches()) / 2;
    }

    @Override
    public void pidWrite(double output) {
        pidRotationOutput = output;
    }

    /**
     * Not supported yet
     */
    public enum RobotBase {
        ROBOT_ORIENTED, FIELD_ORIENTED;
    }

    public enum DriveMode {
        OPEN_LOOP, PID_DRIVE, PID_ROTATE;
    }


}
