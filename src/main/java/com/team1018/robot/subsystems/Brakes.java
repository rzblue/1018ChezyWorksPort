package com.team1018.robot.subsystems;

import com.team1018.robot.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * @author Ryan Blue
 */
public class Brakes extends Subsystem {
    private static Brakes instance;

    private DoubleSolenoid brakesSolenoid;

    private Brakes() {
        brakesSolenoid = new DoubleSolenoid(Constants.BRAKES_DOWN_SLD, Constants.BRAKES_UP_SLD);
    }

    public static Brakes getInstance() {
        if(instance == null) instance = new Brakes();
        return instance;
    }

    public boolean getBrakes() {
        return brakesSolenoid.get() == DoubleSolenoid.Value.kForward;
    }

    public void setBrakes(boolean brakesDown) {
        brakesSolenoid.set(brakesDown ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);

    }

    @Override
    public void outputToSD() {

    }

    @Override
    public void stop() {

    }
}
