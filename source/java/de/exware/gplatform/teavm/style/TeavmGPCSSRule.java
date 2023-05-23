package de.exware.gplatform.teavm.style;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

import de.exware.gplatform.style.CSSRule;

public abstract class TeavmGPCSSRule implements CSSRule, JSObject {
	
	protected TeavmGPCSSRule() {
	}

	public final int getInt(String name) {
		int i = 0;
		String str = getProperty(name);
		if (str != null && str.length() > 0) {
			int index = str.indexOf("px", 0);
			if (index >= 0) {
				str = str.substring(0, index);
			}
			i = Integer.parseInt(str);
		} else {
			throw new IllegalArgumentException("TeavmGPCSSRule.getInt(String name) illeage argument");
		}
		return i;
	}

	public final void setPixel(String name, int value) {
		String str = getProperty(name);
		setPropertyValue(name, value + "px");
	}

	@Override
    public final String getColor(String name)
    {
        String str = getProperty(name);
        return str;
    }

	public final String getProperty(String name) {
		String value = getPropertyValue(name);
		if ("".equals(value)) {
			value = null;
		}
		return value;
	}
	
	@JSProperty
	public native String getSelector();
	
	@JSBody(params = {"jsObject", "i"}, script = "return jsObject.style.item(i);")
	private static native String getPropertyName(JSObject jsObject, int i);
	
	protected String getPropertyName(int i) {
		return getPropertyName(this, i);
	}
	
	@JSBody(params = {"jsObject", "name"}, script = "return jsObject.style.getPropertyValue(name);")
	private static native String getPropertyValue(JSObject jsObject, String name);

	protected String getPropertyValue(String name) {
		return getPropertyValue(this, name);
	}
	
	@JSBody(params = {"jsObject", "name", "value"}, script = "return jsObject.style.setProperty(name, value);")
	private native void setPropertyValue(JSObject jsObject, String name, String value);
	
	protected void setPropertyValue(String name, String value) {
		setPropertyValue(this, name, value);
	}
	
	@JSBody(params = {"jsObject"}, script = "return jsObject.style.length;")
	private native int getPropertyCount(JSObject jsObject);
	
	protected int getPropertyCount() {
		return getPropertyCount(this);
	}

}