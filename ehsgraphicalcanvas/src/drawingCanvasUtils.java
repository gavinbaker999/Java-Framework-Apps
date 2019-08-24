import java.awt.event.MouseEvent;
import java.util.Vector;

public interface drawingCanvasUtils { 
		public void rightClickAction(Vector v,MouseEvent evt);
		public void leftClickAction(drawingItem d,MouseEvent evt);
		public void leftClickSelectedAction(Vector v,MouseEvent evt);
		public void doubleClickAction(drawingItem d,MouseEvent evt);
		public void hoverAction(drawingItem d,MouseEvent evt);
		public void customDoCommand(String cmd,String params,drawingItem d);
		public boolean canvasDoubleClickAction(MouseEvent evt);
		public boolean canvasLeftClickAction(MouseEvent evt);
	}
