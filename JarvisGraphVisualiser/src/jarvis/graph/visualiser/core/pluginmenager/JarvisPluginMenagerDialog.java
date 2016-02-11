package jarvis.graph.visualiser.core.pluginmenager;

import jarvis.graph.visualiser.core.pluginmenager.util.JarvisPlugin;
import jarvis.graph.visualiser.core.pluginmenager.util.JarvisPluginMenager;
import jarvis.graph.visualiser.core.pluginmenager.util.JarvisSelectionListener;

import java.util.ArrayList;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class JarvisPluginMenagerDialog extends Dialog {
	
	private Table table; 
	private TableViewer tableViewer;
	private DataBindingContext m_bindingContext;

	private final static String[] COLUMN_HEADINGS = {"Plugin Name", "Plugin Status"};
	
	public JarvisPluginMenagerDialog(Shell parentShell) {
		super(parentShell);
		
	}
	
	@Override
	protected Point getInitialSize() {
	    return new Point(450, 420);
	}
	
	@Override
	protected Button createButton(Composite arg0, int arg1, String arg2, boolean arg3) 
	{
		//Retrun null so that no default buttons like 'OK' and 'Cancel' will be created
		return null;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.getShell().setText("JARVIS plugin menager");
		
		GridLayout layout = new GridLayout(1, false); 
		layout.verticalSpacing = 10; 
		composite.setLayout(layout);
		
		GridData data = new GridData(GridData.FILL_BOTH); 
		data.grabExcessHorizontalSpace = true; 
		composite.setLayoutData(data);
		
		table = initTable(composite, data);
		
		tableViewer = initTableViewer();
		
		m_bindingContext = initDataBindings();
		
		initButtons(composite);
		
	    return composite;
	}
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public void setTableViewer(TableViewer tableViewer) {
		this.tableViewer = tableViewer;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// TODO Auto-generated method stub
		super.createButtonsForButtonBar(parent);
	}
	
	protected Table initTable(Composite composite, GridData data){
		Table table = new Table(composite, SWT.BORDER | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION); 
		table.setLinesVisible(true); 
		table.setHeaderVisible(true); 
		data = new GridData(SWT.FILL, SWT.FILL, true, false); 
		data.heightHint = 300; 
		table.setLayoutData(data); 

		TableLayout tableLayout = new TableLayout(); 
		table.setLayout(tableLayout); 

		tableLayout.addColumnData(new ColumnWeightData(10, 250, true)); 
		TableColumn column = new TableColumn(table, SWT.NONE); 
		column.setText(COLUMN_HEADINGS[0]); 
		column.setAlignment(SWT.LEFT); 

		tableLayout.addColumnData(new ColumnWeightData(5, 100, true)); 
		column = new TableColumn(table, SWT.NONE); 
		column.setText(COLUMN_HEADINGS[1]); 
		column.setAlignment(SWT.LEFT); 
		
		return table;
	}
	
	protected TableViewer initTableViewer(){
		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setColumnProperties(COLUMN_HEADINGS); 

		CellEditor[] editors = new CellEditor[2]; 
		editors[0] = new TextCellEditor(table); 
		editors[1] = new TextCellEditor(table); 
		tableViewer.setCellEditors(editors);
		
		return tableViewer;
	}
	
	protected void initButtons(Composite composite){
		Composite buttonComposite = new Composite(composite, SWT.NONE); 
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL); 
		fillLayout.spacing = 10; 
		buttonComposite.setLayout(fillLayout);
		
		Button addButton = new Button(buttonComposite, SWT.PUSH); 
		addButton.setText(JarvisPluginMenager.start);
		addButton.addSelectionListener(new JarvisSelectionListener(tableViewer, m_bindingContext));
		
		Button removeButton = new Button(buttonComposite, SWT.PUSH); 
		removeButton.setText(JarvisPluginMenager.stop);
		removeButton.addSelectionListener(new JarvisSelectionListener(tableViewer, m_bindingContext));
		
		Button uninstallButton = new Button(buttonComposite, SWT.PUSH);
		uninstallButton.setText(JarvisPluginMenager.uninstall);
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
			
		ObservableListContentProvider setContentProvider = new ObservableListContentProvider();
		tableViewer.setContentProvider(setContentProvider);
			
		IObservableMap[] observeMaps = PojoObservables.observeMaps(
				setContentProvider.getKnownElements(), JarvisPlugin.class,
				new String[] { "name", "status"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
			
		ArrayList<JarvisPlugin> plugins = JarvisPluginMenager.getJarvisPluginMenger().getPlugins().getPlugins();
			
		WritableList writableSet = new WritableList(plugins, JarvisPlugin.class);
		tableViewer.setInput(writableSet);
			
		return bindingContext;
	}
}
