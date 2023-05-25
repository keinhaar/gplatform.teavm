package de.exware.gplatform.teavm;

import org.teavm.jso.dom.html.HTMLElement;

import de.exware.gplatform.GPStyle;

class TeavmGPStyle implements GPStyle
{
    private HTMLElement nativeElement;
    
    public TeavmGPStyle(HTMLElement nativeElement) {
        this.nativeElement = nativeElement;
    }
    
    @Override
    public void setProperty(String name, String value)
    {
        nativeElement.getStyle().setProperty(name, value);
    }

    @Override
    public String getProperty(String name)
    {
        return nativeElement.getStyle().getPropertyValue(name);
    }
}
