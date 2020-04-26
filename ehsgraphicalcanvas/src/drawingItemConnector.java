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
		public connEdge getConnEdge(drawingItem d,Point ptConn) {
			int margin = 10;
			Rectangle r = d.getTransformBoundingBox();
			float cx = (float)r.getWidth() / (float)2.00;
			float cy = (float)r.getHeight() / (float)2.00;
			
			Rectangle r1 = new Rectangle((int)r.getX()+(int)cx,(int)r.getY(),2*margin,2*margin);
			r1.translate(-margin,-margin);
			if (r1.contains(ptConn.x,ptConn.y)) {return connEdge.TOP;}
			r1 = new Rectangle((int)r.getX()+(int)r.getWidth(),(int)r.getY()+(int)cy,2*margin,2*margin);
			r1.translate(-margin,-margin);
			if (r1.contains(ptConn.x,ptConn.y)) {return connEdge.RIGHT;}
			r1 = new Rectangle((int)r.getX()+(int)cx,(int)r.getY()+(int)r.getHeight(),2*margin,2*margin);
			r1.translate(-margin,-margin);
			if (r1.contains(ptConn.x,ptConn.y)) {return connEdge.BOTTOM;}
			r1 = new Rectangle((int)r.getX(),(int)r.getY()+(int)cy,2*margin,2*margin);
			r1.translate(-margin,-margin);
			if (r1.contains(ptConn.x,ptConn.y)) {return connEdge.LEFT;}
			
			return connEdge.NONE;
		}
		public Point getTextStartPoint(String text,drawingItem d,Point ptConn) {
			int margin = 10;
			int textX = ptConn.x;
			int textY = ptConn.y;
			int stringWidth =  text.length() * charWidth;
			
			connEdge edge = getConnEdge(d,ptConn);
			if (edge == connEdge.TOP) {textX = textX + margin;textY = textY - margin;}
			if (edge == connEdge.RIGHT) {textX = textX + margin;textY = textY - margin;}
			if (edge == connEdge.BOTTOM) {textX = textX + margin;textY = textY + margin;}
			if (edge == connEdge.LEFT) {textX = textX - margin - stringWidth;textY = textY - margin;}
			
			return new Point(textX,textY);
		}
		public String getID() {return connectorID;}
		public void setID(String s) {connectorID = s;}
		public drawingItem getStart() {return connectorStart;}
		public drawingItem getEnd() {return connectorEnd;}
		public boolean hitTest(int x,int y) {
			//GeneralPath p = connector.getConnectorPath();
			//Graphics2D g2d = (Graphics2D)getGraphics();
			//Rectangle r = new Rectangle(x-2,y-2,4,4);
			//return g2d.hit(r,p,true);
			return false;
		}
		public void drawConnector(Graphics2D g2d) {
			//Point pt1 = connector.getP1();
			//Point pt2 = connector.getP2();
			//Point start = getTextStartPoint(getTextStart(),getStart(),connector.getP1());
			//Point end = getTextStartPoint(getTextEnd(),getEnd(),connector.getP2());
			//g2d.drawString(getTextStart(),start.x,start.y);
			//g2d.drawString(getTextEnd(),end.x,end.y);
			
			//int sourceEdgeId = getConnEdge(getStart(),connector.getP1()).edgeID();
			//int destEdgeId = getConnEdge(getEnd(),connector.getP2()).edgeID();
			//drawConnSymbol(g2d,connectorStartSymbol,(int)((sourceEdgeId-1) * Math.PI));
			//drawConnSymbol(g2d,connectorEndSymbol,(int)((destEdgeId-1) * Math.PI));
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
		void drawConnSymbol(Graphics2D g2d,connSymbol connSym,int rotate) {
			if (connSym == connSymbol.NONE) {return;}
		}
	}
