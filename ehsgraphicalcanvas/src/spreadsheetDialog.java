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

public class spreadsheetDialog extends gridDialog implements multiColumnCanvasDialogListener {
		private	ArrayList<spreadsheetRule> rules = null;
		
		spreadsheetDialog (Frame f,String dialogTitle,String[] colTitles,String dialogData,String[][] colOptions,boolean bAllowEdits) {
			super(f,dialogTitle,colTitles,dialogData,colOptions,bAllowEdits);
			
			rules = new ArrayList<spreadsheetRule>();
			addmultiColumnCanvasDialogListener(this);
		}
		public void multiColumnCanvasUpdated(String propNewValue,int row,int column) {
			processSpreadsheetRules();
		}
		public void processSpreadsheetRules() {
			String data = "";
			
			for (spreadsheetRule rule : rules) {
				data = processCellReferences(rule.rule());
				data = supportFunctions.eval(data); 
				choice.setDataCell(row(rule.cellRef()), col(rule.cellRef()), data);
			}
		}
		public void removeAllRules() {
			rules.clear();
		}
		public boolean checkRule(String formula) {return true;}
		public void addRule(String cellRef,String formula) {
			if (checkRule(formula)) {rules.add(new spreadsheetRule(cellRef,formula));}
		}
		public void deleteRule(String cellRef) {
			for (spreadsheetRule rule : rules) {
				if (cellRef.equals(cellRef)) {
					rules.remove(rule);break;
				}
			}
		}
		public String cellReference(int row,int col) {
			// Top left cell is (0,0) and is A1 and max column is ZZ
			StringBuffer buf = new StringBuffer("  ");
			
			int whole = (col / 27) - 1;
			int part = col % 27;
			if (whole >= 0) {
				int ch = 'A' + whole;
				buf.setCharAt(0,(char)ch);
			}
			int ch = 'A' + part;
			buf.setCharAt(1,(char)ch);
			
			return buf.toString().trim() + String.valueOf(row + 1);
		}
		public String processCellReferences(String formula) {
			ehsRegExp p = new ehsRegExp();
			p.regExpMatch(formula, "#(.*)#");
			String[] tokens = p.getFoundStringsArray();
			for (int i=0;i<tokens.length;i++) {
				String cellRef = tokens[i].substring(1, tokens[i].length()-1);
				formula = formula.replaceAll(tokens[i],
						choice.getDataCell(row(cellRef),col(cellRef)));
			}
			
			return formula;
		}
		public int col(String cellRef) {
			// 1st col has an index of 0
			int rowIndex = -1;
			cellRef = cellRef.toUpperCase();
			for (int i=0;i<cellRef.length();i++) {
				if (Character.isDigit(cellRef.charAt(i))) {
					rowIndex = i;break;
				}
			}
			if (rowIndex == -1 || rowIndex == 0) {return -1;} //error!!!
			String colReference = cellRef.substring(0,rowIndex);
			int col = 0;
			for (int i=0;i<colReference.length();i++) {
				char ch = colReference.charAt(i);
				col = col + (((ch - 'A' + 1) * 26) * i);
			}
			return col;
		}
		public int row(String cellRef) {
			// 1st row has an index of 0
			int rowIndex = -1;
			cellRef = cellRef.toUpperCase();
			for (int i=0;i<cellRef.length();i++) {
				if (Character.isDigit(cellRef.charAt(i))) {
					rowIndex = i;break;
				}
			}
			if (rowIndex == -1 || rowIndex == 0) {return -1;} //error!!!
			return Integer.parseInt(cellRef.substring(rowIndex)) - 1;			
		}
	}
