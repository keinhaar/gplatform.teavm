package de.exware.gplatform.teavm.element;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLOptionElement;

import de.exware.gplatform.element.GPOptionElement;
import de.exware.gplatform.teavm.TeavmGPElement;

public class TeavmGPOptionElement extends TeavmGPElement implements GPOptionElement
{

    public TeavmGPOptionElement(HTMLElement nativeElement)
    {
        super(nativeElement);
    }

    @Override
    public void setValue(String t)
    {
        ((HTMLOptionElement) getNativeElement()).setValue(t);
    }
}
