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

public class JScrollPaneRulerHeader extends JComponent {
	    public int INCH = Toolkit.getDefaultToolkit().getScreenResolution();
	    public static final int HORIZONTAL = 0;
	    public static final int VERTICAL = 1;
	    public static final int SIZE = 35;
	 
	    public int orientation;
	    public boolean isMetric;
	    private int increment;
	    private int units;
	 
	    public JScrollPaneRulerHeader(int o, boolean m) {
	        orientation = o;
	        isMetric = m;
	        setIncrementAndUnits();
	    }
	 
	    public void setIsMetric(boolean isMetric) {
	        this.isMetric = isMetric;
	        setIncrementAndUnits();
	        repaint();
	    }
	 
	    private void setIncrementAndUnits() {
	        if (isMetric) {
	            units = (int)((double)INCH / (double)2.54); // dots per centimeter
	            increment = units;
	        } else {
	            units = INCH;
	            increment = units / 2;
	        }
	    }
	 
	    public boolean isMetric() {
	        return this.isMetric;
	    }
	 
	    public int getIncrement() {
	        return increment;
	    }
	 
	    public void setPreferredHeight(int ph) {
	        setPreferredSize(new Dimension(SIZE, ph));
	    }
	 
	    public void setPreferredWidth(int pw) {
	        setPreferredSize(new Dimension(pw, SIZE));
	    }
	 
	    protected void paintComponent(Graphics g) {
	        Rectangle drawHere = g.getClipBounds();
	 
	        // Fill clipping area with dirty brown/orange.
	        g.setColor(new Color(230, 163, 4));
	        g.fillRect(drawHere.x, drawHere.y, drawHere.width, drawHere.height);
	 
	        // Do the ruler labels in a small font that's black.
	        g.setFont(new Font("SansSerif", Font.PLAIN, 10));
	        g.setColor(Color.black);
	 
	        // Some vars we need.
	        int end = 0;
	        int start = 0;
	        int tickLength = 0;
	        String text = null;
	 
	        // Use clipping bounds to calculate first and last tick locations.
	        if (orientation == HORIZONTAL) {
	            start = (drawHere.x / increment) * increment;
	            end = (((drawHere.x + drawHere.width) / increment) + 1)
	                  * increment;
	        } else {
	            start = (drawHere.y / increment) * increment;
	            end = (((drawHere.y + drawHere.height) / increment) + 1)
	                  * increment;
	        }
	 
	        // Make a special case of 0 to display the number
	        // within the rule and draw a units label.
	        if (start == 0) {
	            text = Integer.toString(0) + (isMetric ? " cm" : " in");
	            tickLength = 10;
	            if (orientation == HORIZONTAL) {
	                g.drawLine(0, SIZE-1, 0, SIZE-tickLength-1);
	                g.drawString(text, 2, 21);
	            } else {
	                g.drawLine(SIZE-1, 0, SIZE-tickLength-1, 0);
	                g.drawString(text, 9, 10);
	            }
	            text = null;
	            start = increment;
	        }
	 
	        // ticks and labels
	        for (int i = start; i < end; i += increment) {
	            if (i % units == 0)  {
	                tickLength = 10;
	                text = Integer.toString(i/units);
	            } else {
	                tickLength = 7;
	                text = null;
	            }
	 
	            if (tickLength != 0) {
	                if (orientation == HORIZONTAL) {
	                    g.drawLine(i, SIZE-1, i, SIZE-tickLength-1);
	                    if (text != null)
	                        g.drawString(text, i-3, 21);
	                } else {
	                    g.drawLine(SIZE-1, i, SIZE-tickLength-1, i);
	                    if (text != null)
	                        g.drawString(text, 9, i+3);
	                }
	            }
	        }
	    }
	}
