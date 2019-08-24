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

public class ConnectLine {
    public static final int LINE_TYPE_SIMPLE = 0;
    public static final int LINE_TYPE_RECT_1BREAK = 1;
    public static final int LINE_TYPE_RECT_2BREAK = 2;

    public static final int LINE_START_HORIZONTAL = 0;
    public static final int LINE_START_VERTICAL = 1;

    public static final int LINE_ARROW_NONE = 0;
    public static final int LINE_ARROW_SOURCE = 1;
    public static final int LINE_ARROW_DEST = 2;
    public static final int LINE_ARROW_BOTH = 3;

    public int LINE_ARROW_WIDTH = 10;

    /**
     * Source line point
     */
    Point p1;
    /**
     * Destination line point
     */
    Point p2;

    /**
     * Line type can be one of LINE_TYPE_SIMPLE, LINE_TYPE_RECT_1BREAK, LINE_TYPE_RECT_2BREAK
     */
    int lineType = LINE_TYPE_SIMPLE;
    /**
     * for the LINE_TYPE_RECT_2BREAK type the param defines how line should be rendered
     */
    int lineStart = LINE_START_HORIZONTAL;
    /**
     * arrow can be one of following
     * LINE_ARROW_NONE - no arrow
     * LINE_ARROW_SOURCE - arrow beside source point
     * LINE_ARROW_DEST - arrow beside dest point
     * LINE_ARROW_BOTH - both source and dest has arrows
     */
    int lineArrow = LINE_ARROW_NONE;
	GeneralPath linePath = null;
    /**
     * Constructs default line
     * @param p1 Point start
     * @param p2 Point end
     */
    public ConnectLine(Point p1, Point p2) {
        this(p1, p2, LINE_TYPE_SIMPLE, LINE_START_HORIZONTAL, LINE_ARROW_NONE);
    }

    /**
     * Constructs line with specified params
     * @param p1 Point start
     * @param p2 Point end
     * @param lineType int type of line (LINE_TYPE_SIMPLE, LINE_TYPE_RECT_1BREAK, LINE_TYPE_RECT_2BREAK)
     * @param lineStart int for the LINE_TYPE_RECT_2BREAK type the param defines how line should be rendered
     * @param lineArrow int defines line arrow type
     */
    public ConnectLine(Point p1, Point p2, int lineType, int lineStart, int lineArrow) {
        this.p1 = p1;
        this.p2 = p2;
        this.lineType = lineType;
        this.lineStart = lineStart;
        this.lineArrow = lineArrow;
		this.linePath = null;
    }

    /**
     * Paints the line with specified params
     * @param g2d Graphics2D
     */
    public void paint(Graphics2D g2d) {
		linePath = new GeneralPath();
        switch (lineType) {
            case LINE_TYPE_SIMPLE:
                paintSimple(g2d);
                break;
            case LINE_TYPE_RECT_1BREAK:
                paint1Break(g2d);
                break;
            case LINE_TYPE_RECT_2BREAK:
                paint2Breaks(g2d);
                break;
        }
    }

    protected void paintSimple(Graphics2D g2d) {
		linePath.moveTo(p1.x, p1.y);linePath.lineTo(p2.x, p2.y);
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        switch (lineArrow) {
            case LINE_ARROW_DEST:
                paintArrow(g2d, p1, p2);
                break;
            case LINE_ARROW_SOURCE:
                paintArrow(g2d, p2, p1);
                break;
            case LINE_ARROW_BOTH:
                paintArrow(g2d, p1, p2);
                paintArrow(g2d, p2, p1);
                break;
        }
    }

    protected void paintArrow(Graphics2D g2d, Point p1, Point p2) {
        paintArrow(g2d, p1, p2, getRestrictedArrowWidth(p1, p2));
    }

