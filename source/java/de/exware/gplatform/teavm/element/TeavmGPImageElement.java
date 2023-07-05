package de.exware.gplatform.teavm.element;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLImageElement;

import de.exware.gplatform.element.GPImageElement;
import de.exware.gplatform.teavm.TeavmGPElement;

public class TeavmGPImageElement extends TeavmGPElement implements GPImageElement
{

    public TeavmGPImageElement(HTMLElement nativeElement)
    {
        super(nativeElement);
    }

    @Override
    public void setSrc(String url)
    {
        ((HTMLImageElement) getNativeElement()).setSrc(url);
    }

    @Override
    public void setWidth(int iconWidth)
    {
        ((HTMLImageElement) getNativeElement()).setWidth(iconWidth);
    }

    @Override
    public void setHeight(int iconHeight)
    {
        ((HTMLImageElement) getNativeElement()).setHeight(iconHeight);
    }
}
