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
//import javax.xml.ws.*;
//import javax.xml.ws.handler.*;
//import javax.xml.ws.handler.soap.*;
//import javax.xml.soap.*;
import javax.xml.namespace.QName;
//import javax.xml.ws.handler.Handler;
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
//import sun.audio.*;

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
//import javax.activation.URLDataSource.*;

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

import com.sendgrid.*;
import com.sendgrid.Method;
import com.sendgrid.Response;
import java.io.IOException;

public class supportFunctions extends Component {

	public static int maxBoundingBoxCordValue = 9999;

	public static mysqlJDBC dbConn = null;
	public static configurationSettings appConfigSetting = null;
	
	public static configurationSettings getAppConfigSettings() {return appConfigSetting;}
	public static void createAppConfigSettings(String name) {
		appConfigSetting = new configurationSettings();
		appConfigSetting.openConfigurationSettings(name);
	}
	public static mysqlJDBC getDBConn() {return dbConn;}
	public static void connectDatabase() {
		dbConn = new mysqlJDBC();
	}

	public static int[] lineIntersectsShapeAtCords(Shape shape,int x0,int y0,int x1,int y1) {
		int[] p = new int[2];
		int[] intersectCords = new int[4];
		int pointIndex = 0;

		float seg[] = new float[6];
		int segType = 0;
		float curX = 0,curY = 0;
		float lastMoveX = 0,lastMoveY = 0;

		intersectCords[0] = -maxBoundingBoxCordValue;intersectCords[1] = -maxBoundingBoxCordValue;
		intersectCords[2] = -maxBoundingBoxCordValue;intersectCords[3] = -maxBoundingBoxCordValue;

		PathIterator pi = shape.getPathIterator(null);
		while (!pi.isDone()) {
			segType = pi.currentSegment(seg);
			switch (segType) {
				case PathIterator.SEG_MOVETO:
					curX = seg[0];
					curY = seg[1];
					//System.out.println("(SEG_MOVETO) curX=" + String.valueOf(curX) + ",curY=" + String.valueOf(curY));
					lastMoveX = curX;
					lastMoveY = curY;
					break;
				case PathIterator.SEG_LINETO:
					p = lineIntersectionPoint2D((int)curX,(int)curY,(int)seg[0],(int)seg[1],x0,y0,x1,y1);
					if (p[0] != -maxBoundingBoxCordValue) {
						//System.out.println("(SEG_LINETO) pointIndex=" + String.valueOf(pointIndex) + ",intersectCordsX=" + String.valueOf(p[0]) + ",intersectCordsY=" + String.valueOf(p[1]));
						intersectCords[ (pointIndex * 2) ] = p[0];
						intersectCords[ (pointIndex * 2) + 1] = p[1];
						pointIndex++;
					}
					curX = seg[0];
					curY = seg[1];
					//System.out.println("(SEG_LINETO) curX=" + String.valueOf(curX) + ",curY=" + String.valueOf(curY));
					break;
				case PathIterator.SEG_CLOSE:
					if (curX != lastMoveX || curY != lastMoveY) {
						// only check if we are closing path to a different cord point						
						p = lineIntersectionPoint2D((int)curX,(int)curY,(int)lastMoveX,(int)lastMoveY,x0,y0,x1,y1);
						if (p[0] != -maxBoundingBoxCordValue) {
							//System.out.println("(SEG_CLOSE) pointIndex=" + String.valueOf(pointIndex) + ",intersectCordsX=" + String.valueOf(p[0]) + ",intersectCordsY=" + String.valueOf(p[1]));
							intersectCords[ (pointIndex * 2) ] = p[0];
							intersectCords[ (pointIndex * 2) + 1] = p[1];
							pointIndex++;
						}
					}
					curX = lastMoveX;
					curY = lastMoveY;
					//System.out.println("(SEG_CLOSE) curX=" + String.valueOf(curX) + ",curY=" + String.valueOf(curY));
					break;
				case PathIterator.SEG_QUADTO:
					p = lineIntersectionPoint2D((int)curX,(int)curY,(int)seg[2],(int)seg[3],x0,y0,x1,y1);
					if (p[0] != -maxBoundingBoxCordValue) {
						//System.out.println("(SEG_QUADTO) pointIndex=" + String.valueOf(pointIndex) + ",intersectCordsX=" + String.valueOf(p[0]) + ",intersectCordsY=" + String.valueOf(p[1]));
						intersectCords[ (pointIndex * 2) ] = p[0];
						intersectCords[ (pointIndex * 2) + 1] = p[1];
						pointIndex++;
					}
					curX = seg[2];
					curY = seg[3];
					//System.out.println("(SEG_QUADTO) curX=" + String.valueOf(curX) + ",curY=" + String.valueOf(curY));
					break;
				case PathIterator.SEG_CUBICTO:
					p = lineIntersectionPoint2D((int)curX,(int)curY,(int)seg[4],(int)seg[5],x0,y0,x1,y1);
					if (p[0] != -maxBoundingBoxCordValue) {
						//System.out.println("(SEG_CUBICTO) pointIndex=" + String.valueOf(pointIndex) + ",intersectCordsX=" + String.valueOf(p[0]) + ",intersectCordsY=" + String.valueOf(p[1]));
						intersectCords[ (pointIndex * 2) ] = p[0];
						intersectCords[ (pointIndex * 2) + 1] = p[1];
						pointIndex++;
					}
					curX = seg[4];
					curY = seg[5];
					//System.out.println("(SEG_CUBICTO) curX=" + String.valueOf(curX) + ",curY=" + String.valueOf(curY));
					break;
			}

			pi.next();
		}

		return intersectCords;
	}
	// the next 3 functions should be replaced with the graphic2D functions getBoundingRect, contains and intersect
	public static int[] boundingBox2D(int[] p,int[] bb) {
		for (int i=0;i<2;i++) {
			for (int j=0;j<p.length;j=j+2) {
				int index = i + j;
				//System.out.println("p[index]=" + String.valueOf(p[index]));
				if (p[index] < bb[i]) {bb[i] = p[index];}
				if (p[index] > bb[i + 2]) {bb[i + 2] = p[index];}
			}
		}

		return bb;
	}
	public static int[] boundingBoxOfPoints(int[] points) {
		int bb[] = {maxBoundingBoxCordValue,maxBoundingBoxCordValue,-maxBoundingBoxCordValue,-maxBoundingBoxCordValue};

		for(int i=0;i<points.length;i=i+2) {
			int p[] = new int[2];
			p[0] = points[i];
			p[1] = points[i + 1];
			bb = boundingBox2D(p,bb);
		}

		return bb;
	}
	public static boolean boundingBoxIntersect2D(int[] boxa,int[] boxb) {
		for (int i_min=0;i_min<2;i_min++) {
			int i_max = i_min + 2; //index for the maximum
			if (boxa[i_max] < boxb[i_min]) {return false;}
			if (boxb[i_max] < boxa[i_min]) {return false;}
		}

		return true;
	}
	public static int[] lineIntersectionPoint2D(int x0,int y0,int x1,int y1,int x2,int y2,int x3,int y3) {
		int[] ip = new int[2];
		int[] p = new int[4];

		// we can have overlapping bounding boxes but not lines intersecting !!!
		//int bb[] = {maxBoundingBoxCordValue,maxBoundingBoxCordValue,-maxBoundingBoxCordValue,-maxBoundingBoxCordValue};
		//p[0] = x0;p[1] = y0;p[2] = x1;p[3] = y1;
		//int[] boxa = boundingBox2D(p,bb);
		//int bbb[] = {maxBoundingBoxCordValue,maxBoundingBoxCordValue,-maxBoundingBoxCordValue,-maxBoundingBoxCordValue};
		//p[0] = x2;p[1] = y2;p[2] = x3;p[3] = y3;
		//int[] boxb = boundingBox2D(p,bbb);

		//(!boundingBoxIntersect2D(boxa,boxb)) {// bounding boxes do not intersect
		if(!Line2D.linesIntersect(x0,y0,x1,y1,x2,y2,x3,y3)) {
			//System.out.println("bounding boxes do not intersect");
			ip[0] = -maxBoundingBoxCordValue;
			ip[1] = -maxBoundingBoxCordValue;
			return ip;
		}

		float dy10 = y1 - y0;
		float dx10 = x1 - x0;
		float dy32 = y3 - y2;
		float dx32 = x3 - x2;
		float dyx10 = 0; // the line slope
		float dyx32 = 0; // the line slope

		if (dx10 != 0) {dyx10 = dy10 / dx10;}
		if (dx32 != 0) {dyx32 = dy32 / dx32;}

		float x,y;
		if (dy10 == 0 && dy32 == 0) {// parallel vertical
			//System.out.println("parallel vertical");
			ip[0] = -maxBoundingBoxCordValue;
			ip[1] = -maxBoundingBoxCordValue;
			return ip;
		} 
		else if (dy10 == 0 && dy32 != 0) {// first line horz
			//System.out.println("first line horz");
			y = y0;
			x = x2 + (y - y2) * (dx32 / dy32);
		}
		else if (dy10 != 0 && dy32 == 0) {// second line horz
			//System.out.println("second line horz");
			y = y2;
			x = x0 + (y - y0) * (dx10/dy10);
		}
		else if (dx10 == 0 && dx32 != 0) {// first line vert
			//System.out.println("first line vert");
			x = x0;
			y = y2 + dyx32 * (x - x2);
		}
		else if (dx10 != 0 && dx32 == 0) {// second line vert
			//System.out.println("second line vert");
			x = x2;
			y = y0 + dyx10 * (x - x0);
		}
		else if (Math.abs(dyx10 - dyx32) == 0) {// parallel or parallel collinear
			//System.out.println("parallel or parallel collinear");
			ip[0] = -maxBoundingBoxCordValue;
			ip[1] = -maxBoundingBoxCordValue;
			return ip; 
		}
		else {// we have a line intersection
			//System.out.println("we have a line intersection");
			x = (y2 - y0 + dyx10 * x0 - dyx32 * x2) / (dyx10 - dyx32);
			y = y0 + dyx10 * (x - x0);
		}

		ip[0] = (int)x;
		ip[1] = (int)y;
		return ip;
	}
	  public static Point centerPoint(Rectangle r) {
		float cx = (float)r.getWidth() / (float)2.00;
		float cy = (float)r.getHeight() / (float)2.00;
		cx = cx + (float)r.getX();
		cy = cy + (float)r.getY();
		return new Point((int)cx,(int)cy);
	  }
	public static void deleteFilename(String filename) {
		basicFile tmp = new basicFile(filename);
		tmp.deleteFile();
	}
	public static String getDirectory(String tmp) {
		   int index = tmp.lastIndexOf("/");
		   if (index == -1) {index = 0;}
		   String path = tmp.substring(0,index);
		   if (path.charAt(1) == ':') {
			   	  path = path.substring(2); // remove any drive specifier 
		   }
		   
		   return path;
	}
		public static String getRemoteHostName(String ipaddr) {
			String hostname = "";
			try {
				hostname = InetAddress.getByName(ipaddr).getHostName();
			}
			catch (Exception e) {return "";}
			return hostname;
		}
		
