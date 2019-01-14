# Movie Seeker

This app serves as a front end for 2 different movie search APIs [OMDb](http://www.omdbapi.com/) and [TheMovieDB](https://www.themoviedb.org/documentation/api).
It exposes a REST API endpoint that takes as input a movie title a page number and the preferred API to use a source.
It returns a list of movie title and director name pairs, with as many results as the source API returns per page.

### Design

The app is using Spring Reactive based on Reactor Core instead of Spring MVC and the Servlet API.
Since the data sources are web services running on different servers on probably different geographic location, 
it makes sense to call them asynchronously to avoid blocking multiple threads while waiting for responses.
There is a single controller with the single exposed endpoint on the main package and handlers for the 2 APIs in their own packages.
Exceptions are handled in the error controller and there is validation on the movieTitle and api parameters.
Jackson annotations are used on constructors as reactive does not seem to support the parameter names module.
The tests are actually integration tests as they depend on the source APIs being available. 
The keys to access the source APIs are in *application.properties*  

## Usage

### Directly on the host

Compile the application by going to the root folder and running: 

`mvn spring-boot:run`

and test with curl by running:

`curl -v "http://127.0.0.1:8080/movies/guardians?api=themoviedb&page=1"`

or 

`curl -v "http://127.0.0.1:8080/movies/guardians?api=omdb&page=1"`

Or simply visit these URLs on a browser

### Docker

First build the app with maven:

`mvn clean install -T1C`

and then build the docker image:

`docker image build --tag movie-seeker:1.0 .`

to then run the application with docker:

`docker run --detach --publish=8080:8080 --name=movie-seeker movie-seeker:1.0`

and test as when running directly on the host e.g.

`curl -v "http://127.0.0.1:8080/movies/guardians?api=omdb&page=1"`