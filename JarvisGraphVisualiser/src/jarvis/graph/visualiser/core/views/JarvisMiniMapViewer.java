package jarvis.graph.visualiser.core.views;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;

public class JarvisMiniMapViewer extends ViewPart{
	
	public static final String ID = "JarvisGraphVisualiser.JarvisMiniMapViewer";

	private GraphViewer graphViewer;
	private Viewport viewport;
	
	public JarvisMiniMapViewer() {
		viewport = new Viewport();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		parent.addPaintListener(new MiniMapPaintListener());
		MiniMapMouseListener mmml = new MiniMapMouseListener();
		parent.addMouseListener(mmml);
		parent.addMouseMoveListener(mmml);
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

	public GraphViewer getGraphViewer() {
		return graphViewer;
	}

	public void setGraphViewer(GraphViewer graphViewer) {
		this.graphViewer = graphViewer;
	}
	
	public Viewport getViewport() {
		return viewport;
	}
	
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}
	
	public class Viewport {
		private Point location;
		private int width;
		private int height;
		
		public Viewport() {
			super();
			location = new Point(0, 0);
			width = 25;
			height = 25;
		}
		
		public Viewport(Point location, int width, int height) {
			this.location = location;
			this.width = width;
			this.height = height;
		}
		
		public Point getLocation() {
			return location;
		}
		
		public void setLocation(Point location) {
			this.location = location;
		}
		
		public int getWidth() {
			return width;
		}
		
		public void setWidth(int width) {
			this.width = width;
		}
		
		public int getHeight() {
			return height;
		}
		
		public void setHeight(int height) {
			this.height = height;
		}
		
		public boolean containsPoint(Point point) {
			if ((point.x > location.x) && (point.x < location.x + width) && 
					(point.y > location.y) && (point.y < location.y + height)) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public class MiniMapPaintListener implements PaintListener {
		
		@Override
		public void paintControl(PaintEvent e) {
			if (graphViewer != null) {
				Composite part = (Composite)e.getSource();
			}
			e.gc.drawRectangle(viewport.getLocation().x, viewport.getLocation().y, viewport.getWidth(), viewport.getHeight());
		}
		
	}
	
	public class MiniMapMouseListener implements MouseListener, MouseMoveListener {
		
		private boolean isDragging;
		private Point dragLocation;
		
		@Override
		public void mouseDoubleClick(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseDown(MouseEvent e) {
			if (viewport.containsPoint(new Point(e.x, e.y))) {
				isDragging = true;
				dragLocation = new Point(e.x, e.y);
			}
		}
		
		@Override
		public void mouseUp(MouseEvent e) {
			isDragging = false;
		}
		
		@Override
		public void mouseMove(MouseEvent e) {
			Composite part = (Composite)e.getSource();
			if (isDragging) {
				int x = viewport.getLocation().x + e.x - dragLocation.x;
				int y = viewport.getLocation().y + e.y - dragLocation.y;
				if (x+viewport.width >= part.getClientArea().width) {
					x = part.getClientArea().width - viewport.width;
				}
				else if (x < 0) {
					x = 0;
				}
				if (y+viewport.height >= part.getClientArea().height) {
					y = part.getClientArea().height - viewport.height;
				}
				else if (y < 0) {
					y = 0;
				}
				viewport.setLocation(new Point(x, y));
				dragLocation = new Point(e.x,  e.y);
			}
			part.redraw();
		}
		
	}

}
