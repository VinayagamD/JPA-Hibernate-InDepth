## This Repository is used to study about JPA in Depth

----------

### Environment

* IDE
  * [**STS4**](https://spring.io/tools)
  * [**STS3**](https://spring.io/tools3/sts/all)
  * [**Eclipse**](https://www.eclipse.org/downloads/packages/release/2019-03) _Note STS4 or STS3 plugin to be added_
* SDK
  * [**JDK**](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
  * [**Maven**](https://maven.apache.org/download.cgi)
  * **EmbeddedTomcat**
  * [**h2 Database**](http://www.h2database.com/html/tutorial.html)
  
----------

### Dependencies

#### Parent POM

```

<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.2.0.BUILD-SNAPSHOT</version>
	<relativePath/> <!-- lookup parent from repository -->
</parent>
```

#### Properties

```

<properties>
	<java.version>1.8</java.version>
</properties>
```

#### Development Dependencies

```

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>
```

#### Testing Dependencies

```

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
```

----------

**Following Topics Have Been Coverd**

* JPA And Hibernate Setup
* H2 Setup
* Entity
  * Annontation
    * @Table
    * @Column
    * @NamedQuery
    * @NamedQueries
* Repository
  * FindById
  * Unit Test
  * Delete By Id
  * Unit Test
  * Clear
  * Detach
  * Refresh
  * JPQL Basics
  * Native Queries Basic
  * Unit Testing
    * @DirtiesContext
    * @Transactional
* Entity Manager
* Enable H2 Console
* Relationship
  * One To One
    * Defining Relationship
    * Create Sample Entites Student and Passport
    * Defining Relation
    * Fetch
      * Eager **(Default)**
      * Lazy
    * Transaction
    * Persistence Context
    * Bidirectional Relationship
  * One To Many
    * Create Sample Entity Reviews
    * Defining Relationship
    * Retrieving Course and Adding Reviews
    * Generalizing Adding And Removing Review to course
    * Fetch
      * Eager **(Default)**
      * Lazy
  * Many To Many
    * Table Design
    * Defining Relationship between Student And Course
    * Customizing Join Tables
    * Join Queries
    * Fetch
      * Eager
      * Lazy **(Default)**
* Inheritence Hierarchies
  * Mapping Inheritence Hierachies
  * Anontation
    * @Inheritence
      * Single Table
      * Table Per Class
      * Joined
    * @MappedSuperClass
* Queries With JPQL
* Queries With Crieteria API
* Transaction Management
  * ACID
  * @Transactional (javax) vs @Transactional (spring)
  * Isolation Problems
    * Dirty Reads
    * Non Repeatable Reads
    * Phantom Reads
  * Solving Methods
    * @Isolation
      * Read Uncommited
      * Read Commited
      * Repeatable Read
      * Serializable
* Spring Data JPA
  * JpaRepository Interface
  * findById
  * Count
  * CRUD Methods
  * Sorting
  * Paging
    * PageRequest
    * Pageable
    * Page
  * Custom Queries
* Spring Data Rest
