package de.exware.gplatform.teavm;

import org.teavm.jso.dom.html.HTMLElement;

import de.exware.gplatform.GPStyle;
import de.exware.gplatform.internal.Logger;

class TeavmGPStyle implements GPStyle
{
    private static final Logger LOGGER = new Logger(TeavmGPStyle.class);
    private HTMLElement nativeElement;
    
    public TeavmGPStyle(HTMLElement nativeElement) {
        this.nativeElement = nativeElement;
        LOGGER.log(Logger.LEVEL_IMPLEMENTATION, "created instance");
    }
    
    @Override
    public void setProperty(String name, String value)
    {
        nativeElement.getStyle().setProperty(name, value);
        LOGGER.log(Logger.LEVEL_IMPLEMENTATION, "setProperty -> executed with name: " + name + " value: " + value);
    }

    @Override
    public String getProperty(String name)
    {
        String property = nativeElement.getStyle().getPropertyValue(name);
        LOGGER.log(Logger.LEVEL_IMPLEMENTATION, "getProperty -> success for name: " + name + " property: " + property);
        return property;
    }
    
    public void setBackgroundColor(String col)
    {
        setProperty("background-color", col);
    }

    public String getBackgroundColor()
    {
        return getProperty("background-color");
    }

    public void setBorderColor(String color)
    {
        setProperty("border-color", color);
    }
    
    public void setBorderStyle(String style)
    {
        setProperty("border-style", style);
    }
    
    public void setBorderWidth(int width)
    {
        setProperty("border-width", width + "px");
    }
    
    public void setFontWeight(String weight)
    {
        setProperty("font-weight", weight);
    }
    
    public void setFontSize(float size)
    {
        setProperty("font-size", size + "px");
    }
    
    public void setZIndex(int i)
    {
        setProperty("z-index", "" + i);
    }
    
    public void setTextAlign(String textAlign)
    {
        setProperty("text-align", textAlign);
    }
}