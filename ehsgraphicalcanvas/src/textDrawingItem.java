import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
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
import java.util.Map;
import java.text.*;
import java.lang.reflect.*;
import java.beans.*;
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
import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import javax.script.*;
import javax.swing.filechooser.*;
import javax.xml.ws.*;
import javax.xml.ws.handler.*;
import javax.xml.ws.handler.soap.*;
import javax.xml.soap.*;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
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
import javax.activation.URLDataSource.*;

import java.awt.geom.Point2D.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.HTMLEditorKit.*;
import javax.swing.text.html.HTMLDocument.*;
import javax.swing.text.html.ParagraphView;
import java.awt.datatransfer.*;

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

//import sun.misc.Unsafe; 
//import sun.reflect.ReflectionFactory;  

public class textDrawingItem extends drawingItem {
		public textDrawingItem() {}
		public textDrawingItem(String id,int orgX,int orgY,String p1,String p2,boolean fill,Color c) {
			super(ehsConstants.dcTypeText,id,orgX,orgY,p1,p2,"","",fill,c);
		}
		public void outlinePaint(Graphics2D g2d,int dcLastX,int dcLastY,int xCord,int yCord,boolean dcFilled) {
		}
		  public void paint(Graphics2D g2d,boolean focus) {
			setupPaint(g2d,focus);

			// set font size to fit in width pixels
			Vector v = supportFunctions.splitIntoTokens(getParam2(),",");
			int width = Integer.parseInt((String)v.elementAt(0));
			int height = Integer.parseInt((String)v.elementAt(1));
			
			float fontSize = 1;
			int w = 0;
			do {
				Font newFont = g2d.getFont().deriveFont(fontSize++);
				g2d.setFont(newFont);
				FontMetrics fm = getFontMetrics(g2d.getFont());
				w = fm.stringWidth(diParam1 + "OO");
			}
			while(w < width);
			
			// g2d already has the font of the correct size selected into it
			g2d.drawString(diParam1,diOriginX,diOriginY);
			teardownPaint(g2d,focus);
		}
		public void fitToRectangle(Rectangle r) {
			setOrigin(new Point((int)r.getX(),(int)r.getY()));
			setParam2(String.valueOf((int)r.getWidth()) + "," + String.valueOf((int)r.getHeight()));
		}
		public Rectangle getBoundingRect() {
			  int x1 = diOriginX,y1 = diOriginY;
			  Vector v = supportFunctions.splitIntoTokens(getParam2(),",");
			  int width = Integer.parseInt((String)v.elementAt(0));
			  int height = Integer.parseInt((String)v.elementAt(1));
			  return new Rectangle(x1,y1,width,height);
		}
	}
