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

public class JConnector {
    public static final int CONNECT_LINE_TYPE_SIMPLE = 0;
    public static final int CONNECT_LINE_TYPE_RECTANGULAR = 1;
    protected drawingItem source;
    protected drawingItem dest;
    protected ConnectLine line;
    protected int lineArrow = ConnectLine.LINE_ARROW_NONE;
    protected int lineType = CONNECT_LINE_TYPE_RECTANGULAR;
    protected Color lineColor;

    /**
     * Constructs default connector.
     * @param source drawingItem
     * @param dest darwingItem
     */
    public JConnector(drawingItem source, drawingItem dest) {
        this(source, dest, ConnectLine.LINE_ARROW_NONE, Color.BLACK);
    }

    /**
     * Constructs a connector with specified arrow and color.
     * @param source drawingItem
     * @param dest drawingItem
     * @param lineArrow int
     * @param lineColor Color
     */
    public JConnector(drawingItem source, drawingItem dest, int lineArrow, Color lineColor) {
        this(source, dest, lineArrow, CONNECT_LINE_TYPE_RECTANGULAR, lineColor);
    }

    /**
     * Constructs a connector with specified arrow, line type and color.
     * @param source drawingItem
     * @param dest drawingItem
     * @param lineArrow int
     * @param lineType int
     * @param lineColor Color
     */
    public JConnector(drawingItem source, drawingItem dest, int lineArrow, int lineType, Color lineColor) {
        this.source = source;
        this.dest = dest;
        this.lineArrow = lineArrow;
        this.lineType = lineType;
        this.lineColor = lineColor;
    }	
	public GeneralPath getConnectorPath() {return line.getLinePath();}
	public Point getP1() {return line.getP1();}
	public Point getP2() {return line.getP2();}
    /**
     * It resets clip to draw connecting line between components and set the clip back.
     * @param g2d Graphics2D
     */
    public void drawConnector(Graphics2D g2d) {
        calculateLine();
        if (line != null) {
            Shape oldClip = g2d.getClip();
            g2d.setClip(getLineBounds());
            g2d.setColor(lineColor);
            line.paint(g2d);
            g2d.setClip(oldClip);
        }
    }

    protected void calculateLine() {
        Rectangle rSource = source.getBoundingRect();
        Rectangle rDest = dest.getBoundingRect();
		
        if (rSource.intersects(rDest)) {
            line = null;
            return;
        }

        boolean xIntersect = (rSource.x <= rDest.x && rSource.x + rSource.width >= rDest.x)
            || (rDest.x <= rSource.x && rDest.x + rDest.width >= rSource.x);
        boolean yIntersect = rSource.y <= rDest.y && rSource.y + rSource.height >= rDest.y
            || (rDest.y <= rSource.y && rDest.y + rDest.height >= rSource.y);

        if (xIntersect) {
            int y1;
            int y2;
            int x1 = rSource.x + rSource.width / 2;
            int x2 = rDest.x + rDest.width / 2;
            if (rSource.y + rSource.height <= rDest.y) {
                //source higher
                y1 = rSource.y + rSource.height;
                y2 = rDest.y;
            }
            else {
                y1 = rSource.y;
                y2 = rDest.y + rDest.height;
            }
            line = new ConnectLine(new Point(x1, y1), new Point(x2, y2), ConnectLine.LINE_TYPE_RECT_2BREAK, ConnectLine.LINE_START_VERTICAL, lineArrow);
            if (lineType == CONNECT_LINE_TYPE_SIMPLE) {
                line.setLineType(ConnectLine.LINE_TYPE_SIMPLE);
            }
        }
        else if (yIntersect) {
            int y1 = rSource.y + rSource.height / 2;
            ;
            int y2 = rDest.y + rDest.height / 2;
            ;
            int x1;
            int x2;
            if (rSource.x + rSource.width <= rDest.x) {
                x1 = rSource.x + rSource.width;
                x2 = rDest.x;
            }
            else {
                x1 = rSource.x;
                x2 = rDest.x + rDest.width;
            }
            line = new ConnectLine(new Point(x1, y1), new Point(x2, y2), ConnectLine.LINE_TYPE_RECT_2BREAK, ConnectLine.LINE_START_HORIZONTAL, lineArrow);
            if (lineType == CONNECT_LINE_TYPE_SIMPLE) {
                line.setLineType(ConnectLine.LINE_TYPE_SIMPLE);
            }
        }
        else {
            int y1;
            int y2;
            int x1;
            int x2;
            if (rSource.y + rSource.height <= rDest.y) {
                //source higher
                y1 = rSource.y + rSource.height / 2;
                y2 = rDest.y;
                if (rSource.x + rSource.width <= rDest.x) {
                    x1 = rSource.x + rSource.width;
                }
                else {
                    x1 = rSource.x;
                }
                x2 = rDest.x + rDest.width / 2;
            }
            else {
                y1 = rSource.y + rSource.height / 2;
                y2 = rDest.y + rDest.height;
                if (rSource.x + rSource.width <= rDest.x) {
                    x1 = rSource.x + rSource.width;
                }
                else {
                    x1 = rSource.x;
                }
                x2 = rDest.x + rDest.width / 2;
            }
            line = new ConnectLine(new Point(x1, y1), new Point(x2, y2), ConnectLine.LINE_TYPE_RECT_1BREAK, ConnectLine.LINE_START_HORIZONTAL, lineArrow);
            if (lineType == CONNECT_LINE_TYPE_SIMPLE) {
                line.setLineType(ConnectLine.LINE_TYPE_SIMPLE);
            }
        }
    }

    protected Rectangle getLineBounds() {
        int add = 10;
        int maxX = Math.max(line.getP1().x, line.getP2().x);
        int minX = Math.min(line.getP1().x, line.getP2().x);
        int maxY = Math.max(line.getP1().y, line.getP2().y);
        int minY = Math.min(line.getP1().y, line.getP2().y);

        Rectangle res = new Rectangle(minX - add, minY - add, maxX - minX + 2 * add, maxY - minY + 2 * add);
        return res;
    }

    public drawingItem getSource() {
        return source;
    }
    public drawingItem getDest() {
        return dest;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color c) {
        lineColor = c;
    }

    public int getLineType() {
        return lineType;
    }

    public void setLineType(int type) {
        lineType = type;
    }

    public int getLineArrow() {
        return lineArrow;
    }

    public void setLineArrow(int arrow) {
        lineArrow = arrow;
    }
}
