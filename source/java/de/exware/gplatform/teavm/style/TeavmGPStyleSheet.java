package de.exware.gplatform.teavm.style;

import de.exware.gplatform.style.CSSRule;
import de.exware.gplatform.style.GPStyleSheet;

public final class TeavmGPStyleSheet implements GPStyleSheet
{

    @Override
    public CSSRule getCSSRule(String selector)
    {
        return null;
    }

    @Override
    public String getHref()
    {
        return null;
    }

    /**
     * Get the Stylesheet at index.
     * @param index
     * @return
     */
    public static GPStyleSheet get(int index)
    {
        return null;
    }

    /**
     * Return the number of Stylesheets
     * @return
     */
    public static int count()
    {
        return 0;
    }
}
