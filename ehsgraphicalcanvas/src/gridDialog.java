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

public class gridDialog extends JDialog  implements ActionListener {
		private JButton	butDelete,butAdd,butOK,butCancel;
		protected	multiColumnCanvasComponent choice = null;
		private String dialogTitle;
		private boolean bAllowEdits = false;
		private String[] colTitles;
		private String[][] colOptions;
		private boolean bOk = false;
		private int charWidth = 8;
		private int charHeight = 14;

		// forwarding functions to the component
		public void addmultiColumnCanvasDialogListener(multiColumnCanvasDialogListener dcu) {
			choice.addmultiColumnCanvasDialogListener(dcu);
		}
		public void removemultiColumnCanvasDialogListener() {choice.removemultiColumnCanvasDialogListener();}
		
		gridDialog (Frame f,String dialogTitle,String[] colTitles,String dialogData,String[][] colOptions,boolean bAllowEdits) {
			super(f,dialogTitle,true);
			this.dialogTitle = dialogTitle;
			this.colTitles = colTitles;
			this.colOptions = colOptions;
			this.bAllowEdits = bAllowEdits;
				  
			addWindowListener(new WindowAdapter() {
				public void WindowClosing(WindowEvent evt) {
					destroy();
				}
			});

			Vector v = supportFunctions.splitIntoTokens(dialogData,",");
			choice = new multiColumnCanvasComponent();
			
			int cols = colTitles.length;
			int rows = dialogData.length() / cols;
			String[][] cellData = new String[rows][cols];
			int index = 0;
			for (int i=0;i<rows;i++) {
				for (int j=0;j<cols;j++) {
					cellData[i][j] = (String)v.elementAt(index++);
				}
			}
			String[] colData = new String[rows];
			for (int j=0;j<cols;j++) {
				for (int i=0;i<rows;i++) {
					colData[i] = cellData[i][j];
				}
				if (colOptions[j] == null) {
					choice.addColumn(colTitles[j], true, colData);
				}else {
					choice.addComboColumn(colTitles[j], true, colData, colOptions[j]);
				}
			}
			
			choice.create();
			choice.getTable().setColumnSelectionAllowed(false);
			choice.getTable().setRowSelectionAllowed(true);
			choice.getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			butAdd = new JButton("Add");
			butDelete = new JButton("Delete");
			butOK = new JButton("OK");
		    butCancel = new JButton("Cancel");
		    butAdd.addActionListener(this);
		    butDelete.addActionListener(this);
		    butOK.addActionListener(this);
		    butCancel.addActionListener(this);
		   
			JPanel butPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		    if (bAllowEdits) {
				butPanel.add(butAdd);
				butPanel.add(butDelete);
		    }
			butPanel.add(butOK);
		    butPanel.add(butCancel);

		    JPanel p = new JPanel();
		    p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));		   
			JScrollPane sp = choice.getComponent();
			sp.setPreferredSize(new Dimension(50*charWidth,10*charHeight));
 			p.add(sp);
			p.add(butPanel);

		    add(p,"Center");
		   		
		    pack();
		    setVisible(true);
		}		
	    
		public String getData() {
			String tmp = "";
			int numRows = choice.getNumRows();
		    for(int i=0;i<numRows;i++) {
				String[] tmp1 = choice.getDataRow(i);
				tmp = tmp + tmp1[0] + "," + tmp1[1] + ",";
			}
			
			tmp = tmp.substring(0,tmp.length()-1); // remove last , character
			return tmp;
		}

		public void destroy() {
			setVisible(false);
			dispose();
		}
		public boolean getOK() {return bOk;}
		public void actionPerformed(ActionEvent evt) {
		   if (evt.getSource() == butOK) {
				bOk = true;
				destroy();
		   }
		   if (evt.getSource() == butCancel) {
				bOk = false;
				destroy();
		   }
		   if (evt.getSource() == butAdd) {
			String[] tmp = new String[choice.getNumColumns()];
			for (int i=0;i<choice.getNumColumns();i++) {
				tmp[i] = "xxx";
			}
			choice.getModel().addRow(tmp);
			for (int i=0;i<choice.getNumColumns();i++) {
				choice.addRowDataItem(tmp[0],i,0);				
			}
		   }
		   if (evt.getSource() == butDelete) {
			int[] rows = choice.getTable().getSelectedRows();
			if (rows.length == 0) {supportFunctions.displayMessageDialog(null,"Select Row(s) First");return;}
			for (int i=0;i<rows.length;i++) {
				if(rows[i] != 0) {
					int index = rows[i]; //choice.getTable().ConvertRowIndexToModel(rows[i]);
					choice.getModel().removeRow(index);
					for (int ii=0;ii<choice.getNumColumns();ii++) {
						choice.deleteRowDataItem(ii,index);
					}
				} else {
					supportFunctions.displayMessageDialog(null,"Can't delete this row");
				}
			}
		   }
	    }
	}
