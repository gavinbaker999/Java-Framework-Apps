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


public class JTableExtra extends JTable {
		protected String[] columnToolTips; // element can be null if there is no tooltip text for a column
			
		public JTableExtra() {
			super();
			setTipTexts((String[])null);
		}
		public JTableExtra(String[] tips) {
			super();
			setTipTexts(tips);
		}
		public JTableExtra(int rows,int cols) {
			super(rows,cols);
			setTipTexts((String[])null);
		}
		public JTableExtra(int rows,int cols,String[] tips) {
			super(rows,cols);
			setTipTexts(tips);
		}
		public JTableExtra(Vector data,Vector colnames) {
			super(data,colnames);
			setTipTexts((String[])null);
		}
		public JTableExtra(Vector data,Vector colnames,String[] tips) {
			super(data,colnames);
			setTipTexts(tips);
		}
		public JTableExtra(Object[][] data,Object[] colnames) {
			super(data,colnames);
			setTipTexts((String[])null);
		}
		public JTableExtra(Object[][] data,Object[] colnames,String[] tips) {
			super(data,colnames);
			setTipTexts(tips);
		}
		public JTableExtra(DefaultTableModel m) {
			super(m); // call normal JTable class constructor with a table model parameter
			setTipTexts(null);
		}
		public JTableExtra(DefaultTableModel m,String[] tips) {
			super(m); // call normal JTable class constructor with a table model parameter
			setTipTexts(tips);
		}
		
		public void createComboBoxRender(String[] options,int colNumber) {
			JComboBox cb = new JComboBox();
			TableColumn tc = getColumnModel().getColumn(colNumber);
			
			for (int i=0;i<options.length;i++) {
				cb.addItem(options[i]);
			}
			tc.setCellEditor(new DefaultCellEditor(cb));
		}
		
		public void removeTipTexts() {
			setTipTexts(null);
		}
		public void setTipTexts(String[] tips) {
			columnToolTips = tips;
		}
		//implement table cell tool tips
		public String getToolTipText(MouseEvent e) {
			String tip = null;
			java.awt.Point p = e.getPoint();
			int rowindex = rowAtPoint(p);
			int colindex = columnAtPoint(p);
			// user could have changed the order of the columns
			int realIndex = convertColumnIndexToModel(colindex);
			tip = getCellToolTipText(rowindex,realIndex);
			if (tip == null) {tip = super.getToolTipText(e);}
			return tip;
		}
		// override following function to return text of cell's tool tip or null if no tool tip text
		public String getCellToolTipText(int row,int column) {
			return null;
		}	
		//Implement table header tool tips.
		protected JTableHeader createDefaultTableHeader() {
			return new JTableHeader(columnModel) {
				public String getToolTipText(MouseEvent e) {
					String tip = null;
					java.awt.Point p = e.getPoint();
					int index = columnModel.getColumnIndexAtX(p.x);
					// user could have changed the order of the columns
					int realIndex = columnModel.getColumn(index).getModelIndex();
					if (columnToolTips == null) {return "";}
					return columnToolTips[realIndex];
				}
			};
		}		
   	}
