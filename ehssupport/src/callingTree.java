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

public class callingTree implements TreeSelectionListener,ActionListener,xmlBaseUtils {
		private JTree					callingTree = null;
		private	JButton					editDefsButton = null;
		private	JButton					saveAsXMLButton = null;
		private	DefaultMutableTreeNode	root = null;
		private	DefaultMutableTreeNode	currentNode = null;
		private	xmlDataFile				callingTreeXMLData = new xmlDataFile();
		private	String					currentLanguageBlock = "";
		private	String					entryPoints = "";
		private String					eventHandlers = "";
		private	String					treeName = "";
		private boolean					bValid = true;
		private int						charWidth = 8;
		private int						charHeight = 14;
 		
		callingTree(String treeName) {
			this.treeName = treeName;
			root = new DefaultMutableTreeNode(treeName);
			callingTree = new JTree(root);
			callingTree.addTreeSelectionListener(this);
			TreeSelectionModel m = callingTree.getSelectionModel();
			m.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			callingTree.setSelectionModel(m);
			currentNode = root;
			if (!callingTreeXMLData.openXMLDataFile("../classes/common/callingtree","callingtree",false)) {
				supportFunctions.displayMessageDialog(null,"callingtree.xml not found.");
				bValid = false;
				return;
			}
			callingTreeXMLData.getXmlBase().addXmlUtilsListener(this);
			callingTreeInit();
		}
		public boolean isValid() {return bValid;}
		public void callingTreeInit() {
			currentLanguageBlock = getAttributeWithXPath("/callingtree/currentblock","lang");
			entryPoints = getAttributeWithXPath("/callingtree/languageblock[@lang='" + currentLanguageBlock + "']/entrypoints","data");
			eventHandlers = getAttributeWithXPath("/callingtree/languageblock[@lang='" + currentLanguageBlock + "']/eventhandlers","data");
			reset();
		}
		public String[] getLangBlockList() {
		    ArrayList<String> 				data = new ArrayList<String>();

			NodeList n = supportFunctions.executeXPathExpr(getXmlDataFile().getXMLDocument(),"/callingtree/currentblock");
			for(int i=0;i<n.getLength();i++) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
				data.add(e.getAttribute("lang"));
			}
			
