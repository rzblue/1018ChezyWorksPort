package com.team1018.robot.loops;

import com.team1018.lib.util.CrashTrackingRunnable;
import com.team1018.robot.Constants;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan Blue
 */
public class Looper {
    private final double delay = (long) (1 / Constants.LOOP_RATE_HZ) * 1000;
    private final Notifier notifier;
    private final List<Loop> loops;
    private final Object runLock = new Object();
    private boolean running;
    private double lastRunTime = 0;
    private double loopDuration = 0;
    private final CrashTrackingRunnable runnable = new CrashTrackingRunnable() {
        @Override
        public void runCrashTracked() {
            double currentTime = Timer.getFPGATimestamp();
            for(Loop loop : loops) {
                loop.loop();
            }
            loopDuration = currentTime - lastRunTime;
            lastRunTime = currentTime;
        }
    };

    public Looper() {
        notifier = new Notifier(runnable);
        running = false;
        loops = new ArrayList<>();
    }

    public synchronized void addLoop(Loop loop) {
        synchronized(runLock) {
            loops.add(loop);
        }
    }

    public synchronized void start() {
        if(!running) {
            lastRunTime = Timer.getFPGATimestamp();
            for(Loop loop : loops) {
                loop.onStart();
            }
            running = true;
        }
        notifier.startPeriodic(delay);
    }

    public synchronized void stop() {
        if(running) {
            notifier.stop();
            synchronized(runLock) {
                running = false;
                for(Loop loop : loops) {
                    loop.onStop();
                }
            }
        }
    }
}
