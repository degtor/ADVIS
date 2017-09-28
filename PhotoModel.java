// PhotoModel
// Responsible for storing the state of the component

import javafx.scene.control.TextFormatter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.Enumeration;

public class PhotoModel {

	// https://docs.oracle.com/javase/7/docs/api/javax/swing/event/EventListenerList.html
	EventListenerList listenerList = new EventListenerList();
	ChangeEvent ChangeEvent = null;

	private boolean draw;
	private boolean flipped;
	private Image i;

	public PhotoModel(Image i) {
		this.i = i; // sets image
		System.out.println("Hi! Image in model");
		fireChangeEvent();
	}

	public Image getImage() {
		System.out.print("HI: I am in getImage() model");
		fireChangeEvent();
		return i;
	}

	public boolean isFlipped() {
		return flipped;
	}

	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}


	public void addChangeListener(ChangeListener cl) {
		listenerList.add(ChangeListener.class, cl);
	}

	public void removeChangeListener(ChangeListener cl) {
		listenerList.remove(ChangeListener.class, cl);

	}

	protected void fireChangeEvent() {
		System.out.println("FIRE IN MODEL:O!!!");
		Object[] listeners = listenerList.getListenerList();

		for (int i = listeners.length-2; i>=0; i-=2) {
			if (listeners[i] == ChangeListener.class) {
				if (ChangeEvent == null)
					ChangeEvent = new ChangeEvent(this);
				((ChangeListener)listeners[i+1]).stateChanged(ChangeEvent);
			}
		}
	}
}