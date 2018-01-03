package com.team1018.robot.auto.actions;

import com.team1018.robot.subsystems.Drive;

/**
 * @author Ryan Blue
 */
public class DriveStraightAction implements Action {
    private double distance;
    private double speed;
    private Drive drive = Drive.getInstance();

    public DriveStraightAction(double distance, double speed) {
        this.distance = distance;
        this.speed = speed;
    }

    @Override
    public void start() {
        drive.resetEncoders();
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isFinished() {
        return drive.getLargestEncoderDistance() >= distance;
    }

    @Override
    public void done() {
        drive.stop();
    }
}
