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

class transtableeditor {
	public		Map<String,Vector>		transTableMap = new HashMap<String,Vector>();
	private	int 						xTTMargin = 10;
	private	int 						yTTMargin = 10;
	private int 						charWidth = 8;
	private int							charHeight = 14;
	
	public transtableeditor(String name) {
		displayTransTableEditor(name);
	}
	public class transTableDrawingItem extends drawingItem {
		private String 				keyword;
		private String				text;
		private	String 				transtext;
		private String 				flags; 
		private String				prestring;
		private String				poststring;
		private	Rectangle			boundingRect;
		private	transTableTokenType	type = transTableTokenType.NONE;
		private int					groupTTID = 0;
		private	int					count = 0;
		
		public void setCount(int i) {count = i;}
		public int getCount() {return count;}
		public void setTTGroupID(int i) {groupTTID = i;}
		public int getTTGroupID() {return groupTTID;}
		public transTableTokenType getTokenType() {return type;}
		public void setTokenType(transTableTokenType t) {type = t;}
		public String getPreString() {return prestring;}
		public void setPreString(String s) {prestring = s;} 
		public String getPostString() {return poststring;}
		public void setPostString(String s) {poststring = s;} 
		public String getFlags() {return flags;}
		public String getTransText() {return transtext;}
		public String getText() {return text;}
		public transTableDrawingItem() {}
		public transTableDrawingItem(String id,int orgX,int orgY,String p1,String p2,String p3,String p4,boolean fill,Color c) {
			super(ehsConstants.dcTypeTransTable,id,orgX,orgY,p1,p2,p3,p4,fill,c);
			
			Vector v = supportFunctions.splitIntoTokens(p1,":");
			keyword = (String)v.elementAt(0);
			text = (String)v.elementAt(1);
			this.transtext = p2;
			this.flags = p3;
			this.groupTTID = Integer.parseInt(p4);
			boundingRect = new Rectangle(orgX,orgY,ehsConstants.ttDIWidth,ehsConstants.ttDIHeight);
			prestring = "";
			poststring = "";
		}
		public void outlinePaint(Graphics2D g2d,int dcLastX,int dcLastY,int xCord,int yCord,boolean dcFilled) {
			// do nothing here if drawing item is of a fixed size
		}
		public void editor() {
			String[] titles = new String[]{"Text","Trans Text","Flags"};			
			String[] defaults = new String[] {getText(),getTransText(),getFlags()};
			String[] data = supportFunctions.getDataAsDialog("Edit Trans Table Element",titles,defaults);
			if (data != null) {
				text = data[0];
				transtext = data[1];
				flags = data[2];
			}
			
			repaint();
		}
		public void paint(Graphics2D g2d,boolean focus) {
			setupPaint(g2d,focus);
			
			g2d.setColor(type.getTokenColor());

			AffineTransform at = new AffineTransform();
			Shape s = new RoundRectangle2D.Double(boundingRect.x, boundingRect.y, 
					boundingRect.width, boundingRect.height,
					25,25);
			g2d.fill(s);

			g2d.setColor(Color.black);
			g2d.drawRoundRect(boundingRect.x, boundingRect.y, 
					boundingRect.width, boundingRect.height,
					25,25);
			supportFunctions.centerTextAtBox(g2d,text,boundingRect);
			
			teardownPaint(g2d,focus);
		}
		public void fitToRectangle(Rectangle r) {
			// do nothing here if drawing item is of a fixed size
		}
		public Rectangle getBoundingRect() {
			return boundingRect;
		}
		public void setBoundingRect(Rectangle r) {
			boundingRect = r;
		}
		//public boolean canDelete() {return false;}
	}
	public class transTableCanvasDialog extends JDialog implements drawingCanvasUtils {
		private int 						numRows = 20;
		private int 						numCols = 60;
		private scrollableTransTableCanvas 	sDC1 = null;
		private	drawingCanvas				dc = null;
		private	ScrollPane 					sp = null;
		private	translationTable			table = null;
		private	boolean						bOK = false;
		private JButton						OKBut,CANCELBut;
		private final int					DELETE = -1;
		private final int					INSERT = 1;
		
