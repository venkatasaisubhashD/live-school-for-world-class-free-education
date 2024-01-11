Live School For World-Class Free Education 

Read-Me Document

6.1 Eclipse IDE Installation

6.1.1 Downloading Eclipse

Visit the officiall Eclipse website:https://www.eclipse.org/downloads/).


Select the version compatible with your operating system (e.g., Windows 64-bit, macOS, Linux).

Click the "Download" button associated with your choice.

6.1.2 Installing Eclipse

 - After the download completes, locate the downloaded file.
	
 - Extract the downloaded archive to a designated location on your computer (e.g., C:\Program Files for Windows).
	
 - Eclipse doesn't require a formal installation process; it's a portable application that runs directly from its folder.
	
 - Launch Eclipse.

6.1.3 Navigating to Eclipse Folder
	
 - Inside the Eclipse folder, double-click on the executable file named eclipse (or eclipse.exe for Windows) to initiate Eclipse.


 6.1.4 Setting Up Workspace

 - Upon the first launch, Eclipse will prompt you to choose a workspace directory for storing your projects.

 - Choose an appropriate folder location and click "Launch" to proceed.

6.1.5 Java Development Kit (JDK) Setup

 - Ensure the Java Development Kit (JDK) is installed on your system. If not, download and install it from Oracle's website.
	
 - Open Eclipse, go to Window > Preferences.
	
 - Navigate to Java > Installed JREs, and add your installed JDK by clicking "Add" and selecting the JDK folder.

6.2 MySQL Connector Installation

6.2.1 Downloading MySQL Connector/J
	
 -Visit the MySQL Connector/J download page: [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/).
	
 - Choose the appropriate platform and download the ZIP archive.


6.2.2 Extracting MySQL Connector/J

 - Once the download finishes, locate the downloaded ZIP file.
	
 - Extract the contents of the ZIP archive to a known location on your computer.


6.2.3 Adding MySQL Connector/J to Eclipse Project

 - Open Eclipse IDE where your project is located.
	
 - Right-click on your project in the Project Explorer, select Properties.
	
 - Navigate to Java Build Path > Libraries > Add External JARs.
	
 - Browse and select the mysql-connector-java-x.x.xx.jar file you extracted earlier.
	
 - Click "Apply" and then "OK" to add MySQL Connector/J to your project's build path.

6.3 XAMPP Installation

6.3.1 Downloading XAMPP
	
 -Visit the official XAMPP website:[XAMPPOfficial Website](https://www.apachefriends.org/index.html).
	
 - Download the version suitable for your operating system (e.g., Windows, macOS, Linux).


6.3.2 Installing XAMPP

 - Once the download is complete, locate the downloaded file and run the installer.
	
 - Follow the provided installation instructions.
	
 - Choose the components to install (Apache, MySQL, PHP, etc.).
	
 - Select the installation directory (usually the default is recommended).
	
 - Complete the installation by clicking "Next" and then "Finish."

6.3.3 Setting up MySQL in XAMPP
	
 - Start XAMPP and launch MySQL from the XAMPP Control Panel.
	
 - Open a web browser and go to http://localhost/phpmyadmin/.
	
 - Log in using the default username (root) and leave the password field empty (if no password was set during XAMPP installation).

6.4 Creating a Database

 - Inside phpMyAdmin, click on the "Databases" tab.
	
 - Enter a name for your database in the "Create database" field and choose a collation (usually utf8_general_ci).
	
 - Click "Create" to create the database.

6.5 Setting Up Eclipse for Dynamic Web Project
	
 - Open Eclipse IDE if not already open.
	
 - Create a Dynamic Web Project by going to File > New > Dynamic Web Project.
	
 - Enter a project name, select the target runtime (e.g., Apache Tomcat), and set the Dynamic web module version.
	
 - Click "Next" and configure additional settings if necessary.
	
 - Click "Finish" to create the Dynamic Web Project.

6.5.1 Importing Your Project
	
 - If your project is not within the Eclipse workspace, go to File > Import.
	
 - Choose General > Existing Projects into Workspace.
	
 - Click "Next," select the project root directory, and click "Finish" to import it into Eclipse.

6.5.2 Configuring Apache Tomcat Server

 - Go to the Servers tab at the bottom of the Eclipse window (if not visible, go to Window > Show View > Servers).
	
 - Right-click in the Servers tab area and select New > Server.
	
 - Choose the installed Apache Tomcat version and click "Next."

6.5.3 Set Tomcat Installation Directory
	
 - Click "Browse" next to the "Tomcat installation directory" field.
	
 - Navigate to the Apache Tomcat installation directory and select it.
	
 - Click "Finish" to add the Tomcat server to Eclipse.

6.5.4 Configure Project to Use Tomcat
	
 - Right-click on your Dynamic Web Project in Eclipse.
	
 - Select Properties > Project Facets.
	
 - Check the box for "Dynamic Web Module" and click "Further configuration available..."
	
 - Choose the installed Apache Tomcat server from the dropdown and click "OK."
	
 - Click "Apply" and then "OK" to close the project properties.

6.5.5 Running Your Project

 - Right-click on your project in Eclipse.
	
 - Select Run As > Run on Server.
	
 - Choose the configured Tomcat server and click "Finish" to deploy and run your project.

6.5.6 Accessing Your Project
	
 - Once your project is running on Tomcat, open a web browser.

 - Enter the URL provided in the Console window of Eclipse (usually http://localhost:8080/FarmToFork).

Project
1.	In the front page we have to click on the Register/Login button in the left down
2.	We enter to the login page if we already have an account we can directly login into the application. If you not have an account you have to register in the application 
3.	In the register we have the option to choose to register as a student or teacher 
4.	If you are a student you can search the teachers by there subject or by there name in there username in application  and you can see the homework given by the teacher’s and see the notification shared by teacher’s if you enrolled to that teacher and access there files uploaded by the teacher
5.	If there no need the account there can delete the account 
6.	In teacher’s side there can upload the files and send the notification to the student and assign the homework to the student’s until the teacher start the session the student can’t join the session 
7.	And teacher can also delete the account if there don’t need
8.	We have the chat option in the session for both student and teacher for interaction
