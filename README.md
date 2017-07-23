# **Person REST API**

Its a **Spring Boot** based Java REST API using an embedded H2 Databse instance. Please ensure you have [Java 8 development SDK](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html) installed in your local machine. Then launch the app by executing `gradlew clean bootRun` from the project root directory.

Browse to http://localhost:8080 for index route. The app use HATEOAS to make REST API perusal easier.

The app displays the use of JPQL and appropriate Column indexing to improve data retrieval performance.

*Please note on startup the app loads the csv data into the embedded H2 database, so one must wait a few minutes before running REST queries*.