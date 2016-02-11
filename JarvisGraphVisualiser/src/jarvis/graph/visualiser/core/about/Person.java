package jarvis.graph.visualiser.core.about;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Person {
	private String name;
	private String contact;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public Person() {
		
	}

	public Person(String name, String contact) {
		super();
		this.name = name;
		this.contact = contact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Override
	public String toString() {
	    return name + " : " + contact;
	}
	
	  public void addPropertyChangeListener(String propertyName,
		      PropertyChangeListener listener) {
		    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
		  }

		  public void removePropertyChangeListener(PropertyChangeListener listener) {
		    propertyChangeSupport.removePropertyChangeListener(listener);
		  }
	
}
