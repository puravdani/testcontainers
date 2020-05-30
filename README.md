# POC for Junit 5 and how to run older Junit files.

## Prerequisites

1. Junit Jupiter Dependencies

### Theory
JUnit 5 packages its components in the org.junit.jupiter group and we need to add the junit-jupiter artifact, which is an aggregator artifact that imports the following dependencies:

* junit-jupiter-api defines the API for writing tests and extensions.
* junit-jupiter-engine is the test engine implementation that runs the unit tests.
* junit-jupiter-params provides support for parameterized tests.

###Filtering by Test Class Names for Maven Surefire
If we want to use the native JUnit 5 support of the Maven Surefire Plugin, we must ensure that at least one test engine implementation is found from the classpath. That’s why we added the junit-jupiter-engine dependency to the test scope when we configured the dependencies of our Maven build.
The Maven Surefire Plugin will scan for test classes whose fully qualified names match the following patterns.

* **/Test*.java
* **/*Test.java
* **/*Tests.java
* **/*TestCase.java
It will exclude all nested classes (including static member classes) by default.

However, you can override this default behavior by configuring explicit `include` and `exclude` rules in your `pom.xml` file. 
For example, to keep Maven Surefire from excluding static member classes, you can override its exclude rules.

```
<build>
    <plugins>
        ...
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0-M4</version>
            <configuration>
                <excludes>
                    <exclude>some test to exclude here</exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Filtering by Tags

You can filter group of classes from running by including / excluding tags or expressions

In Pom File
* To include tags or tag expressions, use groups.
* To exclude tags or tag expressions, use excludedGroups

```
<build>
    <plugins>
        ...
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0-M4</version>
            <configuration>
                <groups>development | !feature-a</groups>
                <excludedGroups>development, regression</excludedGroups>
            </configuration>
        </plugin>
    </plugins>
</build>
```

In ClassFile

```
@Tag("development")
public class ClassATest
{
    @Test
    @Tag("userManagement")
    void testCaseA(TestInfo testInfo) {
    }
}
```
[Apache Maven Surefire Plugin](http://maven.apache.org/surefire/maven-surefire-plugin/examples/junit-platform.html#)
