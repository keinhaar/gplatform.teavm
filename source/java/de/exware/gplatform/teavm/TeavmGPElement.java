package de.exware.gplatform.teavm;

import java.util.List;

import de.exware.gplatform.GPElement;
import de.exware.gplatform.GPStyle;
import de.exware.gplatform.event.GPEvent.Type;
import de.exware.gplatform.event.GPEventListener;

public class TeavmGPElement implements GPElement
{
    protected TeavmGPElement()
    {
    }

    @Override
    public List<GPElement> getChildElements()
    {
        return null;
    }
    
    @Override
    public void setClassName(String className)
    {
    }

    @Override
    public void addClassName(String className)
    {
    }

    @Override
    public GPStyle getStyle()
    {
        return null;
    }

    @Override
    public GPElement cloneNode(boolean deep)
    {
        return null;
    }

    @Override
    public int getOffsetWidth()
    {
        return 0;
    }

    @Override
    public int getOffsetHeight()
    {
        return 0;
    }

    @Override
    public void removeChild(GPElement child)
    {
    }

    @Override
    public void appendChild(GPElement child)
    {
    }

    @Override
    public int getAbsoluteLeft()
    {
        return 0;
    }

    @Override
    public int getAbsoluteTop()
    {
        return 0;
    }

    @Override
    public void removeFromParent()
    {
    }

    @Override
    public void setEventListener(GPEventListener eventListener)
    {
    }

    @Override
    public void removeClassName(String classname)
    {
    }

    @Override
    public void enabledEvents( Type... eventTypes )
    {
    }

    @Override
    public Type[] getEnabledEvents()
    {
        return null;
    }

    @Override
    public GPElement getParentElement()
    {
        return null;
    }

    @Override
    public String getClassName()
    {
        return null;
    }

    @Override
    public void removeAllChildren()
    {
    }

    @Override
    public String getInnerText()
    {
        return null;
    }

    @Override
    public void setInnerHTML(String text)
    {
    }

    @Override
    public void insertFirst(GPElement element)
    {
    }

    @Override
    public String getInnerHTML()
    {
        return null;
    }

    @Override
    public void insertAfter(GPElement before, GPElement after)
    {
    }

    @Override
    public void setTabIndex(int i)
    {
    }

    @Override
    public void replaceChild(GPElement newChild, GPElement oldChild)
    {
    }

    @Override
    public GPElement getChild(int i)
    {
        return null;
    }

    @Override
    public void setInnerText(String str)
    {
    }

    @Override
    public void setAttribute(String name, String value)
    {
    }

    @Override
    public String getPropertyString(String name)
    {
        return null;
    }

    @Override
    public int getPropertyInt(String name)
    {
        return 0;
    }

    @Override
    public void setPropertyInt(String name, int value)
    {
    }

    @Override
    public void focus()
    {
    }
    
    @Override
    public int getClientWidth()
    {
        return 0;
    }
}
