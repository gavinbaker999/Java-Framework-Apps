# Java Framework Applications

A collection of applications built using the End House Software Java Framework.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To run locally you need the [Java JRE/JDK](https://www.java.com/en/download/) , [Ant Build Tool](https://ant.apache.org/bindownload.cgi) and the [MySQL server](https://dev.mysql.com/downloads/) to be installed. 

### Database Structure

Use the **classes/common/dbcreate.sql** file to create the **MySQL** database and tables (you will need to edit the **dbcreate.sql** file to change the database name), and then place the database name, username and password in the following environment variables **LOCALDBNAME**, **LOCALDBUSER** and **LOCALDBPASSWORD**. Note: The database host is assumed to be **localhost** and the database table names can not be changed.

## Build Procedures

The following describes the build procedures.

###Common Files:

**classes/common/ehsContants.java** - Constants and Variables definitions bridge file between the application code and JAVA framework code.

**classes/common/syntaxeditorpane.css** - Style sheet used in the JAVA framework color syntx editor control.

**classes/common/ttkey.txt** - Key for the translation table editor.

**classes/common/containerclasses.dat** - CSV list of container class names used in the UML Workbench application.

**classes/common/dbcreate.sql** - SQL statment to create MySQL database used by the JAVA framework.

**classes/common/drawingCanvas.java** - Java framework drawing canvas class (has to exist at the base application directory level).

**classes/common/transtableeditor.java** - Java framework translate table editor classes (has to exist at the base application directory level).

### Command Line Build:

To build the Java framework or applications via the command line us the following **Ant** command lines:

Compile Java framework JAR files (run from the **eclipse_workspace** directory).

	$ant generateehsjars

Compile and build application JAR file (run from the application's base directory - assuming one level below **eclipse_workspace** directory).

	$ant -buildfile ..\build.xml compile -Dappname=umldiag -Dmainclass=umldiag -Dappdir=umldiag
	$ant -buildfile ..\build.xml jar

To run the application,

	$java -jar umldiag.jar <args>

###Support Batch Files

The following batch files exist to run the HDL workbench, the UML workbench and the example Template application.

* hdlwb [status] [gui] [trace] [symbolfump] [about] filename
* umlwb [status] [gui] [trace] [symbolfump] [about] filename
* temlate [status] [gui] [trace] [symbolfump] [about]

## Built With

* [Visual Studio Code](https://code.visualstudio.com/
) - IDE enviroment.
* [Eclipse Editor](www.eclipse.org) - IDE enviroment.
* [Maven](https://maven.apache.org/) - Dependency Management


## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/gavinbaker999/073f0f50ac7995f32862cc407f649b5a) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning.  

## Authors

* **Gavin Baker** - *Initial work* - [End House Software](endhousesoftware.byethost11.com).


## License

This project is licensed under the MIT License - see the [LICENSE](https://gist.github.com/gavinbaker999/3609836877901fa5d449138988fe1d28) file for details.

## Acknowledgments

First and foremost many thanks to the support of family and friends. Any code adapted from public available sources have the appropriate acknowledgements documented in source code comments.

