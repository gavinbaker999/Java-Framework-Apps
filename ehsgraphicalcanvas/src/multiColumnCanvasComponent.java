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

public class multiColumnCanvasComponent implements TableModelListener {
		private JTableExtra table;
		private multiColumnCanvasTableModel model;
		private Vector cols = new Vector();
		private JScrollPane spane;
		private	multiColumnCanvasDialogListener target = null;
		
		public void addmultiColumnCanvasDialogListener(multiColumnCanvasDialogListener dcu) {target = dcu;}
		public void removemultiColumnCanvasDialogListener() {target = null;}
		public void tableChanged(TableModelEvent evt) {
			int row = evt.getFirstRow();
			int column = evt.getColumn();
			TableModel model = (TableModel)evt.getSource();
			if (row == -1 || column == -1) {return;}
			column = table.convertColumnIndexToModel(column);
			//TRACE("tableChanged: row="+String.valueOf(row)+", column="+String.valueOf(column),4);
			// get and set table data using getValueAt(row,column) and setValueAt(row,column)
			// we need to cast them to the apporiate data types as stored as type Object
			Object o =  table.getValueAt(row,column);
			setDataCell(row,column,(String)o);
			
			//TRACE("MCCD: Target NOT NULL: " + (String)(o),4);
			if (target != null) {
				target.multiColumnCanvasUpdated((String)(o),row,column);
			}
		}
		public multiColumnCanvasTableModel getModel() {return model;}
		public JTableExtra getTable() {return table;}
		public void addDBColumn(String title,boolean enabled,String tableName,String colName,String unqColName) {
			cols.addElement(new multiColumnCanvasCol(title,enabled,tableName,colName,unqColName));
		}
		public void addColumn(String title,boolean enabled,String[] data) {
			cols.addElement(new multiColumnCanvasCol(title,enabled,data));
		}
		public void addComboColumn(String title,boolean enabled,String[] data,String[] options) {
			cols.addElement(new multiColumnCanvasCol(title,enabled,data,options));
		}
		public multiColumnCanvasCol getColumn(int index) {
			if (index > cols.size()) {return null;}
			return (multiColumnCanvasCol) cols.elementAt(index);
		}
		public int getNumColumns() {return cols.size();}
		public int getNumRows() {
			if (cols.size() == 0) {return 0;}
			multiColumnCanvasCol col = (multiColumnCanvasCol)cols.elementAt(0); // assume all columns have the same number of data items in
			return col.getNumDataItems();
		}
		public JScrollPane getComponent() {return spane;}
		public String[] getColumnNames() {
			String[] tmp = new String[cols.size()];
			for (int i=0;i<cols.size();i++) {
				multiColumnCanvasCol col = (multiColumnCanvasCol)cols.elementAt(i);
				tmp[i] = col.getTitle();
			}
			
			return tmp;
		}
		public String[][] buildComponentData() {
			int numCols = cols.size();
			multiColumnCanvasCol col = (multiColumnCanvasCol)cols.elementAt(0);
			int numRows = col.getNumDataItems();
			
			// fill up the column names and table data
			String[][] data = new String[numRows][numCols];
			for (int i=0;i<numCols;i++) {
			multiColumnCanvasCol tmp = (multiColumnCanvasCol)cols.elementAt(i);
			String[] s = tmp.getData();
				for (int j=0;j<s.length;j++) {
					data[j][i] = s[j];
				}
			}
			
			return data;
		}
		public void create() {
			model = new multiColumnCanvasTableModel(buildComponentData(),getColumnNames());
			model.setColumns(cols); // is there a better way ?!?
			table = new JTableExtra(model);

			for (int i=0;i<cols.size();i++) {
				multiColumnCanvasCol col = (multiColumnCanvasCol)cols.elementAt(i);			
				if (col.isComboCol()) {
					table.createComboBoxRender(col.getComboOpts(),i);
				}
			}
			
			table.sizeColumnsToFit(JTable.AUTO_RESIZE_OFF); // needed because bug in JVM
			model.addTableModelListener(this);
			spane = new JScrollPane(table);			
		}
		public void setDataCell(int row,int col,String data) {
			if (col >= cols.size()) {return;}
			multiColumnCanvasCol tmp = (multiColumnCanvasCol)cols.elementAt(col);
			tmp.setData(row,data);
			model.setColumns(cols); // is there a better way ?!? update model data set
		}
		public String getDataCell(int row,int col) {
			if (col >= cols.size()) {return null;}
			multiColumnCanvasCol tmp = (multiColumnCanvasCol)cols.elementAt(col);
			return tmp.getData(row);
		}
		public String[] getDataCol(int col) {
			if (col >= cols.size()) {return null;}
			multiColumnCanvasCol tmp = (multiColumnCanvasCol)cols.elementAt(col);
			return tmp.getData();
		}
		public String[] getDataRow(int row) {
			if (cols.size() == 0) {return null;}
			multiColumnCanvasCol col = (multiColumnCanvasCol)cols.elementAt(0);
			int numRows = col.getNumDataItems();
			if (row >= numRows) {return null;}
			String[] data = new String[cols.size()];
			for (int i=0;i<cols.size();i++) {
				data[i] = getDataCell(row,i);
			}
			
			return data;
		}
		public void setRowData(int row,String[] data) {
			if (cols.size() == 0) {return;}
			multiColumnCanvasCol col = (multiColumnCanvasCol)cols.elementAt(0);
			int numRows = col.getNumDataItems();
			if (row >= numRows) {return;}
			for (int i=0;i<cols.size();i++) {
				setDataCell(row,i,data[i]);
			}
			model.setColumns(cols); // is there a better way ?!? update model data set
			model.fireTableDataChanged();
		}
		public void setColData(int col,String[] data) {
			if (col >= cols.size()) {return;}
			multiColumnCanvasCol tmp = (multiColumnCanvasCol)cols.elementAt(col);
			tmp.setData(data);
			model.setColumns(cols); // is there a better way ?!? update model data set
			model.fireTableDataChanged();
		}
		public void addRowDataItem(String s,int col,int index) {
			if (col >= cols.size()) {return;}
			multiColumnCanvasCol tmp = (multiColumnCanvasCol)cols.elementAt(col);
			tmp.addDataItem(s,index); // add index in latter!!! at moment only adds to end of list
			model.setColumns(cols); // is there a better way ?!? update model data set
			model.fireTableDataChanged();
		}
		public void deleteRowDataItem(int col,int index) {
			if (col >= cols.size()) {return;}
			multiColumnCanvasCol tmp = (multiColumnCanvasCol)cols.elementAt(col);
			tmp.deleteDataItem(index);
			model.setColumns(cols); // is there a better way ?!? update model data set
			model.fireTableDataChanged();
		}
	}
