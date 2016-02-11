package jarvis.graph.visualiser.core.about;

import java.util.ArrayList;
import java.util.List;

public enum ModelProvider {
  INSTANCE;

  private List<Person> persons;

  private ModelProvider() {
    persons = new ArrayList<Person>();
    // Image here some fancy database access to read the persons and to
    // put them into the model
    persons.add(new Person("Marko Dikic", "markodikic@gmail.com"));
    persons.add(new Person("Sebastijan Kaplar", "sebastijan.kaplar@gmail.com"));
    persons.add(new Person("Milos Simic", "milossimicsimo@gmail.com"));
    persons.add(new Person("Alen Suljkanovic", "biohazard1491@gmail.com"));
  }

  public List<Person> getPersons() {
    return persons;
  }

}
