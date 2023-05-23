package de.exware.gplatform.teavm;

import org.teavm.jso.browser.Window;

import de.exware.gplatform.GPStorage;

public class TeavmGPStorage implements GPStorage 
{
	public TeavmGPStorage() 
	{
	}
	
	@Override
	public void setItem(String key, String value)
	{
		Window.current().getLocalStorage().setItem(key, value);
	}

	@Override
	public String getItem(String key) 
	{
        return Window.current().getLocalStorage().getItem(key);
	}
}