		    return (String[])data.toArray();
		}
		public xmlDataFile getXmlDataFile() {return callingTreeXMLData;}
		public void setAttributeWithXPath(String xPathExpr,String attrib,String value) {
			NodeList n = supportFunctions.executeXPathExpr(getXmlDataFile().getXMLDocument(),xPathExpr);
			if (n.getLength() > 0) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(0);
				e.setAttribute(attrib,value);
				getXmlDataFile().saveXMLDataFile();
			}
		}
		public String getAttributeWithXPath(String xPathExpr,String attrib) {
			String data = "";
			
			NodeList n = supportFunctions.executeXPathExpr(getXmlDataFile().getXMLDocument(),xPathExpr);
			if (n.getLength() > 0) {
				org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(0);
				data = e.getAttribute(attrib);
			}
			return data;
		}
		public String[] getFunctionCalls(String line) {
			ehsRegExp r = new ehsRegExp();

			String regExp = getAttributeWithXPath("/callingtree/languageblock[@lang='" + currentLanguageBlock + "']/funccalls","data");
			//TRACE("getFunctionCalls:"+regExp,4);
			if (r.regExpMatch(line,regExp)) {
				return r.getFoundGroupsArray();
			}		
			
			return (String[])null;			
		}
		public NodeList getLangElementNodeList() {
			NodeList n = supportFunctions.executeXPathExpr(getXmlDataFile().getXMLDocument(),
				"/callingtree/languageblock[@lang='" + currentLanguageBlock + "']/langelements/element");

			return n;
		}
		public String getVarDefRegExp() {
			return getAttributeWithXPath("/callingtree/languageblock[@lang='" + currentLanguageBlock + "']/vardef","data");
		}
		public String getVarAssignRegExp() {
			return getAttributeWithXPath("/callingtree/languageblock[@lang='" + currentLanguageBlock + "']/varassign","data");
		}
		public org.w3c.dom.Element createNodePerformed(org.w3c.dom.Element e) {
			return e;
		}
		public String getNodePerformed(String data) {
			return data;
		}
		public org.w3c.dom.Element addNodePerformed(org.w3c.dom.Element e) {
			return e;
		}
		public org.w3c.dom.Element updateNodePerformed(org.w3c.dom.Element e) {			
			return e;
		}
		public org.w3c.dom.Element deleteNodePerformed(org.w3c.dom.Element e) {
			return e;
		}
		public DefaultMutableTreeNode addCallingTreeNode(String key,String entry,String data) {
			if (entry.length() == 0) {return (DefaultMutableTreeNode)null;}
			if (data.length() == 0) {data = "-";}
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(key + ":" + entry + ":" + data);
			currentNode.add(node);	
			return node;
		}
		public void startDefine(String type,String name) {
			if (type.equals("pushscopeclass")) {currentNode = addCallingTreeNode("class",name,"");}			
			if (type.equals("pushscopeclasstemplate")) {
				name = name.replace("<","&lt");
				name = name.replace(">","&gt");
				currentNode = addCallingTreeNode("classtemplate",name,"");
			}			
			if (type.equals("pushscopeinterface")) {currentNode = addCallingTreeNode("interface",name,"");}			
			if (type.equals("pushscopefunc")) {currentNode = addCallingTreeNode("function",name,"");}			
			if (type.equals("pushscopefuncps")) {addCallingTreeNode("function",name,"");} // note here we DO NOT change currentNode here
		}
		public void endDefine(String type) {
			currentNode = (DefaultMutableTreeNode) currentNode.getParent();
		}
		
		public void reset() {
			callingTree.removeAll();
			currentNode = root;
		}
		public DefaultMutableTreeNode getCurrentNode() {return currentNode;}
		public DefaultMutableTreeNode getRoot() {return root;}
		public JTree getCallingTree() {return callingTree;}
		public void actionPerformed(ActionEvent evt) {
			if (evt.getSource() == editDefsButton) {
				callingTreeXMLData.miniXMLEditor();
			}
			if (evt.getSource() == saveAsXMLButton) {
				Stack nodeTags = new Stack();
				pseduoFile dataFile = new pseduoFile("../classes/common",supportFunctions.getFilenameNoExt(treeName) + "_callingtreedata.xml");
				dataFile.saveFile("<?xml version=\"1.0\"?>"); // saveFile used to remove any existing file
				dataFile.appendFile("\n<callingtreedata>");

				dataFile.appendFile("\n\t<module data=\"" + supportFunctions.getFilenameNoExt(treeName) + "\"/>");
				dataFile.appendFile("\n\t<language lang=\"" + currentLanguageBlock + "\"/>");
				dataFile.appendFile("\n\t<date data=\"" + supportFunctions.currentShortDate() + "\"/>");
				dataFile.appendFile("\n\t<time data=\"" +supportFunctions.currentShortTime() + "\"/>");
				
				
				// other enumerations: preorderEnumeration, postorderEnumeration, depthFirstEnumeration and breadthFirstEnumeration
				for (Enumeration e = root.preorderEnumeration(); e.hasMoreElements();) {
				    DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
				    String indent = supportFunctions.stringRepeat("\t",nodeTags.size() + 2);
				    if (node.isLeaf()) {
				    	Vector tokens = supportFunctions.splitIntoTokens(node.toString(),":");
				    	dataFile.appendFile("\n" + indent + "<" + (String)tokens.elementAt(0) + " entry=\"" + 
				    			(String)tokens.elementAt(1) + "\" data=\"" + (String)tokens.elementAt(2) + "\"/>");	
				    	if (!nodeTags.isEmpty() && node.getNextSibling()==null) {dataFile.appendFile((String)nodeTags.pop());}
				    } else {
				    	if(node == root) {
					    	dataFile.appendFile("\n" + indent + "<" + node.toString() + ">");
					    	nodeTags.push("\n" + indent + "</" + node.toString() + ">");				    		
				    	} else {				    		
					    	Vector tokens = supportFunctions.splitIntoTokens(node.toString(),":");
					    	dataFile.appendFile("\n" + indent + "<" + (String)tokens.elementAt(0) + " entry=\"" + 
					    			(String)tokens.elementAt(1) + "\" data=\"" + (String)tokens.elementAt(2) + "\">");
					    	nodeTags.push("\n" + indent + "</" + (String)tokens.elementAt(0) + ">");
				    	}
				    }
				}
				
		    	if (!nodeTags.isEmpty()) {dataFile.appendFile((String)nodeTags.pop());}
				dataFile.appendFile("\n</callingtreedata>");
				dataFile.flush();
				
				supportFunctions.displayMessageDialog(null,"Calling tree saved as XML.");
			}
		}
		public void displayCallingTree() {
			JPanel pBut = new JPanel();
			pBut.setLayout(new BoxLayout(pBut,BoxLayout.X_AXIS));
			editDefsButton = new JButton("Edit Lang Definations...");
			editDefsButton.addActionListener(this);
			pBut.add(editDefsButton);
			saveAsXMLButton = new JButton("Save As XML");
			saveAsXMLButton.addActionListener(this);
			pBut.add(saveAsXMLButton);
			
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
			callingTree.expandRow(0);
			JScrollPane sp = new JScrollPane(callingTree);
			p.add(sp);
			p.add(Box.createRigidArea(new Dimension(charWidth,charHeight)));
			p.add(pBut);
			supportFunctions.displayPanelDialog(null,p,"Calling Tree");
		}
		public void valueChanged(TreeSelectionEvent evt) {
			Object selection = callingTree.getLastSelectedPathComponent();
			if (selection != null) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)selection;
				callTreeNodeClicked(callingTree,node);
			}
		}
		public void callTreeNodeClicked(JTree tree,DefaultMutableTreeNode node) {
			if (node.isLeaf()) {
				DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)node.getParent();
				if (node.getRoot() != parentNode) {
					// node text is in the form key:entry:data apart from the root node which is just the treename
					Vector v1 = supportFunctions.splitIntoTokens(node.toString(),":");
					String key = (String)v1.elementAt(0);
					String entry = (String)v1.elementAt(1);
					String data = (String)v1.elementAt(2);
				} else {
					//TRACE("callTreeNodeClicked:Symbol type got no entries",4);
				}
			} else {
				//TRACE("callTreeNodeClicked:Not clicked on a leaf node",4);
			}
		}
		public DefaultMutableTreeNode getStartingNodeByKey(DefaultMutableTreeNode node1,String key1,String entry1) {
			for (Enumeration e = node1.preorderEnumeration(); e.hasMoreElements();) {
			    DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
				// node text is in the form key:entry:data apart from the root node which is just the treename
				Vector v1 = supportFunctions.splitIntoTokens(node.toString(),":");
				String key = (String)v1.elementAt(0);
				String entry = (String)v1.elementAt(1);
				String data = (String)v1.elementAt(2);

				if (key1.equals(key) && entry1.equals(entry)) {
					return node;
				}
			}	

			return (DefaultMutableTreeNode)null;
		}
		public String[] getNodesByKey(DefaultMutableTreeNode node1,String key1) {
		    ArrayList<String> 				data1 = new ArrayList<String>();

			for (Enumeration e = node1.preorderEnumeration(); e.hasMoreElements();) {
			    DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
				// node text is in the form key:entry:data apart from the root node which is just the treename
				Vector v1 = supportFunctions.splitIntoTokens(node.toString(),":");
				String key = (String)v1.elementAt(0);
				String entry = (String)v1.elementAt(1);
				String data = (String)v1.elementAt(2);

				if (key1.equals(key)) {
					data1.add(key);
				}
			}

			return (String[])data1.toArray();
		}
		public String[] getCallingSequence(DefaultMutableTreeNode startNode,String text) {
		    ArrayList<String> 				data1 = new ArrayList<String>();
		    DefaultMutableTreeNode			foundNode = null;

			// other enumerations: preorderEnumeration, postorderEnumeration, depthFirstEnumeration and breadthFirstEnumeration
			for (Enumeration e = root.preorderEnumeration(); e.hasMoreElements();) {
			    DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
				Vector v1 = supportFunctions.splitIntoTokens(node.toString(),":");
				String key = (String)v1.elementAt(0);
				String entry = (String)v1.elementAt(1);
				String data = (String)v1.elementAt(2);
				
			    if (text.equals(node.toString())) {
			    	foundNode = node;
			    }
			}
			
			if (foundNode != null) {
				for (Enumeration e = foundNode.preorderEnumeration(); e.hasMoreElements();) {
				    DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
					if (!node.isLeaf()) { // inner class or interface or function defined
						data1.add("+" + node.toString());
					} else {
						// node text is in the form key:entry:data
						//Vector v1 = supportFunctions.splitIntoTokens(node.toString(),":");
						//String key = (String)v1.elementAt(0);
						//String entry = (String)v1.elementAt(1);
						//String data = (String)v1.elementAt(2);
						data1.add(node.toString());
					}			    	
			    }
			}
			
			return (String[])data1.toArray();
		}
		public String[] getCallingReferences(DefaultMutableTreeNode startNode,String text) {
		    ArrayList<String> 				data1 = new ArrayList<String>();
 			
			// other enumerations: preorderEnumeration, postorderEnumeration, depthFirstEnumeration and breadthFirstEnumeration
			for (Enumeration e = root.preorderEnumeration(); e.hasMoreElements();) {
			    DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
			    if (text.equals(node.toString())) {
			    	DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)node.getParent();
					// node text is in the form key:entry:data
					//Vector v1 = supportFunctions.splitIntoTokens(parentNode.toString(),":");
					//String key = (String)v1.elementAt(0);
					//String entry = (String)v1.elementAt(1);	
					//String data = (String)v1.elementAt(2);
					if (parentNode != null) {data1.add(parentNode.toString());}
			    }
			}
			
			return (String[])data1.toArray();
		}
	}
