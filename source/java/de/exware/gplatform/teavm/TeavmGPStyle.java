package de.exware.gplatform.teavm;

import org.teavm.jso.dom.css.CSSStyleDeclaration;
import org.teavm.jso.dom.html.HTMLElement;

import de.exware.gplatform.GPStyle;
import de.exware.gplatform.internal.Logger;

class TeavmGPStyle implements GPStyle
{
    private static final Logger LOGGER = new Logger(TeavmGPStyle.class);
    private CSSStyleDeclaration nativeElementStyleDeclaration;

    public TeavmGPStyle(HTMLElement nativeElement)
    {
        this(nativeElement.getStyle());
    }

    public TeavmGPStyle(CSSStyleDeclaration nativeElementStyleDeclaration)
    {
        this.nativeElementStyleDeclaration = nativeElementStyleDeclaration;
        LOGGER.log(Logger.LEVEL_IMPLEMENTATION, "created instance");
    }

    @Override
    public void setProperty(String name, String value)
    {
        name = camelCaseToCSS(name);
        nativeElementStyleDeclaration.setProperty(name, value);
        LOGGER.log(Logger.LEVEL_IMPLEMENTATION, "setProperty -> executed with name: " + name + " value: " + value);
    }

    private String camelCaseToCSS(String name)
    {
        String css = "";
        for(int i=0;i<name.length();i++)
        {
            char c = name.charAt(i);
            if(Character.isUpperCase(c))
            {
                css += "-" + Character.toLowerCase(c);
            }
            else
            {
                css += c;
            }
        }
        return css;
    }

    @Override
    public String getProperty(String name)
    {
        name = camelCaseToCSS(name);
        String property = nativeElementStyleDeclaration.getPropertyValue(name);
        LOGGER.log(Logger.LEVEL_IMPLEMENTATION, "getProperty -> success for name: " + name + " property: " + property);
        return property;
    }

    @Override
    public void setWidthInPercent(double width)
    {
        setProperty("width", width + "%");
    }

    @Override
    public void setHeightInPercent(double height)
    {
        setProperty("height", height + "%");
    }

    @Override
    public void clearProperty(String name)
    {
        name = camelCaseToCSS(name);
        nativeElementStyleDeclaration.removeProperty(name);
    }
}
