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

public class translationTable {
		private Vector entries = new Vector();
		private String translationTableName = "";
		
		public Vector getEntries() {return entries;}
		public void loadTranslationTable(String name) {
			clearTranslationTable();
			translationTableName = name;
			
			if (false) {
				supportFunctions.displayMessageDialog(null,"loadTranslationTable:Loading XML trans table file");
				xmlDataFile ttItems = new xmlDataFile();
				if (ttItems.openXMLDataFile(supportFunctions.getPathFilenameNoExt(name),"transtable",false)) {
					org.w3c.dom.Element root = ttItems.getRootElement();
					org.w3c.dom.Document doc = ttItems.getXMLDocument();
					NodeList n = supportFunctions.executeXPathExpr(doc,"/transtable/ttkeyword");
					for (int i=0;i<n.getLength();i++) {
						org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(i);
						String keyword = e.getAttribute("keyword");
						//TRACE("loadTranslationTable:keyword:" + keyword,4);
						
						String line = keyword + ",";					
					    NodeList n1 = supportFunctions.executeXPathExpr(doc,"/transtable/ttkeyword[@keyword='" + keyword + "']/ttentry");
					    if (n1.getLength() == 0) {
					    	supportFunctions.displayMessageDialog(null,"loadTranslationTable:No child nodes for keyword " + keyword);
					    }
					    for (int i1=0 ; i1<n1.getLength() ; i1++) {	    	
					    	org.w3c.dom.Element e1 = (org.w3c.dom.Element)n1.item(i1);
							String text = e1.getAttribute("syntax");
							String transtext = e1.getAttribute("transtext");
							String flags = e1.getAttribute("flags");
							String groupid = e1.getAttribute("groupid");
							String prestring = e1.getAttribute("prestring");
							String poststring = e1.getAttribute("poststring");
							//TRACE("loadTranslationTable:syntax:" + text,4);
							
							if (i1 == 0) {
								line = line + prestring + "," + poststring + ",";
							}
					    } 
					    //TRACE("loadTranslationTable:line:" + line,4);
						processTranslationTableEntry(line);
					}
				} else {
					supportFunctions.displayMessageDialog(null,"loadTranslationTable:Can't load XML translation table");
				}	
			}

			basicFile table = new basicFile(name);
			String tmp = "";
			do {
				tmp = table.readFileLine();
				if (tmp != null) {
					processTranslationTableEntry(tmp);
				}
			} while (tmp != null);
		}
		public void processTranslationTableEntry(String entry) {
			int commentIndex = entry.indexOf("//");
			if (commentIndex != -1) {
				if (commentIndex == 0) {entry = "";} else {
					entry = entry.substring(0,commentIndex);
				}
				entry = entry.trim();
				//supportFunctions.displayMessageDialog(null,"comment removal: " + entry);
			}
//			if (entry.startsWith("//") || entry.length()==0) {return;} // ignore comment and blank lines 
			if (entry.length()==0) {return;} // ignore blank lines 
			//supportFunctions.displayMessageDialog(null,entry);
			Vector v = supportFunctions.splitIntoTokens(entry,",");
			translationTableEntry tentry = new translationTableEntry((String)v.elementAt(0));	
			if (v.size() <= 3) {return;}
			String pre = (String)v.elementAt(1);
			String post = (String)v.elementAt(2);
			if (pre.equals("*")) {tentry.setpreEntryString("");} else {tentry.setpreEntryString(pre);}
			if (post.equals("*")) {tentry.setpostEntryString("");} else {tentry.setpostEntryString(post);}
			for (int i=3;i<v.size();i++) {
				//supportFunctions.displayMessageDialog(null,"PTTE:"+(String)v.elementAt(i));
				Vector v1 = supportFunctions.splitIntoTokens((String)v.elementAt(i),"@@",false);
				String tokenText = (String)v1.elementAt(0);
				String transText = "";
				String tokenFlags = "";
				if (v1.size() > 1) {transText = (String)v1.elementAt(1);}
				if (v1.size() > 2) {tokenFlags = (String)v1.elementAt(2);}
//				supportFunctions.displayMessageDialog(null,"PTTE:tokenText="+tokenText+" transtext="+transText+" flags="+tokenFlags);
				translationTableToken token = new translationTableToken(tokenText,transText,tokenFlags);
				tentry.addTranslationTableToken(token);
			}
			entries.addElement(tentry);
		}
		public void clearTranslationTable() {entries.removeAllElements();}
		public Vector getKeywords() {
			Vector v = new Vector();
			
			for (int i=0;i<entries.size();i++) {
				translationTableEntry entry = (translationTableEntry)entries.elementAt(i);
				v.addElement(entry.getKeyword());
			}
			
			return v;
		}
		public translationTableEntry getTranslationTableEntry(String keyword) {
			for (int i=0;i<entries.size();i++) {
				translationTableEntry entry = (translationTableEntry)entries.elementAt(i);
				if (keyword.equals(entry.getKeyword())) {return entry;}
			}
			
			return (translationTableEntry)null;
		}
		public Vector getTokens(String keyword) {
			for (int i=0;i<entries.size();i++) {
				translationTableEntry entry = (translationTableEntry)entries.elementAt(i);
				if (keyword.equals(entry.getKeyword())) {return entry.getTokens();}
			}
			supportFunctions.displayMessageDialog(null,"Unrecogized keyword : " + keyword);
			return new Vector();
		}
		public Vector expandTokens(Vector v) {
			Vector v1 = new Vector();
			for (int i=0;i<v.size();i++) {
				translationTableToken token = (translationTableToken)v.elementAt(i);
				String tokenText = token.getText();
				//supportFunctions.displayMessageDialog(null,"old token text:"+tokenText);
				ehsRegExp p = new ehsRegExp();
				p.regExpMatch(tokenText,"\\{.*?\\}");
				String[] tokens;
				tokens = p.getFoundStringsArray();
				if (tokens.length != 0) {
					// a line can have more then one keyword to expand
					for (int j=0;j<tokens.length;j++) {
						Vector v2 = expandTokens(getTokens(tokens[j].substring(1,tokens[j].length()-1)));
						String tmp = "";
						for (int k=0;k<v2.size();k++) {
							translationTableToken t = (translationTableToken)v2.elementAt(k);
							tmp = tmp + t.getText();
							if (k < v2.size()-1) {tmp = tmp + " ";}
						}
						tokenText = tokenText.replace(tokens[j],tmp);
						//supportFunctions.displayMessageDialog(null,"new token text:"+tokenText);
					}
					token.setText(tokenText);
				}
				//supportFunctions.displayMessageDialog(null,"ET:Text="+token.getText()+" transtext="+token.getTransText());
				v1.addElement(token);
			}
			return v1;
		}
	}
