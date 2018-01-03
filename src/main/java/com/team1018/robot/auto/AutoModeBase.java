package com.team1018.robot.auto;

import com.team1018.robot.Constants;
import com.team1018.robot.auto.actions.Action;

/**
 * @author Ryan Blue
 */
public abstract class AutoModeBase {

    private boolean active;

    private long delay = (long) (1 / Constants.AUTO_UPDATE_RATE_HZ) * 1000;

    public void run() {
        active = true;
        try {
            routine();
        } catch(AutoModeEndedException e) {
            e.printStackTrace();
            return;
        }
        done();

    }

    protected abstract void routine() throws AutoModeEndedException;

    public void done() {
    }

    public void stop() {

    }

    public void runAction(Action action) throws AutoModeEndedException {
        isActiveWithThrow();
        action.start();
        while(isActiveWithThrow() && !action.isFinished()) {
            action.update();
            try {
                Thread.sleep(delay);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        action.done();
    }

    public boolean isActiveWithThrow() throws AutoModeEndedException {
        if(!isActive()) throw new AutoModeEndedException();
        return isActive();
    }

    public boolean isActive() {
        return active;
    }
}
