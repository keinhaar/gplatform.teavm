package de.exware.gplatform.teavm.element;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

import de.exware.gplatform.element.GPRangeElement;
import de.exware.gplatform.teavm.TeavmGPElement;

public class TeavmGPRangeElement extends TeavmGPElement implements GPRangeElement
{
    public TeavmGPRangeElement(HTMLElement nativeElement, String type) 
    {
        super(nativeElement);
        ((HTMLInputElement) getNativeElement()).setType(type);
    }
}
