import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.datatransfer.*;
import java.awt.font.*;
import java.awt.color.*;				// ColorSpace
import java.awt.geom.*;					// AffineTransform
import java.sql.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.List;
import java.awt.print.*;
import java.util.Date;
import java.text.*;
import java.beans.*;
import java.lang.reflect.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.concurrent.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.Timer;
import javax.sound.sampled.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.OutputKeys.*;
import javax.xml.ws.*;
import javax.xml.ws.handler.*;
import javax.xml.ws.handler.soap.*;
import javax.xml.soap.*;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

//import javax.xml.messaging.*;
import javax.xml.xpath.XPath; 
import javax.xml.xpath.XPathConstants; 
import javax.xml.xpath.XPathExpressionException; 
import javax.xml.xpath.XPathFactory;
import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.XMLEventAllocator;
import javax.xml.transform.Source;

import javax.script.*;
import javax.swing.filechooser.*;

import javax.sound.sampled.*;
import sun.audio.*;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioFileFormat;
import javax.imageio.event.*;
import javax.imageio.metadata.*;
import javax.imageio.plugins.jpeg.*;
import javax.imageio.plugins.bmp.*;
import javax.imageio.spi.*;
import javax.imageio.stream.*;
import javax.imageio.*;
import javax.activation.URLDataSource.*;

import java.awt.geom.Point2D.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.HTMLEditorKit.*;
import javax.swing.text.html.HTMLDocument.*;
import javax.swing.text.html.ParagraphView;

import javax.swing.text.Element;
import javax.swing.text.Document;

import java.sql.*;
//import javax.jms.*;
import javax.naming.*;

//import javax.xml.xquery.XQConnection;
//impordt javax.xml.xquery.XQDataSource;
//import javax.xml.xquery.XQException;
//import javax.xml.xquery.XQPreparedExpression;
//import javax.xml.xquery.XQResultSequence;
//import com.saxonica.xqj.SaxonXQDataSource;

public class statusCanvasDialog extends JDialog implements TableModelListener {

