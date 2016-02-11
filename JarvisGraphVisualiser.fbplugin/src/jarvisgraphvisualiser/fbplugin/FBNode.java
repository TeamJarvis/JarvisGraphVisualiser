package jarvisgraphvisualiser.fbplugin;

import jarvis.graph.visualiser.source.AbstractNode;
import jarvis.graph.visualiser.source.Attribute;
import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribModel;
import jarvis.graph.visualiser.source.IAttribute;
import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.INodeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Node(User) in the graph.
 * @author Bane
 *
 */
public class FBNode extends AbstractNode implements INode {
	
	protected String name;
	protected String first;
	protected String middle;
	protected String last;
	protected String id;
	protected String birthday;
	protected String picture;
	protected String email;
	protected String hometown;
	protected ArrayList<INode> friends;
	
	
	public FBNode(String name, String first, String middle, String last,
			String id, String birthday, String picture, String email,
			String hometown, ArrayList<INode> friends) {
		
		this.name = name;
		this.first = first;
		this.middle = middle;
		this.last = last;
		this.id = id;
		this.birthday = birthday;
		this.picture = picture;
		this.email = email;
		this.hometown = hometown;
		this.friends = friends;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getMiddle() {
		return middle;
	}
	public void setMiddle(String middle) {
		this.middle = middle;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public ArrayList<INode> getFriends() {
		return friends;
	}
	public void setFriends(ArrayList<INode> friends) {
		this.friends = friends;
	}

	@Override
	public String getTitle() {
		return name;
	}

//	@Override
//	public List<List<IAttribute>> getAttributes() {
//		//TODO SREDI KAD SE ODREDI ZADNJA VERZIJA VIZUALIZATORA
//		//prva lista nazivi npr
//		//druga lista povezani korisnici
//		List<List<IAttribute>> a = new ArrayList<List<IAttribute>>();
//		ArrayList<IAttribute> a2 = new ArrayList<IAttribute>();
//		a2.add(new Attribute("Puno ime", this.name));
//		a2.add(new Attribute("Ime", this.first));
//		a2.add(new Attribute("Srednje ime", this.middle));
//		a2.add(new Attribute("Prezime", this.last));
//		a2.add(new Attribute("id", this.id));
//		a.add(a2);
//		return a;
//	}

	@Override
	public INodeModel getConnectedNodes() {
		FBNodeModel model = new FBNodeModel(friends);
		return model;
	}

	@Override
	public IAttribModel getAttributes() {
		List<IAttribClasses> atr = new ArrayList<IAttribClasses>();
		
		List<IAttribute> podaci = new ArrayList<IAttribute>();
		podaci.add(new Attribute("Puno ime", this.name));
		podaci.add(new Attribute("Ime", this.first));
		podaci.add(new Attribute("Srednje ime", (this.middle == null) ? "" : this.middle));
		podaci.add(new Attribute("Prezime", (this.last == null) ? "" : this.last));
		podaci.add(new Attribute("Id", this.id));
		podaci.add(new Attribute("Birthday", (this.birthday == null) ? "" : this.birthday));
		podaci.add(new Attribute("Email", (this.email == null) ? "" : this.email));
		podaci.add(new Attribute("Hometown", (this.hometown == null) ? "" : this.hometown));
		podaci.add(new Attribute("Picture", (this.picture == null) ? "" : this.picture));
		
		SubAttribs atrib1 = new SubAttribs(podaci, "Podaci o Korisniku");
		atr.add(atrib1);
		FBAttribModel model = new  FBAttribModel(atr);
		return model;
	}

//	@Override
//	public List<INode> getConnectedNodes() {
//		return friends;
//	}



}
