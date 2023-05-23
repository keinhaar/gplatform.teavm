package de.exware.gplatform.teavm.style;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

import de.exware.gplatform.style.CSSRule;
import de.exware.gplatform.style.GPStyleSheet;

public class TeavmGPStyleSheet implements GPStyleSheet, JSObject {
	public TeavmGPStyleSheet() {
	}
	
	@Override
	public CSSRule getCSSRule(String selector) {
		CSSRule rule = null;
		int c = getRuleCount(this);
		for (int i = 0; i < c; i++) {
			CSSRule r = (TeavmGPCSSRule) getCSSRule(i, this);
			String sel = r.getSelector();
			if (sel.equals(selector)) {
				rule = r;
				break;
			}
		}
		return rule;
	}
	
	@JSBody(params = {"i", "nativeObject"}, script = "return nativeObject.cssRules[i];")
	protected static native JSObject getCSSRule(int i, JSObject nativeObject); 
	
	@JSBody(params = { "nativeObject" }, script = " return nativeObject.cssRules.length;")
	protected static native int getRuleCount(JSObject nativeObject);
	
	@JSBody(params = { "nativeObject" }, script = "return nativeObject.href")
	private static native String native_getHref(JSObject nativeObject);

	@Override
	public String getHref() {
		return native_getHref(this);
	}

	@JSBody(params = { "index" }, script = "return document.styleSheets[index];")
	public static native JSObject get(int index);

	@JSBody(params = {}, script = "return document.styleSheets.length;")
	public static native int count();
}