Live School For World-Class Free Education 

Read-Me Document

Installing Eclipse IDE

Download Eclipse
1.	Visit the Eclipse official website at https://www.eclipse.org/downloads/.
2.	Choose the appropriate version based on your operating system (e.g., Windows 64-bit, macOS, Linux).
3.	Click on the "Download" button corresponding to your choice.

install Eclipse
1.	Once the download completes, locate the downloaded file.
2.	Extract the downloaded archive file to a location on your computer (e.g., C:\Program Files for Windows).
3.	There is no formal installation process for Eclipse; it's a portable application that runs directly from its folder.
4.	Launch Eclipse

Navigate to the folder where you extracted Eclipse
1.	Inside the Eclipse folder, double-click on the executable file named eclipse (or eclipse.exe for Windows) to start Eclipse.
Set Up Workspace
1.	When Eclipse starts for the first time, it will prompt you to choose a workspace directory where your projects will be stored.
2.	Select a suitable folder location and click "Launch" to proceed.

Install Java Development Kit (JDK)
1.	Ensure you have Java Development Kit (JDK) installed on your system. If not, download and install it from Oracle's website.
2.	Open Eclipse, go to Window > Preferences.
3.	Navigate to Java > Installed JREs and add your installed JDK by clicking on "Add" and selecting the JDK folder.

Installing MySQL Connector

Download MySQL Connector/J
1.	Go to the MySQL Connector/J download page: MySQL Connector/J.
2.	Choose the appropriate platform and click on the download link for the ZIP archive
3.	6.2.3 Extract MySQL Connector/J
4.	Once the download completes, locate the downloaded ZIP file.
5.	Extract the contents of the ZIP archive to a known location on your computer.

Add MySQL Connector/J to Eclipse Project
1.	Open Eclipse IDE where your project resides.
2.	Right-click on your project in the Project Explorer, select Properties.
3.	Navigate to Java Build Path > Libraries > Add External JARs.
4.	Browse and select the mysql-connector-java-x.x.xx.jar file you extracted earlier.
5.	Click "Apply" and then "OK" to add the MySQL Connector/J to your project's build path.

 Installing XAMPP

Download XAMPP
1.	Visit the official XAMPP website: https://www.apachefriends.org/index.html.
2.	Download the version of XAMPP suitable for your operating system (e.g., Windows, macOS, Linux).

Install XAMPP
1.	Once the download completes, locate the downloaded file and run the installer.
2.	Follow the installation instructions provided by the XAMPP installer.
3.	Choose the components you want to install (Apache, MySQL, PHP, etc.).
4.	Select the installation directory (the default is usually recommended).
5.	Complete the installation process by clicking "Next" and then "Finish."

Setting up MySQL in XAMPP
1.	Start XAMPP and Launch MySQL
2.	Open the XAMPP Control Panel (you can find it in the installation directory or via Start Menu on Windows).
3.	Start the Apache and MySQL services by clicking on the "Start" buttons next to their names.

Access phpMyAdmin
1.	Open a web browser and go to http://localhost/phpmyadmin/.
2.	Log in using the default username (root) and leave the password field empty (if you didn't set a password during XAMPP installation).

Create a Database
1.	Inside phpMyAdmin, click on the "Databases" tab.
2.	Enter a name for your database in the "Create database" field and choose a collation (usually utf8_general_ci).
3.	Click "Create" to create the database.

Creating a Dynamic Web Project

Open Eclipse

Launch Eclipse IDE if it's not already open.

Create a Dynamic Web Project:

1.	Go to File > New > Dynamic Web Project.
2.	Enter a name for your project.
3.	Select the target runtime (Apache Tomcat, if available) and set the Dynamic web module version.
4.	Click "Next" and configure further settings if needed.
5.	Click "Finish" to create the Dynamic Web Project.
6.	Importing Your Project:
7.	Open Existing Project in Eclipse:
8.	If your project is not already within the Eclipse workspace:
9.	Go to File > Import.
10.	Choose General > Existing Projects into Workspace.
11.	Click "Next," then select the root directory of your project using the "Browse" button.
12.	Ensure your project is selected in the list and click "Finish" to import it into Eclipse.

Setting up Apache Tomcat Server
1.	Go to the Servers tab at the bottom of the Eclipse window (if you don’t see it, go to Window > Show View > Servers).
2.	Right-click in the Servers tab area and select New > Server.
3.	Choose the version of Apache Tomcat you have installed and click "Next."

Set Tomcat Installation Directory
1.	Click on the "Browse" button next to the "Tomcat installation directory" field.
2.	Navigate to the directory where Apache Tomcat is installed and select it.
3.	Click "Finish" to add the Tomcat server to Eclipse.

Configure Project to Use Tomcat
1.	Right-click on your Dynamic Web Project in Eclipse.
2.	Select Properties > Project Facets.
3.	Check the box for "Dynamic Web Module"
4.	and click "Further configuration available..."
5.	Choose the installed Apache Tomcat server from the dropdown and click "OK."
6.	Click "Apply" and then "OK" to close the project properties.

 Starting Your Project
1.	Right-click on your project in Eclipse.
2.	Select Run As > Run on Server.
3.	Choose the configured Tomcat server and click "Finish" to deploy and run your project.

Access Your Project
1.	Once your project is running on Tomcat, open a web browser.
2.	Enter the URL provided in the Console window of Eclipse (usually http://localhost:8080/FarmToFork).

Project
1.	In the front page we have to click on the Register/Login button in the left down
2.	We enter to the login page if we already have an account we can directly login into the application. If you not have an account you have to register in the application 
3.	In the register we have the option to choose to register as a student or teacher 
4.	If you are a student you can search the teachers by there subject or by there name in there username in application  and you can see the homework given by the teacher’s and see the notification shared by teacher’s if you enrolled to that teacher and access there files uploaded by the teacher
5.	If there no need the account there can delete the account 
6.	In teacher’s side there can upload the files and send the notification to the student and assign the homework to the student’s until the teacher start the session the student can’t join the session 
7.	And teacher can also delete the account if there don’t need
8.	We have the chat option in the session for both student and teacher for interaction
