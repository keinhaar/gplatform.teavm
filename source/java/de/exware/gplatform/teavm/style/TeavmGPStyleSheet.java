package de.exware.gplatform.teavm.style;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

import de.exware.gplatform.internal.Logger;
import de.exware.gplatform.style.CSSRule;
import de.exware.gplatform.style.GPStyleSheet;

public class TeavmGPStyleSheet implements GPStyleSheet {
    private static final Logger LOGGER = new Logger(TeavmGPStyleSheet.class);  
    private final JSObject nativeJsObject;
    
    public TeavmGPStyleSheet(JSObject nativeObject) {
        this.nativeJsObject = nativeObject;
    }
    
    @Override
    public CSSRule getCSSRule(String selector)
    {
        CSSRule rule = null;
        int c = getRuleCount();
        for(int i=0;i<c;i++)
        {
            CSSRule r = getCSSRule(i);
            String sel = r.getSelector();
            if(sel.equals(selector))
            {
                rule = r;
                break;
            }
        }
        return rule;
    }
    
    protected CSSRule getCSSRule(int i) {
        TeavmGPCSSRule teavmGPCSSRule = new TeavmGPCSSRule(native_getCSSRule(nativeJsObject, i));
        LOGGER.log(Logger.LEVEL_NATIVE, "getCSSRule -> success");
        return teavmGPCSSRule;
    }
    
    protected int getRuleCount() {
        int count = native_getRuleCount(nativeJsObject);
        LOGGER.log(Logger.LEVEL_NATIVE, "getRuleCount -> success");
        return count;
    }
    
    public static GPStyleSheet get(int index) {
        TeavmGPStyleSheet teavmGPStyleSheet = new TeavmGPStyleSheet(native_get(index));
        LOGGER.log(Logger.LEVEL_NATIVE, "get -> success");
        return teavmGPStyleSheet;
    }

    @Override
    public String getHref() {
        String href = native_getHref(nativeJsObject);
        LOGGER.log(Logger.LEVEL_NATIVE, "native_getHref -> success");
        return href;
    }

    public static int count() {
        int count = native_count();
        LOGGER.log(Logger.LEVEL_NATIVE, "native_count -> success");
        return count;
    }
    
    /************************ NATIVE **********************/
    @JSBody(params = {"nativeJSObject", "i"}, script = "return nativeJSObject.cssRules[i];")
    private static native JSObject native_getCSSRule(JSObject nativeJSObject, int i);
    
    @JSBody(params = {"nativeJSObject"}, script = "return nativeJSObject.cssRules.length;")
    private static native int native_getRuleCount(JSObject nativeJSObject);
    
    @JSBody(params = {"index"}, script = "return document.styleSheets[index];")
    private static native JSObject native_get(int index);
    
    @JSBody(params = {"nativeJSObject"}, script = "return nativeJSObject.href;")
    private static native String native_getHref(JSObject nativeJSObject);
    
    @JSBody(params = {}, script = "return document.styleSheets.length;")
    private static native int native_count();    
}