		public static Vector removeNumberTokens(Vector v) {
			Vector n = new Vector();
			for (int i=0;i<v.size();i++) {
				try {
					int num = Integer.parseInt((String)v.elementAt(i));
				} catch (Exception e) {
					n.addElement((String)v.elementAt(i));
				}
			}
			
			return n;
		}
	public static int ehsHashCode(String s) {
		int hc = 0;
		for (int i=0;i<s.length();i++) {
			char ch = s.charAt(i);
			hc = hc + ch;
		}
		return hc;
	}
	public static String getMachineUniqueIDInternal(String directory) {
		if (ehsConstants.bRemoteHosted) {return "1";}
		
		pseduoFile f = new pseduoFile(directory + "/.licence");
		String  s = f.loadFile();
		if (s.length() != 0) {return s.trim();}
		
		int tmp = supportFunctions.getSystemVar("muniquecount",0);
		supportFunctions.setSystemVar("muniquecount",tmp+1);

		pseduoFile f1 = new pseduoFile(directory + "/.licence");
		f1.saveFile(String.valueOf(tmp));
		f1.flush();
		
		return String.valueOf(tmp);
	}
	public static String getMachineUniqueString(String directory) {
		return getMachineUniqueIDInternal(directory);
	}
	public static int getMachineUniqueID(String directory) {
		try {
			return Integer.parseInt(getMachineUniqueIDInternal(directory));
		} catch (Exception e) {return 0;}
	}
	public static int getSystemVar(String name, int defValue) {
		String data = "";
		data=supportFunctions.getDBConn().executeSQLQuery("SELECT varValue FROM sysehsvariables WHERE varName='" + name + "'",String.valueOf(defValue));
		
		//TRACE("data (getSystemVar) is " + data + " length="+String.valueOf(data.length()),3);
		if(data.length() == 0) {
			setSystemVar(name,defValue);
			return defValue;
		}

		int val = 0;
		try {
		   val = Integer.parseInt(data);
		} catch(Exception e) {e.printStackTrace();}
		return val;
	}

