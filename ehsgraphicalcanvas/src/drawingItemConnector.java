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
//import javax.xml.xquery.XQDataSource;
//import javax.xml.xquery.XQException;
//import javax.xml.xquery.XQPreparedExpression;
//import javax.xml.xquery.XQResultSequence;
//import com.saxonica.xqj.SaxonXQDataSource;

public class drawingItemConnector  extends Component {
		private drawingItem connectorStart;
		private drawingItem connectorEnd;
		private String	connectorTextStart;
		private String	connectorTextEnd;
		private	String	connectorID;
		private connSymbol connectorStartSymbol;
		private connSymbol connectorEndSymbol;
		private	int		connectorLayer;
		private int charWidth = 8;
		private int charHeight = 14;
		private GeneralPath connectorPath;
	
		public drawingItemConnector(drawingItem start,drawingItem end,String textStart,String textEnd,int symStart,int symEnd,String id,int layer) {
			connectorStart = start;
			connectorEnd = end;
			connectorTextStart = textStart;
			connectorTextEnd = textEnd;
			connectorID = id;
			connectorStartSymbol = graphicalcanvasFunctions.symIDAsInt(symStart);
			connectorEndSymbol = graphicalcanvasFunctions.symIDAsInt(symEnd);
			connectorLayer = layer;
		}
		public drawingItemConnector(drawingItem start,drawingItem end,String textStart,String textEnd,String id) {
			connectorStart = start;
			connectorEnd = end;
			connectorTextStart = textStart;
			connectorTextEnd = textEnd;
			connectorID = id;
			connectorStartSymbol = connSymbol.NONE;
			connectorEndSymbol = connSymbol.NONE;
		}
		public String getID() {return connectorID;}
		public void setID(String s) {connectorID = s;}
		public drawingItem getStart() {return connectorStart;}
		public drawingItem getEnd() {return connectorEnd;}
		public boolean hitTest(int x,int y) {
			Graphics2D g2d = (Graphics2D)getGraphics();
			Rectangle r = new Rectangle(x-2,y-2,4,4);
			return g2d.hit(r,connectorPath,true);
		}
		public void drawConnector(Graphics2D g2d) {
			Point pt1 = getStart().getOrigin();
			Point pt2 = getEnd().getOrigin();
			
			int stringWidthStart =  getTextStart().length() * charWidth;
			int stringWidthEnd =  getTextEnd().length() * charWidth;
			Point start = new Point(0,0);
			Point end = new Point(0,0);
			g2d.drawString(getTextStart(),start.x,start.y);
			g2d.drawString(getTextEnd(),end.x,end.y);
			
			connectorPath = new GeneralPath();
			connectorPath.moveTo(pt1.x,pt1.y);
			connectorPath.lineTo(pt2.x,pt2.y);
			connectorPath.closePath();
			g2d.draw(connectorPath);
			
			connectorStartSymbol.draw(g2d,pt1,(int)Math.PI);
			connectorEndSymbol.draw(g2d,pt2,(int)Math.PI);
		}
		public int getLayer() {return connectorLayer;}
		public void setTextStart(String s) {connectorTextStart = s;}
		public String getTextStart() {return connectorTextStart;}
		public void setTextEnd(String s) {connectorTextEnd = s;}
		public String getTextEnd() {return connectorTextEnd;}
		public int getSymStart() {return connectorStartSymbol.symID();}
		public int getSymEnd() {return connectorEndSymbol.symID();}
		public connSymbol getStartSymbol() {return connectorStartSymbol;}
		public connSymbol getEndSymbol() {return connectorEndSymbol;}
		public void setStartSymbol(connSymbol ci) {connectorStartSymbol = ci;}
		public void setEndSymbol(connSymbol ci) {connectorEndSymbol = ci;}
	}
