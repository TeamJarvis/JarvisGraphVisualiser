package jarvisdbread;

import jarvis.graph.visualiser.source.AbstractNode;
import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribModel;
import jarvis.graph.visualiser.source.IAttribute;
import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.INodeModel;
import jarvisdbread.model.SubAtribs;

import java.util.ArrayList;
import java.util.List;

public class MyNode extends AbstractNode implements INode{
	
	protected String tableName;
	protected ArrayList<IAttribute> privileges;
	protected List<IAttribute> columnInformations;
	protected ArrayList<String> primaryKeys;
	protected ArrayList<String> foreignKeys;
	protected ArrayList<INode> nodes;
	
	public MyNode(
			String tableName,
			ArrayList<IAttribute> privileges,
			List<IAttribute> columnInformations,
			ArrayList<String> primaryKeys,
			ArrayList<String> foreignKeys) {
		
		this.tableName = tableName;
		this.privileges = privileges;
		this.columnInformations = columnInformations;
		this.primaryKeys = primaryKeys;
		this.foreignKeys = foreignKeys;
	}
	
	public ArrayList<String> getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(ArrayList<String> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	@Override
	public String getTitle() {
		
		return tableName;
	}

	public List<IAttribute> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(ArrayList<IAttribute> privileges) {
		this.privileges = privileges;
	}

	public List<IAttribute> getColumnInformations() {
		return columnInformations;
	}

	public void setColumnInformations(List<IAttribute> columnInformations) {
		this.columnInformations = columnInformations;
	}

	public ArrayList<String> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(ArrayList<String> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}
	
	@Override
	public String toString() {
		return getTitle();
	}

	public ArrayList<INode> getNodes() {
		return nodes;
	}
	
	public void setNodes(ArrayList<INode> nodes) {
		this.nodes = nodes;
	}

	@Override
	public INodeModel getConnectedNodes() {
		jarvisdbread.model.NodeModel model = new jarvisdbread.model.NodeModel(nodes);
		
		return model;
	}

	@Override
	public IAttribModel getAttributes() {
		List<IAttribClasses> atr = new ArrayList<IAttribClasses>();
		
		SubAtribs atrib1 = new SubAtribs(columnInformations, "Column informations");
		SubAtribs atrib2 = new SubAtribs(privileges, "Privilegies");
		
		atr.add(atrib1);
		atr.add(atrib2);
		
		jarvisdbread.model.AttribModel model = new  jarvisdbread.model.AttribModel(atr);
		
		return model;
	}

}
