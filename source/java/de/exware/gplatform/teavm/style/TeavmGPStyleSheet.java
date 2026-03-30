package de.exware.gplatform.teavm.style;

import java.util.HashMap;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

import de.exware.gplatform.log.Log;
import de.exware.gplatform.log.LogFactory;
import de.exware.gplatform.style.CSSRule;
import de.exware.gplatform.style.GPStyleSheet;

public class TeavmGPStyleSheet implements GPStyleSheet
{
    private static final Log LOG = LogFactory.getLog(TeavmGPStyleSheet.class);  
    private static int instanceCounter = 0;
    private final JSObject nativeJsObject;
    
    public TeavmGPStyleSheet(JSObject nativeObject)
    {
        this.nativeJsObject = nativeObject;
        LOG.debug("created instance " + instanceCounter++);
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
    
    private HashMap<Integer, TeavmGPCSSRule> teavmGPCSSRuleInstanceCache = new HashMap<>();
    
    protected CSSRule getCSSRule(int i)
    {
        TeavmGPCSSRule teavmGPCSSRule = teavmGPCSSRuleInstanceCache.get(i);
        if(teavmGPCSSRule == null)
        {
            teavmGPCSSRule = new TeavmGPCSSRule(native_getCSSRule(nativeJsObject, i));
            teavmGPCSSRuleInstanceCache.put(i, teavmGPCSSRule);
        }
        LOG.debug("getCSSRule -> success");
        return teavmGPCSSRule;  
    }
    
    protected int getRuleCount()
    {
        int count = native_getRuleCount(nativeJsObject);
        LOG.debug("getRuleCount -> success");
        return count;
    }
    
    private static HashMap<Integer, TeavmGPStyleSheet> teavmGPStyleSheetInstanceCache = new HashMap<>();
    
    public static GPStyleSheet get(int index)
    {
        TeavmGPStyleSheet teavmGPStyleSheet = teavmGPStyleSheetInstanceCache.get(index);
        if(teavmGPStyleSheet == null)
        {
            teavmGPStyleSheet = new TeavmGPStyleSheet(native_getStylesheet(index));
            teavmGPStyleSheetInstanceCache.put(index, teavmGPStyleSheet);
        }
        LOG.debug("get -> success");
        return teavmGPStyleSheet;
    }

    @Override
    public String getHref()
    {
        String href = native_getHref(nativeJsObject);
        LOG.debug("native_getHref -> success");
        return href;
    }

    public static int count()
    {
        int count = native_count();
        LOG.debug("native_count -> success");
        return count;
    }

    public static void add(String url)
    {
        native_add(url);
        LOG.debug("native_add -> success");
    }

    @Override
    public void setEnabled(boolean enabled) 
    {
        if(enabled)
        {
            native_setEnabled(nativeJsObject);
        }
        else
        {
            native_setDisabled(nativeJsObject);
        }
        LOG.debug("native_setEnabled -> success");
    }    
    
    /************************ NATIVE **********************/
    @JSBody(params = {"nativeJSObject", "i"}, script = "return nativeJSObject.cssRules[i];")
    private static native JSObject native_getCSSRule(JSObject nativeJSObject, int i);
    
    @JSBody(params = {"nativeJSObject"}, script = "return nativeJSObject.cssRules.length;")
    private static native int native_getRuleCount(JSObject nativeJSObject);
    
    @JSBody(params = {"index"}, script = "return document.styleSheets[index];")
    private static native JSObject native_getStylesheet(int index);
    
    @JSBody(params = {"nativeJSObject"}, script = "return nativeJSObject.href;")
    private static native String native_getHref(JSObject nativeJSObject);
    
    @JSBody(params = {}, script = "return document.styleSheets.length;")
    private static native int native_count();

    @JSBody(params = {"url"}, script = "var link = document.createElement('link');"
            + "link.rel = 'stylesheet';"
            + "link.type = 'text/css';"
            + "link.href = url;"
            + "document.head.appendChild(link);"
            )
    private static native void native_add(String url);

    @JSBody(params = {"nativeJSObject"}, script = "nativeJSObject.disabled = false;")
    private static native void native_setEnabled(JSObject nativeJSObject);

    @JSBody(params = {"nativeJSObject"}, script = "nativeJSObject.disabled = true;")
    private static native void native_setDisabled(JSObject nativeJSObject);
}
