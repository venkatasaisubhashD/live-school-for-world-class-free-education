Live School For World-Class Free Education 
Read-Me Document
Installing Eclipse IDE
Download Eclipse
	Visit the Eclipse official website at https://www.eclipse.org/downloads/.
	Choose the appropriate version based on your operating system (e.g., Windows 64-bit, macOS, Linux).
	Click on the "Download" button corresponding to your choice.
install Eclipse
	Once the download completes, locate the downloaded file.
	Extract the downloaded archive file to a location on your computer (e.g., C:\Program Files for Windows).
	There is no formal installation process for Eclipse; it's a portable application that runs directly from its folder.
	Launch Eclipse
Navigate to the folder where you extracted Eclipse
	Inside the Eclipse folder, double-click on the executable file named eclipse (or eclipse.exe for Windows) to start Eclipse.
Set Up Workspace
	When Eclipse starts for the first time, it will prompt you to choose a workspace directory where your projects will be stored.
	Select a suitable folder location and click "Launch" to proceed.
Install Java Development Kit (JDK)
	Ensure you have Java Development Kit (JDK) installed on your system. If not, download and install it from Oracle's website.
	Open Eclipse, go to Window > Preferences.
	Navigate to Java > Installed JREs and add your installed JDK by clicking on "Add" and selecting the JDK folder.
Installing MySQL Connector
Download MySQL Connector/J
	Go to the MySQL Connector/J download page: MySQL Connector/J.
	Choose the appropriate platform and click on the download link for the ZIP archive
	6.2.3 Extract MySQL Connector/J
	Once the download completes, locate the downloaded ZIP file.
	Extract the contents of the ZIP archive to a known location on your computer.
Add MySQL Connector/J to Eclipse Project
	Open Eclipse IDE where your project resides.
	Right-click on your project in the Project Explorer, select Properties.
	Navigate to Java Build Path > Libraries > Add External JARs.
	Browse and select the mysql-connector-java-x.x.xx.jar file you extracted earlier.
	Click "Apply" and then "OK" to add the MySQL Connector/J to your project's build path.
 Installing XAMPP
Download XAMPP
	Visit the official XAMPP website: https://www.apachefriends.org/index.html.
	Download the version of XAMPP suitable for your operating system (e.g., Windows, macOS, Linux).
Install XAMPP
	Once the download completes, locate the downloaded file and run the installer.
	Follow the installation instructions provided by the XAMPP installer.
	Choose the components you want to install (Apache, MySQL, PHP, etc.).
	Select the installation directory (the default is usually recommended).
	Complete the installation process by clicking "Next" and then "Finish."
Setting up MySQL in XAMPP
	Start XAMPP and Launch MySQL
	Open the XAMPP Control Panel (you can find it in the installation directory or via Start Menu on Windows).
	Start the Apache and MySQL services by clicking on the "Start" buttons next to their names.
Access phpMyAdmin
	Open a web browser and go to http://localhost/phpmyadmin/.
	Log in using the default username (root) and leave the password field empty (if you didn't set a password during XAMPP installation).
Create a Database
	Inside phpMyAdmin, click on the "Databases" tab.
	Enter a name for your database in the "Create database" field and choose a collation (usually utf8_general_ci).
	Click "Create" to create the database.
Creating a Dynamic Web Project
Open Eclipse
Launch Eclipse IDE if it's not already open.
Create a Dynamic Web Project:
	Go to File > New > Dynamic Web Project.
	Enter a name for your project.
	Select the target runtime (Apache Tomcat, if available) and set the Dynamic web module version.
	Click "Next" and configure further settings if needed.
	Click "Finish" to create the Dynamic Web Project.
	Importing Your Project:
	Open Existing Project in Eclipse:
	If your project is not already within the Eclipse workspace:
	Go to File > Import.
	Choose General > Existing Projects into Workspace.
	Click "Next," then select the root directory of your project using the "Browse" button.
	Ensure your project is selected in the list and click "Finish" to import it into Eclipse.
Setting up Apache Tomcat Server
	Go to the Servers tab at the bottom of the Eclipse window (if you don’t see it, go to Window > Show View > Servers).
	Right-click in the Servers tab area and select New > Server.
	Choose the version of Apache Tomcat you have installed and click "Next."
Set Tomcat Installation Directory
	Click on the "Browse" button next to the "Tomcat installation directory" field.
	Navigate to the directory where Apache Tomcat is installed and select it.
	Click "Finish" to add the Tomcat server to Eclipse.
Configure Project to Use Tomcat
	Right-click on your Dynamic Web Project in Eclipse.
	Select Properties > Project Facets.
	Check the box for "Dynamic Web Module" and click "Further configuration available..."
	Choose the installed Apache Tomcat server from the dropdown and click "OK."
	Click "Apply" and then "OK" to close the project properties.
 Starting Your Project
	Right-click on your project in Eclipse.
	Select Run As > Run on Server.
	Choose the configured Tomcat server and click "Finish" to deploy and run your project.
Access Your Project
	Once your project is running on Tomcat, open a web browser.
	Enter the URL provided in the Console window of Eclipse (usually http://localhost:8080/FarmToFork).
Project
•	In the front page we have to click on the Register/Login button in the left down
•	We enter to the login page if we already have an account we can directly login into the application. If you not have an account you have to register in the application 
•	In the register we have the option to choose to register as a student or teacher 
•	If you are a student you can search the teachers by there subject or by there name in there username in application  and you can see the homework given by the teacher’s and see the notification shared by teacher’s if you enrolled to that teacher and access there files uploaded by the teacher
•	If there no need the account there can delete the account 
•	In teacher’s side there can upload the files and send the notification to the student and assign the homework to the student’s until the teacher start the session the student can’t join the session 
•	And teacher can also delete the account if there don’t need
•	We have the chat option in the session for both student and teacher for interaction
