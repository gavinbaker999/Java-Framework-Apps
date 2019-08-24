-- --------------------------------------------------------

--
-- Table structure for table `chatvariables`
--

CREATE TABLE `chatvariables` (
  `chatVariableID` int(11) NOT NULL,
  `chatVarName` varchar(200) NOT NULL DEFAULT '',
  `chatVarValue` varchar(200) NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds variables for Rapier Chat';

--
-- Dumping data for table `chatvariables`
--

INSERT INTO `chatvariables` (`chatVariableID`, `chatVarName`, `chatVarValue`) VALUES
(3260, 'UD1000Gavin Bakermenubar', '0'),
(3261, 'WB1000Gavin Bakermenubar', '0');

-- --------------------------------------------------------

--
-- Table structure for table `ehswebringitem`
--

CREATE TABLE `ehswebringitem` (
  `ehsWebRingItemID` int(11) NOT NULL,
  `ehsWebRingItemName` varchar(255) NOT NULL,
  `ehsWebRingItemDescription` varchar(255) NOT NULL,
  `ehsWebRingItemImageURL` varchar(255) NOT NULL,
  `ehsWebRingItemSiteURL` varchar(255) NOT NULL,
  `ehsWebRingItemWebRingName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Holds details of all sites in End House Software web rings';

-- --------------------------------------------------------

--
-- Table structure for table `ehswebrings`
--

CREATE TABLE `ehswebrings` (
  `ehsWebRingID` int(11) NOT NULL,
  `ehsWebRingName` varchar(255) NOT NULL,
  `ehsWebRingDescription` varchar(255) NOT NULL,
  `ehsWebRingImageURL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Hold details of all End House Software web rings';

--
-- Dumping data for table `ehswebrings`
--

INSERT INTO `ehswebrings` (`ehsWebRingID`, `ehsWebRingName`, `ehsWebRingDescription`, `ehsWebRingImageURL`) VALUES
(1, 'End House Software', 'End House Software sites and applications', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `hdlwbforces`
--

CREATE TABLE `hdlwbforces` (
  `hdlWBForcesID` int(11) NOT NULL,
  `hdlWBForcesEntity` varchar(100) DEFAULT NULL,
  `hdlWBForcesName` varchar(100) DEFAULT NULL,
  `hdlWBForcesTime` int(11) DEFAULT NULL,
  `hdlWBForcesValue` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds HDL Work Bench Forces';

-- --------------------------------------------------------

--
-- Table structure for table `hdlwbmappings`
--

CREATE TABLE `hdlwbmappings` (
  `hdlWBMappingsID` int(11) NOT NULL,
  `hdlWBMappingsEntity` varchar(100) DEFAULT NULL,
  `hdlWBMappingsSignalName` varchar(100) DEFAULT NULL,
  `hdlWBMappingsProcessName` varchar(100) DEFAULT NULL,
  `hdlWBMappingsSubEntity` varchar(100) DEFAULT NULL,
  `hdlWBMappingsSubSignalName` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds HDL Work Bench Process-Signal Mappings';

-- --------------------------------------------------------

--
-- Table structure for table `hdlwbparameters`
--

CREATE TABLE `hdlwbparameters` (
  `hdlWBParametersID` int(11) NOT NULL,
  `hdlWBParametersEntity` varchar(200) NOT NULL,
  `hdlWBParametersName` varchar(200) NOT NULL,
  `hdlWBParametersType` varchar(200) NOT NULL,
  `hdlWBParametersScope` varchar(200) NOT NULL,
  `hdlWBParametersPosition` int(11) NOT NULL DEFAULT '1',
  `hdlWBParametersMode` enum('in','out','inout') NOT NULL DEFAULT 'in',
  `hdlWBParametersDefault` varchar(200) NOT NULL,
  `hdlWBParametersLB` int(11) NOT NULL DEFAULT '0',
  `hdlWBParametersUB` int(11) NOT NULL DEFAULT '0',
  `hdlWBParametersReturn` int(11) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hdlwbprocesses`
--

CREATE TABLE `hdlwbprocesses` (
  `hdlWBProcessesID` int(11) NOT NULL,
  `hdlWBProcessesEntity` varchar(100) DEFAULT NULL,
  `hdlWBProcessesName` varchar(100) DEFAULT NULL,
  `hdlWBProcessesSensList` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds HDL Work Bench Processeses';

-- --------------------------------------------------------

--
-- Table structure for table `hdlwbsignals`
--

CREATE TABLE `hdlwbsignals` (
  `hdlWBSignalsID` int(11) NOT NULL,
  `hdlWBSignalsEntity` varchar(100) NOT NULL,
  `hdlWBSignalsName` varchar(100) NOT NULL,
  `hdlWBSignalsValue` varchar(100) NOT NULL DEFAULT '0',
  `hdlWBSignalsOldValue` varchar(100) DEFAULT '0',
  `hdlWBSignalsPort` int(11) NOT NULL DEFAULT '0',
  `hdlWBSignalsUser` varchar(100) NOT NULL,
  `hdlWBSignalsType` varchar(100) DEFAULT NULL COMMENT 'Type of signal e.g. bit',
  `hdlWBSignalsDefination` enum('signal','variable','constant') NOT NULL DEFAULT 'signal',
  `hdlWBSignalsDelay` int(11) DEFAULT '0',
  `hdlWBSignalsMode` enum('in','out','inout','linkage','bus','buffer') NOT NULL DEFAULT 'in',
  `hdlWBSignalsLB` int(11) DEFAULT '0',
  `hdlWBSignalsUB` int(11) DEFAULT '0',
  `hdlWBSignalsScope` varchar(50) NOT NULL DEFAULT 'global',
  `hdlWBSignalsLR` int(11) DEFAULT '0' COMMENT 'Signals lower range limit',
  `hdlWBSignalsUR` int(11) DEFAULT '0' COMMENT 'Signals upper range limit',
  `hdlWBSignalsAfterValue` varchar(100) DEFAULT NULL COMMENT 'Holds the signal value to be set for the AFTER keyword',
  `hdlWBSignalsSubScope` varchar(50) DEFAULT NULL,
  `hdlWBSignalsChanged` int(11) DEFAULT '0',
  `hdlWBSignalsChangeTime` int(11) NOT NULL DEFAULT '0' COMMENT 'Holds the time slice that the signal changed in',
  `hdlWBSignalsEventTime` int(11) NOT NULL DEFAULT '-1',
  `hdlWBSignalsActiveTime` int(11) NOT NULL DEFAULT '-1',
  `hdlWBSignalsOneShot` int(11) DEFAULT '0',
  `hdlWBSignalsWaitCondition` varchar(100) DEFAULT 'none',
  `hdlWBSignalsHistory` longtext,
  `hdlWBSignalsArrayValues` varchar(2000) DEFAULT NULL,
  `hdlWBSignalsStringLR` varchar(1000) DEFAULT NULL,
  `hdlWBSignalsStringUR` varchar(1000) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `linkscatdescriptions`
--

CREATE TABLE `linkscatdescriptions` (
  `catID` int(11) NOT NULL,
  `catName` varchar(100) NOT NULL DEFAULT '',
  `catFromCat` varchar(100) NOT NULL DEFAULT '',
  `catDescription` varchar(200) NOT NULL DEFAULT '',
  `catFromName` varchar(100) NOT NULL DEFAULT '',
  `catFromEmail` varchar(100) NOT NULL DEFAULT '',
  `catDateEntered` varchar(50) NOT NULL DEFAULT '',
  `catTimeEntered` varchar(50) NOT NULL DEFAULT '',
  `catActive` int(11) NOT NULL DEFAULT '1'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds details of all the cats in the links database';

--
-- Dumping data for table `linkscatdescriptions`
--

INSERT INTO `linkscatdescriptions` (`catID`, `catName`, `catFromCat`, `catDescription`, `catFromName`, `catFromEmail`, `catDateEntered`, `catTimeEntered`, `catActive`) VALUES
(2, 'Celebrities', 'rootcat', 'Celebrities', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(3, 'Frequently Asked Questions', 'rootcat', 'Frequently Asked Questions', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(4, 'Free Stuff', 'rootcat', 'Free Stuff', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(5, 'Games', 'rootcat', 'Games', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(6, 'Graphics, Fonts And Sounds', 'rootcat', 'Graphics, Fonts And Sounds', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(7, 'Technical Support', 'rootcat', 'Technical Support', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(8, 'Comedy', 'rootcat', 'Comedy', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(9, 'Computer Journals', 'rootcat', 'Computer Journals', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(10, 'Kids', 'rootcat', 'Kids', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(11, 'Online Newspapers and Magazines', 'rootcat', 'Online Newspapers and Magazines', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(12, 'Movies, Flims, Television', 'rootcat', 'Movies, Flims, Television', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(13, 'Newsletters and Ezines', 'rootcat', 'Newsletters and Ezines', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(14, 'Finding Email Addresses and Phone Numbers', 'rootcat', 'Finding Email Addresses and Phone Numbers', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(15, 'Web Site Promotion', 'rootcat', 'Web Site Promotion', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(16, 'Words and Puzzles', 'rootcat', 'Words and Puzzles', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(17, 'Web User Services', 'rootcat', 'Web User Services', 'Gavin Baker', 'endhousesoftware@hotmail.com', '2007-01-01', '09:00:00', 1),
(61, 'rtertretret', 'rootcat', '', 'Gavin Baker', 'endhousesoftware999@gmail.com', '2018-10-21', '11:27:49', 0);

-- --------------------------------------------------------

--
-- Table structure for table `linkslinkdescriptions`
--

CREATE TABLE `linkslinkdescriptions` (
  `linkID` int(11) NOT NULL,
  `linkURL` varchar(200) NOT NULL DEFAULT '',
  `linkDescription` varchar(200) NOT NULL DEFAULT '',
  `linkDateEntered` varchar(50) NOT NULL DEFAULT '',
  `linkTimeEntered` varchar(50) NOT NULL DEFAULT '',
  `linkEnteredBy` varchar(100) NOT NULL DEFAULT '',
  `linkCat` varchar(100) NOT NULL DEFAULT '',
  `linkRating` int(11) NOT NULL DEFAULT '0',
  `linkActive` int(11) NOT NULL DEFAULT '1',
  `linkInactiveReason` varchar(200) DEFAULT NULL,
  `linkType` varchar(100) NOT NULL DEFAULT 'http'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds details of all links in links database';

--
-- Dumping data for table `linkslinkdescriptions`
--

INSERT INTO `linkslinkdescriptions` (`linkID`, `linkURL`, `linkDescription`, `linkDateEntered`, `linkTimeEntered`, `linkEnteredBy`, `linkCat`, `linkRating`, `linkActive`, `linkInactiveReason`, `linkType`) VALUES
(1, 'http://www.fatdogexchange.com', ' Fatdog Exchange', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(2, 'http://www.virtuous.co.uk/fantasy/index.htm', ' Fantasy Celebs', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(3, 'http://www.sofcom.com/lammens/bill.htm', ' The Bill Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(4, 'http://www.sunhill.co.uk/', ' The Bill Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(5, 'http://www.sunhill.co.uk/galleries/polly/pics1.asp', '  Polly Page Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(6, 'http://lavender.fortunecity.com/dolls/125/daniela.html', ' Daniela Denby Ashe Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(7, 'http://www.babemania.com/', ' Babe Mania', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(8, 'http://www2.xtdl.com/~joseph//wgallery.html', ' Sabrina Lloyd Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(9, 'http://members.tripod.com/~slloyder/', ' Sabrina Lloyd Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(10, 'http://assembly.nerdworld.com/assembly.asp?assemblyid=1490', ' Sabrina Lloyd Page 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(11, 'http://www.mclaughlin83.freeserve.co.uk/', ' Sabrina Lloyd Page 4', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(12, 'http://mudpuppy.simplenet.com/', ' Mud Puppy', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(13, 'http://www.stargalaxy.com/', ' Start Galaxy', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(14, 'http://www.thermallance.demon.co.uk/acenight.html', ' Ace Night', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(15, 'http://dspace.dial.pipex.com/town/road/xgj15/sapage.htm', '  Just Ace Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(16, 'http://dspace.dial.pipex.com/finxy/sophiealdred.htm', '  Sophie Aldred', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(17, 'http://freespace.virgin.net/darren.b/pics/celebs.htm', '  British Celebs', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(18, 'http://www.fansites.com/', '  Fansites Celebs', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(19, 'http://www.absolutecelebrities.com/', ' Absolute Celebs', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(20, 'http://www.geocities.com/Hollywood/Agency/8573/YHPG/index.html', ' Young Hollywood Picture Gallery', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(21, 'http://www.teenlusts.com/', ' Teen Stars', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(22, 'http://www.celebritypictures.com/', ' Celebrity Pictures', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(23, 'http://www.famousbabes.com/', ' Famous Babes', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(24, 'http://www.xplosiveweb.com/banana/', ' Banana Links', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(25, 'http://www.geocities.com/Hollywood/3142/babe.htm', ' The Complete Babe Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(26, 'http://www.parkermovies.com', ' S&P Movie Market', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(27, 'http://www.sceneone.co.uk', ' Scene One', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(28, 'http://www.planetx.com/~sonja/btvsurls/btvsurls.html', ' Buffy the Vampire Slayer Links', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(29, 'http://www.redheadclub.com', ' The Red Head Club', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(30, 'http://www.c-cafe.com/index.html', ' Celebrity Cafe', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(31, 'http://tv.cream.org', ' TV Cream', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(32, 'http://www.uktv.com/', ' The British Television Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(33, 'http://www.classic-tv.com', ' Classic TV', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(34, 'http://www.tvbabes.com/uk.html', ' TV Babes', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(35, 'http://photogallery.simplenet.com/', ' Photo Gallery Of Actresses', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(36, 'http://www.phil999.demon.co.uk/links.htm', ' More Actresses', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(37, 'http://dialspace.dial.pipex.com/town/plaza/hq07/', ' Even More Actresses', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(38, 'http://web.city.ac.uk/~aa169/totty.html', ' Yet More Actresses', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(39, 'http://www.celebritycore.com', ' The Celebrity Core (18+)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(40, 'http://www.starbuzz.com', ' The Star Buzz', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(41, 'http://welcome.to/celebpics', ' Welcome To Celebrity Pictures', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(42, 'http://vinnie.simplenet.com/celebrity/index.html', ' The Celebrity Link Directory', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(43, 'http://www.starseeker.com', ' Star Seeker', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(44, 'http://emporium.simplenet.com/home.htm', ' SimpleNet Celebs Pictures', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(45, 'http://www.lairofluxlucre.com', ' Fake Celebs Pictures', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(46, 'http://home.earthlink.net/~captnifty', ' More Celeb Fake Pictures', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(47, 'http://www.mgal.com/links/celeb.html', ' Celebs Home Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(48, 'http://www.rrc-bbs.virtual-space.com/index.htm', ' More Celebs', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(49, 'http://www.pixelvisionmedia.com/erika_eleniak', ' The Erika Eleniak Home Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(50, 'http://members.aol.com/fanforum/anna/', ' The Anne Kournikova Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(51, 'http://www.samsloan.com/anna.htm', ' The Anne Kournikova Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(52, 'http://vinnie.simplenet.com/celebrity/kournikovaa.html', ' The Anne Kournikova Home Page 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(53, 'http://www.geocities.com/Colosseum/Field/2701/ak-pics.html', ' The Anne Kournikova Home Page 4', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(54, 'http://www.geocities.com/Hollywood/2613', ' The Nicole Eggert Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(55, 'http://www.geocities.com/Paris/Rue/9442/nix.html', ' The Nicole Eggert Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(56, 'http://abone.superonline.com/~mtuncman/Eggert.htm', ' The Nicole Eggert Home Page 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(57, 'http://www.geocities.com/Hollywood/7972/index2k.html', ' The Nicole Eggert Home Page 4', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(58, 'http://members.tripod.com/~fnys/index.htm', ' The Nicole Eggert Home Page 5', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(59, 'http://www.netmonkey.com/1997/features/martina', ' The Martina Hingis Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(60, 'http://www.geocities.com/Colosseum/Field/8888/martina.html', ' The Martina Hingis Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(61, 'http://www.geocities.com/Hollywood/7347/', ' The Young Stars Link Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(62, 'http://members.aol.com/alternity/index.html', ' Certain Doubt', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(63, 'http://www.obkb.com/', ' OBKB', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(64, 'http://www.geocities.com/Hollywood/Theater/6588/', ' The Meredith Bishop Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(65, 'http://members.aol.com/yadwigha/index.html', ' The Meredith Bishop Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(66, 'http://www.tardis.ed.ac.uk/~ark/julia/', ' The Julia Sawalha Home Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(67, 'http://www.clairedanes.com', ' The Claire Danes Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(68, 'http://home.sol.no/~ssneen/solvi/danes.html', ' The Claire Danes Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(69, 'http://www2.ucsc.edu/people/ddiego/danes/', ' The Claire Danes Home Page 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(70, 'http://www.geocities.com/Hollywood/Boulevard/7184/', ' The Claire Danes Home Page 4', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(71, 'http://www.geocities.com/Hollywood/Hills/8486/claire.htm', ' The Claire Danes Home Page 5', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(72, 'http://www.geocities.com/Hollywood/Hills/8098/index.html', ' Joide Foster Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(73, 'http://www.tcp.com/~mary/foster.pfhtml', ' Joide Foster Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(74, 'http://members.tripod.com/~Melissa-Joan-Heart_2', ' Melissa Joan Heart Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(75, 'http://www.geocities.com/Hollywood/set/9788', ' Melissa Joan Heart Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(76, 'http://www.morrfin.demon.co.uk', ' Alicia Silverstone Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(77, 'http://www.geocities.com/Hollywood/Studio/2710/alicia.html', ' Alicia Silverstone Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(78, 'http://www.silverstone.org', ' Alicia Silverstone Home Page 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(79, 'http://www.netshopuk.co.uk/annafriel', ' Anna Friel Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(80, 'http://www.homage.ndirect.co.uk/anna_friel.htm', ' Anna Friel Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(81, 'http://www.sandra-bullock.org', ' Sandra Bullock Home Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(82, 'http://www.larisa.com', ' Larisa Oleynik Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(83, 'http://www.frontiernet.net/~hunter/larisa/larisa.htm', ' Larisa Oleynik Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(84, 'http://members.aol.com/gottaluvlo', ' Larisa Oleynik Home Page 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(85, 'http://www.geocites.com/TimesSquare/Alley/9934/helenb.html', ' Helen Baxendale Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(86, 'http://members.tripod.com/~baxendale/index.htm', ' Helen Baxendale Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(87, 'http://freespace.virgin.net/ross.bowerman/hb.html', ' Helen Baxendale Home Page 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(88, 'http://sr6.xoom.com/leewebsite/helenbaxendale/', ' Helen Baxendale Home Page 4', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(89, 'http://widgysoft.8m.com/helen1.htm', ' Helen Baxendale Home Page 5', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(90, 'http://widgysoft.8m.com/gillian_anderson_gallery.htm', ' Gillan Anderson Gallerys', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(91, 'http://widgysoft.8m.com/emma1.htm', ' Emma Noble Gallerys', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(92, 'http://members.spree.com/steve111/df/1.htm', ' Danielle Fishel Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(93, 'http://www.geocities.com/Hollywood/Lot/4490/', ' Danielle Fishel Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(94, 'http://www.netside.com/~bryces/', ' Danielle Fishel Home Page 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(95, 'http://www.amyjojohnson.net/', ' Amy Jo Johnson Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(96, 'http://www.escape.ca/~hootowl/amyjo/', ' Amy Jo Johnson Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(97, 'http://www.technomancer.com/~sgarrett/pink/', ' Amy Jo Johnson Home Page 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(98, 'http://members.tripod.com/~TCF/AmyJo-2.html', ' Amy Jo Johnson Home Page 4', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(99, 'http://www.geocities.com/TelevisionCity/8729/', ' Amy Jo Johnson Home Page 5', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(100, 'http://www.jennavonoy.com/index.shtml', ' Jenna von Oy Home Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(101, 'http://www.pkgibson.demon.co.uk/philippa.html', ' Philippa Forester Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(102, 'http://users.ox.ac.uk/~quee0434/', ' Philippa Forester Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(103, 'http://www.wp.com/88784/philnet.htm', ' Philippa Forester Home Page 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(104, 'http://mudpuppy.simplenet.com/f/forresterphilippa.htm', ' Philippa Forester Home Page 4', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(105, 'http://www.geocities.com/Hollywood/Screen/3350/', ' Hillary Tuck Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(106, 'http://www.angelfire.com/mo/HISTK', ' Hillary Tuck Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(107, 'http://www.getshrunk.go.com', ' Hillary Tuck Home Page 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(108, 'http://www.jenniegarth.com', ' Jennie Garth Home Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(109, 'http://www.barecelebs.com', ' More Celebs', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(110, 'http://www.celebrity-collection.com', ' Celebrity Collection', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(111, 'http://members.xoom.com/sven233/mainkatie.html', ' Katie Holmes', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(112, 'http://www.livetv.co.uk', ' Live TV', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(113, 'http://www.b-witched.com', ' B*Witched', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(114, 'http://www.moorland.force9.co.uk/ricci/', ' Christina Ricci Home Page 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(115, 'http://www.geocities.com/Hollywood/Heights/8657/', ' Christina Ricci Home Page 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(116, 'http://www.geocities.com/Hollywood/Set/8562/index.html', ' Christina Ricci Home Page 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(117, 'http://www.geocities.com/Hollywood/Hills/4550/', ' Christina Ricci Home Page 4', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(118, 'http://members.tripod.com/~hosea/ricci.htm', ' Christina Ricci Home Page 5', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(119, 'http://ms418qzh.ms.u-tokyo.ac.jp/~utcsa/links/World/ent/person/rchristina.html', ' Christina Ricci Home Page 6', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(120, 'http://www.mexi.net/MEXI/ACTRESS/chriri.html', ' Christina Ricci Home Page 7', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(121, 'http://www.geocities.com/Hollywood/Bungalow/1708/', ' Christina Ricci Home Page 8', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(122, 'http://www.nightworks.de/ricci/', ' Christina Ricci Home Page 9', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(123, 'http://www.bomis.com/rings/christinaricci/', ' Christina Ricci Home Page 10', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(124, 'http://www.laughingstar.com/Christina_Ricci/Christina_Ricci.dhtm/Christina_Ricci_5_181__jpg', ' Christina Ricci Home Page 11', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(125, 'http://dir.yahoo.com/entertainment/actors_and_actresses/ricci__christina/', ' Christina Ricci Home Page 12', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(126, 'http://members.xoom.com/mcrs/', ' Christina Ricci Home Page 13', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(127, 'http://www.looksmart.com/eus1/eus52213/eus156227/eus171947/eus300014/eus315002/eus300359/eus303302/r?comefrom=dogpile-eus303302', ' Christina Ricci Home Page 14', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(128, 'http://www.schitzo.com/ricci/', ' Christina Ricci Home Page 15', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(129, 'http://www.c-ricci.freeserve.co.uk/index2.htm', ' Christina Ricci Home Page 16', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(130, 'http://www.christinaricci.co.uk/main.html', ' Christina Ricci Home Page 17', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(131, 'http://christinaricci.8m.com/', ' Christina Ricci Home Page 18', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(132, 'http://www.geocities.com/Broadway/Alley/1932/crindex.html', ' Christina Ricci Home Page 19', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(133, 'http://homepages.tig.com.au/~simonh/ricci/framemain.html', ' Christina Ricci Home Page 20', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(134, 'http://www.geocities.com/Hollywood/Hills/4504/', ' Christina Ricci Home Page 21', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(135, 'http://www.geocities.com/SiliconValley/Way/6790/ricci.html', ' Christina Ricci Home Page 22', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(136, 'http://www.geocities.com/SiliconValley/Way/6790/ricci.html', ' Christina Ricci Home Page 22', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(137, 'http://kosh.prostokvashino.com/cr/topsites/topsites.html', ' Christina Ricci Home Page 23', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(138, 'http://www.geocities.com/Hollywood/Academy/6337/index1.htm', ' The Celeb House', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(139, 'http://web.singnet.com.sg/~bmann/', ' The Celeb Gateway', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(140, 'http://www.gail-porter.co.uk', ' Gail Porter Online', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(141, 'http://members.xoom.com/gailporterx', ' The Gail Porter Experience', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(142, 'http://www.gailporter.net', ' Gail Porter.net', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(143, 'http://www.gail-porter-heaven.co.uk', ' Gail Porter Heaven', '2007-01-01', '09:00:00', 'Gavin Baker', 'Celebrities', 5, 1, '', 'http'),
(144, 'http://www.buzzbrain.com/', ' Buzz Brain', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(145, 'http://www.vmyths.com/', ' Virus Myths', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(146, 'http://www.griffintechnology.com/', ' Griffin Technology', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(147, 'http://www.inquiry.com/', ' Inquiry', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(148, 'http://www.winfinder.com/', ' WinFinder Resource', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(149, 'http://system-failure.com/', ' System Failure', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(150, 'http://unix.miningco.com/', ' Focus on UNIX', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(151, 'http://www.cyberwalker.net', ' Cyberwalker Computer Advice', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(152, 'http://ntgamepalace.8m.com', ' NT Game Place', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(153, 'http://www.supportsource.com/index22.htm', ' Support Source 2K', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(154, 'http://www.software-sleuth.com/', ' Software Sleuth', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(155, 'http://www.faqs.org', ' The Mother of FAQs', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(156, 'http://www.techtales.com', ' A Little Light Humor', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(157, 'http://www.spamm.net/', ' Spamming Tales', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(158, 'http://L62.csm.port.ac.uk', ' Online Computer And Maths Tests', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(159, 'http://www.videodiscovery.com/vdyweb/dvd/dvdfaq.html', ' DVD FAQ', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(160, 'http://www.iis.fhg.de/amm/techinf/layer3/layer3faq/index.html', ' MPEG Audio Layer 3 FAQ', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(161, 'http://drogo.cselt.stet.it/mpeg/#MPEG_FAQs', ' The Official MPEG FAQs', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(162, 'http://www.faqs.org/faqs/compression-faq/part2/section-2.html', ' MPEG Compression FAQs', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(163, 'http://www.mpeg1.de/mpegfaq/', ' The MPEG FAQs', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(164, 'http://www.magicnet.net/~fortna/email_1.html', ' Guide To All Things Offline', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(165, 'http://www.cnet.com/Resources/Tech/Advisers/Error/index.html', ' What Does That Error Message Mean?', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(166, 'http://www.netlingo.com', ' So What Does The Net Lingo Mean?', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(167, 'http://www.ufaq.com', ' The Netscape FAQ', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(168, 'http://www.ufaq.org', ' Netscape FAQ', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(169, 'http://www.experts-exchange.com', ' Experts-Exchange - Get Your Questions Answered', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(170, 'http://www.codenaked.com', ' Code Naked Resource', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(171, 'http://www.systeminternals.com', ' System Internals', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(172, 'http://www.concertric.net/~fgalbrai/howto.shtml', ' MFC Tips and Tricks', '2007-01-01', '09:00:00', 'Gavin Baker', 'Frequently Asked Questions', 5, 1, '', 'http'),
(173, 'http://www.sonic.net/webcentral/freelinks.html', ' Free Links', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(174, 'http://www.bestfreebies.com', ' Freebies On The Net', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(175, 'http://www.totallyfreestuff.com', ' Totaly Free Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(176, 'http://www.freefever.com/', ' Free Fever', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(177, 'http://www.thefreesite.com', 'The Free Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(178, 'http://www.freebiedirectory.com', ' The Freebie Directory', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(179, 'http://www.titchmarsh.com', ' Titchmarsh.Com Free Stuff', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(180, 'http://www.freecenter.com', ' Free Center', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(181, 'http://www.giveawaycentral.com', ' GiveAwayCenteral Free Stuff', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(182, 'http://www.freeonweb.com', ' Free Stuff On The Web', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(183, 'http://www.freestuffcentral.com', ' Free Stuff Central', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(184, 'http://wwww.thefreemail.com', ' The Free Mail', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(185, 'http://woow.findhere.com', ' The Freebie Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Free Stuff', 5, 1, '', 'http'),
(186, 'http://xebia.cjb.net/', ' Un-Named Gamer Creators Organization', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(187, 'http://151.197.84.23/rob/games/', ' Ultimate Game Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(188, 'http://www.candystand.com', ' Candy Stand', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(189, 'http://www.chessed.com/', ' Online Chess', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(190, 'http://www.playsite.com', ' Play Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(191, 'http://www.crimescene.com', ' Crime Scene', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(192, 'http://www.contestguide.com', ' Contest Guide', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(193, 'http://www.barrysworld.com', ' Barrysworld', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(194, 'http://www.videogames.com', ' Video Games', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(195, 'http://www.gameguides.com', ' Game Guides', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(196, 'http://www.gamingsource.com/psxsource/index.html', ' PSX Cheats Source', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(197, 'http://www.goecities.com/TimesSquare/Bunker/3241/', ' The Red Alert Bunker', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(198, 'http://monkeyweb.tsx.org/', ' The Online Home of Monkey Island 1, 2 and 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(199, 'http://www.thepocket.com/selfexe.htm', ' Pocket Fun', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(200, 'http://www.micropose.com/gamesdesign/magic/magic.html', ' Magic by MicroPose', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(201, 'http://www.Wizards.com/Magic/Welcome.html', ' Magic Card Game Home Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(202, 'http://www.magicparadise.com/', ' Magic Paradise', '2007-01-01', '09:00:00', 'Gavin Baker', 'Games', 5, 1, '', 'http'),
(203, 'http://www.fontsandthings.com/', ' Fonts and Things', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(204, 'http://www.typeresource.com/', ' Type Resource', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(205, 'http://www.aaascreensavers.com/', ' AAA Screen Savers', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(206, 'http://www.freegraphicland.com/', ' Free Graphic Land', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(207, 'http://www.animation-central.com/', ' Animation Central', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(208, 'http://www.logowonders.com/', ' Logo Wonders', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(209, 'http://www.webshots.com/', ' Web Shots', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(210, 'http://3dtextmaker.com/', ' 3D Text Maker', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(211, 'http://www.screensaverdirectory.com', ' Screen Saver Directory', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(212, 'http://www.webgfx.ch/start.htm', ' WebGFX', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(213, 'http://www.web-animator.com/', ' Web Animator', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(214, 'http://www.pro-soft.dk/htdocs/visualtubes/', ' PSP Visual Tubes', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(215, 'http://www.coolgraphics.com', ' Cool Graphics', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(216, 'http://thelinkz.w3.to/', ' Macromedia Shockwave Resources', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(217, 'http://www.wavhounds.com/', ' WAV Hounds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(218, 'http://www.wavcentral.com/', ' WAV Central', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(219, 'http://www.fontpool.com', ' The Font Pool', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(220, 'http://www.cyberbounty.com/ad?a=75&b=9999&c=1198', ' On Line Photo Album', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(221, 'http://grsites.com/textures', ' Absolute Backgrounds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(222, 'http://www.free-backgrounds.com', ' Free Backgrounds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(223, 'http://alts1.kodak.com/US/en/corp/playground/index.shtml', ' Picture Playground', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(224, 'http://www.flamingtext.com', ' Flaming Text', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(225, 'http://www.logowonders.com/', ' Logo Wonders', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(226, 'http://www.carmengraphics.com', ' Carmen Graphics', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(227, 'http://www.designs4free.com', ' Designs For Free', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(228, 'http://webpages.metrolink.net/~shoilman', ' Backgounds, etc.', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(229, 'http://www.wbshp.com/freestuff.html', ' Free Graphics, etc.', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(230, 'http://www.clipartguide.com', ' Clipart Guide', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(231, 'http://members.tripod.com/~joliepae/latternsplash.htm', ' Earthy Backgrounds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(232, 'http://surport.com/bydezign', ' By Dezign', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(233, 'http://www.christopher.org/NETWORK/freefilters/download.html', ' Photoshop Free Filters', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(234, 'http://www.ultrafractal.com', ' Ultra Fractal', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(235, 'http://squareonegraphics.com/index.html', ' Square One Graphics', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(236, 'http://www.focus-online.com', ' Focus Online', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(237, 'http://www.colormix.com', ' Color Mixer', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(238, 'http://www.quickbanner.com', ' Quick Banner Generator', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(239, 'http://www.ezskins.com', ' Customize Desktop', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(240, 'http://www.directtodave.com/ibs', ' Internet Bumper Stickers', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(241, 'http://www.graphics4free.com', ' Graphics For Free', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(242, 'http://www.3dtextmaker.com/', ' 3D Text Maker', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(243, 'http://www.wafu.netgate.net/3dlogo/e3dlogo.html', ' 3D Logo Generator', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(244, 'http://www.cooltext.com', ' The CoolText Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(245, 'http://www.technoweenie.com/graphics/', ' Technoweenie', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(246, 'http://www.mediaupgrade.com/', ' Media Upgrade', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(247, 'http://www.xdude.com/', ' The THX Digital Dude', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(248, 'http://astigmatic.com', ' Font Foundry', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(249, 'http://208.233.94.70/fontfairy', ' Font Fairy', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(250, 'http://www.mp3.com', ' mp3.com', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(251, 'http://www.mp3now.com/', ' MP3 Now', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(252, 'http://www.midifarm.com', ' MIDI Farm', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(253, 'http://www.dailywav.com', ' Daily WAV', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(254, 'http://www.clipart.com', ' Clipart.com', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(255, 'http://www.coversarchive.com', ' Cover Archive', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(256, 'http://www.coolarchive.com/buttons.cfm?parameter=main', ' Free Custom Buttons', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(257, 'http://www.kelleypc.com/archives/button.html', ' Kelley PC Button Maker', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(258, 'http://www.007fonts.com', ' 007Fonts and Custom Free Logos', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(259, 'http://www.paletteman.com', ' Palette Man', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(260, 'http://www.animfactory.com', ' Animation Factory', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(261, 'http://members.xoom.com/acidfonts/', ' Acid Fonts', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(262, 'http://www.setcity.com', ' Set City Graphics', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(263, 'http://www.1001freefonts.com', ' 1001 Free Fonts', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(264, 'http://www.ruku.com/index.html', ' PixArt', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(265, 'http://wdvl.internet.com/Authoring/Graphics/Tools/PSP/', ' Intro To Paint Shop Pro', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(266, 'http://www.grafx-design.com/psp_tut.html', ' Paint Shop Pro Tutorials', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(267, 'http://www.digital-foundry.com/index-paintshop.html', ' The Paint Shop Pro Foundry', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(268, 'http://www.northnet.com.au/~robrow/', ' The Hood', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(269, 'http://www.prodraw.com', ' ProDraw Your Graphics', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(270, 'http://www.i-us.com', ' The I-Us Graphics Store', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(271, 'http://www.tunes.com/', ' Tunes.Com', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(272, 'http://www.fontface.com/', ' Font Of The Day', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(273, 'http://www.coolarchive.com/', ' The Cool Archive', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(274, 'http://www.valkhorn.com/midi/', ' Valkhorm MIDI Resource', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(275, 'http://www.planetz.net/midi/', ' Planet MIDI - Free Sounds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(276, 'http://www.kevdebin.atlnet.com', ' Free Grafix', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(277, 'http://www.bigfoot.com/~Juile.Bakewell/', ' More Smooth Web Graphics', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(278, 'http://www.jaguarwoman.com', ' Jaguar Woman Graphics', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(279, 'http://www.gifoptimizer.com', ' The GIF Optimizier', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(280, 'http://www.iconbazaar.com', ' An Icon For Every Purpose', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(281, 'http://www.graphicslibrary.com', ' A Selection Of Buttons, Backgrounds, etc.', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(282, 'http://www.dsuper.net/~zaz/button/button.html', ' The Button Hole', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(283, 'http://www.safari.altavista.digital.com', ' Clipart and Image Search (Corbis Collection)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(284, 'http://www.rsub.com/typo', ' History Of Type Faces, etc.', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(285, 'http://builder.cnet.com/Graphics/Type', ' Fonts Tips...', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(286, 'http://www.pixelwarehouse.com/fonts/Fonts.htm', ' Pixel Warehouse Fonts', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(287, 'http://www.fontfreak.com', ' The Font Freak', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(288, 'http://www.webfonts.com', ' The Web Fonts Resource', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(289, 'http://www.fontface.com', ' Loads Of Fonts...', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(290, 'http://www.fontdiner.com', ' Loads More Fonts...', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(291, 'http://www.gifwizard.com', ' The GIF Wizard', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(292, 'http://www.servtech.com/~dougg/graphics/index.htmlA', ' Preparing Graphics For The Web', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(293, 'http://www.wpdfd.com', ' Web Page Design For Designers', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(294, 'http://www.jokewallpaper.com', ' Joke Wallpaper', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(295, 'http://www.futurenet.com/net/web/45sound/', ' Sound On Your Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(296, 'http://www.free-graphics.com', ' Loads Of Free Gaphics 1 (Free Graphics)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(297, 'http://www.kitgraphics.com', ' Loads Of Free Gaphics 2 (Kit Graphics)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(298, 'http://www.graphicsforum.com', ' Loads Of Free Gaphics 3 (Graphics Forum)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(299, 'http://www.gifcruncher.com', ' Faster Loading For Your GIF Graphics', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(300, 'http://www.webgfx.ch', ' Create Your Own Customised Logos', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(301, 'http://www.drawinghand.com', ' Intressing Screen Saver That Draws Art', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(302, 'http://pertsserver.cs.uiuc.edu/~hull/halftone/', ' Photoshop  Dither Filters', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(303, 'http://www.arttoday.com', ' Art Today', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(304, 'http://www.photoworks-digital.com', ' Transfer Your Images Onto Gifts', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(305, 'http://www.nagelphoto.mcmail.com', ' Edmund Nagele Photography', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(306, 'http://www.kodak.com', ' Kodak - The Yellow Gaint', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(307, 'http://www.netcreations.com/patternland', ' Free Background Patterns', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(308, 'http://www.jbarchuk.com/gifsplit/', ' Split GIFs into seperate images', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(309, 'http://www.zy.com', ' Create Great Looking Text', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(310, 'http://www.oceris.com/viewfont/default.htm', ' View Fonts', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(311, 'http://www.iridis.com/dsabljic/products.html', ' IC Fonts', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(312, 'http://www.theill.com/fs/default.asp', ' Font Selector', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(313, 'http://freeyellow.com/members5/gilly3/index.html', ' Graphics By Gilly', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(314, 'http://dewittindustries.com', ' Dewitt Industries', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(315, 'http://www.galtech.com', ' Gal Tech', '2007-01-01', '09:00:00', 'Gavin Baker', 'Graphics, Fonts And Sounds', 5, 1, '', 'http'),
(316, 'http://www.x86.org/intel.doc/IntelMotherBoards.html', ' Intel Motherboard & BIOS Specs Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(317, 'http://www.bugnet.com', ' Bug Net<', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(318, 'http://www.bxboards.com/', ' Mother Board Review', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(319, 'http://tsinc.simplenet.com/index.hts', ' PC Trouble Shooters', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(320, 'http://www.virtualdr.com', ' Virtual DR Technical Support', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(321, 'http://www.hardwarecentral.com', ' The Hardware Central', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(322, 'http://pages.prodigy.net/michael-santovec/techhelp.htm', ' Techincal Help', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(323, 'http://www.sbsdirect.com/fccenter.html', ' FCC Code Lookup', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(324, 'http://www.matisse.net/files/formats.html', ' Common Internet File Formats', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(325, 'http://www.networksolutions.com/cgi-bin/whois/whois', ' Domain Name Search', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(326, 'http://www.discovery.com/DCO/doc/1012/world/technology/internet/opener.html', ' Internet History', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(327, 'http://www.execpc.com/~marjb/term2.html', ' Internet Terms', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(328, 'http://pavilion.baynetworks.com/pavilion/depot.html', ' Internet Tour', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(329, 'http://info.ox.ac.uk/help/wwwfaq/index.html', ' World Wide Web FAQ', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(330, 'http://www.reach.com/matrix/', ' Welcome To The Matrix', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(331, 'http://hoohoo.ncsa.uiuc.edu/docs/tutorials/', ' NSCA Tutorials', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(332, 'http://www.pcmech.com', ' PC Mechanic', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http');
INSERT INTO `linkslinkdescriptions` (`linkID`, `linkURL`, `linkDescription`, `linkDateEntered`, `linkTimeEntered`, `linkEnteredBy`, `linkCat`, `linkRating`, `linkActive`, `linkInactiveReason`, `linkType`) VALUES
(333, 'http://www.compucare.com', ' Compucare Tech Support', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(334, 'http://www.fiu.edu/paverb01/advice.html', ' Phlip Gosset Tech Support', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(335, 'http://www.nowonder.com', ' No Wonder Tech Support', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(336, 'http://www.nua.ie/surveys', ' NUA Surveys', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(337, 'http://www.annoyances.org/win95', ' Win95 Annoyances', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(338, 'http://www.seekhelp.com', ' Do You Need Help?', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(339, 'http://www.helpanswers.com', ' Help Answers', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(340, 'http://www.helptalk.com', ' Help Talk', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(341, 'http://www.inference.com', ' Inference', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(342, 'http://www.naptop-notebook.com', ' Laptop-Notebook.com', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(343, 'http://www.pcguide.com', ' PC Guide', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(344, 'http://www.zdnet.com/zdhelp', ' ZDnet Help Channel', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(345, 'http://www.modemhelp.com', ' Help For Your Modem', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(346, 'http://www.allexperts.com', ' Ask The Experts', '2007-01-01', '09:00:00', 'Gavin Baker', 'Technical Support', 5, 1, '', 'http'),
(347, 'http://www.jokesinthemail.com', ' Jokes in the Mail', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(348, 'http://www.geocities.com/SoHo/9833/sandyarc.html', ' Sandy Lindsey Humor Archive', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(349, 'http://www.mediashower.com/zug', ' Zug', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(350, 'http://www.sfdt.com', ' Stick Figure Death Theatre', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(351, 'http://www.spinnwebe.com/dfc', ' Dysfunctional Family Circus', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(352, 'http://www.jokewallpaper.com', ' Joke Wallpaper', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(353, 'http://www.jokeland.com', ' Joke Land', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(354, 'http://www.humournet.com', ' Humour Net', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(355, 'http://www.comics-page.com', ' The Comics Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(356, 'http://www.emrich.net/toons/', ' Free Daily Cartoons', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(357, 'http://members.spree.com/gold9029', ' 1Moose Gold, A South Park Shopping Spree!', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(358, 'http://www.laughmail.com/', ' Laugh Mail', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(359, 'http://home.freeuk.net/frazzle', ' Frazzle', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(360, 'http://superbad.com/', ' Super Bad', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(361, 'http://www.thekiss.com/', ' The Kiss', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(362, 'http://www.garfield.com/', ' Garfield', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(363, 'http://www.toxiccustard.com/', ' Toxic Custard', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(364, 'http://humorarchive.webjump.com/', ' Humor Archive', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(365, 'http://www.sdsc.edu/~jeff/comics/Cornucopia.html', ' Comic Strip Cornucopia', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(366, 'http://members.xoom.com/mrflat/index.htm', ' FlatWeb - A Flat Eric Fan Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(367, 'http://www.phill.co.uk', ' British TV Comdey Resources', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(368, 'http://laughcenter.8m.com', ' Laugh Center', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(369, 'http://www.freeholes.com/joke/', ' Free Hole Jokes', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(370, 'http://www.comedycentral.com', ' Comedy Central', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(371, 'http://www.unitedmedia.com/comics', ' The Comic Zone', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(372, 'http://www.smeg.com/backwards/', ' Backwards', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(373, 'http://www.fertnel.com', ' Fertnel', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(374, 'http://www.ghg.enc.purdue.edu', ' George Goble (GHG)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(375, 'http://www.cybercheeze.com', ' Humor Archive', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(376, 'http://www.sunsite.unc.edu/Dave/drfun.html', ' The Doctor Fun Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(377, 'http://www.striaghtdope.com', ' Straight Dope', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(378, 'http://www.ojohaven.com/fun/shakespeareisms.html', ' Shakespeare (Fun)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(379, 'http://www.oraclehumor.com/', ' Oracle Humor', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(380, 'http://www.nervemag.com/', ' Nervazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(381, 'http://www.nerve.com', ' Romantic and Passionate Cultcha', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(382, 'http://mirsky.com/wow/', ' Mirsky - Worest Of The Web', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(383, 'http://www.microsnot.com', ' Microsnot Funny Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(384, 'http://www.kilo.net/tli/index.html', ' Laughing Internet', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(385, 'http://www.csn.ul.ie/~robert/road.html', ' Irish Road Humor', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(386, 'http://www.uproar.com', ' Uproar', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(387, 'http://www.jokes.com', ' The Jokes Zone', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(388, 'http://www.dilbert.com', ' The Dilbert Zone', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(389, 'http://www.aardman.com', ' Wallace and Gromit Zone', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(390, 'http://www.randomhouse.com/seussville/', ' The Cat in the Hat', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(391, 'http://www.online.co.uk/ed.bulter/humour', ' The Humour Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(392, 'http://www.verycrazy.com', ' Very Crazy', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(393, 'http://www.yukyuk.com', ' YukYuk', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(394, 'http://www.shockrave.com', ' Shockrave', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(395, 'http://pages.map.com/cottage', ' Adult Humor', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(396, 'http://www.geocities.com/Area51/Zone/8485/main.htm', ' Smilies Home Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(397, 'http://www.funny.co.uk', ' On-line Comedy', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(398, 'http://www.ymmv.com', ' Your Milage May Vary...', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(399, 'http://www.suck.com', ' It Sucks...', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(400, 'http://www.smacked.com', ' WwW.SmacKed.CoM', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(401, 'http://www.blackout.com', ' Blackout...', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(402, 'http://www.improb.com', ' Hot Air', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(403, 'http://www.crashsite.com', ' Have you Got the Guts?', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(404, 'http://www.hearsay.simplenet.com/translation', ' Roughly Translated', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(405, 'http://www.bluemountain.com', ' Online Greeting Cards', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(406, 'http://www.cartoonwavs.com', ' Cartoon WAV Files', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(407, 'http://www.thisistrue.com', ' This Is True', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(408, 'http://www.mentalstate.com', ' Puzzles and more puzzles', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(409, 'http://mob.nu/decker/e_goodie.htm', ' Mailgoodies and giveaways', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(410, 'http://members.tripod.com/Madtbone', ' Mother of All Excuses', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(411, 'http://www.truthortabloid.com/', ' Truth Or Tablod', '2007-01-01', '09:00:00', 'Gavin Baker', 'Comedy', 5, 1, '', 'http'),
(412, 'http://www.webtechniques.com', ' Web Techniques Journal', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(413, 'http://www.webdeveloper.com', ' Web Developer Journal', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(414, 'http://www.microsoft.com/developr/msj/', ' Microsoft Systems Journal', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(415, 'http://www.microsoft.com/developr/msj/', ' Microsoft Systems Journal', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(416, 'http://www.microsoft.com/developr/mind/', ' Microsoft Internet Developer Journal', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(417, 'http://www.wdj.com', ' Windows Developers Journal Web Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(418, 'http://www.ddj.com', ' Dr. Dobbs Journal Web Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(419, 'http://www.cuj.com', ' C/C++ Users Journal Web Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(420, 'http://www.vcdj.com', ' Visual C++ Online Magazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(421, 'http://www.pcxpert.co.uk', ' PCxpert Online Magazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(422, 'http://www.futurenet.com/pcplus', ' PCPlus Magazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(423, 'http://www.sdmagazine.com', ' Software Development Magazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(424, 'http://www.futurenet.com', ' FutureNet Magazine Group', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(425, 'http://computingnet.co.uk', ' Computing', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(426, 'http://www.computerweekly.co.uk', ' Computer Weekly', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(427, 'http://charlie.simplenet.com/vbc/vbchome.htm', ' Visual Basic Magazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Computer Journals', 5, 1, '', 'http'),
(428, 'http://www.disneybuzz.com/', ' Disney Buzz', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(429, 'http://www.technoteen.com/teen/index.html', ' Techno Teen', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(430, 'http://www.coloring.com', ' Coloring.com', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(431, 'http://www105.pair.com/free4kid/index.html', ' Free 4 Kids', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(432, 'http://www.warnerbros.com', ' Warner Bros', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(433, 'http://www.futuregamer.com', ' The Future Gamer', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(434, 'http://www.travelchannel.com', ' The Travel Channel', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(435, 'http://www.cyberpatrol.com/', ' Welcome To Cyber Patrol', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(436, 'http://www.microsys.com/616/default.htm', ' Route 6-16', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(437, 'http://www.4kids.com/~4kids/', ' 4Kids Treehouse', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(438, 'http://www.woodwind.com/mtlake/CyberKids/CyberKids.html', ' CyberKids Zine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(439, 'http://www.discovery.com', ' Discovery', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(440, 'http://www.disney.com', ' Disney', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(441, 'http://www.ee.surrey.ac.uk/Contrib/Entertainment/nickelodeon.html', ' Nickelodeon', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(442, 'http://www.clangers.co.uk', 'The Clangers', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(443, 'http://www.teenworld.com.my', ' TeenWorld Online', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(444, 'http://www.teengrrl.com', ' Teen Girl', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(445, 'http://www.kilpikonna.media.mit.edu:8001/power/homepage.html', ' The Power Rangers', '2007-01-01', '09:00:00', 'Gavin Baker', 'Kids', 5, 1, '', 'http'),
(446, 'http://www.thestandard.com/', ' The Industry Standard', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(447, 'http://www.newspaperlinks.com/', ' Newspaper Links', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(448, 'http://www.exchangeandmart.com', ' Exchange and Mart', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(449, 'http://scitechdaily.com', ' Scitech Daily Review', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(450, 'http://www.worldwidenews.com', ' World Wide News', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(451, 'http://www.thisislondon.com', ' The Evening Standard', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(452, 'http://www.telegraph.co.uk', ' The Telegraph Newspaper', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(453, 'http://www.the-times.co.uk', ' The Times Newspaper', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(454, 'http://www.mirror.co.uk', ' The Mirror Newspaper', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(455, 'http://www.thepaperboy.com', ' Links To Online Newspapers and Magazines', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(456, 'http://www.ft.com', ' The Financial Times', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(457, 'http://www.gids-games.com', ' Weird Magazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(458, 'http://www.upload.com', ' Uploaded Magazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(459, 'http://www.maximmag.com', ' Maxim Magazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(460, 'http://www.hotwired.com', ' Wired Magazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(461, 'http://www.newscientist.com', ' New Scientist Magazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(462, 'http://www.netmag.co.uk', ' Internet Magazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(463, 'http://www.erack.com/fhm', ' For Him Magazine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(464, 'http://www.dominis.com/Zines/?es', ' Catch Up On The E-Magazines', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(465, 'http://www.bbc.co.uk', ' BBC OnLine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(466, 'http://www.itn.co.uk', ' ITN OnLine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(467, 'http://www.sky.co.uk', ' Sky OnLine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(468, 'http://www.abcnews.go.com', ' ABC News', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(469, 'http://www.spacenews.com', ' Space News OnLine', '2007-01-01', '09:00:00', 'Gavin Baker', 'Online Newspapers and Magazines', 5, 1, '', 'http'),
(470, 'http://www.sceneone.co.uk', ' Scene One', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(471, 'http://www.movieflix.com', ' Movie Flix', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(472, 'http://www.movie-mistakes.co.uk', ' Movie Mistakes', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(473, 'http://www.rotten-tomatoes.com/', ' Rotten Tomatoes', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(474, 'http://www.geocities.com/Broadway/Wing/5176/FilmManiacs.html', ' FilmManiacs', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(475, 'http://www.ohthehumanity.com', ' Worst Movies On Earth', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(476, 'http://www.movie-trailers.com', ' The Trailer Park', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(477, 'http://www.eonline.com', ' E! Online', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(478, 'http://members.xoom.com/kingofsf', ' King of Sci-Fi', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(479, 'http://www.geocities.com/Hollywood/Theater/6219/', ' Internet Movie Sounds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(480, 'http://www.movies.go.com', ' Movies.com', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(481, 'http://www.hollywoodreporter.com', ' Hollywood Reporter', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(482, 'http://www.flim.com', ' Flim.com', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(483, 'http://www.virgin.net/cinema', ' Cinema Channel', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(484, 'http://www.allmovie.com', ' All Movie Guide', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(485, 'http://www.allmusic.com', ' All Music Guide', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(486, 'http://www.zyworld.com/Robotwars', ' Robot Wars', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(487, 'http://www.bflicks.com', ' BFlicks', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(488, 'http://www.mseek.com', ' Movies Seeker', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(489, 'http://uk.imdb.com', ' Internet Movie Database', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(490, 'http://www.mrshowbiz.com', ' Mr. ShowBiz', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(491, 'http://www.moviesounds.com', ' The Movies Sound Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Movies, Flims, Television', 5, 1, '', 'http'),
(492, 'http://www.newslettersforfree.com/', ' Newsletters For Free', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(493, 'http://www.shagmail.com/', ' Shag Mail', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(494, 'http://list-universe.com/', ' List Universe', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(495, 'http://list-resources.com/', ' List Resources', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(496, 'http://List-A-Day.com/', ' List A Day', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(497, 'http://www.techmailings.com/index.html', ' Andover Tech Mailings', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(498, 'http://gort.ucsd.edu/newjour/subscribe.html', ' NewJour', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(499, 'http://published.com/add/', ' Get Published', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(500, 'http://web-star.co.uk/newpage/newpage.html', ' Web-Star', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(501, 'http://www.ahandyguide.com', ' Handi Links', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(502, 'http://www.webloft.com', ' Web Loft Publishing', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(503, 'http://tile.net/lists/', ' Tile Net', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(504, 'http://nu2.com/submit.html', ' Whats New To Submit', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(505, 'http://www.listtool.com/cgi/listtool/addList.cgi', ' List Tool', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(506, 'http://catalog.com/vivian/intsubform2.html', ' The List of Lists by Vivian', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(507, 'http://www.listnet.com', ' The Internet Mailing List', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(508, 'http://www.diysearch.com', ' DIY Search', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(509, 'http://www.questfinder.com', ' Quest Finder', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(510, 'http://www.ezinesearch.com', ' eZINESearch', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(511, 'http://www.ezine-news.com', ' Ezines Today', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(512, 'http://www.infojump.com', ' The Ultimate Magazine Database', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(513, 'http://www.promotefree.com/eswap.htm', ' Ezine-Swap', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(514, 'http://www.image.dk/~knud-sor/en/', ' Electronic Newsstand', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(515, 'http://www.intersuccess.com/ezines.htm', ' List of Great Ezines', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(516, 'http://www.site-city.com/members/e-zine-master/', ' List of 600+ Ezines', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(517, 'http://scout.cs.wisc.edu/scout/new-list', ' New Ezine List', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(518, 'http://www.liszt.com', ' Liszt', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(519, 'http://www.zinebook.com/publicz.html', ' Zine And Ezine Resource Guide', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(520, 'www.homeincome.com/homebiz/homemoney-magazine/', ' HomeIncome Cities Mall', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(521, 'http://mediapeak.com', ' Media Peak', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(522, 'http://www.alacarim.com/', ' ALACARim', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(523, 'http://www.web-source.net', ' Web Source Net', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(524, 'http://www.newsletteradswap.com/', ' Newsletter Ad Swap', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(525, 'http://www.webheadcentral.com/freezines.html', ' Web Head Central Free Zines', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(526, 'http://www.interferenza.net/ezineadsource/al/affiliates.cgi?200', ' Ezine Ad Source', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(527, 'http://globemark.net/FreeZinesOnline.htm', ' FreeZinesOnline', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(528, 'http://www.ezine-tips.com', ' Ezines/Newsletters Tips', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(529, 'http://www.bizx.com/newsletter.html', ' Learn About ezines/Newsletters (BizX)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(530, 'http://www.cyberprosper.com/ezine-exchange.htm', ' Free Ezine Exchange', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(531, 'http://www.ezineseek.com', ' Ezine Seek', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(532, 'http://www.interferenza.net/ezineadsource/al/affiliates.cgi?200', ' Ezines Ad Source', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(533, 'http://www.infobot.net', ' Learn About ezines/Newsletters (InfoBot)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(534, 'http://www.lifestylepub.com', ' Directory of ezines Who Accept Advertising', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(535, 'http://www.zinezone.com', ' ZineZone', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(536, 'http://www.ezinez.com', ' E-Zinez', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(537, 'http://www.dominis.com/Zines/', ' The Ezines Database', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(538, 'http://www.etext.org/services.shtml', ' www.etext.org/services.shtml', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(539, 'http://www.disobey.com/low/addere.shtml', ' www.disobey.com/low/addere.shtml', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(540, 'http://inkpot.com/submit', ' Ink Pot', '2007-01-01', '09:00:00', 'Gavin Baker', 'Newsletters and Ezines', 5, 1, '', 'http'),
(541, 'http://www.bt.com/phonenetuk/', ' BT PhoneNetUK', '2007-01-01', '09:00:00', 'Gavin Baker', 'Finding Email Addresses and Phone Numbers', 5, 1, '', 'http'),
(542, 'http://people.yahoo.com', ' Yahoo People', '2007-01-01', '09:00:00', 'Gavin Baker', 'Finding Email Addresses and Phone Numbers', 5, 1, '', 'http'),
(543, 'http://www.anywho.com', ' Any Who?', '2007-01-01', '09:00:00', 'Gavin Baker', 'Finding Email Addresses and Phone Numbers', 5, 1, '', 'http'),
(544, 'http://www.whowhere.lycos.com', ' Who Where?', '2007-01-01', '09:00:00', 'Gavin Baker', 'Finding Email Addresses and Phone Numbers', 5, 1, '', 'http'),
(545, 'http://www.eyp.co.uk', ' Electronic Yellow Pages', '2007-01-01', '09:00:00', 'Gavin Baker', 'Finding Email Addresses and Phone Numbers', 5, 1, '', 'http'),
(546, 'http://www.emailfinder.com', ' Email Finder', '2007-01-01', '09:00:00', 'Gavin Baker', 'Finding Email Addresses and Phone Numbers', 5, 1, '', 'http'),
(547, 'http://accessld.cjb.net', ' Low cost, Long distance, Worldwide Communication', '2007-01-01', '09:00:00', 'Gavin Baker', 'Finding Email Addresses and Phone Numbers', 5, 1, '', 'http'),
(548, 'http://www.phonenumbers.net', ' Finding a Phone Number Worldwide', '2007-01-01', '09:00:00', 'Gavin Baker', 'Finding Email Addresses and Phone Numbers', 5, 1, '', 'http'),
(549, 'http://alabanza.com/kabacoff/InterLinks/phone.html', ' Finding People On The Net', '2007-01-01', '09:00:00', 'Gavin Baker', 'Finding Email Addresses and Phone Numbers', 5, 1, '', 'http'),
(550, 'http://www.iaf.net', ' Email Address Lookup', '2007-01-01', '09:00:00', 'Gavin Baker', 'Finding Email Addresses and Phone Numbers', 5, 1, '', 'http'),
(551, 'http://www.peoplelink.com', ' People Link', '2007-01-01', '09:00:00', 'Gavin Baker', 'Finding Email Addresses and Phone Numbers', 5, 1, '', 'http'),
(552, 'http://www.scit.wiv.ac.uk/ukinfo/ac/working.alpha.html', ' Individual Unversity Directories', '2007-01-01', '09:00:00', 'Gavin Baker', 'Finding Email Addresses and Phone Numbers', 5, 1, '', 'http'),
(553, 'http://www.freebizsites.com', ' The Free Biz Site Directory', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(554, 'http://www.workingonline.com/', ' Working Online', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(555, 'http://www.tsbj/', ' The Small Business Journal', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(556, 'http://www.promotiontoolkit.com/', ' Promotion Toolkit', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(557, 'http://www.webmasterexchange.com/', ' Webmaster Exchange Banners Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(558, 'http://www.time-capsule.com/cybersaver/', ' Cyber Saver Banners Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(559, 'http://www.markwelch.com/bannerad/baf_exchange.htm', ' Mark Welch Banner Networks', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(560, 'http://www.webreference.com/promotion/banners/networks.html', ' Webreference Banners Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(561, 'http://www.adsdaq.com', ' ADSDAQ Banners Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(562, 'http://www.plemx.com/index.html', ' Power Exchange Banners Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(563, 'http://www.free-banners.com', ' Free Banners Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(564, 'http://freelees.prohosting.com', ' FreeLess Banner Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(565, 'http://www.Bannerpromo.com', ' Banner Promo Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(566, 'http://www.bannerswap.com', ' Banner Swap Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(567, 'http://www.bannerCAST.com', ' Banner Cast Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(568, 'http://www.BannerCo-Op.com', ' Banner Co-Op Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(569, 'http://www.linktower.com', ' Banner Link Tower Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(570, 'http://www.adladder.com', ' Ad Ladder Banner Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(571, 'http://myfreeoffice.com/bannerx/index.html', ' BannerX Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(572, 'http://www.LinkBuddies.com', ' Link Buddies Banner Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(573, 'http://www.morehits.com', ' More Hits Banner Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(574, 'http://www.megabanners.com', ' Mega Banner Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(575, 'http://www.greatbanner.com', ' Great Banner Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(576, 'http://www.bighits.com', ' Big Hit Banner Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(577, 'http://www.clicktrade.com', ' Reward Web Site Owners For Linking To Your Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(578, 'http://www.positionagent.com', ' Monitor Your Web Site Rankings', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(579, 'http://www.neatlinks.com', ' Neatlinks', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(580, 'http://www.sitelaunch.net/', ' Site Launch Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(581, 'http://www.ecki.com/links', ' All Links FREE Advertising Directory', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(582, 'http://www.igoldrush.com/missing/', ' Exchange Links Directory', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(583, 'http://www.ktpbs.com/classifiedlinks/', ' FAA Service 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(584, 'http://www.tri-polar.com/atkisson/index.html', ' FFA Service 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(585, 'http://www.webfulyours.com/links2.html', ' FFA Service 3', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(586, 'http://www.ffaomatic.com/cgi-bin/ffa.cgi?page=TheLinksPage', ' The FFA-O-MATIC Service', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(587, 'http://www.promotefree.com/index.html', ' Promote Free', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(588, 'http://www.thelinktrade.com/', ' Link Trade', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(589, 'http://www.new-list.com/', ' New List', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(590, 'http://www.flintelmarketing.com/flintelsuccess/?fs0713', ' Flintel Marketing', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(591, 'http://freelinksnetwork.com/links/webheadcentral', ' Free Links Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(592, 'http://www.classified-x.com', ' Classified Exchange', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(593, 'http://www.announce.net/personal.htm', ' Announce.Net', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(594, 'http://www.mid.net/NET/input.html', ' Net Happenings', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(595, 'http://www.netscape.com/escapes/submit_new.html', ' Netscape What\rquote s New', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(596, 'http://newtoo.manifest.com/WhatsNewToo/submit.html', ' What\rquote s New Too!', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(597, 'http://www.usatoday.com/life/cyber/chb1103.htm', ' USA Today Hot Sites', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(598, 'http://www.reciprocallink.com/', ' Reciprocal Links.Com', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(599, 'http://members.tripod.com/~brolling/reciproc.html', ' Reciprocal Links', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(600, 'http://www.igoldrush.com/missing/', ' Missing Link-Reciprocal Link Database', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(601, 'http://www.freelinks.com', ' Free Links', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(602, 'http://www.winzip.com/links1.htm', ' Winzip Reciprocal Links', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(603, 'http://websiteawards.xe.net/', ' Website Awards', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(604, 'http://www.focusa.com/awardsites/introduction.htm', ' Award Sites', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(605, 'http://www.dailyhotsite.com', ' Daily Hot Site', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(606, 'http://www.market-tek.com/awardsite.html', ' Ultimate Award Submit', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(607, 'http://www.totalinternetresource.com/sitesearch.html', ' Total Internet Resources', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(608, 'http://www.praservices.com/headlinesads.html', ' PRA Services', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(609, 'http://signpost.merseyworld.com/', ' Signpost', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(610, 'http://www.mrsmithmedia.com/', ' Infosearch Inc.', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web Site Promotion', 5, 1, '', 'http'),
(611, 'http://www.oneacross.com', ' One Across', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(612, 'http://www.assignmenteditor.com/', ' Assignment Editor', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(613, 'http://www.abfab-australia.com/netiquette/grammar.html', ' Cool Misunderstood Grammar', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(614, 'http://www.acronymfinder.com', ' The Acronym Finder 1', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(615, 'http://www.mtnds.com/af/', ' The Acronym Finder 2', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(616, 'http://www.whatis.com', ' What Is?.com', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(617, 'http://fathom.org/opalcat/virtualbw.html', ' UK National Lottery', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(618, 'http://www.waterstones.co.uk', ' Waterstones', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(619, 'http://www.symbols.com', ' Symbols', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(620, 'http://www.striaghtdope.com', ' The Straight Dope', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(621, 'http://www.megaconvertor.com', ' Mega Converter', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(622, 'http://www.magazineshop.co.uk', ' Magazine Shop', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(623, 'http://www.futfan.com', ' Future Fantasy Bookstore', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(624, 'http://www.blackwells.co.uk', ' Blackwells', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(625, 'http://www.barnesandnoble.com', ' Barnes and Noble', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(626, 'http://www.atomicbooks.com', ' Atomic Books', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(627, 'http://www.cs.cmu.edu/People/spok/banned-books.html', ' Banned Books', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(628, 'http://www.bell-labs.com/project/tts/voices.html', ' Text To Speech', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(629, 'http://www.reversespeech.com', ' Reverse Speech', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(630, 'http://www.reel.com', ' Riddler', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(631, 'http://www.cyburbia.net/uselessfacts.html', ' The Usesless Facts Page', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(632, 'http://www.dictnary.com/others/', ' Translate English Words', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(633, 'http://www.quizmaster.com/cotd.htm', ' Interactive Crosswords', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(634, 'http://www.m-w.com', ' Word-Related Things', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(635, 'http://www.anagramgenius.com', ' The Anagram Genuis', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(636, 'http://www.quotations.com', ' Quotations', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(637, 'http://www.quoteland.com', ' Quote Land', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(638, 'http://www.wordcentral.com', ' Word Central', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(639, 'http://www.wordsmith.org', ' The Word Smith', '2007-01-01', '09:00:00', 'Gavin Baker', 'Words and Puzzles', 5, 1, '', 'http'),
(640, 'http://www.web-animator.com/', ' Web Animator', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(641, 'http://www.jenntel.com', ' Jenntel - Personal Reference Portal', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(642, 'http://linkcrafter.bannermation.com/', ' Link Crafter', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(643, 'http://awdsites.com/software/dTag.htm', ' Drag Tag', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(644, 'http://www.homepage.com', ' HomePage.com', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(645, 'http://www.i-filezone.com', ' Internet File Zone', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(646, 'http://www.browsertune.com', ' Browser Tune', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(647, 'http://certifiedmail.com/', ' Certified Mail', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(648, 'http://www.elsop.com/linkscan/quickcheck.html', ' Quick Link Check', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(649, 'http://www.filedemon.com', ' File Demon', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(650, 'http://www.evolvable.com/webtest.htm', ' Evolvable Web Test', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(651, 'http://www.supertips.com', ' Super Tips', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(652, 'http://green.vr9.com', ' Learn Everything', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(653, 'http://www.winomatic.com', ' Win-O-Matic', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(654, 'http://www.virtualis.com', ' Virtualis Web Hosting', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(655, 'http://www.artronicnet.com/', ' Artronic-Web Site Design and Hosting', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(656, 'http://www.devilnet.co.uk/', ' Devil Net', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(657, 'http://www.neosoft.com/internet/paml', ' Public Accessible Mailing Lists', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(658, 'http://www.projectcool.com', ' Project Cool', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(659, 'http://www.freewebspace.net', ' Free Web Space Listing', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(660, 'http://www.minds.com', ' Electric Minds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(661, 'http://www.mail.yahoo.com', ' Free EMail Service (Yahoo)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(662, 'http://www.rocketmail.com', ' Free EMail Service (Rocket Mail)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(663, 'http://www.netaddress.usa.net', ' Free EMail Service (Net Address)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(664, 'http://www.iname.com', ' Free EMail Service (IName)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(665, 'http://www.bigfoot.com', ' Free EMail Service (Big Foot)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(666, 'http://www.hotmail.com', ' Free EMail Service (Hot Mail)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(667, 'http://www.netforward.com', ' Free EMail Service (Net Forward)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(668, 'http://emumail.net', ' Free EMail Service (Emu Mail)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(669, 'http://www.get-mail.com', ' Free EMail Service (Get Mail)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(670, 'http://www.lurch.net', ' Free EMail Service (Lurch Net)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(671, 'http://www.selfpromotion.com', ' Register Site With Search Engines and Indexes', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(672, 'http://www.netwhistle.com', ' Whistle When Site Down', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(673, 'http://www.atwatch.com', ' Notify If Your Site Goes Down', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(674, 'http://www.emailaddresses.com', ' Free Email Directory', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(675, 'http://www.coolads.com', ' Cool Ads', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(676, 'http://www.linkmedia.com/inetexch/nowhoo/nowhoo.cgi', ' The ZAP Directory', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(677, 'http://classifieds.yahoo.com/', ' Yahoo Classifieds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(678, 'http://www.bizsol.com/', ' Business Solutions Classifieds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(679, 'http://www.webheadcentral.com/adland.html', ' Ad Land', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(680, 'http://accusubmit.com/classifieds/', ' Accusubmit Classifieds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(681, 'http://www.freeclassifieds.com/mailad.htm', ' A-Z FREE Classifieds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(682, 'http://www.interking.com', ' Kingdom Classifieds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(683, 'http://www.netclassifieds.com/', ' NetClassifieds', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http');
INSERT INTO `linkslinkdescriptions` (`linkID`, `linkURL`, `linkDescription`, `linkDateEntered`, `linkTimeEntered`, `linkEnteredBy`, `linkCat`, `linkRating`, `linkActive`, `linkInactiveReason`, `linkType`) VALUES
(684, 'http://www.classifieds2000.com/', ' Classifieds 2000', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(685, 'http://www.5starads.com/', ' 5 Star Ads', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(686, 'http://www.freeboards.net', ' Free Message Board', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(687, 'http://www.geocities.com/SiliconValley/Hills/5099/submit.htm', ' Lightening Submission Wizard', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(688, 'http://www.lockergnome.com', ' The Locker Gnome', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(689, 'http://www.webpageomatic.com', ' Web Page O Matic', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(690, 'http://www.microbuttons.com', ' Micro Buttons', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(691, 'http://www.mailstart.com', ' Mail Start Service', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(692, 'http://www.getrealproductions.com', ' Free Classified (Get Real Productions)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(693, 'http://www.iloft.com', ' Free Classified Ads (Iloft)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(694, 'http://www.profitpowerpromo.com/Classifieds.htm', ' Free Classified Ads (Profit Power Promo)', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(695, 'http://free-classifieds.freeservers.com', ' Free Classified Ad Services', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(696, 'http://www.ad-guide.com/Affiliate_Programs', ' AdGuide', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(697, 'http://atlnetwork.com', ' Affiliate Trade Links Network', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(698, 'http://www.associatecash.com', ' Associate Cash', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(699, 'http://associate-it.com', ' Associate-It', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(700, 'http://www.associateprograms.com', ' Associate Programs', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(701, 'http://www.cashpile.com', ' Cash Pile', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(702, 'http://www.clickquick.com/affiliate.htm', ' ClickQuick', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(703, 'http://www.clickslink.com', ' Clicks Link', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(704, 'http://www.refer-it.com', ' Refer It', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(705, 'http://www.revenews.com', ' ReveNews', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http'),
(706, 'http://www.sitecash.com', ' SiteCash', '2007-01-01', '09:00:00', 'Gavin Baker', 'Web User Services', 5, 1, '', 'http');

-- --------------------------------------------------------

--
-- Table structure for table `newsletters`
--

CREATE TABLE `newsletters` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `sdcdrawingitems`
--

CREATE TABLE `sdcdrawingitems` (
  `sdcDIID` int(11) NOT NULL,
  `sdcDIEntity` varchar(200) NOT NULL DEFAULT '',
  `sdcDIType` int(11) NOT NULL DEFAULT '0',
  `sdcDIOriginX` int(11) NOT NULL DEFAULT '0',
  `sdcDIOriginY` int(11) NOT NULL DEFAULT '0',
  `sdcDIParam1` varchar(200) DEFAULT NULL,
  `sdcDIParam2` varchar(200) DEFAULT NULL,
  `sdcDIFilled` tinyint(4) DEFAULT '0',
  `sdcDIColor` varchar(25) NOT NULL DEFAULT '',
  `sdcDIName` varchar(200) NOT NULL DEFAULT '',
  `sdcDIStrokeWidth` int(11) DEFAULT '1',
  `sdcDIParam3` varchar(200) DEFAULT NULL,
  `sdcDIUnique` varchar(200) DEFAULT NULL,
  `sdcDILayer` int(11) NOT NULL DEFAULT '0',
  `sdcDIParam4` varchar(200) DEFAULT NULL,
  `sdcDIRotAngle` int(11) NOT NULL DEFAULT '0' COMMENT 'Rotation angle of drawing item'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds details of drawing items for drawing canvas';

--
-- Dumping data for table `sdcdrawingitems`
--

INSERT INTO `sdcdrawingitems` (`sdcDIID`, `sdcDIEntity`, `sdcDIType`, `sdcDIOriginX`, `sdcDIOriginY`, `sdcDIParam1`, `sdcDIParam2`, `sdcDIFilled`, `sdcDIColor`, `sdcDIName`, `sdcDIStrokeWidth`, `sdcDIParam3`, `sdcDIUnique`, `sdcDILayer`, `sdcDIParam4`, `sdcDIRotAngle`) VALUES
(1, 'test1', 1, 81, 59, '148', '137', 1, 'red', 'test1-subentity-0', 1, '', '1884', 0, NULL, 0),
(2, 'test1', 1, 115, 260, '70', '44', 1, 'purple', 'test1-subentity-1', 1, '', '1894', 0, NULL, 0),
(3, 'test1', 1, 46, 52, '46', '52', 1, 'red', 'test1-subentity-2', 1, '', '1874', 0, NULL, 0),
(4, 'test1', 1, 306, 97, '24', '52', 1, 'red', 'test1-subentity-3', 1, '', '1875', 0, NULL, 0),
(5, 'test1', 1, 81, 59, '148', '137', 0, 'red', 'test1-subentity-0', 1, '', '1880', 0, NULL, 0),
(6, 'test1', 1, 81, 59, '148', '137', 1, 'red', 'test1-subentity-0', 1, '', '1883', 0, NULL, 0),
(7, 'test1', 1, 115, 260, '238', '372', 1, 'purple', 'test1-subentity-1', 1, '', '1889', 0, NULL, 0),
(8, 'test1', 1, 126, 103, '126', '103', 1, 'yellow', 'test1-subentity-2', 1, '', '1890', 0, NULL, 0),
(10, 'test1', 1, 306, 97, '392', '188', 1, 'yellow', 'test1-subentity-3', 1, '', '1889', 0, NULL, 0),
(9, 'test1', 6, 0, 0, '1889', '1883', 0, 'black', '', 1, 'wow', '1888', 0, NULL, 0),
(11, 'test1', 1, 305, 285, '305', '285', 1, 'yellow', 'test1-subentity-4', 1, '', '1889', 0, NULL, 0),
(12, 'test1', 1, 349, 160, '349', '160', 1, 'yellow', 'test1-subentity-5', 1, '', '1891', 0, NULL, 0),
(42, 'test_umldiag', 2, 296, 194, '339', '225', 1, 'red', 'tmp-subentity-8', 1, '', '1893', 0, NULL, 0),
(41, 'test_umldiag', 2, 147, 229, '233', '262', 1, 'red', 'tmp-subentity-7', 1, '', '1891', 0, NULL, 0),
(40, 'test_umldiag', 6, 0, 0, '1888', '1882', 0, 'black', 'tmp-subentity-6', 1, 'ggggg', '1886', 0, NULL, 0),
(59, 'test_umldiag', 1, 41, 88, '406', '148', 0, 'purple', 'tmp-subentity-1', 1, '', '1891', 0, NULL, 0),
(38, 'test_umldiag', 1, 437, 85, '405', '246', 1, 'yellow', 'tmp-subentity-4', 1, '', '1888', 0, NULL, 0),
(37, 'test_umldiag', 1, 312, 161, '170', '204', 1, 'red', 'tmp-subentity-3', 1, '', '1889', 0, NULL, 0),
(29, 'test_umldiag', 1, 161, 87, '245', '157', 0, 'yellow', 'tmp-subentity-6', 1, '', '1890', 0, NULL, 0),
(36, 'test_umldiag', 1, 274, 57, '72', '20', 0, 'blue', 'tmp-subentity-2', 1, '', '1888', 0, NULL, 0),
(35, 'test_umldiag', 1, 41, 88, '103', '114', 0, 'green', 'tmp-subentity-1', 1, '', '1885', 0, NULL, 0),
(34, 'test_umldiag', 1, 182, 103, '145', '99', 0, 'blue', 'tmp-subentity-0', 1, '', '1882', 0, NULL, 0),
(33, 'test_umldiag', 1, 329, 40, '75', '21', 1, 'red', 'tmp-subentity-10', 1, '', '1885', 0, NULL, 0),
(32, 'test_umldiag', 1, 140, 95, '264', '206', 1, 'yellow', 'tmp-subentity-9', 1, '', '1882', 0, NULL, 0),
(31, 'test_umldiag', 1, 349, 99, '422', '173', 1, 'yellow', 'tmp-subentity-8', 1, '', '1880', 0, NULL, 0),
(30, 'test_umldiag', 1, 245, 157, '245', '157', 0, 'yellow', 'tmp-subentity-7', 1, '', '1890', 0, NULL, 0),
(43, 'test_umldiag', 2, 381, 164, '410', '210', 1, 'red', 'tmp-subentity-9', 1, '', '1896', 0, NULL, 0),
(44, 'test_umldiag', 2, 185, 109, '214', '187', 1, 'red', 'tmp-subentity-10', 1, '', '1874', 0, NULL, 0),
(45, 'test_umldiag', 1, 182, 103, '242', '132', 0, 'blue', 'tmp-subentity-0', 1, '', '1886', 0, NULL, 0),
(46, 'test_umldiag', 1, 41, 88, '103', '114', 0, 'green', 'tmp-subentity-1', 1, '', '1879', 0, NULL, 0),
(47, 'test_umldiag', 1, 274, 57, '72', '20', 0, 'blue', 'tmp-subentity-2', 1, '', '1878', 0, NULL, 0),
(52, 'test_umldiag', 1, 182, 103, '197', '132', 0, 'blue', 'tmp-subentity-0', 1, '', '1881', 0, NULL, 0),
(48, 'test_umldiag', 1, 182, 103, '148', '152', 0, 'blue', 'tmp-subentity-0', 1, '', '1888', 0, NULL, 0),
(49, 'test_umldiag', 1, 41, 88, '103', '114', 0, 'green', 'tmp-subentity-1', 1, '', '1884', 0, NULL, 0),
(50, 'test_umldiag', 1, 274, 57, '72', '20', 0, 'blue', 'tmp-subentity-2', 1, '', '1889', 0, NULL, 0),
(51, 'test_umldiag', 1, 312, 161, '341', '233', 1, 'yellow', 'tmp-subentity-3', 1, '', '1886', 0, NULL, 0),
(53, 'test_umldiag', 1, 41, 88, '103', '114', 0, 'green', 'tmp-subentity-1', 1, '', '1877', 0, NULL, 0),
(54, 'test_umldiag', 1, 274, 57, '72', '20', 0, 'blue', 'tmp-subentity-2', 1, '', '1882', 0, NULL, 0),
(55, 'test_umldiag', 1, 312, 161, '470', '237', 1, 'navy', 'tmp-subentity-3', 1, '', '1884', 0, NULL, 0),
(61, 'test_umldiag', 1, 182, 103, '286', '159', 0, 'green', 'tmp-subentity-0', 1, '', '1884', 0, NULL, 0),
(56, 'test_umldiag', 2, 437, 85, '472', '144', 1, 'yellow', 'tmp-subentity-4', 1, '', '1880', 0, NULL, 0),
(58, 'test_umldiag', 1, 182, 103, '235', '159', 0, 'red', 'tmp-subentity-0', 1, '', '1887', 0, NULL, 0),
(60, 'test_umldiag', 1, 274, 57, '72', '20', 0, 'yellow', 'tmp-subentity-2', 1, '', '1894', 0, NULL, 0),
(62, 'test_umldiag', 1, 41, 88, '455', '176', 0, 'red', 'tmp-subentity-1', 1, '', '1886', 0, NULL, 0),
(63, 'test_umldiag', 1, 274, 57, '72', '20', 0, 'purple', 'tmp-subentity-2', 1, '', '1888', 0, NULL, 0),
(64, 'test_umldiag', 1, 182, 103, '223', '127', 1, 'red', 'tmp-subentity-0', 1, '', '1873', 0, NULL, 0),
(65, 'test_umldiag', 1, 41, 88, '430', '198', 1, 'red', 'tmp-subentity-1', 1, '', '1876', 0, NULL, 0),
(66, 'test_umldiag', 6, 274, 57, '72', '20', 0, 'black', 'tmp-subentity-2', 1, 'testcon', '1878', 0, NULL, 0),
(67, 'test_umldiag', 1, 182, 103, '183', '122', 1, 'green', 'tmp-subentity-0', 1, '', '1879', 0, NULL, 0),
(68, 'test_umldiag', 1, 41, 88, '444', '147', 1, 'green', 'tmp-subentity-1', 1, '', '1872', 0, NULL, 0),
(69, 'test_umldiag', 1, 274, 57, '72', '20', 1, 'green', 'tmp-subentity-2', 1, '', '1874', 0, NULL, 0),
(70, 'test_umldiag', 6, 312, 161, '1874', '1879', 0, 'black', 'tmp-subentity-3', 1, 'what', '1876', 0, NULL, 0),
(71, 'test_umldiag', 1, 182, 103, '244', '187', 1, 'red', 'tmp-subentity-0', 1, '', '1878', 0, NULL, 0),
(72, 'test_umldiag', 1, 182, 103, '269', '162', 1, 'green', 'tmp-subentity-0', 1, '', '1887', 0, NULL, 0),
(73, 'test_umldiag', 1, 41, 88, '417', '169', 1, 'green', 'tmp-subentity-1', 1, '', '1878', 0, NULL, 0),
(74, 'test_umldiag', 1, 274, 57, '72', '20', 1, 'green', 'tmp-subentity-2', 1, '', '1881', 0, NULL, 0),
(75, 'test_umldiag', 1, 312, 161, '291', '242', 1, 'green', 'tmp-subentity-3', 1, '', '1883', 0, NULL, 0),
(76, 'test_umldiag', 1, 118, 121, '172', '165', 1, 'green', 'tmp-subentity-4', 1, '', '1878', 0, NULL, 0),
(77, 'test_umldiag', 1, 143, 78, '143', '78', 1, 'green', 'tmp-subentity-5', 1, '', '1881', 0, NULL, 0),
(78, 'test_umldiag', 6, 0, 0, '1878', '1887', 0, 'black', 'tmp-subentity-6', 1, 'text1,text2', '1888', 0, NULL, 0),
(79, 'test_umldiag', 1, 182, 103, '287', '149', 1, 'green', 'tmp-subentity-0', 1, '', '1885', 0, NULL, 0),
(80, 'test_umldiag', 1, 41, 88, '139', '149', 1, 'green', 'tmp-subentity-1', 1, '', '1879', 0, NULL, 0),
(81, 'test_umldiag', 1, 274, 57, '72', '20', 1, 'green', 'tmp-subentity-2', 1, '', '1881', 0, NULL, 0),
(82, 'test_umldiag', 1, 312, 161, '462', '178', 1, 'green', 'tmp-subentity-3', 1, '', '1883', 0, NULL, 0),
(83, 'test_umldiag', 1, 214, 199, '321', '251', 1, 'green', 'tmp-subentity-4', 1, '', '1885', 0, NULL, 0),
(84, 'test_umldiag', 6, 0, 0, '1885', '1883', 0, 'black', 'tmp-subentity-5', 1, 't1,t2', '1885', 0, NULL, 0),
(85, 'test_umldiag', 6, 0, 0, '1879', '1885', 0, 'black', 'tmp-subentity-6', 1, 't3,t4', '1885', 0, NULL, 0),
(86, 'test_umldiag', 1, 182, 103, '313', '152', 1, 'green', 'tmp-subentity-0', 1, '', '1885', 0, NULL, 0),
(87, 'test_umldiag', 1, 41, 88, '439', '159', 1, 'green', 'tmp-subentity-1', 1, '', '1879', 0, NULL, 0),
(88, 'test_umldiag', 1, 182, 103, '270', '186', 1, 'green', 'tmp-subentity-0', 1, '', '1886', 0, NULL, 0),
(89, 'test_umldiag', 1, 41, 88, '121', '131', 1, 'green', 'tmp-subentity-1', 1, '', '1880', 0, NULL, 0),
(90, 'test_umldiag', 1, 274, 57, '364', '109', 1, 'green', 'tmp-subentity-2', 1, '', '1882', 0, NULL, 0),
(91, 'test_umldiag', 1, 312, 161, '341', '220', 1, 'green', 'tmp-subentity-3', 1, '', '1885', 0, NULL, 0),
(92, 'test_umldiag', 1, 182, 103, '245', '174', 1, 'green', 'tmp-subentity-0', 1, '', '1888', 0, NULL, 0),
(93, 'test_umldiag', 1, 304, 126, '338', '166', 1, 'green', 'tmp-subentity-1', 1, '', '1882', 0, NULL, 0),
(94, 'test_umldiag', 1, 211, 50, '234', '71', 1, 'green', 'tmp-subentity-2', 1, '', '1884', 0, NULL, 0),
(95, 'test_umldiag', 1, 92, 122, '125', '150', 1, 'green', 'tmp-subentity-3', 1, '', '1885', 0, NULL, 0),
(96, 'test_umldiag', 1, 209, 220, '245', '229', 1, 'green', 'tmp-subentity-4', 1, '', '1887', 0, NULL, 0),
(97, 'test_umldiag', 1, 227, 153, '227', '153', 1, 'green', 'tmp-subentity-5', 1, '', '1881', 0, NULL, 0),
(98, 'test_umldiag', 6, 0, 0, '1888', '1885', 0, 'black', 'tmp-subentity-6', 1, 'text1,text2', '1871', 0, NULL, 0),
(99, 'test_umldiag', 6, 0, 0, '1888', '1884', 0, 'black', 'tmp-subentity-7', 1, 'text3,text4', '1871', 0, NULL, 0),
(100, 'test_umldiag', 6, 0, 0, '1888', '1882', 0, 'black', 'tmp-subentity-8', 1, 'text5,ext6', '1883', 0, NULL, 0),
(101, 'test_umldiag', 6, 0, 0, '1888', '1887', 0, 'black', 'tmp-subentity-9', 1, 'text7,ext8', '1878', 0, NULL, 0),
(102, 'untitled28072013221050', 1, 51, 59, '139', '180', 1, 'yellow', 'untitled28072013221050-subentity-0', 5, '', '1890', 1, '', 338),
(103, 'untitled28072013221050', 1, 193, 229, '347', '347', 1, 'red', 'untitled28072013221050-subentity-1', 5, '', '1888', 1, '', 0),
(104, 'untitled29072013221143', 1, 42, 103, '124', '127', 0, 'red', 'untitled29072013221143-subentity-0', 1, '', '1889', 1, '', 128),
(105, 'untitled29072013221143', 1, 123, 216, '255', '302', 1, 'red', 'untitled29072013221143-subentity-1', 1, '', '1891', 1, '', 0),
(106, 'untitled29072013221143', 1, 65, 240, '65', '240', 0, 'purple', 'untitled29072013221143-subentity-2', 1, '', '1890', 1, '', 0),
(107, 'untitled29072013221143', 1, 177, 361, '260', '458', 0, 'purple', 'untitled29072013221143-subentity-3', 1, '', '1885', 1, '', 0),
(108, 'untitled29072013221143', 1, 275, 358, '173', '479', 1, 'purple', 'untitled29072013221143-subentity-4', 1, '', '1891', 1, '', 0),
(109, 'untitled29072013221143', 1, 224, 34, '371', '187', 1, 'purple', 'untitled29072013221143-subentity-5', 1, '', '1894', 1, '', 0),
(110, 'untitled29072013221143', 1, 252, 332, '230', '337', 1, 'purple', 'untitled29072013221143-subentity-6', 1, '', '1890', 1, '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sysehsbanners`
--

CREATE TABLE `sysehsbanners` (
  `sysBanID` int(11) NOT NULL,
  `sysBanEHSAd` int(11) NOT NULL DEFAULT '0',
  `sysBanCompanyName` varchar(200) NOT NULL DEFAULT '',
  `sysBanEmail` varchar(200) DEFAULT NULL,
  `sysBanPictureURL` varchar(200) NOT NULL DEFAULT '',
  `sysBanStartDate` date NOT NULL DEFAULT '0000-00-00',
  `sysBanEndDate` date NOT NULL DEFAULT '0000-00-00',
  `sysBanCurrentClicks` int(11) DEFAULT '0',
  `sysBanPictureGotoURL` varchar(200) DEFAULT NULL,
  `sysBanIncValue` int(11) NOT NULL DEFAULT '0',
  `sysBanApp` varchar(200) DEFAULT NULL,
  `sysBanDesc` varchar(250) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds details of End House Software advert banners';

--
-- Dumping data for table `sysehsbanners`
--

INSERT INTO `sysehsbanners` (`sysBanID`, `sysBanEHSAd`, `sysBanCompanyName`, `sysBanEmail`, `sysBanPictureURL`, `sysBanStartDate`, `sysBanEndDate`, `sysBanCurrentClicks`, `sysBanPictureGotoURL`, `sysBanIncValue`, `sysBanApp`, `sysBanDesc`) VALUES
(2, 1, 'End House Software', 'endhousesoftware@hotmail.com', '../../applications/rapierchat/images/rclogo.gif', '2005-09-30', '2020-01-01', 6, '../../applications/rapierchat/index.htm', 0, 'ehsad', 'Graphical Chat System'),
(3, 0, 'End House Software', 'endhousesoftware@hotmail.com', '../images/ehslogo2.gif', '2005-09-30', '2020-01-01', 17, 'http://endhousesoftware.x10hosting.com', 0, 'ehsad', 'End House Software Company Website'),
(4, 1, 'End House Software', 'endhousesoftware@hotmail.com', '../../applications/hdlworkbench/images/hdlwblogo.gif', '2005-09-30', '2020-01-01', 7, '../../applications/hdlworkbench/hdlworkbench.htm', 0, 'ehsad', 'VHDL and Verilog Workbench'),
(22, 1, 'End House Software', 'endhousesoftware@hotmail.com', '../../applications/umldiag/images/umldiaglogo.gif', '2005-01-01', '2020-01-01', 0, '../../applications/umldiag/umldiag.htm', 0, 'ehsad', 'UML Diagrams'),
(23, 1, 'End House Software', 'endhousesoftware@hotmail.com', '../../applications/devtrack/images/proj_tracker.gif', '2013-01-01', '2020-01-01', 0, '../../applications/devtrack/devtrack.htm', 0, 'ehsad', 'Project Tracker');

-- --------------------------------------------------------

--
-- Table structure for table `sysehsdepartments`
--

CREATE TABLE `sysehsdepartments` (
  `sysEHSDeptID` int(11) NOT NULL,
  `sysEHSDeptName` varchar(200) NOT NULL DEFAULT '',
  `sysEHSDeptOwner` varchar(200) NOT NULL DEFAULT '',
  `sysEHSDeptEmail` varchar(200) NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds detail of all EHS departments';

--
-- Dumping data for table `sysehsdepartments`
--

INSERT INTO `sysehsdepartments` (`sysEHSDeptID`, `sysEHSDeptName`, `sysEHSDeptOwner`, `sysEHSDeptEmail`) VALUES
(1, 'Admin', 'Gavin Baker', 'endhousesoftware@hotmail.com'),
(2, 'Sales', 'Gavin Baker', 'endhousesoftware@hotmail.com'),
(3, 'Techinical', 'Gavin Baker', 'endhousesoftware@hotmail.com'),
(4, 'Enquiry', 'Gavin Baker', 'endhousesoftware@hotmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `sysehshelptopics`
--

CREATE TABLE `sysehshelptopics` (
  `sysEHSHelpID` int(11) NOT NULL,
  `sysEHSHelpText` longtext NOT NULL,
  `sysEHSHelpProduct` varchar(250) NOT NULL DEFAULT '',
  `sysEHSHelpTitle` varchar(200) NOT NULL DEFAULT '',
  `sysEHSHelpKeywords` text
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds help text blocks for all products';

--
-- Dumping data for table `sysehshelptopics`
--

INSERT INTO `sysehshelptopics` (`sysEHSHelpID`, `sysEHSHelpText`, `sysEHSHelpProduct`, `sysEHSHelpTitle`, `sysEHSHelpKeywords`) VALUES
(116, '3.0 Card Refence   The Intro card displays an introduction to Rapier Chat. The Msg and Help cards display any messages and this help file respectively. These are the only cards that can be selected without logging on.  The Logon card provides access to the Rapier Chat system.  The Rooms card allows you to select the required chat room or to edit your private room details - room background image and sound.  The Groups card allows you to create a user group, or club. Other users may add themselves to your group via this card. You can also send a email message to all members of your group. Only a monitor, or admin can delete a group.  The Room Admin card allows a monitor or admin to edit a chat rooms details or  add new rooms.  The chat card is the actual chat room  The System card allows admin to control the systems operation parameters.   ', 'Rapier Chat', '3.0 Card Refence', 'Card,Refence,,The,Intro,card,displays,an,introduction,Rapier,Chat,Msg,Help,cards,display,any,messages,help,file,respectively,These,are,only,that,can,be,selected,without,logging,on,Logon,provides,access,system,Rooms,allows,you,select,required,chat,room,or,edit,your,private,details,background,image,sound,Groups,create,user,group,club,Other,users,may,add,themselves,via,You,also,send,email,message,all,members,of,Only,monitor,admin,delete,Room,Admin,rooms,new,actual,System,control,systems,operation,parameters'),
(115, '2.0 Command Reference   Rapier Chat provides quick access to certain functions via hot keys.  RETURN  Execute the chat command CTRL-B Spell check all chat text CTRL-S Display or hide status window CTRL-H Display or hide help window CTRL-O Display or hide hotspots CTRL-P Print the chat room  The hot keys above will not work if the chat command text entry has focus, i.e. when you are typing in the chat command box.  if you are a monitor (or admin), the following commands are avaiable to you while in a chat room, (note the :s are part of the command), these would be entered as the chat text.  debug: - Toggle debugging msgs ban:user:txt - Ban User msg:[all|room|user]:msg - Global msg ths: - Toggles hotspots dhs: - Disables hotspots  The following are avaiable to all users,  print: - Print the chat room im: - send instant msg prof: - Toggle the professor askprof:question - Ask prof a question  The right hand mouse button displays a handy quick access popup menu, when not clicked on an aviator.   ', 'Rapier Chat', '2.0 Command Reference', 'Command,Reference,,Rapier,Chat,provides,quick,access,certain,functions,via,hot,keys,RETURN,Execute,chat,command,CTRL,B,Spell,check,all,text,S,Display,or,hide,status,window,H,help,O,hotspots,P,Print,room,The,above,will,work,if,entry,has,focus,e,when,you,are,typing,in,box,monitor,(or,admin),following,commands,avaiable,while,(note,s,part,of,command),these,would,be,entered,debug,Toggle,debugging,msgs,ban,user,txt,Ban,User,msg,[all|room|user],Global,ths,Toggles,dhs,Disables,users,print,im,send,instant,prof,professor,askprof,question,Ask,right,hand,mouse,button,displays,handy,popup,menu,clicked,on,an,aviator'),
(114, '1.0 Introduction  		    Rapier chat is a new generation chat system with option add-ins. It is designed to be run  in a maximized screen browser window at an  optimal resoultion of 800 by 600. Double click  browser windows title bar to maximize.  Welcome to Rapier Chat. Below you will find a quick reference quide to using Rapier Chat. Please report any suggestions or problems to comments@rapierchat.com, or click the button at the bottom of the help card to send an automated report.  When viewing the chat card aviators are displayed with a colored name block. This will be yellow for a standard aviator, blue if it is a buddy, red if you are having a private conversation with the aviator or  green if the aviator is being shadowed.  Left click anywhere on the chat room background to move your aviator to that position. If your aviator is currently speeking it will move to its new position when the speech bubble  disappears.If that position is a hotspot you will be transported to another room or shown some information.  Left clicking on an aviator will display the aviator information panel which displays the aviators name and email address. If you are a monitor (or admin) this panel also gives you the option of banning the user or shadowing the user as if you were logged on as them.   ', 'Rapier Chat', '1.0 Introduction', 'Introduction,,		,Rapier,chat,new,generation,system,with,option,add,ins,It,designed,be,run,in,maximized,screen,browser,window,an,optimal,resoultion,of,by,Double,click,windows,title,bar,maximize,Welcome,Chat,Below,you,will,find,quick,reference,quide,using,Please,report,any,suggestions,or,problems,comments@rapierchat,com,button,bottom,help,card,send,automated,When,viewing,aviators,are,displayed,colored,name,block,This,yellow,for,standard,aviator,blue,if,it,buddy,red,having,private,conversation,green,being,shadowed,Left,anywhere,on,room,background,move,your,that,position,If,currently,speeking,its,when,speech,bubble,disappears,hotspot,transported,another,shown,some,information,clicking,display,panel,which,displays,email,address,monitor,(or,admin),also,gives,banning,user,shadowing,were,logged,them'),
(117, '4.0 Live Video and Audio   Live audio and video feeds are available via a seperate download, see Rapier Chat main web site - www.rapierchat.com.  Download the ehscapture executable to any directory. Right mouse click on its icon, showen in an explorer window, and select send to ... and Desktop. This creates a shortcut icon on your desktop. To run ehscapture just double click this icon. You logon with your Rapier Chat username and password.  The multimedia features use the Java Media Framework (JMF). The latest version of which can be downloaded from http:// java.sun.com.   ', 'Rapier Chat', '4.0 Live Video and Audio', 'Live,Video,Audio,,Live,audio,video,feeds,are,available,via,seperate,download,see,Rapier,Chat,main,web,site,www,rapierchat,com,Download,ehscapture,executable,any,directory,Right,mouse,click,on,its,icon,showen,in,an,explorer,window,select,send,Desktop,This,creates,shortcut,your,desktop,To,run,just,double,You,logon,with,username,password,The,multimedia,features,use,Java,Media,Framework,(JMF),latest,version,of,which,can,be,downloaded,from,http,//,java,sun'),
(118, '5.0 Room Selection   To enter a chat room select the required room from the middle left list and press the Enter Room button. A status dialog will appear indicating the progress. If  this is the first time you have entered the room this sesson, then the room background will be loaded into memory. This can take a few seconds on slower dial up lines. The chat room will then be  initalized and displayed. If a sound has been defined for the room, this will now be loaded in the background. Hence the sound will start after a few seconds of being in the room. During this time you  can move your aviator and speak as normal.  If you have been invited to a users private room, you will see thier user name in the top left list. To enter one select a name from the list and press the Enter Private Room button. You will only be able to enter the users private room if the user is currently in their private room. The exception to this us when you enter your own private room, which will always be the first item on the chat room list.  To invite users to your private room, select the required users names from the bottom left list. Note this list is a multiple select list - to select more then one user, hold down the CTRL key while clicking on user names. Then press the Update Invites button, The database will then be updated and a status message displayed.   ', 'Rapier Chat', '5.0 Room Selection', 'Room,Selection,,To,enter,chat,room,select,required,from,middle,left,list,press,Enter,Room,button,A,status,dialog,will,appear,indicating,progress,If,first,time,you,entered,sesson,then,background,be,loaded,into,memory,This,can,take,few,seconds,on,slower,dial,up,lines,The,initalized,displayed,sound,has,been,defined,for,now,in,Hence,start,after,of,being,During,move,your,aviator,speak,normal,invited,users,private,see,thier,user,name,top,one,Private,You,only,able,if,currently,their,exception,us,when,own,which,always,item,invite,names,bottom,Note,multiple,more,hold,down,CTRL,key,while,clicking,Then,Update,Invites,database,updated,message'),
(119, '6.0 Chat Control Console   When you enter a chat room, the chat control console is displayed. This  is a floating window that can be moved to any location on the screen by dragging its title bar. It also  remembers its position between sessions. From here you can interact with that  chat session - entering chat text, cycling through the predefined standard chat  text strings, display the chat history window and spell checking the chat text.   ', 'Rapier Chat', '6.0 Chat Control Console', 'Chat,Control,Console,,When,you,enter,chat,room,control,console,displayed,This,floating,window,that,can,be,moved,any,location,on,screen,by,dragging,its,title,bar,It,also,remembers,position,between,sessions,From,here,interact,with,session,entering,text,cycling,through,predefined,standard,strings,display,history,spell,checking'),
(120, '7.0 AVI Console   When you left-click on an avaitor, the Avi Control Console dialog is displayed. This is a floating dialog that can be moved to any location on the screen by dragging its title bar. Left-click on another avaitor and the dialog will be automatically updated - this also results in the shadow and whisper status being reset to the default state of off. To hold a private conversation (whisper) with the avaitor, click the whisper box - the avaitors name block will change color to red. Any text you now speak - until you click the whisper box again - will apear in a red bubble that only you and the selected avaitor can see. If you are a monitor or admin, this dialog has two additional options - to ban users and to shadow users. When banning a user you will be prompted to enter a reason for the ban. The  banned user will be notified via the message system and removed from Rapier Chat - this procedure can take a few seconds to complete. You can also shadow a user - this allows you to control the users actions as if you were logged on as them. Shadowed users are displayed with a green name block.   ', 'Rapier Chat', '7.0 AVI Console', 'AVI,Console,,When,you,left,click,on,an,avaitor,Avi,Control,dialog,displayed,This,floating,that,can,be,moved,any,location,screen,by,dragging,its,title,bar,Left,another,will,automatically,updated,also,results,in,shadow,whisper,status,being,reset,default,state,of,off,To,hold,private,conversation,(whisper),with,box,avaitors,name,block,change,color,red,Any,text,now,speak,until,again,apear,bubble,only,selected,see,If,are,monitor,or,admin,has,two,additional,options,ban,users,banning,user,prompted,enter,reason,for,The,banned,notified,via,message,system,removed,from,Rapier,Chat,procedure,take,few,seconds,complete,You,allows,control,actions,if,were,logged,them,Shadowed,green'),
(121, '8.0 Chat History Dialog   This dialog displays a history of all chat text with each user displayed in a  different color. If an user speaks who does not yet appear in the dialog you will be asked if you wish to add that user or ignore all furture chat texts from that user. When you have added all the  users to the dialog you require, click the lock box and now only the added users will be updated until you click the lock box again.   ', 'Rapier Chat', '8.0 Chat History Dialog', 'Chat,History,Dialog,,This,dialog,displays,history,of,all,chat,text,with,each,user,displayed,in,different,color,If,an,speaks,who,does,yet,appear,you,will,be,asked,if,wish,add,that,or,ignore,furture,texts,from,When,added,users,require,click,lock,box,now,only,updated,until,again'),
(122, '9.0 Message Boards    ', 'Rapier Chat', '9.0 Message Boards', 'Message,Boards,'),
(123, '10.0 Private Rooms    ', 'Rapier Chat', '10.0 Private Rooms', 'Private,Rooms,'),
(124, '11.0 Virtual Rooms    ', 'Rapier Chat', '11.0 Virtual Rooms', 'Virtual,Rooms,'),
(125, '12.0 Chat History    ', 'Rapier Chat', '12.0 Chat History', 'Chat,History,'),
(126, '13.0 Room Editing    ', 'Rapier Chat', '13.0 Room Editing', 'Room,Editing,'),
(127, '14.0 Adminstration    ', 'Rapier Chat', '14.0 Adminstration', 'Adminstration,'),
(128, 'Appendix A - Release History   version 5.0 - (01/01/06)         Bug fixs and optimizations         Live sound         Text translations         IRQ / WAP enabled         Different language versions  version 4.5 - (01/12/05)         Bug fixs and optimizations         Virtual room backgrounds         Help database entries         Live video 		 version 4.0 - (01/11/05)         Bug fixs and optimizations         User shadowing         Private room group invite         Image buttons         Hotkey changes  version 3.5 - (01/11/05)         Bug fixs and optimizations         Message boards         Enhanced chat console         Search engine submission         PayPal subscription page  version 3.0 - (01/04/05)         Bug fixs and optimizations         Status window - user filtering          Basic chat console         Enchanced help desk         Edit proom details         Room thumbnails         Help & status admin tools  Version 2.5 - (01/11/04)         Bug fixs and optimizations         Chat buddies         The Professor         Spell checker         Group creation 		 version 2.2 - (01/10/04)         Bug fixs and optimizations         Dissallowed word list         Standard texts list         Quick access toolbar         Mail address book         IM to any user  version 2.1 - (01/09/04)         Bug fixs and optimizations         Optmized message queue  version 2.0 - (09/07/04)         Bug fixs and optimizations         Progress bar while applet loading         Status window - colors         User mailboxes         More user feedback 		 version 1.30 - 29/04/04         Bug fixs and optimizations         Faster loading time         Prototype user mailboxes  version 1.20 - 07/04/04         Bug fixs and optimizations         Status window         SWING User Interface  version 1.10 - 21/03/04         Bug fixs and optimizations  Version 1.00 - 15/03/04         Initial  release of Rapier Chat.   ', 'Rapier Chat', 'Appendix A - Release History', 'Appendix,A,Release,History,,version,(01/01/06),Bug,fixs,optimizations,Live,sound,Text,translations,IRQ,/,WAP,enabled,Different,language,versions,(01/12/05),Virtual,room,backgrounds,Help,database,entries,video,		,(01/11/05),User,shadowing,Private,group,invite,Image,buttons,Hotkey,changes,Message,boards,Enhanced,chat,console,Search,engine,submission,PayPal,subscription,page,(01/04/05),Status,window,user,filtering,Basic,Enchanced,help,desk,Edit,proom,details,Room,thumbnails,&,status,admin,tools,Version,(01/11/04),Chat,buddies,The,Professor,Spell,checker,Group,creation,(01/10/04),Dissallowed,word,list,Standard,texts,Quick,access,toolbar,Mail,address,book,IM,any,(01/09/04),Optmized,message,queue,(09/07/04),Progress,bar,while,applet,loading,colors,mailboxes,More,feedback,29/04/04,Faster,time,Prototype,07/04/04,SWING,Interface,21/03/04,15/03/04,Initial,release,of,Rapier'),
(129, 'Appendix B - Terms and Conditions   Members are solely responsible for  everything contained in their own Rapier  chat rooms. Rapier Chat does not verify,  endorse or otherwise vouch for the  contents of any rooms. Members can be  held legally liable for the contents  of their Rapier Chat rooms, and may be  held legally accountable if their  rooms include, for example, material  protected by copyright, trademark,  patent or trade secret law without the  permission of the author or owner or  defamatory comments.   The member agrees to indemnify and hold harmless Rapier Chat for any loss,  liability, claim, damage, and expenses (including reasonable attorneys?óÔé¼Ôäó fees)  arising from or in connection with the  contents of the members rooms. In the  event that Rapier Chat receives a complaint that the contents of a room infringe  intellectual property rights or otherwise is unlawful, Rapier Chat shall request  written substantiation of the complaint from the complaining party, for example,  substantiation of proof of ownership in the form of a trademark, service mark,  patent or copyright registration and will take such steps that it believes to  be appropriate under the circumstances.   Rapier Chat also reserves the following rights:   (1) To deny membership to offenders of these guidelines or terms of service  (2) To amend or change these guidelines at any time and without notice  (3) To use images of any part of Rapier Chat for promotional and other  commercial purposes.   We reserve the right to remove any rooms or users from the system which are  brought to our attention and which we find, in our sole discretion, violates  these Rapier Chat content guidelines, or otherwise is in violation of the law.  Rapier Chat is also not responsible for any loss of data resulting from our  deletion of rooms, network or system outages, file corruption or any other  reasons.   PLEASE NOTE...   Although we have used elements of scenes from both Holland and the Arlgarve Portugal as backgrounds within this chat site we would point out that the scenes you observe bare no resemblance  in either name or their geographical location within those countries. However in commissioning the photographic material  used, we were impressed by the help and assistance we received in both Holland ?â?ºand Portugal as to the abundance of scenic  beauty these two countries offer. We thereby can strongly recommend both countries as holiday destinations to our members, youll certainly not be disappointed.   COPYRIGHT NOTICE  Background images on this site may not be copied or reproduced without first seeking permission by Rapier Chat to whom sole copyright belongs unless otherwise indicated.  ', 'Rapier Chat', 'Appendix B - Terms and Conditions', 'Appendix,B,Terms,Conditions,,Members,are,solely,responsible,for,everything,contained,in,their,own,Rapier,chat,rooms,Chat,does,verify,endorse,or,otherwise,vouch,contents,of,any,can,be,held,legally,liable,may,accountable,if,include,example,material,protected,by,copyright,trademark,patent,trade,secret,law,without,permission,author,owner,defamatory,comments,The,member,agrees,indemnify,hold,harmless,loss,liability,claim,damage,expenses,(including,reasonable,attorneys?óÔé¼Ôäó,fees),arising,from,connection,with,members,In,event,that,receives,complaint,room,infringe,intellectual,property,rights,unlawful,shall,request,written,substantiation,complaining,party,proof,ownership,form,service,mark,registration,will,take,such,steps,it,believes,appropriate,under,circumstances,also,reserves,following,(1),To,deny,membership,offenders,these,guidelines,terms,(2),amend,change,time,notice,(3),use,images,part,promotional,other,commercial,purposes,We,reserve,right,remove,users,system,which,brought,our,attention,we,find,sole,discretion,violates,content,violation,data,resulting,deletion,network,outages,file,corruption,reasons,PLEASE,NOTE,Although,used,elements,scenes,both,Holland,Arlgarve,Portugal,backgrounds,within,site,would,point,out,you,observe,bare,no,resemblance,either,name,geographical,location,those,countries,However,commissioning,photographic,were,impressed,help,assistance,received,?â?ºand,abundance,scenic,beauty,two,offer,thereby,strongly,recommend,holiday,destinations,youll,certainly,disappointed,COPYRIGHT,NOTICE,Background,on,copied,reproduced,first,seeking,whom,belongs,unless,indicated'),
(130, '          ---------------  	    Techinical Development End House Software www.endhousesoftware.com   1995 - 2005 ', 'Rapier Chat', '', '*,,	,Techinical,Development,End,House,Software,www,endhousesoftware,com');

-- --------------------------------------------------------

--
-- Table structure for table `sysehsmessageboards`
--

CREATE TABLE `sysehsmessageboards` (
  `sysEHSMBID` int(11) NOT NULL,
  `sysEHSMBName` varchar(250) NOT NULL DEFAULT '',
  `sysEHSMBMsgOwner` varchar(250) NOT NULL DEFAULT '',
  `sysEHSMBMsgText` varchar(250) NOT NULL DEFAULT '',
  `sysEHSMBMsgDate` varchar(50) NOT NULL DEFAULT '',
  `sysEHSMBMsgTime` varchar(50) NOT NULL DEFAULT '',
  `sysEHSMBMsgThread` int(11) DEFAULT '-1',
  `sysEHSMBMsgTopic` varchar(250) NOT NULL DEFAULT '',
  `sysEHSMBMsgEmail` varchar(200) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds details of message board contents';

--
-- Dumping data for table `sysehsmessageboards`
--

INSERT INTO `sysehsmessageboards` (`sysEHSMBID`, `sysEHSMBName`, `sysEHSMBMsgOwner`, `sysEHSMBMsgText`, `sysEHSMBMsgDate`, `sysEHSMBMsgTime`, `sysEHSMBMsgThread`, `sysEHSMBMsgTopic`, `sysEHSMBMsgEmail`) VALUES
(1, 'End House Software', 'EHS', 'Welcome to EHS Message Boards', '10 November 2004', '10:00:00 CET', -1, 'Welcome', 'root@endhousesoftware.com'),
(2, 'Rapier Chat', 'EHS', 'Welcome to Rapier Chat', '10 November 2004', '10:00:00 CET', -1, 'Welcome', 'root@endhousesoftware.com'),
(3, 'HDL Work Bench', 'EHS', 'Welcome to HDL Work Bench', '10 November 2004', '10:00:00 CET', -1, 'Welcome', 'root@endhousesoftware.com');

-- --------------------------------------------------------

--
-- Table structure for table `sysehsmsg`
--

CREATE TABLE `sysehsmsg` (
  `sysEHSMsgID` int(11) NOT NULL,
  `sysEHSMsgEntity` varchar(100) NOT NULL,
  `sysEHSMsgType` varchar(10) NOT NULL,
  `sysEHSMsgParam1` varchar(200) DEFAULT NULL,
  `sysEHSMsgParam2` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sysehsnewsletteremails`
--

CREATE TABLE `sysehsnewsletteremails` (
  `sysEHSNewsletterEmailsID` int(11) NOT NULL,
  `sysEHSNewsletterEmailsEmail` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds email of people subscribed to newsletter';

-- --------------------------------------------------------

--
-- Table structure for table `sysehsproducts`
--

CREATE TABLE `sysehsproducts` (
  `sysEHSProdID` mediumint(9) NOT NULL,
  `sysEHSProdName` varchar(200) NOT NULL DEFAULT '',
  `sysEHSProdDescription` varchar(200) NOT NULL DEFAULT '',
  `sysEHSProdSerialBase` varchar(200) NOT NULL DEFAULT '',
  `sysEHSProdPrice` float NOT NULL DEFAULT '0',
  `sysEHSProdBeta` int(11) NOT NULL DEFAULT '0',
  `sysEHSProdTries` int(11) NOT NULL DEFAULT '0',
  `sysEHSProdVersion` varchar(13) NOT NULL DEFAULT '01.00.0000.00',
  `sysEHSProdUpdateURL` varchar(200) DEFAULT NULL,
  `sysEHSProdClient` int(11) NOT NULL DEFAULT '0',
  `sysEHSProdClientEmail` varchar(200) DEFAULT NULL,
  `sysEHSProdClientName` varchar(200) DEFAULT NULL,
  `sysEHSProdCode` varchar(10) DEFAULT NULL,
  `sysEHSProdAdminUser` varchar(50) DEFAULT NULL,
  `sysEHSProdAdminPassword` varchar(50) DEFAULT NULL,
  `sysEHSProdFeatures` int(11) NOT NULL DEFAULT '0',
  `sysEHSProdKBFilename` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Hold details of End House Software products';

--
-- Dumping data for table `sysehsproducts`
--

INSERT INTO `sysehsproducts` (`sysEHSProdID`, `sysEHSProdName`, `sysEHSProdDescription`, `sysEHSProdSerialBase`, `sysEHSProdPrice`, `sysEHSProdBeta`, `sysEHSProdTries`, `sysEHSProdVersion`, `sysEHSProdUpdateURL`, `sysEHSProdClient`, `sysEHSProdClientEmail`, `sysEHSProdClientName`, `sysEHSProdCode`, `sysEHSProdAdminUser`, `sysEHSProdAdminPassword`, `sysEHSProdFeatures`, `sysEHSProdKBFilename`) VALUES
(2, 'EHS Web Site', 'End House Software Website', 'EH1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, '0', 'ehsws', NULL, NULL, 0, ''),
(3, 'End House Software Client', 'End House Software Client Account', 'BA1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, '0', 'clientacc', NULL, NULL, 0, ''),
(4, 'HDL Work Bench', 'HDL Work Bench', 'WB1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 0, NULL, '0', 'hdlwb', 'admin', 'hdlwb123', 0, 'hdl_kb.txt'),
(6, 'Rapier Chat', 'Rapier Chat', 'RC1000', 0, 0, 0, '05.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, '0', 'rc', 'admin', 'rc123', 1, 'rc_kb.txt'),
(7, 'Audio and Video Capture', 'Real Time Audio and Video Capture', 'CP1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, '0', 'EHS0010', NULL, NULL, 0, ''),
(8, 'Test Buy Product', 'Test Buy Product', 'TB1000', 5, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, '0', NULL, NULL, NULL, 0, ''),
(12, 'Client Web Page', 'Client Web Page', 'CW1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, NULL, 'clientwp', NULL, NULL, 0, ''),
(13, 'Lauries Collectors Corner', 'Lauries Collectors Corner QA', 'CC1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, NULL, 'acqa', 'admin', 'lcc123', 0, ''),
(15, 'UML Analyser', 'UML Anaylser Tool', 'UD1000', 0, 0, 0, '01.20.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 0, NULL, NULL, 'umldiag', 'admin', 'uml123', 0, 'uml_kb.txt'),
(16, 'Admin Control Panel', 'Admin Control Panel Application', 'AC1000', 0, 0, 0, '02.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, '', '', 'acp', 'admin', 'acp123', 1, ''),
(17, 'Technology  Exchange', 'Technology  Exchange', 'TE1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, NULL, 'techex', 'admin', 'techex123', 0, ''),
(18, 'Heritage Log Home Rentals', 'Heritage Log Home Rentals', 'LC1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, NULL, 'hlhr', 'admin', 'hlhr123', 0, ''),
(19, 'Choose Spain Properties', 'Choose Spain Properties', 'SP1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, NULL, 'rps', 'admin', 'rps123', 0, ''),
(20, 'Links Database', 'Links Database', 'LD1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, NULL, 'ld', 'admin', 'ld123', 0, ''),
(22, 'JAVA Template', 'JAVA Template', 'TA1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, NULL, 'tem', 'admin', 'tem123', 0, 'tem_kb.txt'),
(23, 'The Real Aladdins', 'The Real Aladdins Magazine', 'RA1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, NULL, 'tra', 'admin', 'tra123', 0, ''),
(26, 'Project Tracker', 'Project Tracker', 'DT1000', 0, 0, 0, '01.10.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, NULL, 'pt', 'admin', 'pt123', 0, 'pt_kb.txt'),
(25, 'Aladdins Pedreguer WebSite', 'Aladdins Pedreguer WebSite', 'AP1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, '0', 'alp', 'admin', 'alp123', 0, ''),
(27, 'Search Tracker', 'Search tracker', 'ST1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 1, NULL, NULL, 'st', 'admin', 'st123', 0, ''),
(28, 'End House Software', 'End House Software', 'ES1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 0, 'endhousesoftware999@gmail.com', 'End House Software', 'ehs', NULL, NULL, 0, NULL),
(29, 'Chatter Box', 'New generation chat system', 'CB1000', 0, 0, 0, '01.00.0000.00', 'http://endhousesoftware.heliohost.org/endhousesoftware/website/downloads.htm', 0, NULL, NULL, 'cb', 'admin', 'cb123', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sysehsregistrations`
--

CREATE TABLE `sysehsregistrations` (
  `sysEHSRegID` int(11) NOT NULL,
  `sysEHSRegName` varchar(200) NOT NULL DEFAULT '',
  `sysEHSRegAddr` varchar(200) DEFAULT '""',
  `sysEHSRegTel` varchar(200) DEFAULT '""',
  `sysEHSRegFax` varchar(200) DEFAULT '""',
  `sysEHSRegEmail` varchar(200) NOT NULL DEFAULT '',
  `sysEHSRegProduct` varchar(200) NOT NULL DEFAULT '',
  `sysEHSRegSerial` varchar(200) NOT NULL DEFAULT '',
  `sysEHSRegPassword` varchar(200) NOT NULL DEFAULT '',
  `sysEHSRegUserName` varchar(200) NOT NULL DEFAULT '',
  `sysEHSRegDate` varchar(200) DEFAULT '""',
  `sysEHSRegTime` varchar(200) DEFAULT '""',
  `sysEHSRegPayMethod` varchar(100) DEFAULT '""',
  `sysEHSRegPayRef` varchar(100) DEFAULT '""',
  `sysEHSRegFailedLogons` int(11) DEFAULT '0',
  `sysEHSRegLogonDate` varchar(25) DEFAULT '""',
  `sysEHSRegLogonTime` varchar(25) DEFAULT '""',
  `sysEHSRegUses` int(11) DEFAULT '0',
  `sysEHSRegExpired` int(11) DEFAULT '0',
  `sysEHSRegVersion` varchar(13) NOT NULL DEFAULT '00.00.0000.00',
  `sysEHSRegActive` int(11) NOT NULL DEFAULT '1',
  `sysEHSRegData` varchar(250) DEFAULT '""',
  `sysEHSRegQuestion` varchar(250) DEFAULT NULL,
  `sysEHSRegCredit` int(11) NOT NULL DEFAULT '0',
  `sysEHSRegUnique` varchar(200) DEFAULT NULL,
  `sysEHSRegReceiveEmailUpdates` int(11) NOT NULL DEFAULT '1',
  `sysEHSRegEmailValidated` int(11) NOT NULL DEFAULT '0',
  `sysEHSRegToken` varchar(50) DEFAULT NULL,
  `sysEHSRegTag` varchar(250) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds details of End House Software registrartions';

--
-- Dumping data for table `sysehsregistrations`
--

INSERT INTO `sysehsregistrations` (`sysEHSRegID`, `sysEHSRegName`, `sysEHSRegAddr`, `sysEHSRegTel`, `sysEHSRegFax`, `sysEHSRegEmail`, `sysEHSRegProduct`, `sysEHSRegSerial`, `sysEHSRegPassword`, `sysEHSRegUserName`, `sysEHSRegDate`, `sysEHSRegTime`, `sysEHSRegPayMethod`, `sysEHSRegPayRef`, `sysEHSRegFailedLogons`, `sysEHSRegLogonDate`, `sysEHSRegLogonTime`, `sysEHSRegUses`, `sysEHSRegExpired`, `sysEHSRegVersion`, `sysEHSRegActive`, `sysEHSRegData`, `sysEHSRegQuestion`, `sysEHSRegCredit`, `sysEHSRegUnique`, `sysEHSRegReceiveEmailUpdates`, `sysEHSRegEmailValidated`, `sysEHSRegToken`, `sysEHSRegTag`) VALUES
(30, 'Gavin Baker', 'End House Software', '96 645 7291', '', 'endhousesoftware@hotmail.com', 'HDL Work Bench', 'EHS-WB1000-P00-00ZZ', 'joide33', 'gavin', '2012-01-15', '20:27:43', '\"\"', '\"\"', 0, '01/03/15', '13:13', 0, 0, '01.00.0000.00', 1, '\"\"', NULL, 0, '1179', 1, 1, '1234ABCD', NULL),
(31, 'Gavin Baker', 'End House Software', '0208 660 6662', '', 'endhousesoftware@hotmail.com', 'Project Tracker', 'EHS-DT1000-P00-00ZZ', 'joide33', 'gavin', '2012-06-11', '22:32:02', '\"\"', '\"\"', 0, '\"\"', '\"\"', 0, 0, '01.00.0000.00', 1, '\"\"', NULL, 0, '1179', 1, 0, NULL, NULL),
(32, 'Gavin Baker', 'End House Software', '96 645 7291', NULL, 'endhousesoftware@hotmail.com', 'UML Analyser', 'EHS-UD1000-P00-00ZZ', 'joide33', 'gavin', '2013-02-14', '22:00:00', '\"\"', '\"\"', 0, '29/08/14', '14:44', 0, 0, '00.00.0000.00', 1, '\"\"', NULL, 0, '1179', 1, 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sysehsreports`
--

CREATE TABLE `sysehsreports` (
  `sysEHSRepID` int(11) NOT NULL,
  `sysEHSRepOwnerID` int(11) NOT NULL DEFAULT '0',
  `sysEHSRepName` varchar(100) NOT NULL DEFAULT '',
  `sysEHSRepType` varchar(100) NOT NULL DEFAULT '',
  `sysEHSRepDesc` varchar(100) NOT NULL DEFAULT '',
  `sysEHSRepProduct` varchar(100) NOT NULL DEFAULT '',
  `sysEHSRepVersion` varchar(100) NOT NULL DEFAULT '',
  `sysEHSRepDate` varchar(100) NOT NULL DEFAULT '',
  `sysEHSRepEmail` varchar(100) DEFAULT '""',
  `sysEHSRepTicket` varchar(100) DEFAULT '""',
  `sysEHSRepDateFinished` varchar(100) DEFAULT '""',
  `sysEHSRepResponse` varchar(200) DEFAULT NULL,
  `sysEHSRepDept` varchar(200) NOT NULL DEFAULT '',
  `sysEHSRepPrioity` int(11) DEFAULT '5',
  `sysEHSRepUser` varchar(200) NOT NULL DEFAULT '',
  `sysEHSRepStatus` varchar(100) DEFAULT 'unassigned',
  `sysEHSRepDueDate` varchar(50) DEFAULT NULL,
  `sysEHSRepFixedVersion` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Hold details of End House Software reports';

--
-- Dumping data for table `sysehsreports`
--

INSERT INTO `sysehsreports` (`sysEHSRepID`, `sysEHSRepOwnerID`, `sysEHSRepName`, `sysEHSRepType`, `sysEHSRepDesc`, `sysEHSRepProduct`, `sysEHSRepVersion`, `sysEHSRepDate`, `sysEHSRepEmail`, `sysEHSRepTicket`, `sysEHSRepDateFinished`, `sysEHSRepResponse`, `sysEHSRepDept`, `sysEHSRepPrioity`, `sysEHSRepUser`, `sysEHSRepStatus`, `sysEHSRepDueDate`, `sysEHSRepFixedVersion`) VALUES
(4, 4, 'HDL Work Bench Registration', 'Registration', 'HDL Work Bench Registration', 'HDL Work Bench', '01.00.0000.00', '09 May 2012', 'endhousesoftware@hotmail.com', 'EHS-1007-09-05-12', '', '', 'Admin', 5, 'gavin', 'Coding', NULL, NULL),
(3, 3, 'Project Tracker Registration', 'Registration', 'Project Tracker Registration', 'Project Tracker', '01.00.0000.00', '09 May 2012', 'endhousesoftware@hotmail.com', 'EHS-1006-09-05-12', '\"\"', '', 'Admin', 5, 'gavin', 'On Order', NULL, NULL),
(5, 5, 'UML Analyser Registration', 'Registration', 'UML Analyser Registration', 'UML Analyser', '01.00.0000.00', '09 May 2012', 'endhousesoftware@hotmail.com', 'EHS-1008-09-05-12', NULL, NULL, 'Admin', 5, 'gavin', 'Closed', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sysehstasks`
--

CREATE TABLE `sysehstasks` (
  `sysEHSTasksID` int(11) NOT NULL,
  `sysEHSTasksTitle` varchar(200) NOT NULL,
  `sysEHSTasksDate` date NOT NULL,
  `sysEHSTasksTime` time NOT NULL,
  `sysEHSTasksDuration` int(11) DEFAULT '1',
  `sysEHSTasksProduct` varchar(200) NOT NULL,
  `sysEHSTasksAlarm` int(11) DEFAULT '0',
  `sysEHSTasksPrivate` int(11) DEFAULT '0',
  `sysEHSTasksConfirmed` int(11) DEFAULT '0',
  `sysEHSTasksDescription` varchar(200) NOT NULL,
  `sysEHSTasksOwner` varchar(200) NOT NULL,
  `sysEHSTasksEmail` varchar(200) DEFAULT NULL,
  `sysEHSTasksAppID` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds details of taks';

--
-- Dumping data for table `sysehstasks`
--

INSERT INTO `sysehstasks` (`sysEHSTasksID`, `sysEHSTasksTitle`, `sysEHSTasksDate`, `sysEHSTasksTime`, `sysEHSTasksDuration`, `sysEHSTasksProduct`, `sysEHSTasksAlarm`, `sysEHSTasksPrivate`, `sysEHSTasksConfirmed`, `sysEHSTasksDescription`, `sysEHSTasksOwner`, `sysEHSTasksEmail`, `sysEHSTasksAppID`) VALUES
(16, 'title text', '2014-04-29', '09:33:19', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(15, 'title text', '2014-04-29', '09:32:36', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(14, 'title text', '2014-04-29', '09:20:23', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(13, 'title text', '2014-04-29', '09:18:15', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(12, 'title text', '2014-04-29', '09:15:44', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(11, 'title text', '2014-04-29', '09:15:31', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(10, 'title text', '2014-04-29', '09:15:27', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(9, 'title text', '2014-04-27', '01:37:01', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(17, 'title text', '2014-04-29', '09:34:51', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(18, 'title text', '2014-04-29', '09:36:34', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(19, 'title text', '2014-04-29', '09:36:40', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(20, 'title text', '2014-04-29', '09:36:55', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(21, 'title text', '2014-04-29', '09:45:10', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(22, 'title text', '2014-04-29', '09:45:36', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(23, 'title text', '2014-04-29', '09:45:42', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(24, 'title text', '2014-04-29', '09:48:37', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(25, 'title text', '2014-04-29', '09:52:32', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(26, 'title text', '2014-04-29', '09:56:32', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(27, 'title text', '2014-04-30', '02:53:45', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(28, 'title text', '2014-04-30', '02:53:49', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(29, 'title text', '2014-04-30', '02:53:58', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(30, 'title text', '2014-04-30', '02:55:42', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL),
(31, 'title text', '2014-04-30', '03:00:54', 1, 'productname', 0, 0, 0, 'have sex with jo', 'gavin', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sysehstotalwebstats`
--

CREATE TABLE `sysehstotalwebstats` (
  `sysEHSTWSID` int(11) NOT NULL,
  `sysEHSTWSProduct` varchar(50) NOT NULL DEFAULT '',
  `sysEHSTWSDate` varchar(50) NOT NULL DEFAULT '',
  `sysEHSTWSCount` int(50) DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds aggrate web stats for each product';

--
-- Dumping data for table `sysehstotalwebstats`
--

-- --------------------------------------------------------

--
-- Table structure for table `sysehsvariables`
--

CREATE TABLE `sysehsvariables` (
  `sysEHSVarID` int(11) NOT NULL,
  `sysEHSVarName` varchar(200) NOT NULL DEFAULT '',
  `sysEHSVarValue` varchar(200) NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Hold details of End House Software variables';

--
-- Dumping data for table `sysehsvariables`
--

-- --------------------------------------------------------

--
-- Table structure for table `sysehswebstats`
--

CREATE TABLE `sysehswebstats` (
  `sysEHSWSID` int(11) NOT NULL,
  `sysEHSWSProduct` varchar(100) NOT NULL DEFAULT '',
  `sysEHSWSIP` varchar(100) NOT NULL DEFAULT '',
  `sysEHSWSReverse` varchar(100) DEFAULT NULL,
  `sysEHSWSDate` varchar(50) NOT NULL DEFAULT '',
  `sysEHSWSTime` varchar(50) NOT NULL DEFAULT '',
  `sysEHSWSRef` varchar(100) DEFAULT NULL,
  `sysEHSWSOwnHit` int(11) DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds all web stats';

--
-- Dumping data for table `sysehswebstats`
--

-- --------------------------------------------------------

--
-- Table structure for table `sysmessagelog`
--

CREATE TABLE `sysmessagelog` (
  `LogMsgID` int(11) NOT NULL,
  `LogMsgText` varchar(255) NOT NULL DEFAULT '',
  `LogMsgDate` varchar(50) NOT NULL DEFAULT '',
  `LogMsgTime` varchar(50) NOT NULL DEFAULT '',
  `LogMsgProduct` varchar(150) NOT NULL DEFAULT '',
  `LogMsgUser` varchar(150) NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Log of system messages';

--
-- Dumping data for table `sysmessagelog`
--

--
-- Indexes for table `sysehsproducts`
--
ALTER TABLE `sysehsproducts`
  ADD PRIMARY KEY (`sysEHSProdID`),
  ADD UNIQUE KEY `sysEHSProdID` (`sysEHSProdID`),
  ADD KEY `sysEHSProdID_2` (`sysEHSProdID`);

--
-- Indexes for table `sysehsregistrations`
--
ALTER TABLE `sysehsregistrations`
  ADD PRIMARY KEY (`sysEHSRegID`),
  ADD UNIQUE KEY `sysEHSRegID` (`sysEHSRegID`);

--
-- Indexes for table `sysehsreports`
--
ALTER TABLE `sysehsreports`
  ADD PRIMARY KEY (`sysEHSRepID`),
  ADD UNIQUE KEY `sysEHSRepID` (`sysEHSRepID`),
  ADD KEY `sysEHSRepID_2` (`sysEHSRepID`);

--
-- Indexes for table `sysehstasks`
--
ALTER TABLE `sysehstasks`
  ADD PRIMARY KEY (`sysEHSTasksID`);

--
-- Indexes for table `sysehstotalwebstats`
--
ALTER TABLE `sysehstotalwebstats`
  ADD UNIQUE KEY `sysEHSTWSID` (`sysEHSTWSID`);

--
-- Indexes for table `sysehsvariables`
--
ALTER TABLE `sysehsvariables`
  ADD UNIQUE KEY `sysEHSVarID` (`sysEHSVarID`);

--
-- Indexes for table `sysehswebstats`
--
ALTER TABLE `sysehswebstats`
  ADD UNIQUE KEY `sysEHSWSID` (`sysEHSWSID`);

--
-- Indexes for table `sysmessagelog`
--
ALTER TABLE `sysmessagelog`
  ADD PRIMARY KEY (`LogMsgID`),
  ADD UNIQUE KEY `LogMsgID` (`LogMsgID`),
  ADD KEY `LogMsgID_2` (`LogMsgID`);

--
-- AUTO_INCREMENT for table `chatvariables`
--
ALTER TABLE `chatvariables`
  MODIFY `chatVariableID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3262;

--
-- AUTO_INCREMENT for table `ehswebringitem`
--
ALTER TABLE `ehswebringitem`
  MODIFY `ehsWebRingItemID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ehswebrings`
--
ALTER TABLE `ehswebrings`
  MODIFY `ehsWebRingID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `hdlwbforces`
--
ALTER TABLE `hdlwbforces`
  MODIFY `hdlWBForcesID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=588;

--
-- AUTO_INCREMENT for table `hdlwbmappings`
--
ALTER TABLE `hdlwbmappings`
  MODIFY `hdlWBMappingsID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=704;

--
-- AUTO_INCREMENT for table `hdlwbparameters`
--
ALTER TABLE `hdlwbparameters`
  MODIFY `hdlWBParametersID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=120;

--
-- AUTO_INCREMENT for table `hdlwbprocesses`
--
ALTER TABLE `hdlwbprocesses`
  MODIFY `hdlWBProcessesID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=179;

--
-- AUTO_INCREMENT for table `hdlwbsignals`
--
ALTER TABLE `hdlwbsignals`
  MODIFY `hdlWBSignalsID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=759;

--
-- AUTO_INCREMENT for table `linkscatdescriptions`
--
ALTER TABLE `linkscatdescriptions`
  MODIFY `catID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `linkslinkdescriptions`
--
ALTER TABLE `linkslinkdescriptions`
  MODIFY `linkID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=707;

--
--
-- AUTO_INCREMENT for table `sdcdrawingitems`
--
ALTER TABLE `sdcdrawingitems`
  MODIFY `sdcDIID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;

--
-- AUTO_INCREMENT for table `sysehsbanners`
--
ALTER TABLE `sysehsbanners`
  MODIFY `sysBanID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `sysehsdepartments`
--
ALTER TABLE `sysehsdepartments`
  MODIFY `sysEHSDeptID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sysehshelptopics`
--
ALTER TABLE `sysehshelptopics`
  MODIFY `sysEHSHelpID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;

--
-- AUTO_INCREMENT for table `sysehsmessageboards`
--
ALTER TABLE `sysehsmessageboards`
  MODIFY `sysEHSMBID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sysehsmsg`
--
ALTER TABLE `sysehsmsg`
  MODIFY `sysEHSMsgID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sysehsproducts`
--
ALTER TABLE `sysehsproducts`
  MODIFY `sysEHSProdID` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `sysehsregistrations`
--
ALTER TABLE `sysehsregistrations`
  MODIFY `sysEHSRegID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `sysehsreports`
--
ALTER TABLE `sysehsreports`
  MODIFY `sysEHSRepID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `sysehstasks`
--
ALTER TABLE `sysehstasks`
  MODIFY `sysEHSTasksID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `sysehstotalwebstats`
--
ALTER TABLE `sysehstotalwebstats`
  MODIFY `sysEHSTWSID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8932;

--
-- AUTO_INCREMENT for table `sysehsvariables`
--
ALTER TABLE `sysehsvariables`
  MODIFY `sysEHSVarID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sysehswebstats`
--
ALTER TABLE `sysehswebstats`
  MODIFY `sysEHSWSID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4724;

--
-- AUTO_INCREMENT for table `sysmessagelog`
--
ALTER TABLE `sysmessagelog`
  MODIFY `LogMsgID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=212;

