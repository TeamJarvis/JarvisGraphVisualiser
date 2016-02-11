package jarvisdbread.dbutil;

import jarvisdbread.wizard.DBParams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class DBConnection {
	
	private static Connection conn;
	private static final String LOCK_TIMEOUT_INTERVAL = "5000";
	private static DBParams params;

	  public static DBParams getParams() {
		return params;
	}

	public static void setParams(DBParams params) {
		DBConnection.params = params;
	}

	public static Connection getConnection() {
	    if (conn == null)
	      try {
	        open();
	      } catch (Exception e) {
	    	  MessageDialog.openError(Display.getDefault().getActiveShell(), "ERROR", e.getMessage());
	      }
	    
	    return conn;
	  }
	  
	  private static void setLockTimeOut() throws SQLException {
		    Statement stmt = conn.createStatement();
		    String cmd = "SET LOCK_TIMEOUT " + LOCK_TIMEOUT_INTERVAL;
		    stmt.execute(cmd);
	  }
	  
	  public static void open() throws ClassNotFoundException, SQLException {
		    if (conn != null)
		      return;
				   
		    Class.forName(params.getJdbcName());
		    conn = DriverManager.getConnection(params.getUrl(), params.getUsername(), params.getPassword());
		    conn.setAutoCommit(false);
				    
		   setLockTimeOut();
	  }
	  
	  public static void close() {
		    try {
		      if (conn != null)
		        conn.close();
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
	  }
}
