package de.exware.gplatform.internal.event;

import org.teavm.jso.JSProperty;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.MouseEvent;

/**
 * javascript eventtype is wheel
 * @author home
 *
 */
public interface MouseWheelEvent extends MouseEvent {
    public static String MOUSEWHEELEVENT = "mousewheel";

    long MODE_DOM_DELTA_PIXEL = 0x00;
    long MODE_DOM_DELTA_LINE = 0x01;
    long MODE_DOM_DELTA_PAGE = 0x02;
    
    
    @JSProperty
    double getDeltaX();
    
    @JSProperty
    double getDeltaY();
    
    @JSProperty
    double getDeltZ();
    
    
    /**
     * Returns an unsigned long representing the unit of the delta* values' scroll amount. Permitted values are:
     * WheelEvent.DOM_DELTA_PIXEL
     * WheelEvent.DOM_DELTA_LINE
     * WheelEvent.DOM_DELTA_PAGE
     * @return
     */
    @JSProperty
    long getDeltaMode();
}