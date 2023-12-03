package csi2999;

import java.util.Random;

public class RandomTimer {
    private long endTime;
    private final long minTime;
    private final long maxTime;
    private Random random = new Random();
    private TimerCallback timerCallback;
    private boolean paused = false; // Flag to indicate whether the timer is paused

    public RandomTimer(long minTime, long maxTime) {
        this.minTime = minTime;
        this.maxTime = maxTime;
        reset();
    }

    public void reset() {
        long interval = minTime + (long) (random.nextDouble() * (maxTime - minTime));
        endTime = System.currentTimeMillis() + interval;
    }

    public boolean isTimeUp() {
        if (System.currentTimeMillis() >= endTime && !paused) {
            if (timerCallback != null) {
                timerCallback.onTimerFinished();
            }
            return true;
        }
        return false;
    }

    public void setTimerCallback(TimerCallback callback) {
        this.timerCallback = callback;
    }

    public interface TimerCallback {
        void onTimerFinished();
    }

    // Method to pause the timer
    public void pause() {
        paused = true;
    }

    // Method to resume the timer
    public void resume() {
        paused = false;
    }
}
