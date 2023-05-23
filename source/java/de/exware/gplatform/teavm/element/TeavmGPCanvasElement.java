package de.exware.gplatform.teavm.element;

import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLElement;

import de.exware.gplatform.element.GPCanvasElement;
import de.exware.gplatform.element.GPContext2d;
import de.exware.gplatform.teavm.TeavmGPElement;

public class TeavmGPCanvasElement extends TeavmGPElement 
    implements GPCanvasElement
{

    public TeavmGPCanvasElement(HTMLElement nativeElement) {
		super(nativeElement);
	}

	@Override
    public void setWidth(int width)
    {
		((HTMLCanvasElement) getNativeElement()).setWidth(width);
    }

    @Override
    public void setHeight(int height)
    {
    	((HTMLCanvasElement) getNativeElement()).setHeight(height);
    }

    @Override
    public GPContext2d getContext2d()
    {
    	HTMLCanvasElement canvasElement = (HTMLCanvasElement) getNativeElement();
    	CanvasRenderingContext2D canvasRenderingContext2D = (CanvasRenderingContext2D) canvasElement.getContext("2d");
        return new TeavmGPContext2d(canvasRenderingContext2D);
    }
}