    protected void paintArrow(Graphics2D g2d, Point p1, Point p2, int width) {
        Point2D.Float pp1 = new Point2D.Float(p1.x, p1.y);
        Point2D.Float pp2 = new Point2D.Float(p2.x, p2.y);
        Point2D.Float left = getLeftArrowPoint(pp1, pp2, width);
        Point2D.Float right = getRightArrowPoint(pp1, pp2, width);

        g2d.drawLine(p2.x, p2.y, Math.round(left.x), Math.round(left.y));
        g2d.drawLine(p2.x, p2.y, Math.round(right.x), Math.round(right.y));
    }

    protected void paint1Break(Graphics2D g2d) {
        if (lineStart == LINE_START_HORIZONTAL) {
			linePath.moveTo(p1.x, p1.y);linePath.lineTo(p2.x, p1.y);linePath.lineTo(p2.x, p2.y);
            g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
            switch (lineArrow) {
                case LINE_ARROW_DEST:
                    paintArrow(g2d, new Point(p2.x, p1.y), p2);
                    break;
                case LINE_ARROW_SOURCE:
                    paintArrow(g2d, new Point(p2.x, p1.y), p1);
                    break;
                case LINE_ARROW_BOTH:
                    paintArrow(g2d, new Point(p2.x, p1.y), p2);
                    paintArrow(g2d, new Point(p2.x, p1.y), p1);
                    break;
            }
        }
        else if (lineStart == LINE_START_VERTICAL) {
			linePath.moveTo(p1.x, p1.y);linePath.lineTo(p1.x, p2.y);linePath.lineTo(p2.x, p2.y);
            g2d.drawLine(p1.x, p1.y, p1.x, p2.y);
            g2d.drawLine(p1.x, p2.y, p2.x, p2.y);
            switch (lineArrow) {
                case LINE_ARROW_DEST:
                    paintArrow(g2d, new Point(p1.x, p2.y), p2);
                    break;
                case LINE_ARROW_SOURCE:
                    paintArrow(g2d, new Point(p1.x, p2.y), p1);
                    break;
                case LINE_ARROW_BOTH:
                    paintArrow(g2d, new Point(p1.x, p2.y), p2);
                    paintArrow(g2d, new Point(p1.x, p2.y), p1);
                    break;
            }
        }
    }