	public static boolean getSystemVar(String name, boolean defValue) {
		String data="";
		data = supportFunctions.getDBConn().executeSQLQuery("SELECT varValue FROM sysehsvariables WHERE varName='" + name + "'",supportFunctions.valueOf(defValue));
		
		if(data.length() == 0) {
			setSystemVar(name,defValue);
			return defValue;
		}

 	    boolean ret = false;
		try {
		   if(Integer.parseInt(data) == 1) {ret = true;}
		} catch (Exception e) {e.printStackTrace();}
		
		return ret;
	}

	public static String getSystemVar(String name, String defValue) {
		String data = "";
		data=supportFunctions.getDBConn().executeSQLQuery("SELECT varValue FROM sysehsvariables WHERE varName='" + name + "'",defValue);
		
		if(data.length() == 0) {
			setSystemVar(name,defValue);
			return defValue;
		}

		return data.trim();
	}

	public static String setSystemVar(String name, String val) {
		supportFunctions.getDBConn().executeSQLQuery("REPLACE INTO sysehsvariables (varName,varValue) VALUES ('"+name+"','"+val+"')","");
		return val;
	}

	public static int setSystemVar(String name, int val) {
		supportFunctions.getDBConn().executeSQLQuery("REPLACE INTO sysehsvariables (varName,varValue) VALUES ('"+name+"','"+String.valueOf(val)+"')","");
		return val;
	}
	
	public static boolean setSystemVar(String name, boolean val) {
		supportFunctions.getDBConn().executeSQLQuery("REPLACE INTO sysehsvariables (varName,varValue) VALUES ('"+name+"','"+supportFunctions.valueOf(val)+"')","");
		return val;
	}

	public static String displayLogonDialog() {
		logonDialog d = new logonDialog(null);
		return d.getUserName() + "," + d.getPassword();
	}

	public static MenuItem getMenuItem(Menu m,String label) {
		for (int i=0;i<m.getItemCount();i++) {
			if (label.equalsIgnoreCase(m.getItem(i).getLabel())) {return m.getItem(i);}
		}
		return (MenuItem)null;
	}
	
/*	
 public static String readFromJARFile(String filename)
 			throws IOException
			{
			  InputStream is = getClass().getResourceAsStream(filename);
			  InputStreamReader isr = new InputStreamReader(is);
			  BufferedReader br = new BufferedReader(isr);
			  StringBuffer sb = new StringBuffer();
			  String line;
			  while ((line = br.readLine()) != null) 
			  {
			    sb.append(line);
			  }
			  br.close();
			  isr.close();
			  is.close();
			  return sb.toString();
			}
*/

	public static String stringPart(String token) {
		if (token.length() == 0) {return (String)null;}
		
		int index = 0;
		for (int i=0;i<token.length();i++) {
			if (!Character.isDigit(token.charAt(i))) {break;}
			index++;
		}
		if (index == token.length()-1) {return null;}
		return token.substring(index);
	}

	public static int numberPart(String token) {
		if (token.length() == 0) {return 0;}

		int index = 0;
		for (int i=0;i<token.length();i++) {
			if (!Character.isDigit(token.charAt(i))) {break;}
			index++;
		}
		if (index == token.length()-1) {return 0;}
		return Integer.parseInt(token.substring(0,index));
	}
	
	public static String selectCurrentWord(JTextField tf) {
		   Vector v = getWordStartEndPos(tf.getText(),tf.getCaretPosition());
		   tf.select(Integer.parseInt((String)v.elementAt(0)),Integer.parseInt((String)v.elementAt(1))+1);
		   return tf.getSelectedText();
	}
	
	public static void replaceCurrentWord(JTextField tf,String word) {
		   String oldText = tf.getText();
		   Vector v = getWordStartEndPos(tf.getText(),tf.getCaretPosition());
		   String newText = oldText.substring(0,Integer.parseInt((String)v.elementAt(0))) + word + oldText.substring(Integer.parseInt((String)v.elementAt(1)),oldText.length());
		   tf.setText(newText);		   
	}	
	
	public static Vector getWordStartEndPos(String s,int spos) {
	   Vector v = new Vector();
	   if (spos<0 || spos>s.length()) {return v;}
	   StringBuffer b = new StringBuffer(s);
	   int index = 0;
	   for (int i=spos;i>-1;i--) {
	      if (b.charAt(i) == ' ') {index=i+1;break;}
	   }
	   v.addElement(String.valueOf(index));
	   index = s.length()-1;
	   for(int i=spos+1;i<s.length();i++) {
	      if (b.charAt(i) == ' ') {index=i-1;break;}
	   }	   
	   v.addElement(String.valueOf(index));
	   return v;
	}
	public static String listSupportedTargetTypes()
	{
		String	strMessage = "";
		AudioFileFormat.Type[]	aTypes = AudioSystem.getAudioFileTypes();
		for (int i = 0; i < aTypes.length; i++)
		{
			if (i>0) {strMessage += ",";}
			strMessage += aTypes[i].getExtension();
		}
		return strMessage;
	}

