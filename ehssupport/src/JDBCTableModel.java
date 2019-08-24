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

public class JDBCTableModel extends DefaultTableModel {
		Object[][] contents;
		String[] columnNames;
		Class[] columnClasses;

		public JDBCTableModel (java.sql.Connection conn,
				   String tableName)
			throws SQLException {
			super();
			getTableContents (conn, tableName);

		}
		protected void getTableContents (java.sql.Connection conn,
					 String tableName)
			throws SQLException {

		// get metadata: what columns exist and what
		// types (classes) are they?
		DatabaseMetaData meta = conn.getMetaData();
		//System.out.println ("got meta = " + meta);
		ResultSet results =
			meta.getColumns (null, null, tableName, null) ;
		//System.out.println ("got column results");
		ArrayList colNamesList = new ArrayList();
		ArrayList colClassesList = new ArrayList();
		while (results.next()) {
			colNamesList.add (results.getString ("COLUMN_NAME")); 
			//System.out.println ("name: " + 
			//	results.getString ("COLUMN_NAME"));
			int dbType = results.getInt ("DATA_TYPE");
			switch (dbType) {
			case Types.INTEGER:
		colClassesList.add (java.lang.Integer.class); break; 
			case Types.FLOAT:
		colClassesList.add (java.lang.Float.class); break; 
			case Types.DOUBLE: 
			case Types.REAL:
		colClassesList.add (java.lang.Double.class); break; 
			case Types.DATE: 
			case Types.TIME: 
			case Types.TIMESTAMP:
		colClassesList.add (java.sql.Date.class); break; 
			default:
		colClassesList.add (String.class); break; 
			}; 
			//System.out.println ("type: " +
			//	results.getInt ("DATA_TYPE"));
			}
			columnNames = new String [colNamesList.size()];
			colNamesList.toArray (columnNames);
			columnClasses = new Class [colClassesList.size()];
			colClassesList.toArray (columnClasses);
			
			// get all data from table and put into
			// contents array

			java.sql.Statement statement =
		conn.createStatement ();
			results = statement.executeQuery ("SELECT * FROM " +
						  tableName);
			ArrayList rowList = new ArrayList();
			while (results.next()) {
		ArrayList cellList = new ArrayList(); 
		for (int i = 0; i<columnClasses.length; i++) { 
			Object cellValue = null;


			if (columnClasses[i] == String.class) 
		cellValue = results.getString (columnNames[i]); 
			else if (columnClasses[i] == java.lang.Integer.class) 
		cellValue = new java.lang.Integer ( 
				results.getInt (columnNames[i])); 
			else if (columnClasses[i] == java.lang.Float.class) 
		cellValue = new java.lang.Float ( 
				results.getInt (columnNames[i])); 
			else if (columnClasses[i] == java.lang.Double.class) 
		cellValue = new java.lang.Double ( 
				results.getDouble (columnNames[i]));
			else if (columnClasses[i] == java.sql.Date.class) 
		cellValue = results.getDate (columnNames[i]); 
			else 
		System.out.println ("Can't assign " + 
				columnNames[i]);
			cellList.add (cellValue);
		}// for
		Object[] cells = cellList.toArray();
		rowList.add (cells);
		
	} // while
	// finally create contents two-dim array
	contents = new Object[rowList.size()] [];
	for (int i=0; i<contents.length; i++)

		contents[i] = (Object []) rowList.get (i);
	//System.out.println ("Created model with " +
	//		   contents.length + " rows");

	// close stuff
	results.close();
	statement.close();

	}
	// AbstractTableModel methods
	public int getRowCount() {
		return contents.length;
	}

	public int getColumnCount() {
		if (contents.length == 0)
			return 0;
		else
			return contents[0].length;
		}

		public Object getValueAt (int row, int column) {
			return contents [row][column];
		}

		// overrides methods for which AbstractTableModel
		// has trivial implementations

		public Class getColumnClass (int col) {
			return columnClasses [col];
		}

		public String getColumnName (int col) { 
			return columnNames [col]; 
		} 
	}
