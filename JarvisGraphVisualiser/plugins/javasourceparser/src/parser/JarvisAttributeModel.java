package parser;

import java.util.ArrayList;
import java.util.List;

import jarvis.graph.visualiser.source.Attribute;
import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribModel;
import jarvis.graph.visualiser.source.IAttribute;

public class JarvisAttributeModel implements IAttribModel {

	private ArrayList<JarvisField> fields;
	private ArrayList<JarvisConstructor> constructors;
	private ArrayList<JarvisMethod> methods;

	public JarvisAttributeModel(ArrayList<JarvisField> fields, ArrayList<JarvisConstructor> constructors, ArrayList<JarvisMethod> methods) {
		this.fields = fields;
		this.constructors = constructors;
		this.methods = methods;
	}

	@Override
	public List<IAttribClasses> getAttribs() {
		List<IAttribClasses> list = new ArrayList<IAttribClasses>();

		makeAttributeList(list);

		makeConstructorList(list);

		makeMethodList(list);

		return list;
	}

	private void makeAttributeList(List<IAttribClasses> list) {
		List<IAttribute> attributes = new ArrayList<IAttribute>();
		for (JarvisField field : fields) {
			attributes.add(new Attribute(field.getAttributeName(), field.getAttributeValue()));
		}

		if (attributes.size() != 0) {
			JarvisAttributeClasses attributeClass = new JarvisAttributeClasses("attributes", attributes);
			list.add(attributeClass);
		}
	}

	private void makeMethodList(List<IAttribClasses> list) {
		List<IAttribute> methods_ = new ArrayList<IAttribute>();
		for (JarvisMethod method : methods) {
			methods_.add(new Attribute(method.getName(), method.getParametarNames()));
		}

		if (methods_.size() != 0) {
			JarvisAttributeClasses methodClass = new JarvisAttributeClasses("methods", methods_);
			list.add(methodClass);
		}
	}

	private void makeConstructorList(List<IAttribClasses> list) {
		List<IAttribute> constructors__ = new ArrayList<IAttribute>();
		for (JarvisConstructor constructor : constructors) {
			constructors__.add(new Attribute(constructor.getName(), constructor.getParametarNames()));
		}

		if (constructors__.size() != 0) {
			JarvisAttributeClasses constructorClass = new JarvisAttributeClasses("constructors", constructors__);
			list.add(constructorClass);
		}
	}

}
