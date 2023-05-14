package de.exware.gplatform.teavm.timer;

import de.exware.gplatform.timer.GPTimer;
import de.exware.gplatform.timer.GPTimerTask;

public class TeavmGPTimer implements GPTimer
{

    @Override
    public void schedule(GPTimerTask task, int delay)
    {
    }

    @Override
    public void scheduleRepeating(GPTimerTask task, int delay, int interval)
    {
    }

    @Override
    public void cancel()
    {
    }
}
