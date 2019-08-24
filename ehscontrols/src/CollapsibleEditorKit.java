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

public class CollapsibleEditorKit extends HTMLEditorKit { //StyledEditorKit {
	    MouseListener lstCollapse=new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            JEditorPane src=(JEditorPane)e.getSource();

	            int pos=src.viewToModel(e.getPoint());
	            View v=src.getUI().getRootView(src);
	            while (v!=null && !(v instanceof CollapsibleView)) {
	                int i=v.getViewIndex(pos, Position.Bias.Forward);
	                v=v.getView(i);
	            }

	            if (v!=null) {
	                Shape a=getAllocation(v, src);
	                if (a!=null) {
	                    Rectangle r=a instanceof Rectangle ? (Rectangle)a : a.getBounds();
	                    r.width=CollapsibleView.AREA_SHIFT;
	                    r.height=CollapsibleView.AREA_SHIFT;

	                    if (r.contains(e.getPoint())) {
	                        CollapsibleView cv=(CollapsibleView)v;
	                        cv.setExpanded(!cv.isExpanded());

	                        DefaultStyledDocument doc= (DefaultStyledDocument)src.getDocument();
	                        try {
	                            doc.insertString(pos, "\n", new SimpleAttributeSet());
	                            doc.remove(pos,1);
	                        } catch (BadLocationException e1) {
	                            e1.printStackTrace();
	                        }
	                    }
	                }
	            }
	        }
	    };

	    Cursor oldCursor;
	    MouseMotionListener lstMoveCollapse=new MouseMotionAdapter() {
	        public void mouseMoved(MouseEvent e) {
	            JEditorPane src=(JEditorPane)e.getSource();
	            if (oldCursor==null) {
	                oldCursor=src.getCursor();
	            }

	            int pos=src.viewToModel(e.getPoint());
	            View v=src.getUI().getRootView(src);
	            while (v!=null && !(v instanceof CollapsibleView)) {
	                int i=v.getViewIndex(pos, Position.Bias.Forward);
	                v=v.getView(i);
	            }

	            if (v!=null) {
	                Shape a=getAllocation(v, src);
	                if (a!=null) {
	                    Rectangle r=a instanceof Rectangle ? (Rectangle)a : a.getBounds();
	                    r.width=CollapsibleView.AREA_SHIFT;
	                    r.height=CollapsibleView.AREA_SHIFT;

	                    if (r.contains(e.getPoint())) {
	                        CollapsibleView cv=(CollapsibleView)v;

	                        src.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	                        return;
	                    }
	                }
	            }

	            src.setCursor(oldCursor);
	        }
	    };

	    public ViewFactory getViewFactory() {
	        return new CollapsibleEditorKit.StyledViewFactory();
	    }
	    public void install(JEditorPane c) {
	        super.install(c);
	        c.addMouseListener(lstCollapse);
	        c.addMouseMotionListener(lstMoveCollapse);
	    }
	    public void deinstall(JEditorPane c) {
	        c.removeMouseListener(lstCollapse);
	        c.removeMouseMotionListener(lstMoveCollapse);
	        super.deinstall(c);
	    }

	    protected Shape getAllocation(View v, JEditorPane edit) {
	        Insets ins=edit.getInsets();
	        View vParent=v.getParent();
	        int x=ins.left;
	        int y=ins.top;
	        while(vParent!=null) {
	            int i=vParent.getViewIndex(v.getStartOffset(), Position.Bias.Forward);
	            Shape alloc=vParent.getChildAllocation(i, new Rectangle(0,0, Short.MAX_VALUE, Short.MAX_VALUE));
	            x+=alloc.getBounds().x;
	            y+=alloc.getBounds().y;

	            vParent=vParent.getParent();
	        }

	        if (v instanceof BoxView) {
	            int ind=v.getParent().getViewIndex(v.getStartOffset(), Position.Bias.Forward);
	            Rectangle r2=v.getParent().getChildAllocation(ind, new Rectangle(0,0,Integer.MAX_VALUE,Integer.MAX_VALUE)).getBounds();

	            return new Rectangle(x,y, r2.width, r2.height);
	        }

	        return new Rectangle(x,y, (int)v.getPreferredSpan(View.X_AXIS), (int)v.getPreferredSpan(View.Y_AXIS));
	    }


	    class StyledViewFactory implements ViewFactory {

	        public View create(Element elem) {
	            String kind = elem.getName();
	            if (kind != null) {
	                if (kind.equals(AbstractDocument.ContentElementName)) {
	                    return new LabelView(elem);
	                }
	                else if (kind.equals(AbstractDocument.ParagraphElementName)) {
	                    return new ParagraphView(elem);
	                }
	                else if (kind.equals(AbstractDocument.SectionElementName)) {
	                    return new BoxView(elem, View.Y_AXIS);
	                }
	                else if (kind.equals(StyleConstants.ComponentElementName)) {
	                    return new ComponentView(elem);
	                }
	                else if (kind.equals(StyleConstants.IconElementName)) {
	                    return new IconView(elem);
	                }
	                else if (kind.equals(ehsConstants.COLLAPSIBLE_AREA_ELEMENT)) {
	                    return new CollapsibleView(elem);
	                }
	            }

	            // default to text display
	            return new LabelView(elem);
	        }

	    }
	}
