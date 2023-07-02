package de.exware.gplatform.internal;

import java.io.IOException;

import org.teavm.interop.AsyncCallback;
import org.teavm.jso.ajax.ReadyStateChangeHandler;
import org.teavm.jso.ajax.XMLHttpRequest;

public class Ajax {
    
    public static void POST(String url, String urlencodedData, AsyncCallback<String> callback) {
        XMLHttpRequest xhr = XMLHttpRequest.create();
        xhr.open("post", url);
        
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        
        xhr.setOnReadyStateChange(new ReadyStateChangeHandler() {
            @Override
            public void stateChanged() {
                if (xhr.getReadyState() != XMLHttpRequest.DONE) {
                    return;
                }

                int statusGroup = xhr.getStatus() / 100;
                if (statusGroup != 2 && statusGroup != 3) {
                    callback.error(new IOException("HTTP status: " + xhr.getStatus() + " " + xhr.getStatusText()));
                } else {
                    callback.complete(xhr.getResponseText());
                }
            }
        });
        
        xhr.send(urlencodedData);
    }
    
    public static void GET(String url, String urlencodedData, AsyncCallback<String> callback) {
        XMLHttpRequest xhr = XMLHttpRequest.create();
        xhr.open("get", url + "?" + urlencodedData);
        
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        
        xhr.setOnReadyStateChange(new ReadyStateChangeHandler() {
            @Override
            public void stateChanged() {
                if (xhr.getReadyState() != XMLHttpRequest.DONE) {
                    return;
                }

                int statusGroup = xhr.getStatus() / 100;
                if (statusGroup != 2 && statusGroup != 3) {
                    callback.error(new IOException("HTTP status: " + xhr.getStatus() + " " + xhr.getStatusText()));
                } else {
                    callback.complete(xhr.getResponseText());
                }
            }
        });
        
        xhr.send();
    }
    
    @Deprecated
    public static void get(String url, AsyncCallback<String> callback) {
        GET(url, "", callback);
    }
}