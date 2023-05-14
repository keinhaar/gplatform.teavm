package de.exware.gplatform.gwt.event;

import java.util.List;

import de.exware.gplatform.event.GPEvent;
import de.exware.gplatform.event.GPTouch;

public class TeavmGPEvent
    implements GPEvent
{
    private Type type;

    @Override
    public Type getType()
    {
        return null;
    }

    @Override
    public void preventDefault()
    {
    }

    @Override
    public void stopPropagation()
    {
    }

    @Override
    public boolean getShiftKey()
    {
        return false;
    }

    @Override
    public boolean getAltKey()
    {
        return false;
    }

    @Override
    public boolean getCtrlKey()
    {
        return false;
    }

    @Override
    public int getClientX()
    {
        return 0;
    }

    @Override
    public int getClientY()
    {
        return 0;
    }

    @Override
    public Button getButton()
    {
        return null;
    }

    @Override
    public int getKeyCode()
    {
        return 0;
    }

    @Override
    public int getCharCode()
    {
        return 0;
    }

    @Override
    public int getMouseWheelVelocityY()
    {
        return 0;
    }

    @Override
    public List<GPTouch> getTouches()
    {
        return null;
    }

    @Override
    public List<GPTouch> getChangedTouches()
    {
        return null;
    }
}