	public static AudioFileFormat.Type findTargetType(String strExtension)
	{
		AudioFileFormat.Type[]	aTypes = AudioSystem.getAudioFileTypes();
		for (int i = 0; i < aTypes.length; i++)
		{
			if (aTypes[i].getExtension().equals(strExtension))
			{
				return aTypes[i];
			}
		}
		return null;
	}

	public static boolean isPcm(AudioFormat.Encoding encoding)
	{
		return encoding.equals(AudioFormat.Encoding.PCM_SIGNED)
			|| encoding.equals(AudioFormat.Encoding.PCM_UNSIGNED);
	}

	public static Mixer.Info getMixerInfo(String strMixerName)
	{
		Mixer.Info[]	aInfos = AudioSystem.getMixerInfo();
		for (int i = 0; i < aInfos.length; i++)
		{
			if (aInfos[i].getName().equals(strMixerName))
			{
				return aInfos[i];
			}
		}
		return null;
	}

	public static SourceDataLine getSourceDataLine(String strMixerName,AudioFormat audioFormat,int nBufferSize) {
		   SourceDataLine line = null;
		   DataLine.Info  info = new DataLine.Info(SourceDataLine.class,audioFormat,nBufferSize);
		   try {
		   	   if (strMixerName != null) {
			   	  Mixer.Info mixerInfo = getMixerInfo(strMixerName);
				  if (mixerInfo == null) {
				  	 return null;
				  }
				  Mixer mixer = AudioSystem.getMixer(mixerInfo);
				  line = (SourceDataLine)mixer.getLine(info);
			   } else {
			   	 line = (SourceDataLine)AudioSystem.getLine(info);
			   }
			   line.open(audioFormat,nBufferSize);
		   }
		   catch (LineUnavailableException e) {e.printStackTrace();}
		   catch (Exception e) {e.printStackTrace();}
		   return line;
	}
	
	public static TargetDataLine getTargetDataLine(String strMixerName,
							AudioFormat audioFormat,
							int nBufferSize)
	{
		TargetDataLine	targetDataLine = null;
		DataLine.Info	info = new DataLine.Info(TargetDataLine.class,
							 audioFormat, nBufferSize);
		try
		{
			if (strMixerName != null)
			{
				Mixer.Info	mixerInfo = getMixerInfo(strMixerName);
				if (mixerInfo == null)
				{
					return null;
				}
				Mixer	mixer = AudioSystem.getMixer(mixerInfo);
				targetDataLine = (TargetDataLine) mixer.getLine(info);
			}
			else
			{
				targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
			}

			targetDataLine.open(audioFormat, nBufferSize);
		}
		catch (LineUnavailableException e) {e.printStackTrace();}
		catch (Exception e) { e.printStackTrace(); }
		return targetDataLine;
	}
	
