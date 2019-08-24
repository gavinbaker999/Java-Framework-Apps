/**
 * This implementation of the Stroke interface draws a text along the path of 
 * the stroked Shape. Optionally, the text can be cycled along the path. That is,
 * the text is repeats the text along the shape as many times as needed to cover 
 * the full Shape path. Otherwise, the text is drawn only once.
 *
 * @author     Vincent Hardy
 * @version    1.0, 09/09/98
 */
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

public class TextStroke implements Stroke{
  static final String ERROR_INVALID_TEXT_ARGUMENT = "Error : text should be non null and contain non white-space characters";
  final FontRenderContext frc = new FontRenderContext(null, true, true);
  static final int FLATTENESS = 1;

  /**
   * 
  /**
   * Holds the description of the Text glyphs which are drawn along the stroked
   * Shapes path.
   */
  private GlyphVector glyphVector;

  /**
   * Ascent for the glyphVector
   */
  private float ascent;

  /**
   * Number of glyphs in Vector
   */
  private int nGlyphs;

  /**
   * Controls whether or not the text should be repeated
   */
  private boolean cycle;

  /**
   * Size of the spacing between two iterations of the text string
   */
  private float cycleGap;

  /**
   * Text drawn along the stroked shapes's path
   */
  private String text;

  /**
   * Font used to draw text along stroked shapes
   */
  private Font font;

  /**
   * @param text the text to be drawn along the path of stroked Shapes.
   * @param font the font used to create the text glyphs.
   * @param cycle true if the text should be repeated as many times as needed along
   *        the stroked paths.
   * @param cycleGap size of the gap between cycles of text. Ignored if cycle is false.
   *        if cycleGap is negative, it defaults to the font size.
   */
  public TextStroke(String text, Font font, boolean cycle, float cycleGap){
    if(text==null || text.trim().length()==0)
      throw new IllegalArgumentException(ERROR_INVALID_TEXT_ARGUMENT);

    if(font==null)
      throw new NullPointerException();

    glyphVector = font.createGlyphVector(frc, text);
    glyphVector.performDefaultLayout();
    nGlyphs = glyphVector.getNumGlyphs();
    LineMetrics metrics = font.getLineMetrics(text, frc);
    this.ascent = metrics.getAscent();
    this.cycle = cycle;
    this.cycleGap = cycleGap;
    if(cycleGap<0)
      this.cycleGap = font.getSize();

    this.text = text;
    this.font = font;
  }

  /**
   * Stroke interface implementation. 
   */
  public Shape createStrokedShape(Shape shape){
    // Flatten path.
    PathIterator pi = new FlatteningPathIterator(shape.getPathIterator(new AffineTransform()), FLATTENESS);

    // Iterate through the path and process each
    // line segment.
    float seg[] = new float[6];
    int segType = 0;
    ProcessSegmentControl ctl = new ProcessSegmentControl();
    boolean stop = false;

    while(!pi.isDone()){
      segType = pi.currentSegment(seg);
      switch(segType){
      case PathIterator.SEG_MOVETO:
	// Moving to a new start.
	ctl.x = seg[0];
	ctl.y = seg[1];
	ctl.mx = ctl.x;
	ctl.my = ctl.y;
	ctl.start = true;
	break;
      case PathIterator.SEG_LINETO:
	// New line segment, try to add glyphs on the path.
	ctl.dx = seg[0];
	ctl.dy = seg[1];
	stop = processSegment(ctl);
	ctl.start = false;
	break;
	
      case PathIterator.SEG_CLOSE:
	// Closing to the last move to position.
	ctl.dx = ctl.mx;
	ctl.dy = ctl.my;
	stop = processSegment(ctl);
	break;
	
      case PathIterator.SEG_QUADTO:
      case PathIterator.SEG_CUBICTO:
      default:
	// This should never happen because we are using a FlatteningPathIterator
	throw new Error("Illegal seg type : " + segType);
      }
      pi.next();
      if(stop)
	break;
    }    

    return ctl.s;
  }

