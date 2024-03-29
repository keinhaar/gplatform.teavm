package de.exware.gplatform.teavm;

import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;

import de.exware.gplatform.GPElement;
import de.exware.gplatform.element.GPCanvasElement;
import de.exware.gplatform.element.GPFileInputElement;
import de.exware.gplatform.element.GPFormElement;
import de.exware.gplatform.element.GPImageElement;
import de.exware.gplatform.element.GPInputElement;
import de.exware.gplatform.element.GPLabelElement;
import de.exware.gplatform.element.GPOptionElement;
import de.exware.gplatform.element.GPRangeElement;
import de.exware.gplatform.element.GPSelectElement;
import de.exware.gplatform.element.GPTextAreaElement;
import de.exware.gplatform.teavm.element.TeavmGPCanvasElement;
import de.exware.gplatform.teavm.element.TeavmGPFileInputElement;
import de.exware.gplatform.teavm.element.TeavmGPFormElement;
import de.exware.gplatform.teavm.element.TeavmGPImageElement;
import de.exware.gplatform.teavm.element.TeavmGPInputElement;
import de.exware.gplatform.teavm.element.TeavmGPLabelElement;
import de.exware.gplatform.teavm.element.TeavmGPOptionElement;
import de.exware.gplatform.teavm.element.TeavmGPRangeElement;
import de.exware.gplatform.teavm.element.TeavmGPSelectElement;
import de.exware.gplatform.teavm.element.TeavmGPTextAreaElement;

public class TeavmGPDocument implements de.exware.gplatform.GPDocument
{

    @Override
    public GPElement getElementById(String elementId)
    {
        return new TeavmGPElement(HTMLDocument.current().getElementById(elementId));
    }

    @Override
    public GPElement createElement(String tagName)
    {
        return new TeavmGPElement(createNativeElement(tagName));
    }

    @Override
    public GPCanvasElement createCanvasElement()
    {
        return new TeavmGPCanvasElement(createNativeElement("canvas"));
    }
    //TODO: cache it if necessary
    @Override
    public GPElement getBody()
    {
        return new TeavmGPElement(HTMLDocument.current().getBody());
    }

    @Override
    public GPImageElement createImageElement()
    {
        return new TeavmGPImageElement(createNativeElement("img"));
    }

    @Override
    public GPInputElement createCheckInputElement()
    {
        return new TeavmGPInputElement(createNativeElement("input"), "checkbox");
    }

    @Override
    public GPSelectElement createSelectElement()
    {
        return new TeavmGPSelectElement(createNativeElement("select"));
    }

    @Override
    public GPOptionElement createOptionElement()
    {
        return new TeavmGPOptionElement(createNativeElement("option"));
    }

    @Override
    public GPInputElement createRadioInputElement(String string)
    {
        return new TeavmGPInputElement(createNativeElement("input"), "radio");
    }

    @Override
    public GPTextAreaElement createTextAreaElement()
    {
        return new TeavmGPTextAreaElement(createNativeElement("textarea"));
    }

    @Override
    public GPInputElement createTextInputElement()
    {
        return new TeavmGPInputElement(createNativeElement("input"), "text");
    }

    @Override
    public GPInputElement createPasswordInputElement()
    {
        return new TeavmGPInputElement(createNativeElement("input"), "password");
    }
    
    public static int elementID = 0;
    
    private static HTMLElement createNativeElement(String name) {
        HTMLElement element = HTMLDocument.current().createElement(name);
        element.setAttribute("elementID", elementID++ + "");
        return element;
    }

    @Override
    public GPRangeElement createRangeElement() 
    {
        return new TeavmGPRangeElement(createNativeElement("input"), "range");
    }

    @Override
    public GPFormElement createFormElement() 
    {
        return new TeavmGPFormElement(createNativeElement("form"));
    }

    @Override
    public GPLabelElement createLabelElement() 
    {
        return new TeavmGPLabelElement(createNativeElement("label"));
    }

    @Override
    public GPFileInputElement createFileInputElement() 
    {
        return new TeavmGPFileInputElement(createNativeElement("input"));
    }

    @Override
    public GPElement getDocumentElement() {
        return new TeavmGPElement(HTMLDocument.current().getDocumentElement());
    }
}
