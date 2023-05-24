package de.exware.gplatform.teavm.style;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

import de.exware.gplatform.style.CSSRule;
import de.exware.gplatform.style.GPStyleSheet;

public class TeavmGPStyleSheet implements GPStyleSheet {
	
	private final JSObject nativeObject;
	
	public TeavmGPStyleSheet(JSObject nativeObject) {
		this.nativeObject = nativeObject;
	}
	
	public CSSRule getCSSRule(String selector) {
		System.out.println("test");
		CSSRule rule = null;
		int c = native_getRuleCount(nativeObject);
		for (int i = 0; i < c; i++) {
			TeavmGPCSSRule r = new TeavmGPCSSRule(native_getCSSRule(i, nativeObject));
			String sel = r.getSelector();
			if (sel.equals(selector)) {
				rule = r;
				break;
			}
		}
		System.out.println("CSSRule: " + rule);
		return rule;
	}
	
	@JSBody(params = {"i", "nativeObject"}, script = "return nativeObject.cssRules[i];")
	private static native JSObject native_getCSSRule(int i, JSObject nativeObject);
	
	@JSBody(params = {"nativeObject"}, script = " return nativeObject.cssRules.length;")
	private static native int  native_getRuleCount(JSObject nativeObject);
	
	@JSBody(params = {"nativeObject"}, script = "return nativeObject.href")
	private static native String native_getHref(JSObject nativeObject);

	@Override
	public String getHref() {
		return native_getHref(nativeObject);
	}

	@JSBody(params = {"index"}, script = "return document.styleSheets[index];")
	private static native JSObject native_get(int index);
	
	public static TeavmGPStyleSheet get(int index) {
		TeavmGPStyleSheet teavmGPStyleSheet = new TeavmGPStyleSheet(native_get(index)); 
		System.out.println("TeavmGPStyleSheet: trying to get cssrule");
		CSSRule teavmGPCSSRule = teavmGPStyleSheet.getCSSRule("test");
		System.out.println("TeavmGPStyleSheet: got getCssRule successfully");
		teavmGPCSSRule.getProperty("test");
		System.out.println("TeavmGPStyleSheet: getProperty successfully run");
		return teavmGPStyleSheet;
	}
	
	@JSBody(params = {}, script = "return document.styleSheets.length;")
	public static native int count();
}