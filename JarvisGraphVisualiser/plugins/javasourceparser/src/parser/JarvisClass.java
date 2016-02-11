package parser;

import jarvis.graph.visualiser.source.AbstractNode;
import jarvis.graph.visualiser.source.IAttribModel;
import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.INodeModel;

import java.util.ArrayList;

public class JarvisClass extends AbstractNode implements INode {

	private String name;
	private String superclass;
	private ArrayList<JarvisImport> imports = new ArrayList<JarvisImport>();
	private ArrayList<JarvisField> fields = new ArrayList<JarvisField>();
	private ArrayList<JarvisMethod> methods = new ArrayList<JarvisMethod>();
	private ArrayList<JarvisConstructor> constructors = new ArrayList<JarvisConstructor>();
	
	private ArrayList<INode> references = new ArrayList<INode>();
	
	private ArrayList<String> interfaces = new ArrayList<String>();
	
	public void imaReferencuNa(){
		for(JarvisField f : fields){
			System.out.println("\t\treferenca na: " + f.getType());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getSuperclass() {
		return superclass;
	}

	public void setSuperclass(String superclass) {
		this.superclass = superclass;
	}

	public ArrayList<JarvisImport> getImports() {
		return imports;
	}

	public ArrayList<JarvisField> getFields() {
		return fields;
	}

	public ArrayList<JarvisMethod> getMethods() {
		return methods;
	}

	public ArrayList<JarvisConstructor> getConstructors() {
		return constructors;
	}

	@Override
	public String toString() {
		StringBuilder importsBuilder = new StringBuilder();
		for(JarvisImport i : imports){
			importsBuilder.append("\n\t"+i.getName());
		}
		
		StringBuilder fieldsBuilder = new StringBuilder();
		for(JarvisField f : fields){
			fieldsBuilder.append("\n"+f.toString());
		}
		
		StringBuilder constructorsBuilder = new StringBuilder();
		for(JarvisConstructor c : constructors){
			constructorsBuilder.append("\n" + c.toString());
		}
		
		StringBuilder methodsBuilder = new StringBuilder();
		for(JarvisMethod m : methods){
			methodsBuilder.append("\n" + m.toString());
		}
		
		
		
		return "JarvisClass name: " + name  +
				"\n\t imports: \n\t" + importsBuilder.toString() +
				"\n\t fields: \n\t" + fieldsBuilder.toString() +
				"\n\t constructors: \n\t" + constructorsBuilder.toString() +
				"\n\t methods: \n\t" + methodsBuilder.toString();
				
				
	}

	public ArrayList<INode> getReferences() {
		return references;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JarvisClass other = (JarvisClass) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public ArrayList<String> getInterfaces() {
		return interfaces;
	}

	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public IAttribModel getAttributes() {
		return new JarvisAttributeModel(fields, constructors, methods);
	}

	@Override
	public INodeModel getConnectedNodes() {
		return new JarvisNodeModel(references);
	}

}