  /**
   * Process a new segment of the stroked shape
   */
  private boolean processSegment(ProcessSegmentControl ctl){
    boolean stop = false;

    // Process current segment length
    float segLength = (float)Point2D.distance(ctl.x, ctl.y, 
					      ctl.dx, ctl.dy);

    // Segment slope factors
    float dx = (ctl.dx-ctl.x)/segLength;
    float dy = (ctl.dy-ctl.y)/segLength;

    // Adjust start position
    ctl.x -= dx*ctl.startCredit;
    ctl.y -= dy*ctl.startCredit;

    // Initial insert point
    float insertDist = ctl.ngx-ctl.d;
    float x = ctl.x + insertDist*dx;
    float y = ctl.y + insertDist*dy;

    // Angle the current segment makes with the x-axis
    float rotationAngle = (float)Math.atan2(ctl.dy-ctl.y, ctl.dx-ctl.x);

    // Check if this segment overlaps with the previous one.
    float shiftDistance = 0;
    if(!ctl.start){ 
      // Do not perform check in case this is the first segment after a moveTo
      // because the previous segment is considered non-relevant.
      float deltaAngle = rotationAngle - ctl.previousAngle;
      if(deltaAngle<-Math.PI) deltaAngle += 2*(float)Math.PI;
      if(deltaAngle>Math.PI) deltaAngle -= 2*(float)Math.PI;

      if(deltaAngle<0){
	// The current segment overlaps with the previous one
	// Shift starting point to avoid overlapping.
	shiftDistance = Math.abs(ascent*(float)Math.tan(deltaAngle/2));
	segLength -= shiftDistance;
	x += shiftDistance*dx;
	y += shiftDistance*dy;
      }
    }

    // Only process if the next insert point is within the segment
    // and the glyph to insert fits in segment
    if( (ctl.d + ctl.startCredit + segLength)> (ctl.ngx + ctl.glyphWidth) ){  
      // Update the previousAngle if a glyph is actuall inserted.
      ctl.previousAngle = rotationAngle;

      // Process the rotation which should be applied to glyphs
      // prior to inserting them into the stroked path.
      // The rotation is centered about the insert point
      // and its angle is that of the segment with the x axis.
      AffineTransform at = new AffineTransform();

      // Translate glyphs so that the baseline is on the current segment
      at.translate(x-ctl.ngx, y-ctl.ngy);      
      at.rotate(rotationAngle, ctl.ngx, ctl.ngy);
      at.concatenate(ctl.defaultTransform);

      // Used to store the remaining length in current segment
      float remainingSegLength = segLength + ctl.startCredit;

      // Transform used to position glyph
      AffineTransform glyphTransform = new AffineTransform();

      // Controls whether the next glyph fits on current segment
      boolean fits = true;
      while(fits){
	// Add glyph to stroked path
	Point2D glyphPos = glyphVector.getGlyphPosition(ctl.glyphIndex);
	glyphTransform.setToIdentity();
	glyphTransform.concatenate(at);
	glyphTransform.translate(glyphPos.getX(), glyphPos.getY());
	ctl.s.append( glyphTransform.createTransformedShape(glyphVector.getGlyphOutline(ctl.glyphIndex)), false );

	// Update 'used' distance
	// ctl.d += ctl.glyphWidth;
	float newD = (float)ctl.defaultTransform.getTranslateX() + (float)glyphPos.getX() + ctl.glyphWidth;

	// Some of the current segment has been used...
	remainingSegLength -= (newD - ctl.d); // ctl.glyphWidth;

	ctl.d = newD;

	// Update segment starting point
	ctl.x += ctl.glyphWidth*dx;
	ctl.y += ctl.glyphWidth*dy;

	// Update next glyph's index : stop if not cycling
	// and end has been reached.
	ctl.glyphIndex++;
	if(ctl.glyphIndex>=nGlyphs){
	  if(!cycle){
	    stop = true;
	    fits = false;
	  }
	  else{
	    // We have reached the end of the text. We are going to cycle.
	    // Adjust the default transform to take that into account.
	    ctl.defaultTransform.translate((float)glyphVector.getVisualBounds().getWidth() + cycleGap, 0);
	    at.translate((float)glyphVector.getVisualBounds().getWidth() + cycleGap, 0);
	  }
	}

	if(!stop){
	  ctl.glyphIndex %= nGlyphs;
	  
	  // Distance at wich the next insert will happen
	  ctl.ngx = (float)ctl.defaultTransform.getTranslateX() + (float)glyphVector.getGlyphPosition(ctl.glyphIndex).getX();
	  ctl.ngy = (float)glyphVector.getGlyphPosition(ctl.glyphIndex).getY();

	  // Width of the next glyph
	  // ctl.glyphWidth = glyphVector.getGlyphOutline(ctl.glyphIndex).getBounds().width;
	  ctl.glyphWidth = glyphVector.getGlyphLogicalBounds(ctl.glyphIndex).getBounds().width;
	  
	  // Check if next glyph fits
	  fits = (ctl.d + remainingSegLength)> (ctl.ngx + ctl.glyphWidth);
	}
      }

      ctl.x = ctl.dx;
      ctl.y = ctl.dy;
      ctl.startCredit = remainingSegLength;
    }

    return stop;
  }

  public String getText(){
    return text;
  }

  public Font getFont(){
    return font;
  }

  public boolean isCycle(){
    return cycle;
  }

  public float getCycleGap(){
    return cycleGap;
  }

  /*
   * Class used to hold information about the path currently being
   * stroked.
   */
  class ProcessSegmentControl{
    /** Current segment start */
    float x, y;

    /** Last move coordinates */
    float mx, my;

    /** Current segment end */
    float dx, dy;

    /** Used when iterating through a path to store the amount of space not
     * used which can be used in the following segment.
     */
    float startCredit;

    /** Current cumulated distance of 'used' path.*/
    float d;

    /** Next insert can happen at this cummulated distance*/
    float ngx;

    /** Next insert can happen at this elevation */
    float ngy;

    /** Width of the next glyph to be inserted */
    int glyphWidth;

    /** Index of the next glyph to place on path */
    int glyphIndex;

    /** Controls if the current segment is the first one after a moveTo */
    boolean start;

    /** Angle that the previous segment makes with the x-axis. This is used 
     *  to detect situations where the text of one segment might overlap on
     *  the previous one.
     */
    float previousAngle;

    /** Stroked path */
    GeneralPath s = new GeneralPath();

    /** Used to adjust glyphVector position when the text is repeated over
     * the stroked shape.
     */
    AffineTransform defaultTransform = new AffineTransform();

    /**
     * Constructor 
     */
    public ProcessSegmentControl(){
      glyphWidth = glyphVector.getGlyphOutline(0).getBounds().width;
    }
  }
}
