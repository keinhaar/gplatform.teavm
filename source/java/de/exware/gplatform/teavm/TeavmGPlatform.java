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
import de.exware.gplatform.internal.Logger;
import de.exware.gplatform.style.GPStyleSheet;
import de.exware.gplatform.teavm.style.TeavmGPStyleSheet;
import de.exware.gplatform.teavm.timer.TeavmGPTimer;
import de.exware.gplatform.timer.GPTimer;

public class TeavmGPlatform extends de.exware.gplatform.GPlatform {
    public enum Browser {
        UNDEFINDED, OPERA, EDGE, CHROME, SAFARI, FIREFOX, INTERNET_EXPLORER
    }

    private static final Logger LOGGER = new Logger(TeavmGPlatform.class);
    private static GPWindow window = new TeavmGPWindow();
    private static GPDocument document = new TeavmGPDocument();
    private static HTMLCanvasElement measureCanvas = (HTMLCanvasElement) HTMLDocument.current().createElement("canvas");
    private static final String SERVER_ROOT = "./";
    private static Browser browser = null;

    private TeavmGPlatform() {
        super();
        browser = determineBrowser();
    }

    @Override
    public GPDocument getDocument() {
        return document;
    }

    @Override
    public GPWindow getWindow() {
        return window;
    }

    public static void init() {
        new TeavmGPlatform();
        System.setProperty("os.name", browser.name());
        System.setProperty("java.vm.name", "TEAVM");
    }

    @Override
    public double stringWidth(String font, String text) {
        double width = 0;
        CanvasRenderingContext2D g2d = (CanvasRenderingContext2D) measureCanvas.getContext("2d");
        g2d.setFont(font);
        TextMetrics tm = g2d.measureText(text);
        width = tm.getWidth();
        return width;
    }

    @Override
    public double getDevicePixelRatio() {
        double devicePixelRatio = native_getDevicePixelRatio();
        LOGGER.log(Logger.LEVEL_NATIVE, "getDevicePixelRatio() -> success");
        return devicePixelRatio;
    }

    /**
     * @return returns SERVER_ROOT
     */
    @Override
    public String getModuleBaseForStaticFiles() {
        return SERVER_ROOT;
    }

    /**
     * @return returns SERVER_ROOT
     */
    @Override
    public String getModuleBaseURL() {
        return SERVER_ROOT;
    }

    @Override
    public GPStyleSheet getStyleSheet(int index) {
        GPStyleSheet gpStyleSheet = TeavmGPStyleSheet.get(index);
        return gpStyleSheet;
    }

    @Override
    public int getStyleSheetCount() {
        return TeavmGPStyleSheet.count();
    }

    @Override
    public GPTimer createTimer() {
        return new TeavmGPTimer();
    }

    @Override
    public void alert(String text) {
        Window.alert(text);
    }

    @Override
    public void loadData(String url, Callback callback) {
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
    public GPStorage getLocalStorage() {
        return new TeavmGPStorage();
    }

    @Override
    public void clearSelection() {
        native_clearSelection();
        LOGGER.log(Logger.LEVEL_NATIVE, "clearSelection() -> success");
    }

    public Browser getBrowser() {
        return browser;
    }
    
    private static Browser determineBrowser() {
        switch (native_getBrowserName()) {
        case "Opera":
            return Browser.OPERA;
        case "Edge":
            return Browser.EDGE;
        case "Chrome":
            return Browser.CHROME;
        case "Safari":
            return Browser.SAFARI;
        case "Firefox":
            return Browser.FIREFOX;
        case "IE":
            return Browser.INTERNET_EXPLORER;
        case "unknown":
            return Browser.UNDEFINDED;
        default:
            return Browser.UNDEFINDED;
        }
    }

    /****************** NATIVE ********************/
    @JSBody(params = {}, script = "return window.devicePixelRatio;")
    private static native double native_getDevicePixelRatio();

    @JSBody(params = {}, script = "(window.getSelection ? window.getSelection() : document.selection).empty();")
    private static native void native_clearSelection();

    @JSBody(params = {}, script = "if((navigator.userAgent.indexOf(\"Opera\") || navigator.userAgent.indexOf('OPR')) != -1){return 'Opera';}\r\n"
            + "else if(navigator.userAgent.indexOf(\"Edg\") != -1){return 'Edge';}\r\n"
            + "else if(navigator.userAgent.indexOf(\"Chrome\") != -1){return 'Chrome';}\r\n"
            + "else if(navigator.userAgent.indexOf(\"Safari\") != -1){return 'Safari';}\r\n"
            + "else if(navigator.userAgent.indexOf(\"Firefox\") != -1 ){return 'Firefox';}\r\n"
            + "else if((navigator.userAgent.indexOf(\"MSIE\") != -1 ) || (!!document.documentMode == true ))/*IF IE > 10 */{return 'IE';}  \r\n"
            + "else {return 'unknown';}")
    private static native String native_getBrowserName();
}