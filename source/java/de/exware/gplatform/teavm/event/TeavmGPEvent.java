package de.exware.gplatform.teavm.event;

import java.util.List;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.events.MouseEvent;

import de.exware.gplatform.event.GPEvent;
import de.exware.gplatform.event.GPTouch;
import de.exware.gplatform.internal.MouseWheelEvent;

public class TeavmGPEvent
    implements GPEvent
{
	
	private Type type;
	
	private Event nativeEvent;
	
	public TeavmGPEvent(Type type, Event nativeEvent) {
		this.type = type;
		this.nativeEvent = nativeEvent;
	}

    @Override
    public Type getType()
    {
        return type;
    }

    @Override
    public void preventDefault()
    {
    	nativeEvent.preventDefault();
    }

    @Override
    public void stopPropagation()
    {
    	nativeEvent.stopPropagation();
    }

    @Override
    public boolean getShiftKey()
    {
        return ((MouseEvent) nativeEvent).getShiftKey();
    }

    @Override
    public boolean getAltKey()
    {
        return ((MouseEvent) nativeEvent).getAltKey();
    }

    @Override
    public boolean getCtrlKey()
    {
        return ((MouseEvent) nativeEvent).getCtrlKey();
    }

    @Override
    public int getClientX()
    {
        return ((MouseEvent) nativeEvent).getClientX();
    }

    @Override
    public int getClientY()
    {
        return ((MouseEvent) nativeEvent).getClientY();
    }

    @Override
    public Button getButton()
    {
    	short button = ((MouseEvent) nativeEvent).getButton();
    	if(button == MouseEvent.LEFT_BUTTON)
        	return Button.BUTTON_LEFT;
    	if(button == MouseEvent.RIGHT_BUTTON)
        	return Button.BUTTON_RIGHT;
    	if(button == MouseEvent.MIDDLE_BUTTON)
        	return Button.BUTTON_MIDDLE;
		return null; 
    }

    @Override
    public int getKeyCode()
    {
        return ((KeyboardEvent) nativeEvent).getKeyCode();
    }

    @Override
    public int getCharCode()
    {
        return ((KeyboardEvent) nativeEvent).getCharCode();
    }

    @Override
    public int getMouseWheelVelocityY()
    {
        return (int) (((MouseWheelEvent) nativeEvent).getDeltaY() * 10); //TODO: proper implementation
    }

    @Override
    public List<GPTouch> getTouches()
    {
    	throw new RuntimeException("TeavmGPEvent.getTouches() is unsupported."); //TODO: proper implementation
    }

    @Override
    public List<GPTouch> getChangedTouches()
    {
    	throw new RuntimeException("TeavmGPEvent.getChangedTouches() is unsupported."); //TODO: proper implementation
    }
}
