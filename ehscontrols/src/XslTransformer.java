/** Creates an XSLT transformer for processing an XML document.
 *  A new transformer, along with an style template are created 
 *  for each document transformation. The XSLT, DOM, and 
 *  SAX processors are based on system default parameters.
 */ 

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

public class XslTransformer {
  private TransformerFactory factory;
  private xslTransformerUtils target;

  public void addxslUtilsListener(xslTransformerUtils xtu) {target = xtu;}
  public void removexslUtilsListener() {target = null;}
  public XslTransformer() {
    factory =  TransformerFactory.newInstance();
    target = null;
  }

  /** Transform an XML and XSL document as <code>Reader</code>s,
   *  placing the resulting transformed document in a 
   *  <code>Writer</code>. Convenient for handling an XML 
   *  documents as a String (<code>StringReader</code>) residing
   *  in memory, not on disk. The output document could easily be
   *  handled as a String (<code>StringWriter</code>) or as a
   *  <code>JSPWriter</code> in a JavaServer page.
   */

  public void process(Reader xmlFile, Reader xslFile,
                      Writer output)
                throws TransformerException {
    process(new StreamSource(xmlFile),
            new StreamSource(xslFile),
            new StreamResult(output));
  }

  /** Transform an XML and XSL document as <code>File</code>s,
   *  placing the resulting transformed document in a 
   *  <code>Writer</code>. The output document could easily 
   *  be handled as a String (<code>StringWriter</code)> or as 
   *  a <code>JSPWriter</code> in a JavaServer page.
   */

  public void process(File xmlFile, File xslFile,
                      Writer output)
                throws TransformerException {
    process(new StreamSource(xmlFile),
            new StreamSource(xslFile),
            new StreamResult(output));
  }

  /** Transform an XML <code>File</code> based on an XSL 
   *  <code>File</code>, placing the resulting transformed 
   *  document in an <code>OutputStream</code>. Convenient for 
   *  handling the result as a <code>FileOutputStream</code> or 
   *  <code>ByteArrayOutputStream</code>.
   */

  public void process(File xmlFile, File xslFile,
                      OutputStream out)
                 throws TransformerException {
    process(new StreamSource(xmlFile),
            new StreamSource(xslFile),
            new StreamResult(out));
  }

  /** Transform an XML source using XSLT based on a new template
   *  for the source XSL document. The resulting transformed 
   *  document is placed in the passed in <code>Result</code> 
   *  object.
   */

  public void process(Source xml, Source xsl, Result result)
                throws TransformerException {
    try {
      Templates template = factory.newTemplates(xsl);
      Transformer transformer = template.newTransformer();
      if (target != null) {
	      target.preTransform(transformer);
      }
      transformer.transform(xml, result);
    } catch(TransformerConfigurationException tce) {
        throw new TransformerException(
                    tce.getMessageAndLocation());
    } catch (TransformerException te) {
      throw new TransformerException(
                  te.getMessageAndLocation());
    }
  }
}
