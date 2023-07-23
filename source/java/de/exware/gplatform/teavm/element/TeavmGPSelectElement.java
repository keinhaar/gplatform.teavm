package de.exware.gplatform.teavm.element;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLSelectElement;

import de.exware.gplatform.element.GPOptionElement;
import de.exware.gplatform.element.GPSelectElement;
import de.exware.gplatform.teavm.TeavmGPElement;

public class TeavmGPSelectElement extends TeavmGPElement implements GPSelectElement
{

    public TeavmGPSelectElement(HTMLElement nativeElement) 
    {
        super(nativeElement);
    }

    @Override
    public int getSelectedIndex()
    {
        return ((HTMLSelectElement) getNativeElement()).getSelectedIndex();
    }

    @Override
    public void setSelectedIndex(int i)
    {
        ((HTMLSelectElement) getNativeElement()).setSelectedIndex(i);
    }

    @Override
    public void clear()
    {
        ((HTMLSelectElement) getNativeElement()).clear();
    }

    @Override
    public void add(GPOptionElement opt)
    {
        ((HTMLSelectElement) getNativeElement()).appendChild(((TeavmGPOptionElement) opt).getNativeElement());
    }

    @Override
    public void setDisabled(boolean b)
    {
        ((HTMLSelectElement) getNativeElement()).setDisabled(b);
    }
}
