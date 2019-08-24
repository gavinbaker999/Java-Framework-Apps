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

public class printableCanvas extends Canvas {
		public Image getCurrentCanvasImage() {
			Dimension d = getSize();
			Image i = createImage(d.width,d.height);
			Graphics2D g2d = (Graphics2D)i.getGraphics();
			paint(g2d);
			return i;
		}
		public void saveAsJPG(Rectangle rcClip,String filename) {
			   Image i = getCurrentCanvasImage();
			   if (i == null) {return;}
			   if (filename.length() == 0) {
				   filename = controlsFunctions.fileSaveDialog("","*.jpg");
				if (filename == null) {return;}
			   }
				if (!filename.endsWith(".jpg")) {filename = filename + ".jpg";}
			   supportFunctions.displayMessageDialog(null,"Saved " + filename);
			   ImageExtra ie = new ImageExtra(i); 
			   ie.saveImage(filename);
		}
		public void printCanvas() {
			//TRACE("printCanvas called",3);
			PrinterJob pJob = PrinterJob.getPrinterJob();
			pJob.setPrintable(null,pJob.defaultPage());
			if (pJob != null) {
			   if (pJob.printDialog()) {
			   	try {
			   	   pJob.print();
			   	} catch (PrinterException e) {}
			   }
//			   pJob.end();
			}
		}
		public boolean printPageSetupOverride(Graphics2D g2d,PageFormat format,Dimension size) {
			return false;
		}
		public Graphics2D printPageSetup(Graphics g,PageFormat format,Dimension size) {
			Graphics2D g2d = (Graphics2D)g;
			
			if (printPageSetupOverride(g2d,format,size)) {return g2d;}
			g2d.translate(format.getImageableX(),format.getImageableY());
			double pageWidth = format.getImageableWidth();
			double pageHeight = format.getImageableHeight();
			if (size.width > pageWidth) {
				double f = pageWidth / size.width;
				g2d.scale(f,f);
				pageWidth /= f;
				pageHeight /= f;
			}
			if (size.height > pageHeight) {
				double f = pageHeight / size.height;
				g2d.scale(f,f);
				pageWidth /= f;
				pageHeight /= f;
			}
			g2d.translate((pageWidth-size.width)/2,(pageHeight-size.height)/2);
			g2d.drawRect(-1,-1,size.width+2,size.height+2);
			g2d.setClip(0,0,size.width,size.height);
			return g2d;
		}
	}
