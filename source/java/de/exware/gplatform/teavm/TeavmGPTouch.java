package de.exware.gplatform.teavm;

import de.exware.gplatform.event.GPTouch;
import de.exware.gplatform.internal.event.Touch;

public class TeavmGPTouch implements GPTouch
{
    private Touch touch;

    public TeavmGPTouch(Touch touch) 
    {
        this.touch = touch;
    }

    @Override
    public int getClientX()
    {
        return touch.getClientX();
    }

    @Override
    public int getClientY()
    {
        return touch.getClientY();
    }

}