package de.exware.gplatform.gwt;

import de.exware.gplatform.GPDocument;
import de.exware.gplatform.GPStorage;
import de.exware.gplatform.GPWindow;
import de.exware.gplatform.gwt.style.TeavmGPStyleSheet;
import de.exware.gplatform.gwt.timer.TeavmGPTimer;
import de.exware.gplatform.style.GPStyleSheet;
import de.exware.gplatform.timer.GPTimer;

public class TeavmGPlatform extends de.exware.gplatform.GPlatform
{
    private static GPWindow window = new TeavmGPWindow();
    private static GPDocument document;
    
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

    protected static void init()
    {
        new TeavmGPlatform();
    }
    
    @Override
    public double stringWidth(String font, String text)
    {
        return 10; //dummy;
    }

    @Override
    public double getDevicePixelRatio()
    {
        return 0;
    }

    @Override
    public String getModuleBaseForStaticFiles()
    {
        return null;
    }

    @Override
    public String getModuleBaseURL()
    {
        return null;
    }

    @Override
    public GPStyleSheet getStyleSheet(int index)
    {
        return TeavmGPStyleSheet.get(index);
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
//		Window.alert(text);
	}

	@Override
	public void loadData(String url, Callback callback) 
	{
	}
	
	@Override
	public GPStorage getLocalStorage() 
	{
		return new TeavmGPStorage();
	}
}

