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

public class registrationinfo {
		private boolean appUseDatabase;
		private	boolean	appRemoteHosted;
		private	String 	userID;
		private	String	userName;
		private	String	userSerialNumber;
		private	String	userRawSerialNumber;
		private	String	appName;
		private String  appDescription;
		private String  appSerialBase;
		private	String	appVersion;
		private	String	appDate;
		private	String	appCopyright;
		private String 	statusMsg;
		private String  lastLogonDetails;
		private	boolean	appPreRelease;
		private Vector 	rptTypes = new Vector();
		private Vector	rptStatus = new Vector();
		private int	numAttemptsLeft,userCredit,features; 
		private	userManager userMan;
		private String buildDate = "";
		private String frameworkBuildDate = "";
		private String gitVersionInfo = "";
		private String appDirectory = "";
		private String splashJPG = "";
		private String exHelpFile = "";
		private String exFAQFile = "";
		private int charWidth = 8;
		private int charHeight = 14;
		
	public class userManager {
		public String[] getUserListByTag(String tag) {
			String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegID,sysEHSRegName,sysEHSRegEmail,sysEHSRegProduct FROM sysehsregistrations WHERE sysEHSRegTag='"+tag+"' GROUP BY sysEHSRegEmail","");
			
			Vector v = supportFunctions.splitIntoTokens(data,",");
		    String[] tokens = new String[v.size() / 4];
			
			int j = 0;
			for (int i=0;i<v.size();i=i+4) {
				tokens[j++] = (String)v.elementAt(i) + "," + (String)v.elementAt(i+1) + "," + (String)v.elementAt(i+2) + "," + (String)v.elementAt(i+3) + "," + (String)v.elementAt(i+4);
			}
			
			return tokens;
		}
		public String[] getUserListByProduct(String product) {
			String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegID,sysEHSRegName,sysEHSRegEmail,sysEHSRegTag FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' GROUP BY sysEHSRegEmail","");
			
			Vector v = supportFunctions.splitIntoTokens(data,",");
		    String[] tokens = new String[v.size() / 4];
			
			int j = 0;
			for (int i=0;i<v.size();i=i+4) {
				tokens[j++] = (String)v.elementAt(i) + "," + (String)v.elementAt(i+1) + "," + (String)v.elementAt(i+2) + "," + (String)v.elementAt(i+3) + "," + (String)v.elementAt(i+4);
			}
			
			return tokens;
		}
	}
		registrationinfo(String ad,String name,String description,String serialbase,String version,String date,String cr,String sj,String eh,boolean internet,String bd,String fbd,String gvi) {
			buildDate = bd;
			frameworkBuildDate = fbd;
			gitVersionInfo = gvi;
			appDirectory = ad;
			splashJPG = sj;
			exHelpFile = eh;
			numAttemptsLeft = -1;
			appUseDatabase = true;
			appRemoteHosted = internet;
			appName = name;
			appDescription = description;
			appSerialBase = serialbase;
			appVersion = version;
			appDate = date;
			appCopyright = cr;
			appPreRelease = false;
			features = 0;
			userCredit = 0;
			statusMsg = "";
			rptTypes = supportFunctions.splitIntoTokens("Action Item,Defect,Contact Us,Documentation,Enhancement,Feature,Comment,Help File,Knowledge Base,Manual,Order,Quote,Sales,Purchase Order,Equipment Order,Supplies Order,Tech Support,Trainning,Website Design");
			rptStatus = supportFunctions.splitIntoTokens("Unassigned,Open,On Order,In Process,Coding,Documentation,Testing,QA,Closed");
			patchBuildNumber();
			clearRegistrationData();
			supportFunctions.createAppConfigSettings("../"+appDirectory+"/"+name+"_settings");
			userMan = new userManager();
		}
		void setFAQFile(String file) {exFAQFile = file;}
		String getFAQFile() {return exFAQFile;}
		String getSplashJPG() {return splashJPG;}
		String getHelpFile() {return exHelpFile;}
		String getAppDirectory() {return appDirectory;}
		String getBuildDate() {return buildDate;}
		String getFrameworkBuildDate() {return frameworkBuildDate;}
		String getGitVersionInfo() {return gitVersionInfo;}
		public void setUserTag(String s) {
			if (getUserRegistered()) {
				String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegTag FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+userName+"'","");
				Vector v = supportFunctions.splitIntoTokens(data,",");
				String[] tokens = new String[v.size()];
				v.copyInto(tokens);
				
				String newTag = "";
				boolean bTagFound = false;
				for (int i=0;i<tokens.length;i=i+2) {
					if (i != 0) {newTag = newTag + ",";}
					newTag = newTag + tokens[i] + ",";
					if (tokens[i].equals(getAppName())) {newTag = newTag + s;bTagFound = true;} else {newTag = newTag + tokens[i+1];}
				}
				
				if (!bTagFound) {
					if (newTag.length() != 0) {newTag = newTag + ",";}
					newTag = newTag + getAppName() + "," + s;
				}
				
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegTag='" + newTag + "' WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+userName+"'","");
			}	
		}
		public String getUserTag() {
			if (getUserRegistered()) {
				String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegTag FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+userName+"'","");
				Vector v = supportFunctions.splitIntoTokens(data,",");
				String[] tokens = new String[v.size()];
				v.copyInto(tokens);
				
				for (int i=0;i<tokens.length;i=i+2) {
					if (tokens[i].equals(getAppName())) {return tokens[i+1];}
				}
			}
			
			return "";
		}
		public void setAllowEmails(boolean b) {
			if (getUserRegistered()) {
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegReceiveEmailUpdates=" + String.valueOf(b) + " WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+userName+"'","");
			}
		}
		public boolean getAllowEmails() {
			if (getUserRegistered()) {
				String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegReceiveEmailUpdates FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+userName+"'","");
				return supportFunctions.valueOf(data);
			}
			
			return false;
		}
		protected void finalize() throws Throwable {
			super.finalize();
		}
		public userManager getUserManager() {return userMan;}
		public configurationSettings getConfigurationSettings() {return supportFunctions.getAppConfigSettings();}
		public String getLastLogonDetails() {return lastLogonDetails;}
		public String getAppSerialBase() {return appSerialBase;}
		public String getSerialNumber() {return userSerialNumber;}
		public int isTrial() {return numAttemptsLeft;}
		public String getInfoText() {
			String extra = "";
			if (getPreRelease()) {extra="(prerelease)";}
			if(getUserRegistered()) {
				return appName + "\n" + appVersion + " (" + appDate + ") " + extra + "\n" + appCopyright + "\n" + userName + "\n" + userSerialNumber + "\n" + getBuildString() + "\n" + getframeworkBuildString();
			} else {
				return appName + "\n" + appVersion + " (" + appDate + ") " + extra + "\n" + appCopyright + "\n" + "Unregistered Version" + "\n" + getBuildString() + "\n" + getframeworkBuildString();
			}
		}
		public String getHTMLInfoText() {
			String extra = "";
			if (getPreRelease()) {extra="(prerelease)";}
			if(getUserRegistered()) {
				return "<html><center><font color='red'><font size='+3'><i>" + appName + "</i></font></font><br><font color='blue'><font size='+2'>" + appVersion + " (" + appDate + ")" + " " + extra + "</font></font><br><br><b>" + appCopyright + "<br>" + userName + " " + userSerialNumber + "</b><br>" + getBuildString() + "<br><font size='-1'>" + getframeworkBuildString() + "</font></center></html>";
			} else {
				return "<html><center><font color='red'><font size='+3'><i>" + appName + "</i></font></font><br><font color='blue'><font size='+2'>" + appVersion + " (" + appDate + ")" + " " + extra + "</font></font><br><br><b>" + appCopyright + "<br>" + "Unregistered Version</b><br>" + getBuildString() + "<br><font size='-1'>" + getframeworkBuildString() + "</font></center></html>";
			}
		}
		public String getApplicationInfoText() {
			return appName + " " + appVersion + " " + appCopyright;
		}
		public String getSingleLineInfoText() {
			String extra = "";
			if (getPreRelease()) {extra="(prerelease) ";}
			if(getUserRegistered()) {
				return appName + " " + appVersion + " (" + appDate + ")" + " " + extra + appCopyright + " " + userName + " " + userSerialNumber + " " + getBuildString() + " " + getframeworkBuildString();
			} else {
				return appName + " " + appVersion + " (" + appDate + ")" + " " + extra + appCopyright + " " + "Unregistered Version" + " " + getBuildString() + " " + getframeworkBuildString();
			}
		}
		public String getMultiLineInfoText() {
			String extra = "";
			if (getPreRelease()) {extra="(prerelease) ";}
			if(getUserRegistered()) {
				return appName + " " + appVersion + " (" + appDate + ")" + " " + extra + appCopyright + "\n" + userName + " " + userSerialNumber + "\n" + getBuildString() + " " + getframeworkBuildString();
			} else {
				return appName + " " + appVersion + " (" + appDate + ")" + " " + extra + appCopyright + "\n" + "Unregistered Version" + "\n" + getBuildString() + " " + getframeworkBuildString();
			}
		}
		public String getStatusMsg() {return statusMsg;}
		public void setStatusMsg(String msg) {statusMsg = msg;}
		public int getFeatures() {return features;}
		public void setFeatures(int f) {features = f;}
		public Vector getRptTypes() {return rptTypes;}
		public void setRptTypes(Vector v) {rptTypes = v;}
		public boolean getPreRelease() {return appPreRelease;}
		public void setPreRelease(boolean b) {appPreRelease=b;}
		public boolean getUseDatabase() {return appUseDatabase;}
		public void setUseDatabase(boolean b) {appUseDatabase=b;}
		public boolean isOffline() {return !appRemoteHosted;}
		public String getUserName() {return userName;}
		public String getUserID() {return userID;}
		public void setUserID(String s) {userID = s;}
		public void clearRegistrationData() {
			   userName = "unregistered";
			   userID = "";
			   userRawSerialNumber = "";
			   userSerialNumber = "";
			   lastLogonDetails = "";
			   userCredit = 0;
		}
		public void initUserCredit() {
			if (getUserRegistered()) {
				String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegCredit FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+ userName+"'","0");
				try {
					userCredit = Integer.parseInt(data);
				} catch (Exception e) {userCredit = 0;}
			} else {userCredit = 0;}
		}
		public int getUserCredit() {return userCredit;}
		public void setUserCredit(int val) {
			if (getUserRegistered()) {
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegCredit="+String.valueOf(val)+" WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+userName+"'","");
				userCredit = val;
			}
			else {
				supportFunctions.displayMessageDialog(null,"User is not registered");
			}
		}
		public String getAppCopyright() {
			return appCopyright;
		}
		public boolean getAppRemotedHosted() {
			return appRemoteHosted;
		}
		public String getAppName() {
			   return appName;
		}
		public String getAppNameVersion() {
			   return appName + " " + appVersion;
		}
		public String getAppVersion() {
				return appVersion;
		}
		public int getMinorVersionNumber() {
			return getMinorVersionNumber(appVersion);
		}
		public int getMajorVersionNumber() {
			return getMajorVersionNumber(appVersion);
		}
		public int getMinorVersionNumber(String version) {
			int val = 0;
			try {
				val = Integer.parseInt(version.substring(3,5));
			} catch(Exception e) {e.printStackTrace();}
			return val;
		}
		public int getMajorVersionNumber(String version) {
			int val = 0;
			try {
				val = Integer.parseInt(version.substring(0,2));
			} catch(Exception e) {e.printStackTrace();}
			return val;
		}
		public String isUserRegistered(String user,String product) {
			   return supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegEmail FROM sysehsregistrations WHERE sysEHSRegName='"+user+"' AND sysEHSRegProduct='"+product+"'","");
		}
		public void saveRegistrationData() {
		   if (getUseDatabase()) {
		   } else {
//			   	 char[] buf = new char[4096];
//			   	 try {
//				 	 File f = new File(appName+userName+".lic");
//				 	 FileWriter out = new FileWriter(f);
//					 if (out != null) {
//					 	String s = userRawSerialNumber + "," + userName;
//					 	StringBuffer sb = new StringBuffer(s);
//						int len = s.length();
//						for(int i=0;i<len;i++) {buf[i]=sb.charAt(i);}
//						out.write(buf,0,len);
//					 	out.close();
//					}
//				 } catch (Exception e) {e.printStackTrace();}
		   }
		}
		public String getRawSerialNumber() {return userRawSerialNumber;}
		public boolean loadRegistrationData(String username,String password) {
			String data = "";
//			if (getUseDatabase()) {
				data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegSerial,sysEHSRegName,sysEHSRegPassword,sysEHSRegLogonDate,sysEHSRegLogonTime,sysEHSRegUserName FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUserName='"+username+"' AND sysEHSRegActive=1","");
				// assume at the moment passwords match, but need to check that !!!
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegLogonDate='"+supportFunctions.currentShortDate()+"' WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUserName='"+username+"'","");
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegLogonTime='"+supportFunctions.currentShortTime()+"' WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUserName='"+username+"'","");
				//TRACE("LRD:"+data,4);
				if (data.length() == 0) { // user not yet registered
					//TRACE("LRD:User not yet registered",4);
					return false;
				} 
//			}
				
			return loadRegistrationDataInternal(data);
		}		
		public boolean loadRegistrationData(String uniqueID) {
			if (uniqueID.equals("1")) {return loadRegistrationDataInternal("EHS-ES1000-P00-00ZZ,End House Software,endhousesoftware,05/06/16,21:26,endhousesoftware");} // running on the real internet
			
			String data = "";
//			if (getUseDatabase()) {
				data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegSerial,sysEHSRegName,sysEHSRegPassword,sysEHSRegLogonDate,sysEHSRegLogonTime,sysEHSRegUserName FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUnique='"+uniqueID+"' AND sysEHSRegActive=1","");
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegLogonDate='"+supportFunctions.currentShortDate()+"' WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUnique='"+uniqueID+"'","");
				supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegLogonTime='"+supportFunctions.currentShortTime()+"' WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUnique='"+uniqueID+"'","");
				//TRACE("LRD:"+data,4);
				if (data.length() == 0) { // user not yet registered
					//TRACE("LRD:User not yet registered",4);
					return false;
				} 
//			}
				
			return loadRegistrationDataInternal(data);
		}
		public boolean loadRegistrationDataInternal(String data) {
//				 if (getUseDatabase()) {
				  Vector v = supportFunctions.splitIntoTokens(data);
				  if (v.size() > 0) {
					  lastLogonDetails = (String)v.elementAt(3) + " - " + (String)v.elementAt(4);
				  } else {
					  lastLogonDetails = supportFunctions.currentDate() + " - " + supportFunctions.currentTime();
				  }
				  setUserID((String)v.elementAt(5));
				  if (data.startsWith("EHS")) {
				  	setRegistrationData((String)v.elementAt(1),(String)v.elementAt(0));
					userLogOn(getUserID());
					return true;
				  }
//			   } else {
//			   	 char[] buf = new char[4096];
//			   	 try {
//				 	 String name = ""; // set to username at some point !!!
//					 int num = 0;
//					 File f = new File(appName+name+".lic");
//				 	 if (!f.exists()) {return false;}
//				 	 FileReader in = new FileReader(f);
//					 if (in != null) {
//					 	num = in.read(buf);
//					 	in.close();
//					}
//					String s = new String(buf,0,num);
//					Vector v = supportFunctions.splitIntoTokens(s);
//					if (v.size() != 2) {return false;}
//					setRegistrationData((String)v.elementAt(1),(String)v.elementAt(0));
//					return true;
//				 } catch (Exception e) {e.printStackTrace();}
//			   }
			   
			   clearRegistrationData();
			   return false;
		}
		public void logOff() {
			userLogOff(getUserID());
			clearRegistrationData();
		}
		public void userLogOn(String userid) {;}
		public void userLogOff(String userid) {;}
		panelDialog helpDlg = null;
		public void populateChoice(Choice c,Vector v) {
			c.removeAll();
			for (int i=0;i<v.size();i++) {
				c.addItem((String)v.elementAt(i));
			}
		}
		public void displayFAQ(String product) {
			JTextArea ta = new JTextArea("",8,40);
			ta.setBackground(new Color(255,255,170));
			ta.setWrapStyleWord(true);
			JPanel p = new JPanel();
			JScrollPane sp = new JScrollPane(ta);
			p.add(sp);
			supportFunctions.displayTextFile(exFAQFile,ta);
			supportFunctions.displayPanelDialog(null,p,product + " - FAQ");
		}
		public void registerUser() {registerUser(false);}
		public void registerUser(boolean bSkipLogon) {
			if (getUserRegistered()) {return;}

			boolean bRegistered;
			int id = 1;
			if (bSkipLogon) {
				bRegistered = loadRegistrationDataInternal("EHS-ES1000-P00-00ZZ,End House Software,endhousesoftware,05/06/16,21:26,endhousesoftware");
			} else {
				id = supportFunctions.getMachineUniqueID("../" + appDirectory);
				bRegistered = loadRegistrationData(String.valueOf(id));				
			}
			
			if (!bRegistered) {
				Vector v = supportFunctions.splitIntoTokens(supportFunctions.displayLogonDialog(),",");
				if (v.size() == 2) {
					String data = supportFunctions.getDBConn().executeSQLQuery("SELECT sysEHSRegID FROM sysehsregistrations WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegUserName='"+(String)v.elementAt(0)+"' AND sysEHSRegPassword='"+(String)v.elementAt(1)+"' AND sysEHSRegActive=1","");
					if (data.length() != 0) {
						supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegUnique="+id+" WHERE sysEHSRegID="+data,"");
						registerUser();
						return;
					} else {
						supportFunctions.displayMessageDialog(null,"Logon failure, will continue as unregistered.");
					}
				}
			}
			
			if (!ehsConstants.bRunAppWithGUI) {return;}
			
			if (splashJPG.equals("") && !bRegistered) {
				JOptionPane.showMessageDialog(null,"This copy of "+ getAppName() + " is unregistered. Please see website to obtain a serial number.",getAppName(),JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (splashJPG.equals("")) {
					JOptionPane.showMessageDialog(null,getSingleLineInfoText(),getAppName(),JOptionPane.INFORMATION_MESSAGE);
				} else {
					int major = getMajorVersionNumber();
					int minor = getMinorVersionNumber();
					String bt1 = "Unregistered";
					String bt2 = "Serial Number: ";
					if (getUserRegistered()) {
						bt1 = getUserName();
						bt2 = bt2 + getSerialNumber();
					}
					splashScreenDialog d = new splashScreenDialog(null,getAppName(),splashJPG,"Version "+String.valueOf(major)+"."+String.valueOf(minor),bt1,bt2,15);
				}
			}
		}
		public void setRegistrationData(String name,String serial) {
			userName = name;
		  	userRawSerialNumber = serial;
			userSerialNumber = serial.substring(0,12) + "xx-xxxx";
			if (serial.charAt(11) == 'T') {
				numAttemptsLeft = Integer.parseInt(serial.substring(12,14));
				if (numAttemptsLeft == 0) {
					supportFunctions.displayMessageDialog(null,"Sorry your trail licence has expired.");
					supportFunctions.mail(ehsConstants.registrationEmail,"Licence Expiry","A trail licence for "+name+" has expired.");
					clearRegistrationData();
					return;
				} else {
					numAttemptsLeft--;
					String tmp = String.valueOf(numAttemptsLeft);
					if (tmp.length() == 1) {tmp = "0" + tmp;}
					String newValCode = serial.substring(0,12) + tmp + serial.substring(14);
					supportFunctions.displayMessageDialog(null,"You have " + String.valueOf(numAttemptsLeft) + " runs left on your trail licence.");
					//if (getUseDatabase()) 
						{supportFunctions.getDBConn().executeSQLQuery("UPDATE sysehsregistrations SET sysEHSRegSerial='" + newValCode +"' WHERE sysEHSRegProduct='"+appName+"' AND sysEHSRegName='"+name+"'","");}
				}
			}
			saveRegistrationData();
			initUserCredit();
		}
		public boolean getUserRegistered() {
			if(userName.equals("unregistered")) {
				return false; 
			} else {
				return true;
			}
		}
		public String getGitVersionInfoString() {return gitVersionInfo.substring(3,gitVersionInfo.length()-3);}
		public String getBuildString() {return buildDate.substring(3,buildDate.length()-3);}
		public String getframeworkBuildString() {return frameworkBuildDate.substring(3,frameworkBuildDate.length()-3);}
		public String getBuildNumber() {return getBuildString().substring(getBuildString().lastIndexOf(" ")+1);}
		public void patchBuildNumber() {
			appVersion = appVersion.substring(0,6) + String.format("%04d",Integer.parseInt(getBuildNumber())) + appVersion.substring(10);
		}
    		public void displayInfoText() {
			supportFunctions.displayMessageDialog(null,getSingleLineInfoText());
		}
		public void displayAboutBox() {
			ActionListener regTask = new ActionListener() {
      		   public void actionPerformed(ActionEvent evt) {
			   		  registerUser();
			   }
			};
  			JButton regBut = new JButton("Register");
			regBut.addActionListener(regTask);
			   Object[] sels = {"Ok",regBut};
			   int ret = JOptionPane.showOptionDialog(null,getSingleLineInfoText(),getAppName(),JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,sels,sels[0]);
		}
	}
