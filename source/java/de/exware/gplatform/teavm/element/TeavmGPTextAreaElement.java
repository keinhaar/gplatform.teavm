package de.exware.gplatform.teavm.element;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLTextAreaElement;

import de.exware.gplatform.element.GPTextAreaElement;
import de.exware.gplatform.teavm.TeavmGPElement;

public class TeavmGPTextAreaElement extends TeavmGPElement 
    implements GPTextAreaElement
{

    public TeavmGPTextAreaElement(HTMLElement nativeElement)
    {
        super(nativeElement);
    }

    @Override
    public void setCols(int columns)
    {
        ((HTMLTextAreaElement) getNativeElement()).setCols(columns);
    }

    @Override
    public void setRows(int rows)
    {
        ((HTMLTextAreaElement) getNativeElement()).setRows(rows);
    }

    @Override
    public void setDisabled(boolean b)
    {
        ((HTMLTextAreaElement) getNativeElement()).setDisabled(b);
    }

    @Override
    public String getValue()
    {
        return ((HTMLTextAreaElement) getNativeElement()).getValue();
    }

    @Override
    public void setValue(String text)
    {
        ((HTMLTextAreaElement) getNativeElement()).setValue(text);
    }

    @Override
    public int getCols()
    {
        return ((HTMLTextAreaElement) getNativeElement()).getCols();
    }

    @Override
    public int getRows()
    {
        return ((HTMLTextAreaElement) getNativeElement()).getRows();
    }
}