	public static void playSoundToEnd(String soundFile) {
		   int nInternalBufferSize = AudioSystem.NOT_SPECIFIED;
		   try {
		   	   URL u = new URL(soundFile);
		   	   AudioInputStream ais = AudioSystem.getAudioInputStream(u);
		   	   AudioFormat af = ais.getFormat();
		   	   DataLine.Info info = new DataLine.Info(SourceDataLine.class,af,nInternalBufferSize);
		  	   boolean isSupported = AudioSystem.isLineSupported(info);
		   	   if (!isSupported) {
		   	  	   int nSampleSizeInBits = 16;
			  	   boolean bBigEndian = false;
		   	  	   AudioFormat source = af;
			  	   AudioFormat	target = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,source.getSampleRate(),
					nSampleSizeInBits,source.getChannels(),source.getChannels() * (nSampleSizeInBits / 8),source.getSampleRate(),bBigEndian);
			  	   ais = AudioSystem.getAudioInputStream(target, ais);
			       af = ais.getFormat();
		   	   }	  
		  	   SourceDataLine line = getSourceDataLine(null,af,AudioSystem.NOT_SPECIFIED);
		   	   if (line == null) {return;}
		   	   line.start();
		   	   int nBytesRead = 0;
		   	   byte[] abData = new byte[128000];
		   	   while (nBytesRead != -1) {
				 nBytesRead = ais.read(abData,0,abData.length);
		   		 if (nBytesRead >=0) {
				 	int nBytesWritten = line.write(abData,0,nBytesRead);
				 }
			   }
		   	   line.drain();
		   	   line.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	public static void boxCenterText(Graphics g,Color back,String s1,String s2,int x,int y,int w,int h) {
		boxCenterText(g,back,s1,s2,x,y,w,h,true);
	}
	public static void boxCenterText(Graphics g,Color back,String s1,String s2,int x,int y,int w,int h,boolean bRect) {
		Font orgFont = g.getFont();
		int height,ascent;
		int width1=0,width2=0,x0=0,x1=0,y0=0,y1=0;
		float size = g.getFont().getSize();
		size = size + 1;
		do { // reduce the font size until the text fits in the bounding box
			Font newFont = g.getFont().deriveFont(size - 1);
			g.setFont(newFont);
			FontMetrics fm1 = g.getFontMetrics(newFont);
			width1 = fm1.stringWidth(s1);
			if (s2.length() != 0) width2 = fm1.stringWidth(s2);
			height = fm1.getHeight();
			ascent = fm1.getAscent();
			size = size - 1;
		}
		while(((width1 > w) || (width2  > w)) && size > 0);
		x0 = x + (w - width1) / 2;
		x1 = x + (w - width2) / 2;
		if (s2.length() == 0) {
			y0 = y + (h - height) / 2 + ascent;
		} else {
			y0 = y + (h - (int)(height * 2.2)) / 2 + ascent;
			y1 = y0 + (int)(height * 1.2);
		}
		g.setColor(Color.black);
		if (bRect) {
			g.setColor(back);
			g.fillRoundRect(x,y,w,h,20,20);
			g.setColor(Color.black);
			g.drawRoundRect(x,y,w,h,20,20);
		}
		g.drawString(s1,x0,y0);
		if (s2.length() != 0) {
			g.drawString(s2,x1,y1);
		}
		g.setFont(orgFont);
	}
	
	public static void boxCenterText(Graphics g,Color back,String s1,int x,int y,int w,int h) {
		boxCenterText(g,back,s1,"",x,y,w,h);
	}
	public static void boxCenterText(Graphics g,Color back,String s1,int x,int y,int w,int h,boolean bRect) {
		boxCenterText(g,back,s1,"",x,y,w,h,bRect);
	}
	public static void centerTextAtPoint(Graphics2D g,String text,int x,int y) {
		FontMetrics fm = g.getFontMetrics();
		int width = fm.stringWidth(text);
		g.drawString(text,x - (width / 2),y);
	}
	public static void centerTextAtBox(Graphics2D g,String text,int x,int y,int w,int h) {
		centerTextAtPoint(g,text,x + (w / 2),y + (h / 2));
	}
	public static void centerTextAtBox(Graphics2D g,String text,Rectangle r) {
		centerTextAtBox(g,text,(int)r.getX(),(int)r.getY(),(int)r.getWidth(),(int)r.getHeight());
	}
	public static Font getRotatedFont(Font f,int degrees) {
		AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(degrees));
		return f.deriveFont(rotation);
	}
	public static void drawRotatedText(Graphics2D g,String text,int x,int y,int degrees) {
		Font orgFont = g.getFont();
		Font rotFont = getRotatedFont(orgFont,degrees);
		if (degrees > 90 && degrees < 270) {
			rotFont = rotFont.deriveFont(getFlipXTransform());
		}
		g.setFont(rotFont);
		g.drawString(text,x,y);
		g.setFont(orgFont);
	}
	public static int getQuad(Point ptSrc,Point ptDest) {
		// return quad number of ptDest relative to ptSrc
		// Angles are measured positive from the horizontal zero baseline
		// Q0: 0-90 , Q1: 91-180 , Q2: 181-270 , Q3: 271 - 360
		if (ptDest.x < ptSrc.x && ptDest.y > ptSrc.y) {return 2;}
		if (ptDest.x > ptSrc.x && ptDest.y > ptSrc.y) {return 0;}
		if (ptDest.x < ptSrc.x && ptDest.y > ptSrc.y) {return 1;}

		return 3;
	}
	public static AffineTransform getFlipXTransform() {
		return AffineTransform.getScaleInstance(1,-1);
	}
	public static AffineTransform getFlipYTransform() {
		return AffineTransform.getScaleInstance(-1,1);
	}
	public static propBoxDialog displayPropBoxDialog(String msg,Vector props,String id) {
		propBoxDialog d = new propBoxDialog(null,msg,props,id,null); 
		return d;
	}
	public static propBoxDialog displayPropBoxDialog(String msg,Vector props) {
		propBoxDialog d = new propBoxDialog(null,msg,props,"",null);
		return d;
	}
	public static String[] getDataAsDialog(String title,String[] fields,String[] defaults) {
		return getDataAsDialog(title,fields,defaults,null);
	}	
	public static String[] getDataAsDialog(String title,String[] fields,String[] defaults,propBoxDialogListener pbdl) {
		Vector props = new Vector();
		int index = 0;
		
		statusCanvasProp csp;
		csp = new statusCanvasProp(" ",""); // simulates a blank line
		props.addElement(csp);

		for(int i=0;i<fields.length;i++) {
			if (defaults[i].indexOf(",") != -1) {
				//TRACE("getDataAsDialog:"+fields[i]+":"+defaults[i],4);
				Vector v = supportFunctions.splitIntoTokens(defaults[i],",");
				String[] s = new String[v.size()];
				int sindex = 0;
				for (int ii=0;ii<v.size();ii++) {
					String tmp = (String)v.elementAt(ii);
					if (tmp.startsWith("##")) {
						try {
							index = Integer.parseInt(tmp.substring(2));
						} catch (Exception e) {}
					} else {
						s[sindex++] = tmp;						
					}
				}
				if (sindex != v.size()) { // #bug 63 09/08/17
					supportFunctions.displayMessageDialog(null,"compacting array");
					String[] stmp = new String[v.size()-1]; // remove last element
					System.arraycopy(s, 0, stmp, 0, s.length-1);
					s = stmp;
				}
				if (index >= s.length) {index = 0;}
				csp = new statusCanvasProp(fields[i],s[index],s);				
			} else {
				csp = new statusCanvasProp(fields[i],defaults[i]);				
			}
			props.addElement(csp);
		}
		propBoxDialog d = new propBoxDialog(null,title,props,"",pbdl);

		if(d.isOK()) {
			String[] data = new String[props.size()-1]; // ignore 1st property
			for(int i=1;i<props.size();i++) {
				statusCanvasProp prop = (statusCanvasProp)props.elementAt(i);
				data[i-1] = prop.getValue();
			}
			return data;
		}

		return null;
	}

	public static String getPropValue(String title,String propName,String propDefault) {
		Vector props = new Vector();
		props.addElement(new statusCanvasProp(propName,propDefault));
		propBoxDialog d = displayPropBoxDialog(title,props);
		return d.getPropByName(propName);
	}
	public static int getPropValue(String title,String propName,int propDefault) {
		Vector props = new Vector();
		props.addElement(new statusCanvasProp(propName,String.valueOf(propDefault)));
		propBoxDialog d = displayPropBoxDialog(title,props);
		return Integer.parseInt(d.getPropByName(propName));
	}
	public static String eval(String formula) { 
		Object result = null;
		
		try {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");   
			result = engine.eval(formula);
		} catch (Exception e) {;}
        return String.valueOf(result);			
	}
	public static String base64Encode(Serializable obj) {
		try {
			StringBuffer sBuf = new StringBuffer();
			Base64OutputStream bOut = new Base64OutputStream(sBuf);
			ObjectOutputStream out = new ObjectOutputStream(bOut);
			out.writeObject(obj);
			out.flush();
			return sBuf.toString();
		} catch (Exception e) {return "";}
	}
	public static Serializable base64Decode(String s,int start) {
		try {
			Base64InputStream bIn = new Base64InputStream(s,start);
			ObjectInputStream in = new ObjectInputStream(bIn);
			Object obj = in.readObject();
			return (Serializable)obj;
		} catch (Exception e) {return null;}
	}
	public static String currentDate() {
		DateFormat f = DateFormat.getDateInstance(DateFormat.LONG);
		return f.format(new Date());
	  }	

