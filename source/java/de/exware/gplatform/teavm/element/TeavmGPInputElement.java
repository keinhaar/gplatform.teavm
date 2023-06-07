package de.exware.gplatform.teavm.element;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

import de.exware.gplatform.element.GPInputElement;
import de.exware.gplatform.teavm.TeavmGPElement;

public class TeavmGPInputElement extends TeavmGPElement implements GPInputElement
{
    private static final String CHECKED = "checked";
    
    public TeavmGPInputElement(HTMLElement nativeElement, String type) {
        super(nativeElement);
        ((HTMLInputElement) getNativeElement()).setType(type);
    }

    @Override
    public String getValue()
    {
        return ((HTMLInputElement) getNativeElement()).getValue();
    }

    @Override
    public void setValue(String text)
    {
        ((HTMLInputElement) getNativeElement()).setValue(text);
    }

    @Override
    public boolean isChecked()
    {
        return ((HTMLInputElement) getNativeElement()).isChecked();
    }

    @Override
    public void setChecked(boolean sel)
    {
        ((HTMLInputElement) getNativeElement()).setChecked(sel);
    }

    @Override
    public void setDisabled(boolean b)
    {
        ((HTMLInputElement) getNativeElement()).setDisabled(b);
    }
    
    @Override
    public void setDefaultChecked(boolean sel)
    {
        HTMLElement nativeElement = getNativeElement();
        if(sel) {
            nativeElement.setAttribute(CHECKED, CHECKED);
        } else {
            if(nativeElement.hasAttribute(CHECKED)) {
                nativeElement.removeAttribute(CHECKED);
            }
        }
        //((HTMLInputElement) getNativeElement()).setChecked(sel); //TODO: proper fix
    }

    @Override
    public void setSize(int length)
    {
        ((HTMLInputElement) getNativeElement()).setSize(length);
    }

    @Override
    public int getSize()
    {
        return ((HTMLInputElement) getNativeElement()).getSize();
    }
}
