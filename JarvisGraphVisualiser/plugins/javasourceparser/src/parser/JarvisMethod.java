package parser;

import java.util.ArrayList;

public class JarvisMethod {
	
	private String name;
	private String returnValue;
	private ArrayList<String> parameters = new ArrayList<String>();
	private String modifier;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public ArrayList<String> getParameters() {
		return parameters;
	}
	
	public void setParameters(ArrayList<String> paramaters) {
		this.parameters = paramaters;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	@Override
	public String toString() {
		
		StringBuilder parametersBuilder = new StringBuilder();
		for(String c : parameters){
			parametersBuilder.append("\t" + c.toString());
		}
		
		return "JarvisMethod name: " + name + " parameters: " + parametersBuilder.toString();
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
