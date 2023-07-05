package de.exware.gplatform.teavm.element;

import org.teavm.jso.dom.html.HTMLElement;

import de.exware.gplatform.element.GPFormElement;
import de.exware.gplatform.teavm.TeavmGPElement;

public class TeavmGPFormElement extends TeavmGPElement implements GPFormElement
{
    public TeavmGPFormElement(HTMLElement nativeElement) 
    {
        super(nativeElement);
    }
}
