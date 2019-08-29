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

public class multiPagePrintableCanvas extends printableCanvas implements Printable,Pageable {
		// developed from code in JAVA Examples page 330
		PageFormat 	format;
		int 		numPages;
		double 		scaleFactor;
		double 		printX,printY;
		double 		printHeight,printWidth;
		double		documentHeight;
		Rectangle 	drawRect;
		ArrayList 	pageLengths = new ArrayList(); // for a document of n pages, stores lengths of pages 0 to n-2, last page assumed to have a full length
		ArrayList 	pageOffsets = new ArrayList(); // for a document of n pages, stores the starting offset of pages 1 to n-1, offset of page 0 is always 0
		
		public multiPagePrintableCanvas() {
			scaleFactor = 0.75; // as the default fonts are overly large
			format = new PageFormat();
			printX = format.getImageableX()/scaleFactor;
			printY = format.getImageableY()/scaleFactor;
			printWidth = format.getImageableWidth()/scaleFactor;
			printHeight = format.getImageableHeight()/scaleFactor;
			documentHeight = calcDocumentHeight();
			drawRect = new Rectangle(0,0,(int)printWidth,(int)documentHeight);
			if (documentHeight > printHeight) {paginate(drawRect);}
			numPages = pageLengths.size() + 1;			
		}
		public void paint(Graphics2D g2d) { // need to override this function
		}
		public double getDocumentHeight() {return documentHeight;}
		public double calcDocumentHeight() { // need to override this function
			return 0;
		}
		public void paginate(Rectangle2D allocation) { // need to override this function
			// this function fills in the pageLengths and pageOffsets ArrayLists
		}
		public Printable getPrintable(int pageNum) {return this;}
		public int getNumberOfPages() {return numPages;}
		public PageFormat getPageFormat(int pageNum) {
			if (pageNum == numPages-1) {return format;}
			double pageLength = ((java.lang.Double)pageLengths.get(pageNum)).doubleValue();
			PageFormat f = (PageFormat)format.clone();
			Paper p = f.getPaper();
			if (f.getOrientation() == PageFormat.PORTRAIT) {
				p.setImageableArea(printX*scaleFactor,printY*scaleFactor,printWidth*scaleFactor,printHeight*scaleFactor);
			} else {
				p.setImageableArea(printY*scaleFactor,printX*scaleFactor,printHeight*scaleFactor,printWidth*scaleFactor);
			}
			f.setPaper(p);
			return f;
		}
		public Graphics2D printPageSetup(Graphics g,int pageNum) {
			Graphics2D g2d = (Graphics2D)g;
			// translate to accomadate the top and left margins
			g2d.translate(format.getImageableX(),format.getImageableY());
			// scale page by specified factor
			g2d.scale(scaleFactor,scaleFactor);
			// display page number centered in the area of the top margin
			// set a new clpping region so we can draw in the top margin
			// but remember old one so we can restore it
			if (pageNum > 0) {
				Font headerFont = new Font("Serif",Font.PLAIN,12);
				Shape orgClip = g.getClip();
				g.setClip(new Rectangle(0,(int)-printY,(int)printWidth,(int)printY));
				String s = "- " + (pageNum+1) + " -";
				FontRenderContext frc = g2d.getFontRenderContext();
				Rectangle2D r = headerFont.getStringBounds(s,frc);
				LineMetrics metrics = headerFont.getLineMetrics(s,frc);
				g.setFont(headerFont);
				g.setColor(Color.black);
				g.drawString(s,(int)((printWidth-r.getWidth())/2),(int)(-(printY-r.getHeight())/2 + metrics.getAscent()));
				g.setClip(orgClip);
			}
			double pageStart = 0.0, pageLength = printHeight;
			if (pageNum > 0) {pageStart = ((java.lang.Double)pageOffsets.get(pageNum-1)).doubleValue();}
			if (pageNum < numPages-1) {pageLength = ((java.lang.Double)pageLengths.get(pageNum)).doubleValue();}
			// scroll so that apporiate part of document is lined up
			// with the upper-left corner of the page
			g2d.translate(0.0,-pageStart);
			
			return g2d;
		}
		// PrinterJob calls the print method to render the graphics
		// object, starting at pageIndex of 0
		// return PAGE_EXISTS if you have printed that page
		// return NO_SUCH_PAGE if there are no more pages left
	    public int print(Graphics g,PageFormat format,int pagenum) {
		   if (pagenum >= numPages) {return Printable.NO_SUCH_PAGE;}
		   Graphics2D g2d = printPageSetup(g,pagenum);
		   paint(g2d);
		   return Printable.PAGE_EXISTS;
	    }
	}
