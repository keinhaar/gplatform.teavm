package de.exware.gplatform.teavm;

import java.util.ArrayList;
import java.util.List;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.xml.Node;
import org.teavm.jso.dom.xml.NodeList;

import de.exware.gplatform.GPElement;
import de.exware.gplatform.GPStyle;
import de.exware.gplatform.event.GPEvent.Type;
import de.exware.gplatform.event.GPEventListener;
import de.exware.gplatform.internal.Logger;
import de.exware.gplatform.internal.MouseWheelEvent;
import de.exware.gplatform.teavm.event.TeavmGPEvent;

public class TeavmGPElement implements GPElement
{
    private static final Logger LOGGER = Logger.getInstance(TeavmGPElement.class);
    
    private HTMLElement nativeElement;
    
    private GPEventListener gpEventListener;
    
    private Type[] enabledEvents;
    
    public TeavmGPElement(HTMLElement nativeElement) {
        this.nativeElement = nativeElement;
    }

    @Override
    public List<GPElement> getChildElements()
    {
        NodeList<Node> childNodeList = nativeElement.getChildNodes();
        
        List<GPElement> gpElements = new ArrayList<>();
        
        for(int i=0; i<childNodeList.getLength(); i++) {
            HTMLElement element = (HTMLElement) childNodeList.get(i);
            TeavmGPElement teavmGPElement = new TeavmGPElement(element);
            gpElements.add(teavmGPElement);
        }
        return gpElements;
    }
    
    @Override
    public void setClassName(String className)
    {
        nativeElement.setClassName(className);
    }

    @Override
    public void addClassName(String className)
    {
        nativeElement.getClassList().add(className);
    }
    
    private TeavmGPStyle teavmGPStyleCache = null;
    
    @Override
    public GPStyle getStyle()
    {
        if(teavmGPStyleCache == null)
            teavmGPStyleCache = new TeavmGPStyle(nativeElement);
        return teavmGPStyleCache;
    }

    @Override
    public GPElement cloneNode(boolean deep)
    {
        HTMLElement clonedElement = (HTMLElement) nativeElement.cloneNode(deep);
        return new TeavmGPElement(clonedElement);
    }

    @Override
    public int getOffsetWidth()
    {
        return nativeElement.getOffsetWidth();
    }

    @Override
    public int getOffsetHeight()
    {
        return nativeElement.getOffsetHeight();
    }

    @Override
    public void removeChild(GPElement child)
    {
        nativeElement.removeChild(getHtmlElementFromGPElement(child));
    }

    @Override
    public void appendChild(GPElement child)
    {
        nativeElement.appendChild(getHtmlElementFromGPElement(child));
    }

    @Override
    public int getAbsoluteLeft()
    {
        int left = (int) native_getAbsoluteLeft(nativeElement);
        LOGGER.log(Logger.LEVEL_IMPLEMENTATION, "getAbsoluteLeft(): " + left);
        return left;
    }

    @Override
    public int getAbsoluteTop()
    {
        int top = (int) native_getAbsoluteTop(nativeElement);
        LOGGER.log(Logger.LEVEL_IMPLEMENTATION, "getAbsoluteTop(): " + top);
        return top;
    }

    @Override
    public void removeFromParent()
    {
        nativeElement.delete();
    }

    @Override
    public void setEventListener(GPEventListener gpEventListener)
    {
        this.gpEventListener = gpEventListener;
    }

    @Override
    public void removeClassName(String classname)
    {
        nativeElement.getClassList().remove(classname);
    }
    
    private class EventListenerContainer {
        final String eventName;
        final EventListener<? extends Event> eventListener;
        final Type eventType;
        
        public EventListenerContainer(String eventName, Type eventType, EventListener<? extends Event> eventListener) {
            this.eventName = eventName;
            this.eventListener = eventListener;
            this.eventType = eventType;
        }
    }
    
    private ArrayList<EventListenerContainer> enabledEventListeners = new ArrayList<>();
    
