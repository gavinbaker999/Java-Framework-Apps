import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class multiColumnCanvasTableModel extends DefaultTableModel {
		   private Vector cols = new Vector();
		   
		   multiColumnCanvasTableModel(String[][] data,String[] colNames) {
		      super(data,colNames);
		   }
		   public Class getColumnClass(int column) {
			return (getValueAt(0,column).getClass());
		   }
		   public boolean isCellEditable(int row,int column) {
				multiColumnCanvasCol tmp = (multiColumnCanvasCol)cols.elementAt(column);
				return tmp.getEnabled();
		   }
		   public void setColumns(Vector v) {cols = v;}
	}
