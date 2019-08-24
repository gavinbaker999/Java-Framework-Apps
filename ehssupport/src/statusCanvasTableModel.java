import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class statusCanvasTableModel extends DefaultTableModel {
		   private Vector	props = new Vector();
		   
		   statusCanvasTableModel(String[][] data,String[] colNames) {
		      super(data,colNames);
		   }
		   public Class getColumnClass(int column) {
			return (getValueAt(0,column).getClass());
		   }
		   public boolean isCellEditable(int row,int column) {
				statusCanvasProp tmp = (statusCanvasProp)props.elementAt(row);
				if (tmp.getEnabled() == false) {return false;}
				if (column == 1) {return true;} else {return false;}
		   }
		   public void setProps(Vector v) {props=v;}
	}
