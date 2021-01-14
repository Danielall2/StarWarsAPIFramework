# StarWarsAPIFramework

During my seventh week of training at Sparta Global, we were tasked with designing a testing framework for the Star Wars API which is shown in this repository. 

This program is aimed at testers who understand java code.

The designed framework intends to help testers by dealing with JSON objects recieved by the http response when querying specific URLs. Currently this framework deals with 6 endpoints for the URL: "films/", "people/", "planets/", "species/", "starships/" and "vehicles/". The framework can also deal with numbers following these endpoints to deal with specific requests.

The endpoints have been handled so that each type of endpoint has its own Data Transfer Object (DTO) Class. These are grouped based on the fields above, where each of these endpoints with a number after, e.g. "people/1/" or "people/4/" will have the same class.

Additional methods have been added to aid during testing. For example:

  - Methods in each DTO Class which converts links to DTOs.

  - A method to check how many words there are in a String.
  
  - Methods to convert the time in timezones to a simple Local Date.
