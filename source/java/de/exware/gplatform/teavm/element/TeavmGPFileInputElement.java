package de.exware.gplatform.teavm.element;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

import de.exware.gplatform.element.GPFileInputElement;
import de.exware.gplatform.teavm.TeavmGPElement;

public class TeavmGPFileInputElement extends TeavmGPElement implements GPFileInputElement
{
    public TeavmGPFileInputElement(HTMLElement nativeElement) 
    {
        super(nativeElement);
        ((HTMLInputElement) getNativeElement()).setType("file");
    }
}
