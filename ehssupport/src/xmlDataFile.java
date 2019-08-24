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

// Element elementa = getXMLDocument().createElement("name");
	// elementb.appendChild(elementa);
	// elementa.setAttribute("name","value");
	// The first Element you appendChild to will be getRootElement()
	public class xmlDataFile {
		private	String 		csName,csRootElement;
		private	xmlBase 	csFile;
		private org.w3c.dom.Document	xmlDoc;
		private	org.w3c.dom.Element		root;
		
		xmlDataFile() {
			csName = "";
			csRootElement = "";
			csFile = null;
			xmlDoc = null;
			root = null;
		}
		public xmlBase getXmlBase() {return csFile;}
		public void miniXMLEditor() {csFile.miniXMLEditor();}
		public String getRootElementAsString() {return csRootElement;}
		public String getFilename() {return csName;}
		public void setFilename(String s) {	
			if (csFile != null) {
				csName = s;
				csFile.setXmlFilename(s + ".xml");
			}
		}
		public void createXMLDataFile(String name,String rootElement) {
			//TRACE("Creating XML data file " + name + " with root element of " + rootElement,4);
			csFile = new xmlBase();
			csFile.setXmlFilename(name + ".xml");
			xmlDoc = csFile.getXmlDocument();
			root = xmlDoc.createElement(rootElement);
			xmlDoc.appendChild(root);
			csFile.saveXmlFile(true);
		}
		public boolean openXMLDataFile(String name,String rootElement) {
			return openXMLDataFile(name,rootElement,true);
		}
		public boolean openXMLDataFile(String name,String rootElement,boolean bForceCreate) {
			csName = name;
			csRootElement = rootElement;
			//TRACE("Trying to open XML data file " + name + " with root element of " + rootElement,4);
			if (!supportFunctions.urlExists(name + ".xml")) {
				//TRACE("XML data file " + name + ".xml url does not exist",4);
				if (bForceCreate) {createXMLDataFile(name,rootElement);} else {return false;}
			} else {
				if (bForceCreate) {createXMLDataFile(name,rootElement);return true;}
				//TRACE("Opening XML data file " + name + " with root element of " + rootElement,4);
				csFile = new xmlBase(name + ".xml");
				xmlDoc = csFile.getXmlDocument();
				if (xmlDoc == null) {
					return false;
				}
				root = xmlDoc.getDocumentElement();
			}
			
			return true;
		}
		public NodeList buildNodeList(String nodeName) {
			NodeList nl = xmlDoc.getElementsByTagName(nodeName);
			int num = nl.getLength();
			for (int i=0;i<num;i++) {
				processNode(nl.item(i));
			}
			return nl;
		}
		public void processNode(org.w3c.dom.Node n) {
			String name = n.getNodeName();
			short type = n.getNodeType();
			String value = n.getNodeValue(); // needs to child node value !!!
			NamedNodeMap attribs = n.getAttributes();
			String nodeAttribs = "";
			if (attribs != null && attribs.getLength() < 0) {
				for (int index=0;index<attribs.getLength();index++) {
					if (index != 0) {nodeAttribs = nodeAttribs + ",";}
					org.w3c.dom.Node n2 = attribs.item(index);
					nodeAttribs = nodeAttribs + n2.getNodeName() + "," + n2.getNodeValue();
				}
			}

			nodeFound(n,name,value,nodeAttribs);
		}
		public void nodeFound(org.w3c.dom.Node n,String name,String value,String attribs) {}
		public org.w3c.dom.Element getRootElement() {return root;}
		public org.w3c.dom.Document getXMLDocument() {return xmlDoc;}
		public boolean isOpen() {if (csFile == null) {return false;} else {return true;}}
		protected void finalize() throws Throwable {
			   //closeXMLDataFile();
			   super.finalize();
		}
		public void saveXMLDataFile() {
			if (csFile != null) {
				csFile.saveXmlFile(true);
			}
		}
		public void closeXMLDataFile() {
			if (csFile != null) {
				//TRACE("Closing XML data file " + csName,4);
				csFile.saveXmlFile(true);
				csFile = null;
				xmlDoc = null;
				root = null;
			}
		}
	}
