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

public class colorButtonCanvas {
		private buttonCanvas canvas;
		
		public colorButtonCanvas() { 
			String c1 = supportFunctions.getAppConfigSettings().getConfigurationSetting("customcolor1", "0,0,0");
			String c2 = supportFunctions.getAppConfigSettings().getConfigurationSetting("customcolor2", "0,0,0");
			Vector customColor1 = supportFunctions.splitIntoTokens(c1,",");
			Vector customColor2 = supportFunctions.splitIntoTokens(c2,",");
			ehsConstants.colorCodes[ehsConstants.colorCodes.length-1] = new Color(Integer.parseInt((String)customColor1.elementAt(0)),
					Integer.parseInt((String)customColor1.elementAt(1)),
					Integer.parseInt((String)customColor1.elementAt(2)));
			ehsConstants.colorCodes[ehsConstants.colorCodes.length-2] = new Color(Integer.parseInt((String)customColor2.elementAt(0)),
					Integer.parseInt((String)customColor2.elementAt(1)),
					Integer.parseInt((String)customColor2.elementAt(2)));

			canvas = new buttonCanvas("Colors",2);
			Insets m = new Insets(0,0,0,0);
			for(int i=0;i<ehsConstants.colorCodes.length;i++) {
				JCustomButton b = new JCustomButton("",ehsConstants.colors[i],new colorIcon(ehsConstants.colorCodes[i]));
				b.setMargin(m);
				canvas.buttonCanvasAdd(b);
			}
			canvas.buttonCanvasCreate();
		}
		public void dialogClose() {colorButtonCanvasHide();}
		public void dialogMoved() {colorButtonCanvasSave();} 
		public buttonCanvas colorButtonCanvas() {return canvas;}
		public void colorButtonCanvasShow() {
			canvas.buttonCanvasShow();
			//canvas.buttonCanvasPanel().loadPosition(systemUserReg.getAppSerialBase() + systemUserReg.getUserName() + "cbc");
			//canvas.buttonCanvasPanel().addMsgDialogListener(this);
		}
		public void colorButtonCanvasSave() {
			if (canvas.buttonCanvasPanel() != null) {
				//canvas.buttonCanvasPanel().savePosition(systemUserReg.getAppSerialBase() + systemUserReg.getUserName() + "cbc");
			}
		}
		public void colorButtonCanvasHide() {
			if (canvas.buttonCanvasPanel() != null) {
				canvas.buttonCanvasHide();
			}
		}
		protected void finalize() throws Throwable {
			colorButtonCanvasHide();
			super.finalize();
		}
	}
