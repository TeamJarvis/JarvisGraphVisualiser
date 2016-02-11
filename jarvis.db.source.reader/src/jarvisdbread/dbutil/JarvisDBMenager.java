package jarvisdbread.dbutil;

import jarvis.graph.visualiser.source.IAttribute;
import jarvis.graph.visualiser.source.INode;
import jarvisdbread.MyNode;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class JarvisDBMenager {
	
	protected DatabaseMetaData metaData;
	
	public JarvisDBMenager(DatabaseMetaData metaData) {
		this.metaData = metaData;
	}
	
	public DatabaseMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(DatabaseMetaData metaData) {
		this.metaData = metaData;
	}

	public ArrayList<MyNode> getTables() throws SQLException{
		ArrayList<MyNode> tables = new ArrayList<MyNode>();
		ResultSet tSet = metaData.getTables(null, null, null, new String[] {"TABLE"});
		
		while (tSet.next()){
			
			String tableName = tSet.getString("TABLE_NAME");
			ArrayList<IAttribute> privileges = new ArrayList<IAttribute>();
			ResultSet tablePrivileges = metaData.getTablePrivileges(null, null, tableName);
			
			while (tablePrivileges.next()) {
				String pName = tablePrivileges.getString("PRIVILEGE");
				String pGrantable = tablePrivileges.getString("IS_GRANTABLE");
				String pGrantor = tablePrivileges.getString("GRANTOR");
				
				Privileges privilege = new Privileges(pName, pGrantable, pGrantor);
				privileges.add(privilege);
			}
			
			MyNode tableInformation = new MyNode(tableName, privileges,
													getColumns(tableName),
													getPrimaryKey(tableName),
													getForeignKeys(tableName));
			tables.add(tableInformation);
			
		}
		
		tSet.close();
		
		return tables;
	}
	
	protected ArrayList<IAttribute> getColumns(String tableName) throws SQLException {
		ArrayList<IAttribute> columns = new ArrayList<IAttribute>();
		ResultSet columSet = metaData.getColumns(null, null, tableName, null);
		
		while (columSet.next()) {
			String name = columSet.getString("COLUMN_NAME");
			String type = columSet.getString("TYPE_NAME");
			MandatoryStyle mandatory = new MandatoryStyle(isMadatory(columSet.getString("IS_NULLABLE")));
			
			ResultSet columnPrivileges = metaData.getColumnPrivileges(null, null, tableName, name);
			ArrayList<Privileges> privileges = new ArrayList<Privileges>();
			
			while (columnPrivileges.next()) {
				String pName = columnPrivileges.getString("PRIVILEGE");
				String pGranted = columnPrivileges.getString("IS_GRANTABLE");
				String pGrantor = columnPrivileges.getString("GRANTOR");
				Privileges priv = new Privileges(pName, pGranted, pGrantor);
				privileges.add(priv);
			}
			
			columnPrivileges.close();
			
			columns.add(new ColumnInformations(name, type,mandatory,privileges));
		}
		
		columSet.close();
		
		return columns;
	}
	
	protected ArrayList<String> getPrimaryKey(String tableName) throws SQLException{
		ResultSet keySet = metaData.getPrimaryKeys(null, null, tableName);
		ArrayList<String> keys = new ArrayList<String>();
		
		while (keySet.next()) {
			
			keys.add(keySet.getString("COLUMN_NAME"));
		}
		
		keySet.close();
		
		return keys;
	}
	
	protected ArrayList<String> getConnectedTables(String tableName) throws SQLException{
		ArrayList<String> connectedTables = new ArrayList<String>();
		ResultSet foreignTables = metaData.getExportedKeys(null, null, tableName);
		
		while (foreignTables.next()) {
			
			if (!connectedTables.contains(foreignTables.getString("FKTABLE_NAME"))) {
				connectedTables.add(foreignTables.getString("FKTABLE_NAME"));
			}
		}
		
		foreignTables.close();
		
		return connectedTables;
	}
	
	protected ArrayList<String> getForeignKeys(String tableName) throws SQLException {
		ArrayList<String> retval = new ArrayList<String>();
		ResultSet foreignKeysInfo = metaData.getExportedKeys(null, null, tableName);

		while (foreignKeysInfo.next()) {
			
			if(!retval.contains(foreignKeysInfo.getString("FK_NAME"))){
				retval.add(foreignKeysInfo.getString("FK_NAME"));
			}
		}
		
		foreignKeysInfo.close();
		
		return retval;
	}
	
	private Mandatory isMadatory(String nullable){
		if (nullable.equalsIgnoreCase("YES")) {
			return Mandatory.YES;
		}
		
		return Mandatory.NO;
	}
	
	public HashMap<INode, ArrayList<INode>> getConnectedGraph() throws SQLException{
		
		//connected graph pair nod and his succesors
		HashMap<INode, ArrayList<INode>> graph = new HashMap<INode, ArrayList<INode>>();
		
		//all tables in DB
		ArrayList<MyNode> allTables = getTables();
		
		for (MyNode table : allTables) {
			
			//System.out.println("TABLE:"+table.getTitle());
			
			//get all connected table names
			ArrayList<String> connectedTables = getConnectedTables(table.getTitle());
			
			/*for (String string : connectedTables) {
				System.out.println("\tPOVEZANO:"+string);
			}*/
			
			//create list of succesors
			ArrayList<INode> values = new ArrayList<INode>();
			
			//find them all
			for (String name : connectedTables) {
				for (MyNode tableInfo : allTables) {
					if (tableInfo.getTitle().equalsIgnoreCase(name)) {
						values.add(tableInfo);
						//vals.add(tableInfo);
						break;//no same table names,so insert first one when find!
					}
				}
			}
			
			table.setNodes(values);
			
			//put into final graph
			graph.put(table, values);
		}
		
		return graph;
	}
	
	public DBDescription fillDescription() throws SQLException{
		
		String productVersion = metaData.getDatabaseProductVersion();
		String productName = metaData.getDatabaseProductName();
		
		String driverName = metaData.getDriverName();
		String driverVersion = metaData.getDriverVersion();
		
		int isolation = metaData.getDefaultTransactionIsolation();
		
		DBDescription desc = new DBDescription(productName, productVersion, driverName, driverVersion, isolation);
		
		return desc;
		
	}
	
}