    @Override
    public void enabledEvents(Type... eventTypes)
    {
        //remove all the previous enabled event listeners
        //if eventTypes is null
        if(eventTypes == null) {
            for(EventListenerContainer eventListenerContainer : enabledEventListeners) {
                nativeElement.removeEventListener(eventListenerContainer.eventName, eventListenerContainer.eventListener);
            }
            return;
        }
        
        //collect the events that need activation
        eventCollectionLoop: for(Type eventType : eventTypes) {
            for(EventListenerContainer eventListenerContainer : enabledEventListeners)
                if(eventListenerContainer.eventType.equals(eventType))
                    continue eventCollectionLoop; //ensure to enable one eventType only ones
            
            switch(eventType) {
                case ONCHANGE:
                    enabledEventListeners.add(
                            new EventListenerContainer("change", eventType, new EventListener<Event>() {
                                @Override
                                public void handleEvent(Event event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONCLICK:
                    LOGGER.log(Logger.LEVEL_IMPLEMENTATION, "enabledEvents onclick event for element: " + getNativeElement().getAttribute("elementid"));
                    enabledEventListeners.add(
                            new EventListenerContainer(MouseEvent.CLICK, eventType, new EventListener<MouseEvent>() {
                                @Override
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONCONTEXTMENU:
                    enabledEventListeners.add(
                            new EventListenerContainer("contextmenu", eventType, new EventListener<MouseEvent>() {
                                @Override
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONDBLCLICK:
                    enabledEventListeners.add(
                            new EventListenerContainer("dblclick", eventType, new EventListener<MouseEvent>() {
                                @Override
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONERROR:
                    enabledEventListeners.add(
                            new EventListenerContainer("error", eventType, new EventListener<Event>() {
                                @Override
                                public void handleEvent(Event event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONFOCUS:
                    enabledEventListeners.add(
                            new EventListenerContainer("focus", eventType, new EventListener<Event>() {
                                @Override
                                public void handleEvent(Event event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONKEYDOWN:
                    enabledEventListeners.add(
                            new EventListenerContainer("keydown", eventType, new EventListener<KeyboardEvent>() {
                                @Override
                                public void handleEvent(KeyboardEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONKEYPRESS:
                    enabledEventListeners.add(
                            new EventListenerContainer("keypress", eventType, new EventListener<KeyboardEvent>() {
                                @Override
                                public void handleEvent(KeyboardEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONKEYUP:
                    enabledEventListeners.add(
                            new EventListenerContainer("keyup", eventType, new EventListener<KeyboardEvent>() {
                                @Override
                                public void handleEvent(KeyboardEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONLOAD:
                    enabledEventListeners.add(
                            new EventListenerContainer("load", eventType, new EventListener<Event>() {
                                @Override
                                public void handleEvent(Event event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONMOUSEDOWN:
                    enabledEventListeners.add(
                            new EventListenerContainer(MouseEvent.MOUSEDOWN, eventType, new EventListener<MouseEvent>() {
                                @Override
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONMOUSEMOVE:
                    enabledEventListeners.add(
                            new EventListenerContainer(MouseEvent.MOUSEMOVE, eventType, new EventListener<MouseEvent>() {
                                @Override
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONMOUSEOUT:
                    enabledEventListeners.add(
                            new EventListenerContainer(MouseEvent.MOUSEOUT, eventType, new EventListener<MouseEvent>() {
                                @Override
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONMOUSEOVER:
                    enabledEventListeners.add(
                            new EventListenerContainer(MouseEvent.MOUSEOVER, eventType, new EventListener<MouseEvent>() {
                                @Override
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONMOUSEUP:
                    enabledEventListeners.add(
                            new EventListenerContainer(MouseEvent.MOUSEUP, eventType, new EventListener<MouseEvent>() {
                                @Override
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONMOUSEWHEEL:
                    enabledEventListeners.add(
                            new EventListenerContainer("mousewheel", eventType, new EventListener<MouseWheelEvent>() {
                                @Override
                                public void handleEvent(MouseWheelEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONTOUCHEND:
                    throw new Error("Unsupported event: \"ontouchend\"");
                case ONTOUCHMOVE:
                    throw new Error("Unsupported event: \"ontouchmove\"");
                case ONTOUCHSTART:
                    throw new Error("Unsupported event: \"ontouchstart\"");
            }
        }
        //add the events to the native element
        for(EventListenerContainer eventListenerContainer : enabledEventListeners) {
            nativeElement.addEventListener(eventListenerContainer.eventName, eventListenerContainer.eventListener);
        }
    }

    @Override
    public Type[] getEnabledEvents()
    {
        return enabledEvents;
    }

    @Override
    public GPElement getParentElement()
    {
        HTMLElement parentElement = (HTMLElement) nativeElement.getParentNode();
        return new TeavmGPElement(parentElement);
    }

    @Override
    public String getClassName()
    {
        return nativeElement.getClassName();
    }

    @Override
    public void removeAllChildren()
    {
        NodeList<Node> nodeList = nativeElement.getChildNodes();
        for(int i=0; i<nodeList.getLength(); i++) {
            HTMLElement childElement = (HTMLElement) nodeList.get(i);
            childElement.delete();
        }
    }

    @Override
    public String getInnerText()
    {
        return nativeElement.getInnerText();
    }

    @Override
    public void setInnerHTML(String text)
    {
        nativeElement.setInnerHTML(text);
    }

    @Override
    public void insertFirst(GPElement newFirstElement)
    {
        /*
         * var eElement; // some E DOM instance
         * var newFirstElement; //element which should be first in E
         * eElement.insertBefore(newFirstElement, eElement.firstChild);
         */
        nativeElement.insertBefore(getHtmlElementFromGPElement(newFirstElement), nativeElement.getFirstChild());
    }

    @Override
    public String getInnerHTML()
    {
        return nativeElement.getInnerHTML();
    }

    @Override
    public void insertAfter(GPElement before, GPElement after)
    {
        //referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
        nativeElement
        .getParentNode()
        .insertBefore(
                getHtmlElementFromGPElement(before),
                getHtmlElementFromGPElement(after)
                .getNextSibling()
                );
    }

    @Override
    public void setTabIndex(int i)
    {
        nativeElement.setTabIndex(i);
    }

    @Override
    public void replaceChild(GPElement newChild, GPElement oldChild)
    {
        nativeElement.replaceChild(getHtmlElementFromGPElement(newChild), getHtmlElementFromGPElement(oldChild));
    }

    @Override
    public GPElement getChild(int i)
    {
        HTMLElement childElement = (HTMLElement) nativeElement.getChildNodes().get(i);
        return new TeavmGPElement(childElement);
    }

    @Override
    public void setInnerText(String str)
    {
        nativeElement.setInnerHTML(str);
    }

    @Override
    public void setAttribute(String name, String value)
    {
        nativeElement.setAttribute(name, value);
    }
    
    @Override
    public String getPropertyString(String name)
    {
        String prop = nativeElement.getStyle().getPropertyValue(name);
        return prop;
    }
    
    public void setPropertyString(String name, String value) {
        nativeElement.getStyle().setProperty(name, value);
    }

    @Override
    public int getPropertyInt(String name)
    {
        return Integer.parseInt(getPropertyString(name));
    }

    @Override
    public void setPropertyInt(String name, int value)
    {
        setPropertyString(name, String.valueOf(value));
    }

    @Override
    public void focus()
    {
        nativeElement.focus();
    }
    
    @Override
    public int getClientWidth()
    {
        return nativeElement.getClientWidth();
    }
    
    private HTMLElement getHtmlElementFromGPElement(GPElement element) {
        return ((TeavmGPElement)element).nativeElement;
    }
    
    public HTMLElement getNativeElement() {
        return nativeElement;
    }
    
    /******************** NATIVE ****************/
    @JSBody(params = {"nativeJSObject"}, script =
            "var elem = nativeJSObject;"
            + "var rect = elem.getBoundingClientRect && elem.getBoundingClientRect();"
            + "return rect.left | 0;")
    private static native double native_getAbsoluteLeft(JSObject nativeJSObject);
    
    @JSBody(params = {"nativeJSObject"}, script = 
            "var elem = nativeJSObject;"
            + "var rect = elem.getBoundingClientRect && elem.getBoundingClientRect();"
            + "return rect.top | 0;")
    private static native double native_getAbsoluteTop(JSObject nativeJSObject);

    @Override
    public Object getPropertyObject(String name) {
        throw new RuntimeException("TeavmGPElement.getPropertyObject is unsupported."); //TODO: proper implementation
    }
}
