package c.myn4s.thetravellerandroid.GameEngine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by argiraud on 27/04/2018.
 */

public class BooListener {
    private boolean boo = false;
  

    public BooListener(boolean boo){
        this.boo = boo;
    }

    public  void setBoo(boolean boo) {
        boolean oldValue = this.boo;
        this.boo = boo;
        propertyChangeSupport.firePropertyChange("boo", oldValue, this.boo);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
