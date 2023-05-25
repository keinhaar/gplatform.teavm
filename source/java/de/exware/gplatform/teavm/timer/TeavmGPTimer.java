package de.exware.gplatform.teavm.timer;

import de.exware.gplatform.timer.GPTimer;
import de.exware.gplatform.timer.GPTimerTask;

public class TeavmGPTimer implements GPTimer
{

    @Override
    public void schedule(GPTimerTask task, int delay)
    {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(delay);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                task.execute();
            }
        });
        thread.start();
    }

    @Override
    public void scheduleRepeating(GPTimerTask task, int delay, int interval)
    {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(delay);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                while(!task.isCanceled()) {
                    task.execute();
                    try {
                        Thread.sleep(interval);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void cancel()
    {
        
    }
}
