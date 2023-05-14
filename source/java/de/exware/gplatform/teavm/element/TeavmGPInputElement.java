package de.exware.gplatform.teavm.element;

import de.exware.gplatform.element.GPInputElement;
import de.exware.gplatform.teavm.TeavmGPElement;

public class TeavmGPInputElement extends TeavmGPElement implements GPInputElement
{

    @Override
    public String getValue()
    {
        return null;
    }

    @Override
    public void setValue(String text)
    {
    }

    @Override
    public boolean isChecked()
    {
        return false;
    }

    @Override
    public void setChecked(boolean sel)
    {
    }

    @Override
    public void setDisabled(boolean b)
    {
    }

    @Override
    public void setDefaultChecked(boolean sel)
    {
    }

    @Override
    public void setSize(int length)
    {
    }

    @Override
    public int getSize()
    {
        return 0;
    }
}
