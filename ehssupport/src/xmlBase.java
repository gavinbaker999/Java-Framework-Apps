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

public class xmlBase implements ActionListener, ErrorListener {
	private	xmlBaseUtils	target;
	private	JPanel	xmlPanel;
	private	boolean	xmlModified;
	private boolean	xmlDisplay;
	private	org.w3c.dom.Document	xmlDoc;
	private	panelDialog	xmlDialog;
	private	JTree	xmlTree;
	private	DocumentBuilder	builder;
	private	JButton	xmlNewEntryButton,xmlCloseButton,xmlDeleteButton,xmlSaveButton,xmlChangeButton,xmlNewChildButton;
	private String	xmlFilename;
    private int charWidth = 8;
    private int charHeight = 14;
		
	public xmlBase() {
		initxmlBase("");
	}
	public xmlBase(String filename) {
		initxmlBase(filename);
	}
	public xmlBase(URL url) {
		initxmlBase(url.toString());
	}
	public void initxmlBase(String filename) {
		initXmlFile(false);
		xmlFilename = filename;
		
		if (xmlFilename.length() == 0) {
			newXmlFile();
		} else {
			try {
				xmlDoc = builder.parse(new FileInputStream(filename));
			} catch(Exception e) {
				xmlDoc = null;
				if (e != null) {supportFunctions.displayMessageDialog(null,"XML Parse Error In " + filename + ", " + e);}
			}		
		}
	}
	public org.w3c.dom.Element getElementSelected() {
		// to fix this only returns the first element of the selected name,
		// there could be more then one
		Object sel = xmlTree.getLastSelectedPathComponent();
		if (sel == null) {return (org.w3c.dom.Element)null;}
		String s = sel.toString();
		if (s.startsWith("#text")) {return (org.w3c.dom.Element)null;} // do not insert after text nodes
		Vector v = supportFunctions.splitIntoTokens(s,"(");
		s = (String)v.elementAt(0);
		NodeList nList = xmlDoc.getElementsByTagName(s.trim());
		org.w3c.dom.Element first = (org.w3c.dom.Element)nList.item(0);
		return first;
	}
	public org.w3c.dom.Element createNewElement(String ename,String evalue,String eattrib) {
		return createNewElement(ename,evalue,eattrib,true);
	}
	public org.w3c.dom.Element createNewElement(String ename,String evalue,String eattrib,boolean bDialog) {
		String name,value,attribs;
		if (bDialog) {
			xmlNodeDialog dlg = new xmlNodeDialog(null,ename,evalue,eattrib);
			if (!dlg.isOK()) {return null;}
			name = dlg.getNodeName();
			if (name.length() == 0) {return (org.w3c.dom.Element)null;}			
			value = dlg.getNodeValue();
			attribs = dlg.getNodeAttrib();
		} else {
			name = ename;
			value = evalue;
			attribs = eattrib;
		}

		Vector v = supportFunctions.splitIntoTokens(attribs,",");
	        
		//supportFunctions.displayMessageDialog(null,"name:"+name+":value:"+value+":attribs:"+attribs);
		org.w3c.dom.Element node = xmlDoc.createElement(name);
		if (value.length() !=0) {node.appendChild(xmlDoc.createTextNode(value));}
		for (int i=0;i<v.size();i++) {
			Vector v1 = supportFunctions.splitIntoTokens((String)v.elementAt(i),"=");
			if(v1.size() == 2) {node.setAttribute((String)v1.elementAt(0),(String)v1.elementAt(1));}
		}

		if (target != null ) {
			node = target.createNodePerformed(node);
		}
		
		return node;
	}
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == xmlDeleteButton) {
			org.w3c.dom.Element first = getElementSelected();
			if (target != null ) {
				first = target.deleteNodePerformed(first);
				if (first == null) {return;} // GDB 170118
			}	
			
			xmlDoc.getDocumentElement().removeChild(first); // xmlDoc.getDocumentElement() is the root element
		}
		if (evt.getSource() == xmlSaveButton) {
			saveXmlFile();
		}
		if (evt.getSource() == xmlCloseButton) {
			hidexmlBase();
		}
		if (evt.getSource() == xmlNewEntryButton || evt.getSource() == xmlNewChildButton) {
			org.w3c.dom.Element node = createNewElement("","","");
			if (node == null) {return;} // GDB 170118
	
			org.w3c.dom.Element first = getElementSelected();
			if (target != null ) {
				node = target.addNodePerformed(node);
				if (node == null) {return;} // GDB 170118
			}		
						
			try {
				if (evt.getSource() == xmlNewEntryButton) {
					first.getParentNode().appendChild(node);
				} else {
					first.appendChild(node);
				}
			} catch (Exception e) {supportFunctions.displayMessageDialog(null,"Node Insertion Error");}

			updateXmlTree();			
		}
		if (evt.getSource() == xmlChangeButton) {
			org.w3c.dom.Element first = getElementSelected();
			String nodeValue = "";
			switch(first.getNodeType()) {
				case org.w3c.dom.Node.TEXT_NODE:                    
					org.w3c.dom.Text textNode = (org.w3c.dom.Text)first;
					nodeValue = textNode.getData().trim(); 
					if (nodeValue == null) {nodeValue = "";}
					break;
			}
			String nodeAttrib  = "";
			NamedNodeMap elementAttributes = first.getAttributes();
			if (elementAttributes != null && elementAttributes.getLength() > 0) {
				int numAttributes = elementAttributes.getLength();
				for(int i=0; i<numAttributes; i++) {
					org.w3c.dom.Node attribute = elementAttributes.item(i);
					if (i > 0) {nodeAttrib = nodeAttrib + ", ";}
					nodeAttrib = nodeAttrib + attribute.getNodeName() + "=" + attribute.getNodeValue();
				}
			}
			org.w3c.dom.Element node = createNewElement(first.getNodeName(),nodeValue,nodeAttrib);
			if (node == null) {return;} // GDB 170118

			if (target != null ) {
				node = target.updateNodePerformed(node);
				if (node == null) {return;} // GDB 170118
			}

			first.replaceChild(node,first);				
			updateXmlTree();			
		}
	}
	public void updateXmlTree() {
		boolean bDisplayed=xmlBaseDisplayed();
		hidexmlBase();
		treeXmlDocument();		
		if (bDisplayed) {showxmlBase();} 
		xmlModified=true;
		setXmlTitle();
	}
	public void addXmlUtilsListener(xmlBaseUtils xeu) {target = xeu;}
	public void removeXmlUtilsListener() {target = null;}
	public String setXmlTitle() {
		if (xmlDialog!= null) {
			String file = "";
			String modified = "";
			
			if (xmlFilename.length() == 0) {
				file = "Untitled";
			} else {
				file = xmlFilename;
			}
			
			if (xmlModified) {
				modified = " (Modified)";
			}
			
			xmlDialog.setTitleString("XML Editor - " + file + modified);
			return file + modified;
		}
		return "";
	}
			
	public void initXmlFile(boolean bValidate) {
		xmlDoc = null;
		xmlDialog = null;
		xmlModified = false;
		xmlDisplay = false;
		xmlTree = null;
		xmlPanel = null;
		builder = null;
		target = null;
		xmlFilename = "";

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(bValidate);
			dbf.setNamespaceAware(true);
			builder = dbf.newDocumentBuilder();
			builder.setErrorHandler(new org.xml.sax.ErrorHandler() {
				public void warning(SAXParseException e) {}
				public void error(SAXParseException e) {}
				public void fatalError(SAXParseException e) throws SAXException {throw e;}
			});
		} catch (Exception e) {e.printStackTrace();}
	}
	public void newXmlFile() {
		xmlDoc = builder.newDocument();
	}
	public JPanel getXmlPanel() {return xmlPanel;}
	public boolean xmlBaseDisplayed() {return xmlDisplay;}
	public void miniXMLEditor() {showxmlBase();}
	public void showxmlBase() {
		treeXmlDocument();		
		xmlDialog = supportFunctions.displayPanelDialog(null,xmlPanel,"XML Editor");
		setXmlTitle();
		xmlDisplay = true;
	}
	public void hidexmlBase() {
		if (xmlDialog != null) {
			xmlDialog.destory();
			xmlDialog.dispose();
		}
		xmlDisplay = false;
	}
	public void closexmlBase() {
		if (xmlModified) {
			saveXmlFile();
		}
		hidexmlBase();
	}
	public void setXmlFilename(String s) {xmlFilename = s;}
	public String getXmlFilename() {return xmlFilename;}
	public void saveXmlFile() {
		saveXmlFile(false);
	}
	public void saveXmlFile(boolean bForce) {
		int response;
		
		if (xmlDoc == null) {
			//TRACE("saveXmlFile:not saving:"+xmlFilename,4);
			return;
		}
		
		if (!bForce) {
			response = JOptionPane.showConfirmDialog(null,"Save current changes","XML Editor",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		} else {
			response = JOptionPane.YES_OPTION;
		}
		if (response == JOptionPane.YES_OPTION) {
			if (xmlFilename.length() == 0) {
				xmlFilename = JOptionPane.showInputDialog(null,"XML Editor","Enter filename to save as",JOptionPane.QUESTION_MESSAGE);
			}
			if (xmlFilename != null) {
				//TRACE("saveXmlFile:"+xmlFilename,4);
				XMLDocumentWriter docWriter = new XMLDocumentWriter(xmlFilename);
				docWriter.write(xmlDoc);
				docWriter.close();
				xmlModified = false;
				setXmlTitle();
				try {Thread.sleep(1000);} catch (Exception e) {;}
			}
		}
	}
	
	public void treeXmlDocument() {
		if (xmlDoc == null) return;
		org.w3c.dom.Element rootElement = xmlDoc.getDocumentElement();
		DefaultMutableTreeNode root = buildXmlTree(rootElement);
		
		xmlTree = new JTree(root);
		JScrollPane sp = new JScrollPane(xmlTree);
		xmlNewEntryButton = new JButton("New Entry");
		xmlSaveButton = new JButton("Save");
		xmlCloseButton = new JButton("Close");
		xmlChangeButton = new JButton("Edit");
		xmlDeleteButton = new JButton("Delete");
		xmlNewChildButton = new JButton("New Child");
		xmlNewEntryButton.addActionListener(this);
		xmlSaveButton.addActionListener(this);
		xmlCloseButton.addActionListener(this);
		xmlDeleteButton.addActionListener(this);
		xmlChangeButton.addActionListener(this);
		xmlNewChildButton.addActionListener(this);
		
		xmlPanel = new JPanel();
		xmlPanel.setLayout(new BoxLayout(xmlPanel,BoxLayout.Y_AXIS));
		xmlPanel.add(sp);
		xmlPanel.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2,3));
		p.add(xmlNewEntryButton);
		p.add(xmlNewChildButton);
		p.add(xmlChangeButton);
		p.add(xmlDeleteButton);
		p.add(xmlSaveButton);
		p.add(xmlCloseButton);
		xmlPanel.add(p);
	}
	public JTree getXmlTree() {return xmlTree;}
	public org.w3c.dom.Document getXmlDocument() {return xmlDoc;}
	
	public DefaultMutableTreeNode buildXmlTree(org.w3c.dom.Element rootElement) {
		DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode(treeNodeLabel(rootElement));
		addChildren(rootTreeNode, rootElement);
		return(rootTreeNode);
	}
	public void addChildren
                       (DefaultMutableTreeNode parentTreeNode,
                        org.w3c.dom.Node parentXMLElement) {
    
		NodeList childElements = parentXMLElement.getChildNodes();
		for(int i=0; i<childElements.getLength(); i++) {
			org.w3c.dom.Node childElement = childElements.item(i);
			if (!(childElement instanceof org.w3c.dom.Text || childElement instanceof Comment)) {
				DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(treeNodeLabel(childElement));
				parentTreeNode.add(childTreeNode);
				addChildren(childTreeNode, childElement);
			}
		}
	}
  	public String treeNodeLabel(org.w3c.dom.Node childElement) {
		NamedNodeMap elementAttributes = childElement.getAttributes();
		String treeNodeLabel = childElement.getNodeName();
		
		String nodeValue = null;
		switch(childElement.getNodeType()) {
			case org.w3c.dom.Node.TEXT_NODE:                    
				org.w3c.dom.Text textNode = (org.w3c.dom.Text)childElement;
				nodeValue = textNode.getData().trim();  
				if (nodeValue != null && nodeValue.length()>0) {
					treeNodeLabel = treeNodeLabel + "=" + nodeValue;
				}
				break;
		}
		
		if (elementAttributes != null && elementAttributes.getLength() > 0) {
			treeNodeLabel = treeNodeLabel + " (";
			int numAttributes = elementAttributes.getLength();
			for(int i=0; i<numAttributes; i++) {
				org.w3c.dom.Node attribute = elementAttributes.item(i);
				if (i > 0) {treeNodeLabel = treeNodeLabel + ", ";}
				treeNodeLabel = treeNodeLabel + attribute.getNodeName() + "=" + attribute.getNodeValue();
			}
			treeNodeLabel = treeNodeLabel + ")";
		}
		return(treeNodeLabel);
	}
	
	// Transformers are required to continue processing after warning, unless the
	// application throws TransformerException
	public void warning(TransformerException te) throws TransformerException {
		xmlError(te);
	}
	public void error(TransformerException te) throws TransformerException {
		xmlError(te);
	}
	public void fatalError(TransformerException te) throws TransformerException {
		xmlError(te);
	}
	public String xmlError(TransformerException te) {
		SourceLocator loc = te.getLocator(); // may be null
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		pw.println(te.getClass().getName());
		if (loc != null) {
			pw.println("Line Number  : " + loc.getLineNumber());
			pw.println("Column Number: " + loc.getColumnNumber());
			pw.println("Public ID    : " + loc.getPublicId());
			pw.println("System ID    : " + loc.getSystemId());
		}
		
		pw.println("Message & Location: " + te.getMessageAndLocation());
		pw.println("Location          : " + te.getLocationAsString());
		//te.getException();
		//te.getCause();
		
		return sw.toString();
	}
}
