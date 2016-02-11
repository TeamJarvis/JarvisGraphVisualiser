package jarvis.graph.visualiser.core.pluginmenager.util;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.osgi.framework.BundleException;

public class JarvisSelectionListener extends SelectionAdapter {
	
	protected TableViewer tableViewer;
	protected DataBindingContext m_bindingContext;
	
	public JarvisSelectionListener(TableViewer tableViewer,DataBindingContext m_bindingContext) {
		this.tableViewer = tableViewer;
		this.m_bindingContext = m_bindingContext;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		super.widgetSelected(e);
		
		IStructuredSelection sel = (IStructuredSelection) tableViewer.getSelection();
		JarvisPlugin plugin = (JarvisPlugin) sel.getFirstElement();
		Button button = (Button) e.getSource();
		
		if (plugin != null) {
			
			try{
				
				if (button.getText().equalsIgnoreCase(JarvisPluginMenager.start)) {
					JarvisPluginMenager.getJarvisPluginMenger().start(plugin.getName());
				}else if(button.getText().equalsIgnoreCase(JarvisPluginMenager.stop)){
					JarvisPluginMenager.getJarvisPluginMenger().stop(plugin.getName());
				}else{
					JarvisPluginMenager.getJarvisPluginMenger().uninstall(plugin.getName());
				}
				
				m_bindingContext.updateModels();
				m_bindingContext.updateTargets();
				tableViewer.refresh();
				
			}catch (BundleException be) {
				be.printStackTrace();
			}	
		}
	}
}