		public boolean isOK() {return bOK;}
		public void rightClickAction(Vector v,MouseEvent evt) {;}
		public void leftClickAction(drawingItem d,MouseEvent evt) {;}
		public void leftClickSelectedAction(Vector v,MouseEvent evt) {;}
		public void hoverAction(drawingItem d,MouseEvent evt) {;}
		public void customDoCommand(String cmd,String params,drawingItem d) {;}
		public boolean canvasDoubleClickAction(MouseEvent evt) {return false;}
		public boolean canvasLeftClickAction(MouseEvent evt) {return false;}
		public void doubleClickAction(drawingItem d,MouseEvent evt) {
			doubleClickAction((transTableDrawingItem)d);
		}
		public void makeGroup(int groupID,Vector v,transTableTokenType type) {
			for(int i=0;i<v.size();i++) {
				transTableDrawingItem d = (transTableDrawingItem)v.elementAt(i);
				if (d.getTTGroupID() == groupID) {
					d.setTokenType(type);
				}
			}
		}
		public boolean isGroup(int groupID,Vector v) {
				int count = 0;
			
			for(int i=0;i<v.size();i++) {
				transTableDrawingItem d = (transTableDrawingItem)v.elementAt(i);
				if (d.getTTGroupID() == groupID) {
					if (++count > 1) {return true;}
				}
			}
			
			return false;
		}
		public void adjustCordData(int startIndex,int groupID,Vector v,int signAdjust,boolean bGroup) {
			int changeX = (xTTMargin + ehsConstants.ttDIWidth) * signAdjust;
			int changeY = (yTTMargin + ehsConstants.ttDIHeight) * signAdjust;
			
			//supportFunctions.displayMessageDialog(null,"adjustCordData:si:"+String.valueOf(startIndex)+":gi:"+String.valueOf(groupID)
			//		+":sa:"+String.valueOf(signAdjust)+":bGroup:"+String.valueOf(bGroup));
			
			for(int i=startIndex;i<v.size();i++) {
				transTableDrawingItem d = (transTableDrawingItem)v.elementAt(i);
				Rectangle rc = d.getBoundingRect();
				
				if (bGroup) {
					if (d.getTTGroupID() == groupID) {
						rc.y = rc.y + changeY;
						d.setBoundingRect(rc);
					}
				} else {
					rc.x = rc.x + changeX;
					d.setBoundingRect(rc);
					d.setTTGroupID(d.getTTGroupID() + signAdjust);
				}
			}
		}
		public Window getActiveWindow() {
	         final KeyboardFocusManager currentKeyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	         final Window activeWindow = currentKeyboardFocusManager.getActiveWindow();
	         
	         return activeWindow;
		}
		public void doubleClickAction(transTableDrawingItem d) {
			Vector tmp = supportFunctions.splitIntoTokens(d.getUserDefinedName(),":");
			Vector v = transTableMap.get((String)tmp.elementAt(0));
			//supportFunctions.displayMessageDialog(null,"Num Tokens (get)"+String.valueOf(v.size()));
			if(v != null) {
				int index = v.indexOf(d);
				if (index == 0) {
					supportFunctions.displayMessageDialog(null,"Can not edit keyword token.");
					return;
				}
				if(index != -1) {					
					int groupID = d.getTTGroupID();
					Rectangle rc = d.getBoundingRect();
					int xCord = rc.x;
					int yCord = rc.y;
					
					JPanel p = new JPanel();
					p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
					JButton closeBut = new JButton("Close");
					closeBut.setPreferredSize(new Dimension(14*charWidth,charHeight*2));
					closeBut.setMaximumSize(new Dimension(14*charWidth,charHeight*2));
					closeBut.setMinimumSize(new Dimension(14*charWidth,charHeight*2));
					closeBut.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							getActiveWindow().dispose();							
						}
					});
					JButton editorBut = new JButton("Editor");
					editorBut.setPreferredSize(new Dimension(14*charWidth,charHeight*2));
					editorBut.setMaximumSize(new Dimension(14*charWidth,charHeight*2));
					editorBut.setMinimumSize(new Dimension(14*charWidth,charHeight*2));
					editorBut.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							d.editor();
							getActiveWindow().dispose();							
						}
					});
					JButton deleteBut = new JButton("Delete");
					deleteBut.setPreferredSize(new Dimension(14*charWidth,charHeight*2));
					deleteBut.setMaximumSize(new Dimension(14*charWidth,charHeight*2));
					deleteBut.setMinimumSize(new Dimension(14*charWidth,charHeight*2));
					deleteBut.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							boolean bGroup = isGroup(groupID,v);
							getDC().deleteDrawingItem((drawingItem)d);
							v.removeElementAt(index);
							adjustCordData(index,groupID,v,DELETE,bGroup);
							if(isGroup(groupID,v)) {
								//supportFunctions.displayMessageDialog(null,"Group ID "+String.valueOf(groupID)+" is a group");
							} else {
								//supportFunctions.displayMessageDialog(null,"Group ID "+String.valueOf(groupID)+" is NOT a group");
								makeGroup(groupID,v,transTableTokenType.NORMAL);
							}
							getDC().repaint();
							getActiveWindow().dispose();							
						}
					});
					JButton insertBeforeBut = new JButton("Insert");
					insertBeforeBut.setPreferredSize(new Dimension(14*charWidth,charHeight*2));
					insertBeforeBut.setMaximumSize(new Dimension(14*charWidth,charHeight*2));
					insertBeforeBut.setMinimumSize(new Dimension(14*charWidth,charHeight*2));
					insertBeforeBut.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// groupID is one more then element clicked on 
							// xCord is xCord + xMargin + ehsConstants.ttDIWidth
							// yCord is the same as element double clicked on
							transTableDrawingItem dNew = (transTableDrawingItem)getDC().addDrawingItem(
								getDC().getEntity(),ehsConstants.dcTypeTransTable,
								xCord + xTTMargin + ehsConstants.ttDIWidth,yCord,
								(String)tmp.elementAt(0) + ":" + "newtext",
								"*c","x",
								String.valueOf(groupID+1),false,Color.black);
							dNew.setTokenType(transTableTokenType.NORMAL);
							dNew.setCanTransform(false);
							dNew.setPreString("");
							dNew.setPostString("");
							dNew.setUserDefinedName((String)tmp.elementAt(0) + ":" + "newtext");
							v.insertElementAt(dNew,index+1);
							adjustCordData(index + 2,groupID,v,INSERT,false); //+2 to start from element after inserted one
							getDC().repaint();
							getActiveWindow().dispose();							
						}
					});
					JButton insertGroupBut = new JButton("Insert Group");
					insertGroupBut.setPreferredSize(new Dimension(14*charWidth,charHeight*2));
					insertGroupBut.setMaximumSize(new Dimension(14*charWidth,charHeight*2));
					insertGroupBut.setMinimumSize(new Dimension(14*charWidth,charHeight*2));
					insertGroupBut.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// groupID is the same as the element double clicked on 
							// xCord is the same as element click on
							// yCord is yCord + yMargin + ehsConstants.ttDIHeight
							transTableDrawingItem dNew = (transTableDrawingItem)getDC().addDrawingItem(
								getDC().getEntity(),ehsConstants.dcTypeTransTable,
								xCord,yCord + yTTMargin + ehsConstants.ttDIHeight,
								(String)tmp.elementAt(0) + ":" + "newtext",
								"*c","x",
								String.valueOf(groupID),false,Color.black);
							dNew.setTokenType(transTableTokenType.MULTIPLE);
							d.setTokenType(transTableTokenType.MULTIPLE);
							dNew.setCanTransform(false);
							dNew.setPreString("");
							dNew.setPostString("");
							dNew.setUserDefinedName((String)tmp.elementAt(0) + ":" + "newtext");
							v.insertElementAt(dNew,index+1);
							adjustCordData(index + 2,groupID,v,INSERT,true); //+2 to start from element after inserted one
							getDC().repaint();
							getActiveWindow().dispose();							
						}
					});
					p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
					p.add(new JLabel("<html><b>Keyword:"+(String)tmp.elementAt(0)+"</html></b>"));
					p.add(new JLabel("<html><b>Text:"+d.getText()+"</html></b>"));
					p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
					p.add(deleteBut);
					p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
					p.add(insertBeforeBut);
					p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
					p.add(insertGroupBut);
					p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
					p.add(editorBut);
					p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
					p.add(closeBut);
					supportFunctions.displayPanelDialog(null,p,"Edit TT Element",true);
					
					//supportFunctions.displayMessageDialog(null,"Num Tokens (put)"+String.valueOf(v.size()));
					transTableMap.put((String)tmp.elementAt(0),v);								
				} else {
					supportFunctions.displayMessageDialog(null,"Token DI for keyword ("+(String)tmp.elementAt(0)+") NOT found");
				}
			} else {
				supportFunctions.displayMessageDialog(null,"Keyword ("+(String)tmp.elementAt(0)+") NOT Found");
			}
		}
		public void processTransTableDrawingItems() {	
			int xCord = 0;
			int yCord = 0;
			int yCordMax = 0;
			
			transTableMap.clear();
			modelessStatusDialog msgD = supportFunctions.displayModelessStatusDialog("Status");
			int numTokens = 0;
			Vector keywords = table.getKeywords();
			for (int i=0;i<keywords.size();i++) {
				int groupID = 0;
				Vector v = new Vector();
				xCord = xTTMargin;
				yCord = yCordMax + yTTMargin + ehsConstants.ttDIHeight;
				int yCordStart = yCord;
				yCordMax = yCord;
				//TRACE("processTransTableDrawingItems:keyword:"+(String)keywords.elementAt(i),4);
				//TRACE("processTransTableDrawingItems:entity:"+getDC().getEntity(),4);
				msgD.setText("Processing translation table keyword:" + (String)keywords.elementAt(i));
				//supportFunctions.displayMessageDialog(null,"processTransTableDrawingItems:key:"+(String)keywords.elementAt(i));
				transTableDrawingItem d = (transTableDrawingItem)getDC().addDrawingItem(getDC().getEntity(),ehsConstants.dcTypeTransTable,
						xCord,yCord,(String)keywords.elementAt(i) + ":" + (String)keywords.elementAt(i),"","",
						String.valueOf(groupID++),false,Color.black);
				d.setTokenType(transTableTokenType.KEYWORD);
				d.setCanTransform(false);
				d.setPreString(table.getTranslationTableEntry((String)keywords.elementAt(i)).getpreEntryString());
				d.setPostString(table.getTranslationTableEntry((String)keywords.elementAt(i)).getpostEntryString());
				d.setUserDefinedName((String)keywords.elementAt(i) + ":" + (String)keywords.elementAt(i));
				v.addElement(d);

				Vector tokens = table.getTokens((String)keywords.elementAt(i));				
				numTokens = numTokens + tokens.size();
				for (int j=0;j<tokens.size();j++) {
					translationTableToken token = (translationTableToken)tokens.elementAt(j);
					xCord = xCord + xTTMargin + ehsConstants.ttDIWidth;
					Vector syntaxStrings = supportFunctions.splitIntoTokens(token.getText(),"##",false);
					//TRACE("processTransTableDrawingItems:"+token.getText()+":Tokens:"+String.valueOf(syntaxStrings.size()),4);
					
					String transTextStrings1 = token.getTransText();
					if (transTextStrings1.length() == 0) {
						transTextStrings1 = "*c" + supportFunctions.stringRepeat("##*c",syntaxStrings.size()-1);
					}
					String flagStrings1 = token.getFlags();
					if (flagStrings1.length() == 0) {
						if (j == tokens.size()-1) { // last token should have a 'y' finish processing flag
							flagStrings1 = "y" + supportFunctions.stringRepeat("##y",syntaxStrings.size()-1);																						
						} else {
							flagStrings1 = "x" + supportFunctions.stringRepeat("##x",syntaxStrings.size()-1);															
						}
					}
					
					Vector transTextStrings = supportFunctions.splitIntoTokens(transTextStrings1,"##",false);
					Vector flagsStrings = supportFunctions.splitIntoTokens(flagStrings1,"##",false);
					
					for (int part=0;part<syntaxStrings.size();part++) {
						if (part > 0) {
							yCord = yCord + yTTMargin + ehsConstants.ttDIHeight;
							yCordMax = yCord;
						}
						String syntax = (String)syntaxStrings.elementAt(part);
						String transText = (String)transTextStrings.elementAt(part);
						String flags = (String)flagsStrings.elementAt(part);
						String id = (String)keywords.elementAt(i) + ":" + syntax;

						transTableTokenType type = transTableTokenType.NORMAL;
						if (syntaxStrings.size() > 1) {
							type = transTableTokenType.MULTIPLE;
						}
						if (flags.indexOf("z") != -1) {type = transTableTokenType.OPTIONAL;} // check for optional token
						
						d = (transTableDrawingItem)getDC().addDrawingItem(getDC().getEntity(),ehsConstants.dcTypeTransTable,
								xCord,yCord,id,transText,flags,String.valueOf(groupID),false,Color.black);

						d.setTokenType(type);
						d.setCanTransform(false);
						d.setPreString("");
						d.setPostString("");
						d.setUserDefinedName(id);
						v.addElement(d);
					} // for all parts
					groupID++;
					yCord = yCordStart;
				} // for all tokens
				transTableMap.put((String)keywords.elementAt(i),v);
			} // for all keywords
			
			msgD.destory();
			msgD.dispose();
			//supportFunctions.displayMessageDialog(null,"Keywords: " + String.valueOf(keywords.size()) + " Tokens: " + String.valueOf(numTokens));
		}
		public drawingCanvas getDC() {return dc;}
		public ScrollPane getScrollPane() {return sp;}
		public transTableCanvasDialog(Frame parent,String title,String entity,int xMax,int yMax,translationTable table) {
			super(parent,title,true);
			
			this.table = table;
						
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
			
			sDC1 = new scrollableTransTableCanvas(entity,xMax,yMax,20,20,false);

			dc = sDC1.getDC();
			sp = sDC1.getComponent();

			processTransTableDrawingItems();
			
			getDC().hideToolWindows();
			getDC().setUseDatabase(false);
			getDC().setReadOnly(false);
			getDC().addDrawingCanvasListener(this);
			getDC().setDrawFocusHandles(false);
			getDC().setSubEntity("subentity");
			
			getScrollPane().setSize(charWidth*numCols,charHeight*numRows);
			p.add(getScrollPane());
			p.add(Box.createRigidArea(new Dimension(charWidth,(int) (1.5*charHeight))));
			JPanel p1 = new JPanel();
			p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
			OKBut = new JButton("Ok");
			p1.add(OKBut);
			CANCELBut = new JButton("Cancel");
			p1.add(CANCELBut);
			p.add(p1);
			add(p);

			ActionListener OKTask = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					bOK = true;
					dispose();
			   }
			};
			OKBut.addActionListener(OKTask);
			ActionListener CANCELTask = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					dispose();
			   }
			};
			CANCELBut.addActionListener(CANCELTask);

 			addWindowListener(new WindowAdapter() {
 				public void windowClosing(WindowEvent evt) {
 					dispose();
	            }
			});

			pack();
			setVisible(true);			
		}		
		public void destroy() {
			dispose();
	    }
	}	

	public void displayTransTableEditor(String TTName) {
		int xMax = 5000;
		int yMax = 5000;
		translationTable transTable = new translationTable();
		transTable.loadTranslationTable(TTName);
		
		if (transTable != null) {
			JPanel keyPanel = new JPanel();
			basicFile f = new basicFile("../classes/common/ttkey.txt");
			JTextArea text = new JTextArea(f.loadFile(),10,30);
			text.setEditable(false);
			text.setBackground(ehsConstants.lightyellow);
			keyPanel.add(text);
			floatingWindow keyWindow = new floatingWindow(keyPanel,"Trans Table Key");
			keyWindow.setLocation(0,0);
			transTableCanvasDialog d = new transTableCanvasDialog(null,"Trans Table Editor","entity",xMax,yMax,transTable);			
			keyWindow.destory();
			if (d.isOK()) {
				xmlDataFile ttItems = new xmlDataFile();
				if (ttItems.openXMLDataFile(supportFunctions.getPathFilenameNoExt(TTName),"transtable",true)) {
					org.w3c.dom.Element root = ttItems.getRootElement();
					org.w3c.dom.Document doc = ttItems.getXMLDocument();
					for (Map.Entry<String, Vector> entry : transTableMap.entrySet()) {
						String key = entry.getKey();
						Vector value = entry.getValue();
						// assume first DI item is the keyword
						transTableDrawingItem di = (transTableDrawingItem)value.elementAt(0);
						org.w3c.dom.Element keywordnode = doc.createElement("ttkeyword");
						keywordnode.setAttribute("keyword",key);
						root.appendChild(keywordnode);
						
						if (value.size() > 1) {
							for (int i=1;i<value.size();i++) {
								di = (transTableDrawingItem)value.elementAt(i);
								org.w3c.dom.Element node = doc.createElement("ttentry");
								node.setAttribute("syntax",di.getText());
								node.setAttribute("transtext",di.getTransText());
								node.setAttribute("flags",di.getFlags());
								node.setAttribute("prestring",di.getPreString());
								node.setAttribute("poststring",di.getPostString());
								node.setAttribute("groupid",String.valueOf(di.getTTGroupID()));
								keywordnode.appendChild(node);
							}
						}
					}
					
					ttItems.saveXMLDataFile();
					ttItems.closeXMLDataFile();
					supportFunctions.displayMessageDialog(null,"XML Translation Table File Saved, Tokens:" 
							+ String.valueOf(transTableMap.size()));
				}
			}
		} else {
			supportFunctions.displayMessageDialog(null,"Can not load translation table: " + TTName);
		}
	}

	public class transTableDrawingCanvas extends drawingCanvas {
		private ArrayList<Integer> l = new ArrayList<Integer>();
		
		   public transTableDrawingCanvas(String entity,int maxX,int maxY,int spaceX,int spaceY,boolean gVisible) {
			   super(entity,maxX,maxY,spaceX,spaceY,gVisible);
		   }
		   public boolean doPaint(drawingItem d) {
			   if (d.getType() <= ehsConstants.dcTypeBuiltInMaxId) {return true;} //built in types always drawn

			   return true;
		   } 
		   public boolean printOverride(Graphics2D g2d) {
			   g2d.drawImage(getCurrentCanvasImage(),0,0,canvasMaxX,canvasMaxY,null);
			   return true;
		   }
			public int calcJump(transTableDrawingItem d) {
				int jump = 1; // default jump value
				String flags = d.getFlags();
				
				int index = flags.indexOf("r");
				if (index != -1) {
					jump = Integer.parseInt(flags.substring(index+1,index+3));
				}
				if (flags.indexOf("y") != -1) {return ehsConstants.transTableNoJump;}
				
				if (jump != 0) {
					if (jump > 0) { // forward jump
						jump = l.get(d.getTTGroupID()) - d.getCount();
					} else { // backward jump
						jump = jump - d.getCount();
					}
				}
				
				return jump;
			}
			public Vector getTransTableGroupItems(Vector values,int group) {
				Vector v = new Vector();
				
				for(int i=0;i<values.size();i++) {
					transTableDrawingItem di = (transTableDrawingItem)values.elementAt(i);
					if (group == di.getTTGroupID()) {
						v.addElement(di);
					}
				}
				
				return v;
			}
			public void drawTransTableConnectors(Graphics2D g2d) {
				for (Map.Entry<String, Vector> entry : transTableMap.entrySet()) {
					String key = entry.getKey();
					Vector value = entry.getValue();
					l.clear();
					int currentGroup = 0;
					int count = 0;
					for(int i=0;i<value.size();i++) {
						transTableDrawingItem di = (transTableDrawingItem)value.elementAt(i);
						if (currentGroup == di.getTTGroupID()) {
							di.setCount(count++);
						} else {
							l.add(count);
							//TRACE("drawTransTableConnectors:count:"+String.valueOf(count),4);
							currentGroup = di.getTTGroupID();
							count = 0;
							di.setCount(count++);
						}
						if (i == value.size()-1) {// add the count for the LAST TT DI Element
							l.add(count);
							//TRACE("drawTransTableConnectors:count:"+String.valueOf(count),4);
						}
					}
					for(int i=0;i<value.size();i++) {
						
						transTableDrawingItem di = (transTableDrawingItem)value.elementAt(i);
						int jump = calcJump(di);
						
						//TRACE("drawTransTableConnectors:i:"+String.valueOf(i)+":keyword:"+key+":value:"
						//		+di.getText()+":flags:"+di.getFlags()+":group:"+di.getTTGroupID()
						//		+":count:"+di.getCount()+":jump:"+String.valueOf(jump),4);

						if (jump != ehsConstants.transTableNoJump) {
							transTableDrawingItem diTo = (transTableDrawingItem)value.elementAt(i + jump);
														
							if (jump == 0) {
								Vector v = getTransTableGroupItems(value,di.getTTGroupID());
								if (v.size() > 2) {
									transTableDrawingItem diFirst = (transTableDrawingItem)v.elementAt(0);
									transTableDrawingItem diLast = (transTableDrawingItem)v.elementAt(v.size() - 1);
									Rectangle rcFirst = diFirst.getBoundingRect();
									Rectangle rcLast = diLast.getBoundingRect();
									Point ptFirst = centerPoint(rcFirst);
									Point ptLast = centerPoint(rcLast);
									int m = (ehsConstants.ttDIHeight / 2) + (yTTMargin / 2);
									int m1 = (int)(diFirst.getBoundingRect().width / 2.0) + (int)(xTTMargin / 2.0);
									
									g2d.drawLine(ptFirst.x,ptFirst.y,ptFirst.x,ptFirst.y-m);
									g2d.drawLine(ptFirst.x,ptFirst.y-m,ptFirst.x+m1,ptFirst.y-m);
									
									g2d.drawLine(ptFirst.x+m1,ptFirst.y-m,ptLast.x+m1,ptLast.y+m);
									
									g2d.drawLine(ptLast.x+m1,ptLast.y+m,ptLast.x,ptLast.y+m);
									g2d.drawLine(ptLast.x,ptLast.y+m,ptLast.x,ptLast.y);

								}
							}
							if (jump == -1 || jump == 1) {
								Point ptStart = centerPoint(di.getBoundingRect());
								Point ptEnd = centerPoint(diTo.getBoundingRect());
								g2d.drawLine(ptStart.x,ptStart.y,ptEnd.x,ptEnd.y);
							}
							if (jump < -1  || jump > 1) {
								Point ptStart1 = centerPoint(di.getBoundingRect());
								Point ptEnd1 = centerPoint(diTo.getBoundingRect());
								// use 1.5 instead of 2.0 because otherwise the connector would merge with other connectors
								double length = (di.getBoundingRect().height / 2.0) + (yTTMargin / 1.5);
								g2d.drawLine(ptStart1.x,ptStart1.y,ptStart1.x,(int)(ptStart1.y - length));
								g2d.drawLine(ptStart1.x,(int)(ptStart1.y - length),ptEnd1.x,(int)(ptEnd1.y - length));
								g2d.drawLine(ptEnd1.x,(int)(ptEnd1.y - length),ptEnd1.x,ptEnd1.y);
							}
						}
						//supportFunctions.displayMessageDialog(null,"pause");
					}
				}		
			}
		   public void beforePainting(Graphics2D g2d) {
			   drawTransTableConnectors(g2d);
		   }
		   public void customOutlineDrawingItem(Graphics2D g2d,int type) {;}
		   public drawingItem createCustomDrawingItem(String entity,int type,String id,int x,int y,String p1,String p2,String p3,String p4,boolean fill,Color c) {
				if (type == ehsConstants.dcTypeTransTable) {
					transTableDrawingItem d = new transTableDrawingItem(id,x,y,p1,p2,p3,p4,fill,c);
					return d;
				}
				
				return (drawingItem)null;
		   }
		   public PopupMenu customCreateMenu(PopupMenu menu) {
			   menu.removeAll();
			   
			   return menu;
		   }
		   public void customUpdateMenu(PopupMenu menu) {
			   
		   }
	}

	public class scrollableTransTableCanvas {
		  private transTableDrawingCanvas  	dC;
		  private ScrollPane		  		sPane;
		  private Adjustable				bottomSB,rightSB;
		  
		  public scrollableTransTableCanvas() {
			  this("",ehsConstants.dcMaxX,ehsConstants.dcMaxY,ehsConstants.dcGridSpaceX,ehsConstants.dcGridSpaceY,true);
		  }
		  public scrollableTransTableCanvas(String entity,int maxX,int maxY,int spaceX,int spaceY,boolean visible) {
			  dC = new transTableDrawingCanvas(entity,maxX,maxY,spaceX,spaceY,visible);
			  sPane = new ScrollPane();
			  sPane.add(dC);
			  bottomSB = sPane.getHAdjustable();
			  rightSB = sPane.getVAdjustable();
		  }
		  public Point getGCTopLeftCords() {return sPane.getScrollPosition();}
		  public void setGCTopLeftCords(Point pt) {sPane.setScrollPosition(pt);}
		  public Adjustable getBottomSB() {return bottomSB;}
		  public Adjustable getRightSB() {return rightSB;}
		  public void paint(Graphics g) {dC.paint(g);}
		  public drawingCanvas getDC() {return dC;}
		  public ScrollPane getComponent() {return sPane;}
		  public void update(Graphics g) {paint(g);} 
		  public void paint() {paint(dC.getGraphics());}
		  public void setSize(Dimension d) {dC.setSize(d);}
		  public Dimension getSize() {return dC.getSize();}
	}
}