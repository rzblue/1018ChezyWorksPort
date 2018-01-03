package com.team1018.robot.subsystems;

import com.team1018.robot.Constants;
import edu.wpi.first.wpilibj.TalonSRX;

/**
 * @author Ryan Blue
 */
public class Climber extends Subsystem {

    private static Climber instance;

    private TalonSRX lowerMotor;
    private TalonSRX upperMotor;

    public Climber() {
        lowerMotor = new TalonSRX(Constants.CLIMBER_LOWER_PWM);
        upperMotor = new TalonSRX(Constants.CLIMBER_UPPER_PWM);
        lowerMotor.setInverted(true);
    }

    public static Climber getInstance() {
        if(instance == null) instance = new Climber();
        return instance;
    }

    public void setClimbUp() {
        lowerMotor.set(1);
        upperMotor.set(1);
    }

    public void setClimbDown() {
        lowerMotor.set(-1);
        upperMotor.set(-1);
    }

    @Override
    public void outputToSD() {

    }

    @Override
    public void stop() {
        stopClimber();
    }

    public void stopClimber() {
        lowerMotor.set(0);
        upperMotor.set(0);
    }
}
