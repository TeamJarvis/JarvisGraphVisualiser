package jarvis.graph.visualiser.core.views;

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
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

public class JarvisPartDescription extends ViewPart{

	public static final String ID = "JarvisGraphVisualiser.JarvisPartDescription";
	
	private INode node;
	
	public INode getNode() {
		return node;
	}

	public void setNode(INode node) {
		this.node = node;
	}

	private Table table; 
	private TableViewer tableViewer;
	private DataBindingContext m_bindingContext;

	private final static String[] COLUMN_HEADINGS = {"Description", "Value"};
	
	
	
	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.getShell().setText("JARVIS plugin");
		
		GridLayout layout = new GridLayout(2, false); 
		layout.verticalSpacing = 10; 
		composite.setLayout(layout);
		
		GridData data = new GridData(GridData.FILL_BOTH); 
		data.grabExcessHorizontalSpace = true; 
		composite.setLayoutData(data);
		
		table = initTable(composite, data);
		
		tableViewer = initTableViewer();
		
		m_bindingContext = initDataBindings();
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
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
	
	protected TableViewer initTableViewer(){
		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setColumnProperties(COLUMN_HEADINGS); 

		CellEditor[] editors = new CellEditor[2]; 
		editors[0] = new TextCellEditor(table); 
		editors[1] = new TextCellEditor(table); 
		tableViewer.setCellEditors(editors);
		
		return tableViewer;
	}
	
	public DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
			
		ObservableListContentProvider setContentProvider = new ObservableListContentProvider();
		tableViewer.setContentProvider(setContentProvider);
			
		IObservableMap[] observeMaps = PojoObservables.observeMaps(
				setContentProvider.getKnownElements(), JarvisGraphDescriptionPair.class,
				new String[] { "name", "description"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
			

		ArrayList<JarvisGraphDescriptionPair> descriptions = new ArrayList<JarvisGraphDescriptionPair>();


		
		WritableList writableSet = new WritableList(descriptions, JarvisGraphDescriptionPair.class);
		tableViewer.setInput(writableSet);
			
		return bindingContext;
	}
	
	public void sync()
	{
		if(this.node != null)
		{
			ArrayList<JarvisGraphDescriptionPair> descriptions = new ArrayList<JarvisGraphDescriptionPair>();
			descriptions.add(new JarvisGraphDescriptionPair("Node", node.getTitle()));	
			
			IAttribModel nodeModel =  node.getAttributes();
			List<IAttribClasses> nodeClasses = nodeModel.getAttribs();
			for(IAttribClasses nodeClass : nodeClasses)
			{
				for(IAttribute attrib : nodeClass.getAttribs())
				{
					descriptions.add(new JarvisGraphDescriptionPair(attrib.getAttributeName(), attrib.getAttributeValue()));
				}
			}
			WritableList writableSet = new WritableList(descriptions, JarvisGraphDescriptionPair.class);
			tableViewer.setInput(writableSet);
			tableViewer.refresh();
		}
	}
}
