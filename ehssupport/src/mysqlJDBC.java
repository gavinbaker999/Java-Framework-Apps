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

public class mysqlJDBC {
		private	java.sql.Connection 	conn = null;
		private	Vector		logLines = new Vector();
		private boolean		bLogging = false;

		public JTableExtra getDatabaseTableAsTable(String tableName) {
			JTableExtra jtable = null;
			
			try {
				if (conn == null) {return (JTableExtra)null;}
				JDBCTableModel mod = new JDBCTableModel(conn, tableName);
				jtable = new JTableExtra(mod);
		//		JScrollPane scroller =
		//			new JScrollPane (jtable, 
		//				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
		//				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			} catch(Exception e) {;}
		
			return jtable;
		}
		public void setLogging(boolean b) {bLogging = b;}
		public void logLine(String s) {logLines.addElement(s);}
		public void clearLog() {logLines.removeAllElements();}
		public void writeLog() {
			pseduoFile logFile = new pseduoFile("sqlquery.log");
			for (int i=0;i<logLines.size();i++) {
				logFile.appendFile((String)logLines.elementAt(i));
			}
			logFile.flush();
		}

		public boolean connect() {
			conn = null;
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				System.err.println("Mysql JDBC driver loaded");
				conn = DriverManager.getConnection("jdbc:mysql://" + ehsConstants.dbHost 
						+ "/" + ehsConstants.dbName,ehsConstants.dbUser,ehsConstants.dbPassword);
			} 
			catch(ClassNotFoundException e) {e.printStackTrace(); 
				System.err.println("Class not found");
			} 
			catch(IllegalAccessException e) {e.printStackTrace();
				System.err.println("Illegal access exception");
			}
			catch(InstantiationException e) {e.printStackTrace();
				System.err.println("InstantiationException exception");
			}
			catch(SQLException e) {e.printStackTrace();
				System.err.println("SQLException exception");
				System.err.println(e.getMessage() + ":" + e.getSQLState());
			}
			if(conn == null) {
				System.err.println("JDBC did not connect");
				return false;
			}
			System.err.println("JDBC connect sucess");
			System.err.println("Product:"+getDatabaseProduct());
			System.err.println("Version:"+getDatabaseVersion());
			return true;
		}
		public void disconnect() {
			if (conn != null) {
				try {
					conn.close();
					System.err.println("JDBC Connection Closed");
				} catch (SQLException e) {
					System.err.println(e.getMessage() + ":" + e.getSQLState());
				}
			}
			conn = null;
			clearLog();
			setLogging(false);
		}
		public String getDatabaseProduct() {
			if (conn == null) {return "";}
			try {
				DatabaseMetaData dbMD = conn.getMetaData();
				return dbMD.getDatabaseProductName();
			} catch (SQLException e) {;}
			return "";
		}
		public String getDatabaseVersion() {
			if (conn == null) {return "";}
			try {
				DatabaseMetaData dbMD = conn.getMetaData();
				return dbMD.getDatabaseProductVersion();
			} catch (SQLException e) {;}
			return "";
		}
		public java.sql.Connection getConnection() {return conn;}
		public Vector getTableNames() {
			   Vector tableNames = new Vector();
			   if (conn != null) {
				   try {
					   DatabaseMetaData md = conn.getMetaData();
					   ResultSet mrs = md.getTables(null,null,null,new String[] {"TABLE"});
					   while (mrs.next()) {
						   tableNames.addElement(mrs.getString(3));
					   }
					   mrs.close();
				   }
				   catch (SQLException e) {
					   System.err.println(e.getMessage() + ":" + e.getSQLState());
				   }
			   }
			   return tableNames;
		}
		public Vector getColumnNames(String tableName) {
			   java.sql.Statement	stat = null;
			   String data = "";
			   ResultSet results = null;
			   Vector columnNames = new Vector();
			   if (conn != null) {
				   try {
					   stat = conn.createStatement();
					   results = stat.executeQuery("SELECT * FROM " + tableName + " LIMIT 1");
					   if (results != null) {
						   ResultSetMetaData mData = results.getMetaData();
						   int numCols = mData.getColumnCount();
						   for (int j=0;j<numCols;j++) 
						   	{columnNames.addElement(mData.getColumnName(j));}
						   results.close();
					   }
				   }
				   catch (SQLException e) {
					   System.err.println(e.getMessage() + ":" + e.getSQLState());
				   }
			   }
			   return columnNames;
		}
		public String executeSQLQuery(String query,String testData) {
			java.sql.Statement	stat = null;
			String data = "";
			ResultSet results = null;
			if (conn == null) {return testData;}
			if (bLogging) {logLine(query);}
			try {
				stat = conn.createStatement();
				if ( query.startsWith("SELECT") || query.startsWith("select")) {
					results = stat.executeQuery(query);
				} else {
					stat.executeUpdate(query);
				}
				if (results != null) {
					ResultSetMetaData mData = results.getMetaData();
					int numCols = mData.getColumnCount();
					while (results.next()) {
						for (int i=0;i<numCols;i++) {
							if (data.length() != 0) {data = data + ",";}
							// The first column in a ResultSet row has index 1, not 0
							data = data + results.getString(i+1);
						}
					}
				} else {
					data = testData;
				}

				SQLWarning w;
				for (w=conn.getWarnings();w != null;w=w.getNextWarning()) {
					System.err.println("SQLWarning : " + w.getMessage() + " : " + w.getSQLState());
				}

			} catch (SQLException e) {
				data = testData;
				System.err.println(e.getMessage() + ":" + e.getSQLState());
			}
					
			System.err.println("JDBC query data:"+data);
			if (bLogging) {logLine(data);}
			return data;
		}
	}
