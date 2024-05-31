package lada;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashMap;

public class LadaNiva extends LinkedHashMap<Object,Object>
{
    public static final String PROP_PUT = "put";
    private PropertyChangeSupport propertySupport;

    public LadaNiva() {
        super();
        propertySupport = new PropertyChangeSupport(this);
    }

    @Override
    public Object put(Object k, Object v) {
        Object old = super.put(k, v);
        propertySupport.firePropertyChange(PROP_PUT, old, v);
        return old;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
}