	  public static String currentTime() {
		DateFormat f = DateFormat.getTimeInstance(DateFormat.LONG);
		return f.format(new Date());
	  }	

	  public static String currentShortDate() {
		DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
		return f.format(new Date());
	}	

	public static String currentShortTime() {
		DateFormat f = DateFormat.getTimeInstance(DateFormat.SHORT);
		return f.format(new Date());
	}	

	public static int daysTillExpired(Date expDate) {
		Date currentDate = new Date();
		long expTime = expDate.getTime();
		long currTime = currentDate.getTime();
		long diff = expTime - currTime;
		return (int)diff/(1000*60*50*24);
	}
	public static String stringRepeat(String s,int times) {
		String ret = "";
		if (times > 0) {
			for (int i=0;i<times;i++) {
				ret = ret + s;
			}
		}
		
		return ret;
	}
	public static void setNativeLookAndFeel() {
		   try {
		   	   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		   } catch(Exception e) {}
	}
	public static Color getColorCode(String colorName) {
		   for (int i=0;i<ehsConstants.colors.length;i++) {
		   	   if (colorName.equalsIgnoreCase(ehsConstants.colors[i])) {
			   	  return ehsConstants.colorCodes[i];
			   }
		   }
		   return (Color)null;
	  }
	  public static String getColorName(Color c) {
		  for (int i=0;i<ehsConstants.colorCodes.length;i++) {
			  if (c == ehsConstants.colorCodes[i]) {return ehsConstants.colors[i];}
		  }
		  return "";
	  }
	public static boolean rectContainsRect(Rectangle r1,Rectangle r2) {
		if (!r1.contains(new Point(r2.x,r2.y))) {return false;}
		if (!r1.contains(new Point(r2.x + r2.width,r2.y))) {return false;}
		if (!r1.contains(new Point(r2.x + r2.width,r2.y + r2.height))) {return false;}
		if (!r1.contains(new Point(r2.x,r2.y + r2.height))) {return false;}
	
		return true;
	}
	public static void mail(String addr, String subject, String msg) {
			Email from = new Email("endhousesoftware999@gmail.com");
		    Email to = new Email(addr);
		    Content content = new Content("text/plain", msg);
		    Mail mail = new Mail(from, subject, to, content);

		    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		    Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		    } catch (IOException ex) {
		      return;
		    }		
	}
	
	public static void setJavaLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {;}
	}
	public static void setMotifLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (Exception e) {;}
	}

	public static int getMilliSeconds(int time,timeUnits timeUnit) {
		int ret = time;
		switch (timeUnit) {
		case SECS:
			ret = ret * 1000;
			break;
		case MICROSECS:
			ret = ret / 1000;
			break;
		case NANOSECS:
			ret = ret / 1000000;
			break;
		}
		return ret;
	}

	public static String[] regMatchGroups(String line,String pat) {
		ehsRegExp p = new ehsRegExp();
		p.regExpMatch(line,pat);
		return p.getFoundGroupsArray();
	}
	public static int indexOf(String[] values,String value) {
		for (int i=0;i<values.length;i++) {
			if (value.equals(values[i])) {return i;}
		}
		
		return -1;
	}
	public static void setAttributeWithXPath(org.w3c.dom.Document xmlDocument,String xPathExpr,String attrib,String value) {
		NodeList n = executeXPathExpr(xmlDocument,xPathExpr);
		if (n.getLength() > 0) {
			org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(0);
			e.setAttribute(attrib,value);
			//getXmlDataFile().saveXMLDataFile();
		}
	}
	public static String getAttributeWithXPath(org.w3c.dom.Document xmlDocument,String xPathExpr,String attrib) {
		String data = "";
		
		NodeList n = executeXPathExpr(xmlDocument,xPathExpr);
		if (n.getLength() > 0) {
			org.w3c.dom.Element e = (org.w3c.dom.Element)n.item(0);
			data = e.getAttribute(attrib);
		}
		return data;
	}
	public static NodeList executeXPathExpr(org.w3c.dom.Document xmlDocument,String xPathExpr) {
		try {
			XPath xPath =  XPathFactory.newInstance().newXPath();
			return (NodeList) xPath.compile(xPathExpr).evaluate(xmlDocument, XPathConstants.NODESET);
		} catch (Exception e) {return (NodeList)null;}
	}
	public static String rightString(String s,int chars) {
		if (chars < 0) {chars = 0;}
		if (chars > s.length()) {chars = s.length();}
		return s.substring(s.length() - chars);
	}
	public static String leftString(String s,int chars) {
		if (chars < 0) {chars = 0;}
		if (chars > s.length()) {chars = s.length();}
		return s.substring(0,chars);
	}
	public static String midString(String s,int start,int end) {
		if (start < 0) {start = 0;}
		if (end < 0) {end = 0;}
		if (start > s.length()) {start = s.length();}
		if (end > s.length()) {end = s.length();}
		return s.substring(start,end);
	}
	public static String replace(String s,String search,String replace,boolean bAll) {
		int index = s.indexOf(search);
		if (index == -1) {return s;}
		String tmp = s.substring(0,index)+replace+s.substring(index+search.length());
		if (bAll == true) {
			return replace(tmp,search,replace,bAll);
		}
		return tmp;
	}
	public static String pad(String s,String pad,int finalLength,int padDir) {
		if (s.length() >= finalLength) {return s;}
		String finalStr = "";
		if (padDir == ehsConstants.strPadRight) {finalStr = s;}
		for (int i=0;i<finalLength-s.length();i=i+pad.length()) {
			finalStr = finalStr + pad;
		}
		if (padDir == ehsConstants.strPadLeft) {finalStr = finalStr + s;}
		return finalStr;
	}	
	public static int strCount(String s,char ch) {
		int count = 0;
		StringBuffer b = new StringBuffer(s);
		for (int i=0;i<b.length();i++) {
			if (ch == b.charAt(i)) {count++;}
		}
		return count;
	}
	public static int rand(int max) {return rand(0,max);}
	public static int rand(int min, int max) {
		return (int)(Math.round(max * Math.random())) + min;
	}
	public static boolean valueOf(String s) {
		if(s.equals("1")) {return true;}
		return false;
	}
	public static String onOff(boolean b) {
		if(b) {return "On";} else {return "Off";}
	}
	public static String valueOf(boolean b) {
		if(b) {return "1";} else {return "0";}
	}
	public static String decbin(int num) {
		return Integer.toBinaryString(num);
	}
	public static String decoct(int num) {
		return Integer.toOctalString(num);
	}
	public static String dechex(int num) {
		return Integer.toHexString(num);
	}		
	public static String reverseStringByWord(String s) {
		Stack stack = new Stack();
		StringTokenizer strTok = new StringTokenizer(s);
		while(strTok.hasMoreTokens()) {
			stack.push(strTok.nextElement());
		}
		StringBuffer revStr = new StringBuffer();
		while(!stack.isEmpty()) {
			revStr.append(stack.pop());
			revStr.append(" ");
		}
		return revStr.toString().trim();
	}	
	public static String reasembleTokens(Vector v,int start,int end,String seps) {
		String result = "";
		for(int i=0;i<v.size();i++) {
			if (i>=start && i<=end) {
				if(i != start) {result = result + seps;}
				result = result + (String)v.elementAt(i);
			}
		}
		return result;
	}
	public static String reasembleTokens(Vector v,int start) {
		return reasembleTokens(v,start,v.size()-1,",");
	}
	public static String reasembleTokens(Vector v,String seps) {
		return reasembleTokens(v,0,v.size()-1,seps);	
	}
	public static String reasembleTokens(Vector v) {
		return reasembleTokens(v,0,v.size()-1,",");
	}

	public static String removeDupTokens(String data) {
		   return removeDupTokens(data,",");
	}
	
	public static String removeDupTokens(String data,String seps) {
		Vector tmp = new Vector();
		Vector v = supportFunctions.splitIntoTokens(data,seps);

		String result = "";
		if(v.size() == 0) {return result;}
		result = (String)v.elementAt(0);
		for(int i=1;i<v.size();i++) {
			if(!tmp.contains((String)v.elementAt(i))) {
				tmp.addElement((String)v.elementAt(i));
				result = result + seps + (String)v.elementAt(i);
			}
		}

		return result;
	}
	
	public static boolean urlExists(String u) {
		try {
			FileInputStream fis = new FileInputStream(u);
			fis.close();
			
			//URL testURL = new URL(null,u);
			//InputStream in = testURL.openStream();
			//in.close();
		} catch (Exception e) {return false;}
		return true;
	}
	public static String getPath(String pathfilename) {
		String tmp = "";
		int index = pathfilename.lastIndexOf("/");
		if (index == -1) {index = pathfilename.lastIndexOf("\\");}
		if (index != -1) {
			tmp = pathfilename.substring(0,index);
		} else {
			tmp = ".";
		}
		return tmp;
	}
	public static String getFilename(String pathFilename) {
		   String tmp ="";
		   int index = pathFilename.lastIndexOf("/");
		   if (index == -1) {index = pathFilename.lastIndexOf("\\");}
		   if (index == -1) {
		      tmp = pathFilename;
		   } else {
		      tmp = pathFilename.substring(index+1,pathFilename.length());
		   }
		   return tmp;
		}
		public static String getTmpFilename() {
			return getTmpFilename("tmp");
		}
		public static String getTmpFilename(String prefix) {
			Date d = new java.util.Date();
			SimpleDateFormat f = new SimpleDateFormat("ddMMyyyyHHmmss");
			String tmp = f.format(d);
			tmp = prefix + tmp;
			return tmp;
		}
		public static String getFilenameExt(String tmp) {
		   tmp = getFilename(tmp);
		   int index = tmp.lastIndexOf(".");
		   if (index != -1) {
		      tmp = tmp.substring(index+1);
			  return tmp;
		   }
		   return "";
	}
	public static String getPathFilenameNoExt(String tmp) {
		   int index = tmp.lastIndexOf(".");
		   if (index != -1) {
		      tmp = tmp.substring(0,index);
		   }
		   return tmp;
		}
	public static String getFilenameNoExt(String tmp) {
		   tmp = getFilename(tmp);
		   int index = tmp.lastIndexOf(".");
		   if (index != -1) {
		      tmp = tmp.substring(0,index);
		   }
		   return tmp;
		}
	
	public static String executePostURL(String u,String[] params,String testData) {
//		if(systemUserReg.getAppRemotedHosted() || u.contains("files.php")) {
			String data1 = "";
			try {
				URL dataURL = new URL(null,u);
				URLConnection conn = dataURL.openConnection();
				conn.setUseCaches(false);
				conn.setDoOutput(true);
				ByteArrayOutputStream byteStream = new ByteArrayOutputStream(512);
				PrintWriter out = new PrintWriter(byteStream,true);
				String data = params[0]+"="+URLEncoder.encode(params[1],"UTF-8");
				for (int i=2;i<params.length;i=i+2) {
					data = data + "&"+params[i]+"="+URLEncoder.encode(params[i+1],"UTF-8");
				}
				out.print(data);
				out.flush();
				conn.setRequestProperty("Content-Length",String.valueOf(byteStream.size()));
				conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
				byteStream.writeTo(conn.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				boolean output = false;
				while ((line = in.readLine()) != null) {
					//TRACE("(POST) ePHPS line="+line,3);
					if(line.startsWith("+++")) {
						output = !output;
					}
					else {
						if(output) {
							data1 = data1 + line + "\n";
						}
					}
				}
			} catch (Exception e) {e.printStackTrace();}
			return data1.trim();
//		}
		
//		return testData;
	}
	public static String executePHPScript(String u,String[] params,String testData) {
		return executeGetURL(u+".php",params,testData);
	}
	public static String executeGetURL(String name,String[] params,String testData) {
		String paramData = "";
		String paramSep = "";
		String result = "";
		if(params.length % 2 != 0) {return "";}
		for(int i=0;i<params.length;i=i+2) {
			if(i>1) {paramSep = "&";}
			try {paramData = paramData + paramSep + params[i] + "=" + URLEncoder.encode(params[i+1],"UTF-8");} catch(UnsupportedEncodingException e) {e.printStackTrace();}
		}
//		TRACE("executePHPScript - " + name,3);
//		if(systemUserReg.getAppRemotedHosted() || name.contains("files.php") || name.contains("machineid.php")) {
			try {
				URL url = new URL(null,name +"?" + paramData);
//					synchronized(this) { 
					URLConnection conn = url.openConnection();
					conn.setUseCaches(false);
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
					String line;
					boolean output = false;
					while ((line = in.readLine()) != null) {
//						TRACE("(GET) ePHPS line="+line,3);
						if(line.startsWith("+++")) {
							output = !output;
						}
						else {
							if(output) {
								result = result + line + "\n";
							}
						}
					}
					in.close();
//				}
			} catch(MalformedURLException e) {e.printStackTrace();}
			catch(IOException e) {e.printStackTrace();}
//			TRACE("ExecutePHPScript result="+result,3);
//		} else {
//			result = testData;
//		}

		return result.trim();
	}
	
	public static void displayTextFile(String filename, JEditorPane ta) {		
		try {
			URL url = new URL(null,filename);
			ta.setPage(url);
		} catch (IOException e) {}
	}
	public static void displayTextFile(String filename, JTextArea ta) {	
		try {
			String line;
			URL url = new URL(null,filename);
			URLConnection conn = url.openConnection();
			conn.setUseCaches(false);
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = in.readLine()) != null) {
				ta.append(line);
				ta.append("\n");
			}
			in.close();
		} catch (IOException e) {e.printStackTrace();}
	}		
	public static String readTextFile(String filename) {return readTextFile(filename,false);}
	public static String readTextFile(String filename,boolean skip) {
//		if (systemUserReg != null) {
//			if (!systemUserReg.getAppRemotedHosted() && !skip) 
//				{TRACE("Read file (local) " + filename);return "";}
//		}
		//TRACE("Read file (remote) " + filename,4);
		String text = "";
		try {
			String line;
				FileInputStream fis = new FileInputStream(filename);
				BufferedReader bin = new BufferedReader(new InputStreamReader(fis));
		    	while ((line = bin.readLine()) != null) {
					if (text.length() != 0) {text = text + "\n";}
					text = text + line;
				 }
				 bin.close();
			 	 fis.close();
		} catch (IOException e) {e.printStackTrace();}
		return text;
	}	
	
	public static String getDataAsDialog(Frame parent,String title,String message,String defaultValue) {
		return JOptionPane.showInputDialog(null,title,
				message,
				JOptionPane.QUESTION_MESSAGE);
	}
	public static void displayMessageDialog(Frame parent,String message,String title) {
		   JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);
	}
	public static void displayMessageDialog(Frame parent,String message) {
		   JOptionPane.showMessageDialog(null,message,"Mesage",JOptionPane.INFORMATION_MESSAGE);
	}
	public static panelDialog displayPanelDialog(Frame parent,JPanel p,String title) {
		panelDialog dlg = new panelDialog(parent,p,title,false);
		return dlg;
	}
	public static panelDialog displayPanelDialog(Frame parent,JPanel p,String title,boolean bModal) {
		panelDialog dlg = new panelDialog(parent,p,title,bModal);
		return dlg;
	}
	public static modelessStatusDialog displayModelessStatusDialog(String msg) {
		//Frame top = getTopLevelParent(this);
		modelessStatusDialog d = new modelessStatusDialog(null,msg);
		return d;
	}
	
	public static Font getUnderlinedFont(Font f) {
		Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		return f.deriveFont(fontAttributes);
	}	

	public static Frame getTopLevelParent(Component component) {
		Component c = component;
		while(c.getParent() != null) {
			c=c.getParent();
		}
		if(c instanceof Frame) {
			return (Frame)c;
		} else {
			return null;
		}
	}
	
