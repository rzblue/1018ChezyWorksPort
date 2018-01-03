package com.team1018.robot.subsystems;

import com.team1018.lib.LidarLite;
import com.team1018.robot.Constants;
import com.team1018.robot.loops.Loop;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Ryan Blue
 */
public class GearFlipper extends Subsystem {

    private static GearFlipper instance;

    private TalonSRX flipperMotor;
    private DigitalInput bannerSensor;
    private LidarLite lidar;
    private FlipperState state;
    private boolean override = false;
    private Loop loop = new Loop() {
        @Override
        public void onStart() {
            state = FlipperState.WAITING_FOR_GEAR;
        }

        @Override
        public void loop() {
            if(override) {
                runFlipper();
                return;
            }
            switch(state) {
                case WAITING_FOR_GEAR:
                    if(lidar.getDistanceInches() < 20) {
                        state = FlipperState.CORRECTING;
                    }
                    break;
                case CORRECTING:
                    if(!isGearReady()) {
                        runFlipper();
                    } else {
                        stopFlipper();
                        state = FlipperState.READY;
                    }
                    break;
                case READY:
                    if(!isGearReady() && !isGearInHolder()) {
                        state = FlipperState.WAITING_FOR_GEAR;
                        break;
                    }
                    if(!isGearReady()) {
                        state = FlipperState.CORRECTING;
                    }
                    break;
            }
        }

        @Override
        public void onStop() {
            stop();
        }
    };

    private GearFlipper() {
        flipperMotor = new TalonSRX(Constants.FLIPPER_PWM);
        bannerSensor = new DigitalInput(Constants.FLIPPER_BANNER_DIO);
        lidar = new LidarLite(I2C.Port.kMXP);
    }

    public static GearFlipper getInstance() {
        if(instance == null) instance = new GearFlipper();
        return instance;
    }

    public void runFlipper() {
        flipperMotor.set(-1);
    }

    public boolean isGearReady() {
        return !bannerSensor.get();
    }

    public boolean isGearInHolder() {
        return lidar.getDistanceInches() < 20;
    }

    @Override
    public void outputToSD() {
        SmartDashboard.putBoolean("Gear Ready?", state == FlipperState.READY);
        SmartDashboard.putString("Gear State", state.name());
    }

    @Override
    public void stop() {
        stopFlipper();
    }

    public void stopFlipper() {
        flipperMotor.stopMotor();
    }

    public Loop getLoop() {
        return loop;
    }

    public void overrideController(boolean override) {
        this.override = override;
    }

    public void resetState() {
        stopFlipper();
        state = FlipperState.WAITING_FOR_GEAR;
    }

    public enum FlipperState {
        WAITING_FOR_GEAR, CORRECTING, READY
    }

}
