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
import java.awt.geom.*;
import java.lang.reflect.*;
import java.util.Date;
import java.util.Map;
import java.text.*;
import java.beans.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
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
import javax.script.*;
import javax.swing.filechooser.*;
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
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.activation.URLDataSource.*;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.Utilities;
import javax.swing.text.html.HTMLEditorKit.*;
import javax.swing.text.html.ParagraphView;
import javax.swing.text.html.HTMLDocument.*;

import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.DefaultStyledDocument;

import java.sql.*;
//import javax.jms.*;
import javax.naming.*;

//import javax.xml.xquery.XQConnection;
//import javax.xml.xquery.XQDataSource;
//import javax.xml.xquery.XQException;
//import javax.xml.xquery.XQPreparedExpression;
//import javax.xml.xquery.XQResultSequence;
//import com.saxonica.xqj.SaxonXQDataSource;

public class XMLDocumentWriter {
    pseduoFile out;

    public XMLDocumentWriter(String filename) {	
		out = new pseduoFile(supportFunctions.getPath(filename),supportFunctions.getFilenameNoExt(filename) + ".xml");
		//out.saveFile("\n");
	}

    public void close() { out.flush(); }

    public void write(org.w3c.dom.Node node) { write(node, ""); }

    public void write(org.w3c.dom.Node node, String indent) {
	// The output depends on the type of the node
	switch(node.getNodeType()) {
	case org.w3c.dom.Node.DOCUMENT_NODE: {       // If its a Document node
	    org.w3c.dom.Document doc = (org.w3c.dom.Document)node;
	    out.saveFile(indent + "<?xml version='1.0'?>\n");  // Output header
	    org.w3c.dom.Node child = doc.getFirstChild();   // Get the first node
	    while(child != null) {              // Loop 'till no more nodes
		write(child, indent);           // Output node
		child = child.getNextSibling(); // Get next node
	    }
	    break;
	} 
	case org.w3c.dom.Node.DOCUMENT_TYPE_NODE: {  // It is a <!DOCTYPE> tag
	    DocumentType doctype = (DocumentType) node;
	    // Note that the DOM Level 1 does not give us information about
	    // the the public or system ids of the doctype, so we can't output
	    // a complete <!DOCTYPE> tag here.  We can do better with Level 2.
	    out.appendFile("<!DOCTYPE " + doctype.getName() + ">\n");
	    break;
	}
	case org.w3c.dom.Node.ELEMENT_NODE: {        // Most nodes are Elements
	    org.w3c.dom.Element elt = (org.w3c.dom.Element) node;
	    out.appendFile(indent + "<" + elt.getTagName());   // Begin start tag
	    NamedNodeMap attrs = elt.getAttributes();     // Get attributes
	    for(int i = 0; i < attrs.getLength(); i++) {  // Loop through them
		org.w3c.dom.Node a = attrs.item(i);
		out.appendFile(" " + a.getNodeName() + "='" +  // Print attr. name
			  fixup(a.getNodeValue()) + "'"); // Print attr. value
	    }
	    out.appendFile(">\n");                             // Finish start tag

	    String newindent = indent + "    ";           // Increase indent
	    org.w3c.dom.Node child = elt.getFirstChild();             // Get child
	    while(child != null) {                        // Loop 
		write(child, newindent);                  // Output child
		child = child.getNextSibling();           // Get next child
	    }

	    out.appendFile(indent + "</" +                   // Output end tag
			elt.getTagName() + ">\n");
	    break;
	}
	case org.w3c.dom.Node.TEXT_NODE: {                   // Plain text node
	    org.w3c.dom.Text textNode = (org.w3c.dom.Text)node;
	    String text = textNode.getData().trim();   // Strip off space
	    if ((text != null) && text.length() > 0)   // If non-empty
		out.appendFile(indent + fixup(text) + "\n");     // print text
	    break;
	}
	case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE: {  // Handle PI nodes
	    ProcessingInstruction pi = (ProcessingInstruction)node;
	    out.appendFile(indent + "<?" + pi.getTarget() +
			       " " + pi.getData() + "?>\n");
	    break;
	}
	case org.w3c.dom.Node.ENTITY_REFERENCE_NODE: {        // Handle entities
	    out.appendFile(indent + "&" + node.getNodeName() + ";\n");
	    break;
	}
	case org.w3c.dom.Node.CDATA_SECTION_NODE: {           // Output CDATA sections
	    CDATASection cdata = (CDATASection)node;
	    // Careful! Don't put a CDATA section in the program itself!
	    out.appendFile(indent + "<" + "![CDATA[" + cdata.getData() +
			"]]" + ">\n");
	    break;
	}
	case org.w3c.dom.Node.COMMENT_NODE: {                 // Comments
	    Comment c = (Comment)node;
	    out.appendFile(indent + "<!--" + c.getData() + "-->\n");
	    break;
	}
	default:   // Hopefully, this won't happen too much!
	    System.err.println("Ignoring node: " + node.getClass().getName());
	    break;
	}
    }

    // This method replaces reserved characters with entities.
    String fixup(String s) {
	StringBuffer sb = new StringBuffer();
	int len = s.length();
	for(int i = 0; i < len; i++) {
	    char c = s.charAt(i);
	    switch(c) {
	    default: sb.append(c); break;
	    case '<': sb.append("&lt;"); break;
	    case '>': sb.append("&gt;"); break;
	    case '&': sb.append("&amp;"); break;
	    case '"': sb.append("&quot;"); break;
	    case '\'': sb.append("&apos;"); break;
	    }
	}
	return sb.toString();
    }
}
