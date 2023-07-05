package de.exware.gplatform.teavm.element;

import org.teavm.jso.dom.html.HTMLElement;

import de.exware.gplatform.element.GPLabelElement;
import de.exware.gplatform.teavm.TeavmGPElement;

public class TeavmGPLabelElement extends TeavmGPElement implements GPLabelElement
{
    public TeavmGPLabelElement(HTMLElement nativeElement) 
    {
        super(nativeElement);
    }
}
