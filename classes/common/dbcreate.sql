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
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Holds registration details';

--
-- Dumping data for table `sysehsregistrations`
--

INSERT INTO `sysehsregistrations` (`sysEHSRegID`, `sysEHSRegName`, `sysEHSRegAddr`, `sysEHSRegTel`, `sysEHSRegFax`, `sysEHSRegEmail`, `sysEHSRegProduct`, `sysEHSRegSerial`, `sysEHSRegPassword`, `sysEHSRegUserName`, `sysEHSRegDate`, `sysEHSRegTime`, `sysEHSRegPayMethod`, `sysEHSRegPayRef`, `sysEHSRegFailedLogons`, `sysEHSRegLogonDate`, `sysEHSRegLogonTime`, `sysEHSRegUses`, `sysEHSRegExpired`, `sysEHSRegVersion`, `sysEHSRegActive`, `sysEHSRegData`, `sysEHSRegQuestion`, `sysEHSRegCredit`, `sysEHSRegUnique`, `sysEHSRegReceiveEmailUpdates`, `sysEHSRegEmailValidated`, `sysEHSRegToken`, `sysEHSRegTag`) VALUES
(1, 'Admin', 'Your Company', 'Your Telephone Number', 'Your Fax Number', 'Your Email Address', 'HDL Work Bench', 'EHS-WB1000-P00-00ZZ', 'admin', 'admin', '2012-01-15', '20:27:43', '\"\"', '\"\"', 0, '01/03/15', '13:13', 0, 0, '01.00.0000.00', 1, '\"\"', NULL, 0, '1179', 1, 1, '1234ABCD', NULL),(2, 'Admin', 'Your Company', 'Your Telephone Number', 'Your Fax Number', 'Your Email Address', 'UML Work Bench', 'EHS-UD1000-P00-00ZZ', 'admin', 'admin', '2012-01-15', '20:27:43', '\"\"', '\"\"', 0, '01/03/15', '13:13', 0, 0, '01.00.0000.00', 1, '\"\"', NULL, 0, '1179', 1, 1, '1234ABCD', NULL),(3, 'Admin', 'Your Company', 'Your Telephone Number', 'Your Fax Number', 'Your Email Address', 'Project Tracker', 'EHS-DT1000-P00-00ZZ', 'admin', 'admin', '2012-01-15', '20:27:43', '\"\"', '\"\"', 0, '01/03/15', '13:13', 0, 0, '01.00.0000.00', 1, '\"\"', NULL, 0, '1179', 1, 1, '1234ABCD', NULL);

--
-- Table structure for table `sysehsvariables`
--

CREATE TABLE `sysehsvariables` (
  `varID` int(11) NOT NULL,
  `varName` varchar(200) NOT NULL DEFAULT '',
  `varValue` varchar(200) NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Hold details of End House Software variables';

--
-- Indexes for table `sysehsregistrations`
--
ALTER TABLE `sysehsregistrations`
  ADD PRIMARY KEY (`sysEHSRegID`),
  ADD UNIQUE KEY `sysEHSRegID` (`sysEHSRegID`);

--
-- Indexes for table `sysehsvariables`
--
ALTER TABLE `sysehsvariables`
  ADD UNIQUE KEY `sysEHSVarID` (`sysEHSVarID`);

--
-- AUTO_INCREMENT for table `hdlwbforces`
--
ALTER TABLE `hdlwbforces`
  MODIFY `hdlWBForcesID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `hdlwbmappings`
--
ALTER TABLE `hdlwbmappings`
  MODIFY `hdlWBMappingsID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `hdlwbparameters`
--
ALTER TABLE `hdlwbparameters`
  MODIFY `hdlWBParametersID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `hdlwbprocesses`
--
ALTER TABLE `hdlwbprocesses`
  MODIFY `hdlWBProcessesID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `hdlwbsignals`
--
ALTER TABLE `hdlwbsignals`
  MODIFY `hdlWBSignalsID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
--
-- AUTO_INCREMENT for table `sdcdrawingitems`
--
ALTER TABLE `sdcdrawingitems`
  MODIFY `sdcDIID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `sysehsregistrations`
--
ALTER TABLE `sysehsregistrations`
  MODIFY `sysEHSRegID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sysehsvariables`
--
ALTER TABLE `sysehsvariables`
  MODIFY `sysEHSVarID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
