package de.exware.gplatform.teavm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.xml.Node;
import org.teavm.jso.dom.xml.NodeList;

import de.exware.gplatform.GPElement;
import de.exware.gplatform.GPStyle;
import de.exware.gplatform.event.GPEvent.Type;
import de.exware.gplatform.internal.MouseWheelEvent;
import de.exware.gplatform.teavm.event.TeavmGPEvent;
import de.exware.gplatform.event.GPEventListener;

public class TeavmGPElement implements GPElement
{
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
        
        List<GPElement> gpElements = new ArrayList<GPElement>();
        
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

    @Override
    public GPStyle getStyle()
    {
        return new TeavmGPStyle(nativeElement);
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
        return nativeElement.getAbsoluteLeft();
    }

    @Override
    public int getAbsoluteTop()
    {
        return nativeElement.getAbsoluteTop();
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
        public EventListenerContainer(String eventName, EventListener eventListener) {
            this.eventName = eventName;
            this.eventListener = eventListener;
        }
        String eventName;
        EventListener eventListener;
    }
    
    private ArrayList<EventListenerContainer> enabledEventListeners = new ArrayList<EventListenerContainer>();
    
    @Override
    public void enabledEvents(Type... eventTypes)
    {
        //remove all the previous enabled event listeners
        for(EventListenerContainer eventListenerContainer : enabledEventListeners) {
            nativeElement.removeEventListener(eventListenerContainer.eventName, eventListenerContainer.eventListener);
        }
        enabledEventListeners.clear();
        //set the new enabled events
        enabledEvents = eventTypes;
        //collect the events that need activation
        for(Type eventType : enabledEvents) {
            switch(eventType) {
                case ONCHANGE:
                    enabledEventListeners.add(
                    new EventListenerContainer("change", new EventListener<Event>() {
                        public void handleEvent(Event event) {
                            gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                        }
                    })
                );
                    
                    break;
                case ONCLICK:
                    enabledEventListeners.add(
                            new EventListenerContainer("click", new EventListener<MouseEvent>() {
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONCONTEXTMENU:
                    enabledEventListeners.add(
                            new EventListenerContainer("contextmenu", new EventListener<MouseEvent>() {
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONDBLCLICK:
                    enabledEventListeners.add(
                            new EventListenerContainer("dblclick", new EventListener<MouseEvent>() {
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONERROR:
                    enabledEventListeners.add(
                            new EventListenerContainer("error", new EventListener<Event>() {
                                public void handleEvent(Event event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONFOCUS:
                    enabledEventListeners.add(
                            new EventListenerContainer("focus", new EventListener<Event>() {
                                public void handleEvent(Event event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONKEYDOWN:
                    enabledEventListeners.add(
                            new EventListenerContainer("keydown", new EventListener<KeyboardEvent>() {
                                public void handleEvent(KeyboardEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONKEYPRESS:
                    enabledEventListeners.add(
                            new EventListenerContainer("keypress", new EventListener<KeyboardEvent>() {
                                public void handleEvent(KeyboardEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONKEYUP:
                    enabledEventListeners.add(
                            new EventListenerContainer("keyup", new EventListener<KeyboardEvent>() {
                                public void handleEvent(KeyboardEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONLOAD:
                    enabledEventListeners.add(
                            new EventListenerContainer("load", new EventListener<Event>() {
                                public void handleEvent(Event event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONMOUSEDOWN:
                    enabledEventListeners.add(
                            new EventListenerContainer("mousedown", new EventListener<MouseEvent>() {
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONMOUSEMOVE:
                    enabledEventListeners.add(
                            new EventListenerContainer("mousemove", new EventListener<MouseEvent>() {
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONMOUSEOUT:
                    enabledEventListeners.add(
                            new EventListenerContainer("mouseout", new EventListener<MouseEvent>() {
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONMOUSEOVER:
                    enabledEventListeners.add(
                            new EventListenerContainer("mouseover", new EventListener<MouseEvent>() {
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONMOUSEUP:
                    enabledEventListeners.add(
                            new EventListenerContainer("mouseup", new EventListener<MouseEvent>() {
                                public void handleEvent(MouseEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONMOUSEWHEEL:
                    enabledEventListeners.add(
                            new EventListenerContainer("mousewheel", new EventListener<MouseWheelEvent>() {
                                public void handleEvent(MouseWheelEvent event) {
                                    gpEventListener.onBrowserEvent(new TeavmGPEvent(eventType, event));
                                }
                            })
                        );
                    break;
                case ONTOUCHEND:
                    //unused
                    break;
                case ONTOUCHMOVE:
                    //unused
                    break;
                case ONTOUCHSTART:
                    //unused
                    break;
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
        nativeElement.setInnerText(text);
    }

    @Override
    public void insertFirst(GPElement newFirstElement)
    {
        /*
         * var eElement; // some E DOM instance
         * var newFirstElement; //element which should be first in E
         * eElement.insertBefore(newFirstElement, eElement.firstChild);
         */
        nativeElement.insertBefore(getHtmlElementFromGPElement(newFirstElement), nativeElement);
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
        return nativeElement.getStyle().getPropertyValue(name);
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
}
