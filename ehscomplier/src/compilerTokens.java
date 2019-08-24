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

public class compilerTokens {
		public Vector reservedWords = new Vector();
		public Vector types = new Vector();
		
		public String getKeywordORedString() {
			if (reservedWords.size() == 0) {return "";}
			String tmp = (String)reservedWords.elementAt(0);
			for (int i=1;i<reservedWords.size();i++) {
				tmp = tmp + "|" + (String)reservedWords.elementAt(i);
			}
			
			return tmp;
		}
		public String getTypeORedString() {
			if (types.size() == 0) {return "";}
			String tmp = (String)types.elementAt(0);
			for (int i=1;i<types.size();i++) {
				tmp = tmp + "|" + (String)types.elementAt(i);
			}
			
			return tmp;
		}
		public Vector tokenizeLine(String line) {
			return tokenizeLine(line,true);
		}
		public Vector tokenizeLine(String line,boolean bKeywords) {
			Vector tokens = new Vector();
			line = preProcessLine(line,bKeywords);
			tokens = supportFunctions.splitIntoTokensIncStringLit(line," ");
			return tokens;
		}
		public String preProcessLine(String line,boolean bKeywords) {
			//TRACE("preProcessLine:compilerTokens",4);
			return line;
		}
		public Vector getReservedWords() {return reservedWords;}
		public Vector getTypes() {return types;}
		public String getStringOfTypes() {
			if (types.size() == 0) {return "";}
			String tmp = (String)types.elementAt(0);
			for (int i=1;i<types.size();i++) {
				tmp = tmp + "," + (String)types.elementAt(i);
			}
			
			return tmp;
		}
		public void setReservedWords(String words) {
			reservedWords.removeAllElements();
			reservedWords = supportFunctions.splitIntoTokens(words,",");
		}
		public void setTypes(String words) {
			types.removeAllElements();
			types = supportFunctions.splitIntoTokens(words,",");
		}
		public boolean isClassAContainer(String className) {
//			try {
//				Class c = Class.forName(className);
//				while (c.getSuperclass() != null) {
//					className = c.getName();
//					TRACE("Super class: " + className,4);
//					int pos = className.lastIndexOf('.');
//					if (pos != -1) {className = className.substring(pos + 1);}
//					if (className.equals("AbstractCollection")) {
//						TRACE("Class " + className + " is a container",4);
//						return true;
//					}
//				}
//			} catch (Exception e) {e.printStackTrace();}
			
			basicFile f = new basicFile("../classes/common/containerclasses.dat");
			String  s = f.loadFile();
			Vector v = supportFunctions.splitIntoTokens(s,",");
			for (int i=0;i<v.size();i++) {
				String tmp = (String)v.elementAt(i);
				if (tmp.equals(className)) {
					//TRACE("Class " + className + " is a container",4);
					return true;
				}
			}

			//TRACE("Class " + className + " is not a container",4);
			return false;
		}
		public boolean isTemplateType(String token) {
			ehsRegExp p1 = new ehsRegExp();
			if (p1.regExpMatch(token,"<.*?>")) {return true;}
			return false;
		}
		public boolean isReserved(String token) {
			return reservedWords.contains(token);
		}
		public boolean isNumeric(String token) {
			try {
				Integer.parseInt(token);
			} catch (Exception e) {return false;}
			return true;
		}	
		public boolean isString(String token) {
			if ((token.charAt(0) == '\"') && token.charAt(token.length()-1) == '\"')  {return true;}
			
			return false;
		}
		public boolean isType(String token) {
			// remove any [...] to get base type
			int index = token.indexOf("[");
			String tmp = token;
			if (index != -1) {
				tmp = token.substring(0,index);
			}
			return types.contains(tmp);
		}
		public boolean isComment(String token) {
			return false;
		}
		public boolean isSyntax(String token) {
			ehsRegExp p = new ehsRegExp();
			p.regExpMatch(token,"\\p{Punct}");
			String[] tmp;
			tmp = p.getFoundStringsArray();
			if (tmp.length == 1) {return true;}
			return false;
		}
		public String basePreProcessLine(String line,boolean bKeywords) {
			//TRACE("basePreProcessLine:compilerTokens",4);
				
			ehsRegExp p = new ehsRegExp();
			
			// save all the string literals in the input line
			p.regExpMatch(line,"(\".*?\")");
			String[] strings = p.getFoundStringsArray();			
					
			// put a space character ether side of any syntax character 
			p.regExpMatch(line,"[\\p{Punct}&&[^_'\\[\\]\\.\"]]"); // GDB 18/05/2014
			String[] tokens = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				line = line.replace((String)tokens[i]," " + (String)tokens[i] + " ");
			}
		
			// remove space between '-' characters and numbers where there is a symbol before the '-' character or its
			// at the beginning of the line. (i.e. the unary operator)
			p.regExpMatch(line,"^-\\s(\\d+)");			
			tokens = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				String tmp = (String)tokens[i].replace(" ","");
				line = line.replace((String)tokens[i],tmp);
			}
			p.regExpMatch(line,"\\p{Punct}\\s-\\s(\\d+)");			
			tokens = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				String tmp = (String)tokens[i].replace("- ","-");
				line = line.replace((String)tokens[i],tmp);
			}

			// replace tabs with just one space
			line = line.replaceAll("\\t{2,}"," ");

			// replace n spaces with just one space
			line = line.replaceAll("\\s{2,}"," ");
			
			// replace double keywords with no space e.g. port map becomes portmap
			if (bKeywords) {
				for (int i=0;i<reservedWords.size();i++) {
					String tmp = (String)reservedWords.elementAt(i);
					line = line.replaceAll("(?i)" + tmp,tmp); // make all keywords lower case
					if (tmp.indexOf(' ') != -1) {
						line = line.replace(tmp,tmp.replace(" ","")); // remove space between double keywords
					}
				}
			}

			// replace any string literals in the input line that we have alterted by any of the reg exps above
			p.regExpMatch(line,"(\".*?\")");			
			tokens = p.getFoundStringsArray();
			for (int i=0;i<strings.length;i++) {
				//TRACE("SL:" + (String)tokens[i] + " with " + (String)strings[i],4);
				line = line.replace((String)tokens[i],(String)strings[i]);
			}
			
			// remove any whtespace at start and end of line
			line = line.trim();

			return line;
		}
		String processNumberStrings(String line) {
			ehsRegExp p = new ehsRegExp();

			// replace hex strings with their decimal equivalents
			p.regExpMatch(line,"(X\"[0-9a-f]+\")");
			String[] tokens = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				String tmp = tokens[i];
				int number = 0;
				try {
					number = Integer.parseInt(tmp.substring(1,tmp.length()-1),16);
				} catch (Exception e) {;}
				line = line.replace(tokens[i],Integer.toString(number));
				return line;
			}

			// replace octal strings with their decimal equivalents
			p.regExpMatch(line,"(O\"[0-7]+\")");
			tokens = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				String tmp = tokens[i];
				int number = 0;
				try {
					number = Integer.parseInt(tmp.substring(1,tmp.length()-1),8);
				} catch (Exception e) {;}
				line = line.replace(tokens[i],Integer.toString(number));
				return line;
			}

			// replace binary strings with their decimal equivalents
			p.regExpMatch(line,"(B*\"[0|1]+\")");
			tokens = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				String tmp = tokens[i];
				int number = 0;
				try {
					number = Integer.parseInt(tmp.substring(1,tmp.length()-1),2);
				} catch (Exception e) {;}
				line = line.replace(tokens[i],Integer.toString(number));
			}

			return line;
		}		
	}
