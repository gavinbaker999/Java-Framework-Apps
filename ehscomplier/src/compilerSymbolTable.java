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

public class compilerSymbolTable implements TreeSelectionListener,TextListener {
		private Vector symbols = new Vector();
		private	Vector complexSymbols = new Vector();
		private panelDialog symbolDialog = null;
		private	JTree symbolTree = null;
		private boolean bEditValue = false;
		private TextField valueTF = null;
		private compilerSymbol cs = null;
		private boolean bUseForwardDecls = false;
		private String symbolTableName = "Default";
		
		public compilerSymbolTable(String name) {symbolTableName = name;}
		public void setUseForwardDecls(boolean b) {bUseForwardDecls = b;}
		public boolean getUseForwardDecls() {return bUseForwardDecls;}
		public void setEditValue(boolean b) {bEditValue = b;}
		public Vector getAllSymbols() {return symbols;}
		public void mergeSymbolTable(compilerSymbolTable cst) {
			Vector v = cst.getAllSymbols();
			for (int i=0;i<v.size();i++) {
				compilerSymbol symbol = (compilerSymbol)v.elementAt(i);	
				addSymbol(symbol.getScope(),symbol.getSubScope(),symbol.getSymbolName(),symbol.getSymbolValue(),symbol.getSymbolType(),symbol.getSymbolClass());
			}
		}
		public void textValueChanged(TextEvent evt) {
			if (cs != null) {
				cs.setSymbolValue(valueTF.getText());
			}
		}
		public void valueChanged(TreeSelectionEvent evt) {
			Object selection = symbolTree.getLastSelectedPathComponent();
			if (selection != null) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)selection;
				symbolTreeNodeClicked(symbolTree,node);
			}
		}
		public void symbolTreeNodeClicked(JTree tree,DefaultMutableTreeNode node) {
			if (node.isLeaf()) {
				DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)node.getParent();
				if (node.getRoot() != parentNode) {
					String symbolType = parentNode.toString();
					// node text is in the form scope:sub scope:symbol name
					Vector v1 = supportFunctions.splitIntoTokens(node.toString(),":");
					String symbolScope = (String)v1.elementAt(0);
					String symbolSubScope = (String)v1.elementAt(1);
					String symbolName = (String)v1.elementAt(2);
					//TRACE("symbolTreeNodeClicked:scope:"+symbolScope+":subscope:"+symbolSubScope+":name:"+symbolName,4);
					//supportFunctions.displayMessageDialog(null,"symbolTreeNodeClicked:scope:"+symbolScope+":subscope:"+symbolSubScope+":name:"+symbolName);
					String symbolValue = "";
					symType type = complierFunctions.symTypeFromString(symbolType);
					//Vector v = mainTab.compilier.getSymbolTable().getSymbols(type);
					Vector v = getSymbols(type); // GDB 06/02/2014
					//TRACE("symbolTreeNodeClicked:number of type symbols "+String.valueOf(v.size()),4);
					//supportFunctions.displayMessageDialog(null,"symbolTreeNodeClicked:number of type symbols "+String.valueOf(v.size()));
					cs = null;
					for (int i=0;i<v.size();i++) {
						cs = (compilerSymbol)v.elementAt(i);
						//supportFunctions.displayMessageDialog(null,"symbolTreeNodeClicked:next symbol name:"+cs.getSymbolName());
						if (symbolName.equals(cs.getSymbolName())) {
							//supportFunctions.displayMessageDialog(null,"symbolTreeNodeClicked:got symbol value:"+cs.getSymbolValue());
							symbolValue = cs.getSymbolValue();
							break;
						}
					}
					//TRACE("symbolTreeNodeClicked:Clicked symbol:Type:"+symbolType+":Name:"+symbolName+":Value:"+symbolValue,4);
					valueTF.setText(symbolValue);
					//symbolNodeClicked(symbolScope,symbolSubScope,symbolName,symbolValue);
				} else {
					//TRACE("symbolTreeNodeClicked:Symbol type got no entries",4);
					valueTF.setText("Not Defined");
				}
			} else {
				//TRACE("symbolTreeNodeClicked:Not clicked on a leaf node",4);
				valueTF.setText("Not Defined");
			}
		}
		public JTree buildSymbolTree(String rootText) {
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootText);
			symbolTree = new JTree(root);
			for (symType s : symType.values()) {
				if (s == symType.NONE) {continue;}
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(s.getDescription());
				root.add(child);
				Vector v = getSymbols(s);
				for (int ii=0;ii<v.size();ii++) {
					compilerSymbol cs1 = (compilerSymbol)v.elementAt(ii);
					child.add(new DefaultMutableTreeNode(cs1.getScope() + ":" + cs1.getSubScope() + ":" + cs1.getSymbolName()));
				}
			}
			
			symbolTree.expandRow(0);
			return symbolTree;
		}
		public panelDialog createSymbolDialog(String title,String msg) {
			if (symbolDialog != null) {destroySymbolDialog();}
			symbolTree = buildSymbolTree(symbolTableName);
			symbolTree.addTreeSelectionListener(this);
			TreeSelectionModel m = symbolTree.getSelectionModel();
			m.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			symbolTree.setSelectionModel(m);
			JScrollPane spane = new JScrollPane(symbolTree);
			JPanel symbolPanel = new JPanel();
			symbolPanel.setLayout(new BoxLayout(symbolPanel,BoxLayout.Y_AXIS));			
			cs = null;
			valueTF = new TextField(20);
			valueTF.addTextListener(this);
			valueTF.setEnabled(bEditValue);
			symbolPanel.add(valueTF);
			symbolPanel.add(Box.createRigidArea(new Dimension(10,10)));
			symbolPanel.add(spane);
			symbolPanel.add(new JLabel(msg,JLabel.CENTER));
			symbolPanel.setSize(250,275);
			symbolDialog = supportFunctions.displayPanelDialog(null,symbolPanel,title);
			return symbolDialog;
		}
		public void destroySymbolDialog() {
			if (symbolDialog != null) {
				symbolDialog.dispose();
				symbolDialog.destory();
				symbolDialog = null;
			}
		}
		
		public void removeAllSymbols() {
			symbols.removeAllElements();
			complexSymbols.removeAllElements();
		}
		public void unresolvedForwardDecl(compilerSymbol cs) {
			cs.setForwardDecl(false);
		}
		public void unresolvedForwardDecls() {
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol symbol = (compilerSymbol)symbols.elementAt(i);
				if (getUseForwardDecls() == true) {
					unresolvedForwardDecl(cs);
				}
			}
		}
		public compilerSymbol checkForwardDecls(String scope,String subScope,String name,symType type,symClass sclass) {
			if (getUseForwardDecls() == true) {
				compilerSymbol cs = addSymbol(scope,subScope,name,"",type,sclass);
				cs.setForwardDecl(true);
				//TRACE("checkForwardDecls:Defined forward symbol decl:" + name,4);
				supportFunctions.displayMessageDialog(null,"checkForwardDecls:Defined forward symbol decl:" + name);
				return cs;
			}
			return (compilerSymbol)null;
		}
		public compilerSymbol findSymbol(String scope,String subScope,String name) {
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol symbol = (compilerSymbol)symbols.elementAt(i);
				if (scope.equals(symbol.getScope()) && name.equals(symbol.getSymbolName())) {return symbol;}
			}
			return checkForwardDecls(scope,subScope,name,symType.NONE,symClass.NONE);
		}
		public compilerSymbol findSymbol(String scope,String subScope,String name,symType type,symClass sclass) {
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol symbol = (compilerSymbol)symbols.elementAt(i);
				if (scope.equals(symbol.getScope()) && name.equals(symbol.getSymbolName()) && type == symbol.getSymbolType() && sclass == symbol.getSymbolClass()) {return symbol;}
			}
			return checkForwardDecls(scope,subScope,name,type,sclass);
		}
		public compilerSymbol findSymbol1(int code,String scope,String subScope,String name,symType type) {
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol symbol = (compilerSymbol)symbols.elementAt(i);
				if (scope.equals(symbol.getScope()) && subScope.equals(symbol.getSubScope()) && name.equals(symbol.getSymbolName()) && type == symbol.getSymbolType() && code == symbol.getCode()) {return symbol;}
			}
			return checkForwardDecls(scope,subScope,name,type,symClass.NONE);
		}
		public compilerSymbol findSymbol(String scope,String subScope,String name,symType type) {
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol symbol = (compilerSymbol)symbols.elementAt(i);
				if (scope.equals(symbol.getScope()) && name.equals(symbol.getSymbolName()) && type == symbol.getSymbolType()) {return symbol;}
			}
			return checkForwardDecls(scope,subScope,name,type,symClass.NONE);
		}
		public compositeCompilerSymbol findComplexSymbol(String name) {
			for (int i=0;i<complexSymbols.size();i++) {
				compositeCompilerSymbol symbol = (compositeCompilerSymbol)symbols.elementAt(i);
				if (name.equals(symbol.getCompositeID())) {return symbol;}
			}
			return (compositeCompilerSymbol)null;
		}
		public Vector getSymbols(String scope) {
			Vector v = new Vector();
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol symbol = (compilerSymbol)symbols.elementAt(i);
				if (scope == symbol.getScope()) {v.addElement(symbol);}
			}
			return v;
		}
		public Vector getSymbols(String scope,String subScope) {
			Vector v = new Vector();
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol symbol = (compilerSymbol)symbols.elementAt(i);
				if (scope == symbol.getScope() && subScope == symbol.getSubScope()) {v.addElement(symbol);}
			}
			return v;
		}
		public Vector getSymbols(symType type) {
			Vector v = new Vector();
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol symbol = (compilerSymbol)symbols.elementAt(i);
				if (type == symbol.getSymbolType()) {v.addElement(symbol);}
			}
			return v;
		}
		public Vector getSymbols(String scope,symType type) {
			Vector v = new Vector();
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol symbol = (compilerSymbol)symbols.elementAt(i);
				if (scope == symbol.getScope() && type == symbol.getSymbolType()) {v.addElement(symbol);}
			}
			return v;
		}
		public Vector getSymbols(String scope,symClass c) {
			Vector v = new Vector();
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol symbol = (compilerSymbol)symbols.elementAt(i);
				if (scope == symbol.getScope() && c == symbol.getSymbolClass()) {v.addElement(symbol);}
			}
			return v;
		}
		public Vector getSymbols(String scope,String subScope,symType type) {
			Vector v = new Vector();
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol symbol = (compilerSymbol)symbols.elementAt(i);
				if (scope == symbol.getScope() && subScope == symbol.getSubScope() && type == symbol.getSymbolType()) {v.addElement(symbol);}
			}
			return v;
		}
		public Vector getSymbols(String scope,String subScope,symClass c) {
			Vector v = new Vector();
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol symbol = (compilerSymbol)symbols.elementAt(i);
				if (scope == symbol.getScope() && subScope == symbol.getSubScope() && c == symbol.getSymbolClass()) {v.addElement(symbol);}
			}
			return v;
		}
		public compilerSymbol addSymbol(int code,String scope,String subScope,String name,String value,symType type,symClass sclass) {
			compilerSymbol symbol = new compilerSymbol(scope,subScope,name,value,type,sclass);
			symbol.setCode(code);
			symbols.addElement(symbol);
			return symbol;
		}
		public compilerSymbol addSymbol(String scope,String subScope,String name,String value,symType type,symClass sclass) {
			return addSymbol(scope,subScope,name,value,type,sclass,"");
		}
		public compilerSymbol addSymbol(String scope,String subScope,String name,String value,symType type,symClass sclass,String complexSym) {
			compilerSymbol cs = findSymbol(scope,subScope,name,type);
			if (cs != null) {
				if (getUseForwardDecls() == true) {
					cs.setForwardDecl(false);
					//TRACE("addSymbol:Resolving forward symbol decl:" + name,4);
					supportFunctions.displayMessageDialog(null,"addSymbol:Resolving forward symbol decl:" + name);
					return cs;
				} else {
					//TRACE("addSymbol:Symbol found " + name + ": NO ForwardDecl",4);
				}
			}
			compilerSymbol symbol = new compilerSymbol(scope,subScope,name,value,type,sclass);
			if (complexSym.length() != 0) {
				  compositeCompilerSymbol ci = findComplexSymbol(complexSym);
				  if (ci == null) {
					  ci = new compositeCompilerSymbol(complexSym);
					  complexSymbols.addElement(ci);
				  }
				  ci.addSymbol(symbol);
			} else {
				symbols.addElement(symbol);
			}
			return symbol;
		}
		public void deleteSymbol(String scope,String subScope,String name,symType type) {
			compilerSymbol symbol = findSymbol(scope,subScope,name,type);
			if (symbol != null) {
				symbols.removeElement(symbol);
			}
		}
		public String dumpSymbols() {
			String dump = "";
			for (int i=0;i<symbols.size();i++) {
				compilerSymbol s = (compilerSymbol)symbols.elementAt(i);
				dump = dump + s.dumpSymbol();
			}
			for (int i=0;i<complexSymbols.size();i++) {
				compositeCompilerSymbol s = (compositeCompilerSymbol)complexSymbols.elementAt(i);
				dump = dump + s.dumpSymbol();
			}
			return dump;
		}
	}
