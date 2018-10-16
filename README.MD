# User CRUD Application with Spring Data JPA with GraphQL

This application serves as a simple GraphQL CRUD example service using a User, Group, Role, and Permission schema. It is pretty
much the same as the 'gradle-spring-data-jpa-user-repository' sample project except with a GraphQL interface instead of a RESTful one. 

I worked on this to learn about GraphQL and salso explore what working with Spring GraphQL Java is like to see what it has to offer. 
I took my existing Spring REST User CRUD sample app, sliced off the "web" package, and converted it to use GraphQL; not too bad.

I document my impressions down below.

##Tech Highlights
* GraphQL Java 
  - Resolvers
  - Error handling
  - GraphiQL UI
* Java 8
  - Stream API
* Spring Boot
  - Profile driven design
* Spring AOP
  - Annotation && method pointcut
* Spring Data
  - Projections
  - Hibernate mappings
  - Validation
  - @Query
    - JPQL
    - Native
* Postgresql

## Getting Started

These instructions will get you up and running on your local machine.

1. Pull the repository in and open it up with your favorite IDE.
2. Install or setup Postgres 10 (I'm not a big fan of using embedded DBs for samples)
  -  I highly suggest using Docker since it's quick!
   ```
     docker run --name user-app-postgres -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_DB=user_app -p5432:5432  -d postgres
   ```
   - Note: If you don't want to keep it, just run it in interactive and "self-desctruct on quit mode" by adding -it --rm to the docker run command
3. If you need to, use your IDE's "run configuration" to overwrite any properties via environment variables
4. Run UserCrudApplication.java
5. Open a browser and navigate to http://localhost:8080/graphiql

NOTE: The GraphQL schemas are inside the /resources/graphl folder.

### Prerequisites
* Postgres 9+
* Java 8 JRE

### My Personal Observations
These are my personal initial thoughts on Spring GraphQL versus Spring REST in this context.

## To The Point
As it stands right now, I'd be hard pressed to suggest using Spring + GraphQL unless there was a very specific need for it.
There are many benefits but I just don't think it's quite there yet (for me) to be all 100% board with it. At least I got to learn 
a bit about GraphQL and learned how to do it "the hard way". I will probably go learn about better ways to implement GraphQL APIs and
definitely continue tracking what is going on with the Spring GraphQL community.

## Pros:
- Interface "controlling" code (a.k.a RestControllers) is significantly reduced.

- No more Swagger documentation & configuration needed, documentation through GraphQL schema is already built-in.
  Those Swagger annotations can get hairy and distracting!

- Projections to the web layer are gone; that's part of what GraphQL solves so that's expected!

- Using GraphQL reduces the possibility of screwing up the API contract. Where
as in REST, people coming from the Servlet, SOAP, or RPC world tend to do REST in a very non-Restful fashion (whatever that means). Additionally,
GraphQL provides an easier path to uniformity instead of having a handful of "This is how we do REST here" guidelines that are often
dismissed for personal opinionated preferences.

- Schema up-front approach that can be shared with clients.

- You can "fairly easily" switch from Rest to GraphQL. Heck, I'm sure you can probably have them both co-existing on the same service? hmm.

## Cons:
- Paging was so much nicer with Spring REST. Since GraphQL only has a "recommended", cursor based, way to page, you have to come up with a
way to translate from one to the other. (I didn't even bother)

- GraphQL schema's can get way out of control and it would be so annoying to implement manually. Just tons of boiler-plate "pipe code". Yuck.

- I can see the appeal of turnkey products that just auto-magically build you a GraphQL service from a Schema. I know there are multiple efforts to make
 all this easier on the Java side but I'm not sure how mature they are and if they truly worth it. I guess that for very small focused microservices,
 it's not too bad. 

## Not assessed (TODO)
- Security. I haven't looked into adding security. I suppose it wouldn't be hard. Add Spring Security, ensure the "/graphql" path is covered, and annotate 
for security permissions at the Service level.

- Multi-interface. I didn't try keeping the original REST API with the GraphQL API on the same app. I don't see why it wouldn't work?