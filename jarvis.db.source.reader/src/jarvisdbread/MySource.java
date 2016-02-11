package jarvisdbread;

import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.ISource;
import jarvisdbread.dbutil.DBConnection;
import jarvisdbread.dbutil.JarvisDBMenager;
import jarvisdbread.wizard.DBParams;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class MySource implements ISource{
	
	protected JarvisDBMenager jarvisMenager;
	private String name = "DB source model [TeamJarvis]";
	private String id ="jarvis.db.source.reader";
	
	public MySource() throws SQLException {
		DatabaseMetaData metaData = DBConnection.getConnection().getMetaData();
		jarvisMenager = new JarvisDBMenager(metaData);
	}
	
	public MySource(DBParams dbParams){
		DBConnection.setParams(dbParams);
		DatabaseMetaData metaData;
		try {
			metaData = DBConnection.getConnection().getMetaData();
			jarvisMenager = new JarvisDBMenager(metaData);
		} catch (SQLException e) {
			MessageDialog.openError(Display.getDefault().getActiveShell(), "ERROR", e.getMessage());
		}catch (NullPointerException e) {
			MessageDialog.openError(Display.getDefault().getActiveShell(), "ERROR", e.getMessage());
		}
	}
	
	@Override
	public String getName() {
		
		try {
			return jarvisMenager.fillDescription().getProductName();
		} catch (SQLException e) {
			MessageDialog.openError(Display.getDefault().getActiveShell(), "ERROR", e.getMessage());
		}
		
		return "";
	}

	@Override
	public HashMap<INode, ArrayList<INode>> getDataGraph() {
	
		try {
			return jarvisMenager.getConnectedGraph();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			MessageDialog.openError(Display.getDefault().getActiveShell(), "ERROR", e.getMessage());
		}
		
		return new HashMap<INode, ArrayList<INode>>();
	}

	@Override
	public Object getDescription() {

		try {
			return jarvisMenager.fillDescription();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		
		return getName();
	}

	@Override
	public List<INode> getNodeDataGraph() {
		
		ArrayList<INode> nodes = new ArrayList<INode>();
		try {
			Iterator<INode> iterator = jarvisMenager.getConnectedGraph().keySet().iterator();
			
			while (iterator.hasNext()) {
				INode iNode = (INode) iterator.next();
				nodes.add(iNode);
			}
			
			return nodes;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nodes;
	}

	@Override
	public String getPluginID() {
		
		return id;
	}

	@Override
	public String getPluginName() {
		
		return name;
	}

}
