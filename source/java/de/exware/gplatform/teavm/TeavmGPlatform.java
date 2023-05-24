package de.exware.gplatform.teavm;

import org.teavm.interop.AsyncCallback;
import org.teavm.jso.JSBody;
import org.teavm.jso.browser.Window;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.canvas.TextMetrics;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLDocument;

import de.exware.gplatform.GPDocument;
import de.exware.gplatform.GPStorage;
import de.exware.gplatform.GPWindow;
import de.exware.gplatform.internal.Ajax;
import de.exware.gplatform.style.CSSRule;
import de.exware.gplatform.style.GPStyleSheet;
import de.exware.gplatform.teavm.style.TeavmGPStyleSheet;
import de.exware.gplatform.teavm.timer.TeavmGPTimer;
import de.exware.gplatform.timer.GPTimer;

public class TeavmGPlatform extends de.exware.gplatform.GPlatform
{
    private static GPWindow window = new TeavmGPWindow();
    private static GPDocument document = new TeavmGPDocument();
    private static HTMLCanvasElement measureCanvas = (HTMLCanvasElement) HTMLDocument.current().createElement("canvas");
    private static final String SERVER_ROOT = "../../"; 
    
    private TeavmGPlatform()
    {}
    
    @Override
    public GPDocument getDocument()
    {
        return document;
    }

    @Override
    public GPWindow getWindow()
    {
        return window;
    }

    public static void init()
    {
        new TeavmGPlatform();
    }
    
    @Override
    public double stringWidth(String font, String text)
    {
    	double width = 0;
        CanvasRenderingContext2D g2d = (CanvasRenderingContext2D) measureCanvas.getContext("2d");
        g2d.setFont(font);
        TextMetrics tm = g2d.measureText(text);
        width = tm.getWidth();
        return width;
    }
    
    @JSBody(params = {}, script = "return window.devicePixelRatio;")
    private static native double native_getDevicePixelRatio();
    
    @Override
    public double getDevicePixelRatio()
    {
        return native_getDevicePixelRatio();
    }
    /**
     * @return returns SERVER_ROOT
     */
    @Override
    public String getModuleBaseForStaticFiles()
    {
    	return SERVER_ROOT;
    }
    
    /**
     * @return returns SERVER_ROOT
     */
    @Override
    public String getModuleBaseURL()
    {
    	return SERVER_ROOT;
    }

    @Override
    public GPStyleSheet getStyleSheet(int index)
    {	
    	GPStyleSheet gpStyleSheet = TeavmGPStyleSheet.get(index);
    	System.out.println("TeavmGPlatform: got TeavmGPStyleSheet");
		System.out.println("TeavmGPlatform: TeavmGPStyleSheet");
		CSSRule cssRule = gpStyleSheet.getCSSRule("test");
		System.out.println("TeavmGPlatform: getCssRule successfully run");
		cssRule.getProperty("test");
		System.out.println("TeavmGPlatform: getProperty successfully run");
        return gpStyleSheet;
    }

    @Override
    public int getStyleSheetCount()
    {
        return TeavmGPStyleSheet.count();
    }
    
    @Override
    public GPTimer createTimer()
    {
        return new TeavmGPTimer();
    }

	@Override
	public void alert(String text) 
	{
		Window.alert(text);
	}

	@Override
	public void loadData(String url, Callback callback) 
	{
		Ajax.get(url, new AsyncCallback<String>() {
			
			@Override
			public void error(Throwable e) {
				callback.onError(e);
			}
			
			@Override
			public void complete(String result) {
				callback.onSuccess(200, result);
			}
		});
	}
	
	@Override
	public GPStorage getLocalStorage() 
	{
		return new TeavmGPStorage();
	}
	
	public void clearSelection() {
		native_clearSelection();
	}
	
	@JSBody(params = {}, script = "(window.getSelection ? window.getSelection() : document.selection).empty();")
	private static native void native_clearSelection();
}