public static Vector splitIntoTokens(String data) {
		return splitIntoTokens(data,",",true);
	}
public static Vector splitIntoTokens(String data, String seps) {
		return splitIntoTokens(data,seps,true);
	}
public static Vector splitIntoTokens(String data, String seps,boolean emptyTokens) {		
		Vector v = new Vector();
		//TRACE("splitIntoTokens - " + data);
		if(emptyTokens) {
		String newData = "";
		StringBuffer b = new StringBuffer(data);
		StringBuffer s = new StringBuffer(seps);
		for(int i=0;i<b.length();i++) {
			int j = i + 1;
			if(j == b.length()) {
				j = j - 1;
			}
			if(b.charAt(i) == s.charAt(0) && b.charAt(j) == s.charAt(0)) {
				newData = newData + seps.substring(0,1) + "*";
			} else {
				newData = newData + data.substring(i,i+1);
			}
		}
		//TRACE("newData=" + newData);
		data = newData;
		}
		if(data.length() == 0) {return v;}

		StringTokenizer st = new StringTokenizer(data,seps);
		String token = st.nextToken();
		v.addElement(token);
		//TRACE("Token - " + token);
		while(st.hasMoreTokens()) {
			token = st.nextToken();
			if(token.equals("*")) {
				token = "";
			}
			v.addElement(token);
			//TRACE("Token - " + token);
		}

		return v;		
	}		
	public static Vector splitIntoTokensIncStringLit(String data, String seps) {
		String newData = ""; 
		boolean slFlag = false;
		StringBuffer s = new StringBuffer(seps);
		StringBuffer b = new StringBuffer(data);
		for(int i=0;i<b.length();i++) {
			if(b.charAt(i) == '\"') {slFlag = !slFlag;}
			if(b.charAt(i) == s.charAt(0) && slFlag) {
				newData = newData + "#@#";
			} else {
				newData = newData + data.substring(i,i+1);
			}
		}
		data = newData;
		
		Vector v = splitIntoTokens(data,seps,true);

		String s1 = "";
		for (int i=0;i<v.size();i++) {
			s1 = (String)v.elementAt(i);
			s1 = s1.replace("#@#",seps.substring(0,1));
			v.setElementAt(s1,i);
		}
		
		return v;
	}
}
