package de.exware.gplatform.teavm;

import org.teavm.jso.JSBody;

import de.exware.gplatform.GPWindow;

public class TeavmGPWindow implements GPWindow
{
    TeavmGPWindow()
    {
    }

    @Override
    public int getClientWidth()
    {
        return native_getClientWidth();
    }

    @Override
    public int getClientHeight()
    {
        return native_getClientHeight();
    }
    
    @JSBody(params = {}, script = "return window.innerWidth;")
    private static native int native_getClientWidth();
    
    @JSBody(params = {}, script = "return window.innerHeight;")
    private static native int native_getClientHeight();
}
