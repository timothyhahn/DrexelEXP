#DrexelEXP

Travis CI Test

Setup Instructions

1. Get SpringToolSource http://www.springsource.org/downloads/sts-ggts  (Note STS is simply a customized Eclipse with the Spring libraries, but it also takes care of a virtualized application server for you, making set up easeir)
2. Get MySQL setup. http://dev.mysql.com/downloads/ If you are on a Mac or Linux machine, it's probably a good idea to just build it from source or use Homebrew/Package management since the non Windows MySQL tools are fairly confusing and lacking features.
3. Set up MySQL's root password to be "password". This is only for development and the password is changed during deployment to the Tomcat server.`
4. Run the commands in create.sql I've been told MySQL can simply load in a .sql file, but I just copy and paste  them in.
5. Import DrexelEXP as an existing maven project in STS. This will grab most of the dependencies for you. Spring-test was being really weird with Maven, so I simply included the latest copy as a .jar in /lib .
6. Right click on the drexelexp project and "Run as" a "Run on Server" configuration.
7. Visit http://localhost:8080/drexelexp once the project has finished compiling and deploying to the virtual server.
8. Visit http://localhost:8080/ingest to grab all of the subjects and load them into the database. This may take up to a minute.
9. Either click on the "Click me to ingest" text to see a popup alert and then wait for it to ingest all the files (not suggested for testing/dev, we will probably only do this on the prod server, instead do the following)
10. OR Visit http://localhost:8080/ingest/courses/CE/CS and after that has finished loading, either click the link or go to http://localhost:8080/ingest/professor/CE/CS Courses will take maybe a minute, professors a few seconds
11. The project is now ready for development

NOTE TO PROFESSOR: As discussed during our presentation, our tests greatly started to disappear once we had the DAO working as it does now. Like we considered in class, however, that would be a good reason to switch back to the MVC + Services + DAO architecture, but we definitely had too much already going on and ran out of time.

Any questions about Models, Views, Controllers, or Spring should be directed to Timothy Hahn
Any questions about the database or the DAOs should be directed to Chris Harvey
Any questions about unit testing or integration testing sohuld be directed to Martin Barbella

Production Server: http://www.drexelexp.com 
