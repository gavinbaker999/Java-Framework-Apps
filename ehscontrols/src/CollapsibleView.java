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

public class CollapsibleView extends BoxView {
	    private boolean isExpanded=true;
	    public static final int AREA_SHIFT=10;

	    public CollapsibleView(Element elem) {
	        super(elem, View.Y_AXIS);
	        setInsets((short)0, (short)AREA_SHIFT, (short)0, (short)0);
	    }
	    
	    public void paint(Graphics g, Shape alloc) {
	        Rectangle a=alloc instanceof Rectangle ? (Rectangle)alloc : alloc.getBounds();
	        Shape oldClip=g.getClip();
	        if (!isExpanded()) {
	            Area newClip=new Area(oldClip);
	            newClip.intersect(new Area(a));
	            g.setClip(newClip);
	        }
	        super.paint(g, a);
	        g.setClip(oldClip);
	        a.width--;
	        a.height--;
	        g.setColor(Color.lightGray);
	        ((Graphics2D)g).draw(a);
	        g.drawRect(a.x,  a.y, AREA_SHIFT,AREA_SHIFT);

	        if (!isExpanded()) {
	            g.drawLine(a.x+AREA_SHIFT/2, a.y+2, a.x+AREA_SHIFT/2, a.y+AREA_SHIFT-2);
	        }
	        g.drawLine(a.x+2, a.y+AREA_SHIFT/2, a.x+AREA_SHIFT-2, a.y+AREA_SHIFT/2);
	    }

	    public float getPreferredSpan(int axis) {
	        if (isExpanded() || axis!=View.Y_AXIS) {
	            return super.getPreferredSpan(axis);
	        }
	        else {
	            View firstChild=getView(0);
	            if (firstChild instanceof BoxView && ((BoxView)firstChild).getAxis()==View.Y_AXIS) {
	                return getTopInset()+firstChild.getView(0).getPreferredSpan(View.Y_AXIS);
	            }
	            else {
	                return getTopInset()+firstChild.getPreferredSpan(View.Y_AXIS);
	            }
	        }
	    }

	    public float getMinimumSpan(int axis) {
	        if (isExpanded() || axis!=View.Y_AXIS) {
	            return super.getMinimumSpan(axis);
	        }
	        else {
	            View firstChild=getView(0);
	            if (firstChild instanceof BoxView && ((BoxView)firstChild).getAxis()==View.Y_AXIS) {
	                return getTopInset()+firstChild.getView(0).getMinimumSpan(View.Y_AXIS);
	            }
	            else {
	                return getTopInset()+firstChild.getMinimumSpan(View.Y_AXIS);
	            }
	        }
	    }

	    public float getMaximumSpan(int axis) {
	        if (isExpanded() || axis!=View.Y_AXIS) {
	            return super.getMaximumSpan(axis);
	        }
	        else {
	            View firstChild=getView(0);
	            if (firstChild instanceof BoxView && ((BoxView)firstChild).getAxis()==View.Y_AXIS) {
	                return getTopInset()+firstChild.getView(0).getMaximumSpan(View.Y_AXIS);
	            }
	            else {
	                return getTopInset()+firstChild.getMaximumSpan(View.Y_AXIS);
	            }
	        }
	    }

	    public boolean isExpanded() {
	        return isExpanded;
	    }

	    public void setExpanded(boolean expanded) {
	        isExpanded = expanded;
	    }

	    protected int getNextNorthSouthVisualPositionFrom(int pos, Position.Bias b,
							      Shape a, int direction,
							      Position.Bias[] biasRet)
		                                        throws BadLocationException {
	        int newPos=super.getNextNorthSouthVisualPositionFrom(pos, b, a, direction, biasRet);
	        if (!isExpanded()) {
	            if (newPos<getView(0).getView(0).getEndOffset()) {
	                //first line of first child
	                return newPos;
	            }
	            if (direction== SwingConstants.SOUTH) {
	                int ind=getParent().getViewIndex(getStartOffset(), Position.Bias.Forward);
	                if (ind<getParent().getViewCount()) {
	                    while (newPos<getEndOffset() && newPos>=0) {
	                        int p=super.getNextNorthSouthVisualPositionFrom(newPos, b, a, direction, biasRet);
	                        if (p<0) {
	                            newPos=getParent().getNextVisualPositionFrom(newPos, b, a, direction, biasRet);
	                            break;
	                        }
	                        newPos=p;
	                    }
	                }
	            }
	            else {
	                int ind=getParent().getViewIndex(getStartOffset(), Position.Bias.Forward);
	                if (ind<getParent().getViewCount()) {
	                    while (newPos>getStartOffset() && newPos>0) {
	                        int p=super.getNextNorthSouthVisualPositionFrom(newPos, b, a, direction, biasRet);
	                        if (p<0) {
	                            newPos=getParent().getNextVisualPositionFrom(newPos, b, a, direction, biasRet);
	                            break;
	                        }
	                        newPos=p;
	                        if (newPos<getView(0).getView(0).getEndOffset()) {
	                            //first line of first child
	                            return newPos;
	                        }
	                    }
	                }
	            }
	        }

	        return newPos;
	    }

	    protected int getNextEastWestVisualPositionFrom(int pos, Position.Bias b,
							    Shape a,
							    int direction,
							    Position.Bias[] biasRet)
		                                        throws BadLocationException {
	        int newPos=super.getNextEastWestVisualPositionFrom(pos, b, a, direction, biasRet);
	        if (!isExpanded()) {
	            if (newPos>=getStartOffset() && newPos<getView(0).getView(0).getEndOffset()) {
	                //first line of first child
	                return newPos;
	            }
	            else if (newPos>=getView(0).getView(0).getEndOffset()) {
	                if (direction==SwingConstants.EAST) {
	                    newPos=Math.min(getDocument().getLength()-1, getEndOffset());
	                }
	                else {
	                    newPos=getView(0).getView(0).getEndOffset()-1;
	                }
	            }
	        }

	        return newPos;
	    }
	}