    protected void paint2Breaks(Graphics2D g2d) {
        if (lineStart == LINE_START_HORIZONTAL) {
			linePath.moveTo(p1.x, p1.y);linePath.lineTo(p1.x + (p2.x - p1.x) / 2, p1.y);
			linePath.lineTo(p1.x + (p2.x - p1.x) / 2, p2.y);linePath.lineTo(p2.x, p2.y);
            g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
            switch (lineArrow) {
                case LINE_ARROW_DEST:
                    paintArrow(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p2.y), p2);
                    break;
                case LINE_ARROW_SOURCE:
                    paintArrow(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p1.y), p1);
                    break;
                case LINE_ARROW_BOTH:
                    paintArrow(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p2.y), p2);
                    paintArrow(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p1.y), p1);
                    break;
            }
        }
        else if (lineStart == LINE_START_VERTICAL) {
			linePath.moveTo(p1.x, p1.y);linePath.lineTo( p1.x, p1.y + (p2.y - p1.y) / 2);
			linePath.lineTo(p2.x, p1.y + (p2.y - p1.y) / 2);linePath.lineTo(p2.x, p2.y);
            
			g2d.drawLine(p1.x, p1.y, p1.x, p1.y + (p2.y - p1.y) / 2);
            g2d.drawLine(p1.x, p1.y + (p2.y - p1.y) / 2, p2.x, p1.y + (p2.y - p1.y) / 2);
            g2d.drawLine(p2.x, p1.y + (p2.y - p1.y) / 2, p2.x, p2.y);

            switch (lineArrow) {
                case LINE_ARROW_DEST:
                    paintArrow(g2d, new Point(p2.x, p1.y + (p2.y - p1.y) / 2), p2);
                    break;
                case LINE_ARROW_SOURCE:
                    paintArrow(g2d, new Point(p1.x, p1.y + (p2.y - p1.y) / 2), p1);
                    break;
                case LINE_ARROW_BOTH:
                    paintArrow(g2d, new Point(p2.x, p1.y + (p2.y - p1.y) / 2), p2);
                    paintArrow(g2d, new Point(p1.x, p1.y + (p2.y - p1.y) / 2), p1);
                    break;
            }
        }
    }

    public int getLineType() {
        return lineType;
    }

    public void setLineType(int type) {
        lineType = type;
    }

    public int getLineStart() {
        return lineStart;
    }

    public void setLineStart(int start) {
        lineStart = start;
    }

    public int getLineArrow() {
        return lineArrow;
    }

    public void setLineArrow(int arrow) {
        lineType = lineArrow;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p) {
        p1 = p;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p) {
        p2 = p;
    }
	public GeneralPath getLinePath() {
		return linePath;
	}

    protected Point2D.Float getMidArrowPoint(Point2D.Float p1, Point2D.Float p2, float w) {
        Point2D.Float res = new Point2D.Float();
        float d = Math.round(Math.sqrt( (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)));

        if (p1.x < p2.x) {
            res.x = p2.x - w * Math.abs(p1.x - p2.x) / d;
        }
        else {
            res.x = p2.x + w * Math.abs(p1.x - p2.x) / d;
        }

        if (p1.y < p2.y) {
            res.y = p2.y - w * Math.abs(p1.y - p2.y) / d;
        }
        else {
            res.y = p2.y + w * Math.abs(p1.y - p2.y) / d;
        }

        return res;
    }

    protected Point2D.Float getLeftArrowPoint(Point2D.Float p1, Point2D.Float p2) {
        return getLeftArrowPoint(p1, p2, LINE_ARROW_WIDTH);
    }

    protected Point2D.Float getLeftArrowPoint(Point2D.Float p1, Point2D.Float p2, float w) {
        Point2D.Float res = new Point2D.Float();
        double alpha = Math.PI / 2;
        if (p2.x != p1.x) {
            alpha = Math.atan( (p2.y - p1.y) / (p2.x - p1.x));
        }
        alpha += Math.PI / 10;
        float xShift = Math.abs(Math.round(Math.cos(alpha) * w));
        float yShift = Math.abs(Math.round(Math.sin(alpha) * w));
        if (p1.x <= p2.x) {
            res.x = p2.x - xShift;
        }
        else {
            res.x = p2.x + xShift;
        }
        if (p1.y < p2.y) {
            res.y = p2.y - yShift;
        }
        else {
            res.y = p2.y + yShift;
        }
        return res;
    }

    protected Point2D.Float getRightArrowPoint(Point2D.Float p1, Point2D.Float p2) {
        return getRightArrowPoint(p1, p2, LINE_ARROW_WIDTH);
    }

    protected Point2D.Float getRightArrowPoint(Point2D.Float p1, Point2D.Float p2, float w) {
        Point2D.Float res = new Point2D.Float();
        double alpha = Math.PI / 2;
        if (p2.x != p1.x) {
            alpha = Math.atan( (p2.y - p1.y) / (p2.x - p1.x));
        }
        alpha -= Math.PI / 10;
        float xShift = Math.abs(Math.round(Math.cos(alpha) * w));
        float yShift = Math.abs(Math.round(Math.sin(alpha) * w));
        if (p1.x < p2.x) {
            res.x = p2.x - xShift;
        }
        else {
            res.x = p2.x + xShift;
        }
        if (p1.y <= p2.y) {
            res.y = p2.y - yShift;
        }
        else {
            res.y = p2.y + yShift;
        }
        return res;
    }

    protected int getRestrictedArrowWidth(Point p1, Point p2) {
        return Math.min(LINE_ARROW_WIDTH, (int) Math.sqrt( (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)));
    }
}