		// this is an inner classs so it has access to the 'props' variable ...
		public class statusCanvasTableCellRenderer extends DefaultTableCellRenderer {
			public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,
                                                 boolean hasFocus,int row,int column) {
				Component c = super.getTableCellRendererComponent(table, value,isSelected, hasFocus,row, column);
			
				statusCanvasProp tmp = (statusCanvasProp)props.elementAt(row);
				if (tmp.getComboOpts() != null) {
					JComboBox cb = new JComboBox();
					String[] opts = tmp.getComboOpts();
					String propValue = tmp.getValue();
					cb.addItem(propValue);
					for (int i=0;i<opts.length;i++) {
						if (!propValue.equals(opts[i])) {cb.addItem(opts[i]);}
					}
					c = cb;
				}
			
				return c;
			}
		}
		// this is an inner classs so it has access to the 'props' variable ...
		public class JTableStatusCanvas extends JTableExtra {
		
			public JTableStatusCanvas(DefaultTableModel m,String[] tips) {
				super(m,tips); 
			}
			public TableCellEditor getCellEditor(int row, int column)
			{	
				int modelColumn = convertColumnIndexToModel( column );
				statusCanvasProp tmp = (statusCanvasProp)props.elementAt(row);
				if (tmp.getComboOpts() != null) {
					JComboBox comboBox1 = new JComboBox( tmp.getComboOpts() );
					return new DefaultCellEditor( comboBox1 );
				}

				return super.getCellEditor(row, column); 
			}
		}

		private JTableStatusCanvas statusTable;
		private statusCanvasTableModel statusTableM;
		private String[] colNames = {"Name","Value"};
		private Vector	props = new Vector();
		private	statusCanvasDialogListener target = null;
		private String title = "";
		
		public Vector getProps() {return props;}
		public void addStatusCanvasDialogListener(statusCanvasDialogListener dcu) {target = dcu;}
		public void removeStatusCanvasDialogListener() {target = null;}
		public void tableChanged(TableModelEvent evt) {
			int row = evt.getFirstRow();
			int column = evt.getColumn();
			TableModel model = (TableModel)evt.getSource();
			if (row == -1 || column == -1) {return;}
			//TRACE("tableChanged: row="+String.valueOf(row)+", column="+String.valueOf(column),4);
			// get and set table data using getValueAt(row,column) and setValueAt(row,column)
			// we need to cast them to the appropriate data types as stored as type Object
			statusCanvasProp tmp = (statusCanvasProp)props.elementAt(row);
			Object o =  statusTable.getValueAt(row,column);
			tmp.setValue((String)o);
			
			if (target != null) {
				target.propTableUpdated(title,(String)statusTable.getValueAt(row,0),(String)(o));
			}
		}
		public void setComboOptsByName(String name,String[] opts) {
			for (int j=0;j<props.size();j++) {
				statusCanvasProp tmp = (statusCanvasProp)props.elementAt(j);
				if (name.equals(tmp.getName())) {tmp.setComboOpts(opts);}
			}
			statusTableM.setProps(props);
		}
		public void setEnabledPropByName(String name,boolean enable) {
			for (int j=0;j<props.size();j++) {
				statusCanvasProp tmp = (statusCanvasProp)props.elementAt(j);
				if (name.equals(tmp.getName())) {tmp.setEnabled(enable);}
			}
			statusTableM.setProps(props);
		}
		public String getPropByName(String name) {
			for (int j=0;j<props.size();j++) {
				statusCanvasProp tmp = (statusCanvasProp)props.elementAt(j);
				if (name.equals(tmp.getName())) {return tmp.getValue();}
			}
			return "";
		}
		public void setPropByName(String name,String value) {
			int tableRow = -1;
			for (int j=0;j<props.size();j++) {
				statusCanvasProp tmp = (statusCanvasProp)props.elementAt(j);
				if (name.equals(tmp.getName())) {tmp.setValue(value);tableRow = j;}
			}
			if (tableRow != -1) {
				statusTableM.setProps(props);
				statusTableM.setValueAt((Object)value,tableRow,1);
				statusTableM.fireTableDataChanged();
			}
		}
		public String[][] buildStatusCanvasTableCells() {
			int columns = colNames.length;
			String[][] cells = new String[props.size()][columns];
			int i = 0;
			for (int j=0;j<props.size();j++) {
				statusCanvasProp tmp = (statusCanvasProp)props.elementAt(j);
				cells[i][0] = tmp.getName();  
				cells[i][1] = tmp.getValue(); 
				i++;
			}
			return(cells);
		}
		
		public statusCanvasDialog(Frame parent,String msg,Vector p1) {
			super(parent,msg,false);
			
			title = msg;
			
			String[] columnToolTips = {
				"The name of the property",
				"The value of the property"};

			target = null;
			props = p1;
			String[][]data = buildStatusCanvasTableCells();
			statusTableM = new statusCanvasTableModel(data,colNames);
			statusTableM.addTableModelListener(this);
			statusTableM.setProps(props);
			statusTable = new JTableStatusCanvas(statusTableM,columnToolTips);

			// 1 because thats the value column
			TableColumn tc1 = statusTable.getColumnModel().getColumn(1);
			tc1.setCellRenderer(new statusCanvasTableCellRenderer());
	
			TableColumn tc = statusTable.getColumn(colNames[0]);
			tc.setPreferredWidth(50); // set all three widths to get a fixed size column
			tc.setMaxWidth(50);
			tc.setMinWidth(50);
			statusTable.sizeColumnsToFit(JTable.AUTO_RESIZE_OFF); // needed because bug in JVM
			statusTable.setPreferredScrollableViewportSize(new Dimension(200,160));
			JScrollPane statusTablePane = new JScrollPane(statusTable);
			
			JPanel p = new JPanel();
			p.add(statusTablePane);
			add(p);
	   	    addComponentListener(new ComponentAdapter() {
				public void componentMoved(ComponentEvent evt) {
				}
			});
			pack();
			setVisible(true);
		}
		
		public void destory() {
			dispose();
		}
	}
