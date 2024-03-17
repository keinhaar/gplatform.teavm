package de.exware.gplatform.teavm;

import org.teavm.jso.JSBody;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLElement;

import de.exware.gplatform.GPElement;
import de.exware.gplatform.GPWindow;

public class TeavmGPWindow implements GPWindow
{
    TeavmGPWindow()
    {
    }

    @Override
    public int getClientWidth()
    {
        int teavmInnerWidth = Window.current().getInnerWidth();
        int nativeInnerWidth = native_getClientWidth();
        if(teavmInnerWidth != nativeInnerWidth) {
            System.out.println("Window.current().getInnerWidth(): " + teavmInnerWidth + "\n native_getClientWidth():" + nativeInnerWidth);
        }
        return nativeInnerWidth;
    }

    @Override
    public int getClientHeight()
    {
        int teavmInnerHeight = Window.current().getInnerHeight();
        int nativeInnerHeight = native_getClientHeight();
        if(teavmInnerHeight != nativeInnerHeight) {
            System.out.println("Window.current().getInnerHeight(): " + teavmInnerHeight + "\n native_getClientHeight():" + nativeInnerHeight);
        }
        return nativeInnerHeight;
    }

    @Override
    public String getComputedStyleProperty(GPElement element, String pseudoElement)
    {
        TeavmGPElement tel = (TeavmGPElement) element;
        HTMLElement nativeElement = tel.getNativeElement();
        String prop = native_getComputedStyle(nativeElement, pseudoElement);
        return prop;
    }

    @JSBody(params = {"nativeElement", "pseudoElement"}, script = "return window.getComputedStyle(nativeElement, null).getPropertyValue(pseudoElement);")
    private static native String native_getComputedStyle(HTMLElement nativeElement, String pseudoElement);

    @JSBody(params = {}, script = "return window.innerWidth;")
    private static native int native_getClientWidth();

    @JSBody(params = {}, script = "return window.innerHeight;")
    private static native int native_getClientHeight();
}
