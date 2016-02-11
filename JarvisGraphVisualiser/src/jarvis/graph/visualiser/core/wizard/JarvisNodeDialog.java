package jarvis.graph.visualiser.core.wizard;

import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribModel;
import jarvis.graph.visualiser.source.IAttribute;
import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.util.JarvisGraphDescriptionPair;

import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class JarvisNodeDialog extends Dialog{
	
	private INode node;
	CTabFolder tabFolder = null;
	private Table table; 
	private TableViewer tableViewer;
	private DataBindingContext m_bindingContext;

	private final static String[] COLUMN_HEADINGS = {"Description", "Value"};
	
	public JarvisNodeDialog(Shell parentShell, INode node) {
		super(parentShell);
		this.node = node;
	}
	
	@Override
	protected Point getInitialSize() {
	    return new Point(450, 420);
	}
	
	@Override
	protected Button createButton(Composite arg0, int arg1, String arg2, boolean arg3) 
	{
		return null;
	}
	
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.getShell().setText("JARVIS plugin menager");
		
		tabFolder = new CTabFolder(composite, SWT.NONE); //border
		tabFolder.setSize(new Point(450, 420));
		prepare(composite);
		return parent;
	}
	

	private void prepare(Composite container) {

		IAttribModel nodeModel =  node.getAttributes();
		List<IAttribClasses> nodeClasses = nodeModel.getAttribs();
		CTabItem tabItem = null;
		Composite page = null;
		for(IAttribClasses nodeClass : nodeClasses)
		{

			tabItem = new CTabItem(tabFolder, SWT.NONE);
			tabItem.setText(nodeClass.getName());
			
			page = new Composite(tabFolder, SWT.BORDER);
			GridLayout layout = new GridLayout(2, false); 
			layout.verticalSpacing = 10; 
			page.setLayout(layout);
		    tabItem.setControl(page);
			
			GridData data = new GridData(GridData.FILL_BOTH); 
			data.grabExcessHorizontalSpace = true; 
			page.setLayoutData(data); 

			table = initTable(page, data);
			tableViewer = initTableViewer(table);
			
			m_bindingContext = initDataBindings(tableViewer);
			sync(tableViewer, nodeClass.getAttribs());
			
		}
		
	}
	
	public INode getNode() {
		return node;
	}

	public void setNode(INode node) {
		this.node = node;
	}
	
	
	protected Table initTable(Composite composite, GridData data){
		Table table = new Table(composite, SWT.FILL | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION); 
		table.setLinesVisible(true); 
		table.setHeaderVisible(true); 
		data = new GridData(SWT.FILL, SWT.FILL, true, false); 
		data.heightHint = 300; 
		table.setLayoutData(data); 

		TableLayout tableLayout = new TableLayout(); 
		table.setLayout(tableLayout); 

		tableLayout.addColumnData(new ColumnWeightData(10, 150, true)); 
		TableColumn column = new TableColumn(table, SWT.NONE); 
		column.setText(COLUMN_HEADINGS[0]); 
		column.setAlignment(SWT.LEFT); 

		tableLayout.addColumnData(new ColumnWeightData(5, 200, true)); 
		column = new TableColumn(table, SWT.NONE); 
		column.setText(COLUMN_HEADINGS[1]); 
		column.setAlignment(SWT.LEFT); 
		
		return table;
	}
	
	protected TableViewer initTableViewer(Table t){
		TableViewer tableViewer = new TableViewer(t);
		tableViewer.setColumnProperties(COLUMN_HEADINGS); 

		CellEditor[] editors = new CellEditor[2]; 
		editors[0] = new TextCellEditor(t); 
		editors[1] = new TextCellEditor(t); 
		tableViewer.setCellEditors(editors);
		
		return tableViewer;
	}
	
	
	public DataBindingContext initDataBindings(TableViewer tv) {
		DataBindingContext bindingContext = new DataBindingContext();
			
		ObservableListContentProvider setContentProvider = new ObservableListContentProvider();
		tv.setContentProvider(setContentProvider);
			
		IObservableMap[] observeMaps = PojoObservables.observeMaps(
				setContentProvider.getKnownElements(), JarvisGraphDescriptionPair.class,
				new String[] { "name", "description"});
		tv.setLabelProvider(new ObservableMapLabelProvider(observeMaps));

		ArrayList<JarvisGraphDescriptionPair> descriptions = new ArrayList<JarvisGraphDescriptionPair>();
		WritableList writableSet = new WritableList(descriptions, JarvisGraphDescriptionPair.class);
		tv.setInput(writableSet);
			
		return bindingContext;
	}
	
	public void sync(TableViewer tv, List<IAttribute> list)
	{
		if(this.node != null)
		{
			ArrayList<JarvisGraphDescriptionPair> descriptions = new ArrayList<JarvisGraphDescriptionPair>();
			
			for(IAttribute attrib : list)
			{
				descriptions.add(new JarvisGraphDescriptionPair(attrib.getAttributeName(), attrib.getAttributeValue()));
			}
			
			WritableList writableSet = new WritableList(descriptions, JarvisGraphDescriptionPair.class);
			tv.setInput(writableSet);
			tv.refresh();
		}
	}

}
