

# SRT

Based on the project :  https://github.com/nurkiewicz/spring-data-jdbc-repository.git

This example is a Prototype of a prototype of a pre-alpha version, written
in as an alpha-skunkworks project.

## Sayings

 * Read the error, fix the error.
 * Use tools to get more done faster, with less code.
 * If you do it twice, do it once.
 * And everyone's favorite, Measure twice, cut once.
 * ... Other sayings will pop out of my mouth from time to time.

## Summary

The concepts I intend to show are as follows:
 * Gradle, one project making separate jars for each sub-project.
   * The same as Maven only better.  Uses everything maven, the '.m2'
        directory, Maven repositories, and more.
 * Spring Boot
   * Spring Boot can be integrated into an Apache/Tomcat installiation
        via AJP.  https://tomcat.apache.org/tomcat-7.0-doc/config/ajp.html
        This is not explained further in this example.
 * Intro to Spring Data - one of my favorite things EVER!
   * http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-lookup-strategies
   * Pageable : section 3.4.4/3.4.5 and all over
   * Streams : section 3.4.6
   * Async : section 3.4.7
   * Table 4. Supported keywords inside method names
   * 4.3.7 : Modifying queries
 * QueryDSL - My second favorite thing ever : http://www.querydsl.com/
 * Spring Rest/MVC
 * Demo of SRT - w/ JDBCTemplate (Some Rest Things or TRS spelled backwards)
   * Introducing Spring Data-Lite with JDBCTemplate (stolen and modified)
   * A bit of FHIR in action.
 * Quick walkthrough of Theradoc-Rest-Services
 * Suprise Guest, as in ... but wait ... There's more ....
