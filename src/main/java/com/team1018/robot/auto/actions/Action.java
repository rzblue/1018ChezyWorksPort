package com.team1018.robot.auto.actions;

/**
 * @author Ryan Blue
 */
public interface Action {
    void start();

    void update();

    boolean isFinished();

    void done();
}
