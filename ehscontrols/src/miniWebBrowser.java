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
import java.awt.geom.*;
import java.lang.reflect.*;
import java.util.Date;
import java.util.Map;
import java.text.*;
import java.beans.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
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
import javax.script.*;
import javax.swing.filechooser.*;
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
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.activation.URLDataSource.*;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.Utilities;
import javax.swing.text.html.HTMLEditorKit.*;
import javax.swing.text.html.ParagraphView;
import javax.swing.text.html.HTMLDocument.*;

import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.DefaultStyledDocument;

import java.sql.*;
//import javax.jms.*;
import javax.naming.*;

//import javax.xml.xquery.XQConnection;
//import javax.xml.xquery.XQDataSource;
//import javax.xml.xquery.XQException;
//import javax.xml.xquery.XQPreparedExpression;
//import javax.xml.xquery.XQResultSequence;
//import com.saxonica.xqj.SaxonXQDataSource;

public class miniWebBrowser implements HyperlinkListener,PropertyChangeListener {
		private JEditorPane		ta;
		private	java.util.List	history = new ArrayList();
		private	int				MAX_HISTORY = 50;
		private	int				currentHistoryPage = -1;
		private	JToolBar		tools = new JToolBar();
		private backAction		backCommand = new backAction();
		private forwardAction	forwardCommand = new forwardAction();
		private printAction		printCommand = new printAction();
		private JPanel			webPanel = new JPanel();

		public class backAction extends AbstractAction {
			   public backAction() {super("Back");putValue(SHORT_DESCRIPTION,"Backward Page");}
			   public void actionPerformed(ActionEvent evt) {
				   back();
			   }
		}
		public class forwardAction extends AbstractAction {
			   public forwardAction() {super("Forward");putValue(SHORT_DESCRIPTION,"Forward Page");}
			   public void actionPerformed(ActionEvent evt) {
				   forward();
			   }
		}
		public class printAction extends AbstractAction {
			   public printAction() {super("Print");putValue(SHORT_DESCRIPTION,"Print Page");}
			   public void actionPerformed(ActionEvent evt) {
				   print();
			   }
		}
		
		private miniWebBrowser() {
			ta = new JEditorPane();			
			ta.addHyperlinkListener(this);
			ta.addPropertyChangeListener(this);
			ta.setEditable(false);
			ta.setContentType("text/html");
		}
		miniWebBrowser(URL u) {
			try {
				tools.add(backCommand);
				tools.add(forwardCommand);
				tools.add(printCommand);
				
				backCommand.setEnabled(false);
				forwardCommand.setEnabled(false);
				
				ta = new JEditorPane();			
				ta.addHyperlinkListener(this);
				ta.addPropertyChangeListener(this);
				ta.setEditable(false);
				ta.setPage(u);
				String ref = u.getRef();
				if (ref != null) {ta.scrollToReference(ref);}
				
				webPanel.add(new JScrollPane(ta),BorderLayout.CENTER);
				webPanel.add(tools,BorderLayout.NORTH);
				supportFunctions.displayPanelDialog(null,webPanel,"Mini Web Browser");
				
			} catch (Exception e) {}
		}
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals("page")) {
				// set when page has finished loading ...
			}
		}
		public void hyperlinkUpdate(HyperlinkEvent evt) {
			   if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			   	  try {
				  	  URL u = evt.getURL();
				  	  history.add(u);
				  	  int num = history.size();
				  	  if (num > MAX_HISTORY + 10) {
				  		  history = history.subList(num - MAX_HISTORY, num);
				  		  num = MAX_HISTORY;
				  	  }
				  	  currentHistoryPage = num - 1;
				  	  if (currentHistoryPage > 0) {
				  		  backCommand.setEnabled(true);
				  	  }
				  	  
					  ta.setPage(u);
					  String ref = u.getRef();
					  if (ref != null) {ta.scrollToReference(ref);}
				  } catch (IOException e) {}
			   }
			   if (evt.getEventType() == HyperlinkEvent.EventType.ENTERED) {
				   //showStatus(evt.getURL().toString());
			   }
			   if (evt.getEventType() == HyperlinkEvent.EventType.EXITED) {
				   //showStatus("");
			   }
		}
		public void back() {
			try {
				if (currentHistoryPage > 0) {
					ta.setPage((URL)history.get(--currentHistoryPage));
					backCommand.setEnabled(currentHistoryPage > 0);
					forwardCommand.setEnabled(currentHistoryPage < history.size() - 1);
				}
			} catch (Exception e) {;}
		}
		public void forward() {
			try {
				if (currentHistoryPage < history.size() - 1) {
					ta.setPage((URL)history.get(++currentHistoryPage));
					backCommand.setEnabled(currentHistoryPage > 0);
					forwardCommand.setEnabled(currentHistoryPage < history.size() - 1);
				}
			} catch (Exception e) {;}
		}
		public void print() {
			PrinterJob job = PrinterJob.getPrinterJob();
			PrintableDocument pd = new PrintableDocument(ta);
			job.setPageable(pd);
			if (job.printDialog()) {
				try {
					job.print();
				} catch (Exception e) {;}
		}
		}
	}
