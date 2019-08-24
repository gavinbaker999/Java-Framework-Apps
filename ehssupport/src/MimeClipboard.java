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

class MimeClipboard extends Clipboard
	{  public MimeClipboard(Clipboard cb) 
	   {  super("MIME/" + cb.getName());
	      clip = cb;
	   }

	   public synchronized void setContents(Transferable contents, 
	      ClipboardOwner owner) 
	   {  if (contents instanceof SerializableSelection) 
	      {  try
	         {  DataFlavor flavor 
	               = serializableFlavor;
	            Serializable obj = (Serializable)
	               contents.getTransferData(flavor);
	            String enc = encode(obj);
	            String header = "Content-type: " 
	               + flavor.getMimeType()
	               + "\nContent-length: " 
	               + enc.length() + "\n\n";
	            StringSelection selection 
	               = new StringSelection(header + enc);
	            clip.setContents(selection, owner);
	         }
	         catch(UnsupportedFlavorException e)
	         {}
	         catch(IOException e)
	         {}
	      }
	      else clip.setContents(contents, owner);
	   }

	   public synchronized Transferable getContents
	      (Object requestor) 
	   {  Transferable contents = clip.getContents(requestor);

	      if (contents instanceof StringSelection)
	      {  String data = null;
	         try  
	         {  data = (String)contents.getTransferData
	               (DataFlavor.stringFlavor);
	         }
	         catch(UnsupportedFlavorException e)
	         { return contents; }
	         catch(IOException e)
	         { return contents; }

	         if (!data.startsWith("Content-type: "))
	            return contents;
	         int start = -1;
	         // skip three newlines
	         for (int i = 0; i < 3; i++)
	         {  start = data.indexOf('\n', start + 1);
	            if (start < 0) return contents;
	          }
	         Serializable obj = decode(data, start);
	         SerializableSelection selection
	            = new SerializableSelection(obj);
	         return selection;
	      }
	      else return contents;
	   }

	   private String encode(Serializable obj)
	   {  try
	      {  StringBuffer sbuf = new StringBuffer();
	         Base64OutputStream bout
	            = new Base64OutputStream(sbuf);
	         ObjectOutputStream out
	            = new ObjectOutputStream(bout);
	         out.writeObject(obj);
	         out.flush();
	         return sbuf.toString();
	      }  
	      catch(Exception e)
	      {  return "";
	      } 
	   }

	   private Serializable decode(String s, int start)
	   {  try
	      {  Base64InputStream bin
	            = new Base64InputStream(s, start);
	         ObjectInputStream in
	            = new ObjectInputStream(bin);
	         Object obj = in.readObject();
	         return (Serializable)obj;
	      }  
	      catch(Exception e)
	      {  return null;
	      } 
	   }

	   public final DataFlavor serializableFlavor
	      = new DataFlavor(java.io.Serializable.class,"Serializable Object");
	   private Clipboard clip;
	}
