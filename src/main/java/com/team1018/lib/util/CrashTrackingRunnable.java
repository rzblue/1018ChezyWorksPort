package com.team1018.lib.util;

/**
 * @author Ryan Blue
 */
public abstract class CrashTrackingRunnable implements Runnable {

    @Override
    public final void run() {
        try {
            runCrashTracked();
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    public abstract void runCrashTracked();
}
