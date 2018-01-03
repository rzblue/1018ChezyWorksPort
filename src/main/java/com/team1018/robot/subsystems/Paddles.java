package com.team1018.robot.subsystems;

import com.team1018.robot.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * @author Ryan Blue
 */
public class Paddles extends Subsystem {

    private static Paddles instance;
    private DoubleSolenoid paddlesSolenoid;

    private Paddles() {
        paddlesSolenoid = new DoubleSolenoid(Constants.PADDLES_OUT_SLD, Constants.PADDLES_IN_SLD);
    }

    public static Paddles getInstance() {
        if(instance == null) instance = new Paddles();
        return instance;
    }

    public void setPaddlesIn(boolean paddlesIn) {
        paddlesSolenoid.set(paddlesIn ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
    }

    @Override
    public void outputToSD() {

    }

    @Override
    public void stop() {

    }

}
