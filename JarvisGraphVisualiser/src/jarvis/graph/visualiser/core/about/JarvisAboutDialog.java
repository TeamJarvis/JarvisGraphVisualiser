package jarvis.graph.visualiser.core.about;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

public class JarvisAboutDialog extends Dialog {
	
	private Table table; 
	private TableViewer tableViewer;
	
	private final static String[] COLUMN_HEADINGS = {"Developers", "Contact"};
	
	public JarvisAboutDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
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
		composite.getShell().setText("About JARVIS Visualiser");
		
		GridLayout layout = new GridLayout(1, false); 
		layout.verticalSpacing = 10; 
		composite.setLayout(layout);
		
		GridData data = new GridData(GridData.FILL_BOTH); 
		data.grabExcessHorizontalSpace = true; 
		composite.setLayoutData(data);
		
		table = initTable(composite, data);
		tableViewer = initTableViewer();
		
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setInput(ModelProvider.INSTANCE.getPersons());
		
		initButtons(composite);
		
		return composite;
	}
	
	protected void initButtons(Composite composite){
		Composite buttonComposite = new Composite(composite, SWT.NONE); 
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL); 
		fillLayout.spacing = 10; 
		buttonComposite.setLayout(fillLayout);
		
		Button addButton = new Button(buttonComposite, SWT.PUSH); 
		addButton.setText("OK");
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JarvisAboutDialog.this.close();
			}
		});
		
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

}
