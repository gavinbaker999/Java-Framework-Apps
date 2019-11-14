import java.lang.System;
import java.awt.Color;

public class ehsConstants {
	protected static boolean	bRunAppWithGUI = false;
	protected static final String		registrationEmail="endhousesoftware999@gmail.com";
	protected static final String		adminEmail="endhousesoftware999@gmail.com";
	protected static boolean	bRemoteHosted = false;

	protected	static final int		windowXMax = 1600;
	protected	static final int		windowYMax = 1200;
	protected	static int				dcMaxX = windowXMax;
	protected	static int				dcMaxY = windowYMax;
	protected	static final int		dcGridSpaceX = 20;
	protected	static final int		dcGridSpaceY = 20;
	public		static final int		transTableNoJump = 99;
	protected 	static String			rotateJPG = "../classes/common/rotate.png";
	protected 	static String 			machineID = "";
	protected	static String			applicationName = "";

	protected 	static Color navy = new Color(0,0,128);
	protected 	static Color purple = new Color(128,0,128);
	protected 	static Color maroon = new Color(128,0,0);
	protected 	static Color lightyellow = new Color(255,255,170);
	protected 	static String[] colors = {"black","blue","green","red","navy","purple","maroon","yellow","white","custom1","custom2"};
	protected 	static Color[] colorCodes = {Color.black,Color.blue,Color.green,Color.red,navy,purple,maroon,Color.yellow,Color.white,Color.white,Color.white};

	protected 	static int	ttDIWidth = 125;
	protected	static int	ttDIHeight = 30;

	protected	static final int		strPadLeft = 0;
	protected 	static final int		strPadRight = 1;
	
	protected	static final int		dcTypeLine = 0;
	protected	static final int		dcTypeRect = 1;
	protected	static final int		dcTypeElispe = 2;
	protected	static final int		dcTypeImage = 3;
	protected	static final int		dcTypeText = 4;
	protected	static final int		dcTypeSelect = 5;
	protected	static final int		dcTypeConnector = 6;
	protected	static final int		dcTypeTextBox = 7;
	protected	static final int		dcTypeSwitchGCSheet = 8;
	protected	static final int		dcTypeTransTable = 9;
	protected	static final int		dcTypeBuiltInMaxId = 9;

	protected	static final int		aviRHS = 1;
	protected	static final int		aviLHS = -1;
	protected	static final int		MaxX = 300;
	protected	static final int		numDirAvis = 5;	
	protected	static final int		aviUser = 0;
	protected	static final int		aviTest = 1;
	protected	static final int		aviGlobal = 2;
	protected	static final int		chatAVISizeX = 73;
	protected	static final int		chatAVISizeY = 70;

	protected	static final int		noScaleX = -1;
	protected	static final int		noScaleY = -1;

	public 		static String		dbHost = "localhost";
	public		static String		dbName = System.getenv("LOCALDBNAME");
	public 		static String		dbUser = System.getenv("LOCALDBUSER");
	public		static String		dbPassword = System.getenv("LOCALDBPASSWORD");

    public static String COLLAPSIBLE_AREA_ELEMENT="collapsible_area_element";
}
