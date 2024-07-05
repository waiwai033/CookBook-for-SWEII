# Digital Cookbook


<h3>Overview</h3>
The Digital cookbook is an application for showing and building recipes.  Users can see available recipes and search for recipes.
The cookbook will display detailed information including ingredients, needed time for preparation and cooking, the picture of product. Additionally users can create, edit and delete recipes if they want.

<h3>Environment</h3>

> <ol>
> <li>OS: Windows/MacOS</li>
> <li>SDK: Java 18</li>
> <li>IDE: Eclipse/IntelliJ IDEA</li>
> <li>Database: MySQL Server 8+</li>
> <li>Build Tool: Maven(https://maven.apache.org)</li>
> </ol> 

<h4>Before running the project please configure your database first </h4>
<h3>Database configuration</h3>
> <ol>
> <li>Create a database called cookbook in MySQL and execute "use cookbook" command
> <li>Run cookbook.sql on your MySQL bash or MySQL Workbench  </li>
> <li>Modify parameters including username in src->main->resources->jdbc.properties file to adapt to your local database</li>
> <li>connect to your local MySQL</li>
> </ol> 


<h3>How to Run the Maven Project</h3>
<h4>In Maven projects, IntelliJ IDEA or Eclipse usually automatically detects and downloads the required dependencies. </h4>
> <ol>
> <li>Open Eclipse and import project</li>
> <li>Select maven project</li>
> <li>Choose this project directory</li>
> <li>Wait for maven project build during which eclipse will automatically recognize the project and start downloading the necessary dependencies</li>
> <li>Run App.java file in src->main->java->Enter folder </li>
> </ol>

<h3>Tips</h3>
In this project package it has already run environments for both Eclipse and IntelliJ IDEA, you can run the project in either IDE.
And you must have a nice cooking experience with this application!!!

