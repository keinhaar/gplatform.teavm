package de.exware.gplatform.teavm.timer;

import org.teavm.jso.browser.TimerHandler;
import org.teavm.jso.browser.Window;

import de.exware.gplatform.timer.GPTimer;
import de.exware.gplatform.timer.GPTimerTask;

public class TeavmGPTimer implements GPTimer
{
    public TeavmGPTimer() {
    }
    
    @Override
    public void schedule(GPTimerTask task, int delay)
    {
        Window.setTimeout(new TimerHandler() {
            
            @Override
            public void onTimer() {
                task.execute();
            }
        }, delay);
    }

    @Override
    public void scheduleRepeating(GPTimerTask task, int delay, int interval)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
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
