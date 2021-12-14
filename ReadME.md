# Spring Boot Mini Bank 

A simple web application that mimics a bank, where you can create customers, open accoutns for them and make transactions on these accounts.

## Getting Started

You may start by cloning the project up and running it on your local machine for development and testing purposes.

### Prerequisites



```
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
```
### Running the application

There are several options to run the application:

1- Using Maven:

```
mvn clean package
java -jar target/MiniBank-0.0.1-SNAPSHOT.jar
```

2. Using Docker

   1. clone the repo
   2. navigate to the project directory
   3. ```docker build -t mini-bank . ```
   4. ```docker run --rm -it -p 8080:8080```



## Running the tests


Tests were written using Mockito, SpringTestMVC and Junit.

To run those tests do:
```
mvn test
```


[comment]: <> (## Deployment)


[comment]: <> (work in progress)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management




## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
