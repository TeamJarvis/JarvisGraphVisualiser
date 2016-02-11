package parser;

import java.util.ArrayList;

public class JarvisConstructor {
	
	private String name;
	private ArrayList<String> parameters = new ArrayList<String>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getParameters() {
		return parameters;
	}
	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}
	@Override
	public String toString() {
		
		StringBuilder parametersBuilder = new StringBuilder();
		for(String c : parameters){
			parametersBuilder.append("\t" + c.toString());
		}
		
		return "JarvisConstructor name=" + name + " parameters: " + parametersBuilder.toString();
	}
	
	public String getParametarNames(){
		StringBuilder parametersBuilder = new StringBuilder();
		parametersBuilder.append("(parameters): ");
		if(parameters.size() == 0){
			parametersBuilder.append("none");
		}
		
		for(int i = 0; i < parameters.size(); i++){
			if(i!=0){
				parametersBuilder.append(", ");
			}
			parametersBuilder.append(parameters.get(i).toString());
		}
		
		return parametersBuilder.toString();
	}
	
	
	
	
}
