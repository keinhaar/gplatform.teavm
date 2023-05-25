package de.exware.gplatform.teavm.style;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import de.exware.gplatform.internal.Logger;
import de.exware.gplatform.style.CSSRule;

public class TeavmGPCSSRule implements CSSRule {
    private static final Logger LOGGER = new Logger(TeavmGPCSSRule.class); 
    private JSObject nativeJSObject;
    
    public TeavmGPCSSRule(JSObject nativeJSObject) {
        this.nativeJSObject = nativeJSObject;
    }

    @Override
    public final int getInt(String name)
    {
        int i = 0;
        String str = getProperty(name);
        if(str != null && str.length() > 0)
        {
            int index = str.indexOf("px",0);
            if(index >= 0)
            {
                str = str.substring(0,index);
            }
            i = Integer.parseInt(str);            
        }
        else
        {
            throw new IllegalArgumentException("");
        }
        return i;
    }
    
    @Override
    public final void setPixel(String name, int value)
    {
        setPropertyValue(name, value + "px");
    }
    
    @Override
    public final String getColor(String name)
    {
        String str = getProperty(name);
        return str;
    }
    
    @Override
    public final String getProperty(String name)
    {
        String value = getPropertyValue(name);
        if("".equals(value))
        {
            value = null;
        }
        return value;
    }
    
    @Override
    public String getSelector() {
        String selector = native_getSelector(nativeJSObject);
        LOGGER.log(Logger.LEVEL_NATIVE, "getSelector -> success");
        return selector;
    }
    
    protected String getPropertyName(int i) {
        String name = native_getPropertyName(nativeJSObject, i);
        LOGGER.log(Logger.LEVEL_NATIVE, "getPropertyName -> success");
        return name;
    }

    protected String getPropertyValue(String name) {
        String value = native_getPropertyValue(nativeJSObject, name);
        LOGGER.log(Logger.LEVEL_NATIVE, "getPropertyValue -> success");
        return value;
    }

    protected void setPropertyValue(String name, String value) {
        native_setPropertyValue(nativeJSObject, name, value);
        LOGGER.log(Logger.LEVEL_NATIVE, "setPropertyValue -> success");
    }

    protected int getPropertyCount() {
        int count = native_getPropertyCount(nativeJSObject);
        LOGGER.log(Logger.LEVEL_NATIVE, "getPropertyCount -> success");
        return count;
    }
    
    /************************ NATIVE **********************/
    @JSBody(params = {"nativeJSObject"}, script = "return nativeJSObject.selectorText;")
    private static native String native_getSelector(JSObject nativeJSObject);
    
    @JSBody(params = {"nativeJSObject", "i"}, script = "return nativeJSObject.style.item(i);")
    private static native String native_getPropertyName(JSObject nativeJSObject, int i);
    
    @JSBody(params = {"nativeJSObject", "name"}, script = "return nativeJSObject.style.getPropertyValue(name);")
    private static native String native_getPropertyValue(JSObject nativeJSObject, String name);
    
    @JSBody(params = {"nativeJSObject", "name", "value"}, script = "return nativeJSObject.style.setProperty(name, value);")
    private static native void native_setPropertyValue(JSObject nativeJSObject, String name, String value);
    
    @JSBody(params = {"nativeJSObject"}, script = "return nativeJSObject.style.length;")
    private static native int native_getPropertyCount(JSObject nativeJSObject);
}