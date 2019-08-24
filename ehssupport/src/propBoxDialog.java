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

public class propBoxDialog extends JDialog implements TableModelListener {
		private JButton	OKBut,CANCELBut;
		private JTableX statusTable; 
		private statusCanvasTableModel statusTableM;
		private String[] colNames = {"Name","Value"};
		private Vector	props = new Vector();
		private	boolean bOK = false;
		private	String id;
		private String title;
		private propBoxDialogListener target = null;
		private int charWidth = 10;
		private int charHeight = 10;

		public void addPropBoxDialogListener(propBoxDialogListener pbdl) {target = pbdl;}
		public void removePropBoxDialogListener() {target = null;}
		public void saveAsXML(String name) {
			configurationSettings settings = new configurationSettings();
			settings.openConfigurationSettings(name+"_"+id);
			for (int j=0;j<props.size();j++) {
				statusCanvasProp tmp = (statusCanvasProp)props.elementAt(j);
				settings.setConfigurationSetting(tmp.getName(),tmp.getValue());
			}
			settings.closeConfigurationSettings();
		}
		public void loadAsXML(String name) {
			configurationSettings settings = new configurationSettings();
			settings.openConfigurationSettings(name+"_"+id);
			for (int j=0;j<props.size();j++) {
				statusCanvasProp tmp = (statusCanvasProp)props.elementAt(j);
				tmp.setValue(settings.getConfigurationSetting(tmp.getName(),""));
			}
			settings.closeConfigurationSettings();
		}
		public void createComboBoxRender(String[] options,int rowNumber) {
			JComboBox cb = new JComboBox();
			//TableColumn tc = statusTable.getColumnModel().getColumn(colNumber);
			
			for (int i=0;i<options.length;i++) {
				cb.addItem(options[i]);
			}
			//tc.setCellEditor(new DefaultCellEditor(cb));
			statusTable.getRowEditorModel().addEditorForRow(rowNumber,new DefaultCellEditor(cb));
		}
		public JTable getStatusTable() {return statusTable;}
		public boolean isOK() {return bOK;}
		public Vector getProps() {return props;}
		public void tableChanged(TableModelEvent evt) {
			int row = evt.getFirstRow();
			int column = evt.getColumn();
			TableModel model = (TableModel)evt.getSource();
			if (row == -1 || column == -1) {return;}
			//TRACE("tableChanged: row="+String.valueOf(row)+", column="+String.valueOf(column),4);
			// get and set table data using getValueAt(row,column) and setValueAt(row,column)
			// we need to cast them to the apporiate data types as stored as type Object
			statusCanvasProp tmp = (statusCanvasProp)props.elementAt(row);
			Object o =  statusTable.getValueAt(row,column);
			tmp.setValue((String)o);
			if (target != null) {
				target.propChanged(title,tmp,(String)o);
			}
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
			for (int j=0;j<props.size();j++) {
				statusCanvasProp tmp = (statusCanvasProp)props.elementAt(j);
				if (name.equals(tmp.getName())) {tmp.setValue(value);}
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
		
		public propBoxDialog(Frame parent,String msg,Vector v,String id,propBoxDialogListener pbdl) {
			super(parent,msg,true);
			
			this.target = pbdl;
			this.id = id;
			this.title = msg;
			props = v;
			String[][]data = buildStatusCanvasTableCells();
			statusTableM = new statusCanvasTableModel(data,colNames);
			statusTableM.addTableModelListener(this);
			statusTableM.setProps(props);
			statusTable = new JTableX(statusTableM);
			statusTable.setPreferredScrollableViewportSize(new Dimension(250,(charHeight*props.size()))); // 100 GDB 20102016
			JScrollPane statusTablePane = new JScrollPane(statusTable);
			
	        // create a RowEditorModel... this is used to hold the extra
	        // information that is needed to deal with row specific editors
	        RowEditorModel rm = new RowEditorModel();
	        // tell the JTableX which RowEditorModel we are using
	        statusTable.setRowEditorModel(rm);
	        
			for(int i=0;i<props.size();i++) {
				statusCanvasProp tmp = (statusCanvasProp)props.elementAt(i);
				if (tmp.isCombo()) {createComboBoxRender(tmp.getComboOpts(),i);}
			}
			
			JPanel p = new JPanel();
			p.setBackground(Color.gray);
			p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
			p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
			p.add(statusTablePane);
			p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
			JPanel p1 = new JPanel();
			p1.setBackground(Color.gray);
			p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
			OKBut = new JButton("Ok");
			p1.add(OKBut);
			CANCELBut = new JButton("Cancel");
			p1.add(CANCELBut);
			JPanel p3 = new JPanel();
			p3.setBackground(Color.gray);
			p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
			p3.add(p);
			p3.add(p1);
			add(p3);

			ActionListener OKTask = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					bOK = true;
					dispose();
			   }
			};
			OKBut.addActionListener(OKTask);
			ActionListener CANCELTask = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					for (int j=0;j<props.size();j++) {
						statusCanvasProp tmp = (statusCanvasProp)props.elementAt(j);
						tmp.setValue(tmp.getIntValue());
					}
					dispose();
			   }
			};
			CANCELBut.addActionListener(CANCELTask);
	   	    addComponentListener(new ComponentAdapter() {
				public void componentMoved(ComponentEvent evt) {
					//savePosition(systemUserReg.getAppSerialBase() + systemUserReg.getUserName() + "stc");
				}
			});
			bOK = false;
			pack();
			//loadPosition(systemUserReg.getAppSerialBase() + systemUserReg.getUserName() + "stc");
			setVisible(true);
		}
		
		public void destory() {
			dispose();
		}
	}
