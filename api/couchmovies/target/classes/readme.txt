missing serialVersionUID warning:
    https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
    https://stackoverflow.com/questions/49667888/visual-studio-code-java-disable-serialversionuid-warning

I went ahead and disabled it as in this context I don't really care, but if we use any of this code for 'best practices' I would imagine we want to recommend using the serialVersionUID as 
the flexibility allowed w/ the CB schema probably means some 'gotchyas' when it comes to serialization.

CouchbasePagingAndSortingRepository extends the PagingAndSortingRepository which extends CrudRepository in the org.springframework.data.repository library
org.springframework.data.repository can be found in the spring-data-commons library.

I found that in ActorNameRepository.java and MovieService.java there are reference to a *.findById() method.  That method appears to be a newer implementation.  I had to add the
following dependency in the pom.xml so tha the correct reference was pulled in.  Otherwise it was using version 1.9.x which apparently doesnt have *.findById().

TODO:  understand the java dependency tree and see which lib was pulling in the older version of the spring-data-commons lib

    <dependency>
        <groupId>org.springframework.data</groupId>
        <artifactId>spring-data-commons</artifactId>
        <version>2.0.9.RELEASE</version>
    </dependency>

references:
    org.springframework.data.repository:
        https://docs.spring.io/autorepo/docs/spring-data-commons/1.13.0.M1/api/org/springframework/data/repository/PagingAndSortingRepository.html
    spring-data-commons:
        https://github.com/spring-projects/spring-data-commons
    spring-data-couchbase:
        https://github.com/spring-projects/spring-data-couchbase