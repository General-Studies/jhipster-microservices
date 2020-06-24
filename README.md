# Microservice Architecture Diagram

![Eureka](https://www.jhipster.tech/images/microservices_architecture_detail.001.png)

# Jhipster Registry

https://github.com/jhipster/jhipster-registry

### Config before run

1. open `pom.xml`
2. change `<java.version>11</java.version>` to `<java.version>8</java.version>`
3. find `<release>${java.version}</release>` and comment this line

Perform that commands, the Jipster Registry will be supported by java version 8 instead version 11 (recommend use version 11 because, in soon, the support to java 8 will be removed)

### Clone, install and run

1. `git clone https://github.com/jhipster/jhipster-registry.git registry` 
2. `cd registry && npm install && ./mvnw`

### Run if already exist

1. `cd registry && ./mvnw`

# Docker Services

Before to execute the services deploy, its need execute the script bellow (this script work only to Linux based operation system):
`cd ./docker && sh create-volumes-to-docker-service.sh`

## Deploy

1. `cd ./docker`
2. `sh deploy-services.sh`

## Undeploy

1. `cd ./docker`
2. `sh undeploy-services.sh`

### Keycloak

`keycloak.yml`

The directory `realm-config` contains the realm will be imported to Keycloak on startup.

Its necessary add an configration to `/etc/hosts` to allow use Keycloak inside Docker.

```sh
127.0.0.1	keycloak
```

### Postgres + PgAdmin4

`postgres.yml`

### MongoDB + MongoExpress

`mongodb.yml`

# Youtube helper tutorial

https://www.youtube.com/watch?v=6Gd7OOjqHX0

# Blog Service

0. Which *type* of application would you like to create? `Microservice application`
1. [Alpha] Do you want to make it reactive with Spring WebFlux? `No`
2. What is the base name of your application? `blog`
3. As you are running in a microservice architecture, on which port would like your server to run? It should be unique to avoid port confli
cts. `8081`
4. What is your default Java package name? `com.jetherrodrigues.blog`
5. Which service discovery server do you want to use? `JHipster Registry (uses Eureka, provides Spring Cloud Config support and monitoring dashboards)`
6. Which *type* of authentication would you like to use? `OAuth 2.0 / OIDC Authentication (stateful, works with Keycloak and Okta)`
7. Which *type* of database would you like to use? `SQL (H2, MySQL, MariaDB, PostgreSQL, Oracle, MSSQL)`
8. Which *production* database would you like to use? `PostgreSQL`
9. Which *development* database would you like to use? `PostgreSQL`
10. Do you want to use the Spring cache abstraction? `Yes, with the Hazelcast implementation (distributed cache, for multiple nodes, supports rate-limiting for gateway applications)`
11. Do you want to use Hibernate 2nd level cache? `Yes`
12. Would you like to use Maven or Gradle for building the backend? `Maven`
13. Which other technologies would you like to use? `Search engine using Elasticsearch`
14. Would you like to enable internationalization support? `Yes`
15. Please choose the native language of the application `English`
16. Please choose additional languages to install `Portuguese (Brazilian)`
17. Besides JUnit and Jest, which testing frameworks would you like to use? `Gatling, Cucumber`
18. Would you like to install other generators from the JHipster Marketplace? (y/N) `n`

### JDL

1. `cd blog` 
2. `jhipster import-jdl blog.jdl`

```java
entity Blog {
  name String required minlength(3)
  handle String required minlength(2)
}

entity Post {
  title String required
  content TextBlob required
  date Instant required
}

entity Tag {
  name String required minlength(2)
}

relationship ManyToOne {
  Blog{user(login)} to User
  Post{blog(name)} to Blog
}

relationship ManyToMany {
  Post{tag(name)} to Tag{entry}
}

microservice Blog with blog
microservice Post with blog
microservice Tag with blog
```

![Eureka](https://github.com/General-Studies/jhipster-microservices/blob/master/blog/blog.png)

# Store Service

0. Which *type* of application would you like to create? `Microservice application`
1. [Alpha] Do you want to make it reactive with Spring WebFlux? `No`
2. What is the base name of your application? `store`
3. As you are running in a microservice architecture, on which port would like your server to run? It should be unique to avoid port confli
cts. `8082`
4. What is your default Java package name? `com.jetherrodrigues.store`
5. Which service discovery server do you want to use? `JHipster Registry (uses Eureka, provides Spring Cloud Config support and monitoring dashboards)`
6. Which *type* of authentication would you like to use? `OAuth 2.0 / OIDC Authentication (stateful, works with Keycloak and Okta)`
7. Which *type* of database would you like to use? `SQL (H2, MySQL, MariaDB, PostgreSQL, Oracle, MSSQL)`
8. Which *production* database would you like to use? `PostgreSQL`
9. Which *development* database would you like to use? `PostgreSQL`
10. Do you want to use the Spring cache abstraction? `Yes, with the Hazelcast implementation (distributed cache, for multiple nodes, supports rate-limiting for gateway applications)`
11. Do you want to use Hibernate 2nd level cache? `Yes`
12. Would you like to use Maven or Gradle for building the backend? `Maven`
13. Which other technologies would you like to use? `Search engine using Elasticsearch`
14. Would you like to enable internationalization support? `Yes`
15. Please choose the native language of the application `English`
16. Please choose additional languages to install `Portuguese (Brazilian)`
17. Besides JUnit and Jest, which testing frameworks would you like to use? `Gatling, Cucumber`
18. Would you like to install other generators from the JHipster Marketplace? (y/N) `n`

### JDL

1. `cd store` 
2. `jhipster import-jdl store.jdl`

```java
entity Product {
    sku String required minlength(5) unique
    name String required minlength(3)
    observation String maxlength(256)
    price BigDecimal required
}

microservice Product with store
```

![Eureka](https://github.com/General-Studies/jhipster-microservices/blob/master/store/store.png)


# Gateway Service

0. Which *type* of application would you like to create? `Microservice gateway`
1. [Alpha] Do you want to make it reactive with Spring WebFlux? `No`
2. What is the base name of your application? `gateway`
3. As you are running in a microservice architecture, on which port would like your server to run? It should be unique to avoid port confli
cts. `8080`
4. What is your default Java package name? `com.jetherrodrigues.gateway`
5. Which service discovery server do you want to use? `JHipster Registry (uses Eureka, provides Spring Cloud Config support and monitoring dashboards)`
6. Which *type* of authentication would you like to use? `OAuth 2.0 / OIDC Authentication (stateful, works with Keycloak and Okta)`
7. Which *type* of database would you like to use? `MongoDB`
8. Do you want to use the Spring cache abstraction? `Yes, with the Ehcache implementation (local cache, for a single node)`
9. Would you like to use Maven or Gradle for building the backend? `Maven`
10. Which other technologies would you like to use? (Press <space> to select, <a> to toggle all, <i> to invert selection) `Nothing`
11. Which *Framework* would you like to use for the client? `Angular`
12. Would you like to use a Bootswatch theme (https://bootswatch.com/)? `Lumen`
13. Choose a Bootswatch variant navbar theme (https://bootswatch.com/)? `Primary`
14. Would you like to enable internationalization support? `Yes`
15. Please choose the native language of the application `English`
16. Please choose additional languages to install `Portuguese (Brazilian)`
17. Besides JUnit and Jest, which testing frameworks would you like to use? `Gatling, Cucumber, Protractor`
18. Would you like to install other generators from the JHipster Marketplace? (y/N) `N`

### Import JDL

1. `jhipster import-jdl ../blog/blog.jdl`
1. `jhipster import-jdl ../store/store.jdl`


# Generated Docker Compose Resources

1. `cd docker-compose-msa`
2. `jhipster docker-compose` 

0. Which *type* of application would you like to deploy? `Microservice application`
1. Which *type* of gateway would you like to use? `JHipster gateway based on Netflix Zuul`
2. Enter the root directory where your gateway(s) and microservices are located `../`

`3 applications found at /mnt/hd/dev/general/java/jhipster/jhipster-microservices/`
3. Which applications do you want to include in your configuration? `blog, gateway, store`
4. Which applications do you want to use with clustered databases (only available with MongoDB and Couchbase)? (Press <space> to select, <a
> to toggle all, <i> to invert selection) `Nothing`
5. Do you want to setup monitoring for your applications ? `No`
6. `JHipster registry detected as the service discovery and configuration provider used by your apps` - Enter the admin password used to secure the JHipster Registry `admin`

### Images

To generate the missing Docker image(s), please run:
  `./mvnw -ntp -Pprod verify jib:dockerBuild in ../blog`
  `./mvnw -ntp -Pprod verify jib:dockerBuild in ../gateway`
  `./mvnw -ntp -Pprod verify jib:dockerBuild in ../store`

- `cd docker-compose-msa && docker-compose up -d`

### Docker Services Managment Tool

https://github.com/maxdevjs/kitematic.wiki-my/blob/master/Linux-Install.md

if an error occurred in installation access this link to help to solve the install problem:

https://www.how2shout.com/how-to/how-to-install-kitematic-on-ubuntu-20-04.html
